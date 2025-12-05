package com.group4.sportsclub.Samiha.FinancialOfficer;

import java.io.Serial;
import java.io.Serializable;

public class Expense implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    protected String sector;
    protected Double cost;

    public Expense(String sector, Double cost) {
        this.sector = sector;
        this.cost = cost;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}