package l2.poo3.model;

import l2.poo3.model.Enum.CartesDev;
import l2.poo3.model.Enum.Pcolor;
import l2.poo3.model.Enum.Resources;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class PlayerModel {

    private final Pcolor color;
    private final Map<Resources, Integer> resources = new LinkedHashMap<>();
    private final Map<CartesDev, Integer> cartesDev = new LinkedHashMap<>();

    private boolean throwDice = false, thiefPlay = false, biggestRoad = false, moreKnight = false;

    private int pointDeVic = 0, nbrRessources = 0, nbrRoad = 0, nbrKnight = 0, invPVic = 0;

    protected PlayerModel(Pcolor color) {
        this.color = color;
        initResources();
        cartesDev.put(CartesDev.Chevalier, 0);
        cartesDev.put(CartesDev.Monopole, 0);
        cartesDev.put(CartesDev.Route, 0);
        cartesDev.put(CartesDev.Invention, 0);
        cartesDev.put(CartesDev.PointVictoire, 0);
    }

    public boolean isBiggestRoad() {
        return biggestRoad;
    }

    public void setBiggestRoad(boolean biggestRoad) {
        this.biggestRoad = biggestRoad;
    }

    public boolean isMoreKnight() {
        return moreKnight;
    }

    public void setMoreKnight(boolean moreKnight) {
        this.moreKnight = moreKnight;
    }

    public boolean isThiefPlay() {
        return thiefPlay;
    }

    public void setThiefPlay(boolean thiefPlay) {
        this.thiefPlay = thiefPlay;
    }

    public boolean isThrowDice() {
        return throwDice;
    }

    public void setThrowDice(boolean throwDice) {
        this.throwDice = throwDice;
    }

    public Map<CartesDev, Integer> getCartesDev() {
        return cartesDev;
    }

    private void initResources(){
        resources.put(Resources.BOIS, 4);
        resources.put(Resources.BLE, 2);
        resources.put(Resources.ARGILE, 4);
        resources.put(Resources.MINERAI, 0);
        resources.put(Resources.MOUTON, 2);
    }

    public int getNbrRoad() {
        return nbrRoad;
    }

    public void setNbrRoad(int nbrRoad) {
        this.nbrRoad = nbrRoad;
    }

    public int getNbrKnight() {
        return nbrKnight;
    }

    public void increaseNbrKnight() {
        this.nbrKnight++;
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

    public int getInvPVic() {
        return invPVic;
    }

    public void setInvPVic(int invPVic) {
        this.invPVic = invPVic;
    }

    public int getPoVicFinal(){
        return pointDeVic + invPVic;
    }

    public int getNbrRessources() {
        return nbrRessources;
    }

    public final void setResources(Resources res, int value){

        resources.replace(res, value);
        updateRessources();
    }

    public final void updateCartes(CartesDev carte, boolean add){
        int oldVal = cartesDev.get(carte);
        if(add){
            cartesDev.put(carte, oldVal+1);
        }else {
            cartesDev.put(carte, oldVal-1);
        }
    }

    public final String getStringResources(){
        StringBuilder to_return = new StringBuilder();
        to_return.append("Ressources:");
        for(var res : resources.entrySet()){
            to_return.append(res.getKey() + ": " + res.getValue() + "\n");
        }
        return to_return.toString();
    }

    public void updateRessources(){
        int maxCartes = 0;
        for(int i: resources.values()){
            maxCartes += i;
        }
        nbrRessources = maxCartes;
    }

    public void debug(){
        for(Map.Entry<Resources, Integer> t : resources.entrySet()){
            resources.put(t.getKey(), 2);
        }
        for(Map.Entry<CartesDev, Integer> t : cartesDev.entrySet()){
            cartesDev.put(t.getKey(), 3);
        }
    }

    public final String toString(){
        return "\nJouer " + color + "\n" + getStringResources();
    }

}
