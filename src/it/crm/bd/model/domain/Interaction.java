package it.crm.bd.model.domain;

import java.sql.Time;
import java.time.LocalDate;

public class Interaction {
    public LocalDate date;
    public Time time;
    public String Customer;
    public OffersType  Offer;
    public String Operator;
    public Interaction(LocalDate date, Time time, String customer, OffersType offer, String operator) {
        this.date = date;
        this.time = time;
        Customer = customer;
        this.Offer = offer;
        this.Operator=operator;
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
    public OffersType getOffer() {
        return Offer;
    }
    public void setOffer(OffersType offer) {
        Offer = offer;
    }
    public String getOperator() {
        return Operator;
    }
    public void setOperator(String operator) {
        Operator = operator;
    }
    @Override
    public String toString(){
        return "Interaction{" +
                "date=" + date +
                ", time=" + time +
                ", Customer='" + Customer + '\'' +
                ", Offer='" + Offer + '\'' +
                ", Operator='" + Operator + '\'' +
                '}';
    }
}
