package collectionmanager.api.games;

import org.springframework.stereotype.Component;

import collectionmanager.business.games.Game;
import collectionmanager.business.games.Platform;
import collectionmanager.business.types.Name;
import collectionmanager.business.types.Progress;
import collectionmanager.business.types.Rating;
import collectionmanager.commons.Transformer;


@Component
class GameResourceToBoTransformer implements Transformer<GameResource, Game> {

    @Override
    public Game transform(GameResource resource) {

        Name name = Name.of(resource.getName());
        Platform platform = Platform.of(resource.getPlatform());

        Game bo = new Game(name, platform);
        bo.setRating(Rating.of(resource.getRating()));
        bo.setProgress(Progress.of(resource.getProgress()));
        bo.setDone(resource.isDone());
        return bo;

    }

}
