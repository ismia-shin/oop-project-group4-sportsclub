package com.group4.sportsclub.Samiha.Physician;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Reports implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected ArrayList<MealPlan> mealPlanHistory = new ArrayList<>();
    protected ArrayList<Prescription> prescriptionHistory = new ArrayList<>();

    public ArrayList<MealPlan> getMealPlanHistory() {
        return mealPlanHistory;
    }

    public void setMealPlanHistory(ArrayList<MealPlan> mealPlanHistory) {
        this.mealPlanHistory = mealPlanHistory;
    }

    public ArrayList<Prescription> getPrescriptionHistory() {
        return prescriptionHistory;
    }

    public void setPrescriptionHistory(ArrayList<Prescription> prescriptionHistory) {
        this.prescriptionHistory = prescriptionHistory;
    }

    public void addPrescription(Prescription p){
        prescriptionHistory.add(p);
    }

    public void addMealPlan(MealPlan m){
        mealPlanHistory.add(m);
    }
}
