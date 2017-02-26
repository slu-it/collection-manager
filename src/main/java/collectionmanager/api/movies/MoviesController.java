package collectionmanager.api.movies;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import javax.validation.Valid;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import collectionmanager.business.exceptions.NotFoundException;
import collectionmanager.business.movies.Movie;
import collectionmanager.business.movies.MoviesService;
import collectionmanager.business.movies.PersistedMovie;
import collectionmanager.business.types.Id;
import collectionmanager.commons.Transformer;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MoviesController {

    private final MoviesService service;
    private final Transformer<PersistedMovie, MovieResource> boToDtoTf;
    private final Transformer<MovieResource, Movie> dtoToBoTf;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResource> get() throws NotFoundException {
        return service.get().map(this::transformAndAddSelfLink).collect(toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResource post(@Valid @RequestBody MovieResource body) {
        Movie movie = dtoToBoTf.transform(body);
        PersistedMovie persistedMovie = service.create(movie);
        return transformAndAddSelfLink(persistedMovie);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieResource getForId(@PathVariable String id) throws NotFoundException {
        PersistedMovie persistedMovie = service.get(Id.of(id));
        return transformAndAddSelfLink(persistedMovie);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieResource putForId(@PathVariable String id, @Valid @RequestBody MovieResource body) throws NotFoundException {
        Movie movie = dtoToBoTf.transform(body);
        PersistedMovie persistedMovie = service.update(Id.of(id), movie);
        return transformAndAddSelfLink(persistedMovie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteForId(@PathVariable String id) throws NotFoundException {
        service.deleteById(Id.of(id));
    }

    private MovieResource transformAndAddSelfLink(PersistedMovie movie) {
        MovieResource dto = boToDtoTf.transform(movie);
        dto.add(selfLink(movie));
        return dto;
    }

    private Link selfLink(PersistedMovie movie) {
        String id = movie.getId().toString();
        return linkTo(methodOn(MoviesController.class).getForId(id)).withSelfRel();
    }

}
