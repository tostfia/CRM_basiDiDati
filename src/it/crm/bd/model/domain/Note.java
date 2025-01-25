package it.crm.bd.model.domain;

import java.sql.Time;
import java.time.LocalDate;

public class Note {
    private String customer;
    private Boolean outcome;
    private String description;
    private String operator;
    private LocalDate date;
    private Time time;
    private String customerName;
    private String customerSurname;
    private String offer;
    private Appointment appointment;

    public Note() {
    }
    public Note(Boolean outcome, String description, String customer, String operator, LocalDate date, Time time, String offer, Appointment appointment) {
        this.outcome = outcome;
        this.description = description;
        this.customer = customer;
        this.operator = operator;
        this.date = date;
        this.time = time;
        this.offer = offer;
        this.appointment = appointment;
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
    public Appointment getAppointment() {
        return appointment;
    }
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }



    @Override
    public String toString() {
        return "--------------------"+ customerName+ customerSurname+ "'s notes--------------------" +
                "\nOutcome: " + (Boolean.TRUE.equals(outcome) ? "Accepted" : "Refused") +
                "\nDescription: " + description +
                "\nOperator: " + operator +
                "\nDate: " + date +
                "\nTime: " + time +
                "\nOffer: " + offer +
                "\nAppointment: " + (appointment== null ? "No appointment" : appointment.getBranch() + " on " + appointment.getDate() + " at " + appointment.getTime());
    }
}
