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
    //Construttore email e telefono
    public Customer(String fiscalCode,String type,String value){
        this.fiscalCode = fiscalCode;
        if(type.equals("phone")){
            this.phones = new ArrayList<>();
            this.phones.add(value);
        }else if(type.equals("email")){
            this.emails = new ArrayList<>();
            this.emails.add(value);
        }
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
    public String getType() {
        boolean hasPhone = phones != null && !phones.isEmpty();
        boolean hasEmail = emails != null && !emails.isEmpty();

        if (hasPhone && hasEmail) {
            return "phone, email";  // Se entrambi i contatti sono presenti
        } else if (hasPhone) {
            return "phone";  // Se solo il telefono è presente
        } else if (hasEmail) {
            return "email";  // Se solo l'email è presente
        }
        return null;  // Nessun contatto presente
    }

    public List<String> getValues() {
        List<String> values = new ArrayList<>();

        // Aggiungi i numeri di telefono se presenti
        if (phones != null && !phones.isEmpty()) {
            values.addAll(phones); // Aggiunge tutti i numeri di telefono alla lista
        }

        // Aggiungi le email se presenti
        if (emails != null && !emails.isEmpty()) {
            values.addAll(emails); // Aggiunge tutte le email alla lista
        }
        return values; // Restituisce la lista di contatti
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
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    // toString per la stampa dell'oggetto
    @Override
    public String toString() {
        return "--------------"+ name+ surname+ "'s data--------------" +
                "\n |Fiscal code: " + fiscalCode +"|" +
                "\n |Birthdate: " + birthdate +"|" +
                "\n |Registration date: " + registrationDate + "|" +
                "\n |Phones: " + phones + "|" +
                "\n |Emails: " + emails + "|" +
                "\n |Address: " + address + "|" +
                "\n |City: " + city + "|" +
                "\n |CAP: " + cap + "|";
    }


}
