package l2.poo3.model.PlayerType;

import l2.poo3.model.CaseModel;
import l2.poo3.model.CaseType.Batiment;
import l2.poo3.model.CaseType.Route;
import l2.poo3.model.CaseType.Vide;
import l2.poo3.model.Enum.Pcolor;
import l2.poo3.model.Enum.Resources;
import l2.poo3.model.PlateauxModel;
import l2.poo3.model.PlayerModel;
import l2.poo3.view.gui.GuiView;

import java.util.LinkedList;
import java.util.Random;

public class Ai extends PlayerModel {

    private PlateauxModel plateaux = null;

    private final Random r = new Random(System.nanoTime()*System.currentTimeMillis());

    public Ai(Pcolor color) {
        super(color);
    }

    public void setPlateaux(PlateauxModel plateaux) {
        this.plateaux = plateaux;
    }

    public PlateauxModel getPlateaux() {
        return plateaux;
    }

    private final LinkedList<String> badCords = new LinkedList<>();

    public void deleteRessources(){
        while ((getNbrRessources()-7) > 0){
            Resources res = choixRessource(r.nextInt(5)+1);
            if(getResources().get(res) > 0){
                setResources(res, getResources().get(res)-1);
            }
        }
        setThiefPlay(false);
    }

    public void thiefAi(int xLength, int yLength, PlayerModel[] players){
        boolean good = true;

        while (good) {
            int x = aiGenerateRandom(true, xLength);
            int y = aiGenerateRandom(true, yLength);
            String result = plateaux.moveThief(x, y);
            if(result.contains("ok")){
                Resources res = plateaux.stealOneRessources(x,y, this);
                if(res != null) {
                    for (PlayerModel p : players) {
                        p.setThiefPlay(true);
                    }
                }
                deleteRessources();
                good = false;
            }
        }
    }


    private Resources choixRessource(int choix){
        switch (choix) {
            case 1:
                return Resources.BOIS;
            case 2:
                return Resources.BLE;
            case 3:
                return Resources.ARGILE;
            case 4:
                return Resources.MINERAI;
            case 5:
                return Resources.MOUTON;
        }
        return null;
    }

