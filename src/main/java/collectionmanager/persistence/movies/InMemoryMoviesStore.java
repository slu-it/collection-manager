package collectionmanager.persistence.movies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import collectionmanager.business.movies.Movie;
import collectionmanager.business.movies.MoviesStore;
import collectionmanager.business.movies.PersistedMovie;
import collectionmanager.business.types.Id;


@Service
class InMemoryMoviesStore implements MoviesStore {

    private Map<Id, Movie> store = new HashMap<>();

    @Override
    public Stream<PersistedMovie> findAll() {
        List<PersistedMovie> movies = new ArrayList<>(store.size());
        store.forEach((id, movie) -> {
            movies.add(PersistedMovie.of(id, movie));
        });
        return movies.stream();
    }

    @Override
    public Optional<PersistedMovie> findById(Id id) {
        return Optional.ofNullable(store.get(id)).map(movie -> PersistedMovie.of(id, movie));
    }

    @Override
    public PersistedMovie create(Movie movie) {
        Id id = Id.generate();
        store.put(id, movie);
        return PersistedMovie.of(id, movie);
    }

    @Override
    public Optional<PersistedMovie> updateById(Id id, Movie movie) {
        if (store.containsKey(id)) {
            store.put(id, movie);
            return Optional.of(PersistedMovie.of(id, movie));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Id id) {
        return store.remove(id) != null;
    }

}
