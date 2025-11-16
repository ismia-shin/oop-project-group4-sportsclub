package com.group4.sportsclub.Samiha.FinancialOfficer;

import com.group4.sportsclub.Samiha.Member.Member;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Fee implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected Member member;
    protected FinancialOfficer recordedBy;
    protected LocalDate date;
    protected String paymentMethod;
    protected Double amountPaid;
    protected String monthCovered;

}