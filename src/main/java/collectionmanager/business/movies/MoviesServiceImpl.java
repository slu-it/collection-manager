package collectionmanager.business.movies;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import collectionmanager.business.exceptions.NotFoundException;
import collectionmanager.business.types.Id;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
class MoviesServiceImpl implements MoviesService {

    private final MoviesStore store;

    @Override
    public Stream<PersistedMovie> get() {
        return store.findAll();
    }

    @Override
    public PersistedMovie get(Id id) throws NotFoundException {
        return store.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    @Override
    public PersistedMovie create(Movie movie) {
        return store.create(movie);
    }

    @Override
    public PersistedMovie update(Id id, Movie movie) throws NotFoundException {
        return store.updateById(id, movie).orElseThrow(() -> new MovieNotFoundException(id));
    }

    @Override
    public void deleteById(Id id) throws NotFoundException {
        boolean deleted = store.deleteById(id);
        if (!deleted) {
            throw new MovieNotFoundException(id);
        }
    }

}
