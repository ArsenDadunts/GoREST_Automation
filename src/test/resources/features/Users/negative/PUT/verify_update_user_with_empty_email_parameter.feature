@GoREST_API @negative @users
Feature: Verify GoREST API PUT request to update user with empty "email" parameter
  Description: All the test cases:
  - make POST request to create user
  - validate response JSON structure (i.e. there are no missing fields)
  - make PUT request to update user

  @create_user
  Scenario: Create new user
    Given I create HTTP "POST" request to Users API with "users" endpoint
    And I set "{\"name\":\"Allasani Peddana\", \"email\":\"\", \"status\":\"active\", \"gender\":\"male\"}" as request body
    And I set "email" as random generated email
    And I send the request

  Scenario: Check status code
    Then The response code should be equal to 201
    And I store "id" value data with "userId" key
    And I store "email" value data with "userEmail" key

  @update_user
  Scenario: Update user with empty email
    Given I create HTTP "PUT" request to Users API with "users/{userId}" endpoint
    And I set "userId" stored data as "userId" path segment
    And I set "{\"name\":\"Name\", \"email\":\"\", \"status\":\"active\"}" as request body
    And I send the request

  Scenario Outline: Check response
    And "<key>" is equal to "<value>"
    Examples:
      | key         | value          |
      | [0].field   | email          |
      | [0].message | can't be blank |

  Scenario: Check status code
    Then The response code should be equal to 422