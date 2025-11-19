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

    private java.util.Set<Integer> destinations = new java.util.HashSet<>();
    private java.util.Set<Integer> upCalls = new java.util.HashSet<>();
    private java.util.Set<Integer> downCalls = new java.util.HashSet<>();
    private Direction currentDirection = Direction.IDLE;

    public void requestFloor(int floor) {
        if (floor >= 1 && floor <= maxFloor) {
            destinations.add(floor);
        }
    }

    public void call(int floor, Direction direction) {
        if (floor >= 1 && floor <= maxFloor) {
            if (direction == Direction.UP) {
                upCalls.add(floor);
            } else if (direction == Direction.DOWN) {
                downCalls.add(floor);
            }
        }
    }

    public void step() {
        if (doorsOpen) {
            doorsOpen = false;
            return;
        }

        if (shouldStopAt(currentFloor)) {
            openDoorsAndClearRequests();
            return;
        }

        updateDirection();

        if (currentDirection == Direction.IDLE) {
            return;
        }

        if (currentDirection == Direction.UP) {
            currentFloor++;
        } else {
            currentFloor--;
        }
    }

    private boolean shouldStopAt(int floor) {
        if (destinations.contains(floor))
            return true;
        if (currentDirection == Direction.UP && upCalls.contains(floor))
            return true;
        if (currentDirection == Direction.DOWN && downCalls.contains(floor))
            return true;

        // Stop if we are changing direction
        if (currentDirection == Direction.UP && !hasRequestsAbove(floor) && downCalls.contains(floor))
            return true;
        if (currentDirection == Direction.DOWN && !hasRequestsBelow(floor) && upCalls.contains(floor))
            return true;

        return false;
    }

    private void openDoorsAndClearRequests() {
        doorsOpen = true;
        destinations.remove(currentFloor);
        if (currentDirection == Direction.UP || currentDirection == Direction.IDLE) {
            upCalls.remove(currentFloor);
        }
        if (currentDirection == Direction.DOWN || currentDirection == Direction.IDLE) {
            downCalls.remove(currentFloor);
        }
        // If we are at the top/bottom of our journey, we might clear the opposite call
        // too if we are switching
        // But for now, let's be simple.
    }

    private void updateDirection() {
        if (currentDirection == Direction.IDLE) {
            if (hasRequestsAbove(currentFloor))
                currentDirection = Direction.UP;
            else if (hasRequestsBelow(currentFloor))
                currentDirection = Direction.DOWN;
            else if (hasRequestsAt(currentFloor)) {
                // Should have stopped already, but if we are here, maybe we just closed doors?
                // If we have requests at current floor, we should stay or open?
                // If we just closed doors, we shouldn't open again unless new request.
                // But this logic is called when doors are closed.
                // If we have requests at current floor, we should probably open.
                // But we handle that in step() before calling updateDirection().
            }
            return;
        }

        if (currentDirection == Direction.UP) {
            if (!hasRequestsAbove(currentFloor)) {
                if (hasRequestsBelow(currentFloor) || hasRequestsAt(currentFloor)) {
                    currentDirection = Direction.DOWN;
                } else {
                    currentDirection = Direction.IDLE;
                }
            }
        } else { // DOWN
            if (!hasRequestsBelow(currentFloor)) {
                if (hasRequestsAbove(currentFloor) || hasRequestsAt(currentFloor)) {
                    currentDirection = Direction.UP;
                } else {
                    currentDirection = Direction.IDLE;
                }
            }
        }
    }

    private boolean hasRequestsAbove(int floor) {
        if (destinations.stream().anyMatch(f -> f > floor))
            return true;
        if (upCalls.stream().anyMatch(f -> f > floor))
            return true;
        if (downCalls.stream().anyMatch(f -> f > floor))
            return true;
        return false;
    }

    private boolean hasRequestsBelow(int floor) {
        if (destinations.stream().anyMatch(f -> f < floor))
            return true;
        if (upCalls.stream().anyMatch(f -> f < floor))
            return true;
        if (downCalls.stream().anyMatch(f -> f < floor))
            return true;
        return false;
    }

    private boolean hasRequestsAt(int floor) {
        return destinations.contains(floor) || upCalls.contains(floor) || downCalls.contains(floor);
    }
}
