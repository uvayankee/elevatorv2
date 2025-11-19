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
}
