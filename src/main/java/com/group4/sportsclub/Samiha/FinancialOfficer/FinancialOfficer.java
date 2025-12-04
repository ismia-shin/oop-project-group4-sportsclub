package com.group4.sportsclub.Samiha.FinancialOfficer;

import com.group4.sportsclub.Common.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class FinancialOfficer extends User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public FinancialOfficer(String name, Integer id, String password) {
        super(name, "Financial", id, password);
    }
}
