package l2.poo3.model.Enum;

public enum Pcolor {
    RED,
    GREEN,
    YELLOW,
    BLUE;

    @Override
    public String toString() {
        return super.toString().charAt(0)+super.toString().substring(1).toLowerCase();
    }
}
