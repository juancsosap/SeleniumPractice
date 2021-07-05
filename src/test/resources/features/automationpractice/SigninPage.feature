Feature: Automation Practice Signin Page Tests

  Background: Open Home Page
    Given Web Browser is Open for test "Automation Practice Signin Page Tests"
    And Automation Practice Signin Page is Launch

  Scenario: Test Elements on Signin Page
    Given Email input and register button elements are present
    Then Close the browser

  Scenario Outline: Test Register on Signin Page
    Given The email <value> is typed and try to register
    Then <error> Error is received
    And Close the browser

    Examples:
      | value                 | error                                     |
      |                       | Invalid email address                     |
      | prueba@@prueba.prueba | Invalid email address                     |
      | prueba@prueba.com     | email address has already been registered |

  Scenario Outline: Test Register on Signin Page
    Given The email <value> is typed and try to register
    Then Register page is shown
    And Close the browser

    Examples:
      | value                |
      | prueba@prueba.prueba |
