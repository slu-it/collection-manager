package collectionmanager.business.games;

import collectionmanager.business.exceptions.NotFoundException;
import collectionmanager.business.types.Id;


class GameNotFoundException extends NotFoundException {

    public GameNotFoundException(Id id) {
        super(id, "Game");
    }

}
