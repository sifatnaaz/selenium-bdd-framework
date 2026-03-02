package api.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/api/features",
    glue = {"api.stepDefinitions", "api.hooks"},
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true,
    tags = "@wiremock" 
)
public class ApiTestRunner {}