    public void buildColl(int xLength, int yLength, PlayerModel[] players){
        int minBat = 0;
        for (PlayerModel p : players){
            if(minBat > p.getNbrBat()){
                minBat = p.getNbrBat();
            }
        }
        if(plateaux != null && getNbrBat() <= minBat+r.nextInt(3)) {
            int x = aiGenerateRandom(false, xLength);
            int y = aiGenerateRandom(false, yLength);
            while (badCords.contains(x + ":" + y)){
                x = aiGenerateRandom(false, xLength);
                y = aiGenerateRandom(false, yLength);
            }
            if (!badCords.contains(x + ":" + y)) {
                if (verifCase(y, x)) {
                    if (plateaux.getPlateaux()[y][x] instanceof Batiment) {
                        if (this.getResources().get(Resources.BOIS) >= 1 && this.getResources().get(Resources.ARGILE) >= 1 && this.getResources().get(Resources.BLE) >= 1 && this.getResources().get(Resources.MOUTON) >= 1) {
                            boolean allowBuild = false;

                            if (y + 1 <= getPlateaux().getLength_y() - 1 && plateaux.getPlateaux()[y + 1][x] instanceof Route && (((Route) plateaux.getPlateaux()[y + 1][x]).getPlayer() == this || ((Route) plateaux.getPlateaux()[y + 1][x]).getPlayer() == null)) {
                                allowBuild = true;
                            } else if (y - 1 >= 0 && plateaux.getPlateaux()[y - 1][x] instanceof Route && (((Route) plateaux.getPlateaux()[y - 1][x]).getPlayer() == this || ((Route) plateaux.getPlateaux()[y - 1][x]).getPlayer() == null)) {
                                allowBuild = true;
                            } else if (x + 1 <= getPlateaux().getLength_x() - 1 && plateaux.getPlateaux()[y][x + 1] instanceof Route && (((Route) plateaux.getPlateaux()[y][x + 1]).getPlayer() == this || ((Route) plateaux.getPlateaux()[y][x + 1]).getPlayer() == null)) {
                                allowBuild = true;
                            } else if (x - 1 >= 0 && plateaux.getPlateaux()[y][x - 1] instanceof Route && (((Route) plateaux.getPlateaux()[y][x - 1]).getPlayer() == this || ((Route) plateaux.getPlateaux()[y][x - 1]).getPlayer() == null)) {
                                allowBuild = true;
                            }

                            if (y + 1 <= getPlateaux().getLength_y() - 1 && plateaux.getPlateaux()[y + 1][x] instanceof Route && ((Route) plateaux.getPlateaux()[y + 1][x]).getPlayer() != this && ((Route) plateaux.getPlateaux()[y + 1][x]).getPlayer() != null) {
                                allowBuild = false;
                            } else if (y - 1 >= 0 && plateaux.getPlateaux()[y - 1][x] instanceof Route && ((Route) plateaux.getPlateaux()[y - 1][x]).getPlayer() != this && ((Route) plateaux.getPlateaux()[y - 1][x]).getPlayer() != null) {
                                allowBuild = false;
                            } else if (x + 1 <= getPlateaux().getLength_x() - 1 && plateaux.getPlateaux()[y][x + 1] instanceof Route && ((Route) plateaux.getPlateaux()[y][x + 1]).getPlayer() != this && ((Route) plateaux.getPlateaux()[y][x + 1]).getPlayer() != null) {
                                allowBuild = false;
                            } else if (x - 1 >= 0 && plateaux.getPlateaux()[y][x - 1] instanceof Route && ((Route) plateaux.getPlateaux()[y][x - 1]).getPlayer() != this && ((Route) plateaux.getPlateaux()[y][x - 1]).getPlayer() != null) {
                                allowBuild = false;
                            }

                            if (allowBuild) {
                                plateaux.getPlateaux()[y][x].setName("C " + this.getColor().toString().charAt(0));
                                ((Batiment) plateaux.getPlateaux()[y][x]).setPlayer(this);

                                if (plateaux.getViewModel() instanceof GuiView) {
                                    ((GuiView) plateaux.getViewModel()).addInfo("AI " + getColor() + " a construit une ville");
                                } else {
                                    System.out.println("AI " + getColor() + " a construit une colonie");
                                }
                                super.addNbrCol();
                                this.setResources(Resources.BOIS, this.getResources().get(Resources.BOIS) - 1);
                                this.setResources(Resources.ARGILE, this.getResources().get(Resources.ARGILE) - 1);
                                this.setResources(Resources.BLE, this.getResources().get(Resources.BLE) - 1);
                                this.setResources(Resources.MOUTON, this.getResources().get(Resources.MOUTON) - 1);


                                this.setPointDeVic(this.getPointDeVic() + 1);
                                badCords.add(x + ":" + y);
                            }
                        }
                    }
                }
            }
        }
    }

    public void buildVill(int xLength, int yLength) {
        if (plateaux != null) {
            int x = aiGenerateRandom(false, xLength);
            int y = aiGenerateRandom(false, yLength);

            if (verifCase(y, x)) {
                if (plateaux.getPlateaux()[y][x] instanceof Batiment) {
                    if (plateaux.getPlateaux()[y][x].getName().contains("C")) {
                        if (this.getResources().get(Resources.BLE) >= 2 && this.getResources().get(Resources.MINERAI) >= 3) {
                            plateaux.getPlateaux()[y][x].setName("V " + this.getColor().toString().charAt(0));
                            ((Batiment) plateaux.getPlateaux()[y][x]).setPlayer(this);
                            if(plateaux.getViewModel() instanceof GuiView){
                                ((GuiView) plateaux.getViewModel()).addInfo("AI " + getColor() + " a construit une ville");
                            }else {
                                System.out.println("AI " + getColor() + " a construit une ville");
                            }
                            super.addNbrVille();
                            this.setResources(Resources.BLE, this.getResources().get(Resources.BLE) - 2);
                            this.setResources(Resources.MINERAI, this.getResources().get(Resources.MINERAI) - 3);

                            this.setPointDeVic(this.getPointDeVic() + 1);
                        }
                    }
                }
            }
        }
    }

