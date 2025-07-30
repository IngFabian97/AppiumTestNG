package utilities;

import java.io.File;
import java.net.MalformedURLException;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

@Listeners({listeners.TestListeners.class, listeners.SuiteListeners.class})
public class BaseTest {

    protected SoftAssert softAssert;
    protected final String regression = "regression";
    protected final String smoke = "smoke";
    protected AndroidDriver driver;

@BeforeMethod(alwaysRun = true)
public void masterSetup(){
    softAssert = new SoftAssert();

    Logs.debug("Iniciando el driver");
    driver = initDriver();

    Logs.debug("Asignando el driver a driver provider");
    new DriverProvider().setDriver(driver);

}
@AfterMethod(alwaysRun = true)
public void masterTearDown(){
    Logs.debug("Finalizando el driver");
    driver.quit();
}

private static AndroidDriver initDriver(){
    try{
        final var appiumUrl = "http://127.0.0.1:4723/";
        final var desiredCapabilities = getDesiredCapabilities();

        Logs.debug("Iniciando el driver con las siguientes capacidades: " + desiredCapabilities.toString());
        return new AndroidDriver(new URL(appiumUrl), desiredCapabilities);
    }catch (MalformedURLException malformedURLException) {
        Logs.error("Error al iniciar el driver: " + malformedURLException.getLocalizedMessage());
        throw new RuntimeException();
    }
}

private static DesiredCapabilities getDesiredCapabilities(){
    final var desiredCapabilities = new DesiredCapabilities();

    final var fileAPK = new File("src/test/java/resources/apk/sauceLabs.apk");
    
    desiredCapabilities.setCapability("appium:autoGrantPermissions", true);
    desiredCapabilities.setCapability("appium:appWaitActivity", "com.swaglabsmobileapp.MainActivity");
    desiredCapabilities.setCapability("appium:platformName", "Android");
    desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
    desiredCapabilities.setCapability("appium:app", fileAPK.getAbsolutePath());

    return desiredCapabilities;
}


}
