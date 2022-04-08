@GoREST_API @negative @users
Feature: Verify GoREST API DELETE request to delete user by id with invalid userId
  Description: All the test cases:
  - make DELETE request delete user
  - validate response JSON structure (i.e. there are no missing fields)

  @delete_user
  Scenario: Delete user with invalid userId
    Given I create HTTP "DELETE" request to Users API with "users/{userId}" endpoint
    And I set "invalid" as "userId" path segment
    And I send the request

  Scenario: Check error message
    Then "message" is equal to "Resource not found"

  Scenario: Check status code
    Then The response code should be equal to 404