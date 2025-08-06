package automation;

import org.apache.commons.math3.analysis.function.Log;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import utilities.BaseTest;
import utilities.Logs;

public class DeeplinkTests extends BaseTest{
    @Test
    public void completarCompraDeepLinkTest(){
        final var deeplink = "swaglabs://complete";

        Logs.info("Abriendo el deeplink: " + deeplink);
        driver.get(deeplink);

        Logs.info("Esperando que la pagina de compra cargue");
        sleep(1500);

        Logs.info("Clickeand en el boton back home");
        driver.findElement(AppiumBy.accessibilityId("test-BACK HOME")).click();

        Logs.info("Esperando que la pagina de shopping cargue");
        sleep(1500);

        Logs.info("Verificando que la pagina de shopping se muestre correctamente");
        Assert.assertTrue((driver.findElement(AppiumBy.androidUIAutomator("text(\"PRODUCTS\")"))).isDisplayed(), "El titulo de la pagina no se muestra como se esperaba");
    }

    @Test
    public void itemDetalleDeepLinkTest(){
           final var deeplink = "swaglabs://swag-item/4";

        Logs.info("Abriendo el deeplink: " + deeplink);
        driver.get(deeplink);

        Logs.info("Esperando que la pagina de compra cargue");
        sleep(1500);

        Logs.info("Verificando que el titulo del producto sea Sauce Labs Onesie");
        final var listaTitulos = driver.findElements(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView"));
        final var titulo = listaTitulos.get(0);
        
        Assert.assertEquals(titulo.getText(), "Sauce Labs Onesie", "El titulo del producto no es el esperado");
}

    @Test
    public void webViewDeepLinkTest(){
        final var deeplink = "swaglabs://webview";

        Logs.info("Abriendo el webview usando el deeplink: " + deeplink);
        driver.get(deeplink);

        Logs.info("Esperando que la pagina de webview cargue");
        sleep(1500);

        Logs.info("Escribiendo la url en en input de la pagina web");
        driver.findElement(AppiumBy.accessibilityId("test-enter a https url here...")).sendKeys("https://www.saucedemo.com");
        
        Logs.info("Clickeando en el boton Go To Site");
        driver.findElement(AppiumBy.accessibilityId("test-GO TO SITE")).click();

        Logs.info("Esperando que la pagina web cargue");
        sleep(2000);

        Logs.debug("Cambiando al contexto web");
        utilities.ContextUtilities.switchWebContext();

        Logs.info("Verificando si el username es visible en la pagina web");
        final var usernameInput = driver.findElement(By.id("user-name"));
        Assert.assertTrue(usernameInput.isDisplayed(), "El input de username no se muestra como se esperaba");

        Logs.debug("Regresando al contexto nativo");
        utilities.ContextUtilities.switchNativeContext();

        Logs.info("Presionando el boton atras del dispositivo para regresar a la pagina anterior");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        Logs.info("Esperando que la pagina de webview cargue");
        sleep(1500);

        Logs.info("Verificando que se haya regresado a la pagina de webview");
        Assert.assertTrue((driver.findElement(AppiumBy.accessibilityId("test-GO TO SITE"))).isDisplayed(), "No se ha regresado a la pagina de webview como se esperaba");

    }

}
