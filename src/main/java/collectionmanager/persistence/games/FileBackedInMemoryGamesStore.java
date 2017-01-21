package collectionmanager.persistence.games;

import java.util.Optional;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import collectionmanager.business.games.Game;
import collectionmanager.business.games.GamesStore;
import collectionmanager.business.games.PersistedGame;
import collectionmanager.business.types.Id;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
class FileBackedInMemoryGamesStore implements GamesStore {

    private final GamesCache cache;
    private final GamesFileStore fileStore;

    @PostConstruct
    public void init() {
        fileStore.readAll().forEach(cache::store);
    }

    @Override
    public Stream<PersistedGame> findAll() {
        return cache.getAll().stream();
    }

    @Override
    public Optional<PersistedGame> findById(Id id) {
        return cache.get(id);
    }

    @Override
    public PersistedGame create(Game game) {
        PersistedGame persistedGame = PersistedGame.of(Id.generate(), game);
        fileStore.store(persistedGame);
        cache.store(persistedGame);
        return persistedGame;
    }

    @Override
    public Optional<PersistedGame> updateById(Id id, Game game) {
        if (cache.contains(id)) {
            PersistedGame persistedGame = PersistedGame.of(id, game);
            fileStore.store(persistedGame);
            cache.store(persistedGame);
            return Optional.of(persistedGame);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Id id) {
        if (cache.contains(id)) {
            fileStore.remove(id);
            cache.remove(id);
            return true;
        }
        return false;
    }

}
