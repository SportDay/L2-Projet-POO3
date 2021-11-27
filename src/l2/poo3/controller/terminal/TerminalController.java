package l2.poo3.controller.terminal;

import l2.poo3.model.CaseModel;
import l2.poo3.model.CaseType.*;
import l2.poo3.model.Enum.Pcolor;
import l2.poo3.model.PlateauxModel;
import l2.poo3.model.PlayerModel;
import l2.poo3.model.PlayerType.Ai;
import l2.poo3.model.PlayerType.Player;
import l2.poo3.view.terminal.TerminalView;

import java.util.Scanner;

public class TerminalController {
    private PlayerModel[] players;
    private PlateauxModel plateaux;
    private Scanner sc = new Scanner(System.in);
    private int quiJoue;
    private TerminalView view;

    public TerminalController(PlateauxModel plateaux, TerminalView view){
        this.plateaux = plateaux;
        this.view = view;
        view.setController(this);
        showTitle();
        //initAll();
    }

    private void initAll(){
        int number_player = askInteger("nbrPlayer");

        players = new PlayerModel[number_player];
        for (int i = 0; i < players.length; i++){
            boolean ia = askYesNo("aiPlayer",i);
            switch (i){
                case 0: if(ia){
                            players[i] = new Ai(Pcolor.RED);
                        }else{
                            players[i] = new Player(Pcolor.RED);
                        }
                            break;
                case 1: if(ia){
                            players[i] = new Ai(Pcolor.GREEN);
                        }else{
                            players[i] = new Player(Pcolor.GREEN);
                        }
                    break;
                case 2: if(ia){
                            players[i] = new Ai(Pcolor.YELLOW);
                        }else{
                            players[i] = new Player(Pcolor.YELLOW);
                        }
                        break;
                case 3: if(ia){
                            players[i] = new Ai(Pcolor.BLUE);
                        }else{
                            players[i] = new Player(Pcolor.BLUE);
                        }
                        break;
            }
        }
        int x = askInteger("x");
        int y = askInteger("y");
        plateaux.initPlateaux(x,y);
    }

    private int askInteger(String type){
        int to_return = -1;
        while (true) {
            if(type.contains("nbrPlayer")) {
                System.out.print("Veuillez indiquer le nombre de joueurs (3 ou 4): ");
            }else if(type.contains("x")){
                System.out.print("Veuillez indiquer la longueur du tableau: ");
            }else if(type.contains("y")){
                System.out.print("Veuillez indiquer la largeur du tableau: ");
            }
            String input = sc.next();
            try {
                to_return = Integer.parseInt(input);
                if(type.contains("nbrPlayer")) {
                    if (to_return == 3 || to_return == 4) {
                       return to_return;
                    }
                }else if(type.contains("x")){
                    if(to_return != -1){
                        return to_return;
                    }
                }else if(type.contains("y")){
                    if(to_return != -1){
                        return to_return;
                    }
                }
                System.out.println("Le chiffre " + to_return + " est incorrect.");
            } catch (NumberFormatException e) {
                System.out.println("Merci d'indiquer un chiffre");
            }
        }
    }

    private boolean askYesNo(String type, int i){
        boolean ia = false;
        while (true) {
            if(type.contains("aiPlayer")) {
                System.out.print("Veuillez indiquer si le jouer " + (i + 1) + " est un ordinateur (oui ou non): ");
            }
            String rep = sc.next().toLowerCase();
            if(rep.contains("oui") || rep.contains("non")){
                if(rep.contains("oui")){
                    return true;
                }else {
                    return false;
                }
            }else {
                System.out.println("Reponse incorrecte!");
            }
        }
    }

    private void showTitle(){
        System.out.println( "+---------------------------------------------+\n" +
                            "|    Projet de Programmation Orientee Objet   |\n" +
                            "|       Sujet: Jeu Les Colons de Catane       |\n" +
                            "|              Année 2021 - 2022              |\n" +
                            "|                Développé par                |\n" +
                            "+======================+======================+\n" +
                            "|     Gargaun Illia    |    Legrand Lilian    |\n" +
                            "|        INFO-5        |    INFO-2 INFO-JAP   |\n" +
                            "| N-Etudiant: 00000000 | N-Etudiant: 00000000 |\n" +
                            "+----------------------+----------------------+\n");
    }

    public PlateauxModel getPlateaux() {
        return plateaux;
    }

    public void consulterRessources() {
        System.out.println(players[quiJoue].getStringResources());
    }


    public void construireBat(String choix, String lieu) {
    }

    public void construireRoute(String depart, String arrive) {
    }

    public void acheterCarte() {
    }

    public void montrerCartes() {
    }

    public void jouerCarte(String num) {
    }

    public void joueurSuivant() {
        quiJoue++;
        if (quiJoue == players.length) quiJoue = 0;
    }

    public boolean verifCase(int y, int x){
        if(y < 0 || x < 0 || y > plateaux.getLength_y()-1 || x > plateaux.getLength_x()-1){
            System.out.println("Hors du plateaux, merci de resseyer");
            return false;
        }
        CaseModel cases = plateaux.getPlateaux()[y][x];
        System.out.println(cases.getName());
        if(cases != null){
            if(cases instanceof Vide){
                System.out.println("C'est une case vide");
                return false;
            }
            if(!(cases instanceof Route) && !(cases instanceof Batiment)){
                System.out.println("C'est n'est pas une route ni un batiment");
                return false;
            }
            if(cases instanceof Route){
                if(((Route) cases).getPlayer() == null){
                    return true;
                }
            }
            if(cases instanceof Batiment){
                if(((Batiment) cases).getPlayer() == null){
                    return true;
                }
            }
        }
        return false;
    }

    private void askQuestion(){
        System.out.println("Que voulez vous faire?:");
        System.out.println("    - Consulter les ressources (cr): ");
        System.out.println("    - Contruire des batiments (cb): ");
        System.out.println("    - Contruire une route (cr): ");
        System.out.println("    - Acheter des cartes (bc): ");
        System.out.println("    - Jouer une carte (pc): ");
        System.out.println("    - Finir (end): ");
    }

    public void round(){
        Scanner sc = new Scanner(System.in);
        askQuestion();

        String line;
        while(sc.hasNextLine()){
            line = sc.nextLine();

            if ("consulter_ress".equals(line)) {
                consulterRessources();

            } else if ("construire_batiment".equals(line)) {
                System.out.println("Que voulez-vous construire ? colonnie/cité");
                String choix = sc.nextLine();
                System.out.println("A quel emplacement ? ligne colonne");
                String lieu = sc.nextLine();
                construireBat(choix, lieu);

            } else if ("construire_route".equals(line)) {
                System.out.println("Point de départ de la route ? ligne colonne");
                String depart = sc.nextLine();
                System.out.println("Point d'arrivée de la route ? ligne colonne");
                String arrive = sc.nextLine();
                construireRoute(depart,arrive);

            } else if ("acheter_carte".equals(line)) {
                acheterCarte();

            } else if ("jouer_carte".equals(line)) {
                montrerCartes();
                System.out.println("Quelle carte voulez-vous jouer ? numéro");
                String num = sc.nextLine();
                jouerCarte(num);

            } else if ("échanger".equals(line)) {
                // J'ai pas encore d'idée pour faire ça
            } else if ("finir".equals(line)) {
                joueurSuivant();
            }
        }
    }

    public void start(){
        view.affichePlateaux();
    }

}
