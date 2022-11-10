package steam.tests;

import local.projects.steam.listeners.LocalListenersTestNG;
import local.projects.steam.runners.BaseRunner;
import local.projects.steam.steps.HomePageSteps;
import local.projects.steam.utils.Selenider;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(LocalListenersTestNG.class)
public class HomePage extends BaseRunner {


    @BeforeMethod
    public void beforeTest() {
        System.out.println("START>>>>>>>>>");

    }


    @Test(description = "check sign in is present and has exact text", priority = 1)
    public void appHomeScreenTest() {
        Selenider $ = new Selenider(driver, 70);
        HomePageSteps homePageSteps = new HomePageSteps(driver, 90);
        String validationText = "SIGN IN";
        $.visibility(homePageSteps.getSignInText(), true, true);
        $.valueEquals(homePageSteps.getSignInText(), validationText, true, true);
    }

    @Test(description = "check that account name field is visible and can be written", priority = 2)
    public void checkAccountNameTest() {
        Selenider $ = new Selenider(driver, 70);
        HomePageSteps homePageSteps = new HomePageSteps(driver, 90);
        String accountName = "steamGenericName";
        homePageSteps.enterAccountName("steamGenericName");
        $.valueEquals(homePageSteps.getAccountNameField(), accountName, true, true);
        // valueEquals() method has visibility assertion, so visibility() is not necessary
    }

    @Test(description = "check that password field is visible and can be written", priority = 3)
    public void checkPasswordFieldTest() {
        Selenider $ = new Selenider(driver, 70);
        HomePageSteps homePageSteps = new HomePageSteps(driver, 90);
        String password = "steamGenericPassword";
        homePageSteps.enterPassword("steamGenericPassword");
        $.valueEquals(homePageSteps.getPasswordField(), password, true, false);
        // since password field input is hidden check that doted string length matches password string length instead
        // valueEquals() method will not fail the test since assertAll is set to false
        Assert.assertEquals(driver.findElement(homePageSteps.getPasswordField()).getText().length(), password.length());
    }

    @Test(description = "check that  forgot text is visible", priority = 4)
    public void checkForgotTextTest() {
        Selenider $ = new Selenider(driver, 70);
        HomePageSteps homePageSteps = new HomePageSteps(driver, 90);
        String validationText = "Forgot your account name or password?";
        $.visibility(homePageSteps.getForgotPasswordField(), true, true);
        $.valueEquals(homePageSteps.getForgotPasswordField(), validationText, true, true);
    }


    @Test(description = "check that  wrong password or account name error is visible", priority = 5)
    public void errorMessageTest() {
        Selenider $ = new Selenider(driver, 70);
        HomePageSteps homePageSteps = new HomePageSteps(driver, 90);
        String validationText = "The account name or password that you have entered is incorrect.";
        homePageSteps.enterAccountName("steamGenericName")
                .enterPassword("steamGenericPassword")
                .clickOnSignInButton();
        $.visibility(homePageSteps.getErrorMessage1(), true, true);
        $.valueEquals(homePageSteps.getErrorMessage1(), validationText, true, true);
    }

    @Test(description="fail test",priority = 6)
    public void failTest(){
        Assert.fail("?");
    }

    @AfterMethod
    public void afterTest() {
        System.out.println("END<<<<<<<<<");
    }
}
