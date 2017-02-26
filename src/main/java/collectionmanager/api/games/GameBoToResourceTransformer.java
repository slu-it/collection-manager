package collectionmanager.api.games;

import org.springframework.stereotype.Component;

import collectionmanager.business.games.Game;
import collectionmanager.business.games.PersistedGame;
import collectionmanager.commons.Transformer;


@Component
class GameBoToResourceTransformer implements Transformer<PersistedGame, GameResource> {

    @Override
    public GameResource transform(PersistedGame persistedGame) {
        return doTransform(persistedGame.getGame());
    }

    private GameResource doTransform(Game game) {
        GameResource gameResource = new GameResource();
        gameResource.setName(game.getName().toString());
        gameResource.setRating(game.getRating().toInt());
        gameResource.setPlatform(game.getPlatform().toString());
        gameResource.setProgress(game.getProgress().toInt());
        gameResource.setDone(game.isDone());
        return gameResource;
    }

}
