package collectionmanager.api.movies;

import org.springframework.stereotype.Component;

import collectionmanager.business.movies.Movie;
import collectionmanager.business.movies.PersistedMovie;
import collectionmanager.commons.Transformer;


@Component
class MovieBoToResourceTransformer implements Transformer<PersistedMovie, MovieResource> {

    @Override
    public MovieResource transform(PersistedMovie persistedMovie) {
        return doTransform(persistedMovie.getMovie());
    }

    private MovieResource doTransform(Movie movie) {
        MovieResource movieResource = new MovieResource();
        movieResource.setName(movie.getName().toString());
        movieResource.setRating(movie.getRating().toInt());
        return movieResource;
    }

}
