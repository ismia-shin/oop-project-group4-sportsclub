package com.group4.sportsclub.Samiha.FinancialOfficer;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class EventBudget implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public String eventName;
    public LocalDate eventDate;
    public String departmentName;
    protected Double estimatedBudger;
    protected ArrayList<Expense> expenseList = new ArrayList<>();


}