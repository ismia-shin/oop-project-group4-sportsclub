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

    public Fee(Member member, FinancialOfficer recordedBy, LocalDate date, String paymentMethod, Double amountPaid, String monthCovered) {
        this.member = member;
        this.recordedBy = recordedBy;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.monthCovered = monthCovered;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public FinancialOfficer getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(FinancialOfficer recordedBy) {
        this.recordedBy = recordedBy;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getMonthCovered() {
        return monthCovered;
    }

    public void setMonthCovered(String monthCovered) {
        this.monthCovered = monthCovered;
    }

    public String getFinancialOfficerName(){
        return this.recordedBy.getName();
    }
}