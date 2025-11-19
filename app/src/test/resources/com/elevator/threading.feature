Feature: Elevator Threading
  The elevator should run on its own in a separate thread.

  Scenario: Elevator runs autonomously
    Given the elevator is initialized
    And the elevator is running in a thread
    When I request floor 3
    And I wait for 2000 milliseconds
    Then the elevator should be on floor 3
    And the doors should be open
    When I stop the elevator thread
    Then the elevator thread should stop
