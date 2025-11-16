package com.group4.sportsclub.Samiha.Physician;

import com.group4.sportsclub.Samiha.Member.Member;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class ClearanceRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected Member member;
    protected String requestedActivity;
    protected LocalDate dateRequested;

}