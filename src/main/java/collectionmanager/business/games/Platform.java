package collectionmanager.business.games;

import static java.util.Arrays.stream;

import java.util.HashSet;
import java.util.Set;


public enum Platform {

    UNKNOWN("unknown"),
    STEAM("Steam"),
    PLAYSTATION_3("PlayStation 3", "playstation-3", "PS3"),
    PLAYSTATION_4("PlayStation 4", "playstation-4", "PS4"),
    XBOX_ONE("Xbox One", "xbox-one", "XB1"),
    ALL("all");

    private String name;
    private Set<String> aliases;

    Platform(String name, String... aliases) {
        this.name = name;
        this.aliases = new HashSet<>();
        this.aliases.add(name.toLowerCase());
        stream(aliases).map(String::toLowerCase).forEach(this.aliases::add);
    }

    @Override
    public String toString() {
        return name;
    }

    public static Platform of(String name) {
        String lowerCasedName = name.toLowerCase();
        return stream(values())//
            .filter(platform -> platform.aliases.contains(lowerCasedName))//
            .findFirst().orElseThrow(() -> new IllegalArgumentException("unknown platform: " + name));
    }

}
