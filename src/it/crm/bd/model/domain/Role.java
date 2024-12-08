package it.crm.bd.model.domain;

public enum Role {
    AMMINISTRATORE(1),
    SEGRETERIA(2),
    OPERATORE(3);
    private final int id;

    private Role(int id) {
        this.id = id;
    }

    public static Role fromInt(int id) {
        for (Role type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }
}

