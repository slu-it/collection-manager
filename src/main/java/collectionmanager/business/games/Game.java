package collectionmanager.business.games;

import collectionmanager.business.types.Name;
import collectionmanager.business.types.Progress;
import collectionmanager.business.types.Rating;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Game {

    @NonNull
    private Name name;
    @NonNull
    private Platform platform;

    private Rating rating = Rating.UNRATED;
    private Progress progress = Progress.none();
    private boolean done;

}
