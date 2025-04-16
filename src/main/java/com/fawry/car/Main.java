package com.fawry.car;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        GasEngine gasEngine = new GasEngine();
//        ElectricEngine electricEngine = new ElectricEngine();
//        HybirdEngine hybirdEngine = new HybirdEngine();
//
//        Car car = CarFactory.creatCar(CarFactory.type.HYBRID);
//        car.start();
//        car.brake();
//        car.brake();
        int wantedSum = 10;
        // first Solution
//        for (int i = 0; i < arr.length; i++) {
//            int curSum = arr[i];
//            for (int j = i + 1; j < arr.length; j++) {
//                curSum += arr[j];
//                if (curSum == wantedSum) {
//                    System.out.println("true");
//                    return;
//                }
//            }
//        }
//        System.out.println("false");

        //Second Solution
//        int tempSum = 0, j = 0;
//        for (int i = 0; i < arr.length; i++) {
//            tempSum += arr[i];
//            if (tempSum == wantedSum) {
//                System.out.println("true");
//                return;
//            }
//            while (tempSum > wantedSum) {
//                tempSum -= arr[j++];
//            }
//        }
//        System.out.println("false");

        // Third Solution
//        HashMap<Integer, Integer> map = new HashMap<>();
//        int temp = 0;
//        for (int i = 0; i < arr.length; i++) {
//            temp += arr[i];
//            temp %= wantedSum;
//            if (!map.isEmpty() && map.get(temp) != null && map.get(temp) != 0) {
//                System.out.println("true");
//                return;
//            } else {
//                map.put(temp, 1);
//            }
//        }
//        System.out.println("false");

        int[] arr = {5, 6, 1, 5, 10,8, 11, 12, 34, 79, 12, 15};
        List<Integer> LIS = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int idx = Collections.binarySearch(LIS, arr[i]);

            if (idx < 0)
                idx = -idx - 1;

            if (idx == LIS.size()) {
                LIS.add(arr[i]);
            } else {
                LIS.set(idx, arr[i]);
            }
        }

        System.out.println(LIS.size());
        System.out.println(LIS);


    }
}