package tests.automationpractice;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pom.automationpractice.RegisterPage;
import pom.automationpractice.SigninPage;
import utils.tests.PageTests;

public class SigninPageTests extends PageTests<SigninPage> {

    @BeforeClass
    @Override
    public void preparePage() {
        page = new SigninPage(browser);
    }

    @Test
    public void elementsPresent() {
        asserter.assertActive(SigninPage.inputEmailRegister, wait);
        asserter.assertActive(SigninPage.buttonCreate, wait);
    }

    @Test
    public void createAccountWithoutEmail() {
        page.createAccount("", wait);

        asserter.assertListSize(SigninPage.errorRegisterMessages, data.getInt(1), data.get(2));
        asserter.assertListContains(SigninPage.errorRegisterMessages, data.get(3), data.get(4));
    }

    @Test
    public void createAccountWithWrongEmail() {
        page.createAccount(data.get(1), wait);

        asserter.assertListSize(SigninPage.errorRegisterMessages, data.getInt(2), data.get(3));
        asserter.assertListContains(SigninPage.errorRegisterMessages, data.get(4), data.get(5));
    }

    @Test
    public void createAccountWithRegisteredEmail() {
        page.createAccount(data.get(1), wait);

        asserter.assertListSize(SigninPage.errorRegisterMessages, data.getInt(2), data.get(3));
        asserter.assertListContains(SigninPage.errorRegisterMessages, data.get(4), data.get(5));
    }

    @Test
    public void createAccountWithRightEmail() {
        page.createAccount(data.get(1), wait);

        asserter.assertText(RegisterPage.textHeading, data.get(2), data.get(3), wait);
    }

}
