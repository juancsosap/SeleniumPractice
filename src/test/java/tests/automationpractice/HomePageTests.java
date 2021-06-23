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
        asserter.assertActive(HomePage.buttonSignin, "Button Signin", 3000);
        asserter.assertActive(HomePage.inputSearch, "Input Search", 3000);
        asserter.assertActive(HomePage.buttonSearch, "Button Search", 3000);
    }

    @Test
    public void pressButtonSigninSuccess() {
        browser.click(HomePage.buttonSignin, 3000);
        asserter.assertText(SigninPage.textHeading, "Authentication", "Signin Page not displayed", 3000);
    }

    @Test
    public void searchSuccess() {
        page.searchProduct(3000, data.get(1));

        asserter.assertText(ResultPage.textHeading, "Search", "Result Page not displayed", 3000);
        asserter.assertListSize(ResultPage.produstResults, 0, "No Result displayed");
    }

}
