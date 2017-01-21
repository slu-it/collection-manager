package collectionmanager.business.games;

import collectionmanager.business.types.Id;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PersistedGame {

    @Getter
    private final Id id;
    @Getter
    private final Game game;

    public static PersistedGame of(Id id, Game game) {
        return new PersistedGame(id, game);
    }

}
