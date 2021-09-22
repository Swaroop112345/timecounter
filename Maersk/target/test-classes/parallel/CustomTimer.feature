Feature: Custom Timer Feature

    @Assignment
    Scenario Outline: Custom CountDown Timer Validation
      Given user is already on home page
      When user enters the custom time "<input_time>" and click on start button in order to launch "<countdown_time>" validation page
      Then custom time page will be opened and it should start with "<input_time>"
      Then user validates that countdown should happen in every "<countdown_time>" second
      And remaining time should decrease with "<countdown_time>" second increment

      Examples:
      | input_time | countdown_time |
      | 25         |   1            |

  @Regression
  Scenario Outline: Predefined CountDown Timer 5 minutes validation
    Given user is already on home page
    When user clicks on "<predefined_time>" minutes button
    Then predefined time page will be opened and it should contain "<predefined_time>" minutes
    Then validates that predefined count down should happen in every 1 second

    Examples:
      |predefined_time |
      | 5              |
