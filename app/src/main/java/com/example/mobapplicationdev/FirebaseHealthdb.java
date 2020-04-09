package com.example.mobapplicationdev;

public class FirebaseHealthdb {

    int protien;
    int calories;
    int carbs;
    int fat;
    int water;

    public FirebaseHealthdb(){

    }

    public FirebaseHealthdb(int calories, int protien, int carbs, int fat,int water) {
        this.calories = calories;
        this.protien = protien;
        this.carbs = carbs;
        this.fat = fat;
        this.water = water;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtien() {
        return protien;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getFat() {
        return fat;
    }

    public int getWater() {
        return water;
    }
}
