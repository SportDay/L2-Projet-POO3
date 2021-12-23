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
        int nbrMonop = 0;
        int nbrRoute = 0;
        int nbrInvent = 0;
        int nbrPV = 0;

        while ((nbrChevalier+nbrPV+nbrInvent+nbrMonop+nbrRoute) < 25){
            int r = new Random(System.nanoTime()*System.currentTimeMillis()).nextInt(5);
            switch (r){
                case 0: if(nbrChevalier <= 14){
                            list.add(CartesDev.Chevalier);
                            nbrChevalier++;
                        }
                        break;
                case 1: if(nbrMonop <= 2){
                            list.add(CartesDev.Monopole);
                            nbrMonop++;
                        }
                        break;
                case 2: if(nbrRoute <= 2){
                            list.add(CartesDev.Route);
                            nbrRoute++;
                        }
                        break;
                case 3: if(nbrInvent <= 2){
                            list.add(CartesDev.Invention);
                            nbrInvent++;
                        }
                        break;
                case 4: if(nbrPV <= 5){
                            list.add(CartesDev.PointVictoire);
                            nbrPV++;
                        }
                        break;
            }
        }
    }
}
