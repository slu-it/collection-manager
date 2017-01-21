package collectionmanager.business.types;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;


@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Id {

    private final UUID uuid;

    @Override
    public String toString() {
        return uuid.toString();
    }

    public static Id generate() {
        return new Id(UUID.randomUUID());
    }

    public static Id of(String value) {
        return new Id(UUID.fromString(value));
    }

}
