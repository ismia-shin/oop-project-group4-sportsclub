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

    public Refund(Member member, Double amountRequested, String refundReason) {
        this.member = member;
        this.amountRequested = amountRequested;
        this.refundReason = refundReason;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Double getAmountRequested() {
        return amountRequested;
    }

    public void setAmountRequested(Double amountRequested) {
        this.amountRequested = amountRequested;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getStatement() {
        return Statement;
    }

    public void setStatement(String statement) {
        Statement = statement;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public FinancialOfficer getReviewer() {
        return reviewer;
    }

    public void setReviewer(FinancialOfficer reviewer) {
        this.reviewer = reviewer;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
}
