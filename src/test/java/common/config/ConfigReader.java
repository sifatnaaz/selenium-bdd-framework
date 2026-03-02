package common.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private final Properties prop = new Properties();

    public ConfigReader(String configBaseName) {
        // env can be: local / qa / dev / stage / prod
        String env = System.getProperty("env", "qa").trim().toLowerCase();

        // Example: apiConfig.qa.properties
        String fileName = "config/" + configBaseName + "." + env + ".properties";
        System.out.println("fileName is: "+ fileName);

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new RuntimeException("Config file not found on classpath: " + fileName);
            }
            prop.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config: " + fileName, e);
        }
    }

 // raw access (no validation)
    private String getRaw(String key) {
        return prop.getProperty(key);
    }

    // REQUIRED FIELDS (throws if missing or empty)
    public String getProperty(String key) {
        String value = getRaw(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Missing required property: " + key);
        }
        return value.trim();
    }

    // OPTIONAL (returns null if missing/empty)
    public String getOptionalProperty(String key) {
        String value = getRaw(key);
        if (value == null) return null;
        value = value.trim();
        return value.isEmpty() ? null : value;
    }
}