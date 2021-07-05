package tests.automationpractice;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pom.automationpractice.HomePage;
import pom.automationpractice.RegisterPage;
import tests.PageTests;

public class RegisterPageTests extends PageTests<RegisterPage> {

    @BeforeClass
    @Override
    public void preparePage() {
        page = new RegisterPage(browser);
    }

    @Test
    public void elementsPresent() {
        asserter.assertActive(RegisterPage.radioTitleMr, wait);
        asserter.assertActive(RegisterPage.radioTitleMrs, wait);

        asserter.assertActive(RegisterPage.inputFirstname, wait);
        asserter.assertActive(RegisterPage.inputLastname, wait);
        asserter.assertActive(RegisterPage.inputEmail, wait);
        asserter.assertActive(RegisterPage.inputPassword, wait);

        asserter.assertActive(RegisterPage.selectBirthDay, wait);
        asserter.assertSelectSize(RegisterPage.selectBirthDay, data.getInt(1), data.get(2));
        asserter.assertActive(RegisterPage.selectBirthMonth, wait);
        asserter.assertSelectSize(RegisterPage.selectBirthMonth, data.getInt(3), data.get(4));
        asserter.assertActive(RegisterPage.selectBirthYear, wait);
        asserter.assertSelectSize(RegisterPage.selectBirthYear, data.getInt(5), data.get(6));

        asserter.assertActive(RegisterPage.inputCompany, wait);
        asserter.assertActive(RegisterPage.inputAddress, wait);
        asserter.assertActive(RegisterPage.inputCity, wait);
        asserter.assertActive(RegisterPage.selectState, wait);
        asserter.assertSelectSize(RegisterPage.selectState, data.getInt(7), data.get(8));
        asserter.assertActive(RegisterPage.inputPostCode, wait);
        asserter.assertActive(RegisterPage.selectCountry, wait);
        asserter.assertSelectSize(RegisterPage.selectCountry, data.getInt(9), data.get(10));
        asserter.assertActive(RegisterPage.inputAdditional, wait);
        asserter.assertActive(RegisterPage.inputHomePhone, wait);
        asserter.assertActive(RegisterPage.inputMobilePhone, wait);
        asserter.assertActive(RegisterPage.inputAlias, wait);

        asserter.assertActive(RegisterPage.buttonRegister, wait);
    }

    @Test
    public void registerWithEmptyField() {
        page.register(null, wait);

        asserter.assertListSize(RegisterPage.errorMessages, data.getInt(1), data.get(2));
        asserter.assertListContains(RegisterPage.errorMessages, data.get(3), data.get(4));
        asserter.assertListContains(RegisterPage.errorMessages, data.get(5), data.get(6));
        asserter.assertListContains(RegisterPage.errorMessages, data.get(7), data.get(8));
        asserter.assertListContains(RegisterPage.errorMessages, data.get(9), data.get(10));
        asserter.assertListContains(RegisterPage.errorMessages, data.get(11), data.get(12));
        asserter.assertListContains(RegisterPage.errorMessages, data.get(13), data.get(14));
        asserter.assertListContains(RegisterPage.errorMessages, data.get(15), data.get(16));
    }

    @Test
    public void registerWithInvalidField() {
        page.fillForm().firstname(data.get(1)).lastname(data.get(2)).password(data.get(3))
                       .birthDay(data.get(4)).birthMonth(data.get(5)).birthYear(data.get(6))
                       .postCode(data.get(7)).register(wait);
        browser.wait(wait);

        asserter.assertListSize(RegisterPage.errorMessages, data.getInt(8), data.get(9));
        asserter.assertListContains(RegisterPage.errorMessages, data.get(10), data.get(11));
        asserter.assertListContains(RegisterPage.errorMessages, data.get(12), data.get(13));
        asserter.assertListContains(RegisterPage.errorMessages, data.get(14), data.get(15));
        asserter.assertListContains(RegisterPage.errorMessages, data.get(16), data.get(17));
        asserter.assertListContains(RegisterPage.errorMessages, data.get(18), data.get(19));
    }

    @Test
    public void registerWithValidField() {
        page.fillForm().title(data.get(1)).firstname(data.get(2)).lastname(data.get(3)).password(data.get(4))
                .birthDay(data.get(5)).birthMonth(data.get(6)).birthYear(data.get(7)).company(data.get(8))
                .address(data.get(9)).city(data.get(10)).state(data.get(11)).postCode(data.get(12))
                .country(data.get(13)).additional(data.get(14)).homePhone(data.get(15))
                .mobilePhone(data.get(16)).alias(data.get(17)).register(wait);

        asserter.assertActive(HomePage.buttonLogout, wait);

        browser.click(HomePage.buttonLogout, wait);
    }

}
