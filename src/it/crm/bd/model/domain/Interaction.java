package it.crm.bd.model.domain;

import java.sql.Time;
import java.time.LocalDate;

public class Interaction {
    public LocalDate date;
    public Time time;
    public String Customer;
    public String   Offer;
    public Interaction(LocalDate date, Time time,  String customer, String offer) {
        this.date = date;
        this.time = time;
        Customer = customer;
        Offer = offer;
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
    public String getCustomer() {
        return Customer;
    }
    public void setCustomer(String customer) {
        Customer = customer;
    }
    public String getOffer() {
        return Offer;
    }
    public void setOffer(String offer) {
        Offer = offer;
    }
    @Override
    public String toString() {
        return "Interaction{" +
                "date=" + date +
                ", time=" + time +
                ", Customer='" + Customer + '\'' +
                ", Offer='" + Offer + '\'' +
                '}';
    }
}
