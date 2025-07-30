package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import utilities.Logs;

public class TestListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Logs.info("Comenzando la ejecución del test: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Logs.info("Test fallido: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logs.info("Test omitido: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Logs.info("Test exitoso: " + result.getName());
    }
    
}
