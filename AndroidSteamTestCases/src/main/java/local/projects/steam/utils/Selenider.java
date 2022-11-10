package local.projects.steam.utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import local.projects.steam.runners.BaseRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

// wrapping selenium syntax into selenide like double action (wait and click)
public class Selenider extends BaseRunner {
    private  AndroidDriver driver;
    private  WebDriverWait wait;

    private static final String TRUE_VALUE ="true";
    private static final String FALSE_VALUE ="false";

    public Selenider(AndroidDriver driver, int seconds) {
        this.driver = driver;
        wait = new WebDriverWait(driver, seconds);
    }


    public void mobileWait(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    public void click(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).click();

    }

    public void click(By by, boolean log) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).click();
        if (log) System.out.println("clicked on: " + by.toString());

    }

    public void sendKeys(By by, String keys) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(keys);

    }

    public void sendKeys(By by, String keys, boolean log) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(keys);
        if (log) System.out.println("sent keys to: " + by.toString());
    }

    public void visibility(By by, boolean state, boolean assertAll) {
        SoftAssert softAssert = new SoftAssert();
        if (state) {
            softAssert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed(), "[" + by.toString() + "] was not visible");
        }
        if (!state) {
            softAssert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(by)), "[" + by.toString() + "] was visible");
        }

        if (assertAll) {
            softAssert.assertAll();
        }
    }

    public void visibility(MobileElement mobileElement, boolean state, boolean assertAll) {
        SoftAssert softAssert = new SoftAssert();
        if (state) {
            softAssert.assertTrue(wait.until(ExpectedConditions.visibilityOf(mobileElement)).isDisplayed(), "[" + mobileElement.toString() + "] was not visible");
        }
        if (!state) {
            softAssert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(mobileElement)), "[" + mobileElement.toString() + "] was visible");
        }

        if (assertAll) {
            softAssert.assertAll();
        }
    }

    public void select(By by, String option, boolean log) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(option);
        if (log) System.out.println("selected : " + option + " on :" + by.toString());

    }

    public void isEmptyDropDown(By by, boolean state, boolean assertAll) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Select select = new Select(driver.findElement(by));
        dropDownState(state, assertAll, select, by.toString());


    }

    private void dropDownState(boolean state, boolean assertAll, Select select, String s) {
        SoftAssert softAssert = new SoftAssert();

        if (state) {
            softAssert.assertTrue(select.getOptions().isEmpty()
                    , "[" + s + "] was not empty");
        }

        if (!state) {
            softAssert.assertTrue(!select.getOptions().isEmpty()
                    , "[" + s + "] is empty");
        }

        if (assertAll) {
            softAssert.assertAll();
        }
    }


    public void isEmptyDropDown(MobileElement mobileElement, boolean state, boolean assertAll) {
        wait.until(ExpectedConditions.visibilityOf(mobileElement));
        Select select = new Select(mobileElement);
        dropDownState(state, assertAll, select, mobileElement.toString());


    }

    public void valueEquals(By by, String value, boolean state, boolean assertAll) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        SoftAssert softAssert = new SoftAssert();
        if (state) {
            softAssert.assertTrue(driver.findElement(by).getText().equalsIgnoreCase(value)

                    , "[" + by.toString() + "] has wrong value");
        }

        if (!state) {
            softAssert.assertTrue(!driver.findElement(by).getText().equalsIgnoreCase(value)
                    , "[" + by.toString() + "] has wrong value");
        }

        if (assertAll) {
            softAssert.assertAll();
        }
    }

    public void scrollIntoView(By by, boolean log) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        String text = driver.findElement(by).getText();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"ფიქრია კალანდაძე\").instance(0))");
        if (log) System.out.println(text);
    }

    public void scrollIntoView(By by, String text, boolean click, boolean log) {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))");
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        if (click) {
            driver.findElement(by).click();
        }
        if (log) System.out.println("scrolled until " + text + " text was visible");
    }

    public void checked(By by, boolean checked, boolean assertAll) {
        String attribute = "checked";
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        SoftAssert softAssert = new SoftAssert();

        if (checked) {
            wait.until(ExpectedConditions.attributeContains(by, attribute, TRUE_VALUE));
            String checkedStatus = driver.findElement(by).getAttribute(attribute);
            softAssert.assertTrue(checkedStatus.equalsIgnoreCase(TRUE_VALUE), "[" + by.toString() + "] is not checked");
        }
        if (!checked) {
            wait.until(ExpectedConditions.attributeContains(by, attribute, FALSE_VALUE));
            String checkedStatus = driver.findElement(by).getAttribute(attribute);
            softAssert.assertTrue(checkedStatus.equalsIgnoreCase(FALSE_VALUE), "[" + by.toString() + "] is checked");
        }
        if (assertAll) {
            softAssert.assertAll();
        }
    }

    public void clickable(By by, boolean clickable, boolean assertAll) {
        String attribute = "clickable";
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        SoftAssert softAssert = new SoftAssert();

        if (clickable) {
            wait.until(ExpectedConditions.attributeContains(by, attribute, TRUE_VALUE));
            String checkedStatus = driver.findElement(by).getAttribute(attribute);
            softAssert.assertTrue(checkedStatus.equalsIgnoreCase(TRUE_VALUE), "[" + by.toString() + "] is not clickable");
        }
        if (!clickable) {
            wait.until(ExpectedConditions.attributeContains(by, attribute, FALSE_VALUE));
            String checkedStatus = driver.findElement(by).getAttribute(attribute);
            softAssert.assertTrue(checkedStatus.equalsIgnoreCase(FALSE_VALUE), "[" + by.toString() + "] is clickable");
        }
        if (assertAll) {
            softAssert.assertAll();
        }
    }


    public void enabled(By by, boolean enabled, boolean assertAll) {
        String attribute = "enabled";
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        SoftAssert softAssert = new SoftAssert();

        if (enabled) {
            wait.until(ExpectedConditions.attributeContains(by, attribute, TRUE_VALUE));
            String checkedStatus = driver.findElement(by).getAttribute(attribute);
            softAssert.assertTrue(checkedStatus.equalsIgnoreCase(TRUE_VALUE), "[" + by.toString() + "] is not enabled");
        }
        if (!enabled) {
            wait.until(ExpectedConditions.attributeContains(by, attribute, FALSE_VALUE));
            String checkedStatus = driver.findElement(by).getAttribute(attribute);
            softAssert.assertTrue(checkedStatus.equalsIgnoreCase(FALSE_VALUE), "[" + by.toString() + "] is enabled");
        }
        if (assertAll) {
            softAssert.assertAll();
        }
    }


    public void setValue(By by, String value, boolean log) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        MobileElement el1 = (MobileElement) driver.findElement(by);
        el1.setValue(value);
        if (log) System.out.println("set value : " + value + " on: " + by.toString());
    }


}
