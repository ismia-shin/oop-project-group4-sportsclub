package com.group4.sportsclub.Samiha.FinancialOfficer;

import com.group4.sportsclub.Samiha.Member.Member;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Refund implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public Member member;
    public Double amountRequested;
    public String refundReason;
    protected String Statement;
    protected LocalDate date;
    protected FinancialOfficer reviewer;
    private String refundStatus;

}
