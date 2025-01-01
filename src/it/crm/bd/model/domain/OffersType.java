package it.crm.bd.model.domain;

public enum OffersType {
    PROMOTIONAL(1),
    DISCOUNT(2),
    GIFT(3),
    OTHER(4);
    private int id;
    OffersType(int id) {
        this.id = id;
    }
    public static OffersType fromInt(int id) {
        return switch (id) {
            case 1 -> PROMOTIONAL;
            case 2 -> DISCOUNT;
            case 3 -> GIFT;
            default -> OTHER;
        };
    }
    public int getId() {
        return id;
    }
}
