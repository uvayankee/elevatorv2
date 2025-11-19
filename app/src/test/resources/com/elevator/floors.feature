Feature: Elevator Floors
  The elevator should know which floors it can visit.

  Scenario: Elevator provides a list of floors
    Given the elevator is initialized with 10 floors
    Then the elevator should report it can visit floors 1 through 10
