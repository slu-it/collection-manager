package collectionmanager.business.games;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import collectionmanager.business.exceptions.NotFoundException;
import collectionmanager.business.types.Id;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
class GamesServiceImpl implements GamesService {

    private final GamesPersistenceService store;

    @Override
    public Stream<PersistedGame> get(Platform platform) {
        if (platform == Platform.ALL) {
            return store.findAll();
        }
        return store.findAllForPlatform(platform);
    }

    @Override
    public PersistedGame get(Id id) throws NotFoundException {
        return store.findById(id).orElseThrow(() -> new GameNotFoundException(id));
    }

    @Override
    public PersistedGame create(Game game) {
        return store.create(game);
    }

    @Override
    public PersistedGame update(Id id, Game game) throws NotFoundException {
        return store.updateById(id, game).orElseThrow(() -> new GameNotFoundException(id));
    }

    @Override
    public void deleteById(Id id) throws NotFoundException {
        boolean deleted = store.deleteById(id);
        if (!deleted) {
            throw new GameNotFoundException(id);
        }
    }

}
