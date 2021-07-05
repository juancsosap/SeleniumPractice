package steps.automationpractice;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pom.automationpractice.HomePage;
import pom.automationpractice.RegisterPage;
import pom.automationpractice.ResultPage;
import pom.automationpractice.SigninPage;
import steps.PageSteps;
import utils.reports.LogStatus;

public class SigninPageSteps {

    protected SigninPage page;
    protected String step;

    @Given("^Automation Practice Signin Page is Launch$")
    public void launchPage() {
        step = "BDD : Automation Practice Signin Page is Launch";
        PageSteps.reporter.log(LogStatus.INFO, step);

        page = new SigninPage(PageSteps.browser);
        page.go(PageSteps.wait);
    }

    @Given("^Email input and register button elements are present$")
    public void elementsPresent() {
        step = "BDD : Email input and register button elements are present";
        PageSteps.reporter.log(LogStatus.INFO, step);

        PageSteps.asserter.assertActive(SigninPage.inputEmailRegister, PageSteps.wait);
        PageSteps.asserter.assertActive(SigninPage.buttonCreate, PageSteps.wait);
    }

    @Given("^The email (.*) is typed and try to register$")
    public void registerEmail(String value) {
        step = "BDD : The email \"" + value + "\" is typed and try to register";
        PageSteps.reporter.log(LogStatus.INFO, step);

        page.createAccount(value, PageSteps.wait);
    }

    @Then("^(.*) Error is received$")
    public void errorReceived(String message) {
        step = "BDD : \"" + message + "\" Error is received";
        PageSteps.reporter.log(LogStatus.INFO, step);

        String error = "No error generated";
        PageSteps.asserter.assertListSize(SigninPage.errorRegisterMessages, 0, error);
        PageSteps.asserter.assertListContains(SigninPage.errorRegisterMessages, message, error);
    }

    @Then("^Register page is shown$")
    public void registerPageShown() {
        step = "BDD : Register page is shown";
        PageSteps.reporter.log(LogStatus.INFO, step);

        String error = "Register Page doesn't shown";
        PageSteps.asserter.assertActive(RegisterPage.inputFirstname, PageSteps.wait);
    }

}
