package collectionmanager.business.games;

import java.util.Optional;
import java.util.stream.Stream;

import collectionmanager.business.types.Id;


public interface GamesStore {

    Stream<PersistedGame> findAll();

    Optional<PersistedGame> findById(Id id);

    PersistedGame create(Game game);

    Optional<PersistedGame> updateById(Id id, Game game);

    boolean deleteById(Id id);

}
