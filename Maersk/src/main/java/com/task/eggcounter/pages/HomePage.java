package com.task.eggcounter.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author swarooppattanaik
 * @project timecounter
 */
public class HomePage {

    private static Logger logger = LogManager.getLogger(HomePage.class);

    private WebDriver driver;

    //Locators - OR (Object Repositories)
    private By contactLink = By.cssSelector("a[title='@edotggtimer on twitter']");
    private By logo = By.xpath("//*[name()='path' and contains(@d,'M202.868 1')]");
    private By linkCount = By.tagName("a");


    //Constructor of page class
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    //Page action (features)

    public String getHomePageTitle(){
        return driver.getTitle();
    }

    public String getContactPageUrl(){
        String winHandleBefore = driver.getWindowHandle();
        driver.findElement(contactLink).click();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        String contactPageUrl = driver.getCurrentUrl();
        logger.info("Contact Page URL " + contactPageUrl);
        driver.close();
        driver.switchTo().window(winHandleBefore);
        return contactPageUrl;
    }

    public int isLogoPresent(){
        //return (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete " + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", logo);
        return driver.findElements(logo).size();
    }

    public int totalLinks(){
        logger.info("Total Links " + driver.findElements(linkCount).size());
        return driver.findElements(linkCount).size();
    }


}
