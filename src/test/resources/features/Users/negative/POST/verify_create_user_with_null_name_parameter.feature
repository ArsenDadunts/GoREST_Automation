@GoREST_API @negative @users
Feature: Verify GoREST API POST request to create new user with empty "name" parameter
  Description: All the test cases:
  - make POST request create user
  - validate response JSON structure (i.e. there are no missing fields)

  @create_user
  Scenario: Create new user empty name
    Given I create HTTP "POST" request to Users API with "users" endpoint
    And I set "{\"name\":null, \"email\":\"\", \"status\":\"active\", \"gender\":\"male\"}" as request body
    And I set "email" as random generated email
    And I send the request

  Scenario Outline: Check response
    And "<key>" is equal to "<value>"
    Examples:
      | key         | value          |
      | [0].field   | name           |
      | [0].message | can't be blank |

  Scenario: Check status code
    Then The response code should be equal to 422