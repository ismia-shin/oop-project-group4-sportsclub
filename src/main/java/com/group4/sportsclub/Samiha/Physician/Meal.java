package com.group4.sportsclub.Samiha.Physician;

import java.io.Serial;
import java.io.Serializable;

public class Meal implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected String type;
    protected Double protein;
    protected Double carb;
    protected Double fat;
    protected Double mealCal;

    public Meal(String type, Double protein, Double carb, Double fat) {

        this.type = type;
        this.protein = protein;
        this.carb = carb;
        this.fat = fat;

        calculateMealCal();

    }

    public void calculateMealCal(){

        this.mealCal = this.carb * 4 + this.protein * 4 + this.fat * 9;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getCarb() {
        return carb;
    }

    public void setCarb(Double carb) {
        this.carb = carb;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getMealCal(){
        return mealCal;
    }

    public void seMealCal(){calculateMealCal();}
}