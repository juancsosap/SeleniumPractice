package steps.automationpractice;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pom.automationpractice.HomePage;
import pom.automationpractice.ResultPage;
import pom.automationpractice.SigninPage;
import steps.PageSteps;
import utils.reports.LogStatus;

public class HomePageSteps {

    protected HomePage page;
    protected String step;

    @Given("^Automation Practice Home Page is Launch$")
    public void launchPage() {
        step = "BDD : Automation Practice Home Page is Launch";
        PageSteps.reporter.log(LogStatus.INFO, step);

        page = new HomePage(PageSteps.browser);
        page.go(PageSteps.wait);
    }

    @Given("^Search Product input and button elements are present$")
    public void elementsPresent() {
        step = "BDD : Search Product input and button elements are present";
        PageSteps.reporter.log(LogStatus.INFO, step);

        PageSteps.asserter.assertActive(HomePage.inputSearch, PageSteps.wait);
        PageSteps.asserter.assertActive(HomePage.buttonSearch, PageSteps.wait);
    }

    @Given("^The value (.*) is typed and searched a Product$")
    public void searchValue(String value) {
        step = "BDD : The value \"" + value + "\" is typed and searched a Product";
        PageSteps.reporter.log(LogStatus.INFO, step);

        page.searchProduct(value, PageSteps.wait);
    }

    @Then("^Result page is shown with the products$")
    public void resultPageShown() {
        step = "BDD : Result page is shown with the products";
        PageSteps.reporter.log(LogStatus.INFO, step);

        String error = "The Search couldn't be completed";
        PageSteps.asserter.assertListSize(ResultPage.produstResults, 0, error);
    }

    @Given("^Signin button element is present$")
    public void signinButtonPresent() {
        step = "BDD : Signin button element is present";
        PageSteps.reporter.log(LogStatus.INFO, step);

        PageSteps.asserter.assertActive(HomePage.buttonSignin, PageSteps.wait);
    }

    @Given("^Click the Signin button element$")
    public void clickSigninButton() {
        step = "BDD : Click the Signin button element";
        PageSteps.reporter.log(LogStatus.INFO, step);

        PageSteps.browser.click(HomePage.buttonSignin, PageSteps.wait);
    }

    @Then("^Signin page is shown$")
    public void signinPageShown() {
        step = "BDD : Signin page is shown";
        PageSteps.reporter.log(LogStatus.INFO, step);

        String error = "Signin Page doesn't shown";
        PageSteps.asserter.assertText(SigninPage.textHeading, "Authentication", error, PageSteps.wait);
    }

}
