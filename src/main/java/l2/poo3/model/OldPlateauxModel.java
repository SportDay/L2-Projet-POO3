package l2.poo3.model;

import l2.poo3.model.CaseType.*;
import l2.poo3.model.Enum.Resources;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class OldPlateauxModel {

    private CaseModel[][] plateaux;
    private int length_x, length_y, carteDevPos = 0, thiefX, thiefY;


    private final CartesDevList cartesDevList = new CartesDevList();

    public int getLength_x() {
        return length_x;
    }

    public CartesDevList getCartesDevList() {
        return cartesDevList;
    }

    public int getCarteDevPos() {
        return carteDevPos;
    }

    public void setCarteDevPos(int carteDevPos) {
        this.carteDevPos = carteDevPos;
    }

    public int getLength_y() {
        return length_y;
    }

    public OldPlateauxModel(){}

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
                            thiefX = x;
                            thiefY = y;
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

    public void generateRessources(int number) {
        for(int y = 0; y < plateaux.length; y++){
            if(y % 2 == 0) {
                for (int x = 0; x < plateaux[y].length; x++) {
                    if (plateaux[y][x] != null) {
                        CaseModel cas = plateaux[y][x];
                        if (x % 2 == 0 && !cas.thiefPresent()) {
                            if(!(cas.getNumber() < 0) && !(cas instanceof Port) && cas.getNature() != null){
                                Resources res = cas.getNature();
                                int num = cas.getNumber();
                                if(num == number){
                                    if (x + 1 <= getLength_x() - 1 && y + 1 <= getLength_y() - 1 && plateaux[y + 1][x + 1] instanceof Batiment && ((Batiment) plateaux[y + 1][x + 1]).getPlayer() != null) {
                                        PlayerModel player = ((Batiment) plateaux[y + 1][x + 1]).getPlayer();
                                        System.out.println("Generate 1 " + player.getColor() + " " + res);
                                        if(plateaux[y + 1][x + 1].getName().contains("V")){
                                            player.setResources(res,player.getResources().get(res) + 2);
                                        }else {
                                            player.setResources(res,player.getResources().get(res) + 1);
                                        }
                                    } else if (x - 1 >= 0 && y - 1 >= 0 && plateaux[y - 1][x - 1] instanceof Batiment && ((Batiment) plateaux[y - 1][x - 1]).getPlayer() != null) {
                                        PlayerModel player = ((Batiment) plateaux[y - 1][x - 1]).getPlayer();
                                        System.out.println("Generate 2 " + player.getColor() + " " + res);
                                        if(plateaux[y - 1][x - 1].getName().contains("V")){
                                            player.setResources(res,player.getResources().get(res) + 2);
                                        }else {
                                            player.setResources(res,player.getResources().get(res) + 1);
                                        }
                                    } else if (y - 1 >= 0 && x + 1 <= getLength_x() - 1 && plateaux[y - 1][x + 1] instanceof Batiment && ((Batiment) plateaux[y - 1][x + 1]).getPlayer() != null) {
                                        PlayerModel player = ((Batiment) plateaux[y - 1][x + 1]).getPlayer();
                                        System.out.println("Generate 3 " + player.getColor() + " " + res);

                                        if(plateaux[y - 1][x + 1].getName().contains("V")){
                                            player.setResources(res,player.getResources().get(res) + 2);
                                        }else {
                                            player.setResources(res,player.getResources().get(res) + 1);
                                        }
                                    } else if (y + 1 <= getLength_y() - 1 && x - 1 >= 0 && plateaux[y + 1][x - 1] instanceof Batiment && ((Batiment) plateaux[y + 1][x - 1]).getPlayer() != null) {
                                        PlayerModel player = ((Batiment) plateaux[y + 1][x - 1]).getPlayer();
                                        System.out.println("Generate 4 " + player.getColor() + " " + res);

                                        if(plateaux[y + 1][x - 1].getName().contains("V")){
                                            player.setResources(res,player.getResources().get(res) + 2);
                                        }else {
                                            player.setResources(res,player.getResources().get(res) + 1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public String moveThief(int x, int y) {
        if(plateaux[y][x].thiefPresent()){
            return "Error thief present";
        }
        if(plateaux[y][x] instanceof Batiment || plateaux[y][x] instanceof Route || plateaux[y][x] instanceof Port){
            return "noneRessources";
        }
        if(plateaux[thiefY][thiefX].thiefPresent()){
            plateaux[thiefY][thiefX].setThief(false);
            plateaux[y][x].setThief(true);
            thiefX = x;
            thiefY = y;
            return "ok";
        }
        return "error";
    }

    public Resources stealOneRessources(int x, int y, PlayerModel playerThief) {
        Resources max = null;
        PlayerModel player = null;
        int tmp = 0;
        if (x + 1 <= getLength_x() - 1 && y + 1 <= getLength_y() - 1 && plateaux[y + 1][x + 1] instanceof Batiment && ((Batiment) plateaux[y + 1][x + 1]).getPlayer() != null && ((Batiment) plateaux[y + 1][x + 1]).getPlayer() != playerThief) {
            player = ((Batiment) plateaux[y + 1][x + 1]).getPlayer();
            for(Map.Entry<Resources, Integer> t : player.getResources().entrySet()){
                if(tmp < t.getValue()){
                    tmp = t.getValue();
                    max = t.getKey();
                }
            }
        } else if (x - 1 >= 0 && y - 1 >= 0 && plateaux[y - 1][x - 1] instanceof Batiment && ((Batiment) plateaux[y - 1][x - 1]).getPlayer() != null && ((Batiment) plateaux[y - 1][x - 1]).getPlayer() != playerThief) {
            player = ((Batiment) plateaux[y - 1][x - 1]).getPlayer();
            for(Map.Entry<Resources, Integer> t : player.getResources().entrySet()){
                if(tmp < t.getValue()){
                    tmp = t.getValue();
                    max = t.getKey();
                }
            }
        } else if (y - 1 >= 0 && x + 1 <= getLength_x() - 1 && plateaux[y - 1][x + 1] instanceof Batiment && ((Batiment) plateaux[y - 1][x + 1]).getPlayer() != null && ((Batiment) plateaux[y - 1][x + 1]).getPlayer() != playerThief) {
            player = ((Batiment) plateaux[y - 1][x + 1]).getPlayer();
            for(Map.Entry<Resources, Integer> t : player.getResources().entrySet()){
                if(tmp < t.getValue()){
                    tmp = t.getValue();
                    max = t.getKey();
                }
            }
        } else if (y + 1 <= getLength_y() - 1 && x - 1 >= 0 && plateaux[y + 1][x - 1] instanceof Batiment && ((Batiment) plateaux[y + 1][x - 1]).getPlayer() != null && ((Batiment) plateaux[y + 1][x - 1]).getPlayer() != playerThief) {
            player = ((Batiment) plateaux[y + 1][x - 1]).getPlayer();
            for(Map.Entry<Resources, Integer> t : player.getResources().entrySet()){
                if(tmp < t.getValue()){
                    tmp = t.getValue();
                    max = t.getKey();
                }
            }
        }
        if(player != null && max != null){
            player.setResources(max,player.getResources().get(max)-1);
            playerThief.setResources(max, player.getResources().get(max)+1);
            player.updateRessources();
            playerThief.updateRessources();
        }

        return max;
    }

    public int getLengthBiggestRoad(int x, int y, PlayerModel player){
        tmp = new ArrayList<Route>();
        int[] tmpCords =  findLastRoad(x,y, player, (Route) plateaux[y][x], (Route) plateaux[y][x], (Route) plateaux[y][x], (Route) plateaux[y][x], (Route) plateaux[y][x], (Route) plateaux[y][x], (Route) plateaux[y][x], (Route) plateaux[y][x]);
        if(tmpCords != null){
            x = tmpCords[0];
            y = tmpCords[1];
            tmp = new ArrayList<Route>();
            return checkRoad(x,y, player, (Route) plateaux[y][x], (Route) plateaux[y][x], (Route) plateaux[y][x], (Route) plateaux[y][x], (Route) plateaux[y][x], (Route) plateaux[y][x], (Route) plateaux[y][x], (Route) plateaux[y][x]);
        }
        return 0;
    }

    int[] lastRoadCords = new int[2];

    ArrayList<Route> tmp = new ArrayList<Route>();

    private int[] findLastRoad(int x, int y, PlayerModel player, Route father, Route oldFather, Route old1Father, Route old2Father, Route old3Father, Route old4Father, Route old5Father, Route old6Father){
        if(endRoad(x, y, player, father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father, old6Father)){
            lastRoadCords[0] = x;
            lastRoadCords[1] = y;
            return lastRoadCords;
        }

        if (x + 1 <= getLength_x() - 1 && y + 1 <= getLength_y() - 1 && plateaux[y + 1][x + 1] instanceof Route && ((Route) plateaux[y + 1][x + 1]).getPlayer() == player && plateaux[y + 1][x + 1] != father && plateaux[y + 1][x + 1] != oldFather && plateaux[y + 1][x + 1] != old1Father && plateaux[y + 1][x + 1] != old2Father && plateaux[y + 1][x + 1] != old3Father && plateaux[y + 1][x + 1] != old4Father && plateaux[y + 1][x + 1] != old5Father && plateaux[y + 1][x + 1] != old6Father) {
            if(!tmp.contains((Route) plateaux[y + 1][x + 1])) {
                tmp.add((Route) plateaux[y + 1][x + 1]);
                findLastRoad(x + 1, y + 1, player, (Route) plateaux[y + 1][x + 1], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
            }else {
                tmp.remove((Route) plateaux[y + 1][x + 1]);
            }
        }
        if (x - 1 >= 0 && y - 1 >= 0 && plateaux[y - 1][x - 1] instanceof Route && ((Route) plateaux[y - 1][x - 1]).getPlayer() == player && plateaux[y - 1][x - 1] != father && plateaux[y - 1][x - 1] != oldFather && plateaux[y - 1][x - 1] != old1Father && plateaux[y - 1][x - 1] != old2Father && plateaux[y - 1][x - 1] != old3Father && plateaux[y - 1][x - 1] != old4Father && plateaux[y - 1][x - 1] != old5Father && plateaux[y - 1][x - 1] != old6Father) {
            if(!tmp.contains((Route) plateaux[y - 1][x - 1])) {
                tmp.add((Route) plateaux[y - 1][x - 1]);
                findLastRoad(x - 1, y - 1, player, (Route) plateaux[y - 1][x - 1], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
            }else {
                tmp.remove((Route) plateaux[y - 1][x - 1]);
            }
        }
        if (y - 1 >= 0 && x + 1 <= getLength_x() - 1 && plateaux[y - 1][x + 1] instanceof Route && ((Route) plateaux[y - 1][x + 1]).getPlayer() == player && plateaux[y - 1][x + 1] != father && plateaux[y - 1][x + 1] != oldFather && plateaux[y - 1][x + 1] != old1Father && plateaux[y - 1][x + 1] != old2Father && plateaux[y - 1][x + 1] != old3Father && plateaux[y - 1][x + 1] != old4Father && plateaux[y - 1][x + 1] != old5Father && plateaux[y - 1][x + 1] != old6Father) {
            if(!tmp.contains((Route) plateaux[y - 1][x + 1])) {
                tmp.add((Route) plateaux[y - 1][x + 1]);
                findLastRoad(x + 1, y - 1, player, (Route) plateaux[y - 1][x + 1], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
            }else {
                tmp.remove((Route) plateaux[y - 1][x + 1]);
            }
        }
        if (y + 1 <= getLength_y() - 1 && x - 1 >= 0 && plateaux[y + 1][x - 1] instanceof Route && ((Route) plateaux[y + 1][x - 1]).getPlayer() == player && plateaux[y + 1][x - 1] != father && plateaux[y + 1][x - 1] != oldFather && plateaux[y + 1][x - 1] != old1Father && plateaux[y + 1][x - 1] != old2Father && plateaux[y + 1][x - 1] != old3Father && plateaux[y + 1][x - 1] != old4Father && plateaux[y + 1][x - 1] != old5Father && plateaux[y + 1][x - 1] != old6Father) {
            if(!tmp.contains((Route) plateaux[y + 1][x - 1])) {
                tmp.add((Route) plateaux[y + 1][x - 1]);
                findLastRoad(x - 1, y + 1, player, (Route) plateaux[y + 1][x - 1], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
            }else {
                tmp.remove((Route) plateaux[y + 1][x - 1]);
            }
        }

        if (y % 2 != 0){
            if (x + 2 <= getLength_x() - 1 && plateaux[y][x + 2] instanceof Route && ((Route) plateaux[y][x + 2]).getPlayer() == player && plateaux[y][x + 2]!= father && plateaux[y][x + 2] != oldFather && plateaux[y][x + 2] != old1Father && plateaux[y][x + 2] != old2Father && plateaux[y][x + 2] != old3Father && plateaux[y][x + 2] != old4Father && plateaux[y][x + 2] != old5Father && plateaux[y][x + 2] != old6Father) {
                if(!tmp.contains((Route) plateaux[y][x + 2])) {
                    tmp.add((Route) plateaux[y][x + 2]);
                    findLastRoad(x + 2, y, player, (Route) plateaux[y][x + 2], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
                }else {
                    tmp.remove((Route) plateaux[y  ][x + 2]);
                }
            }
            if (x - 2 >= 0 && plateaux[y][x - 2] instanceof Route && ((Route) plateaux[y][x - 2]).getPlayer() == player && plateaux[y][x - 2]!= father && plateaux[y][x - 2] != oldFather && plateaux[y][x - 2] != old1Father && plateaux[y][x - 2] != old2Father && plateaux[y][x - 2] != old3Father && plateaux[y][x - 2] != old4Father && plateaux[y][x - 2] != old5Father && plateaux[y][x - 2] != old6Father) {
                if(!tmp.contains((Route) plateaux[y][x - 2])) {
                    tmp.add((Route) plateaux[y][x - 2]);
                    findLastRoad(x - 2, y, player, (Route) plateaux[y][x - 2], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
                }else {
                    tmp.remove((Route) plateaux[y ][x - 2]);
                }
            }
        }
        if(x % 2 != 0){
            if (y - 2 >= 0 && plateaux[y - 2][x] instanceof Route && ((Route) plateaux[y - 2][x]).getPlayer() == player && plateaux[y - 2][x] != father && plateaux[y - 2][x] != oldFather && plateaux[y - 2][x] != old1Father && plateaux[y - 2][x] != old2Father && plateaux[y - 2][x] != old3Father && plateaux[y - 2][x] != old4Father && plateaux[y - 2][x] != old5Father && plateaux[y - 2][x] != old6Father) {
                if(!tmp.contains((Route) plateaux[y-2][x])) {
                    tmp.add((Route) plateaux[y - 2][x]);
                    findLastRoad(x, y - 2, player, (Route) plateaux[y - 2][x], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
                }else {
                    tmp.remove((Route) plateaux[y -2][x ]);
                }
            }
            if (y + 2 <= getLength_y() - 1 && plateaux[y + 2][x] instanceof Route && ((Route) plateaux[y + 2][x]).getPlayer() == player && plateaux[y + 2][x] != father && plateaux[y + 2][x] != oldFather && plateaux[y + 2][x] != old1Father && plateaux[y + 2][x] != old2Father && plateaux[y + 2][x] != old3Father && plateaux[y + 2][x] != old4Father && plateaux[y + 2][x] != old5Father && plateaux[y + 2][x] != old6Father) {
                if(!tmp.contains((Route) plateaux[y + 2][x])) {
                    tmp.add((Route) plateaux[y + 2][x]);
                    findLastRoad(x, y + 2, player, (Route) plateaux[y + 2][x], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
                }else {
                    tmp.remove((Route) plateaux[y + 2][x  ]);
                }
            }
        }
        return lastRoadCords;
    }

    public int checkRoad(int x, int y, PlayerModel player, Route father, Route oldFather, Route old1Father, Route old2Father, Route old3Father, Route old4Father, Route old5Father, Route old6Father){
        if(endRoad(x, y, player, father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father, old6Father)){
            return 1;
        }

        int upl = 0, downl = 0, upr = 0, downr = 0, down = 0, up = 0, left = 0, right = 0;
        if (x + 1 <= getLength_x() - 1 && y + 1 <= getLength_y() - 1 && plateaux[y + 1][x + 1] instanceof Route && ((Route) plateaux[y + 1][x + 1]).getPlayer() == player && plateaux[y + 1][x + 1] != father && plateaux[y + 1][x + 1] != oldFather && plateaux[y + 1][x + 1] != old1Father && plateaux[y + 1][x + 1] != old2Father && plateaux[y + 1][x + 1] != old3Father && plateaux[y + 1][x + 1] != old4Father && plateaux[y + 1][x + 1] != old5Father && plateaux[y + 1][x + 1] != old6Father) {
            if(!tmp.contains((Route) plateaux[y + 1][x + 1])) {
                tmp.add((Route) plateaux[y + 1][x + 1]);
                downr = checkRoad(x + 1, y + 1, player, (Route) plateaux[y + 1][x + 1], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
            }else {
                tmp.remove((Route) plateaux[y + 1][x + 1]);
            }
        }
        if (x - 1 >= 0 && y - 1 >= 0 && plateaux[y - 1][x - 1] instanceof Route && ((Route) plateaux[y - 1][x - 1]).getPlayer() == player && plateaux[y - 1][x - 1] != father && plateaux[y - 1][x - 1] != oldFather && plateaux[y - 1][x - 1] != old1Father && plateaux[y - 1][x - 1] != old2Father && plateaux[y - 1][x - 1] != old3Father && plateaux[y - 1][x - 1] != old4Father && plateaux[y - 1][x - 1] != old5Father && plateaux[y - 1][x - 1] != old6Father) {
            if(!tmp.contains((Route) plateaux[y - 1][x - 1])) {
                tmp.add((Route) plateaux[y - 1][x - 1]);
                upl = checkRoad(x - 1, y - 1, player, (Route) plateaux[y - 1][x - 1], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
            }else {
                tmp.remove((Route) plateaux[y - 1][x - 1]);
            }
        }
        if (y - 1 >= 0 && x + 1 <= getLength_x() - 1 && plateaux[y - 1][x + 1] instanceof Route && ((Route) plateaux[y - 1][x + 1]).getPlayer() == player && plateaux[y - 1][x + 1] != father && plateaux[y - 1][x + 1] != oldFather && plateaux[y - 1][x + 1] != old1Father && plateaux[y - 1][x + 1] != old2Father && plateaux[y - 1][x + 1] != old3Father && plateaux[y - 1][x + 1] != old4Father && plateaux[y - 1][x + 1] != old5Father && plateaux[y - 1][x + 1] != old6Father) {
            if(!tmp.contains((Route) plateaux[y - 1][x + 1])) {
                tmp.add((Route) plateaux[y - 1][x + 1]);
                upr = checkRoad(x + 1, y - 1, player, (Route) plateaux[y - 1][x + 1], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
            }else {
                tmp.remove((Route) plateaux[y - 1][x + 1]);
            }
        }
        if (y + 1 <= getLength_y() - 1 && x - 1 >= 0 && plateaux[y + 1][x - 1] instanceof Route && ((Route) plateaux[y + 1][x - 1]).getPlayer() == player && plateaux[y + 1][x - 1] != father && plateaux[y + 1][x - 1] != old1Father && plateaux[y + 1][x - 1] != oldFather && plateaux[y + 1][x - 1] != old2Father && plateaux[y + 1][x - 1] != old3Father && plateaux[y + 1][x - 1] != old4Father && plateaux[y + 1][x - 1] != old5Father && plateaux[y + 1][x - 1] != old6Father) {
            if(!tmp.contains((Route) plateaux[y + 1][x - 1])) {
                tmp.add((Route) plateaux[y + 1][x - 1]);

                downl = checkRoad(x - 1, y + 1, player, (Route) plateaux[y + 1][x - 1], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
            }else {
                tmp.remove((Route) plateaux[y + 1][x - 1]);
            }
        }

        if (y % 2 != 0){
            if (x + 2 <= getLength_x() - 1 && plateaux[y][x + 2] instanceof Route && ((Route) plateaux[y][x + 2]).getPlayer() == player && plateaux[y][x + 2]!= father && plateaux[y][x + 2] != oldFather && plateaux[y][x + 2] != old1Father && plateaux[y][x + 2] != old2Father && plateaux[y][x + 2] != old3Father && plateaux[y][x + 2] != old4Father && plateaux[y][x + 2] != old5Father && plateaux[y][x + 2] != old6Father) {
                if(!tmp.contains((Route) plateaux[y ][x + 2])) {
                    tmp.add((Route) plateaux[y][x + 2]);
                    right = checkRoad(x + 2, y, player, (Route) plateaux[y][x + 2], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
                }else {
                    tmp.remove((Route) plateaux[y ][x + 2]);
                }
            }
            if (x - 2 >= 0 && plateaux[y][x - 2] instanceof Route && ((Route) plateaux[y][x - 2]).getPlayer() == player && plateaux[y][x - 2]!= father && plateaux[y][x - 2] != oldFather && plateaux[y][x - 2] != old1Father && plateaux[y][x - 2] != old2Father && plateaux[y][x - 2] != old3Father && plateaux[y][x - 2] != old4Father && plateaux[y][x - 2] != old5Father && plateaux[y][x - 2] != old6Father) {
                if(!tmp.contains((Route) plateaux[y][x - 2])) {
                    tmp.add((Route) plateaux[y][x - 2]);
                    left = checkRoad(x - 2, y, player, (Route) plateaux[y][x - 2], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
                }else {
                    tmp.remove((Route) plateaux[y ][x -2]);
                }
            }
        }
        if(x % 2 != 0){
            if (y - 2 >= 0 && plateaux[y - 2][x] instanceof Route && ((Route) plateaux[y - 2][x]).getPlayer() == player && plateaux[y - 2][x] != father && plateaux[y - 2][x] != oldFather && plateaux[y - 2][x] != old1Father && plateaux[y - 2][x] != old2Father && plateaux[y - 2][x] != old3Father && plateaux[y - 2][x] != old4Father && plateaux[y - 2][x] != old5Father && plateaux[y - 2][x] != old6Father) {
                if(!tmp.contains((Route) plateaux[y - 2][x])) {
                    tmp.add((Route) plateaux[y - 2][x]);
                    up = checkRoad(x, y - 2, player, (Route) plateaux[y - 2][x], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
                }else {
                    tmp.remove((Route) plateaux[y - 2][x ]);
                }
            }
            if (y + 2 <= getLength_y() - 1 && plateaux[y + 2][x] instanceof Route && ((Route) plateaux[y + 2][x]).getPlayer() == player && plateaux[y + 2][x] != father && plateaux[y + 2][x] != oldFather && plateaux[y + 2][x] != old1Father && plateaux[y + 2][x] != old2Father && plateaux[y + 2][x] != old3Father && plateaux[y + 2][x] != old4Father && plateaux[y + 2][x] != old5Father && plateaux[y + 2][x] != old6Father) {
                if(!tmp.contains((Route) plateaux[y + 2][x])) {
                    tmp.add((Route) plateaux[y + 2][x]);
                    down = checkRoad(x, y + 2, player, (Route) plateaux[y + 2][x], father, oldFather, old1Father, old2Father, old3Father, old4Father, old5Father);
                }else {
                    tmp.remove((Route) plateaux[y + 2][x ]);
                }
            }
        }
        return 1+ Math.max(Math.max(Math.max(upr,upl),Math.max(downl,downr)), Math.max(Math.max(left,right),Math.max(up,down)));
    }

    private boolean endRoad(int x, int y, PlayerModel player, Route father, Route oldFather, Route old1Father, Route old2Father, Route old3Father, Route old4Father, Route old5Father, Route old6Father){
        if(plateaux[y][x] instanceof  Route){
            boolean to_return = false;
            if (x + 1 <= getLength_x() - 1 && y + 1 <= getLength_y() - 1 && plateaux[y + 1][x + 1] instanceof Route && ((Route) plateaux[y + 1][x + 1]).getPlayer() != player) {
                to_return = true;
            }
            if (x - 1 >= 0 && y - 1 >= 0 && plateaux[y - 1][x - 1] instanceof Route && ((Route) plateaux[y - 1][x - 1]).getPlayer() != player) {
                to_return = true;
            }
            if (y - 1 >= 0 && x + 1 <= getLength_x() - 1 && plateaux[y - 1][x + 1] instanceof Route && ((Route) plateaux[y - 1][x + 1]).getPlayer() != player) {
                to_return = true;
            }
            if (y + 1 <= getLength_y() - 1 && x - 1 >= 0 && plateaux[y + 1][x - 1] instanceof Route && ((Route) plateaux[y + 1][x - 1]).getPlayer() != player) {
                to_return = true;
            }

            if (y % 2 != 0){
                if (x + 2 <= getLength_x() - 1 && plateaux[y][x + 2] instanceof Route && ((Route) plateaux[y][x + 2]).getPlayer() != player) {
                    to_return = true;
                }
                if (x - 2 >= 0 && plateaux[y][x - 2] instanceof Route && ((Route) plateaux[y][x - 2]).getPlayer() != player ) {
                    to_return = true;
                }
            }
            if(x % 2 != 0){
                if (y - 2 >= 0 && plateaux[y - 2][x] instanceof Route && ((Route) plateaux[y - 2][x]).getPlayer() != player) {
                    to_return = true;
                }
                if (y + 2 <= getLength_y() - 1 && plateaux[y + 2][x] instanceof Route && ((Route) plateaux[y + 2][x]).getPlayer() != player) {
                    to_return = true;
                }
            }

            if (to_return){
                if (x + 1 <= getLength_x() - 1 && y + 1 <= getLength_y() - 1 && plateaux[y + 1][x + 1] instanceof Route && ((Route) plateaux[y + 1][x + 1]).getPlayer() == player && (plateaux[y + 1][x + 1] != father && plateaux[y + 1][x + 1] != old1Father && plateaux[y + 1][x + 1] != oldFather && plateaux[y + 1][x + 1] != old2Father && plateaux[y + 1][x + 1] != old3Father && plateaux[y + 1][x + 1] != old4Father && plateaux[y + 1][x + 1] != old5Father && plateaux[y + 1][x + 1] != old6Father)) {
                    if(!tmp.contains((Route) plateaux[y + 1][x + 1])) {
                        to_return = false;
                    }else {
                        tmp.remove((Route) plateaux[y + 1][x + 1]);
                    }
                }
                if (x - 1 >= 0 && y - 1 >= 0 && plateaux[y - 1][x - 1] instanceof Route && ((Route) plateaux[y - 1][x - 1]).getPlayer() == player && (plateaux[y - 1][x - 1] != father && plateaux[y - 1][x - 1] != oldFather && plateaux[y - 1][x - 1] != old1Father && plateaux[y - 1][x - 1] != old2Father && plateaux[y - 1][x - 1] != old3Father && plateaux[y - 1][x - 1] != old4Father && plateaux[y - 1][x - 1] != old5Father && plateaux[y - 1][x - 1] != old6Father)) {
                    if(!tmp.contains((Route) plateaux[y - 1][x - 1])) {
                        to_return = false;
                    }else {
                        tmp.remove((Route) plateaux[y - 1][x - 1]);
                    }
                }
                if (y - 1 >= 0 && x + 1 <= getLength_x() - 1 && plateaux[y - 1][x + 1] instanceof Route && ((Route) plateaux[y - 1][x + 1]).getPlayer() == player && (plateaux[y - 1][x + 1] != father && plateaux[y - 1][x + 1] != oldFather && plateaux[y - 1][x + 1] != old1Father && plateaux[y - 1][x + 1] != old2Father && plateaux[y - 1][x + 1] != old3Father && plateaux[y - 1][x + 1] != old4Father && plateaux[y - 1][x + 1] != old5Father && plateaux[y - 1][x + 1] != old6Father)) {
                    if(!tmp.contains((Route) plateaux[y - 1][x + 1])) {
                        to_return = false;
                    }else {
                        tmp.remove((Route) plateaux[y - 1][x + 1]);
                    }
                }
                if (y + 1 <= getLength_y() - 1 && x - 1 >= 0 && plateaux[y + 1][x - 1] instanceof Route && ((Route) plateaux[y + 1][x - 1]).getPlayer() == player && (plateaux[y + 1][x - 1] != father && plateaux[y + 1][x - 1] != old1Father && plateaux[y + 1][x - 1] != oldFather && plateaux[y + 1][x - 1] != old2Father && plateaux[y + 1][x - 1] != old3Father && plateaux[y + 1][x - 1] != old4Father && plateaux[y + 1][x - 1] != old5Father && plateaux[y + 1][x - 1] != old6Father)) {
                    if(!tmp.contains((Route) plateaux[y + 1][x - 1])) {
                        to_return = false;
                    }else {
                        tmp.remove((Route) plateaux[y + 1][x - 1]);
                    }
                }
                if (y % 2 != 0){
                    if (x + 2 <= getLength_x() - 1 && plateaux[y][x + 2] instanceof Route && ((Route) plateaux[y][x + 2]).getPlayer() == player && plateaux[y][x + 2]!= father && plateaux[y][x + 2] != oldFather && plateaux[y][x + 2] != old1Father && plateaux[y][x + 2] != old2Father && plateaux[y][x + 2] != old3Father && plateaux[y][x + 2] != old4Father && plateaux[y][x + 2] != old5Father && plateaux[y][x + 2] != old6Father) {
                        if(!tmp.contains((Route) plateaux[y ][x + 2])) {
                            to_return = false;
                        }else {
                            tmp.remove((Route) plateaux[y ][x + 2]);
                        }
                    }
                    if (x - 2 >= 0 && plateaux[y][x - 2] instanceof Route && ((Route) plateaux[y][x - 2]).getPlayer() == player && plateaux[y][x - 2] != father && plateaux[y][x - 2] != oldFather && plateaux[y][x - 2] != old1Father && plateaux[y][x - 2] != old2Father && plateaux[y][x - 2] != old3Father && plateaux[y][x - 2] != old4Father && plateaux[y][x - 2] != old5Father && plateaux[y][x - 2] != old6Father) {
                        if(!tmp.contains((Route) plateaux[y ][x - 2])) {
                            to_return = false;
                        }else {
                            tmp.remove((Route) plateaux[y ][x -2]);
                        }
                    }
                }
                if(x % 2 != 0){
                    if (y - 2 >= 0 && plateaux[y - 2][x] instanceof Route && ((Route) plateaux[y - 2][x]).getPlayer() == player && plateaux[y - 2][x] != father && plateaux[y - 2][x] != oldFather && plateaux[y - 2][x] != old1Father && plateaux[y - 2][x] != old2Father && plateaux[y - 2][x] != old3Father && plateaux[y - 2][x] != old4Father && plateaux[y - 2][x] != old5Father && plateaux[y - 2][x] != old6Father) {
                        if(!tmp.contains((Route) plateaux[y - 2][x ])) {
                            to_return = false;
                        }else {
                            tmp.remove((Route) plateaux[y -2][x ]);
                        }
                    }
                    if (y + 2 <= getLength_y() - 1 && plateaux[y + 2][x] instanceof Route && ((Route) plateaux[y + 2][x]).getPlayer() == player && plateaux[y + 2][x] != father && plateaux[y + 2][x] != oldFather && plateaux[y + 2][x] != old1Father && plateaux[y + 2][x] != old2Father && plateaux[y + 2][x] != old3Father && plateaux[y + 2][x] != old4Father && plateaux[y + 2][x] != old5Father && plateaux[y + 2][x] != old6Father) {
                        if(!tmp.contains((Route) plateaux[y + 2][x ])) {
                            to_return = false;
                        }else {
                            tmp.remove((Route) plateaux[y + 2][x ]);
                        }
                    }
                }
            }
            return to_return;
        }
        return false;
    }
}