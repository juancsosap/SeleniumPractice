package utils.tests;

import org.testng.annotations.*;
import utils.data.DataDriven;
import utils.data.DataStorage;
import utils.pom.Page;
import utils.reports.ExtentReporter;
import utils.reports.LogStatus;
import utils.reports.Reporter;
import utils.web.Browser;
import utils.web.BrowserSelector;

import java.lang.reflect.Method;

public abstract class PageTests<T extends Page> {

    protected Browser browser;
    protected Asserter asserter;
    protected T page;
    protected DataDriven dataDriven;
    protected DataStorage data;

    protected static final int wait = 1000;

    protected static Reporter reporter;

    @BeforeSuite
    public static void beforeSuite() {
        reporter = new ExtentReporter("target/reports/output.html");
    }

    @BeforeClass
    public void beforeClass() {
        String driverPath = "src/test/resources/webdriver/chromedriver.exe";
        browser = new Browser(BrowserSelector.CHROME, driverPath, 20);
        asserter = new Asserter(browser);

        String filePath = "src/test/resources/data/tests.xlsx";
        dataDriven = new DataDriven(filePath, "Cases", this.getClass());
    }

    @BeforeClass
    public abstract void preparePage();

    @BeforeMethod
    public void beforeMethod(Method method) {
        data = dataDriven.getData(method.getName());

        String methodName = this.getClass().getName() + "." + method.getName();
        String name = "";
        if(!data.isEmpty()) name = data.get(0);
        else name = methodName;
        reporter.begin(name.replace(") ", ") <br>"));
        printMessage(LogStatus.INFO, "Running : " + name);
        printMessage(LogStatus.INFO, "Calling Method : " + methodName);

        page.go(1000);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("--------------------------------------------------------");
    }

    @AfterClass
    public void afterClass() { browser.close(); }

    @AfterSuite
    public static void afterSuite() {
        reporter.commit();
    }

    public static void printMessage(LogStatus status, String message) {
        System.out.println(status + " : " + message);
        reporter.log(status, message + "<br>");
    }

}
