package collectionmanager.business.types;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;


@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Progress {

    private final int value;

    @Override
    public String toString() {
        return value + "%";
    }

    public int toInt() {
        return value;
    }

    public static Progress of(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("progress must be between 0% and 100%!");
        }
        return new Progress(percent);
    }

    public static Progress none() {
        return of(0);
    }

}
