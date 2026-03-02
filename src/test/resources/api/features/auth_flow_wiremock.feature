@api @auth @wiremock
Feature: True Login Token Flow (WireMock)

@api @auth @wiremock
  Scenario: Login and access secure endpoint successfully
    Given I login with username "sifat" and password "pass123"
    Then the response status should be 200
    And I store the auth token

    When I call secure profile endpoint
    Then the response status should be 200
    And the profile name should be "Sifat"