package l2.poo3.model;

import l2.poo3.model.Enum.Pcolor;
import l2.poo3.model.Enum.Resources;

import java.util.HashMap;
import java.util.Map;

public abstract class PlayerModel {

    private final Pcolor color;
    private final Map<Resources, Integer> resources = new HashMap<>();

    private int pointDeVic = 0;


    protected PlayerModel(Pcolor color) {
        this.color = color;
        initResources();
    }

    private void initResources(){
        resources.put(Resources.BOIS, 4);
        resources.put(Resources.BLE, 2);
        resources.put(Resources.ARGILE, 4);
        resources.put(Resources.MINERAI, 0);
        resources.put(Resources.MOUTON, 2);
    }

    public Map<Resources, Integer> getResources() {
        return resources;
    }

    public final Pcolor getColor() {
        return color;
    }

    public void setPointDeVic(int pointDeVic) {
        this.pointDeVic = pointDeVic;
    }

    public int getPointDeVic() {
        return pointDeVic;
    }

    public final boolean updateResources(Resources res, int value){

        resources.replace(res, value);
        if(resources.get(res) == value){
            return true;
        }

        return false;
    }

    public final String getStringResources(){
        StringBuilder to_return = new StringBuilder();
        to_return.append("Ressources:");
        for(var res : resources.entrySet()){
            to_return.append(res.getKey() + ": " + res.getValue() + "\n");
        }
        return to_return.toString();
    }

    public final String toString(){
        return "\nJouer " + color + "\n" + getStringResources();
    }

}
