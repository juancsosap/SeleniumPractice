package tests.google;

import org.testng.annotations.*;
import pom.google.HomePage;
import pom.google.ResultPage;
import utils.tests.PageTests;

public class HomePageTests extends PageTests<HomePage> {

    @BeforeClass
    @Override
    public void preparePage() {
        page = new HomePage(browser);
    }

    @Test
    public void inputSearchPresent() {
        asserter.assertActive(HomePage.inputQuery, wait);
    }

    @Test
    public void buttonSearchPresent() {
        asserter.assertActive(HomePage.buttonSearch, wait);
    }

    @Test
    public void completeSearchSuccess() {
        page.searchText(data.get(1), wait);
        asserter.assertListSize(ResultPage.results, data.getInt(2), data.get(3));
    }

}
