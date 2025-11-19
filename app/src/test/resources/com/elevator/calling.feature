Feature: Elevator Calling
  The elevator should respond to calls and prioritize them correctly.

  Scenario: Elevator stops for a call on the way
    Given the elevator is initialized
    When I request floor 5
    And I call the elevator to floor 3 to go UP
    And I tell the elevator to step
    Then the elevator should be on floor 1
    When I tell the elevator to step
    Then the elevator should be on floor 2
    When I tell the elevator to step
    Then the elevator should be on floor 3
    When I tell the elevator to step
    Then the doors should be open

  Scenario: Elevator ignores a call on the way if opposite direction
    Given the elevator is initialized
    When I request floor 5
    And I call the elevator to floor 3 to go DOWN
    And I tell the elevator to step
    Then the elevator should be on floor 1
    When I tell the elevator to step
    Then the elevator should be on floor 2
    When I tell the elevator to step
    Then the elevator should be on floor 3
    And the doors should be closed
    When I tell the elevator to step
    Then the elevator should be on floor 4
