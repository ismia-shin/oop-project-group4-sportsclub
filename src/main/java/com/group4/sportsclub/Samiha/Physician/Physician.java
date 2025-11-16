package com.group4.sportsclub.Samiha.Physician;

import com.group4.sportsclub.Common.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Physician extends User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public String designation;
    public ArrayList<Checkup> pendingCheckup = new ArrayList<>();
    protected ArrayList<Agenda> allAgenda = new ArrayList<Agenda>();
    protected static ArrayList<ClearanceRequest> clearanceList = new ArrayList<>();


    public Physician(String name, Integer id, String password, String designation) {
        super(name, "Physician", id, password);
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public ArrayList<Checkup> getPendingCheckup() {
        return pendingCheckup;
    }

    public void setPendingCheckup(ArrayList<Checkup> pendingCheckup) {
        this.pendingCheckup = pendingCheckup;
    }

    public void setAllAgenda(ArrayList<Agenda> allAgenda) {
        this.allAgenda = allAgenda;
    }

    public static ArrayList<ClearanceRequest> getClearanceList() {
        return clearanceList;
    }

    public static void setClearanceList(ArrayList<ClearanceRequest> clearanceList) {
        Physician.clearanceList = clearanceList;
    }

    public ArrayList<Agenda> getAllAgenda() {
        return allAgenda;
    }
}
