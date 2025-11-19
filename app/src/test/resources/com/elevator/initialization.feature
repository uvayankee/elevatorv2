Feature: Elevator Initialization
  The elevator should start in a known state.

  Scenario: Elevator starts at floor 1 with doors open
    Given the elevator is initialized
    Then the elevator should be on floor 1
    And the doors should be open
