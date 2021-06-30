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
        page.createAccount(email, miliseconds);
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

    public void selectBirth(int day, int month, int year, int miliseconds) {
        browser.selectByValue(RegisterPage.selectBirthDay, day + "", miliseconds);
        browser.selectByValue(RegisterPage.selectBirthMonth, month + "", miliseconds);
        browser.selectByValue(RegisterPage.selectBirthYear, year + "", miliseconds);
    }

    public void selectTitle(String value, int miliseconds) {
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
            page.selectTitle(data.title, miliseconds);
            browser.inputText(RegisterPage.inputFirstname, data.firstname, miliseconds);
            browser.inputText(RegisterPage.inputLastname, data.lastname, miliseconds);
            browser.inputText(RegisterPage.inputPassword, data.password, miliseconds);
            page.selectBirth(miliseconds, data.dayBirth, data.monthBirth, data.yearBirth);
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

            browser.click(RegisterPage.buttonRegister, miliseconds);
        }
    }

}
