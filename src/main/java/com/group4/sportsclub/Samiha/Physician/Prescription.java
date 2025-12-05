package com.group4.sportsclub.Samiha.Physician;

import com.group4.sportsclub.Samiha.Member.Member;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Prescription implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected Member member;
    protected Physician physician;
    protected LocalDate date;
    protected Integer age;
    protected Double weight;
    protected Double bp;
    protected Double temperature;
    protected Double pulse;
    protected String diagnosis;
    protected String generalStatement;
    protected String medicalStatement;
    protected ArrayList<Medication> medicationList = new ArrayList<Medication>();

    public Prescription(Member member, Physician physician, LocalDate date) {
        this.member = member;
        this.physician = physician;
        this.date = date;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public Double getBp() {
        return bp;
    }

    public void setBp(Double bp) {
        this.bp = bp;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPulse() {
        return pulse;
    }

    public void setPulse(Double pulse) {
        this.pulse = pulse;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
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

    public ArrayList<Medication> getMedicationList() {
        return medicationList;
    }

    public void setMedicationList(ArrayList<Medication> medicationList) {
        this.medicationList = medicationList;
    }

    public String  getPhysicianName(){return this.physician.name;}

    public String getMedicationListName(){

        String medicineNames = "";

        for (Medication i: this.medicationList){

            medicineNames = medicineNames + " " + i.drugName;

        }

        return medicineNames;

    }
}
