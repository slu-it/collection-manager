package collectionmanager.business.games;

import java.util.stream.Stream;

import collectionmanager.business.exceptions.NotFoundException;
import collectionmanager.business.types.Id;


public interface GamesService {

    Stream<PersistedGame> get();

    PersistedGame get(Id id) throws NotFoundException;

    PersistedGame create(Game game);

    PersistedGame update(Id id, Game game) throws NotFoundException;

    void deleteById(Id id) throws NotFoundException;

}
