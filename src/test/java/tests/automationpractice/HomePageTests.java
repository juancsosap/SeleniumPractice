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
        asserter.assertActive(HomePage.buttonSignin, wait);
        asserter.assertActive(HomePage.inputSearch, wait);
        asserter.assertActive(HomePage.buttonSearch, wait);
    }

    @Test
    public void pressButtonSigninSuccess() {
        browser.click(HomePage.buttonSignin, wait);
        asserter.assertText(SigninPage.textHeading, data.get(1), data.get(2), wait);
    }

    @Test
    public void searchSuccess() {
        page.searchProduct(data.get(1), wait);

        asserter.assertText(ResultPage.textHeading, data.get(2), data.get(3), wait);
        asserter.assertListSize(ResultPage.produstResults, data.getInt(4), data.get(5));
    }

}
