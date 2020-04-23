Feature: Update Event
  Scenario: the client wants to update the existing event
    Given the event with id 1 and title Title-1
    When the client updates the event 1 with new title NewTitle
    Then the client recieves status code of 200
    And responded entity has 1 id and NewTitle title


  Scenario: the client wants to update a not existing event
    When the client updates the event 1 with new title NewTitle
    Then the client recieves status code of 404