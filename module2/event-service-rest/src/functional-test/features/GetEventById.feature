Feature: Get Event By Id
  Scenario: the client want to get existing event by id
    Given the event with id 1 and title Title-1
    When the client gets the event with id 1
    Then the client recieves status code of 200
    And responded entity has 1 id and Title-1 title


  Scenario: the client want to get not existing event by id
    When the client gets the event with id id 12
    Then the client recieves status code of 404