package com.example.mobapplicationdev;

public class FirebaseDb {
    String firstname;
    String lastname;
    String age;
    String gender;
    String height;
    int targetCalorie;
    int targetProtein;
    int targetCarbs;
    int targetFat;
    int targetWater;

    public FirebaseDb(){

    }
    public FirebaseDb(String firstname,String lastname,String age,String gender, String height,int targetCalorie, int targetFat,int targetProtein,int targetCarbs,int targetWater) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.targetCalorie = targetCalorie;
        this.targetCarbs = targetCarbs;
        this.targetFat = targetFat;
        this.targetProtein = targetProtein;
        this.targetWater = targetWater;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public String getAge(){
        return age;
    }
    public String getGender(){
        return gender;
    }
    public String getHeight(){
        return height;
    }

    public int getTargetCalorie(){
        return targetCalorie;
    }


    public int getTargetProtein(){
        return targetProtein;
    }


    public int getTargetFat(){
        return targetFat;
    }


    public int getTargetCarbs(){
        return targetCarbs;
    }

    public int getTargetWater(){
        return targetWater;
    }



}
