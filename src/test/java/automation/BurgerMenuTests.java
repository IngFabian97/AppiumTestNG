package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import utilities.BaseTest;
import utilities.Logs;

public class BurgerMenuTests extends BaseTest{

     @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Logs.info("Escribiendo el username");
        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("standard_user");
    
        Logs.info("Escirbiendo el password");
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys("secret_sauce");

        Logs.info("Haciendo click en el boton de login");
        driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click(); 

        Logs.info("Esperando que la pagina de shopping cargue");
        sleep(1500);

        Logs.info("Abriendo el menu de hamburguesa");
        driver.findElement(AppiumBy.accessibilityId("test-Menu")).click();

        Logs.info("Esperando que el menu de hamburguesa se abra");
        sleep(2000);
    }

    @Test
    public void logoutTest() {
        Logs.info("Haciendo click en el boton de logout");
        driver.findElement(AppiumBy.accessibilityId("test-LOGOUT")).click();

        Logs.info("Esperando que la pagina de login cargue");
        sleep(1500);

        Logs.info("Validando que la pagina de login se muestre correctamente");
        final var usernameInput = driver.findElement(AppiumBy.accessibilityId("test-Username"));
        final var passwordInput = driver.findElement(AppiumBy.accessibilityId("test-Password"));
        final var loginButton = driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));

        softAssert.assertTrue(usernameInput.isDisplayed(), "El campo de username no se muestra como se esperaba");
        softAssert.assertTrue(passwordInput.isDisplayed(), "El campo de password no se muestra como se esperaba");
        softAssert.assertTrue(loginButton.isDisplayed(), "El boton de login no se muestra como se esperaba");
        softAssert.assertAll();
    }
    
    @Test
    public void buttonXTest() {
        Logs.info("Clicando en el boton X para cerrar el menu de hamburguesa");
        driver.findElement(AppiumBy.accessibilityId("test-Close")).click();

        Logs.info("Esperando que la pagina de shopping cargue");
        sleep(1500);    

        Logs.info("Validando que la pagina de shopping se muestre correctamente");
        softAssert.assertTrue(driver.findElement(AppiumBy.accessibilityId("test-PRODUCTS")).isDisplayed(), "El titulo de la pagina de shopping no se muestra como se esperaba");
    }
}