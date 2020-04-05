package org.testerfabrica.intermedio.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class realTimeReport implements ITestListener {//Implmentamos la interfaz ITestListener
    // (ya trae metodos, hacer clic en implementar metodos en el bombillo o raya roja)
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Tests start: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Tests pass: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Tests failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Tests Skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Start Excution (Test): " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("End of Excution (Test): " + context.getName());
    }

}
