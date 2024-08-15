package base;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.example.di.ApiClientModule;
import org.junit.jupiter.api.BeforeEach;
import steps.PostAssertionSteps;
import steps.PostSteps;

public abstract class TestBase {
    protected PostSteps postSteps;
    protected PostAssertionSteps postAssertionSteps;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new ApiClientModule());
        postSteps = injector.getInstance(PostSteps.class);
        postAssertionSteps = new PostAssertionSteps();
        RestAssured.filters(new AllureRestAssured());
    }
}
