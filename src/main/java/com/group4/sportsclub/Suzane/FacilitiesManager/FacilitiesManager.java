package com.group4.sportsclub.Suzane.FacilitiesManager;

import com.group4.sportsclub.Common.User;
import java.io.Serial;
import java.io.Serializable;

public class FacilitiesManager extends User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public FacilitiesManager(String name, Integer id, String password) {
        super(name, "Facilities Manager", id, password);
    }

    @Override
    public String toString() {
        return "FacilitiesManager{id=" + getId() + ", name='" + getName() + "'}";
    }
}