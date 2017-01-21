package collectionmanager.api.movies;

import org.springframework.stereotype.Component;

import collectionmanager.business.movies.Movie;
import collectionmanager.business.types.Name;
import collectionmanager.business.types.Rating;
import collectionmanager.commons.Transformer;


@Component
class MovieDtoToBoTransformer implements Transformer<MovieDto, Movie> {

    @Override
    public Movie transform(MovieDto dto) {
        Movie bo = new Movie(Name.of(dto.getName()));
        bo.setRating(Rating.of(dto.getRating()));
        return bo;
    }

}
