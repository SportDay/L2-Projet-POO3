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
            System.out.println(buildLine());
            afficheNumberLigne();
            System.out.println(buildLine());
            for(int y = 0; y < plateaux.getPlateaux().length; y++){
                if(y % 2 == 0){
                        System.out.print("|     | ");
                        for (int x = 0; x < plateaux.getPlateaux()[y].length; x++) {
                            if (plateaux.getPlateaux()[y][x] != null) {
                                if (x % 2 == 0) {
                                    System.out.print(fixString(plateaux.getPlateaux()[y][x].getName(), 8) + "| ");
                                } else {
                                    System.out.print(fixString("", 4) + "| ");

                                }
                            }
                        }
                        System.out.print("\n|  " + (char) (65 + y) + "  | ");
                        for (int x = 0; x < plateaux.getPlateaux()[y].length; x++) {
                            if (plateaux.getPlateaux()[y][x] != null) {
                                if (x == 0 || x == plateaux.getLength_x()-1) {
                                    if(plateaux.getPlateaux()[y][x].getNature() == null){
                                        System.out.print(fixString("", 8) + "| ");
                                    }else {
                                        System.out.print(fixString(plateaux.getPlateaux()[y][x].getNature() + "", 8) + "| ");
                                    }
                                } else if (x % 2 == 0) {
                                    if(plateaux.getPlateaux()[y][x].getNumber() < 0) {
                                        System.out.print(fixString("", 8) + "| ");
                                    }else if (y == 0 || y == plateaux.getLength_y()-1) {
                                        if(plateaux.getPlateaux()[y][x].getNature() == null){
                                            System.out.print(fixString("", 8) + "| ");
                                        }else {
                                            System.out.print(fixString(plateaux.getPlateaux()[y][x].getNature() + "", 8) + "| ");
                                        }
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
                                if (x == 0 || x == plateaux.getLength_x()-1) {
                                    if(plateaux.getPlateaux()[y][x].getNumber() < 0){
                                        System.out.print(fixString("", 8) + "| ");
                                    }else if(plateaux.getPlateaux()[y][x] instanceof Port){
                                        System.out.print(fixString(((Port) plateaux.getPlateaux()[y][x]).getPrix(), 8) + "| ");
                                    }
                                } else if (x % 2 == 0) {
                                    if (y == 0 || y == plateaux.getLength_y()-1) {
                                        if(plateaux.getPlateaux()[y][x].getNature() == null){
                                            System.out.print(fixString("", 8) + "| ");
                                        }else if(plateaux.getPlateaux()[y][x] instanceof Port){
                                            System.out.print(fixString(((Port) plateaux.getPlateaux()[y][x]).getPrix(), 8) + "| ");
                                        }
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
                    for(int x = 0; x < plateaux.getPlateaux()[y].length; x++){
                        if(plateaux.getPlateaux()[y][x] != null) {
                            if(x % 2 == 0) {
                                System.out.print(fixString(plateaux.getPlateaux()[y][x].getName(), 8) + "| ");
                            }else {
                                System.out.print(fixString(plateaux.getPlateaux()[y][x].getName(),4) + "| ");
                            }
                        }
                    }
                }
                System.out.println("\n" + buildLine());
            }
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

    public void round(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Que voulez vous faire ? consulter_ress/construire_batiment/construire_route/acheter_carte/jouer_carte/échanger/finir");

        String line;
        while(sc.hasNextLine()){
            line = sc.nextLine();

            if ("consulter_ress".equals(line)) {
                controller.consulterRessources();

            } else if ("construire_batiment".equals(line)) {
                System.out.println("Que voulez-vous construire ? colonnie/cité");
                String choix = sc.nextLine();
                System.out.println("A quel emplacement ? ligne colonne");
                String lieu = sc.nextLine();
                controller.construireBat(choix, lieu);

            } else if ("construire_route".equals(line)) {
                System.out.println("Point de départ de la route ? ligne colonne");
                String depart = sc.nextLine();
                System.out.println("Point d'arrivée de la route ? ligne colonne");
                String arrive = sc.nextLine();
                controller.construireRoute(depart,arrive);

            } else if ("acheter_carte".equals(line)) {
                controller.acheterCarte();

            } else if ("jouer_carte".equals(line)) {
                controller.montrerCartes();
                System.out.println("Quelle carte voulez-vous jouer ? numéro");
                String num = sc.nextLine();
                controller.jouerCarte(num);

            } else if ("échanger".equals(line)) {
                // J'ai pas encore d'idée pour faire ça
            } else if ("finir".equals(line)) {
                controller.joueurSuivant();
            }
        }
    }
}
