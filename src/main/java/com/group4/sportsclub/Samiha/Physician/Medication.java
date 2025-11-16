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

}
