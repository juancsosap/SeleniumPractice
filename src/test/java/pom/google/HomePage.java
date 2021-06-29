package pom.google;

import org.openqa.selenium.By;
import utils.pom.Page;
import utils.web.Browser;

public class HomePage extends Page {

    public HomePage(Browser browser) {
        super(browser);
    }

    public void go(int miliseconds) {
        browser.get("http://google.com/");
        browser.wait(miliseconds);
    }

    public static final By inputQuery = By.xpath("//input[@name='q']");
    public static final By buttonSearch = By.xpath("//input[@name='btnK']");

    public void searchText(int miliseconds, String text) {
        browser.inputText(HomePage.inputQuery, miliseconds, text);
        browser.click(HomePage.buttonSearch, miliseconds);
        browser.wait(miliseconds);
    }

}
