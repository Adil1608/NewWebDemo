package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Driver {

    public static WebDriver driver;

    public Driver() {
    }

    public static void killDriver() {
        driver.close();
        driver = null;
    }

    public static void initalize(String browser) {
        String driverPath = "";
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        System.out.println("OS Name from system property :: " + os);
        String directory = Constants.USER_DIRECTORY + Constants.DRIVERS_DIRECTORY;
        String driverKey = "";
        String driverValue = "";

        if (browser.equalsIgnoreCase("firefox")) {
            driverKey = Constants.GECKO_DRIVER_KEY;
            driverValue = Constants.GECKO_DRIVER_VALUE;
        } else if (browser.equalsIgnoreCase("chrome")) {
            driverKey = Constants.CHROME_DRIVER_KEY;
            driverValue = Constants.CHROME_DRIVER_VALUE;
        } else if (browser.equalsIgnoreCase("ie")) {
            driverKey = Constants.IE_DRIVER_KEY;
            driverValue = Constants.IE_DRIVER_VALUE;
        } else {
            System.out.println("Browser type not supported");
        }

        if (driver != null) {
            return;
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
//        System.setProperty(Constants.CHROME_DRIVER_KEY,"//Users//adilraza//AutomationProjects//NewWebDemo//drivers//chromedriver");
        driverPath = directory + driverValue + (os.equals("win") ? ".exe" : "");
        System.out.println("Driver Binary :: " + driverPath);
        System.setProperty(driverKey, driverPath);
        driver = new ChromeDriver(options);
//        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
