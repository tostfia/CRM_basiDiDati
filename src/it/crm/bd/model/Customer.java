package it.crm.bd.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String fiscalCode;
    private List<String> phones;
    private String address;
    private String city;
    private String cap;
    private List<String> emails;
    private LocalDate registrationDate;
    private String segreteria;

    // Construttore di default
    public Customer() {
        this.phones = new ArrayList<>();
        this.emails = new ArrayList<>();
    }
    // Construttore con parametri
    public Customer(String name, String surname, LocalDate birthdate, String fiscalCode,
                    List<String> phones, String address, String city, String cap,
                    List<String> emails, String segreteria) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.fiscalCode = fiscalCode;
        this.phones = phones != null ? phones : new ArrayList<>();
        this.address = address;
        this.city = city;
        this.cap = cap;
        this.emails = emails != null ? emails : new ArrayList<>();
        this.segreteria = segreteria;
    }

    // Metodo per indirizzi
    public Customer(String fiscalCode, String address, String city, String cap) {
        this.fiscalCode = fiscalCode;
        this.address = address;
        this.city = city;
        this.cap = cap;
    }


    // Getter e Setter
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
    public LocalDate getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    public String getFiscalCode() {
        return fiscalCode;
    }
    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }
    public List<String> getPhones() {
        return phones;
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
    public void setCap(String cap) {this.cap = cap;}
    public List<String> getEmails() {return emails;}
    public void setRegistrationDate(LocalDate registrationDate) {this.registrationDate = registrationDate;}
    public String getSegreteria() {return segreteria;}
    public void setSegreteria(String segreteria) {this.segreteria = segreteria;}

    // toString per la stampa dell'oggetto
    @Override
    public String toString() {
        return "\n--------------"+ name+ surname+ "'s data--------------" +
                "\nName: " + name +
                "\nSurname: " + surname +
                "\nBirthdate: " + birthdate +
                "\nFiscal Code: " + fiscalCode +
                "\nPhones: " + phones +
                "\nAddress: " + address +
                "\nCity: " + city +
                "\nCAP: " + cap +
                "\nEmails: " + emails +
                "\nRegistration Date: " + registrationDate +
                "\nWho entered this customer :" + segreteria;
    }


}
