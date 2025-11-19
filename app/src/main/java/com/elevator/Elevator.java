package com.elevator;

public class Elevator {
    private int currentFloor;
    private boolean doorsOpen;

    public Elevator() {
        this.currentFloor = 1;
        this.doorsOpen = true;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public boolean areDoorsOpen() {
        return doorsOpen;
    }
}
