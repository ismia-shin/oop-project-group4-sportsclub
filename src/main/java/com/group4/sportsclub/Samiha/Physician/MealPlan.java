package com.group4.sportsclub.Samiha.Physician;

import com.group4.sportsclub.Samiha.Member.Member;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class MealPlan implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected Member member;
    protected Physician physician;
    protected LocalDate date;
    protected Double weight;
    protected Double height;
    protected Integer age;
    protected Double workIndex;
    protected Double BMR;
    protected Double BMI;
    protected Double TotalCal;
    protected String generalStatement;
    protected String medicalStatement;
    protected ArrayList<Meal> mealList = new ArrayList<>();

}
