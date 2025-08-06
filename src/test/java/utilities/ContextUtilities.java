package utilities;

import io.appium.java_client.android.AndroidDriver;

public class ContextUtilities {
    private static AndroidDriver getDriver() {
        return new DriverProvider().get();
    }

    public static void switchWebContext() {
        final var driver = getDriver();
        final var setContext = driver.getContextHandles();
        Logs.info("Set context: " + setContext);

        for(var context : setContext) {
            if(context.contains("WEBVIEW")) {
                Logs.info("Cambiando a contexto web: " + context);
                driver.context(context);
            }
        }
    }

    public static void switchNativeContext() {
        final var driver = getDriver();
        final var setContext = driver.getContextHandles();
        Logs.info("Set context: " + setContext);

        for(var context : setContext) {
            if(context.contains("NATIVE_APP")) {
                Logs.info("Cambiando a contexto nativo: " + context);
                driver.context(context);
            }
        }
    }

}
