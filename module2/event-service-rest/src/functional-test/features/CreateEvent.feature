Feature: Create Event
  Scenario: client wants to create an event
    When the client creates an event with title NewTitle
    Then the client recieves status code of 201
    And the created entity must have generated id