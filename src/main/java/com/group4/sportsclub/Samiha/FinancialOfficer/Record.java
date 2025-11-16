package com.group4.sportsclub.Samiha.FinancialOfficer;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Record implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected ArrayList<Fee> feeList = new ArrayList<>();
    protected ArrayList<Refund> refundList = new ArrayList<>();
    protected ArrayList<EventBudget> eventBudgetList = new ArrayList<>();
    protected ArrayList<DepartmentBudget> departmentBudgetList = new ArrayList<>();
    protected ArrayList<Payroll> payrollList = new ArrayList<>();

}
