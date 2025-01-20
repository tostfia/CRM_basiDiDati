package it.crm.bd.model.domain;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class Note {
    private String customer;
    private Boolean outcome;
    private String description;
    private String operator;
    private LocalDate date;
    private Time time;
    private String customerName;
    private String customerSurname;
    private Date appointmentDate;
    private Time appointmentTime;
    private String appointmentBranch;
    private String offer;

    public Note() {
    }

    public Note(Boolean outcome, String description, String customer, String operator, LocalDate date, Time time, String offer) {
        this.outcome = outcome;
        this.description = description;
        this.customer = customer;
        this.operator = operator;
        this.date = date;
        this.time = time;
        this.offer = offer;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public void setDate(LocalDate date) {this.date = date;}
    public LocalDate getDate() {return date;}
    public String getOperator() {return operator;}
    public String getOffer() {return offer;}
    public void setOffer(String offer) {this.offer = offer;}
    public Time getTime() {return time;}
    public void setTime(Time time) {this.time = time;}
    public Boolean getOutcome() {
        return outcome;
    }
    public void setOutcome(Boolean outcome) {
        this.outcome = outcome;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCustomer() {return customer;}
    public void setCustomerName(String customer) {this.customerName = customer;}
    public void setCustomerSurname(String customer) {this.customerSurname = customer;}
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public void setAppointmentDate(Date appointmentDate) {this.appointmentDate = appointmentDate;}
    public void setAppointmentTime(Time appointmentTime) {this.appointmentTime = appointmentTime;}
    public void setAppointmentBranch(String appointmentBranch) {this.appointmentBranch = appointmentBranch;}



    @Override
    public String toString() {
        return "--------------------"+ customerName+ customerSurname+ "'s notes--------------------" +
                "\nOutcome: " + (Boolean.TRUE.equals(outcome) ? "Accepted" : "Refused") +
                "\nDescription: " + description +
                "\nOperator: " + operator +
                "\nDate: " + date +
                "\nTime: " + time +
                "\nOffer: " + offer +
                "\nAppointment: " + (appointmentBranch == null ? "No appointment" : appointmentBranch + " on " + appointmentDate + " at " + appointmentTime);
    }
}
