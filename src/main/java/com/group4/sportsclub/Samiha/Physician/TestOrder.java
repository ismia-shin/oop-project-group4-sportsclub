package com.group4.sportsclub.Samiha.Physician;

import com.group4.sportsclub.Samiha.Member.Member;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected Member member;
    protected Physician physician;
    protected LocalDate date;
    protected String medicalStatement;
    protected ArrayList<String> testList = new ArrayList<String>();

    public TestOrder(Member member, Physician physician, LocalDate date) {
        this.member = member;
        this.physician = physician;
        this.date = date;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMedicalStatement() {
        return medicalStatement;
    }

    public void setMedicalStatement(String medicalStatement) {
        this.medicalStatement = medicalStatement;
    }

    public ArrayList<String> getTestList() {
        return testList;
    }

    public void setTestList(ArrayList<String> testList) {
        this.testList = testList;
    }

    public void addTest(String test){testList.add(test);}

    @Override
    public String toString() {
        return "Physician=" + physician.name + "\n" +
                "Date=" + date + "\n" +
                "Medical Statement='" + medicalStatement + "\n" +
                "Test List=" + testList;
    }
}
