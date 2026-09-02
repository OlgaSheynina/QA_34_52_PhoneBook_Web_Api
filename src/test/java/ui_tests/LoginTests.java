package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;

public class LoginTests extends AppManager {

    LoginPage loginPage;

    @BeforeMethod
    public void goToRegistrationLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest() {
        UserLombok user = UserLombok.builder()
                .username("gavana55@club.com")
                .password("Adfert55!")
                .build();

        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();

        Assert.assertTrue(new ContactsPage(getDriver())
                .validateTextInMessageNoContacts("No Contacts here!"));
    }

    @Test
    public void loginNegativeEmptyAllFieldsTest() {

        loginPage.clickBtnRegistration();

        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }
}