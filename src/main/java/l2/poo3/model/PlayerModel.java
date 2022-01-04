package l2.poo3.model;

import l2.poo3.model.Enum.CartesDev;
import l2.poo3.model.Enum.Pcolor;
import l2.poo3.model.Enum.Resources;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class PlayerModel {

    private final Pcolor color;
    private final Map<Resources, Integer> resources = new LinkedHashMap<>();
    private final Map<CartesDev, Integer> cartesDev = new LinkedHashMap<>();

    private boolean throwDice = false, thiefPlay = false, largestRoadP = false, moreKnight = false;

    private int pointDeVic = 0, nbrRessources = 0, largestRoad = 0, nbrKnight = 0, invPVic = 0, nbrBat = 0, nbrCol = 0, nbrVil = 0;

    protected PlayerModel(Pcolor color) {
        this.color = color;
        initResources();
        cartesDev.put(CartesDev.Chevalier, 0);
        cartesDev.put(CartesDev.Monopole, 0);
        cartesDev.put(CartesDev.Route, 0);
        cartesDev.put(CartesDev.Invention, 0);
        cartesDev.put(CartesDev.PointVictoire, 0);
    }

    public boolean isLargestRoad() {
        return largestRoadP;
    }

    public int getNbrBat(){
        return nbrCol+nbrVil;
    }

    public int getNbrCol() {
        return nbrCol;
    }

    public int getNbrVil() {
        return nbrVil;
    }

    public void addNbrCol() {
        nbrCol++;
    }

    public void addNbrVille() {
        nbrCol--;
        nbrVil++;
    }

    public void setLargestRoad(boolean largestRoadP) {
        this.largestRoadP = largestRoadP;
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
        resources.put(Resources.BOIS, 10);
        resources.put(Resources.BLE, 10);
        resources.put(Resources.ARGILE,10);
        resources.put(Resources.MINERAI, 10);
        resources.put(Resources.MOUTON, 10);
    }

    public int getLargestRoad() {
        return largestRoad;
    }

    public void setLargestRoad(int nbrRoad) {
        this.largestRoad = nbrRoad;
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
        if(pointDeVic < 0){
            pointDeVic = 0;
        }
        this.pointDeVic = pointDeVic;
    }

    public int getPointDeVic() {
        return pointDeVic;
    }

    public int getInvPVic() {
        return invPVic;
    }

    public void setInvPVic(int invPVic) {
        if(invPVic < 0){
            invPVic = 0;
        }
        this.invPVic = invPVic;
    }

    public int getPoVicReal(){
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
    
    public final String toString(){
        return color + "";
    }

}
