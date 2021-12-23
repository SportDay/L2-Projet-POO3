package l2.poo3.model.Enum;

public enum Resources {
    DESERTS,
    BOIS,
    ARGILE,
    MINERAI,
    MOUTON,
    BLE;

    @Override
    public String toString() {
        return super.toString().charAt(0) + super.toString().substring(1).toLowerCase();
    }
}
