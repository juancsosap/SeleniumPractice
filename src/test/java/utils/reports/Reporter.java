package utils.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public abstract class Reporter {

    private ExtentReports report;
    private ExtentTest test;

    public abstract void begin(String testName);

    public abstract void log(LogStatus status, String details);

    public abstract void commit();

}
