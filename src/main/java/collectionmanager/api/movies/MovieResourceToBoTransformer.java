package collectionmanager.api.movies;

import org.springframework.stereotype.Component;

import collectionmanager.business.movies.Movie;
import collectionmanager.business.types.Name;
import collectionmanager.business.types.Rating;
import collectionmanager.commons.Transformer;


@Component
class MovieResourceToBoTransformer implements Transformer<MovieResource, Movie> {

    @Override
    public Movie transform(MovieResource dto) {
        Movie bo = new Movie(Name.of(dto.getName()));
        bo.setRating(Rating.of(dto.getRating()));
        return bo;
    }

}
