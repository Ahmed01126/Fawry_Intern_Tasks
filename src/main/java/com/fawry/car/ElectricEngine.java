package com.fawry.car;

public class ElectricEngine implements Engine{

    @Override
    public void start() {
        System.out.println("Electric Engine is running");
    }

    @Override
    public void stop() {
        System.out.println("Electric Engine is stopped");
    }

    @Override
    public void currentSpeed(int speed) {
        System.out.println("Electric Engine speed: " + speed);
    }
}
