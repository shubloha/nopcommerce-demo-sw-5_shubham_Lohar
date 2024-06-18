package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.pages.RegisterPage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.testdata.TestData;

public class RegisterPageTest extends BaseTest {
    RegisterPage registerPage;
    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        registerPage = new RegisterPage();
        homePage = new HomePage();
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        //Click on Register Link
        homePage.clickOnRegisterLink();
        // Verify "Register" text
        String expMsg = "Register";
        String actMsg = registerPage.getRegisterHeading();
        Assert.assertEquals(actMsg, expMsg, "Register page not displayed");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyThatFirstNameLastNameEmailPasswordAndConfirmPasswordFieldsAreMandatory() {
        // Click on Register Link
        homePage.clickOnRegisterLink();
        // Click on "REGISTER" button
        registerPage.clickOnRegisterButton();
        // Verify the error message "First name is required."
        Assert.assertEquals(registerPage.getFirstNameError(), "First name is required.");
        // Verify the error message "Last name is required."
        Assert.assertEquals(registerPage.getLastNameError(), "Last name is required.");
        // Verify the error message "Email is required."
        Assert.assertEquals(registerPage.getEmailError(), "Email is required.");
        // Verify the error message "Password is required."
        Assert.assertEquals(registerPage.getConfirmPasswordError(), "Password is required.");
    }

    @Test(groups = "regression", dataProvider = "registrationData", dataProviderClass = TestData.class)
    public void verifyThatUserShouldCreateAccountSuccessfully(String gender, String fName, String lName, String date, String month, String year, String email, String pwd, String cpwd) {
        //Click on Register Link
        homePage.clickOnRegisterLink();
        // Select gender "Female"
        // Enter firstname
        // Enter lastname
        // Select day
        // Select month
        // Select year
        // Enter email
        // Enter password
        // Enter Confirm Password
        // Click on "REGISTER" button
        registerPage.registerToApp(gender, fName, lName, date, month, year, email, pwd, cpwd);
        // Verify message "Your registration completed"
        Assert.assertEquals(registerPage.getRegistrationSuccessMsg(), "Your registration completed");
    }
}
