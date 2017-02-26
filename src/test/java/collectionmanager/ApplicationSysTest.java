package collectionmanager;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import collectionmanager.api.games.GameResource;


class ApplicationSysTest {

    String indexUrl = "http://localhost:8080/api";

    ConfigurableApplicationContext application;
    RestTemplate template;
    ResourceSupport index;

    @BeforeEach
    void startApplication() {
        System.setProperty("spring.profiles.active", "test");
        application = Application.startInstance();
        template = new RestTemplate();
        index = getIndex();
    }

    @AfterEach
    void stopApplication() {
        application.close();
    }

    @Test
    void crudOperationsForGames() {

        GameResource bloodborne = GameResource.builder()//
            .name("Bloodborne")//
            .platform("PS4")//
            .progress(100)//
            .rating(10)//
            .build();
        bloodborne = createGame(bloodborne);

        GameResource darkSouls3 = GameResource.builder()//
            .name("Dark Souls 3")//
            .platform("PS4")//
            .progress(93)//
            .rating(10)//
            .build();
        darkSouls3 = createGame(darkSouls3);

        GameResource halo5 = GameResource.builder()//
            .name("Halo 5")//
            .platform("XBox One")//
            .progress(100)//
            .rating(7)//
            .build();
        halo5 = createGame(halo5);

        assertThat(getGame(bloodborne)).isEqualTo(bloodborne);
        assertThat(getGame(darkSouls3)).isEqualTo(darkSouls3);
        assertThat(getGame(halo5)).isEqualTo(halo5);

        assertThat(getAllGames()).containsOnly(bloodborne, darkSouls3, halo5);
        assertThat(getAllGamesForPlatform("playstation-4")).containsOnly(bloodborne, darkSouls3);
        assertThat(getAllGamesForPlatform("xbox-one")).containsOnly(halo5);

        halo5.setRating(8);
        halo5 = updateGame(halo5);
        assertThat(halo5.getRating()).isEqualTo(8);
        assertThat(getGame(halo5)).isEqualTo(halo5);

        deleteGame(bloodborne);
        deleteGame(darkSouls3);
        deleteGame(halo5);

        assertThat(getAllGames()).isEmpty();

    }

    private GameResource createGame(GameResource game) {
        String url = index.getLink("games").getHref();
        ResponseEntity<GameResource> response = template.postForEntity(url, game, GameResource.class);
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.CREATED);
        return response.getBody();
    }

    private GameResource getGame(GameResource game) {
        String url = game.getLink("self").getHref();
        ResponseEntity<GameResource> response = template.getForEntity(url, GameResource.class);
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        return response.getBody();
    }

    private Set<GameResource> getAllGames() {
        String url = index.getLink("games").getHref();
        ResponseEntity<GameResource[]> response = template.getForEntity(url, GameResource[].class);
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        return stream(response.getBody()).collect(toSet());
    }

    private Set<GameResource> getAllGamesForPlatform(String platform) {
        String url = index.getLink("games").getHref() + "?platform=" + platform;
        ResponseEntity<GameResource[]> response = template.getForEntity(url, GameResource[].class);
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        return stream(response.getBody()).collect(toSet());
    }

    private GameResource updateGame(GameResource game) {
        String url = game.getLink("self").getHref();
        HttpEntity<GameResource> request = new HttpEntity<>(game);
        ResponseEntity<GameResource> response = template.exchange(url, HttpMethod.PUT, request, GameResource.class);
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        return response.getBody();
    }

    private void deleteGame(GameResource game) {
        String url = game.getLink("self").getHref();
        ResponseEntity<Void> response = template.exchange(url, HttpMethod.DELETE, new HttpEntity(null), Void.class);
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.NO_CONTENT);
    }

    private ResourceSupport getIndex() {
        ResponseEntity<ResourceSupport> response = template.getForEntity(indexUrl, ResourceSupport.class);
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        return response.getBody();
    }

    private String getLink(ResourceSupport resource, String ref) {
        return resource.getLink(ref).getHref();
    }

}
