package com.group4.sportsclub.Samiha.FinancialOfficer;

import java.io.Serial;
import java.io.Serializable;

public class Expense implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected String sector;
    protected Double cost;

}