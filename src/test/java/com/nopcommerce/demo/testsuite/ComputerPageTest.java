package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.pages.BuildYourOwnComputerPage;
import com.nopcommerce.demo.pages.ComputerPage;
import com.nopcommerce.demo.pages.DesktopPage;
import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.testdata.TestData;

public class ComputerPageTest extends BaseTest {
    HomePage homePage;
    ComputerPage computerPage;
    DesktopPage desktopPage;
    BuildYourOwnComputerPage buildYourOwnComputerPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        computerPage = new ComputerPage();
        desktopPage = new DesktopPage();
        buildYourOwnComputerPage = new BuildYourOwnComputerPage();
    }

    @Test(groups = {"sanity", "smoke"})
    public void verifyUserShouldNavigateToComputerPageSuccessfully() {
        //Click on Computer tab
        homePage.selectMenu("Computers");
        // Verify "Computer" text
        Assert.assertEquals(computerPage.getComputerHeading(), "Computers");
    }

    @Test(groups = {"regression", "smoke"})
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        //Click on Computer tab
        homePage.selectMenu("Computers");
        // Click on Desktops link
        computerPage.clickOnDesktopLink();
        // Verify "Desktops" text
        Assert.assertEquals(desktopPage.getDesktopHeading(), "Desktops");
    }

    @Test(groups = {"regression"}, dataProvider = "buildComData", dataProviderClass = TestData.class)
    public void verifyThatUserShouldBuildYouOwnComputerAndAddThemToCartSuccessfully(String processor, String ram, String hdd, String os, String software) {
        //Click on Computer tab
        homePage.selectMenu("Computers");
        // Click on Desktops link
        computerPage.clickOnDesktopLink();
        // Click on product name "Build your own computer"
        desktopPage.clickOnBuildYourOwnCompLink();

        // Select processor "processor"
        buildYourOwnComputerPage.selectProcessorFromDropdown(processor);
        // Select RAM "ram"
        buildYourOwnComputerPage.selectRam(ram);
        // Select HDD "hdd"
        buildYourOwnComputerPage.selectHddRadio(hdd);
        // Select OS "os"
        buildYourOwnComputerPage.selectOs(os);
        // Select Software "software"
        buildYourOwnComputerPage.selectCheckBox(software);
        // Click on "ADD TO CART" Button
        buildYourOwnComputerPage.clickOnAddToCart();
    }
}
