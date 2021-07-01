package pom.automationpractice;

import org.openqa.selenium.By;
import pom.automationpractice.data.RegisterBuilder;
import pom.automationpractice.data.RegisterData;
import utils.pom.Page;
import utils.web.Browser;
import utils.RandomGen;

public class RegisterPage extends Page {

    public RegisterPage(Browser browser) {
        super(browser);
    }

    public void go(int miliseconds) {
        SigninPage page = new SigninPage(browser);
        page.go(miliseconds);
        page.createAccount(getRandomEmail(), miliseconds);
    }

    public static final By textHeading = By.xpath("//span[@class='navigation_page']");

    public static final By radioTitleMr = By.xpath("//input[@id='id_gender1']");
    public static final By radioTitleMrs = By.xpath("//input[@id='id_gender2']");

    public static final By inputFirstname = By.xpath("//input[@id='customer_firstname']");
    public static final By inputLastname = By.xpath("//input[@id='customer_lastname']");
    public static final By inputEmail = By.xpath("//input[@id='email']");
    public static final By inputPassword = By.xpath("//input[@id='passwd']");

    public static final By selectBirthDay = By.xpath("//select[@id='days']");
    public static final By selectBirthMonth = By.xpath("//select[@id='months']");
    public static final By selectBirthYear = By.xpath("//select[@id='years']");

    public static final By inputCompany = By.xpath("//input[@id='company']");
    public static final By inputAddress = By.xpath("//input[@id='address1']");
    public static final By inputCity = By.xpath("//input[@id='city']");
    public static final By selectState = By.xpath("//select[@id='id_state']");
    public static final By inputPostCode = By.xpath("//input[@id='postcode']");
    public static final By selectCountry = By.xpath("//select[@id='id_country']");
    public static final By inputAdditional = By.xpath("//textarea[@id='other']");
    public static final By inputHomePhone = By.xpath("//input[@id='phone']");
    public static final By inputMobilePhone = By.xpath("//input[@id='phone_mobile']");
    public static final By inputAlias = By.xpath("//input[@id='alias']");

    public static final By buttonRegister = By.xpath("//button[@id='submitAccount']");
    public static final By errorMessages = By.xpath("//div[@id='center_column']/div[contains(@class, 'alert')]//li");

    private String getRandomEmail() {
        return "prueba" + RandomGen.getDigits(5) + "@prueba.prueba";
    }

    public void selectTitle(String value, int miliseconds) {
        if(value != null) {
            if (value.equalsIgnoreCase("mr")) browser.click(RegisterPage.radioTitleMr, miliseconds);
            if (value.equalsIgnoreCase("mrs")) browser.click(RegisterPage.radioTitleMrs, miliseconds);
        }
    }

    public RegisterBuilder fillForm() { return new RegisterBuilder(this); }

    public void register(RegisterData data, int miliseconds) {
        if(data != null) {
            selectTitle(data.title, miliseconds);
            browser.inputText(RegisterPage.inputFirstname, data.firstname, miliseconds);
            browser.inputText(RegisterPage.inputLastname, data.lastname, miliseconds);
            browser.inputText(RegisterPage.inputPassword, data.password, miliseconds);
            browser.selectByValue(RegisterPage.selectBirthDay, data.dayBirth, miliseconds);
            browser.selectByValue(RegisterPage.selectBirthMonth, data.monthBirth, miliseconds);
            browser.selectByValue(RegisterPage.selectBirthYear, data.yearBirth, miliseconds);
            browser.inputText(RegisterPage.inputCompany, data.company, miliseconds);
            browser.inputText(RegisterPage.inputAddress, data.address, miliseconds);
            browser.inputText(RegisterPage.inputCity, data.city, miliseconds);
            browser.selectByVisibleText(RegisterPage.selectState, data.state, miliseconds);
            browser.inputText(RegisterPage.inputPostCode, data.postCode, miliseconds);
            browser.selectByVisibleText(RegisterPage.selectCountry, data.country, miliseconds);
            browser.inputText(RegisterPage.inputAdditional, data.additional, miliseconds);
            browser.inputText(RegisterPage.inputHomePhone, data.homePhone, miliseconds);
            browser.inputText(RegisterPage.inputMobilePhone, data.mobilePhone, miliseconds);
            browser.inputText(RegisterPage.inputAlias, data.alias, miliseconds);
        }
        browser.click(RegisterPage.buttonRegister, miliseconds);
    }

}
