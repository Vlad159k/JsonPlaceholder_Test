package org.example.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.client.JsonPlaceholderClient;
import org.example.config.ConfigLoader;
import org.example.utils.WordFrequencyAnalyzerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiClientModule extends AbstractModule {

    @Provides
    @Singleton
    public JsonPlaceholderClient provideJsonPlaceholderClient() {
        return new JsonPlaceholderClient(ConfigLoader.getBaseUrl());
    }

    @Provides
    @Singleton
    public Logger provideLogger() {
        return LoggerFactory.getLogger("ApplicationLogger");
    }

    @Provides
    @Singleton
    public WordFrequencyAnalyzerUtil provideWordFrequencyAnalyzer(Logger logger) {
        return new WordFrequencyAnalyzerUtil(logger);
    }
}
