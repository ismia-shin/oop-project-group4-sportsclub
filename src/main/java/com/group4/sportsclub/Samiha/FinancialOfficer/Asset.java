package com.group4.sportsclub.Samiha.FinancialOfficer;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Asset implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public Integer id;
    public String name;
    public LocalDate date;
    public  Double cost;
    public String amount;
    public String status;

}