    private boolean verifCords(int y, int x){
        if(y < 0 || x < 0 || y > plateaux.getLength_y()-1 || x > plateaux.getLength_x()-1){
            return false;
        }
        return true;
    }

    public boolean verifCase(int y, int x){
        if(verifCords(y,x)) {
            CaseModel cases = plateaux.getPlateaux()[y][x];
            if (cases != null) {
                if (cases instanceof Vide) {
                    return false;
                }
                if (!(cases instanceof Route) && !(cases instanceof Batiment)) {
                    return false;
                }
                if (cases instanceof Route) {
                    if (((Route) cases).getPlayer() == null) {
                        return true;
                    }
                }
                if (cases instanceof Batiment) {
                    if (((Batiment) cases).getPlayer() == null) {
                        return true;
                    }
                    if (cases.getName().contains("C") && ((Batiment) cases).getPlayer() == this) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void buildRoad(int xLength, int yLength, PlayerModel[] players) {
        if (plateaux != null) {
            int x;
            int y;
            if (r.nextBoolean()) {
                x = aiGenerateRandom(false, xLength);
                y = aiGenerateRandom(true, yLength);
                while (badCords.contains(x + ":" + y)){
                    x = aiGenerateRandom(false, xLength);
                    y = aiGenerateRandom(true, yLength);
                }
            } else {
                x = aiGenerateRandom(true, xLength);
                y = aiGenerateRandom(false, yLength);
                while (badCords.contains(x + ":" + y)){
                    x = aiGenerateRandom(true, xLength);
                    y = aiGenerateRandom(false, yLength);
                }
            }

            if (!badCords.contains(x + ":" + y)) {
                if (this.getResources().get(Resources.BOIS) >= 1 && this.getResources().get(Resources.ARGILE) >= 1) {
                    CaseModel[][] plateaux = this.plateaux.getPlateaux();
                    boolean allowBuild = false;
                    if (verifCase(y, x)) {
                        if (y + 1 <= getPlateaux().getLength_y() - 1 && plateaux[y + 1][x] instanceof Batiment && ((Batiment) plateaux[y + 1][x]).getPlayer() == this) {
                            allowBuild = true;
                        } else if (y - 1 >= 0 && plateaux[y - 1][x] instanceof Batiment && ((Batiment) plateaux[y - 1][x]).getPlayer() == this) {
                            allowBuild = true;
                        } else if (x + 1 <= getPlateaux().getLength_x() - 1 && plateaux[y][x + 1] instanceof Batiment && ((Batiment) plateaux[y][x + 1]).getPlayer() == this) {
                            allowBuild = true;
                        } else if (x - 1 >= 0 && plateaux[y][x - 1] instanceof Batiment && ((Batiment) plateaux[y][x - 1]).getPlayer() == this) {
                            allowBuild = true;
                        } else if (x + 1 <= getPlateaux().getLength_x() - 1 && y + 1 <= getPlateaux().getLength_y() - 1 && plateaux[y + 1][x + 1] instanceof Route && ((Route) plateaux[y + 1][x + 1]).getPlayer() == this) {
                            allowBuild = true;
                        } else if (x - 1 >= 0 && y - 1 >= 0 && plateaux[y - 1][x - 1] instanceof Route && ((Route) plateaux[y - 1][x - 1]).getPlayer() == this) {
                            allowBuild = true;
                        } else if (y - 1 >= 0 && x + 1 <= getPlateaux().getLength_x() - 1 && plateaux[y - 1][x + 1] instanceof Route && ((Route) plateaux[y - 1][x + 1]).getPlayer() == this) {
                            allowBuild = true;
                        } else if (y + 1 <= getPlateaux().getLength_y() - 1 && x - 1 >= 0 && plateaux[y + 1][x - 1] instanceof Route && ((Route) plateaux[y + 1][x - 1]).getPlayer() == this) {
                            allowBuild = true;
                        } else if (y % 2 != 0) {
                            if (x + 2 <= getPlateaux().getLength_x() - 1 && plateaux[y][x + 2] instanceof Route && ((Route) plateaux[y][x + 2]).getPlayer() == this) {
                                allowBuild = true;
                            } else if (x - 2 >= 0 && plateaux[y][x - 2] instanceof Route && ((Route) plateaux[y][x - 2]).getPlayer() == this) {
                                allowBuild = true;
                            }
                        } else if (x % 2 != 0) {
                            if (y - 2 >= 0 && plateaux[y - 2][x] instanceof Route && ((Route) plateaux[y - 2][x]).getPlayer() == this) {
                                allowBuild = true;
                            } else if (y + 2 <= getPlateaux().getLength_y() - 1 && plateaux[y + 2][x] instanceof Route && ((Route) plateaux[y + 2][x]).getPlayer() == this) {
                                allowBuild = true;
                            }
                        }

                        if (y + 1 <= getPlateaux().getLength_y() - 1 && plateaux[y + 1][x] instanceof Batiment && ((Batiment) plateaux[y + 1][x]).getPlayer() != this && ((Batiment) plateaux[y + 1][x]).getPlayer() != null) {
                            allowBuild = false;
                        } else if (y - 1 >= 0 && plateaux[y - 1][x] instanceof Batiment && ((Batiment) plateaux[y - 1][x]).getPlayer() != this && ((Batiment) plateaux[y - 1][x]).getPlayer() != null) {
                            allowBuild = false;
                        } else if (x + 1 <= getPlateaux().getLength_x() - 1 && plateaux[y][x + 1] instanceof Batiment && ((Batiment) plateaux[y][x + 1]).getPlayer() != this && ((Batiment) plateaux[y][x + 1]).getPlayer() != null) {
                            allowBuild = false;
                        } else if (x - 1 >= 0 && plateaux[y][x - 1] instanceof Batiment && ((Batiment) plateaux[y][x - 1]).getPlayer() != this && ((Batiment) plateaux[y][x - 1]).getPlayer() != null) {
                            allowBuild = false;
                        }

                        if (plateaux[y][x] instanceof Route) {
                            if (allowBuild) {
                                plateaux[y][x].setName("R " + this.getColor().toString().charAt(0));
                                ((Route) plateaux[y][x]).setPlayer(this);

                                this.setResources(Resources.BOIS, this.getResources().get(Resources.BOIS) - 1);
                                this.setResources(Resources.ARGILE, this.getResources().get(Resources.ARGILE) - 1);

                                if (this.plateaux.getViewModel() instanceof GuiView) {
                                    ((GuiView) this.plateaux.getViewModel()).addInfo("AI " + getColor() + " a construit une route");
                                } else {
                                    System.out.println("AI " + getColor() + " a construit une route");
                                }
                                this.plateaux.updateLargestRoadPlayer(x, y, this, players);
                                badCords.add(x + ":" + y);
                            }
                        }
                    }
                }
            }
        }
    }

    private final int aiGenerateRandom(boolean evenNum, int length){
        int randomNum = (r.nextInt(length+1))*2;
        while (randomNum == 0 && evenNum){
            randomNum = (r.nextInt(length+1))*2;
        }
        if(!evenNum){
            randomNum++;
        }
        return randomNum;
    }
}
