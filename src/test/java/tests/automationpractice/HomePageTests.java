package tests.automationpractice;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pom.automationpractice.ResultPage;
import pom.automationpractice.HomePage;
import pom.automationpractice.SigninPage;
import utils.tests.PageTests;

public class HomePageTests extends PageTests<HomePage> {

    @BeforeClass
    @Override
    public void preparePage() {
        page = new HomePage(browser);
    }

    @Test
    public void elementsPresent() {
        asserter.assertActive(HomePage.buttonSignin, "Button Signin", wait);
        asserter.assertActive(HomePage.inputSearch, "Input Search", wait);
        asserter.assertActive(HomePage.buttonSearch, "Button Search", wait);
    }

    @Test
    public void pressButtonSigninSuccess() {
        browser.click(HomePage.buttonSignin, wait);
        asserter.assertText(SigninPage.textHeading, "Authentication", "Signin Page not displayed", wait);
    }

    @Test
    public void searchSuccess() {
        page.searchProduct(wait, data.get(1));

        asserter.assertText(ResultPage.textHeading, "Search", "Result Page not displayed", wait);
        asserter.assertListSize(ResultPage.produstResults, 1, "No Result displayed");
    }

}
