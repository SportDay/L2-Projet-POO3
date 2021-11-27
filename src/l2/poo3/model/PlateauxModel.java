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

    public void construirePlateaux() {
        boolean setThief = false;
        buildPort();
        for (int y = 0; y < plateaux.length; y++) {
        if (y % 2 != 0) {
                for (int x = 0; x < plateaux[y].length; x++) {
                    if (plateaux[y][x] == null) {
                        if (x % 2 == 0 && x != 0 && x != length_x - 1) {
                            plateaux[y][x] = new Route();
                        } else if (x != 0 && x != length_x - 1) {
                            plateaux[y][x] = new Batiment();
                        }
                    }
                }
            } else {
                for (int x = 0; x < plateaux[y].length; x++) {
                    if (plateaux[y][x] == null) {
                        if (x % 2 != 0) {
                            plateaux[y][x] = new Route();
                        } else if (y >= (length_y) / 2 && x >= (length_x) / 2 && !setThief) {
                            plateaux[y][x] = new Deserts();
                            setThief = true;
                        } else if (x != 0 && x != length_x - 1) {
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


    private void buildPort(){
        int xNextPortFirstLine = 2;
        int xNextPortLastLine = 2;
        int yNextPortFirstLine = 4;
        int yNextPortLastLine = 4;

        int normalLenghtX = ((length_x - 3) / 2);
        int normalLenghtY = ((length_y - 3) / 2);

        if((normalLenghtX % 2 == 0 && normalLenghtY % 2 == 0) || (normalLenghtX % 2 == 1 && normalLenghtY % 2 != 1)){
            xNextPortLastLine = 4;
        }
        if((normalLenghtX % 2 == 0 && normalLenghtY % 2 == 0) || (normalLenghtX % 2 != 1 && normalLenghtY % 2 == 1)){
            yNextPortLastLine = 2;
        }

        for(int x = 0; x < plateaux[0].length; x++) {
            if (x % 2 == 0 && x == xNextPortFirstLine && x != length_x - 1) {
                plateaux[0][x] = new Port();
                xNextPortFirstLine += 4;
            }
            if (x % 2 == 0 && x == xNextPortLastLine && x != length_x - 1) {
                plateaux[length_y-1][x] = new Port();
                xNextPortLastLine += 4;
            }
            if(plateaux[0][x] == null){
                plateaux[0][x] = new Vide();
            }
            if(plateaux[length_y-1][x] == null){
                plateaux[length_y-1][x] = new Vide();
            }
        }

        for(int y = 0; y < plateaux.length; y++) {
            if (y % 2 == 0 && y == yNextPortFirstLine && y != length_y - 1) {
                plateaux[y][0] = new Port();
                yNextPortFirstLine += 4;
            }
            if (y % 2 == 0 && y == yNextPortLastLine && y != length_y - 1) {
                plateaux[y][length_x-1] = new Port();
                yNextPortLastLine += 4;
            }
            if(plateaux[y][0] == null){
                plateaux[y][0] = new Vide();
            }
            if(plateaux[y][length_x-1] == null){
                plateaux[y][length_x-1] = new Vide();
            }
        }


    }
}
