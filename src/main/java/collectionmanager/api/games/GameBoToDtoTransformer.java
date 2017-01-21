package collectionmanager.api.games;

import org.springframework.stereotype.Component;

import collectionmanager.business.games.Game;
import collectionmanager.business.games.PersistedGame;
import collectionmanager.commons.Transformer;


@Component
class GameBoToDtoTransformer implements Transformer<PersistedGame, GameDto> {

    @Override
    public GameDto transform(PersistedGame persistedGame) {
        return doTransform(persistedGame.getGame());
    }

    private GameDto doTransform(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setName(game.getName().toString());
        gameDto.setRating(game.getRating().toInt());
        gameDto.setPlatform(game.getPlatform().toString());
        gameDto.setProgress(game.getProgress().toInt());
        return gameDto;
    }

}
