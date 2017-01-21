package collectionmanager.business.movies;

import java.util.stream.Stream;

import collectionmanager.business.exceptions.NotFoundException;
import collectionmanager.business.types.Id;


public interface MoviesService {

    Stream<PersistedMovie> get();

    PersistedMovie get(Id id) throws NotFoundException;

    PersistedMovie create(Movie movie);

    PersistedMovie update(Id id, Movie movie) throws NotFoundException;

    void deleteById(Id id) throws NotFoundException;

}
