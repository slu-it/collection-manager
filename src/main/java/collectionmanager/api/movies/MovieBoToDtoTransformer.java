package collectionmanager.api.movies;

import org.springframework.stereotype.Component;

import collectionmanager.business.movies.Movie;
import collectionmanager.business.movies.PersistedMovie;
import collectionmanager.commons.Transformer;


@Component
class MovieBoToDtoTransformer implements Transformer<PersistedMovie, MovieDto> {

    @Override
    public MovieDto transform(PersistedMovie persistedMovie) {
        return doTransform(persistedMovie.getMovie());
    }

    private MovieDto doTransform(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setName(movie.getName().toString());
        movieDto.setRating(movie.getRating().toInt());
        return movieDto;
    }

}
