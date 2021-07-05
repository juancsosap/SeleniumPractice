package steps.automationpractice;

import io.cucumber.java.en.Given;
import pom.automationpractice.RegisterPage;
import steps.PageSteps;
import utils.reports.LogStatus;

public class RegisterPageSteps {

    protected RegisterPage page;
    protected String step;

    @Given("^Automation Practice Register Page is Launch$")
    public void launchPage() {
        step = "BDD : Automation Practice Register Page is Launch";
        PageSteps.reporter.log(LogStatus.INFO, step);

        page = new RegisterPage(PageSteps.browser);
        page.go(PageSteps.wait);
    }

}
