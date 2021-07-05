package utils.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import tests.PageTests;
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
        String message = "Assert : " + locator + " | " + test;
        try {
            Assert.assertTrue(result, error);
            browser.reporter.log(LogStatus.PASS, message);
        } catch(Error e) {
            browser.reporter.log(LogStatus.FAIL, message);
            throw e;
        }
    }

    public void assertText(By locator, String text, String error, int miliseconds) throws Error {
        WebElement element = browser.highlight(locator, miliseconds);

        String testDesc = "Text Contains ('" + text + "')";
        boolean result = element != null && element.getText().contains(text);
        assertTrue(testDesc, result, error, locator);
    }

    public void assertActive(By locator, int miliseconds) throws Error {
        WebElement element = browser.highlight(locator, miliseconds);

        String testDesc = "Enabled";
        boolean result = element != null && element.isEnabled();
        assertTrue(testDesc, result, "Not enabled", locator);
    }

    public void assertListSize(By locator, int size, String error) throws Error {
        List<WebElement> elements = browser.getElements(locator);

        String testDesc = "List Size must be >= " + size + " and is " + elements.size();
        boolean result = elements.size() >= size;
        assertTrue(testDesc, result, error, locator);
    }

    public void assertListContains(By locator, String text, String error) throws Error {
        List<WebElement> elements = browser.getElements(locator);
        List<String> elementsText = elements.stream().map(WebElement::getText).collect(Collectors.toList());

        String testDesc = "List Contains ('" + text + "')";
        boolean result = elementsText.stream().anyMatch(e -> e.contains(text));
        assertTrue(testDesc, result, error, locator);
    }

    public void assertSelectSize(By locator, int size, String error) throws Error {
        List<WebElement> elements = browser.getSelect(locator).getOptions();

        String testDesc = "Select Size (" + elements.size() + " >= " + size + ")";
        boolean result = elements.size() >= size;
        assertTrue(testDesc, result, error, locator);
    }

    public void assertSelectContains(By locator, String attribute, String value, String error) throws Error {
        List<WebElement> elements = browser.getSelect(locator).getOptions();
        List<String> elementsText = elements.stream().map(we -> we.getAttribute(attribute)).collect(Collectors.toList());

        String testDesc = "Select Contains ('" + value + "')";
        boolean result = elementsText.contains(value);
        assertTrue(testDesc, result, error, locator);
    }

}
