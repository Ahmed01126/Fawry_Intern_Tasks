package com.fawry.car;

public class CarFactory {
    public enum type {GAS,ELECTRIC,HYBRID}

    public static Car creatCar(type type) {
        Engine engine = switch (type) {
            case GAS -> new GasEngine();
            case ELECTRIC -> new ElectricEngine();
            case HYBRID -> new HybirdEngine();
        };
        return new Car(engine);

    }

}
