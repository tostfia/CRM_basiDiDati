package it.crm.bd.model.domain;

public class Contact {
    private String type;
    private String value;
    private String customer;

    public Contact() {}
    public Contact(String type, String value, String customer) {
        this.type = type;
        this.value = value;
        this.customer = customer;
    }
    public void setValue(String value) {this.value = value;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public String getValue() {return value;}
    public void setCustomer(String customer) {this.customer = customer;}
    public String getFiscalCode() {return customer;}


    @Override
    public String toString() {
        return "\n-------------------Contact-------------------" +
                "\n |Type: " + type + "|" +
                "\n |Value: " + value + "|" +
                "\n |Customer: " + customer + "|";


    }
}
