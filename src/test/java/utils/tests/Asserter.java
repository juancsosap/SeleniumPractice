package utils.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.reports.LogStatus;
import utils.web.Browser;

import java.util.List;
import java.util.stream.Collectors;

public class Asserter {

    protected Browser browser;

    public Asserter(Browser browser) {
        this.browser = browser;
    }

    public void assertTrue(String test, boolean result, String error, By locator) throws Error {
        try {
            Assert.assertTrue(result, error);
            PageTests.printMessage(LogStatus.PASS, "Assert : " + locator + " | " + test);
        } catch(Error e) {
            PageTests.printMessage(LogStatus.FAIL, "Assert : " + locator + " | " + test);
            throw e;
        }
    }

    public void assertText(By locator, String text, String error, int miliseconds) throws Error {
        WebElement element = browser.highlight(locator, miliseconds);

        if(element != null) {
            assertTrue("Text Equal ('" + text + "')", element.getText().equalsIgnoreCase(text), error, locator);
        } else {
            assertTrue("Text Equal ('" + text + "')", false, error, locator);
        }
    }

    public void assertActive(By locator, String name, int miliseconds) throws Error {
        WebElement element = browser.highlight(locator, miliseconds);

        if(element != null) {
            assertTrue("Enabled", element.isEnabled(), name + " not enabled", locator);
        } else {
            assertTrue("Enabled", false, name + " not enabled", locator);
        }
    }

    public void assertListSize(By locator, int size, String error) throws Error {
        List<WebElement> elements = browser.getElements(locator);
        assertTrue("List Size (" + elements.size() + " >= " + size + ")", elements.size() >= size, error, locator);
    }

    public void assertListContains(By locator, String text, String error) throws Error {
        List<WebElement> elements = browser.getElements(locator);
        List<String> elementsText = elements.stream().map(WebElement::getText).collect(Collectors.toList());
        assertTrue("List Contains ('" + text + "')", elementsText.contains(text), error, locator);
    }

    public void assertSelectSize(By locator, int size, String error) throws Error {
        List<WebElement> elements = browser.getSelect(locator).getOptions();
        assertTrue("Select Size (" + elements.size() + " >= " + size + ")", elements.size() >= size, error, locator);
    }

    public void assertSelectContains(By locator, String attribute, String value, String error) throws Error {
        List<WebElement> elements = browser.getSelect(locator).getOptions();
        List<String> elementsText = elements.stream().map(we -> we.getAttribute(attribute)).collect(Collectors.toList());
        assertTrue("Select Contains ('" + value + "')", elementsText.contains(value), error, locator);
    }

}
