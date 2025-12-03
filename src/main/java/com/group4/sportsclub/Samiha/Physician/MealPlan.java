package com.group4.sportsclub.Samiha.Physician;

import com.group4.sportsclub.Samiha.Member.Member;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class MealPlan implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected Member member;
    protected Physician physician;
    protected LocalDate date;
    protected Double weight;
    protected Double height;
    protected Integer age;
    protected Double workIndex;
    protected Double BMR;
    protected Double BMI;
    protected Double TotalCal;
    protected String generalStatement;
    protected String medicalStatement;
    protected ArrayList<Meal> mealList = new ArrayList<>();

    public MealPlan(Member member, Physician physician, LocalDate date) {
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWorkIndex() {
        return workIndex;
    }

    public void setWorkIndex(Double workIndex) {
        this.workIndex = workIndex;
    }

    public Double getBMR() {
        return BMR;
    }

    public void setBMR(Double BMR) {
        this.BMR = BMR;
    }

    public Double getBMI() {
        return BMI;
    }

    public void setBMI(Double BMI) {
        this.BMI = BMI;
    }

    public Double getTotalCal() {
        return TotalCal;
    }

    public void setTotalCal(Double totalCal) {
        TotalCal = totalCal;
    }

    public String getGeneralStatement() {
        return generalStatement;
    }

    public void setGeneralStatement(String generalStatement) {
        this.generalStatement = generalStatement;
    }

    public String getMedicalStatement() {
        return medicalStatement;
    }

    public void setMedicalStatement(String medicalStatement) {
        this.medicalStatement = medicalStatement;
    }

    public ArrayList<Meal> getMealList() {
        return mealList;
    }

    public void setMealList(ArrayList<Meal> mealList) {
        this.mealList = mealList;
    }
}
