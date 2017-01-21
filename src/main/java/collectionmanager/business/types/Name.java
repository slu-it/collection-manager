package collectionmanager.business.types;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;


@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Name {

    private final String name;

    @Override
    public String toString() {
        return name;
    }

    public static Name of(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name mustn't be blank!");
        }
        return new Name(name);
    }

}
