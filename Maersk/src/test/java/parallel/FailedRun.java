package parallel;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * @author swarooppattanaik
 * @project timecounter
 */


@CucumberOptions(
        features = {"@target/failedrerun.txt"},
        glue = {"parallel"},
        plugin = {"pretty",
                "rerun:target/failedrerun.txt"},

        monochrome = true
)
public class FailedRun extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
