package it.crm.bd.model.domain;

import java.sql.Time;
import java.time.LocalDate;

public class Interaction {
    public LocalDate date;
    public Time time;
    public String customer;
    public OffersType  Offer;

    public Interaction(LocalDate date, Time time, String customer, OffersType offer) {
        this.date = date;
        this.time = time;
        this.customer = customer;
        this.Offer = offer;
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
        return customer;
    }
    public void setCustomer(String customer) {this.customer = customer;}
    public OffersType getOffer() {
        return Offer;
    }
    public void setOffer(OffersType offer) {
        Offer = offer;
    }

    @Override
    public String toString(){
        return "Interaction{" +
                "date=" + date +
                ", time=" + time +
                ", Customer='" + customer + '\'' +
                ", Offer='" + Offer + '\'' +
                '}';
    }
}
