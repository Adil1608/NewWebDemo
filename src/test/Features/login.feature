Feature: Login tests

  Background:
    Given I navigates to the gmail URL
    When I enter username as "adizir@testingsaiyan.co.in"
    And I tap on Next after entering username
    And I enter password as "99379937"
    And I tap on Next after entering password

  @TC001
  Scenario:  Verify successful login when user enter valid username and password and click on Login button
    Then I should see user should be successfully Logged In

  @TC002
  Scenario Outline:  Verify compose functionality
    And I click on compose button on homepage
    And I enter To address as "<toEmail>" in compose window
    And I enter subject line as "<subject>" in compose window
    And I enter body as "<body>" in compose window
    And I click on send button in compose window
    Then I verify email sent message
    And I verify received email in Inbox

    Examples:
      | toEmail                    | subject  | body                            |
      | adizir@testingsaiyan.co.in | Incubyte | Automation QA test for Incubyte |