Feature: Student Management API
  As a course administrator
  I want to manage students via the REST API

  Scenario: Create and retrieve a student
    When I create a student with name "Nikola" and surname "Tesla"
    Then the student should be successfully created
    And I should be able to retrieve the student with name "Nikola" and surname "Tesla" by its ID

  Scenario: Delete a student and verify count
    Given there are 2 students in the system
    When I delete the student with ID 2
    Then there should be 1 student remaining