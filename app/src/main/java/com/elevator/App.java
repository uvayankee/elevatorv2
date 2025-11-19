package com.elevator;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Elevator Simulator - Manual Test");
        Elevator elevator = new Elevator();

        elevator.start();
        Thread elevatorThread = new Thread(() -> {
            while (elevator.isRunning()) {
                elevator.step();
                System.out.println("Floor: " + elevator.getCurrentFloor() + ", Doors: "
                        + (elevator.areDoorsOpen() ? "OPEN" : "CLOSED"));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        elevatorThread.start();

        System.out.println("Requesting floor 3...");
        elevator.requestFloor(3);

        Thread.sleep(2000);

        System.out.println("Final state - Floor: " + elevator.getCurrentFloor() + ", Doors: "
                + (elevator.areDoorsOpen() ? "OPEN" : "CLOSED"));

        elevator.stop();
        elevatorThread.join();
    }
}
