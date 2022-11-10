package local.projects.steam.runners;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseRunner {
    public static ThreadLocalDriver threadLocalDriver = new ThreadLocalDriver();

    public volatile AndroidDriver driver;

    @BeforeSuite
    @Parameters({"platformVersion", "udid"})
    public void mobileRunner() throws MalformedURLException {
        URL remoteURL;

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        // setting up desiredCapabilities for appium server
        String udid = "emulator-5554";
        String platformVersion = "8.1";
//        desiredCapabilities.setCapability("app", System.getProperty("user.dir") + "\\src\\test\\resources\\apps\\YouTube_17.44.34_Apkpure.apk");
        desiredCapabilities.setCapability("app", System.getProperty("user.dir") + "\\src\\test\\resources\\apps\\Steam_3.0_Apkpure.apk");

        desiredCapabilities.setCapability("udid", udid);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4 API 27");
        remoteURL = new URL("http://localhost:4723/wd/hub");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "android");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability("unicodeKeyboard", true);
//        desiredCapabilities.setCapability("appWaitActivity", "com.valvesoftware.android.steam.community");
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        //  desiredCapabilities.setCapability("autoWebview",false);
        desiredCapabilities.setCapability("chromeDriverPort", "11001");
        desiredCapabilities.setCapability("newCommandTimeout", 120);
        desiredCapabilities.setCapability("appium:uiautomator2ServerInstallTimeout", 120000);
        desiredCapabilities.setCapability("appium:adbExecTimeout", 120000);
        desiredCapabilities.setCapability("appium:androidInstallTimeout", 120000);
        desiredCapabilities.setCapability("appWaitDuration", 120000);
        // initialising selenium driver for appium server
        threadLocalDriver.setTlDriver(new AndroidDriver(remoteURL, desiredCapabilities));
        driver = threadLocalDriver.getTLDriver();
    }


//    @AfterMethod
//    public synchronized void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

}