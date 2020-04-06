Feature: Delete Event
  Scenario: the client wants to delete event by id
    Given the event with id 1 and title TitleEvent
    When the client deletes event with 1 id
    Then the client recieves status code of 200
    And responded entity has 1 id and TitleEvent title


  Scenario: the client wants to delete not existing event
    When the client deletes event with 1 id
    Then the client recieves status code of 404