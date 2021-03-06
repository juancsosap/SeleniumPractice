package pom.automationpractice;

import org.openqa.selenium.By;
import utils.pom.Page;
import utils.web.Browser;

public class HomePage extends Page {

    public HomePage(Browser browser) {
        super(browser);
    }

    public void go(int miliseconds) {
        browser.get("http://automationpractice.com/");
        browser.wait(miliseconds);
    }

    public static final By buttonSignin = By.xpath("//a[@class='login']");
    public static final By buttonContact = By.xpath("//a[@title='Contact Us']");
    public static final By inputSearch = By.xpath("//input[@name='search_query']");
    public static final By buttonSearch = By.xpath("//button[@name='submit_search']");

    public static final By buttonLogout = By.xpath("//a[@class='logout']");

    public void searchProduct(String product, int miliseconds) {
        browser.inputText(HomePage.inputSearch, product, miliseconds);
        browser.click(HomePage.buttonSearch, miliseconds);
        browser.wait(miliseconds);
    }

    public void clickSigninButton(int miliseconds) {
        browser.click(HomePage.buttonSignin, miliseconds);
        browser.wait(miliseconds);
    }

}
