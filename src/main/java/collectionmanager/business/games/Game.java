package collectionmanager.business.games;

import collectionmanager.business.types.Name;
import collectionmanager.business.types.Progress;
import collectionmanager.business.types.Rating;
import lombok.Data;


@Data
public class Game {

    private Name name;
    private Rating rating = Rating.UNRATED;
    private Platform platform = Platform.UNKNOWN;
    private Progress progress = Progress.none();
    private boolean done;

    public Game(Name name) {
        this.name = name;
    }

}
