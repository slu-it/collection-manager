package collectionmanager.api.games;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import javax.validation.Valid;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import collectionmanager.business.exceptions.NotFoundException;
import collectionmanager.business.games.Game;
import collectionmanager.business.games.GamesService;
import collectionmanager.business.games.PersistedGame;
import collectionmanager.business.types.Id;
import collectionmanager.commons.Transformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/games", produces = MediaType.APPLICATION_JSON_VALUE)
class GamesController {

    private final GamesService service;
    private final Transformer<PersistedGame, GameDto> boToDtoTf;
    private final Transformer<GameDto, Game> dtoToBoTf;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<GameDto> get() throws NotFoundException {
        return service.get().map(this::transformAndAddSelfLink).collect(toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    GameDto post(@Valid @RequestBody GameDto body) {
        Game game = dtoToBoTf.transform(body);
        PersistedGame persistedGame = service.create(game);
        return transformAndAddSelfLink(persistedGame);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    GameDto getForId(@PathVariable String id) throws NotFoundException {
        PersistedGame persistedGame = service.get(Id.of(id));
        return transformAndAddSelfLink(persistedGame);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    GameDto putForId(@PathVariable String id, @Valid @RequestBody GameDto body) throws NotFoundException {
        Game game = dtoToBoTf.transform(body);
        PersistedGame persistedGame = service.update(Id.of(id), game);
        return transformAndAddSelfLink(persistedGame);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteForId(@PathVariable String id) throws NotFoundException {
        service.deleteById(Id.of(id));
    }

    private GameDto transformAndAddSelfLink(PersistedGame game) {
        GameDto dto = boToDtoTf.transform(game);
        dto.add(selfLink(game));
        return dto;
    }

    private Link selfLink(PersistedGame game) {
        String id = game.getId().toString();
        return linkTo(methodOn(GamesController.class).getForId(id)).withSelfRel();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    void handleNotFoundException(NotFoundException e) {
        log.debug(e.getMessage(), e);
    }

}
