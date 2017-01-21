package collectionmanager.business.movies;

import collectionmanager.business.types.Id;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PersistedMovie {

    @Getter
    private final Id id;
    @Getter
    private final Movie movie;

    public static PersistedMovie of(Id id, Movie movie) {
        return new PersistedMovie(id, movie);
    }

}
