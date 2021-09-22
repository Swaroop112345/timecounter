package parallel;

import com.task.eggcounter.factory.DriverFactory;
import com.task.eggcounter.pages.CustomerTimerPage;
import com.task.eggcounter.util.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author swarooppattanaik
 * @project timecounter
 */
public class CustomTimerPageSteps {

    private CustomerTimerPage customerTimerPage = new CustomerTimerPage(DriverFactory.getDriver());
    SoftAssert softAssert = new SoftAssert();
    static Map<Integer, List<Integer>> map = new HashMap<>();
    static Map<Integer, String> firstTimeStamp = new HashMap<>();

    @Given("user is already on home page")
    public void user_is_already_on_home_page() {
        DriverFactory.getDriver().get(Constants.URL);
    }
    @When("user enters the custom time {string} and click on start button in order to launch {string} validation page")
    public void user_enters_the_custom_time_and_click_on_start_button_in_order_to_launch_validation_page(String inputTime, String countDownTime){
        customerTimerPage.counterValidation(inputTime,Integer.parseInt(countDownTime.trim()));
    }
    @Then("custom time page will be opened and it should start with {string}")
    public void custom_time_page_will_be_opened_and_it_should_start_with(String inputTime) {
        softAssert.assertTrue(customerTimerPage.getCustomPageTitle().contains(inputTime.trim()));
    }
    @Then("user validates that countdown should happen in every {string} second")
    public void user_validates_that_countdown_should_happen_in_every_second(String countDownTime) {
        softAssert.assertTrue(customerTimerPage.validateWebPageCountDown(Integer.parseInt(countDownTime.trim())));
    }

    @Then("remaining time should decrease with {string} second increment")
    public void remaining_time_should_decrease_with_second_increment(String countDownTime) {
        softAssert.assertTrue(customerTimerPage.validateLogicCountDown(Integer.parseInt(countDownTime.trim())));
        softAssert.assertAll();
    }


    @When("user clicks on {string} minutes button")
    public void user_clicks_on_minutes_button(String preDefinedMinutes) {
        List<Integer> preDefinedList = customerTimerPage.preDefinedTimerList(preDefinedMinutes,1);
        String firstTitle = customerTimerPage.getCustomPageTitle();
        map.put(Integer.parseInt(preDefinedMinutes.trim()),preDefinedList);
        firstTimeStamp.put(Integer.parseInt(preDefinedMinutes.trim()),firstTitle);
    }
    @Then("predefined time page will be opened and it should contain {string} minutes")
    public void predefined_time_page_will_be_opened_and_it_should_contain_minutes(String preDefinedMinutes) {
        Iterator itr = firstTimeStamp.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry pair = (Map.Entry)itr.next();
            softAssert.assertTrue(pair.getValue().toString().trim().contains(preDefinedMinutes));
        }
    }

    @Then("validates that predefined count down should happen in every {int} second")
    public void validates_that_predefined_count_down_should_happen_in_every_second(Integer number) {
        Iterator itr = map.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry pair = (Map.Entry)itr.next();
            softAssert.assertTrue(customerTimerPage.validateLogicCountDown((List<Integer>) pair.getValue(),number));
            softAssert.assertAll();
        }
    }


}
