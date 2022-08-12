package runnerclass;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "/Users/adilraza/AutomationProjects/NewWebDemo/src/test/Features", tags = "@TC001",
        glue = {"stepdefinations","utils"},
        plugin = {"pretty",
                "html:target/cucumber-html-report.html",
                "json:target/cucumber.json",
                "rerun:target/failedrerun.txt", //Creates a text file with failed scenarios
                "json:target/cucumber.json"}

)
public class TestRunner {
}
