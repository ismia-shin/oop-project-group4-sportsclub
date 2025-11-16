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

}
