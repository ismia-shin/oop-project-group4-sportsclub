package com.group4.sportsclub.Samiha.FinancialOfficer;

import com.group4.sportsclub.Common.User;

import java.util.ArrayList;

public class FinancialOfficer extends User {

    protected ArrayList<Asset> assetList = new ArrayList<>();

    public FinancialOfficer(String name, Integer id, String password) {
        super(name, "Financial", id, password);
    }
}
