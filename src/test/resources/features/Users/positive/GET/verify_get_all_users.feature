@GoREST_API @positive @users
Feature: Verify GoREST API GET request to get all users
  Description: All the test cases:
  - make GET request get users
  - validate response JSON structure (i.e. there are no missing fields)

  @get_users
  Scenario: Get all users
    Given I create HTTP "GET" request to Users API with "users" endpoint
    And I send the request

  Scenario: Check status code
    Then The response code should be equal to 200