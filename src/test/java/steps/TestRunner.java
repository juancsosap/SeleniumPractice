package steps;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"steps"},
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/reports/cucumber.html",
                "json:target/reports/cucumber.json"
        }
)

public class TestRunner extends AbstractTestNGCucumberTests {
}
