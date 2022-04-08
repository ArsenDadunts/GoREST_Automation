@GoREST_API @positive @users
Feature: Verify GoREST API GET request to get user by id
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

  Scenario: Validate POST response
    Then The "id" key is not null or empty
    And The "name" key is not null or empty
    And The "email" key is not null or empty
    And The "gender" key is not null or empty
    And The "status" key is not null or empty

  Scenario: Check status code
    Then The response code should be equal to 201
    And I store "id" value data with "userId" key
    And I store "name" value data with "userName" key
    And I store "gender" value data with "userGender" key
    And I store "email" value data with "userEmail" key
    And I store "status" value data with "userStatus" key

  @get_user
  Scenario: Get created user
    Given I create HTTP "GET" request to Users API with "users/{userId}" endpoint
    And I set "userId" stored data as "userId" path segment
    And I send the request

  Scenario: Validate GET response
    Then The "id" key is equal to stored data with "userId" key
    And The "name" key is equal to stored data with "userName" key
    And The "gender" key is equal to stored data with "userGender" key
    And The "email" key is equal to stored data with "userEmail" key
    And The "status" key is equal to stored data with "userStatus" key

  Scenario: Check status code
    Then The response code should be equal to 200