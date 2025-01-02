package it.crm.bd.model.domain;

public class Offer {
    private OffersType type;
    private String description;

    public Offer(OffersType type, String description) {
        this.type = type;
        this.description = description;
    }

    public OffersType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
    public void setType(OffersType type) {
        this.type = type;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
