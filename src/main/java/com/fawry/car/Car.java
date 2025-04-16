package com.fawry.car;

public class Car {
    private Engine engine;
    private int speed;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        System.out.println("Car starting...");
        engine.start();
    }

    public void stop() {
        System.out.println("Car stopping...");
        engine.stop();
    }

    public void accelerate() {
        if (speed < 200) {
            speed += 20;
            System.out.println("Accelerating to " + speed);
            engine.currentSpeed(speed);
        } else {
            System.out.println("Already at max speed");
        }
    }

    public void brake() {
        if (speed > 0) {
            speed -= 20;
            System.out.println("Braking from " + speed);
            engine.currentSpeed(speed);
        } else {
            System.out.println("Speed cannot be negative, Speed is 0");
        }
    }

    public void replaceEngine(Engine newEngine) {
        System.out.println("Replace engine with " + engine);
        this.engine.stop();
        this.engine = newEngine;
        this.engine.start();
        this.engine.currentSpeed(speed);
    }

    }

