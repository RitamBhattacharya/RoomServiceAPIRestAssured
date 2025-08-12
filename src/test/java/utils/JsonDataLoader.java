package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonDataLoader {

    // Loads a JSON file and returns it as a Map<String, Object>
    public static Map<String, Object> loadJsonAsMap(String filePath) {
        ObjectMapper mapper = new ObjectMapper(); // Jackson ObjectMapper for JSON parsing
        try {
            return mapper.readValue(new File(filePath), Map.class); // Read and convert JSON to Map
        } catch (IOException e) {
            // Wrap IOException in RuntimeException for easier handling
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
}
