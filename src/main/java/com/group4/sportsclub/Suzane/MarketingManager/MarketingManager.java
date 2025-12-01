package com.group4.sportsclub.Suzane.MarketingManager;

import com.group4.sportsclub.Common.User;
import java.io.Serial;
import java.io.Serializable;

public class MarketingManager extends User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public MarketingManager(String name, Integer id, String password) {
        super(name, "Marketing Manager", id, password);
    }

    @Override
    public String toString() {
        return "MarketingManager{id=" + getId() + ", name='" + getName() + "'}";
    }
}