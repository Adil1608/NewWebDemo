package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class baseClass {
    public WebDriver driver;

    public baseClass() {
        getdriver.initalize(Constants.BROWSER);
        init();
    }

    public void init() {
        this.driver = getdriver.driver;
    }

    /***
     * Builds the By type with given locator strategy
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @return Returns By Type
     */
    public By getByType(String locator) {
        By by = null;
        String locatorType = locator.split("=>")[0];
        locator = locator.split("=>")[1];
        try {
            if (locatorType.contains("id")) {
                by = By.id(locator);
            } else if (locatorType.contains("name")) {
                by = By.name(locator);
            } else if (locatorType.contains("xpath")) {
                by = By.xpath(locator);
            } else if (locatorType.contains("css")) {
                by = By.cssSelector(locator);
            } else if (locatorType.contains("class")) {
                by = By.className(locator);
            } else if (locatorType.contains("tag")) {
                by = By.tagName(locator);
            } else if (locatorType.contains("link")) {
                by = By.linkText(locator);
            } else if (locatorType.contains("partiallink")) {
                by = By.partialLinkText(locator);
            } else {
                System.out.println("Locator type not supported");
            }
        } catch (Exception e) {
            System.out.println("By type not found with: " + locatorType);
        }
        return by;
    }

    /**
     * Builds The WebElement By given locator strategy
     *
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *                tag=>example, xpath=>//example, link=>example
     * @return WebElement
     */
    public WebElement getElement(String locator) {
        WebElement element = null;
        By byType = getByType(locator);
        try {
            element = driver.findElement(byType);
        } catch (Exception e) {
            System.out.println("Element not found with: " + locator);
            e.printStackTrace();
        }
        return element;
    }

    /***
     * Get simple date time as string
     * @return date time as string type
     */
    public static String getCurrentDateTime() {
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "MM/dd/yyyy HH:mm:ss");
        String date = formatter.format(currentDate.getTime()).replace("/", "_");
        date = date.replace(":", "_");
        System.out.println("Date and Time :: " + date);
        return date;
    }

    /***
     *
     * @param methodName
     * @param browserName
     * @return
     */
    public static String getScreenshotName(String methodName, String browserName) {
        String localDateTime = getCurrentDateTime();
        StringBuilder name = new StringBuilder().append(browserName)
                .append("_")
                .append(methodName)
                .append("_")
                .append(localDateTime)
                .append(".png");
        return name.toString();
    }


    /***
     *
     * @param methodName
     * @param browserName
     * @return
     */
    public String takeScreenshot(String methodName, String browserName) {
        String fileName = getScreenshotName(methodName, browserName);
        String screenshotDir = System.getProperty("user.dir") + "//" + "test-output/screenshots";
        new File(screenshotDir).mkdirs();
        String path = screenshotDir + "//" + fileName;

        try {
            File screenshot = ((TakesScreenshot) driver).
                    getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));
            System.out.println("Screen Shot Was Stored at: " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    /***
     * Send Keys to element
     * @param element - Element to send data
     * @param data - Data to send
     * @param clear - True if you want to clear the field before sending data
     */
    public void sendData(WebElement element, String data, Boolean clear) {
        try {
            if (clear) {
                element.clear();
            }
            element.sendKeys(data);
            System.out.println("Send Keys on element with data :: " + data);
        } catch (Exception e) {
            System.out.println("Send Keys on element with data :: " + data);
        }
    }

    /***
     * Send Keys to element with locator and clear
     * @param locator - locator
     * @param data - Data to send
     */
    public void sendData(String locator, String data) {
        WebElement element = getElement(locator);
        sendData(element, data, true);
    }

    public void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickOnElement(String locator) {
        WebElement element = getElement(locator);
        clickElement(element);
    }

    /***
     * Sleep for specified number of milliseconds
     * @param msec
     * @param info
     */
    public static void sleep(long msec, String info) {
        if (info != null) {
            System.out.println("Waiting " + (msec * .001) + " seconds :: " + info);
        }
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /***
     * Sleep for specified number of milliseconds
     * @param msec
     */
    public static void sleep(long msec) {
        sleep(msec, null);
    }

    public WebElement waitForElement(String locator, int timeout) {
        By byType = getByType(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
            System.out.println("Waiting for max:: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(byType));
            System.out.println("Element appeared on the screen");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        } catch (Exception e) {
            System.out.println("Element not appeared on the screen");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        }
        return element;
    }

    public WebElement waitForTextToBeVisible(String text, int timeout) {
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@text='" + text + "']")));
        } catch (Exception e) {
            System.out.println("Text not appeared on the screen");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        }
        return element;
    }

    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
