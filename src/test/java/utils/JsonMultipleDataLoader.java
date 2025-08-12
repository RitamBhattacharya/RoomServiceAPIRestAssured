package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonMultipleDataLoader {

    // Load a single JSON object from file into a Map<String, Object>
    public static Map<String, Object> loadJsonAsMap(String filePath) {
        try {
            return new ObjectMapper().readValue(new File(filePath), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load JSON file: " + filePath, e);
        }
    }

    // Load multiple JSON objects from file into a List<Map<String, Object>>
    public static List<Map<String, Object>> loadJsonAsList(String filePath) {
        try {
            return new ObjectMapper().readValue(new File(filePath), List.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load JSON file: " + filePath, e);
        }
    }
}
