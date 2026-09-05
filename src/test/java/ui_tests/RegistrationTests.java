package ui_tests;

import data_providers.UserDataProvider;
import dto.User;
import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

import static utils.UserFactory.*;

import java.util.Random;

public class RegistrationTests extends AppManager {

    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToRegistrationLoginPage() {
        new HomePage(getDriver()).clickLinkLogin();
        loginPage = new LoginPage(getDriver());
        logger.info("Start registration test");
    }


    @Test
    public void registrationPositiveTest() {
        UserLombok user = positiveUser();
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();

        Assert.assertTrue(new ContactsPage(getDriver())
                .validateTextInMessageNoContacts("No Contacts here!"));
    }
//    @Test
//    public void registrationPositiveTest() {
//        int i = new Random().nextInt(1000);
//        UserLombok user = UserLombok.builder()
//                .username("gavana" + i + "55@club.com")
//                .password("Adfert55!")
//                .build();
//
//        loginPage.typeLoginRegistrationForm(user);
//        loginPage.clickBtnRegistration();
//
//        Assert.assertTrue(new ContactsPage(getDriver())
//                .validateTextInMessageNoContacts("No Contacts here!"));
//    }

    @Test
    public void registrationPositiveWithFakerTest() {

        UserLombok user = positiveUser();
        System.out.println(user);

        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();

        Assert.assertTrue(new ContactsPage(getDriver())
                .validateTextInMessageNoContacts("No Contacts here!"));
    }

    @Test
    public void registrationNegativeAllFieldsEmptyWOTypeFormTest() {

        loginPage.clickBtnRegistration();

        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }

    @Test
    public void registrationNegativeEmptyEmailFieldTest() {
        UserLombok user = positiveUser();
        user.setUsername("");

        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();

        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }

    @Test
    public void registrationNegativeEmptyPasswordFieldTest() {
        UserLombok user = positiveUser();
        user.setPassword("");

        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();

        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }

    @Test
    public void registrationNegativeWithExistedInDBEmail() {
        UserLombok user = positiveUser();
        user.setUsername("email");

        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();

        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }

    @Test(dataProvider = "dataProviderWrongPasswordOrEmail",
            dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongPasswordOrEmailTest(UserLombok user) {

        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();

        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }



//    @Test
//    public void testMethod() {
//        new HomePage(getDriver()).method();
//    }
//
//    @Test
//    public void testAjaxMethod() {
//        new HomePage(getDriver()).ajaxMethod();
//    }
}












