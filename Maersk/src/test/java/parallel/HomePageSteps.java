package parallel;

import com.task.eggcounter.factory.DriverFactory;
import com.task.eggcounter.pages.HomePage;
import com.task.eggcounter.util.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

/**
 * @author swarooppattanaik
 * @project timecounter
 */
public class HomePageSteps {

    private HomePage homePage = new HomePage(DriverFactory.getDriver());
    SoftAssert softAssert = new SoftAssert();

    private static String title;

    @Given("user is on home page")
    public void user_is_on_home_page() {
        DriverFactory.getDriver().get(Constants.URL);
    }
    @When("user gets the title of the page")
    public void user_gets_the_title_of_the_page() {
        title = homePage.getHomePageTitle();
        System.out.println("Login Page Title is " + title);
    }
    @Then("page title should be {string}")
    public void page_title_should_be(String expectedTitle) {
        softAssert.assertTrue(title.contains(expectedTitle));
    }

    @Then("twitter page {string} should be opened on clicking contact")
    public void twitter_page_should_be_opened_on_clicking_contact(String contactUsUrl) {
        softAssert.assertTrue(homePage.getContactPageUrl().contains(contactUsUrl));
    }

    @Then("egg timer logo should be present")
    public void egg_timer_logo_should_be_present() {
        softAssert.assertTrue(homePage.isLogoPresent()==1);
    }

    @Then("total number of links should be {int}")
    public void total_number_of_links_should_be(Integer totalLinks) {
        softAssert.assertTrue(homePage.totalLinks()==totalLinks);
        softAssert.assertAll();
    }

}
