package l2.poo3.model;

import l2.poo3.model.Enum.Case;
import l2.poo3.model.Enum.Resources;

import java.util.Random;

public abstract class CaseModel {

    private Resources nature;
    private Case type;

    private String name;
    private final int number;
    private boolean thief;

    protected CaseModel(Case type, Resources nature, boolean thief) {
        this.nature = nature;
        this.number = new Random(System.nanoTime()*System.currentTimeMillis()).nextInt(11) + 2;
        this.thief = thief;
        this.name = type.toString().charAt(0) + type.toString().substring(1).toLowerCase();
        this.type = type;
    }

    protected CaseModel(String name) {
        this.name = name;
        nature = null;
        number = -1;
    }

    public CaseModel(Resources nature, String name, int number) {
        this.nature = nature;
        this.name = name;
        this.number = number;
    }

    public CaseModel(Case type, Resources nature) {
        this(type, nature, false);
    }

    public final Resources getNature() {
        return nature;
    }

    public void setNature(Resources nature) {
        this.nature = nature;
    }

    public final int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
