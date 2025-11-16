package com.group4.sportsclub.Samiha.Physician;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Reports implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected ArrayList<MealPlan> mealPlanHistory = new ArrayList<>();
    protected ArrayList<Prescription> prescriptionHistory = new ArrayList<>();

}
