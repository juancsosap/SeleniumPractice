Feature: Google Home Page Tests

  Background: Open Home Page
    Given Web Browser is Open for test "Google Home Page Tests"
    And Google Home Page is Launch

  Scenario: Test Elements on Home Page
    Given Search input and button elements are present
    Then Close the browser

  Scenario Outline: Test Searching on Home Page
    Given The value <value> is typed and searched
    Then Result page is shown
    And Close the browser

    Examples:
    | value    |
    | testing  |
    | cucumber |
