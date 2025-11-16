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

}