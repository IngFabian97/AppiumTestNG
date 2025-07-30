package utilities;

import java.io.File;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners({listeners.TestListeners.class, listeners.SuiteListeners.class})
public class BaseTest {

    protected SoftAssert softAssert;
    protected final String regression = "regression";
    protected final String smoke = "smoke";

@BeforeMethod(alwaysRun = true)
public void masterSetup(){
    Logs.info("Setup del padre");
    softAssert = new SoftAssert();

}
@AfterMethod(alwaysRun = true)
public void masterTearDown(){
    Logs.info("TearDown del padre");
}

private static DesiredCapabilities getDesiredCapabilities(){
    final var desiredCapabilities = new DesiredCapabilities();

    final var fileAPK = new File("src/test/resources/apk/sauceLabs.apk");
    
    desiredCapabilities.setCapability("appium:autoGrantPermissions", true);
    desiredCapabilities.setCapability("appium:appWaitActivity", "com.swaglabsmobileapp.MainActivity");
    desiredCapabilities.setCapability("appium:platformName", "Android");
    desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
    desiredCapabilities.setCapability("appium:app", fileAPK.getAbsolutePath());

    return desiredCapabilities;
}
}
