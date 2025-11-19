Feature: Elevator Movement
  The elevator should move to requested floors, handling doors correctly.

  Scenario: Elevator moves to a requested floor
    Given the elevator is initialized
    When I request floor 3
    And I tell the elevator to step
    Then the doors should be closed
    And the elevator should be on floor 1
    When I tell the elevator to step
    Then the elevator should be on floor 2
    When I tell the elevator to step
    Then the elevator should be on floor 3
    And the doors should be open
