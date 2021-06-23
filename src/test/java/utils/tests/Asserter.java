package utils.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.web.Browser;

import java.util.List;
import java.util.stream.Collectors;

public class Asserter {

    protected Browser browser;

    public Asserter(Browser browser) {
        this.browser = browser;
    }

    public void assertTrue(boolean test, String error, By locator) {
        System.out.println("Assert : " + locator + " | " + test);
        Assert.assertTrue(test, error);
    }

    public void assertText(By locator, String text, String error, int miliseconds) {
        browser.highlight(locator, miliseconds);
        WebElement element = browser.getElement(locator);

        assertTrue(element.getText().equalsIgnoreCase(text), error, locator);
    }

    public void assertActive(By locator, String name, int miliseconds) {
        browser.highlight(locator, miliseconds);
        WebElement element = browser.getElement(locator);

        assertTrue(element.isDisplayed(), name + " not displayed", locator);
        assertTrue(element.isEnabled(), name + " not enabled", locator);
    }

    public void assertListSize(By locator, int size, String error) {
        List<WebElement> elements = browser.getElements(locator);
        assertTrue(elements.size() > size, error, locator);
    }

    public void assertListContains(By locator, String text, String error) {
        List<WebElement> elements = browser.getElements(locator);
        List<String> elementsText = elements.stream().map(we -> we.getText()).collect(Collectors.toList());
        assertTrue(elementsText.contains(text), error, locator);
    }

    public void assertSelectSize(By locator, int size, String error) {
        List<WebElement> elements = browser.getSelect(locator).getOptions();
        assertTrue(elements.size() > size, error, locator);
    }

    public void assertSelectContains(By locator, String attribute, String value, String error) {
        List<WebElement> elements = browser.getSelect(locator).getOptions();
        List<String> elementsText = elements.stream().map(we -> we.getAttribute(attribute)).collect(Collectors.toList());
        assertTrue(elementsText.contains(value), error, locator);
    }

}
