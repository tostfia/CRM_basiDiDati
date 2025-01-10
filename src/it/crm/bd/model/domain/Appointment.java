package it.crm.bd.model.domain;

import java.sql.Time;
import java.time.LocalDate;

public class Appointment {
    private String customer;
    private LocalDate date;
    private Time time;
    private String branch;
    private String operator;
    public Appointment(String customer, LocalDate date, Time time, String branch, String operator) {
        this.customer = customer;
        this.date = date;
        this.time = time;
        this.branch = branch;
        this.operator = operator;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Time getTime() {
        return time;
    }
    public void setTime(Time time) {
        this.time = time;
    }
    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public String getOperator() {return operator;}
    @Override
    public String toString() {
        return "Appointment{" +
                "customer='" + customer + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", branch='" + branch + '\'' +
                '}';
    }


}
