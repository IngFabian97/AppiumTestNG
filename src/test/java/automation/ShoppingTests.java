package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import utilities.BaseTest;
import utilities.Logs;

public class ShoppingTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Logs.info("Escribiendo el username");
        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("standard_user");
    
        Logs.info("Escirbiendo el password");
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys("secret_sauce");

        Logs.info("Haciendo click en el boton de login");
        driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click(); 

        // Logs.info("Esperando que la pagina de shopping cargue");
        // sleep(1500);
    }

    @Test(groups = {"regression, smoke"})
    public void verificarPaginaShoppingTest() {
        Logs.info("Verificando la pagina de shopping");
        final var title = driver.findElement(AppiumBy.androidUIAutomator("text(\"PRODUCTS\")"));
        final var buttonFilter = driver.findElement(AppiumBy.accessibilityId("test-Modal Selector Button"));
        final var buttonViewList = driver.findElement(AppiumBy.accessibilityId("test-Toggle"));
        final var listItems = driver.findElement(AppiumBy.accessibilityId("test-PRODUCTS"));

        softAssert.assertTrue(title.isDisplayed(), "El titulo de la pagina no se muestra como se esperaba");
        softAssert.assertTrue(buttonFilter.isDisplayed(), "El boton de filtro no se muestra como se esperaba");
        softAssert.assertTrue(buttonFilter.isEnabled(), "El boton de filtro no esta habilitado como se esperaba");
        softAssert.assertTrue(buttonViewList.isDisplayed(), "El boton de vista de lista no se muestra como se esperaba");
        softAssert.assertTrue(buttonViewList.isEnabled(), "El boton de vista de lista no esta habilitado como se esperaba");
        softAssert.assertTrue(listItems.isDisplayed(), "La lista de productos no se muestra como se esperaba");
        softAssert.assertAll();

    }

    @Test(groups = {"regression"})
    public void filtroPrecioTest(){
        Logs.info("Clicando en el boton de filtro");
        driver.findElement(AppiumBy.accessibilityId("test-Modal Selector Button")).click();

        Logs.info("Esperando que se muestren los filtros");
        sleep(500);

        Logs.info("Seleccionando el filtro de precio: bajo a alto");
        driver.findElement(AppiumBy.androidUIAutomator("text(\"Price (low to high)\")")).click();

        // Logs.info("Espernado que se refresque la pagina");
        // sleep(1500);

        Logs.info("Verificando la información del primer producto");
        final var listaTitulos = driver.findElements(AppiumBy.accessibilityId("test-Item title"));
        final var listaPrecios = driver.findElements(AppiumBy.accessibilityId("test-Price"));

        final var primerTitulo = listaTitulos.get(0).getText();
        final var primerPrecio = Double.parseDouble(listaPrecios.get(0).getText().replace("$", ""));

        softAssert.assertEquals(primerTitulo, "Sauce Labs Onesie", "El titulo del primer producto no es el esperado");
        softAssert.assertEquals(primerPrecio, 7.99, "El precio del primer producto no es el esperado");
        softAssert.assertAll();


    }
}
