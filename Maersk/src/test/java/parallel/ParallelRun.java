package parallel;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * @author swarooppattanaik
 * @project timecounter
 */



@CucumberOptions(
        features = {"src/test/resources/parallel/"},
        glue = {"parallel"},
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
                "rerun:target/failedrerun.txt",
                "timeline:test-output-thread/"},

        monochrome = true,
        tags="",
        dryRun = false
)

public class ParallelRun extends AbstractTestNGCucumberTests{

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
