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

    public ClearanceRequest(Member member, String requestedActivity, LocalDate dateRequested) {
        this.member = member;
        this.requestedActivity = requestedActivity;
        this.dateRequested = dateRequested;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getRequestedActivity() {
        return requestedActivity;
    }

    public void setRequestedActivity(String requestedActivity) {
        this.requestedActivity = requestedActivity;
    }

    public LocalDate getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(LocalDate dateRequested) {
        this.dateRequested = dateRequested;
    }

    @Override
    public String toString() {
        return "ClearanceRequest{" +
                "member=" + member +
                ", requestedActivity='" + requestedActivity + '\'' +
                ", dateRequested=" + dateRequested +
                '}';
    }

    public Integer getMemberId() {
        return member.getId();
    }

    public String getMemberName() {
        return member.getName();
    }


}