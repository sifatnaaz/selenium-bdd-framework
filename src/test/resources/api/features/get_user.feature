Feature: USER API

Scenario: Get user by User ID
  Given I call GET user with id "3"
  Then the response status should be 200
  And the response should contain user id 3