package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.customerlisteners.CustomListeners;
import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.pages.LoginPage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import resources.testdata.TestData;

@Listeners(CustomListeners.class)
public class LoginPageTest extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void userShouldNavigateToLoginPageSuccessFully() {
        //Click on login link
        homePage.clickOnLoginLink();
        //verify that "Welcome, Please Sign In!" message display
        String expMsg = "Welcome, Please Sign In!";
        String actMsg = loginPage.getWelcomeText();
        Assert.assertEquals(actMsg, expMsg, "Login page not displayed");
    }

    @Test(groups = {"smoke", "regression"}, dataProvider = "invalidCredentials", dataProviderClass = TestData.class)
    public void verifyTheErrorMessageWithInValidCredentials(String email, String pwd) {
        //Click on login link
        homePage.clickOnLoginLink();
        //Enter EmailId
        //Enter Password
        //Click on Login Button
        loginPage.loginToApp(email, pwd);
        //Verify that the Error message
        String expMsg = "Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found";
        String actMsg = loginPage.getErrorMessage();
        Assert.assertEquals(actMsg, expMsg);
    }

    @Test(groups = {"regression"}, dataProvider = "validCredentials", dataProviderClass = TestData.class)
    public void verifyThatUserShouldLogInSuccessFullyWithValidCredentials(String email, String pwd) {
        //Click on login link
        homePage.clickOnLoginLink();
        //Enter EmailId
        //Enter Password
        //Click on Login Button
        loginPage.loginToApp(email, pwd);
        //Verify that LogOut link is display
        String expMsg = "Log out";
        String actMsg = homePage.getLogoutLinkText();
        Assert.assertEquals(actMsg, expMsg);
    }

    @Test(groups = {"regression"}, dataProvider = "validCredentials", dataProviderClass = TestData.class)
    public void verifyThatUserShouldLogOutSuccessFully(String email, String pwd) {
        // Click on login link
        homePage.clickOnLoginLink();
        //Enter EmailId
        //Enter Password
        //Click on Login Button
        loginPage.loginToApp(email, pwd);
        //Click on LogOut Link
        homePage.clickOnLogoutLink();
        //Verify that LogIn Link Display
        String expMsg = "Log in";
        String actMsg = homePage.getLoginLinkText();
        Assert.assertEquals(actMsg, expMsg);
    }
}
