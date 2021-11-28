package l2.poo3.view.terminal;

import l2.poo3.Other.StringUtil;
import l2.poo3.controller.terminal.TerminalController;
import l2.poo3.model.CaseType.*;
import l2.poo3.model.PlateauxModel;

public class TerminalView {

    private TerminalController controller;
    private PlateauxModel plateaux;

    public TerminalController getController() {
        return controller;
    }

    public void setController(TerminalController controller) {
        this.controller = controller;
        plateaux = controller.getPlateaux();
    }

    public void affichePlateaux(){
        if(plateaux != null){
            System.out.println("\n" + StringUtil.buildTablePattern("-----+----------+", plateaux.getLength_x()));
            afficheNumberLigne();
            System.out.println(StringUtil.buildTablePattern("-----+----------+", plateaux.getLength_x()));
            for(int y = 0; y < plateaux.getPlateaux().length; y++){
                if(y % 2 == 0){
                    System.out.print("|     | ");
                    afficheNameLigne(y);
                    System.out.print("\n|  " + (char) (65 + y) + "  | ");
                        for (int x = 0; x < plateaux.getPlateaux()[y].length; x++) {
                            if (plateaux.getPlateaux()[y][x] != null) {
                                if (x % 2 == 0) {
                                    if(plateaux.getPlateaux()[y][x] instanceof Port){
                                        if(plateaux.getPlateaux()[y][x].getNature() == null){
                                            System.out.print(StringUtil.center("", 9) + "| ");
                                        }else {
                                            System.out.print(StringUtil.center(plateaux.getPlateaux()[y][x].getNature() + "", 9) + "| ");
                                        }
                                    }else if(plateaux.getPlateaux()[y][x].getNumber() < 0) {
                                        System.out.print(StringUtil.center("", 9) + "| ");
                                    }else {
                                        System.out.print(StringUtil.center(plateaux.getPlateaux()[y][x].getNumber() + "", 9) + "| ");
                                    }
                                } else {
                                    System.out.print(StringUtil.center(plateaux.getPlateaux()[y][x].getName(), 4) + "| ");
                                }
                            }
                        }
                        System.out.print("\n|     | ");
                        for (int x = 0; x < plateaux.getPlateaux()[y].length; x++) {
                            if (plateaux.getPlateaux()[y][x] != null) {
                                if (plateaux.getPlateaux()[y][x] instanceof Port) {
                                    System.out.print(StringUtil.center(((Port) plateaux.getPlateaux()[y][x]).getPrix(), 9) + "| ");
                                } else if (x % 2 == 0) {
                                    if(plateaux.getPlateaux()[y][x] instanceof Port){
                                        System.out.print(StringUtil.center(((Port) plateaux.getPlateaux()[y][x]).getPrix(),  9) + "| ");
                                    }else if (plateaux.getPlateaux()[y][x].thiefPresent()) {
                                        System.out.print(StringUtil.center("Voleur", 9) + "| ");
                                    } else {
                                        System.out.print(StringUtil.center("", 9) + "| ");
                                    }
                                } else {
                                    System.out.print(StringUtil.center("", 4) + "| ");
                                }
                            }
                        }

                }else {
                    System.out.print("|  "+ (char) (65+y) + "  | ");
                    afficheNameLigne(y);
                }
                System.out.println("\n" + StringUtil.buildTablePattern("-----+----------+", plateaux.getLength_x()));
            }
        }
    }

    private void afficheNameLigne(int y){
        for(int x = 0; x < plateaux.getPlateaux()[y].length; x++){
            if(plateaux.getPlateaux()[y][x] != null) {
                if(x % 2 == 0) {
                    System.out.print(StringUtil.center(plateaux.getPlateaux()[y][x].getName(), 9) + "| ");
                }else {
                    if(plateaux.getPlateaux()[y][x] instanceof Route){
                        System.out.print(StringUtil.center("", 4) + "| ");
                    }else {
                        System.out.print(StringUtil.center(plateaux.getPlateaux()[y][x].getName(), 4) + "| ");
                    }
                }
            }
        }
    }

    private void afficheNumberLigne(){
        if(plateaux != null){
            System.out.print("| y\\x ");
            for(int x = 1; x <= plateaux.getLength_x(); x++){
                if(x % 2 != 0) {
                    System.out.print("| " + StringUtil.center(String.valueOf(x), 9));
                }else{
                    System.out.print("| " + StringUtil.center(String.valueOf(x), 4));

                }
            }
            System.out.print("|\n");
        }
    }


}
