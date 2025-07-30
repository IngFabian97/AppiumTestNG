package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import utilities.Logs;

public class SuiteListeners implements ISuiteListener{

    @Override
    public void onFinish(ISuite suite) {
        Logs.info("Suite inicializada: " + suite.getName());
    }

    @Override
    public void onStart(ISuite suite) {
        Logs.info("Suite finalizada: " + suite.getName());
    }

}
