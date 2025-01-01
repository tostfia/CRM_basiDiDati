package it.crm.bd.model.domain;

public class Note {
    private Boolean outcome;
    private String description;
    public Note(Boolean outcome, String description) {
        this.outcome = outcome;
        this.description = description;
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
    @Override
    public String toString() {
        return "Note{" +
                "outcome=" + (Boolean.TRUE.equals(outcome) ? "offer accepted":"offer denied") +
                ", description='" + description + '\'' +
                '}';
    }
}
