package l2.poo3.model;

import l2.poo3.model.Enum.Resources;

import java.util.Random;

public abstract class CaseModel {

    private final Resources nature;
    private final String name;
    private final int number;
    private boolean thief;

    public String getName() {
        return name;
    }

    protected CaseModel(Resources nature, boolean thief) {
        this.nature = nature;
        this.number = new Random(System.nanoTime()*System.currentTimeMillis()).nextInt(11) + 2;
        this.thief = thief;
        this.name = nature.toString().charAt(0) + nature.toString().substring(1).toLowerCase();
    }

    protected CaseModel(String name) {
        this.name = name;
        nature = null;
        number = -1;
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
