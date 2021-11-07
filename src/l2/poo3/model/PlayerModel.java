package l2.poo3.model;

import l2.poo3.model.Enum.Pcolor;
import l2.poo3.model.Enum.Resources;

import java.util.HashMap;
import java.util.Map;

public abstract class PlayerModel {

    private final boolean ai;
    private final Pcolor color;
    private final Map<Resources, Integer> resources = new HashMap<>();

    protected PlayerModel(Pcolor color, boolean ai) {
        this.ai = ai;
        this.color = color;
        initResources();
    }

    protected PlayerModel(Pcolor color) {
        this(color, false);
    }

    private void initResources(){
        resources.put(Resources.BOIS, 0);
        resources.put(Resources.BLE, 0);
        resources.put(Resources.ARGILE, 0);
        resources.put(Resources.MINERAI, 0);
        resources.put(Resources.MOUTON, 0);
    }

    public Map<Resources, Integer> getResources() {
        return resources;
    }

    public final boolean isAi() {
        return ai;
    }

    public final Pcolor getColor() {
        return color;
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
