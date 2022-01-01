package l2.poo3.model.Enum;

public enum Case {
    DESERTS,
    FORET,
    PRE,
    CHAMPS,
    COLLINE,
    MONTAGNE;

    @Override
    public String toString() {
        return super.toString().charAt(0) + super.toString().substring(1).toLowerCase();
    }
}
