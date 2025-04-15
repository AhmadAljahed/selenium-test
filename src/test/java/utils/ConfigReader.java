package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();
    private static boolean isInitialized = false;

    // Static initializer to load properties file
    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.error("Unable to find config.properties in classpath");
                throw new IOException("config.properties not found");
            }
            properties.load(input);
            isInitialized = true;
            logger.info("Successfully loaded config.properties");
        } catch (IOException e) {
            logger.error("Failed to load config.properties: ", e);
            isInitialized = false;
        }
    }

    /**
     * Retrieves a property value by key, with an optional default value.
     *
     * @param key          The property key to look up.
     * @param defaultValue The default value to return if the key is not found or properties are not initialized.
     * @return The property value, or the default value if not found.
     */
    public static String getProperty(String key, String defaultValue) {
        if (!isInitialized) {
            logger.warn("Properties not initialized, returning default value for key: {}", key);
            return defaultValue;
        }

        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property '{}' not found, using default value: {}", key, defaultValue);
            return defaultValue;
        }

        logger.debug("Retrieved property '{}': {}", key, value);
        return value;
    }

    /**
     * Retrieves a property value by key, without a default value.
     *
     * @param key The property key to look up.
     * @return The property value, or null if not found or properties are not initialized.
     */
    public static String getProperty(String key) {
        return getProperty(key, null);
    }
}
