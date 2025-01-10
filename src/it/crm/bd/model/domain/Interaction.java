package it.crm.bd.model.domain;

import java.sql.Time;
import java.time.LocalDate;

public class Interaction {
    private LocalDate date;
    private  Time time;
    private  String customer;
    private  String Offer;
    private String operator;

    public Interaction(LocalDate date, Time time, String customer, String offer,String operator) {
        this.date = date;
        this.time = time;
        this.customer = customer;
        this.Offer = offer;
        this.operator = operator;
    }
    public String getOperator() {return operator;}
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

    public String getOffer() {return Offer;}
    public void setOffer(String offer) {this.Offer = offer;}

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
