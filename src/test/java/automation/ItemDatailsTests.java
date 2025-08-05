package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import utilities.BaseTest;
import utilities.Logs;

public class ItemDatailsTests extends BaseTest{

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

        Logs.info("Haicnedo click en la primera imagen para ir al detalle del producto");
        driver.findElements(AppiumBy.androidUIAutomator("description(\"test-Item\").childSelector(className(\"android.widget.ImageView\"))"))
              .get(0).click();

        Logs.info("Esperando que la pagina de detalle del producto cargue");
        sleep(1500);
    }

    @Test
    public void verificarPaginaTest(){
        Logs.info("Verificando el detaller del producto");
        final var botonBackProducts= driver.findElement(AppiumBy.accessibilityId("test-BACK TO PRODUCTS"));
        final var imagenProducto = driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.ImageView\").instance(5)"));

        final var listaTitulos = driver.findElements(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView"));
        final var titulo = listaTitulos.get(0);
        final var descripcion = listaTitulos.get(1);

        final var precio = driver.findElement(AppiumBy.accessibilityId("test-Price"));

        softAssert.assertTrue(botonBackProducts.isDisplayed(), "El boton de volver a productos no se muestra como se esperaba");
        softAssert.assertTrue(botonBackProducts.isEnabled(), "El boton de volver a productos no esta habilitado como se esperaba");
        softAssert.assertTrue(imagenProducto.isDisplayed(), "La imagen del producto no se muestra como se esperaba");
        softAssert.assertTrue(titulo.isDisplayed(), "El titulo del producto no se muestra como se esperaba");
        softAssert.assertTrue(descripcion.isDisplayed(), "La descripcion del producto no se muestra como se esperaba");
        softAssert.assertTrue(precio.isDisplayed(), "El precio del producto no se muestra como se esperaba");   
        softAssert.assertAll();
    }

    @Test
    public void clickBackProductsTest(){
        Logs.info("Haciendo click en el boton de volver a productos");
        driver.findElement(AppiumBy.accessibilityId("test-BACK TO PRODUCTS")).click();

        Logs.info("Esperando que la pagina de shopping cargue");
        sleep(1500);

        Logs.info("Verificando que se haya regresado a la pagina de shopping");
        final var title = driver.findElement(AppiumBy.androidUIAutomator("text(\"PRODUCTS\")"));
        softAssert.assertTrue(title.isDisplayed(), "No se ha regresado a la pagina de shopping como se esperaba");
    }

    @Test
    public void presionarBotonAtrasTest(){
        Logs.info("Presionando el boton de atras del dispositivo");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        Logs.info("Esperando que la pagina de shopping cargue");
        sleep(1500);

        Logs.info("Verificando que se haya regresado a la pagina de shopping");
        final var title = driver.findElement(AppiumBy.androidUIAutomator("text(\"PRODUCTS\")"));
        softAssert.assertTrue(title.isDisplayed(), "No se ha regresado a la pagina de shopping como se esperaba");}

}
