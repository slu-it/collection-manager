package collectionmanager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import collectionmanager.api.games.GameResource;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ApplicationSysTest {

    String indexUrl = "http://localhost:8080/api";

    TestRestTemplate template = new TestRestTemplate();

    @Test
    void crudOperationsForGames() {

        ResourceSupport index = getIndex();

        GameResource gameToCreate = GameResource.builder()//
            .name("Bloodborne")//
            .platform("PS4")//
            .progress(100)//
            .rating(10)//
            .build();
        String gamesResourceUrl = getLink(index, "games");

        ResponseEntity<GameResource> response = template.postForEntity(gamesResourceUrl, gameToCreate, GameResource.class);
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.CREATED);
        GameResource createdGame = response.getBody();





        System.out.println(createdGame);

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
