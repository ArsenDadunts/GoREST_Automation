@GoREST_API @negative @users
Feature: Verify GoREST API GET request to get user by id with invalid userId
  Description: All the test cases:
  - make GET request delete user
  - validate response JSON structure (i.e. there are no missing fields)

  @get_user
  Scenario: Get user with invalid userId
    Given I create HTTP "GET" request to Users API with "users/{userId}" endpoint
    And I set "invalid" as "userId" path segment
    And I send the request

  Scenario: Check error message
    Then "message" is equal to "Resource not found"

  Scenario: Check status code
    Then The response code should be equal to 404