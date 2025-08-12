package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    // Properties object to hold config key-value pairs
    private static Properties props = new Properties();

    // Static block to load config.properties at class load time
    static {
        try (InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("config.properties")) {
            props.load(is); // Load properties from file
        } catch (Exception e) {
            // Throw runtime exception if loading fails
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    // Retrieve property value by key
    public static String get(String key) {
        return props.getProperty(key);
    }
}
