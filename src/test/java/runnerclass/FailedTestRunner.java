package runnerclass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"@target/failedrerun.txt"},
        glue = "stepDefinations",
        plugin = {"pretty",
                "rerun:target/failedrerun.txt"} //Creates a text file with failed scenarios

)

public class FailedTestRunner {
}
