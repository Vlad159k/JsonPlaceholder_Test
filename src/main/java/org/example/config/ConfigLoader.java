package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties;

    private static void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    throw new RuntimeException("Unable to find config.properties");
                }
                properties.load(input);
            } catch (IOException ex) {
                throw new RuntimeException("Error loading config.properties", ex);
            }
        }
    }

    public static String getBaseUrl() {
        loadProperties();
        return properties.getProperty("baseUrl");
    }
}