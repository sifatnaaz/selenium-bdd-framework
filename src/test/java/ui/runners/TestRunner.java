package ui.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/ui/features",
    glue = {"ui.stepDefinitions","ui.hooks"},
    plugin = {"pretty", "html:target/cucumber-report.html"},
    monochrome = true,
    tags= "@reg"
)
public class TestRunner {}