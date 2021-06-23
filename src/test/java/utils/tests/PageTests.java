package utils.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.data.DataDriven;
import utils.data.DataStorage;
import utils.pom.Page;
import utils.web.Browser;
import utils.web.BrowserSelector;

import java.lang.reflect.Method;

public abstract class PageTests<T extends Page> {

    protected Browser browser;
    protected Asserter asserter;
    protected T page;
    protected DataDriven dataDriven;
    protected DataStorage data;

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
        page.go(1000);
        data = dataDriven.getData(method.getName());

        System.out.print("Running : ");
        if(!data.isEmpty()) System.out.println(data.get(0));
        else System.out.println(this.getClass().getName() + "." + method.getName());
    }

    @AfterClass
    public void afterClass() {
        browser.close();
    }

}
