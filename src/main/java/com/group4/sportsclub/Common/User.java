package com.group4.sportsclub.Common;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public String name;
    protected final String role;
    private  final Integer id;
    private final String password;

    public User(String name, String role, Integer id, String password) {
        this.name = name;
        this.role = role;
        this.id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Boolean LoginValidation(String gId, String gPass){
        try{
            return Integer.parseInt(gId) == this.id && Objects.equals(gPass, this.password);
        }
        catch (Exception e){
            return false;
        }
    }

    public Boolean equals(User other){

        return Objects.equals(this.name, other.name) && Objects.equals(this.id, other.id) && Objects.equals(this.role, other.role);

    }

}