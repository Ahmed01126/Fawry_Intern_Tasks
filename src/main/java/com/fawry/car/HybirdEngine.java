package com.fawry.car;

public class HybirdEngine implements Engine {
    private Engine currentEngine;
    private GasEngine gasEngine = new GasEngine();
    private ElectricEngine electricEngine = new ElectricEngine();



    @Override
    public void start() {
        currentEngine = electricEngine; // by default
        currentEngine.start();
    }

    @Override
    public void stop() {
        currentEngine.stop();
    }

    @Override
    public void currentSpeed(int speed) {
        if(speed < 50){
            if(!(currentEngine instanceof ElectricEngine)){
                System.out.println("Switching to ElectricEngine");
                currentEngine.stop();
                electricEngine.start();
                currentEngine = electricEngine;
            }
        }
        else {
            if(!(currentEngine instanceof GasEngine)){
                System.out.println("Switching to GasEngine");
                currentEngine.stop();
                gasEngine.start();
                currentEngine = gasEngine;
            }
        }
        currentEngine.currentSpeed(speed);
    }
}
