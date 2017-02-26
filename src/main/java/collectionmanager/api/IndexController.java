package collectionmanager.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import collectionmanager.api.games.GamesController;
import collectionmanager.api.movies.MoviesController;


@RestController
@RequestMapping("/api")
public class IndexController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResourceSupport get() {
        ResourceSupport response = new ResourceSupport();
        response.add(linkTo(methodOn(MoviesController.class).get()).withRel("movies"));
        response.add(linkTo(methodOn(GamesController.class).get(null)).withRel("games"));
        return response;
    }

}
