package utils.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseReporter extends Reporter {

    public void begin(String testName) {  }

    public void log(LogStatus status, String details) {
        org.testng.Reporter.log(status + " : " + details);
    }

    public void commit() {  }

}
