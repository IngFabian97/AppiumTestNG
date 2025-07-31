package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import utilities.FileManager;
import utilities.Logs;

public class SuiteListeners implements ISuiteListener{

    @Override
    public void onStart(ISuite suite) {
        Logs.info("Suite finalizada: " + suite.getName());
        FileManager.deletePreviousScreenshots();
    }

    @Override
    public void onFinish(ISuite suite) {
        Logs.info("Suite inicializada: " + suite.getName());
    }

}
