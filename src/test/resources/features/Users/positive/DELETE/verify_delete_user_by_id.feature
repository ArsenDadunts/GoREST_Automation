@GoREST_API @positive @users
Feature: Verify GoREST API DELETE request to delete user by id
  Description: All the test cases:
  - make POST request create user
  - validate response JSON structure (i.e. there are no missing fields)
  - make GET request to get created user

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