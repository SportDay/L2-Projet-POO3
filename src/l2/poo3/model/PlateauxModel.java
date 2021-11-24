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

    public PlateauxModel(){}

    public void initPlateaux(int x, int y){
        if(y > 11){
            y = 11;
        }
        if(x > 3446){
            x = 3446;
        }
        if(y < 1){
            y = 4;
        }
        if(x < 1){
            x = 4;
        }
        length_x = 2*x+3;
        length_y = 2*y+3;
        plateaux = new CaseModel[length_y][length_x];
        construirePlateaux();
    }

    public CaseModel[][] getPlateaux() {
        return plateaux;
    }

    public void initCorner(){
        if(plateaux != null){
            plateaux[1][1] = new Vide();
            plateaux[1][length_x-2] = new Vide();
            plateaux[length_y-2][1] = new Vide();
            plateaux[length_y-2][length_x-2] = new Vide();
        }
    }


    public void construirePlateaux(){
        boolean setThief = false;
        initCorner();
        for(int y = 0; y < plateaux.length; y++){
            if(y == 0 || y == length_y-1){
                for (int x = 0; x < plateaux[y].length; x++) {
                    if (plateaux[y][x] == null) {
                        plateaux[y][x] = new Vide();
                    }
                }
            }else if(y % 2 != 0){
                for (int x = 0; x < plateaux[y].length; x++) {
                    if(plateaux[y][x] == null) {
                        if (x % 2 == 0 && x != 0 && x != length_x-1) {
                            plateaux[y][x] = new Route();
                        } else if(x != 0 && x != length_x-1) {
                            plateaux[y][x] = new Batiment();
                        }else{
                            plateaux[y][x] = new Vide();
                        }
                    }
                }
            }else {
                for (int x = 0; x < plateaux[y].length; x++) {
                    if (plateaux[y][x] == null) {
                        if(x == 0 || x == length_x-1){
                            plateaux[y][x] = new Vide();
                        }else if (x % 2 != 0 ) {
                            plateaux[y][x] = new Route();
                        } else if (y >= (length_y) / 2 && x >= (length_x) / 2 && !setThief) {
                            plateaux[y][x] = new Deserts();

                            plateaux[y][0] = new Port();
                            plateaux[y][length_x-1] = new Port();

                            plateaux[0][x] = new Port();
                            plateaux[length_y-1][x] = new Port();

                            setThief = true;
                        } else if(x != 0 && x != length_x-1){
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
                        }else{
                            plateaux[y][x] = new Vide();
                        }
                    }
                }
            }
        }
    }



}
