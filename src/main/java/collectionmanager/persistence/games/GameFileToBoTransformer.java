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
    public Game transform(GameFile file) {

        Name name = Name.of(file.getName());
        Platform platform = Platform.of(file.getPlatform());

        Game game = new Game(name, platform);
        game.setRating(Rating.of(file.getRating()));
        game.setProgress(Progress.of(file.getProgress()));
        game.setDone(file.isDone());
        return game;

    }

}
