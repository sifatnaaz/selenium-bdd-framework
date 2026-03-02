Feature: Bearer Authentication using httpbin



  @api @auth
  Scenario: With bearer token returns 200
    When I call httpbin bearer endpoint with token "abc123"
    Then the response status should be 200
    And the response should confirm bearer is true