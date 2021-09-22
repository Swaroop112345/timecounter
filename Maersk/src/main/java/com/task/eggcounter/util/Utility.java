package com.task.eggcounter.util;

import com.task.eggcounter.exceptions.CustomExceptions;
import com.task.eggcounter.pages.CustomerTimerPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swarooppattanaik
 * @project timecounter
 */
public class Utility {

    private static Logger logger = LogManager.getLogger(Utility.class);

    static List<String> timer = new ArrayList<>();
    List<Integer> newTimerList = new ArrayList<>();
    public static String firstTitle;

    //Common OR for utility class
    private By startButton = By.cssSelector(".validTime");
    private By countDownPageTimer = By.xpath("//div[@class='EggTimer']/main/div/div/div[2]/p");

    static public boolean isAlertPresent(WebDriver driver) {
        try
        {
            if(driver!=null){
                Alert alert = driver.switchTo().alert();
                System.out.println("Switched to Alert");
                alert.accept();}
              logger.info(" Alert Present");
                return true;
        }
        catch (NoAlertPresentException e)
        {
            logger.info("No Alert Present");
             return false;
        }
    }

    static public List<Integer> parseTimeStamp(List<String> timer,String regEx){
        List<Integer> list = new ArrayList<>();
        for (String s : timer) {
            String[] tmpStr = s.split(regEx);
            int timeInSeconds = ((Integer.parseInt(tmpStr[0]) * 3600)) +
                    ((Integer.parseInt(tmpStr[1]) * 60)) +
                    ((Integer.parseInt(tmpStr[2])));
            list.add(timeInSeconds);
        }
        return list;
    }

    public String customPageTitle(){
        return firstTitle;
    }

    public List<Integer> counterLogic(WebDriver driver, By webElement,String inputTime,int countDownTime,String custom){
        logger.info("Inside counter logic");
        logger.info("WebElement " + webElement);
        logger.info("Driver " + driver);
        new WebDriverWait(driver,5).until((WebDriver d) -> d.findElement(webElement));
        if(custom.equalsIgnoreCase("custom")){
            logger.info("About to click Custom");
        driver.findElement(webElement).sendKeys(inputTime);
        driver.findElement(startButton).click(); }
        else
        {
            logger.info("About to click Predefined");
            driver.findElement(webElement).click();
        }
        new WebDriverWait(driver,5).until((WebDriver d) -> d.findElement(countDownPageTimer));
        firstTitle = driver.getTitle();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String before = null;
        while (true) {
            Long statTime = System.currentTimeMillis();
            isAlertPresent(driver);
            before = (String) (js.executeScript("return document.title"));
            logger.info("Current Time " + before);
            if(before.contains("expired")){
                System.out.println("Skipping");
                isAlertPresent(driver);
                break;
            }
            else{timer.add(before);}
            Long endTime = System.currentTimeMillis();
            logger.info("Difference in Millis " + (endTime-statTime));
            try {
                Thread.sleep((2000 * countDownTime) - (endTime - statTime));
            } catch (InterruptedException iex) {
                try {
                    throw new CustomExceptions("Thread is not handled properly");
                } catch (CustomExceptions e) {
                    e.printStackTrace();
                }
                iex.printStackTrace();
            }
        }
        System.out.println(timer);
        newTimerList = parseTimeStamp(timer,":");
        System.out.println(newTimerList);
        return newTimerList;
    }

}
