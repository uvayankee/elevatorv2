package com.elevator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {
    private Elevator elevator;

    @Given("the elevator is initialized")
    public void the_elevator_is_initialized() {
        elevator = new Elevator();
    }

    @Then("the elevator should be on floor {int}")
    public void the_elevator_should_be_on_floor(Integer floor) {
        assertEquals(floor, elevator.getCurrentFloor());
    }

    @Then("the doors should be open")
    public void the_doors_should_be_open() {
        assertTrue(elevator.areDoorsOpen());
    }
}
