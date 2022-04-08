@GoREST_API @negative @users @smoke
Feature: Verify GoREST API DELETE request to delete user by id with deleted userId
  Description: All the test cases:
  - make DELETE request delete user
  - validate response JSON structure (i.e. there are no missing fields)

  @create_user
  Scenario: Create new user
    Given I create HTTP "POST" request to Users API with "users" endpoint
    And I set "{\"name\":\"Allasani Peddana\", \"email\":\"\", \"status\":\"active\", \"gender\":\"male\"}" as request body
    And I set "email" as random generated email
    And I send the request

  Scenario: Check status code
    Then The response code should be equal to 201
    And I store "id" value data with "userId" key

  @delete_user
  Scenario: Delete created user
    Given I create HTTP "DELETE" request to Users API with "users/{userId}" endpoint
    And I set "userId" stored data as "userId" path segment
    And I send the request

  Scenario: Check status code
    Then The response code should be equal to 204

  @delete_user
  Scenario: Delete user with invalid userId
    Given I create HTTP "DELETE" request to Users API with "users/{userId}" endpoint
    And I set "userId" stored data as "userId" path segment
    And I send the request

  Scenario: Check error message
    Then "message" is equal to "Resource not found"

  Scenario: Check status code
    Then The response code should be equal to 404