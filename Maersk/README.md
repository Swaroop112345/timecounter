# timecounter


CONTENTS OF THIS FILE
---------------------

 * Introduction
 * Requirements
 * Installation
 * Platform Specification
 * Test Coverage
 * Configuration
 * Run the suite
 * Reporting and Logs
 * Highlights
 * Scalability
 * Improvements 
 * Maintainance
 * Dependencies


INTRODUCTION
------------

The timecounter testing framework is designed to test a website https://e.ggtimer.com/ .
The above website performs a countdown task (i.e it counts down time in descending order).
This will validate the logic of UI is actually working or not . This project can be enhanced to test any website which follows this logic. 

The framework followed in this project is very generic and loosely coupled . Hence this is very easy to maintain . It follows below design patterns
 
 * Single Responsibility Principle
 * Factory Design Patten
 * Page Object Model


REQUIREMENTS
------------

This test project requires no modules outside of timecounter project.


INSTALLATION
------------

  Pre-requisties
  1. Requires Java 1.8(Since java 8 is used) and above.
  2. Maven should be installed in your OS. 
  3. Clone your code from https://github.com/Swaroop112345/timecounter

PLATFORM SPECIFICATION
----------------------
  
  Supported: chrome,firefox,safari,edge,headless
  Unsupported: mobile versions
  

TEST COVERAGE
-------------

  Current automation suite validates the Home Page, Custom Input (when user manually enters the time)
  and Predefined selections (5 minutes,10 minutes,15 minutes)


CONFIGURATION
-------------

 1. Feature File Location

This suite is developed on a BDD approach . So the driving part are the feature files which are placed under /src/test/resources/parallel. User can add the test data in the feature file to have more coverage.

 2. Variables

The variable section is driven from pom.xml. Hence please use below variables in case you need to change

       <systemPropertyVariables>
                <browser>chrome</browser>
                <url>https://e.ggtimer.com/</url>
        </systemPropertyVariables>

  browser : can be any options of the supported format . Ensure to put the proper name as mentioned in format
  eg : if we need to specify firefox it can be either firefox or FIREFOX or FireFOX but it should not be fire.

 3. Cucumber Tags

  Default tag is @Assignment. User can change to any other tags available during run time @HomePage @Regression

  <argLine>-Dcucumber.filter.tags="@Assignment"</argLine>



RUN THE SUITE
-------------

  
  1. Default : **mvn clean verify** 
  
   It will launch chrome browser and execute the task asked @Assignment

  2. Custom: **mvn -Dbrowser="firefox" -Dcucumber.filter.tags="'@Assignment'" clean verify**
 
   User can change during run time

  3. If URL gets modified and need to be changed on run time then it can be passed through as -Durl="https://e.ggtimer.com/modified"


REPORTING AND LOGS
-------------------

  1. Reports : Path - test-output/
     Extent-Report : $Path/SparkReport
     Allure-Report : $Path/allure-results - Note this is not tested yet . However it is configured . user can use Jenkins plugin to install and fetch allure Jsons
     MultiThread-Report: test-output-thread/index.html
     Cucumber Reports: Only JSONs and html are generated under target/cucumber-reports. In order to get them please run - mvn run verify -DskipTests post execution

  2. Logs : All the application logs are captured under /logs/ 
     factory-info.log - For factory package logs
     page-info.log - For pages package logs
     utility-info.log - For utility package logs

HIGHLIGHTS
-----------

  1. Generic framework and loosly coupled. Developed based upon OOPs.
  2. Use of Java 8. Java doc created .
  3. Thread Safe and Synchronized which supports parallel execution.
  4. Parameterized variables which makes user friendly and maintainable.
  5. Good reporting . Almost support all available formats . Snapshot capture. 



SCALABILITY
-----------

Currently unable to test and configure due to system limitations. It can be added with docker-compose.yml file which can be downloaded from below link.

https://github.com/SeleniumHQ/docker-selenium/blob/trunk/docker-compose-v3.yml

Also zalenium configuration can be used which has kubernetes support

Below code changes will be required while implementing this 

In factory driver class - driver need to be initialized with remote web driver using Chrome Options
and pointing the URI to docker hub 

IMPROVEMENTS
------------
 1. Scalability
 2. More Test coverage.
 3. Test Validation logic. As currently selenium is not able to track frequent DOM changes which are happening rapidly. Even if with WebDriver IO and JS it is failing to capture. So the logic used here to give more interval and validate results as per 70% pass occurrence. 
 4. Assignment tag is currently supporting one variable that is 25 which can be enhanced to added several data using map concept.


MAINTAINANCE
------------
 Easily Maintainable. For every page test we have a page business logic
  
  1. Business Logic : src/main/java
  2. Test Logic : src/test/java
  3. Features : src/test/resources
  4. Report Config files : src/test/resources/config/extent-config.xml, src/test/resources/extent.properties, src/test/resources/cucumber.properties , src/test/resources/allure.properties


Dependencies
------------

  Detailed can be fetched from pom.xml

    <!--Maven Plugin -->
    <mavensurefire.version>2.22.0</mavensurefire.version>

    <!--Selenium Dependencies -->
    <selenium.version>4.0.0-rc-1</selenium.version>
    <drivermanager.version>5.0.3</drivermanager.version>

    <!--Logger Dependency -->
    <logger.version>2.14.1</logger.version>

    <!--Cucumber & Reporting Dependencies -->
    <cucumber.version>6.11.0</cucumber.version>
    <extentreport.version>2.8.4</extentreport.version>
    <reporting.version>5.5.4</reporting.version>
    <allurereport.version>2.14.0</allurereport.version>
    <aspectj.version>1.9.7</aspectj.version>

    <!--Framework Dependencies -->
    <cucumbertestng.version>6.10.4</cucumbertestng.version>
    <cucumberjunit.version>6.11.0</cucumberjunit.version>
    <junit.version>4.11</junit.version>
    <testNg.version>7.3.0</testNg.version>
