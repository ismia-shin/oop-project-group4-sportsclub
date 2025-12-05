package com.group4.sportsclub.Samiha.Physician;

import java.io.Serial;
import java.io.Serializable;

public class Medication implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected String drugName;
    protected String drugDose;
    protected String drugFrequency;
    protected String route;
    protected String duration;

    public Medication(String drugName, String drugDose, String drugFrequency, String route, String duration) {
        this.drugName = drugName;
        this.drugDose = drugDose;
        this.drugFrequency = drugFrequency;
        this.route = route;
        this.duration = duration;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugDose() {
        return drugDose;
    }

    public void setDrugDose(String drugDose) {
        this.drugDose = drugDose;
    }

    public String getDrugFrequency() {
        return drugFrequency;
    }

    public void setDrugFrequency(String drugFrequency) {
        this.drugFrequency = drugFrequency;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


}
