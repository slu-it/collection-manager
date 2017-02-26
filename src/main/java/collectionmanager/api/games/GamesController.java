package collectionmanager.api.games;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;
import java.util.stream.Stream;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import collectionmanager.business.exceptions.NotFoundException;
import collectionmanager.business.games.Game;
import collectionmanager.business.games.GamesService;
import collectionmanager.business.games.PersistedGame;
import collectionmanager.business.games.Platform;
import collectionmanager.business.types.Id;
import collectionmanager.commons.Transformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/games", produces = MediaType.APPLICATION_JSON_VALUE)
public class GamesController {

    private final GamesService service;
    private final Transformer<PersistedGame, GameResource> boToDtoTf;
    private final Transformer<GameResource, Game> dtoToBoTf;

    /* CRUD OPERATIONS */

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = GET)
    public List<GameResource> get(@RequestParam(required = false) String platform) throws NotFoundException {
        Stream<PersistedGame> games;
        if (platform != null) {
            games = service.get(Platform.of(platform));
        } else {
            games = service.get(Platform.ALL);
        }
        return games.map(this::transformAndAddSelfLink).collect(toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = POST)
    public GameResource post(@Valid @RequestBody GameResource body) {
        Game game = dtoToBoTf.transform(body);
        PersistedGame persistedGame = service.create(game);
        return transformAndAddSelfLink(persistedGame);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{id}", method = GET)
    public GameResource getForId(@PathVariable String id) throws NotFoundException {
        PersistedGame persistedGame = service.get(Id.of(id));
        return transformAndAddSelfLink(persistedGame);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{id}", method = PUT)
    public GameResource putForId(@PathVariable String id, @Valid @RequestBody GameResource body) throws NotFoundException {
        Game game = dtoToBoTf.transform(body);
        PersistedGame persistedGame = service.update(Id.of(id), game);
        return transformAndAddSelfLink(persistedGame);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = DELETE)
    public void deleteForId(@PathVariable String id) throws NotFoundException {
        service.deleteById(Id.of(id));
    }

    /* OTHER OPERATIONS */

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{id}/markAsDone", method = POST)
    public GameResource markAsDoneForId(@PathVariable String id) throws NotFoundException {
        PersistedGame persistedGame = service.markAsDoneById(Id.of(id));
        return transformAndAddSelfLink(persistedGame);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{id}/markAsNotDone", method = POST)
    public GameResource markAsNotDoneForId(@PathVariable String id) throws NotFoundException {
        PersistedGame persistedGame = service.markAsNotDoneById(Id.of(id));
        return transformAndAddSelfLink(persistedGame);
    }

    /* EXCEPTION HANDLING */

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    void handleNotFoundException(NotFoundException e) {
        log.debug(e.getMessage(), e);
    }

    /* UTILITIES */

    private GameResource transformAndAddSelfLink(PersistedGame persistedGame) {
        GameResource dto = boToDtoTf.transform(persistedGame);

        String id = persistedGame.getId().toString();
        dto.add(linkTo(methodOn(GamesController.class).getForId(id)).withSelfRel());

        Game game = persistedGame.getGame();
        if (game.isDone()) {
            dto.add(linkTo(methodOn(GamesController.class).markAsNotDoneForId(id)).withRel("markAsNotDone"));
        } else {
            dto.add(linkTo(methodOn(GamesController.class).markAsDoneForId(id)).withRel("markAsDone"));
        }

        return dto;
    }

}
