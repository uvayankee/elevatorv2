package com.elevator;

public class App {
    public static void main(String[] args) {
        System.out.println("Elevator Simulator");
        Elevator elevator = new Elevator();
        System.out.println("Elevator is at floor " + elevator.getCurrentFloor());
    }
}
