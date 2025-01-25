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

    public Appointment() {}

    public Appointment(String branch, LocalDate appointmentDate, Time appointmentTime) {
        this.branch = branch;
        this.date = appointmentDate;
        this.time = appointmentTime;
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
    public void setOperator(String operator) {this.operator = operator;}
    @Override
    public String toString() {
        return "--------Appointment--------\n" +
                "|Customer: " + customer + "|\n" +
                "|Date: " + date + "|\n" +
                "|Time: " + time + "|\n" +
                "|Branch: " + branch + "|\n" +
                "|Operator: " + operator + "|\n" +
                "---------------------------";
    }



}
