package utils.web;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
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
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.reports.BaseReporter;
import utils.reports.ExtentReporter;
import utils.reports.Reporter;
import utils.reports.LogStatus;
import utils.tests.PageTests;
//import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Browser {
    protected WebDriver driver;
    protected BrowserSelector selector;
    protected Object options;

    public Browser(BrowserSelector selector, String driverPath, int waitSeconds, boolean privateMode) {
        this.selector = selector;
        setPath(driverPath);
        setOptions(privateMode);
        setDriver();
        setImplicitWait(waitSeconds);

        driver.manage().window().maximize();
    }
    public Browser(BrowserSelector selector, String driverPath) {
        this(selector, driverPath, 10, false);
    }
    public Browser(BrowserSelector selector, String driverPath, int waitSeconds) {
        this(selector, driverPath, waitSeconds, false);
    }

    public void setOptions(boolean privateMode) {
        options = switch (selector) {
            case CHROME -> new ChromeOptions();
            case EDGE -> new EdgeOptions();
            default -> null;
        };
        if(privateMode) {
            switch(selector) {
                case CHROME: ((ChromeOptions) options).addArguments("--incognito"); break;
                case EDGE: ((EdgeOptions) options).setCapability("InPrivate", true); break;
            }
        }
    }

    public void setDriver() {
        logMessage(LogStatus.INFO, "Opening " + selector + " Browser");
        driver = switch(selector) {
            case CHROME -> new ChromeDriver((ChromeOptions) options);
            case FIREFOX -> new FirefoxDriver();
            case EDGE -> new EdgeDriver((EdgeOptions) options);
            case IE -> new InternetExplorerDriver();
            case SAFARI -> new SafariDriver();
            case OPERA -> new OperaDriver();
        };
    }

    private void setImplicitWait(int waitSeconds) {
        driver.manage().timeouts().implicitlyWait(waitSeconds, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(waitSeconds, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(waitSeconds, TimeUnit.SECONDS);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setPath(String driverPath) {
        System.setProperty(switch (selector) {
            case CHROME -> "webdriver.chrome.driver";
            case FIREFOX -> "webdriver.gecko.driver";
            case EDGE -> "webdriver.edge.driver";
            case IE -> "webdriver.ie.driver";
            default -> "";
        }, driverPath);
    }

    public void get(String url) {
        driver.get(url);
    }

    public void wait(int miliseconds) {
        try { if(miliseconds > 0) Thread.sleep(miliseconds); }
        catch (InterruptedException e) { e.printStackTrace(); }
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
        return executeScript(getElement(locator), script);
    }
    public Object executeScript(WebElement element, String script) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(script, element);
    }

    public WebElement highlight(By locator, int miliseconds) {
        return highlight(getElement(locator), miliseconds);
    }
    public WebElement highlight(WebElement element, int miliseconds) {
        if(element != null) {
            String cssBorder = element.getCssValue("border");
            setStyle(element, "border", "2px solid red");
            wait(miliseconds);
            setStyle(element, "border", cssBorder);
        }
        return element;
    }

    public void setAttribute(By locator, String attribute, String value) {
        setAttribute(getElement(locator), attribute, value);
    }
    public void setAttribute(WebElement element, String attribute, String value) {
        if(element != null) {
            String script = "arguments[0].setAttribute('" + attribute + "', '" + value + "')";
            executeScript(element, script);
        }
    }

    public void setStyle(By locator, String property, String value) {
        setStyle(getElement(locator), property, value);
    }
    public void setStyle(WebElement element, String property, String value) {
        if(element != null) {
            String script = "arguments[0].style." + property + " = '" + value + "'";
            executeScript(element, script);
        }
    }

    public WebElement getElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            PageTests.printMessage(LogStatus.INFO, "Element Found : " + locator);
            return element;
        } catch(NoSuchElementException e) {
            PageTests.printMessage(LogStatus.WARNING, "Element not Found : " + locator);
        }
        return null;
    }

    public List<WebElement> getElements(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        if(elements.size() > 0) {
            PageTests.printMessage(LogStatus.INFO, "Elements Found : " + locator);
        } else {
            PageTests.printMessage(LogStatus.WARNING, "Elements not Found : " + locator);
        }
        return elements;
    }

    public Select getSelect(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return getSelect(element, locator);
        } catch(NoSuchElementException e) {
            PageTests.printMessage(LogStatus.WARNING, "Element not Found : " + locator);
        }
        return null;
    }
    public Select getSelect(WebElement element, By locator) {
        try {
            if(element != null) {
                Select select = new Select(element);
                PageTests.printMessage(LogStatus.INFO, "Select Found : " + locator);
                return select;
            } else {
                PageTests.printMessage(LogStatus.WARNING, "Select not Found : " + locator);
            }
        } catch(UnexpectedTagNameException e) {
            PageTests.printMessage(LogStatus.WARNING, "Element is no a Select : " + locator);
        }
        return null;
    }

    public void inputText(By locator, int miliseconds, String value) {
        WebElement element = getElement(locator);
        if(isActive(element)) {
            PageTests.printMessage(LogStatus.INFO, "Input Text : " + locator + " | '" + value + "'");
            highlight(element, miliseconds).sendKeys(value);
        } else {
            PageTests.printMessage(LogStatus.WARNING, "Couldn't be Input Text : " + locator + " | '" + value + "'");
        }
    }

    public void click(By locator, int miliseconds) {
        WebElement element = getElement(locator);
        if(isActive(element)) {
            PageTests.printMessage(LogStatus.INFO, "Click : " + locator);
            highlight(element, miliseconds).click();
        } else {
            PageTests.printMessage(LogStatus.WARNING, "Couldn't be Clicked : " + locator);
        }
    }

    public void selectByValue(By locator, int miliseconds, String value) {
        WebElement element = getElement(locator);
        if(isActive(element)) {
            PageTests.printMessage(LogStatus.INFO, "Select by Value : " + locator + " | '" + value + "'");
            highlight(element, miliseconds);
            getSelect(element, locator).selectByValue(value);
        } else {
            PageTests.printMessage(LogStatus.WARNING, "Couldn't be selected by Value : " + locator + " | '" + value + "'");
        }
    }

    public void selectByVisibleText(By locator, int miliseconds, String value) {
        WebElement element = getElement(locator);
        if(isActive(element)) {
            PageTests.printMessage(LogStatus.INFO, "Select by Visible Text : " + locator + " | '" + value + "'");
            highlight(element, miliseconds);
            getSelect(element, locator).selectByVisibleText(value);
        } else {
            PageTests.printMessage(LogStatus.WARNING, "Couldn't be selected by Visible Text : " + locator + " | '" + value + "'");
        }
    }

    public void logMessage(LogStatus status, String message) {
        System.out.println(status + " : " + message);
    }

    public boolean isActive(By locator) {
        return isActive(getElement(locator));
    }
    public boolean isActive(WebElement element) {
        if(element != null) return element.isEnabled();
        return false;
    }

    public void takeSnapShot(String folderPath, String filePath) {
        File dstFile = Path.of(folderPath, filePath).toFile();
        try {
            TakesScreenshot scrShot = (TakesScreenshot) driver;
            File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, dstFile);
            PageTests.printMessage(LogStatus.INFO, "Snapshot taken : '" + dstFile + "'");
        } catch(IOException e) {
            PageTests.printMessage(LogStatus.WARNING, "Couldn't be taken the snapshoot : '" + dstFile + "'");
        }
    }

    public void close() {
        driver.close();
    }
}