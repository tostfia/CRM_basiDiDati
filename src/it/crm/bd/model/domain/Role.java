package it.crm.bd.model.domain;

public enum Role {
    SEGRETERIA(1),
    OPERATORE(2),
    NON_RICONOSCIUTO(3);//valore di fallback
    private int id;

    Role(int id) {
        this.id = id;
    }

    public static Role fromInt(int id) {
        return switch (id) {
            case 1 -> SEGRETERIA;
            case 2 -> OPERATORE;
            default -> NON_RICONOSCIUTO;
        };
    }

    public int getId() {
        return id;
    }
}

