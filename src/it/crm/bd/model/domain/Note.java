package it.crm.bd.model.domain;

public class Note {
    private String customer;
    private Boolean outcome;
    private String description;
    public Note(Boolean outcome, String description, String customer) {
        this.outcome = outcome;
        this.description = description;
        this.customer = customer;
    }
    public Boolean getOutcome() {
        return outcome;
    }
    public void setOutcome(Boolean outcome) {
        this.outcome = outcome;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    @Override
    public String toString() {
        return "Note{" +
                "customer='" + customer + '\'' +
                ", outcome=" + outcome +
                ", description='" + description + '\'' +
                '}';
    }
}
