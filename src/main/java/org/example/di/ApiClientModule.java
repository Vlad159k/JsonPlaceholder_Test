package org.example.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.client.JsonPlaceholderClient;
import org.example.config.ConfigLoader;
import org.example.model.AppConfig;
import org.slf4j.*;

public class ApiClientModule extends AbstractModule {

    @Provides
    @Singleton
    public JsonPlaceholderClient provideJsonPlaceholderClient() {
        AppConfig config = ConfigLoader.getConfig();
        return new JsonPlaceholderClient(config.getBaseUrl());
    }

    @Provides
    @Singleton
    public Logger provideLogger() {
        return LoggerFactory.getLogger("ApplicationLogger");
    }
}
