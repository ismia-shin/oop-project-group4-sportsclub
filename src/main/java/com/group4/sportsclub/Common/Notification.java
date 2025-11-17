package com.group4.sportsclub.Common;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Notification implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected String message;
    protected User fromUser;
    protected User toUser;
    protected LocalDate sentDate;

    public Notification(String message, User fromUser, User toUser, LocalDate sentDate) {
        this.message = message;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.sentDate = sentDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public LocalDate getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDate sentDate) {
        this.sentDate = sentDate;
    }


    public Boolean equals(Notification other){

        return this.message.equals(other.message) && this.fromUser.equals(other.fromUser) && this.toUser.equals(other.toUser) && this.sentDate.equals(other.sentDate);

    }

    @Override
    public String toString() {
        return "Message: " + this.message + "\n From: " + this.fromUser.name + "\n Sent: " + this.sentDate.toString();
    }

    public String getFromUserName(){
        return fromUser.name;
    }
}
