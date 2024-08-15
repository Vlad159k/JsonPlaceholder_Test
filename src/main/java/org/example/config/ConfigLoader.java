package org.example.config;

import org.example.model.AppConfig;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static AppConfig config;

    private static void loadProperties() {
        if (config == null) {
            Properties properties = new Properties();
            try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    throw new RuntimeException("Unable to find config.properties");
                }
                properties.load(input);

                config = new AppConfig();
                config.setBaseUrl(properties.getProperty("baseUrl"));
                config.setTimeout(Integer.parseInt(properties.getProperty("timeout")));
            } catch (IOException ex) {
                throw new RuntimeException("Error loading config.properties", ex);
            }
        }
    }

    public static AppConfig getConfig() {
        loadProperties();
        return config;
    }
}