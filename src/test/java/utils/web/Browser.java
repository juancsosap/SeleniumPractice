package utils.web;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.reports.LogStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import utils.reports.Reporter;

import java.util.concurrent.TimeUnit;

public class Browser {
    protected WebDriver driver;
    protected BrowserSelector selector;
    protected Object options;
    public Reporter reporter;

    public Browser(BrowserSelector selector, String driverPath, Reporter reporter, int waitSeconds, boolean privateMode) {
        this.selector = selector;
        this.reporter = reporter;
        setPath(driverPath);
        setOptions(privateMode);
        setDriver();
        setImplicitWait(waitSeconds);

        driver.manage().window().maximize();
    }
    public Browser(BrowserSelector selector, Reporter reporter, String driverPath) {
        this(selector, driverPath, reporter, 10, false);
    }
    public Browser(BrowserSelector selector, String driverPath, Reporter reporter, int waitSeconds) {
        this(selector, driverPath, reporter, waitSeconds, false);
    }

    public void setOptions(boolean privateMode) {
        switch (selector) {
            case CHROME -> {
                options = new ChromeOptions();
                if (privateMode) {
                    ((ChromeOptions) options).addArguments("--incognito");
                }
            }
            case FIREFOX -> {
                options = new FirefoxOptions();
                if (privateMode) {
                    ((FirefoxOptions) options).addArguments("-private");
                }
            }
            case EDGE -> {
                options = new EdgeOptions();
                if (privateMode) {
                    ((EdgeOptions) options).setCapability("InPrivate", true);
                }
            }
            case IE -> {
                options = new InternetExplorerOptions();
                if (privateMode) {
                    ((InternetExplorerOptions) options).setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
                    ((InternetExplorerOptions) options).addCommandSwitches("-private");
                }
            }
            case OPERA -> {
                options = new OperaOptions();
                if (privateMode) {
                    DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
                    ((OperaOptions) options).addArguments("private");
                    capabilities.setCapability(OperaOptions.CAPABILITY, options);
                }
            }
        }
    }

    public void setDriver() {
        logMessage(LogStatus.INFO, "Opening " + selector + " Browser");
        driver = switch(selector) {
            case CHROME -> new ChromeDriver((ChromeOptions) options);
            case FIREFOX -> new FirefoxDriver((FirefoxOptions) options);
            case EDGE -> new EdgeDriver((EdgeOptions) options);
            case IE -> new InternetExplorerDriver((InternetExplorerOptions) options);
            case SAFARI -> new SafariDriver();
            case OPERA -> new OperaDriver((OperaOptions) options);
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
            if(attribute != null && value != null) {
                String script = "arguments[0].setAttribute('" + attribute + "', '" + value + "')";
                executeScript(element, script);
            }
        }
    }

    public void setStyle(By locator, String property, String value) {
        setStyle(getElement(locator), property, value);
    }
    public void setStyle(WebElement element, String property, String value) {
        if(element != null) {
            if(property != null && value != null) {
                String script = "arguments[0].style." + property + " = '" + value + "'";
                executeScript(element, script);
            }
        }
    }

    public WebElement getElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            String message = "Element Found : " + locator;
            reporter.log(LogStatus.INFO, message);
            return element;
        } catch(NoSuchElementException e) {
            String error = "Element not Found : " + locator;
            reporter.log(LogStatus.WARNING, error);
        }
        return null;
    }

    public List<WebElement> getElements(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        if(elements.size() > 0) {
            String message = "Elements Found : " + locator;
            reporter.log(LogStatus.INFO, message);
        } else {
            String error = "Elements not Found : " + locator;
            reporter.log(LogStatus.WARNING, error);
        }
        return elements;
    }

    public Select getSelect(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return getSelect(element, locator);
        } catch(NoSuchElementException e) {
            String error = "Element not Found : " + locator;
            reporter.log(LogStatus.WARNING, error);
        }
        return null;
    }
    public Select getSelect(WebElement element, By locator) {
        try {
            if(element != null) {
                Select select = new Select(element);
                String message = "Select Found : " + locator;
                reporter.log(LogStatus.INFO, message);
                return select;
            } else {
                String error = "Select not Found : " + locator;
                reporter.log(LogStatus.WARNING, error);
            }
        } catch(UnexpectedTagNameException e) {
            String error = "Element is no a Select : " + locator;
            reporter.log(LogStatus.WARNING, error);
        }
        return null;
    }

    public void inputText(By locator, String value, int miliseconds) {
        WebElement element = getElement(locator);
        if(isActive(element)) {
            if(value != null) {
                String message = "Input Text : " + locator + " | Value ('" + value + "')";
                reporter.log(LogStatus.INFO, message);
                highlight(element, miliseconds).sendKeys(value);
            }
        } else {
            String error = "Couldn't be Input Text : " + locator + " | '" + value + "'";
            reporter.log(LogStatus.WARNING, error);
        }
    }

    public void click(By locator, int miliseconds) {
        WebElement element = getElement(locator);
        if(isActive(element)) {
            String message = "Click : " + locator;
            reporter.log(LogStatus.INFO, message);
            highlight(element, miliseconds).click();
        } else {
            String error = "Couldn't be Clicked : " + locator;
            reporter.log(LogStatus.WARNING, error);
        }
    }

    public void selectByValue(By locator, String value, int miliseconds) {
        WebElement element = getElement(locator);
        if(isActive(element)) {
            if(value != null) {
                String message = "Select by Value : " + locator + " | '" + value + "'";
                reporter.log(LogStatus.INFO, message);
                highlight(element, miliseconds);
                getSelect(element, locator).selectByValue(value);
            }
        } else {
            String error = "Couldn't be selected by Value : " + locator + " | '" + value + "'";
            reporter.log(LogStatus.WARNING, error);
        }
    }

    public void selectByVisibleText(By locator, String value, int miliseconds) {
        WebElement element = getElement(locator);
        if(isActive(element)) {
            if(value != null) {
                String message = "Select by Visible Text : " + locator + " | '" + value + "'";
                reporter.log(LogStatus.INFO, message);
                highlight(element, miliseconds);
                getSelect(element, locator).selectByVisibleText(value);
            }
        } else {
            String error = "Couldn't be selected by Visible Text : " + locator + " | '" + value + "'";
            reporter.log(LogStatus.WARNING, error);
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
            String message = "Snapshot taken : '" + dstFile + "'";
            reporter.log(LogStatus.INFO, message);
        } catch(IOException e) {
            String error = "Couldn't be taken the snapshoot : '" + dstFile + "'";
            reporter.log(LogStatus.WARNING, error);
        }
    }

    public void close() {
        driver.close();
    }
}