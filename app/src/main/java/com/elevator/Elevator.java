package com.elevator;

public class Elevator {
    private int currentFloor;
    private boolean doorsOpen;
    private int maxFloor;

    public Elevator() {
        this(10); // Default to 10 floors
    }

    public Elevator(int maxFloor) {
        this.currentFloor = 1;
        this.doorsOpen = true;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public boolean areDoorsOpen() {
        return doorsOpen;
    }

    public java.util.List<Integer> getFloors() {
        java.util.List<Integer> floors = new java.util.ArrayList<>();
        for (int i = 1; i <= maxFloor; i++) {
            floors.add(i);
        }
        return floors;
    }

    private Integer targetFloor = null;

    public void requestFloor(int floor) {
        if (floor >= 1 && floor <= maxFloor) {
            this.targetFloor = floor;
        }
    }

    public void step() {
        if (targetFloor == null) {
            return;
        }

        if (doorsOpen) {
            if (currentFloor != targetFloor) {
                doorsOpen = false;
            }
        } else {
            if (currentFloor < targetFloor) {
                currentFloor++;
            } else if (currentFloor > targetFloor) {
                currentFloor--;
            } else {
                doorsOpen = true;
                targetFloor = null; // Request fulfilled
            }
        }
    }
}
