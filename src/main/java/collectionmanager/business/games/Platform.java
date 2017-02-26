package collectionmanager.business.games;

import static java.util.Arrays.stream;

import java.util.HashSet;
import java.util.Set;


public enum Platform {

    PLAYSTATION_3("PS3", "PlayStation 3", "playstation-3"),
    PLAYSTATION_4("PS4", "PlayStation 4", "playstation-4"),
    PLAYSTATION_VITA("VITA", "PlayStation Vita"),

    XBOX_ONE("XB1", "Xbox One", "xbox-one"),

    ALL("");

    private String code;
    private Set<String> aliases;

    Platform(String code, String... aliases) {
        this.code = code;
        this.aliases = new HashSet<>();
        this.aliases.add(code.toLowerCase());
        stream(aliases).map(String::toLowerCase).forEach(this.aliases::add);
    }

    @Override
    public String toString() {
        return code;
    }

    public static Platform of(String name) {
        String lowerCasedName = name.toLowerCase();
        return stream(values())//
            .filter(platform -> platform.aliases.contains(lowerCasedName))//
            .findFirst().orElseThrow(() -> new IllegalArgumentException("unknown platform: " + name));
    }

}
