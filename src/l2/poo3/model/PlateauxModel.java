package l2.poo3.model;

import l2.poo3.model.CaseType.*;

import java.util.Random;

public class PlateauxModel {

    private CaseModel[][] plateaux;
    private int length_x, length_y;

    public int getLength_x() {
        return length_x;
    }

    public int getLength_y() {
        return length_y;
    }

    public PlateauxModel(int x, int y){
        length_x = 2*x-1;
        length_y = 2*y-1;
        plateaux = new CaseModel[2*y-1][2*x-1];
        construirePlateaux();
    }

    public CaseModel[][] getPlateaux() {
        return plateaux;
    }

    public void construirePlateaux(){
        boolean setThief = false;
        for(int y = 0; y < plateaux.length; y++){
            if(y % 2 != 0){
                for (int x = 0; x < plateaux[y].length; x++) {
                    if(x % 2 == 0) {
                        plateaux[y][x] = new Route();
                    }else {
                        plateaux[y][x] = new Batiment();
                    }
                }
            }else {
                for (int x = 0; x < plateaux[y].length; x++) {
                    if (x % 2 != 0) {
                        plateaux[y][x] = new Route();
                    } else if (y >= (length_y)/2  && x >= (length_x)/2 && !setThief) {
                        plateaux[y][x] = new Deserts();
                        setThief = true;
                    } else {
                        int randomCase = new Random().nextInt(5);
                        switch (randomCase) {
                            case 0:
                                plateaux[y][x] = new Forests();
                                break;
                            case 1:
                                plateaux[y][x] = new Hills();
                                break;
                            case 2:
                                plateaux[y][x] = new Mountain();
                                break;
                            case 3:
                                plateaux[y][x] = new Pastures();
                                break;
                            case 4:
                                plateaux[y][x] = new Wheat();
                                break;
                        }
                    }
                }
            }
        }
    }



}
