package collectionmanager.persistence.games;

import static java.util.stream.Collectors.toSet;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import collectionmanager.business.games.Game;
import collectionmanager.business.games.PersistedGame;
import collectionmanager.business.types.Id;
import collectionmanager.commons.Transformer;
import collectionmanager.commons.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@RequiredArgsConstructor
class GamesFileStore {

    private final GamesPersistenceSettings settings;
    private final Transformer<Game, GameFile> boToFileTf;
    private final Transformer<GameFile, Game> fileToBoTf;
    private final JsonUtils jsonUtils;

    @PostConstruct
    void init() {
        File directory = settings.getStorageDirectory();
        if (directory.mkdirs()) {
            log.debug("created data storage directory: {}", directory);
        }
    }

    Set<PersistedGame> readAll() {
        return getFileCandidates().filter(File::isFile).map(this::readFile).collect(toSet());
    }

    private Stream<File> getFileCandidates() {
        File directory = settings.getStorageDirectory();
        File[] files = directory.listFiles();
        if (files == null) {
            throw new IllegalStateException("couldn't read files from directory: " + directory);
        }
        return Arrays.stream(files);
    }

    private PersistedGame readFile(File file) {
        try {
            return doReadFile(file);
        } catch (IOException e) {
            // TODO: handle
            throw new UndeclaredThrowableException(e);
        }
    }

    private PersistedGame doReadFile(File file) throws IOException {
        GameFile gameFile = jsonUtils.readFromFile(file, GameFile.class);
        Game game = fileToBoTf.transform(gameFile);
        Id id = Id.of(file.getName());
        return PersistedGame.of(id, game);
    }

    void store(PersistedGame persistedGame) {
        try {
            doStore(persistedGame);
        } catch (IOException e) {
            // TODO: handle
            throw new UndeclaredThrowableException(e);
        }
    }

    private void doStore(PersistedGame persistedGame) throws IOException {
        String fileName = persistedGame.getId().toString();
        File directory = settings.getStorageDirectory();
        File file = new File(directory, fileName);
        GameFile gameFile = boToFileTf.transform(persistedGame.getGame());
        jsonUtils.writeToFile(file, gameFile);
    }

    void remove(Id id) {
        File file = new File(settings.getStorageDirectory(), id.toString());
        if (file.delete()) {
            log.debug("deleted file: {}", file);
        } else {
            log.error("couldn't delete file: {}", file);
        }
    }

    private static class Filter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            return pathname.isFile();
        }

    }

}
