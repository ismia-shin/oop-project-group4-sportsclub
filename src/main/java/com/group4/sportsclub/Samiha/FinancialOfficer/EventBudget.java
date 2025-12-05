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
    protected String statement;
    protected ArrayList<Expense> expenseList = new ArrayList<>();

    public EventBudget(String eventName, LocalDate eventDate, String departmentName) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.departmentName = departmentName;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Double getEstimatedBudger() {
        return estimatedBudger;
    }

    public void setEstimatedBudger(Double estimatedBudger) {
        this.estimatedBudger = estimatedBudger;
    }

    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(ArrayList<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public void addExpense(Expense expense){
        this.expenseList.add(expense);
    }
}