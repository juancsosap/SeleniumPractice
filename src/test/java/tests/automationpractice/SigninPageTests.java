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
        asserter.assertActive(SigninPage.inputEmailRegister, "Input Email Register", 3000);
        asserter.assertActive(SigninPage.buttonCreate, "Button Create Account", 3000);
    }

    @Test
    public void createAccountWithoutEmail() {
        page.createAccount(3000, "");

        asserter.assertListSize(SigninPage.errorRegisterMessages, 0, "No error generated with empty email");
        asserter.assertListContains(SigninPage.errorRegisterMessages, "Invalid email address.", "Error no present with empty email");
    }

    @Test
    public void createAccountWithWrongEmail() {
        page.createAccount(3000, data.get(1));

        asserter.assertListSize(SigninPage.errorRegisterMessages, 0, "No error generated with wrong email");
        asserter.assertListContains(SigninPage.errorRegisterMessages, "Invalid email address.", "Error no present with wrong email");
    }

    @Test
    public void createAccountWithRightEmail() {
        page.createAccount(3000, data.get(1));

        asserter.assertText(RegisterPage.textHeading, "Authentication", "Register Page not displayed", 3000);
    }

}
