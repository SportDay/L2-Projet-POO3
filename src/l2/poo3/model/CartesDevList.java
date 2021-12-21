package l2.poo3.model;

import l2.poo3.model.Enum.CartesDev;

import java.util.ArrayList;
import java.util.Random;

public class CartesDevList {

    private final ArrayList<CartesDev> list = new ArrayList<>();

    public CartesDevList() {
        generate();
    }

    public ArrayList<CartesDev> getList() {
        return list;
    }

    public void generate(){
        int nbrChevalier = 0;
        int nbrProgres = 0;
        int nbrPV = 0;

        while ((nbrChevalier+nbrPV+nbrProgres) < 25){
            int r = new Random(System.nanoTime()*System.currentTimeMillis()).nextInt(3);
            switch (r){
                case 0: if(nbrChevalier <= 14){
                            list.add(CartesDev.Chevalier);
                            nbrChevalier++;
                        }
                        break;
                case 1: if(nbrProgres <= 6){
                            list.add(CartesDev.Progres);
                            nbrProgres++;
                        }
                        break;
                case 2: if(nbrPV <= 5){
                            list.add(CartesDev.PointVictoire);
                            nbrPV++;
                        }
                        break;
            }
        }
    }
}
