package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class Hooks extends baseClass {

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                File srcFile = ((TakesScreenshot) driver).
                        getScreenshotAs(OutputType.FILE);
                String filename = scenario.getName();
                String timestamp = timestamp();
                File targetFile = new File(System.getProperty("user.dir") + "/screenshots/" + filename + timestamp +".jpg");
                FileUtils.copyFile(srcFile, targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("This will run before SS");
        }
        //        System.out.println(driver.queryAppState("com.alefeducation.arabits.dev"));
        driver.quit();
    }
}
