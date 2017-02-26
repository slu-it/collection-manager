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
    public Game transform(GameResource dto) {
        Game bo = new Game(Name.of(dto.getName()));
        bo.setRating(Rating.of(dto.getRating()));
        bo.setPlatform(Platform.of(dto.getPlatform()));
        bo.setProgress(Progress.of(dto.getProgress()));
        return bo;
    }

}
