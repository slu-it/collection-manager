package collectionmanager.persistence.games;

import java.io.File;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
@ConfigurationProperties("persistence.games")
class GamesPersistenceSettings {

    File storageDirectory = new File("data/games");

}
