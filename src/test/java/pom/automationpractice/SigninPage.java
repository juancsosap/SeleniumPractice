package pom.automationpractice;

import org.openqa.selenium.By;
import utils.pom.Page;
import utils.web.Browser;

public class SigninPage extends Page {

    public SigninPage(Browser browser) {
        super(browser);
    }

    public void go(int miliseconds) {
        HomePage page = new HomePage(browser);
        page.go(miliseconds);
        browser.click(HomePage.buttonSignin, miliseconds);
    }

    public static final By textHeading = By.xpath("//span[@class='navigation_page']");

    public static final By inputEmailRegister = By.xpath("//input[@id='email_create']");
    public static final By buttonCreate = By.xpath("//button[@id='SubmitCreate']");
    public static final By errorRegisterMessages = By.xpath("//div[@id='create_account_error']//li");

    public static final By inputEmailLogin = By.xpath("//input[@id='email']");
    public static final By inputPasswordLogin = By.xpath("//input[@id='passwd']");
    public static final By linkLostPassword = By.xpath("//p[contains(@class, 'lost_password')]/a");
    public static final By buttonLogin = By.xpath("//button[@id='SubmitLogin']");
    public static final By errorLoginMessages = By.xpath("//div[@id='center_column']/div[contains(@class, 'alert')]//li");

    public void createAccount(String email, int miliseconds) {
        browser.inputText(SigninPage.inputEmailRegister, email, miliseconds);
        browser.click(SigninPage.buttonCreate, miliseconds);
        browser.wait(miliseconds);
    }

}
