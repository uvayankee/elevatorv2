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

    @Given("the elevator is initialized with {int} floors")
    public void the_elevator_is_initialized_with_floors(Integer maxFloor) {
        elevator = new Elevator(maxFloor);
    }

    @Then("the elevator should report it can visit floors {int} through {int}")
    public void the_elevator_should_report_it_can_visit_floors_through(Integer min, Integer max) {
        java.util.List<Integer> floors = elevator.getFloors();
        assertEquals(max - min + 1, floors.size());
        assertTrue(floors.contains(min));
        assertTrue(floors.contains(max));
    }

    @io.cucumber.java.en.When("I request floor {int}")
    public void i_request_floor(Integer floor) {
        elevator.requestFloor(floor);
    }

    @io.cucumber.java.en.When("I tell the elevator to step")
    public void i_tell_the_elevator_to_step() {
        elevator.step();
    }

    @Then("the doors should be closed")
    public void the_doors_should_be_closed() {
        assertFalse(elevator.areDoorsOpen());
    }
}
