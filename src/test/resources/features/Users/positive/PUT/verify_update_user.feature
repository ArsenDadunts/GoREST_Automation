@GoREST_API @positive @users
Feature: Verify GoREST API PUT request to update user with
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
    And I store "name" value data with "userName" key
    And I store "email" value data with "userEmail" key
    And I store "status" value data with "userStatus" key

  @update_user
  Scenario: Update created user
    Given I create HTTP "PUT" request to Users API with "users/{userId}" endpoint
    And I set "userId" stored data as "userId" path segment
    And I set "{\"name\":\"Updated Name\", \"email\":\"\", \"status\":\"inactive\"}" as request body
    And I set "email" as random generated email
    And I send the request

  Scenario Outline: Check response
    And "<key>" is equal to "<value>"
    Examples:
      | key    | value        |
      | name   | Updated Name |
      | status | inactive     |

  Scenario: Check status code
    Then The response code should be equal to 200