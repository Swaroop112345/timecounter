Feature: Home Page Validation


  @HomePage @Regression
  Scenario: Home Page Title
    Given user is on home page
    When user gets the title of the page
    Then page title should be "e.ggtimer"

  @HomePage @Regression
  Scenario: Contact Link
    Given user is on home page
    Then twitter page "https://twitter.com/edotggtimer" should be opened on clicking contact

  @HomePage @Regression
  Scenario: Logo Image Verification
    Given user is on home page
    Then egg timer logo should be present

  @HomePage @Regression
  Scenario: Total Number of Links Verification
    Given user is on home page
    Then total number of links should be 7

