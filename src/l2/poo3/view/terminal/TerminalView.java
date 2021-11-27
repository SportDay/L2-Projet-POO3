package l2.poo3.view.terminal;

import l2.poo3.controller.terminal.TerminalController;
import l2.poo3.model.CaseModel;
import l2.poo3.model.CaseType.*;
import l2.poo3.model.PlateauxModel;
import l2.poo3.model.PlayerModel;

import java.util.Random;
import java.util.Scanner;

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
            System.out.println("\n" + buildLine());
            afficheNumberLigne();
            System.out.println(buildLine());
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
                                            System.out.print(fixString("", 8) + "| ");
                                        }else {
                                            System.out.print(fixString(plateaux.getPlateaux()[y][x].getNature() + "", 8) + "| ");
                                        }
                                    }else if(plateaux.getPlateaux()[y][x].getNumber() < 0) {
                                        System.out.print(fixString("", 8) + "| ");
                                    }else {
                                        System.out.print(fixString(plateaux.getPlateaux()[y][x].getNumber() + "", 8) + "| ");
                                    }
                                } else {
                                    System.out.print(fixString(plateaux.getPlateaux()[y][x].getName(), 4) + "| ");
                                }
                            }
                        }
                        System.out.print("\n|     | ");
                        for (int x = 0; x < plateaux.getPlateaux()[y].length; x++) {
                            if (plateaux.getPlateaux()[y][x] != null) {
                                if (plateaux.getPlateaux()[y][x] instanceof Port) {
                                    System.out.print(fixString(((Port) plateaux.getPlateaux()[y][x]).getPrix(), 8) + "| ");
                                } else if (x % 2 == 0) {
                                    if(plateaux.getPlateaux()[y][x] instanceof Port){
                                        System.out.print(fixString(((Port) plateaux.getPlateaux()[y][x]).getPrix(),  8) + "| ");
                                    }else if (plateaux.getPlateaux()[y][x].thiefPresent()) {
                                        System.out.print(fixString("Voleur", 8) + "| ");
                                    } else {
                                        System.out.print(fixString("", 8) + "| ");
                                    }
                                } else {
                                    System.out.print(fixString("", 4) + "| ");
                                }
                            }
                        }

                }else {
                    System.out.print("|  "+ (char) (65+y) + "  | ");
                    afficheNameLigne(y);
                }
                System.out.println("\n" + buildLine());
            }
        }
    }

    private void afficheNameLigne(int y){
        for(int x = 0; x < plateaux.getPlateaux()[y].length; x++){
            if(plateaux.getPlateaux()[y][x] != null) {
                if(x % 2 == 0) {
                    System.out.print(fixString(plateaux.getPlateaux()[y][x].getName(), 8) + "| ");
                }else {
                    if(plateaux.getPlateaux()[y][x] instanceof Route){
                        System.out.print(fixString("", 4) + "| ");
                    }else {
                        System.out.print(fixString(plateaux.getPlateaux()[y][x].getName(), 4) + "| ");
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
                    System.out.print("| " + fixString(String.valueOf(x), 8));
                }else{
                    System.out.print("| " + fixString(String.valueOf(x), 4));

                }
            }
            System.out.print("|\n");
        }
    }

    private String buildLine(){
        String t = "+";
        int length = plateaux.getLength_x();
        if(length % 2 != 0){
            length++;
        }
        for(int i = 0; i < (length/2); i++){
            t += "-----+---------+";
        }

        return t;
    }

    private String fixString(String text, int caseLength){
        //on centre le text
        for (int i = 0; i < caseLength - text.length(); i++) {
            text += " ";


        }
        for(int i = text.length(); i < caseLength; i++){
            text = " " + text;

        }
        return text;
    }


}
