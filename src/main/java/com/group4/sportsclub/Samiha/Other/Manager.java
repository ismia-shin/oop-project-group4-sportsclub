package com.group4.sportsclub.Samiha.Other;

import com.group4.sportsclub.Common.User;

import java.io.Serial;
import java.io.Serializable;

public class Manager extends User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public Manager(String name, Integer id, String password) {
        super(name, "Manager", id, password);
    }

}
