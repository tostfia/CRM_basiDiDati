package it.crm.bd.model;

public class ReportCustomer {
    private String fiscalCode;
    private String name;
    private String surname;
    private int interactions;
    private int acceptedOffers;
    private String acceptedOffersType;
    public ReportCustomer() {}
    public ReportCustomer(String fiscalCode, String name, String surname, int interactions, int acceptedOffers) {
        this.fiscalCode = fiscalCode;
        this.name = name;
        this.surname = surname;
        this.interactions = interactions;
        this.acceptedOffers = acceptedOffers;
    }
    public void setFiscalCode(String fiscalCode) {this.fiscalCode = fiscalCode;}
    public void setName(String name) {this.name = name;}
    public void setSurname(String surname) {this.surname = surname;}
    public void setInteractions(int interactions) {this.interactions = interactions;}
    public void setAcceptedOffers(int acceptedOffers) {this.acceptedOffers = acceptedOffers;}
    public void setAcceptedOffersType(String acceptedOffersType) {this.acceptedOffersType = acceptedOffersType;}
    @Override
    public String toString() {
        return "\n-------------------ReportCustomer-------------------" +
                "\n |Fiscal code: " + fiscalCode + "|" +
                "\n |Name: " + name + "|" +
                "\n |Surname: " + surname + "|" +
                "\n |Interactions: " + interactions + "|" +
                "\n |Accepted offers: " + acceptedOffers + "|"+
                "\n |Accepted offers type: " + acceptedOffersType + "|";
    }
}
