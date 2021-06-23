package tests.google;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pom.google.HomePage;
import pom.google.ResultPage;
import utils.tests.PageTests;

import java.util.List;

public class HomePageTests extends PageTests<HomePage> {

    @BeforeClass
    @Override
    public void preparePage() {
        page = new HomePage(browser);
    }

    @Test
    public void inputSearchPresent() {
        browser.highlight(HomePage.inputQuery, 3000);
        WebElement element = browser.getElement(HomePage.inputQuery);

        Assert.assertTrue(element.isDisplayed(), "Input Search element not displayed");
        Assert.assertTrue(element.isEnabled(), "Input Search element not enabled");
    }

    @Test
    public void buttonSearchPresent() {
        browser.highlight(HomePage.buttonSearch, 3000);
        WebElement element = browser.getElement(HomePage.buttonSearch);

        Assert.assertTrue(element.isDisplayed(), "Button Search element not displayed");
        Assert.assertTrue(element.isEnabled(), "Button Search element not enabled");
    }

    @Test
    public void completeSearchSuccess() {
        browser.highlight(HomePage.inputQuery, 3000);
        browser.getElement(HomePage.inputQuery).sendKeys(data.get(1));
        browser.highlight(HomePage.buttonSearch, 3000);
        browser.getElement(HomePage.buttonSearch).submit();

        List<WebElement> elements = browser.getElements(ResultPage.results);

        Assert.assertTrue(elements.size() > 0, "No Result Elements displayed");
    }

}
