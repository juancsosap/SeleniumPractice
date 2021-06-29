package utils.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter extends Reporter {

    private ExtentReports report;
    private ExtentTest test;

    public ExtentReporter(String filePath) {
        report = new ExtentReports();
        ExtentSparkReporter observer = new ExtentSparkReporter(filePath);
        report.attachReporter(observer);
    }

    public void begin(String testName) {
        test = report.createTest(testName);
    }

    public void log(LogStatus status, String details) {
        test.log(status.get(), details);
    }

    public void commit() {
        report.flush();
    }

}
