package utilities;

import io.appium.java_client.android.AndroidDriver;

public class DriverProvider {
    private static final ThreadLocal<AndroidDriver> threadLocal = new ThreadLocal<>();

    public AndroidDriver get() {
        return threadLocal.get();
    }

    public void setDriver(AndroidDriver driver) {
       threadLocal.set(driver);
    }
}
