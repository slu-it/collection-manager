package collectionmanager.persistence.games;

import org.springframework.stereotype.Component;

import collectionmanager.business.games.Game;
import collectionmanager.business.games.Platform;
import collectionmanager.business.types.Name;
import collectionmanager.business.types.Progress;
import collectionmanager.business.types.Rating;
import collectionmanager.commons.Transformer;


@Component
class GameFileToBoTransformer implements Transformer<GameFile, Game> {

    @Override
    public Game transform(GameFile gameFile) {
        Game game = new Game(Name.of(gameFile.getName()));
        game.setRating(Rating.of(gameFile.getRating()));
        game.setPlatform(Platform.of(gameFile.getPlatform()));
        game.setProgress(Progress.of(gameFile.getProgress()));
        game.setDone(gameFile.isDone());
        return game;
    }

}
