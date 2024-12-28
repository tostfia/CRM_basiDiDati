package it.crm.bd.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
    private String name;
    private String surname;
    private Date birthdate;
    private String cf;
    private List<String> phones = new ArrayList<>();
    private String address;
    private String city;
    private String cap;
    private List<String> emails = new ArrayList<>();
    private Date registrationDate;
    public Customer() {}

    // Construttore con dati essenziali
    public Customer(String name, String surname, Date birthdate, String cf) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.cf = cf;
    }

    // Construttore con dettagli di contatto
    public Customer(List<String> phones, List<String> emails, Date registrationDate) {
        this.phones = phones;
        this.emails = emails;
        this.registrationDate = registrationDate;
    }

    // Construttore con indirizzo
    public Customer(String address, String city, String cap) {
        this.address = address;
        this.city = city;
        this.cap = cap;
    }

    // Metodi getter e setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    // Metodo toString per stampare i dettagli
    @Override
    public String toString() {
        return "Customer{name='" + name + "', surname='" + surname + "', birthdate=" + birthdate +
                ", cf='" + cf + "', phones=" + phones + ", address='" + address + "', city='" + city +
                "', cap='" + cap + "', emails=" + emails + ", registrationDate=" + registrationDate + "}";
    }
}

