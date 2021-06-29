package pom.automationpractice;

import org.openqa.selenium.By;
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
        String email = "prueba" + RandomGen.getDigits(5) + "@prueba.prueba";
        page.createAccount(miliseconds, email);
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

    public void selectBirth(int miliseconds, int day, int month, int year) {
        browser.selectByValue(RegisterPage.selectBirthDay, miliseconds, day + "");
        browser.selectByValue(RegisterPage.selectBirthMonth, miliseconds, month + "");
        browser.selectByValue(RegisterPage.selectBirthYear, miliseconds, year + "");
    }

    public void selectTitle(int miliseconds, String value) {
        if(value.equalsIgnoreCase("mr")) browser.click(RegisterPage.radioTitleMr, miliseconds);
        if(value.equalsIgnoreCase("mrs")) browser.click(RegisterPage.radioTitleMrs, miliseconds);
    }

    public Builder fillForm() { return new Builder(this); }

    private class Data {

        public Data() {
            title = "Mr"; firstname = ""; lastname = ""; password = "";
            dayBirth = 1; monthBirth = 1; yearBirth = 2020; company = ""; address = ""; city = "";
            state = "-"; postCode = "00000"; country = "United States"; additional = "";
            homePhone = ""; mobilePhone = ""; alias = "";
        }

        private String title;
        private String firstname;
        private String lastname;
        private String password;
        private int dayBirth, monthBirth, yearBirth;
        private String company;
        private String address;
        private String city;
        private String state;
        private String postCode;
        private String country;
        private String additional;
        private String homePhone;
        private String mobilePhone;
        private String alias;

    }

    public class Builder {

        private Data data;
        private RegisterPage page;

        public Builder(RegisterPage page) {
            this.data = new Data();
            this.page = page;
        }

        public Builder title(String value) { data.title = value; return this; }
        public Builder firstname(String value) { data.firstname = value; return this; }
        public Builder lastname(String value) { data.lastname = value; return this; }
        public Builder password(String value) { data.password = value; return this; }
        public Builder birth(int day, int month, int year) {
            data.dayBirth = day; data.monthBirth = month; data.yearBirth = year; return this; }
        public Builder company(String value) { data.company = value; return this; }
        public Builder address(String value) { data.address = value; return this; }
        public Builder city(String value) { data.city = value; return this; }
        public Builder state(String value) { data.state = value; return this; }
        public Builder postCode(String value) { data.postCode = value; return this; }
        public Builder country(String value) { data.country = value; return this; }
        public Builder additional(String value) { data.additional = value; return this; }
        public Builder homePhone(String value) { data.homePhone = value; return this; }
        public Builder mobilePhone(String value) { data.mobilePhone = value; return this; }
        public Builder alias(String value) { data.alias = value; return this; }

        public void register(int miliseconds) {
            page.selectTitle(miliseconds, data.title);
            browser.inputText(RegisterPage.inputFirstname, miliseconds, data.firstname);
            browser.inputText(RegisterPage.inputLastname, miliseconds, data.lastname);
            browser.inputText(RegisterPage.inputPassword, miliseconds, data.password);
            page.selectBirth(miliseconds, data.dayBirth, data.monthBirth, data.yearBirth);
            browser.inputText(RegisterPage.inputCompany, miliseconds, data.company);
            browser.inputText(RegisterPage.inputAddress, miliseconds, data.address);
            browser.inputText(RegisterPage.inputCity, miliseconds, data.city);
            browser.selectByVisibleText(RegisterPage.selectState, miliseconds, data.state);
            browser.inputText(RegisterPage.inputPostCode, miliseconds, data.postCode);
            browser.selectByVisibleText(RegisterPage.selectCountry, miliseconds, data.country);
            browser.inputText(RegisterPage.inputAdditional, miliseconds, data.additional);
            browser.inputText(RegisterPage.inputHomePhone, miliseconds, data.homePhone);
            browser.inputText(RegisterPage.inputMobilePhone, miliseconds, data.mobilePhone);
            browser.inputText(RegisterPage.inputAlias, miliseconds, data.alias);

            browser.click(RegisterPage.buttonRegister, miliseconds);
        }
    }

}
