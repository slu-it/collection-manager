package collectionmanager.persistence.games;

import org.springframework.stereotype.Component;

import collectionmanager.business.games.Game;
import collectionmanager.commons.Transformer;


@Component
class GameBoToFileTransformer implements Transformer<Game, GameFile> {

    @Override
    public GameFile transform(Game game) {
        GameFile gameFile = new GameFile();
        gameFile.setName(game.getName().toString());
        gameFile.setRating(game.getRating().toInt());
        gameFile.setPlatform(game.getPlatform().toString());
        gameFile.setProgress(game.getProgress().toInt());
        gameFile.setDone(game.isDone());
        return gameFile;
    }

}
