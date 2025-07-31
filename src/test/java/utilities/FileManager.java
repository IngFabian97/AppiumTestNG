package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.qameta.allure.Attachment;

public class FileManager {
    private static final String scrrenshotPath = "src/test/resources/screenshots/";
    private static final String pageSourcePath = "src/test/resources/pageStructure/";

    public static void getPageSource(String fileName){
        Logs.debug("Guardando estructura de la página: " + fileName);

        final var path = String.format("%s%s.html", pageSourcePath, fileName);

        try{
            final var file = new File(path);
            Logs.debug("Creandos los directorios padres si no existen");
            if(file.getParentFile().mkdirs()){
                final var fileWriter = new FileWriter(file);
                final var pageSource = new DriverProvider().getDriver().getPageSource();
                if(pageSource != null){
                    fileWriter.write(Jsoup.parse(pageSource).toString());
                }

                fileWriter.close();
            }
        }catch (IOException ioException){
            Logs.error("Error al guardar la estructura de la página: " + ioException.getLocalizedMessage());
        }
    }

    public static void getScreenshot(String screenshotName){
        Logs.debug("Guardando screenshot: " + screenshotName);
        
        final var screenshotFile = ((TakesScreenshot) new DriverProvider().getDriver()).getScreenshotAs(OutputType.FILE);

        final var path = String.format("%s%s.png", scrrenshotPath, screenshotName);
        try{
            FileUtils.copyFile(screenshotFile, new File(path));
        }catch (IOException ioException){
            Logs.error("Error al guardar el screenshot: " + ioException.getLocalizedMessage());
        }
    }

    public static void deletePreviousScreenshots() {
        try{
            Logs.debug("Borrando screenshots previos");
            FileUtils.cleanDirectory(new File(scrrenshotPath));
            FileUtils.cleanDirectory(new File(pageSourcePath));
        }catch (IOException ioException){
            Logs.error("Error al borrar screenshots previos: " + ioException.getLocalizedMessage());
        }
    }

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] getScreenShot(){
        return ((TakesScreenshot) new DriverProvider().getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "pageSource", type = "text/html", fileExtension = "txt")
    public static String getPageSource() {
        final var pageSource = new DriverProvider().getDriver().getPageSource();
        return pageSource != null ? Jsoup.parse(pageSource).toString() : "Error al obtener la estructura de la página";
}
}
