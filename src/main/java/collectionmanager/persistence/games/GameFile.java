package collectionmanager.persistence.games;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;


@Data
@JsonInclude(Include.NON_EMPTY)
class GameFile {
    private String name;
    private String platform;
    private Integer rating;
    private int progress;
    private boolean done;
}
