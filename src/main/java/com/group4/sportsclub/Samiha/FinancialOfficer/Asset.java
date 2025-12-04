package com.group4.sportsclub.Samiha.FinancialOfficer;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Asset implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public Integer id;
    public String name;
    public LocalDate date;
    public  Double cost;
    public String amount;
    public String status;

    public Asset(Integer id, String name, LocalDate date, Double cost, String amount, String status) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.cost = cost;
        this.amount = amount;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean equals(Asset other){

        return Objects.equals(other.id, this.id) || Objects.equals(other.name, this.name);

    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", cost=" + cost +
                ", amount='" + amount + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}