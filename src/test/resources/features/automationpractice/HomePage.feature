Feature: Automation Practice Home Page Tests

  Background: Open Home Page
    Given Web Browser is Open for test "Automation Practice Home Page Tests"
    And Automation Practice Home Page is Launch

  Scenario: Test Elements on Home Page
    Given Search Product input and button elements are present
    And Signin button element is present
    Then Close the browser

  Scenario Outline: Test Searching on Home Page
    Given The value <value> is typed and searched a Product
    Then Result page is shown with the products
    And Close the browser

    Examples:
      | value    |
      | blouse   |
      | dress    |

  Scenario: Test Signin Button in Home Page
    Given Click the Signin button element
    Then Signin page is shown
    And Close the browser
