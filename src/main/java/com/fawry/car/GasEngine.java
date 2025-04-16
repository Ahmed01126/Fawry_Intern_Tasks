package com.fawry.car;

public class GasEngine implements Engine{

    @Override
    public void start() {
        System.out.println("Gas Engine is running");
    }

    @Override
    public void stop() {
        System.out.println("GasEngine is stopped");
    }

    @Override
    public void currentSpeed(int speed) {
        System.out.println("Gas Engine speed: " + speed);
    }
}
