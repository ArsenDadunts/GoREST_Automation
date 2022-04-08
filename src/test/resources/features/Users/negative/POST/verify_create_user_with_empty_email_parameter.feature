@GoREST_API @negative @users
Feature: Verify GoREST API POST request to create new user with empty "email" parameter
  Description: All the test cases:
  - make POST request create user
  - validate response JSON structure (i.e. there are no missing fields)

  @create_user
  Scenario: Create new user with empty email
    Given I create HTTP "POST" request to Users API with "users" endpoint
    And I set "{\"name\":\"Allasani Peddana\", \"email\":\"\", \"status\":\"active\", \"gender\":\"male\"}" as request body
    And I send the request

  Scenario Outline: Check response
    And "<key>" is equal to "<value>"
    Examples:
      | key         | value          |
      | [0].field   | email          |
      | [0].message | can't be blank |

  Scenario: Check status code
    Then The response code should be equal to 422