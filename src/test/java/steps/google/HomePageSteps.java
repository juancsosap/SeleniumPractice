package steps.google;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pom.google.HomePage;
import pom.google.ResultPage;
import steps.PageSteps;
import utils.reports.LogStatus;

public class HomePageSteps {

    protected HomePage page;
    protected String step;

    @Given("^Google Home Page is Launch$")
    public void launchPage() {
        step = "BDD : Google Home Page is Launch";
        PageSteps.reporter.log(LogStatus.INFO, step);

        page = new HomePage(PageSteps.browser);
        page.go(PageSteps.wait);
    }

    @Given("^Search input and button elements are present$")
    public void elementsPresent() {
        step = "BDD : Search input and button elements are present";
        PageSteps.reporter.log(LogStatus.INFO, step);

        PageSteps.asserter.assertActive(HomePage.inputQuery, PageSteps.wait);
        PageSteps.asserter.assertActive(HomePage.buttonSearch, PageSteps.wait);
    }

    @Given("^The value (.*) is typed and searched$")
    public void searchValue(String value) {
        step = "BDD : The value \"" + value + "\" is typed and searched";
        PageSteps.reporter.log(LogStatus.INFO, step);

        page.searchText(value, PageSteps.wait);
    }

    @Then("^Result page is shown$")
    public void resultPageShown() {
        step = "BDD : Result page is shown";
        PageSteps.reporter.log(LogStatus.INFO, step);

        String error = "The Search couldn't be completed";
        PageSteps.asserter.assertListSize(ResultPage.results, 0, error);
    }

}
