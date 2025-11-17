package com.group4.sportsclub.Samiha.Physician;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Agenda implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected LocalDate checkupDate;
    protected ArrayList<Checkup> checkupList = new ArrayList<>();

    public LocalDate getCheckupDate() {
        return checkupDate;
    }

    public void setCheckupDate(LocalDate checkupDate) {
        this.checkupDate = checkupDate;
    }

    public ArrayList<Checkup> getCheckupList() {
        return checkupList;
    }

    public void setCheckupList(ArrayList<Checkup> checkupList) {
        this.checkupList = checkupList;
    }

    public Agenda(LocalDate checkupDate) {
        this.checkupDate = checkupDate;
    }

}
