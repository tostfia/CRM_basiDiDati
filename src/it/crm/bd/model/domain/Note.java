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
    private String time;
    private String customerName;
    private String customerSurname;
    private Date appointmentDate;
    private Time appointmentTime;
    private String appointmentBranch;

    public Note() {
    }

    public Note(Boolean outcome, String description, String customer) {
        this.outcome = outcome;
        this.description = description;
        this.customer = customer;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public void setDate(LocalDate date) {this.date = date;}
    public void setTime(String time) {this.time = time;}
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
                "\n |Outcome: " + (Boolean.TRUE.equals(outcome) ? "accepted" : "refused") + "|" +
                "\n |Description: " + description + "|" +
                "\n |Date: " + date + "|" +
                "\n |Time: " + time + "|" +
                "\n |Operator: " + operator + "|" +
                "\n |Appointment: " + (appointmentBranch == null ? "none" : appointmentBranch + " on " + appointmentDate + " at " + appointmentTime) + "|";
    }
}
