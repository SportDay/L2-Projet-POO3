package l2.poo3.model;

import l2.poo3.model.Enum.Resources;

import java.util.Random;

public abstract class CaseModel {

    private final Resources nature;
    private final int number;
    private boolean thief;

    protected CaseModel(Resources nature, boolean thief) {
        this.nature = nature;
        this.number = new Random().nextInt(12-2) + 2;
        this.thief = thief;
    }

    public CaseModel(Resources nature) {
        this(nature, false);
    }

    public final Resources getNature() {
        return nature;
    }

    public final int getNumber() {
        return number;
    }

    public final boolean thiefPresent() {
        return thief;
    }

    public final void setThief(boolean thief){
        this.thief = thief;
    }

    public final String toString(){
        return "Terrains: " + nature + " Numero: " + number;
    }


}
