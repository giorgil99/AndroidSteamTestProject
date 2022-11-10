package local.projects.steam.steps;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import local.projects.steam.elements.HomePageElements;
import local.projects.steam.utils.Selenider;

public class HomePageSteps extends HomePageElements {
    public AndroidDriver driver;
    int seconds;
    Selenider $;

    public HomePageSteps(AndroidDriver driver, int seconds) {
        this.driver = driver;
        this.seconds = seconds;
        $ = new Selenider(driver, seconds);
    }

    @Step("click on sign in button")
    public HomePageSteps clickOnSignInButton(){
        $.click(getSingInButton(),true);
        return this;
    }

    @Step("enter password")
    public HomePageSteps enterPassword(String password){
        $.sendKeys(getPasswordField(),password,true);
        return this;
    }

    @Step("enter account name")
    public HomePageSteps enterAccountName(String accountName){
        $.sendKeys(getAccountNameField(),accountName,true);
        return this;
    }

}
