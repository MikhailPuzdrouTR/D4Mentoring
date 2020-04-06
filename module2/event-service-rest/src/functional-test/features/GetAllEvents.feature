Feature: Get All Events
  Scenario: the client wants to get all events
    Given the event with id 1 and title Title-1
    Given the event with id 2 and title Title-2
    Given the event with id 3 and title Title-3
    When the client calls /events with title ''
    Then the client recieves status code of 200
    And the size of responsed events if 3
    And the response have to contain entity with id 1
    And the response have to contain entity with id 2
    And the response have to contain entity with id 3


  Scenario: the client wants to find events by title
    Given the event with id 1 and title Title-1
    Given the event with id 2 and title Title-2
    Given the event with id 3 and title Title-1
    When the client calls /events with title 'Title-1'
    Then the client recieves status code of 200
    And the size of responsed events if 2
    And the response have to contain entity with id 1
    And the response have to contain entity with id 3