package it.crm.bd.model.domain;

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

    // Construttore di default
    public Customer() {
        this.phones = new ArrayList<>();
        this.emails = new ArrayList<>();
    }

    // Construttore con parametri
    public Customer(String name, String surname, LocalDate birthdate, String fiscalCode,
                    List<String> phones, String address, String city, String cap,
                    List<String> emails, LocalDate registrationDate) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.fiscalCode = fiscalCode;
        this.phones = phones != null ? phones : new ArrayList<>();
        this.address = address;
        this.city = city;
        this.cap = cap;
        this.emails = emails != null ? emails : new ArrayList<>();
        this.registrationDate = registrationDate;
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

    public void setPhones(List<String> phones) {
        this.phones = phones != null ? phones : new ArrayList<>();
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
        if (cap.matches("\\d{5}")) {
            this.cap = cap;
        } else {
            throw new IllegalArgumentException("CAP must be a 5-digit number.");
        }
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails != null ? emails : new ArrayList<>();
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    // toString per la stampa dell'oggetto
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", fiscalCode='" + fiscalCode + '\'' +
                ", phones=" + phones +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", cap='" + cap + '\'' +
                ", emails=" + emails +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
