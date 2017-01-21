package collectionmanager.commons;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JsonUtils {

    private final ObjectMapper mapper;

    public <T> T readFromFile(File file, Class<T> valueType) throws IOException {
        return mapper.readValue(file, valueType);
    }

    public void writeToFile(File file, Object value) throws IOException {
        mapper.writeValue(file, value);
    }

}
