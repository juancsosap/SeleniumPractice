package utils.web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.automationpractice.RegisterPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Browser {
    protected WebDriver driver;
    protected BrowserSelector selector;
    protected Object options;

    public Browser(BrowserSelector selector, String driverPath, int waitSeconds) {
        this.selector = selector;
        setPath(driverPath);
        setOptions();
        setDriver();

        driver.manage().timeouts().implicitlyWait(waitSeconds, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(waitSeconds, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(waitSeconds, TimeUnit.SECONDS);

        driver.manage().window().maximize();
    }

    public void setOptions() {
        switch(selector) {
            case CHROME:
                options = new ChromeOptions();
                ((ChromeOptions) options).addArguments("--incognito");
                break;
            case FIREFOX:
                //options = new FirefoxProfile();
                //((FirefoxProfile) options).setPreference("browser.privatebrowsing.autostart", true);
                break;
            case EDGE:
                options = new EdgeOptions();
                ((EdgeOptions) options).setCapability("InPrivate", true);
                break;
            case IE:
                //options = DesiredCapabilities.internetExplorer();
                //((DesiredCapabilities) options).setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
                //((DesiredCapabilities) options).setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
                break;
            case SAFARI:
                break;
            case OPERA:
                //DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
                //options = new OperaOptions();
                //((DesiredCapabilities) options).setCapability("private", true);
                //capabilities.setCapability(OperaOptions.CAPABILITY, options);
                break;
        }
    }

    public void setDriver() {
        switch(selector) {
            case CHROME: driver = new ChromeDriver((ChromeOptions) options); break;
            case FIREFOX: driver = new FirefoxDriver(); break;
            case EDGE: driver = new EdgeDriver((EdgeOptions) options); break;
            case IE: driver = new InternetExplorerDriver(); break;
            case SAFARI: driver = new SafariDriver(); break;
            case OPERA: driver = new OperaDriver(); break;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setPath(String driverPath) {
        switch (selector) {
            case CHROME: System.setProperty("webdriver.chrome.driver", driverPath); break;
            case FIREFOX: System.setProperty("webdriver.gecko.driver", driverPath); break;
            case EDGE: System.setProperty("webdriver.edge.driver", driverPath); break;
            case IE: System.setProperty("webdriver.ie.driver", driverPath); break;
        }
    }

    public void get(String url) {
        driver.get(url);
    }

    public void wait(int miliseconds) {
        try { if(miliseconds > 0) Thread.sleep(miliseconds); }
        catch (InterruptedException e) {}
    }

    public void waitUntilElement(By locator, int miliseconds) {
        WebDriverWait explicitWait = new WebDriverWait(driver, miliseconds);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilElements(By locator, int miliseconds) {
        WebDriverWait explicitWait = new WebDriverWait(driver, miliseconds);
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
    }

    public Object executeScript(By locator, String script) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(script, getElement(locator));
    }

    public void highlight(By locator, int miliseconds) {
        String cssBorder = getElement(locator).getCssValue("border");
        setStyle(locator, "border", "2px solid red");
        wait(miliseconds);
        setStyle(locator, "border", cssBorder);
    }

    public void setAttribute(By locator, String attribute, String value) {
        String script = "arguments[0].setAttribute('" + attribute + "', '" + value + "')";
        executeScript(locator, script);
    }

    public void setStyle(By locator, String property, String value) {
        String script = "arguments[0].style." + property + " = '" + value + "'";
        executeScript(locator, script);
    }

    public WebElement getElement(By locator) {
        WebElement element = driver.findElement(locator);
        return element;
    }

    public List<WebElement> getElements(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return elements;
    }

    public Select getSelect(By locator) {
        Select select = new Select(getElement(locator));
        return select;
    }

    public void inputText(By locator, int miliseconds, String value) {
        highlight(locator, miliseconds);
        getElement(locator).sendKeys(value);
    }

    public void click(By locator, int miliseconds) {
        highlight(locator, miliseconds);
        getElement(locator).click();
    }

    public void selectByValue(By locator, int miliseconds, String value) {
        highlight(locator, miliseconds);
        getSelect(RegisterPage.selectBirthDay).selectByValue(value);
    }

    public void selectByVisibleText(By locator, int miliseconds, String value) {
        highlight(locator, miliseconds);
        getSelect(RegisterPage.selectBirthDay).selectByVisibleText(value);
    }

    public void close() {
        driver.close();
    }
}