package automation;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import utilities.BaseTest;
import utilities.Gestures;
import utilities.Logs;

public class LoginTests extends BaseTest{

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Logs.info("Esperando que la app cargue");
        // sleep(1500);

    }

    @Test(groups = {"regression, smoke"})
    public void credencialesBloqueadasTest(){
        Logs.info("Escribiendo el username");
        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("locked_out_user");
    
        Logs.info("Escirbiendo el password");
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys("secret_sauce");

        Logs.info("Haciendo click en el boton de login");
        driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();

        // Logs.info("Esperando que el mensaje de error aparezca");
        // sleep(1500);

        Logs.info("Validando que el mensaje de error sea el correcto");
        final var mensajeError = driver.findElement(AppiumBy.androidUIAutomator("text(\"Sorry, this user has been locked out.\")"));
        
        softAssert.assertTrue(mensajeError.isDisplayed(), "El mensaje de error no se muestra como se esperaba");
        softAssert.assertEquals(mensajeError.getText(), "Sorry, this user has been locked out.", "El mensaje de error no es el esperado");
        softAssert.assertAll();

    }

    @Test(groups = {"regression"})
    public void verifciarPagTest(){
        Logs.info("Verificando la pagin de login");
        final var usernameInput = driver.findElement(AppiumBy.accessibilityId("test-Username"));
        final var passwordInput = driver.findElement(AppiumBy.accessibilityId("test-Password"));
        final var loginButton = driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));

        softAssert.assertTrue(usernameInput.isDisplayed(), "El campo de username no se muestra como se esperaba");
        softAssert.assertTrue(passwordInput.isDisplayed(), "El campo de password no se muestra como se esperaba");
        softAssert.assertTrue(loginButton.isDisplayed(), "El boton de login no se muestra como se esperaba");
        softAssert.assertTrue(loginButton.isEnabled(), "El boton de login no esta habilitado como se esperaba");
        softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void taoStandarUserTest(){
        Logs.info("Haciendo tap en el label de standard_user");
        Gestures.tap(driver.findElement(AppiumBy.accessibilityId("test-standard_user")));

        Logs.info("Haciendo tap en el boton de login");
        Gestures.tap(driver.findElement(AppiumBy.accessibilityId("test-LOGIN")));

        Logs.info("Verificando que se llegue a la pagina de shopping");
        Assert.assertTrue(driver.findElement(AppiumBy.accessibilityId("test-PRODUCTS")).isDisplayed(), "No se llego a la pagina de shopping como se esperaba");
    }


}
