Feature: Login functionality

  Scenario: Valid user login
    Given user is on login page
	When user enters valid credentials
    Then user should see the homepage