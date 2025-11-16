package com.group4.sportsclub.Samiha.Member;

import com.group4.sportsclub.Common.User;

import java.io.Serial;
import java.io.Serializable;

public class Member extends User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public String bloodGroup;
    public String allergies;
    public Integer mobileNum;
    public Integer nid;


    public Member(String name, Integer id, String password, String bloodGroup, String allergies, Integer mobileNum, Integer nid) {
        super(name, "Member", id, password);
        this.bloodGroup = bloodGroup;
        this.allergies = allergies;
        this.mobileNum = mobileNum;
        this.nid = nid;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Integer getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(Integer mobileNum) {
        this.mobileNum = mobileNum;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

}
