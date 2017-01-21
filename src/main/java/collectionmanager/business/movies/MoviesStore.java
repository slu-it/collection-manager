package collectionmanager.business.movies;

import java.util.Optional;
import java.util.stream.Stream;

import collectionmanager.business.types.Id;


public interface MoviesStore {

    Stream<PersistedMovie> findAll();

    Optional<PersistedMovie> findById(Id id);

    PersistedMovie create(Movie movie);

    Optional<PersistedMovie> updateById(Id id, Movie movie);

    boolean deleteById(Id id);

}
