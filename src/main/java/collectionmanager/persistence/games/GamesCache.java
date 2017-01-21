package collectionmanager.persistence.games;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import collectionmanager.business.games.PersistedGame;
import collectionmanager.business.types.Id;


@Component
class GamesCache {

    private Map<Id, PersistedGame> cache = new HashMap<>();

    boolean contains(Id id) {
        return cache.containsKey(id);
    }

    Set<PersistedGame> getAll() {
        return new HashSet<>(cache.values());
    }

    Optional<PersistedGame> get(Id id) {
        return Optional.ofNullable(cache.get(id));
    }

    void store(PersistedGame game) {
        cache.put(game.getId(), game);
    }

    void remove(Id id) {
        cache.remove(id);
    }

}
