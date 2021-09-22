package testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author swarooppattanaik
 * @project timecounter
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/CustomTimer.feature"},
        glue = {"stepdefinitions","apphooks"},
        plugin = {"pretty",
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
            "timeline:test-output-thread"},
        tags="@All",
        dryRun = false
)


public class MyTestRunner {

}
