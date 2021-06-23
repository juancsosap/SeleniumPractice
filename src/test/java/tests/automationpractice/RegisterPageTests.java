package tests.automationpractice;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pom.automationpractice.HomePage;
import pom.automationpractice.ProfilePage;
import pom.automationpractice.RegisterPage;
import utils.tests.PageTests;

public class RegisterPageTests extends PageTests<RegisterPage> {

    @BeforeClass
    @Override
    public void preparePage() {
        page = new RegisterPage(browser);
    }

    @Test
    public void elementsPresent() {
        asserter.assertActive(RegisterPage.radioTitleMr, "Radio Input Mr Title", 3000);
        asserter.assertActive(RegisterPage.radioTitleMrs, "Radio Input Mrs Title", 3000);

        asserter.assertActive(RegisterPage.inputFirstname, "Input Firstname", 3000);
        asserter.assertActive(RegisterPage.inputLastname, "Input Lastname", 3000);
        asserter.assertActive(RegisterPage.inputEmail, "Input Email", 3000);
        asserter.assertActive(RegisterPage.inputPassword, "Input Password", 3000);

        asserter.assertActive(RegisterPage.selectBirthDay, "Select Day Birth", 3000);
        asserter.assertActive(RegisterPage.selectBirthMonth, "Select Month Birth", 3000);
        asserter.assertActive(RegisterPage.selectBirthYear, "Select Year Birth", 3000);
        asserter.assertSelectSize(RegisterPage.selectBirthDay, 31, "Select Day Birth no fill");
        asserter.assertSelectSize(RegisterPage.selectBirthMonth, 12, "Select Month Birth no fill");
        asserter.assertSelectSize(RegisterPage.selectBirthYear, 20, "Select Year Birth no fill");

        asserter.assertActive(RegisterPage.inputCompany, "Input Company", 3000);
        asserter.assertActive(RegisterPage.inputAddress, "Input Address", 3000);
        asserter.assertActive(RegisterPage.inputCity, "Input City", 3000);
        asserter.assertActive(RegisterPage.selectState, "Select State", 3000);
        asserter.assertSelectSize(RegisterPage.selectState, 50, "Select State no fill");
        asserter.assertActive(RegisterPage.inputPostCode, "Input PostCode", 3000);
        asserter.assertActive(RegisterPage.selectCountry, "Select Country", 3000);
        asserter.assertSelectSize(RegisterPage.selectCountry, 1, "Select Country no fill");
        asserter.assertActive(RegisterPage.inputAdditional, "Input Additional", 3000);
        asserter.assertActive(RegisterPage.inputHomePhone, "Input Home Phone", 3000);
        asserter.assertActive(RegisterPage.inputMobilePhone, "Input Mobile Phone", 3000);
        asserter.assertActive(RegisterPage.inputAlias, "Input Alias", 3000);

        asserter.assertActive(RegisterPage.buttonRegister, "Button Register", 3000);
    }

    @Test
    public void registerWithFieldEmpty() {
        page.fillForm().register(1000);

        asserter.assertListSize(RegisterPage.errorMessages, 6, "No error generated with empty email");
        asserter.assertListContains(RegisterPage.errorMessages, "firstname is required.", "firstname field no required");
        asserter.assertListContains(RegisterPage.errorMessages, "lastname is required.", "lastname field no required");
        asserter.assertListContains(RegisterPage.errorMessages, "passwd is required.", "password field no required");
        asserter.assertListContains(RegisterPage.errorMessages, "address1 is required.", "address field no required");
        asserter.assertListContains(RegisterPage.errorMessages, "city is required.", "city field no required");
        asserter.assertListContains(RegisterPage.errorMessages, "This country requires you to choose a State.", "state field no required");
        asserter.assertListContains(RegisterPage.errorMessages, "You must register at least one phone number.", "no phone number required");
    }

    @Test
    public void registerWithInvalidFieldValue() {
        page.fillForm().firstname(data.get(1)).lastname(data.get(2)).password(data.get(3))
                       .birth(data.getInt(4), data.getInt(5), data.getInt(6))
                       .postCode(data.get(7)).register(1000);

        asserter.assertListSize(RegisterPage.errorMessages, 0, "No error generated with wrong email");
        asserter.assertListContains(RegisterPage.errorMessages, "firstname is invalid.", "firstname field format not validated");
        asserter.assertListContains(RegisterPage.errorMessages, "lastname is invalid.", "lastname field format not validated");
        asserter.assertListContains(RegisterPage.errorMessages, "passwd is invalid.", "password field format not validated");
        asserter.assertListContains(RegisterPage.errorMessages, "Invalid date of birth.", "birthday date not validated");
        asserter.assertListContains(RegisterPage.errorMessages, "The Zip/Postal code you've entered is invalid. It must follow this format: 00000", "birthday date not validated");
    }

    @Test
    public void registerWithValidFieldValue() {
        System.out.println("Begining : Register with Valid Field Values Test");

        page.fillForm().title(data.get(1)).firstname(data.get(2)).lastname(data.get(3)).password(data.get(4))
                .birth(data.getInt(5), data.getInt(6), data.getInt(7)).company(data.get(8))
                .address(data.get(9)).city(data.get(10)).state(data.get(11)).postCode(data.get(12))
                .country(data.get(13)).additional(data.get(14)).homePhone(data.get(15))
                .mobilePhone(data.get(16)).alias(data.get(17)).register(1000);

        asserter.assertText(ProfilePage.textHeading, "Profile", "Profile Page not displayed",  300);

        browser.click(HomePage.buttonLogout, 3000);
    }

}
