package com.task.eggcounter.pages;

import com.task.eggcounter.factory.DriverFactory;
import com.task.eggcounter.util.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;

/**
 * @author swarooppattanaik
 * @project timecounter
 */
public class CustomerTimerPage {

    private static Logger logger = LogManager.getLogger(CustomerTimerPage.class);

    private WebDriver driver;
    private Utility utility ;

    //Locators - OR (Object Repositories)
    private By inputBox = By.cssSelector("#EggTimer-start-time-input-text");
    private By fiveMinutes = By.cssSelector("a[title='5 minutes']");
    private By tenMinutes = By.cssSelector("a[title='10 minutes']");
    private By fifteenMinutes = By.cssSelector("a[title='10 minutes']");

    //Varaibles for this class
    private static String firstTitle;
    static List<Integer> newTimerList = new ArrayList<>();

    //Constructor of page class
    public CustomerTimerPage(WebDriver driver){
        this.driver = driver;
    }


    //Page action (features)

    /**
     * This to get the very first timestamp of the flow
     * @return a string of hh:mm:ss format
     */
    public String getCustomPageTitle(){
        firstTitle = utility.customPageTitle();
        return firstTitle;
    }

    /**
     * This is to verify the countdown logic happening on UI
     * @param countDownNumber
     * @return boolean
     */
    public boolean validateWebPageCountDown(int countDownNumber){
        int count=0;
        int validateNumber = 0;
        if(newTimerList.size()>100){
            validateNumber = 30;
        }
        else
        {
            validateNumber=2;
        }
        for (int i = 0; i < newTimerList.size()-1; i++) {
            logger.info("validateWebPageCountDown" +(newTimerList.get(i) - newTimerList.get(i+1)));
            if (newTimerList.get(i) - newTimerList.get(i+1) == (2*countDownNumber)) {
                logger.info("Validated Successfully for " + newTimerList.get(i));
            } else {
                System.out.println("Validation failed for " + newTimerList.get(i));
                count++;
            }
        }
        logger.info("Value of count in validateLogicCountDown "+ count);
        if(count>validateNumber){
            return false;
        }
        else
            return true;
    }

    /**
     * This is to verify the actual countdown logic
     * @param countDownNumber
     * @return boolean
     */

    public boolean validateLogicCountDown(int countDownNumber){
        int count=0;
        int validateNumber = 0;
        if(newTimerList.size()>100){
            validateNumber = 30;
        }
        else
        {
            validateNumber=2;
        }
        for (int i = 0; i < newTimerList.size()-1; i++) {
            logger.info("Inside Division Class -->" + newTimerList);
            logger.info("About to evaluate "+ ((newTimerList.get(i) +"-"+ newTimerList.get(i+1))+"/"+"(2*"+countDownNumber+") == countDownNumber"));
            if ( ((newTimerList.get(i) - newTimerList.get(i+1))/(2*countDownNumber)) == countDownNumber) {
                logger.info("Validated Successfully for " + newTimerList.get(i));
            } else {
                System.out.println("Validation failed for " + newTimerList.get(i));
                count++;
            }
        }
        logger.info("Value of count in validateLogicCountDown "+ count);
        if(count>validateNumber){
            return false;
        }
        else
            return true;
    }

    /**
     * overloading functionality of validateLogicCountDown
     * @param newTimerList
     * @param countDownNumber
     * @return
     */
    public boolean validateLogicCountDown(List<Integer> newTimerList,int countDownNumber){
        int count=0;
        int validateNumber = 0;
        if(newTimerList.size()>100){
            validateNumber = 30;
        }
        else
        {
            validateNumber=2;
        }
        for (int i = 0; i < newTimerList.size()-1; i++) {
            if ( ((newTimerList.get(i) - newTimerList.get(i+1))/(2*countDownNumber)) == countDownNumber) {
                logger.info("Validated Successfully for " + newTimerList.get(i));
            } else {
                logger.error("Validation failed for " + newTimerList.get(i));
                count++;
            }
        }
        logger.info("Value of count in validateLogicCountDown "+ count);
        if(count>validateNumber){
            return false;
        }
        else
            return true;
    }

    /**
     * Heart of this counter functionality which actually validates and stores the time stamp for comparision
     * @param inputTime
     * @param countDownTime
     */
    public void counterValidation(String inputTime,int countDownTime){
        utility = new Utility();
        newTimerList = utility.counterLogic(driver,inputBox,inputTime,countDownTime,"custom");
    }

    /**
     *
     * @param inputTime
     * @param countDownTime
     * @return list of timestamps which will be used for verification in step class
     */
    public List<Integer> preDefinedTimerList(String inputTime,int countDownTime){
        utility = new Utility();
        if(Integer.parseInt(inputTime.trim())==5){
            newTimerList = utility.counterLogic(driver,fiveMinutes,inputTime,countDownTime,"predefined");
        }
        if(Integer.parseInt(inputTime)==10){
            newTimerList = utility.counterLogic(driver,tenMinutes,inputTime,countDownTime,"predefined");
        }
        if(Integer.parseInt(inputTime)==15){
            newTimerList = utility.counterLogic(driver,fifteenMinutes,inputTime,countDownTime,"predefined");
        }
        logger.info("preDefinedTimerList from preDefinedTimerList function" + newTimerList);
        return newTimerList;
    }

}
