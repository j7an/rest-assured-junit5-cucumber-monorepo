Feature: User API

  Scenario: Get users for a specific page
    Given the base URI is set
    When a GET request is made to the users endpoint for page 2
    Then the response status code should be 200
    And the response content type should be JSON
    And the response body should contain page number 2
    And the response body should contain user data