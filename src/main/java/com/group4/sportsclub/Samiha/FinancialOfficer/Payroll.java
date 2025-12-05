package com.group4.sportsclub.Samiha.FinancialOfficer;

import com.group4.sportsclub.Common.User;

import java.io.Serial;
import java.io.Serializable;

public class Payroll implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private User user;
    private String payPeriod;
    private Double grossSalary;
    private Double payableAmount;

    public Payroll(User user, String payPeriod, Double grossSalary) {
        this.user = user;
        this.payPeriod = payPeriod;
        this.grossSalary = grossSalary;

        this.payableAmount = calculatePayableAmount();
    }

    public Double getPayableAmount() {
        return payableAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public Double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(Double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public Double calculatePayableAmount(){

        return this.grossSalary * 0.75;

    }

    public String getUserName(){

        return this.user.getName();

    }

    public Integer getUserId(){

        return this.user.getId();

    }

}
