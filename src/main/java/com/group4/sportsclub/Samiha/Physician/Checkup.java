package com.group4.sportsclub.Samiha.Physician;

import com.group4.sportsclub.Samiha.Member.Member;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Checkup implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getTypeCheckup() {
        return typeCheckup;
    }

    public void setTypeCheckup(String typeCheckup) {
        this.typeCheckup = typeCheckup;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    protected Member member;
    protected String typeCheckup;
    protected LocalDate requestedDate;

    public Checkup(Member member, String typeCheckup, LocalDate requestedDate) {
        this.member = member;
        this.typeCheckup = typeCheckup;
        this.requestedDate = requestedDate;
    }

    public Boolean equalsToDate(Checkup other){

        return this.member.equals(other.member) && this.requestedDate == other.requestedDate;

    }

}