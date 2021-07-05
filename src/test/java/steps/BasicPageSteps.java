package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.reports.ExtentReporter;
import utils.reports.LogStatus;
import utils.tests.Asserter;
import utils.web.Browser;
import utils.web.BrowserSelector;

import java.nio.file.Path;

public class BasicPageSteps {

    protected String step;

    @Given("^Web Browser is Open for test \"(.*)\"$")
    public void openBrowser(String name) {
        step = "BDD : Web Browser is Open for test \"" + name + "\"";

        if(PageSteps.reporter == null)
            PageSteps.reporter = new ExtentReporter(PageSteps.reportFolder + "output.html");
        PageSteps.reporter.begin(name);
        PageSteps.reporter.log(LogStatus.INFO, step);

        String driverPath = Path.of(PageSteps.resourceFolder + "webdriver", "chromedriver.exe").toString();
        PageSteps.browser = new Browser(BrowserSelector.CHROME, driverPath, PageSteps.reporter, 20);
        PageSteps.asserter = new Asserter(PageSteps.browser);
    }

    @Then("^Close the browser$")
    public void closeBrowser() {
        step = "BDD : Close the browser";
        PageSteps.reporter.log(LogStatus.INFO, step);

        PageSteps.browser.close();
        PageSteps.reporter.commit();
    }

}
