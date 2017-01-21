package collectionmanager.business.movies;

import collectionmanager.business.types.Name;
import collectionmanager.business.types.Rating;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@ToString
@EqualsAndHashCode
public class Movie {

    private Name name;
    private Rating rating = Rating.UNRATED;

    public Movie(Name name) {
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

}
