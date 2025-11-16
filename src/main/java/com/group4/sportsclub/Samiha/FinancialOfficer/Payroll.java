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

}
