package collectionmanager.persistence.games;

import lombok.Data;


@Data
class GameFile {
    private String name;
    private int rating;
    private String platform;
    private int progress;
    private boolean done;
}
