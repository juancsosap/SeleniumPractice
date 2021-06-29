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
import java.nio.file.Path;

public abstract class PageTests<T extends Page> {

    protected Browser browser;
    protected Asserter asserter;
    protected T page;
    protected DataDriven dataDriven;
    protected DataStorage data;

    protected static final int wait = 1000;
    protected static final String reportFolder = "target/reports/";
    protected static final String resourceFolder = "src/test/resources/";

    protected static Reporter reporter;

    @BeforeSuite
    public static void beforeSuite() {
        reporter = new ExtentReporter(path(reportFolder, "output", "html"));
    }

    @BeforeClass
    public void beforeClass() {
        String driverPath = path(resourceFolder + "webdriver", "chromedriver", "exe");
        browser = new Browser(BrowserSelector.CHROME, driverPath, 20);
        asserter = new Asserter(browser);

        String filePath = path(resourceFolder + "data", "tests", "xlsx");
        dataDriven = new DataDriven(filePath, "Cases", this.getClass());
    }

    @BeforeClass
    public abstract void preparePage();

    @BeforeMethod
    public void beforeMethod(Method method) {
        data = dataDriven.getData(method.getName());

        String methodName, name;
        if(!data.isEmpty()) name = data.get(0);
        else name = getMethodName(method);
        reporter.begin(name.replace(") ", ") <br>"));
        printMessage(LogStatus.INFO, "Running : " + name);
        printMessage(LogStatus.INFO, "Calling Method : " + getMethodName(method));

        page.go(1000);
    }

    @AfterMethod
    public void afterMethod(Method method) {
        browser.takeSnapShot(reportFolder + "images/", getMethodName(method) + ".jpg");
        System.out.println("--------------------------------------------------------");
    }

    @AfterClass
    public void afterClass() { browser.close(); }

    @AfterSuite
    public static void afterSuite() {
        reporter.commit();
    }

    private String getMethodName(Method method) {
        return this.getClass().getName() + "." + method.getName();
    }

    public static void printMessage(LogStatus status, String message) {
        System.out.println(status + " : " + message);
        reporter.log(status, message + "<br>");
    }

    private static String path(String folder, String file, String ext) {
        return Path.of(folder, file + "." + ext).toString();
    }

}
