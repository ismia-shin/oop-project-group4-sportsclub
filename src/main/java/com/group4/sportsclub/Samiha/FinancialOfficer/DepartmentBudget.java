package com.group4.sportsclub.Samiha.FinancialOfficer;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class DepartmentBudget implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public String department;
    protected Double estimatedBudget;
    protected ArrayList<Expense> expenseList = new ArrayList<>();

    private String status;
    private String statement;

}