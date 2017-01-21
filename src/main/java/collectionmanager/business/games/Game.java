package collectionmanager.business.games;

import collectionmanager.business.types.Name;
import collectionmanager.business.types.Progress;
import collectionmanager.business.types.Rating;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@ToString
@EqualsAndHashCode
public class Game {

    private Name name;
    private Rating rating = Rating.UNRATED;
    private Platform platform = Platform.UNKNOWN;
    private Progress progress = Progress.none();

    public Game(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Rating getRating() {
        return rating;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

}
