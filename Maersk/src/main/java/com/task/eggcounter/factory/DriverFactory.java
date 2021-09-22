package com.task.eggcounter.factory;

import com.task.eggcounter.exceptions.CustomExceptions;
import com.task.eggcounter.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

/**
 * @author swarooppattanaik
 * @project timecounter
 */
public class DriverFactory {

    //Declare variables for Driver factory
    public WebDriver driver;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    private static Logger logger = LogManager.getLogger(DriverFactory.class);

    /**
     * Initializes thread safe browser(in order to have parallel execution) as per user choice
     * @param browser
     * @return will return a tlDriver
     */
    public WebDriver init_driver(String browser){

        System.out.println("Browser Type : " + browser);

        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver());
            logger.info("Chrome Driver set up successfully ");
        }

        else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            tlDriver.set(new FirefoxDriver());
            logger.info("Firefox Driver set up successfully ");
        }

        else if(browser.equalsIgnoreCase("safari")){
            WebDriverManager.safaridriver().setup();
            tlDriver.set(new SafariDriver());
            logger.info("Safari Driver set up successfully ");
        }

        else if(browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            tlDriver.set(new EdgeDriver());
            logger.info("Edge Driver set up successfully ");
        }

        else if(browser.equalsIgnoreCase("headless")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions option=new ChromeOptions();
            option.setHeadless(true);
            tlDriver.set(new ChromeDriver(option));
            logger.info("Chrome Headless Driver set up successfully ");
        }

        else
        {
            System.out.println("Please Enter correct browser " + browser);
            logger.error("User input is not correct");

            try {
                throw new CustomExceptions("Wrong browser input by user ");
            } catch (CustomExceptions e) {
                e.printStackTrace();
            }
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.PAGE_LOAD_TIMEOUT));
        return getDriver();
    }

    /**
     *this will return a thread local synchronized driver
     * @return
     */
    public static synchronized WebDriver getDriver(){
        return tlDriver.get();
    }

}
