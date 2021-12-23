package l2.poo3.controller.terminal;

import l2.poo3.model.CaseModel;
import l2.poo3.model.CaseType.*;
import l2.poo3.model.DiceModel;
import l2.poo3.model.Enum.CartesDev;
import l2.poo3.model.Enum.Pcolor;
import l2.poo3.model.Enum.Resources;
import l2.poo3.model.PlateauxModel;
import l2.poo3.model.PlayerModel;
import l2.poo3.model.PlayerType.Ai;
import l2.poo3.model.PlayerType.Player;
import l2.poo3.view.terminal.TerminalView;

import java.util.Scanner;
import java.util.regex.Pattern;

public class TerminalController {

    private PlayerModel[] players;
    private final PlateauxModel plateaux;
    private final Scanner sc = new Scanner(System.in);
    private int quiJoue, nbrTour = 1;
    private final TerminalView view;

    private PlayerModel biggestThief = null;

    private boolean monopole = false;
    private PlayerModel monopolePlayer = null;
    private Resources monopoleRes = null;



    public TerminalController(PlateauxModel plateaux, TerminalView view){
        this.plateaux = plateaux;
        this.view = view;
        view.setController(this);
        showTitle();
        initAll();
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
        int x = askInteger("xTab");
        int y = askInteger("yTab");
        plateaux.initPlateaux(x,y);
    }

    private int askInteger(String type){
        int to_return = -1;
        while (true) {
            if(type.contains("nbrPlayer")) {
                System.out.print("Veuillez indiquer le nombre de joueurs (3 ou 4): ");
            }else if(type.contains("xTab")){
                System.out.print("Veuillez indiquer la longueur du tableau: ");
            }else if(type.contains("yTab")){
                System.out.print("Veuillez indiquer la largeur du tableau: ");
            }else if(type.contains("xBuild")){
                System.out.print("Veuillez indiquer la cordonne x: ");
            }else if(type.contains("idCartes")){
                System.out.print("Veuillez indiquer l'id de la carte que vous voulez utiliser: ");
            }else if(type.contains("idRessources")){
                System.out.print("Veuillez indiquer l'id de la ressource que vous voulez obtenir: ");
            }
            String input = sc.next();
            try {
                to_return = Integer.parseInt(input);
                if(type.contains("nbrPlayer")) {
                    if (to_return == 3 || to_return == 4) {
                       return to_return;
                    }
                }else if(type.contains("idCartes") || type.contains("idRessources")){
                    if(to_return >= 1 && to_return <= 5){
                        return to_return;
                    }
                }else {
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
            }else if(type.contains("yesno")){
                System.out.print("(oui / non): ");
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
        if(players != null && players[quiJoue] instanceof Player){
            view.afficheRessource(players[quiJoue], false);
        }
    }

    public void construireBat() {
        String type = askString("cb");
        int x = askInteger("xBuild")-1;
        int y = ((int)askString("yBuild").charAt(0)-65);
        if(verifCase(y, x)){
            if(plateaux.getPlateaux()[y][x] instanceof Batiment){

                if(type.contains("col")){
                    if(players[quiJoue].getResources().get(Resources.BOIS) >= 1 && players[quiJoue].getResources().get(Resources.ARGILE) >= 1 && players[quiJoue].getResources().get(Resources.BLE) >= 1 && players[quiJoue].getResources().get(Resources.MOUTON) >= 1) {
                        boolean allowBuild = false;

                        if (y + 1 <= getPlateaux().getLength_y() - 1 && plateaux.getPlateaux()[y + 1][x] instanceof Route && (((Route) plateaux.getPlateaux()[y + 1][x]).getPlayer() == players[quiJoue] || ((Route) plateaux.getPlateaux()[y + 1][x]).getPlayer() == null)) {
                            allowBuild = true;
                        } else if (y - 1 >= 0 && plateaux.getPlateaux()[y - 1][x] instanceof Route && (((Route) plateaux.getPlateaux()[y - 1][x]).getPlayer() == players[quiJoue] || ((Route) plateaux.getPlateaux()[y - 1][x]).getPlayer() == null)) {
                            allowBuild = true;
                        } else if (x + 1 <= getPlateaux().getLength_x() - 1 && plateaux.getPlateaux()[y][x + 1] instanceof Route && (((Route) plateaux.getPlateaux()[y][x + 1]).getPlayer() == players[quiJoue] || ((Route) plateaux.getPlateaux()[y][x+1]).getPlayer() == null)) {
                            allowBuild = true;
                        } else if (x - 1 >= 0 && plateaux.getPlateaux()[y][x - 1] instanceof Route && (((Route) plateaux.getPlateaux()[y][x - 1]).getPlayer() == players[quiJoue] || ((Route) plateaux.getPlateaux()[y][x-1]).getPlayer() == null)) {
                            allowBuild = true;
                        }

                        if (y + 1 <= getPlateaux().getLength_y() - 1 && plateaux.getPlateaux()[y + 1][x] instanceof Route && ((Route) plateaux.getPlateaux()[y + 1][x]).getPlayer() != players[quiJoue] && ((Route) plateaux.getPlateaux()[y + 1][x]).getPlayer() != null) {
                            allowBuild = false;
                        } else if (y - 1 >= 0 && plateaux.getPlateaux()[y - 1][x] instanceof Route && ((Route) plateaux.getPlateaux()[y - 1][x]).getPlayer() != players[quiJoue] && ((Route) plateaux.getPlateaux()[y - 1][x]).getPlayer() != null) {
                            allowBuild = false;
                        } else if (x + 1 <= getPlateaux().getLength_x() - 1 && plateaux.getPlateaux()[y][x + 1] instanceof Route && ((Route) plateaux.getPlateaux()[y][x + 1]).getPlayer() != players[quiJoue] && ((Route) plateaux.getPlateaux()[y][x+1]).getPlayer() != null) {
                            allowBuild = false;
                        } else if (x - 1 >= 0 && plateaux.getPlateaux()[y][x - 1] instanceof Route && ((Route) plateaux.getPlateaux()[y][x - 1]).getPlayer() != players[quiJoue] && ((Route) plateaux.getPlateaux()[y][x-1]).getPlayer() != null) {
                            allowBuild = false;
                        }

                        if(allowBuild) {

                            plateaux.getPlateaux()[y][x].setName("C " + players[quiJoue].getColor().toString().charAt(0));
                            ((Batiment) plateaux.getPlateaux()[y][x]).setPlayer(players[quiJoue]);
                            System.out.println("Vous avez construit une colonie");

                            players[quiJoue].setResources(Resources.BOIS, players[quiJoue].getResources().get(Resources.BOIS) - 1);
                            players[quiJoue].setResources(Resources.ARGILE, players[quiJoue].getResources().get(Resources.ARGILE) - 1);
                            players[quiJoue].setResources(Resources.BLE, players[quiJoue].getResources().get(Resources.BLE) - 1);
                            players[quiJoue].setResources(Resources.MOUTON, players[quiJoue].getResources().get(Resources.MOUTON) - 1);


                            players[quiJoue].setPointDeVic(players[quiJoue].getPointDeVic() + 1);
                        }else {
                            System.out.println("Vous pouvez pas contruire une collonie ici");
                        }
                    }else{
                        System.out.println("Vous avez pas asses de ressources");
                    }
                }else if(type.contains("vil")) {
                    if (nbrTour > 2) {
                        if (plateaux.getPlateaux()[y][x].getName().contains("C")) {
                            if (players[quiJoue].getResources().get(Resources.BLE) >= 2 && players[quiJoue].getResources().get(Resources.MINERAI) >= 3) {
                                plateaux.getPlateaux()[y][x].setName("V " + players[quiJoue].getColor().toString().charAt(0));
                                ((Batiment) plateaux.getPlateaux()[y][x]).setPlayer(players[quiJoue]);
                                System.out.println("Vous avez construit une ville");

                                players[quiJoue].setResources(Resources.BLE,players[quiJoue].getResources().get(Resources.BLE) - 2);
                                players[quiJoue].setResources(Resources.MINERAI,players[quiJoue].getResources().get(Resources.MINERAI) - 3);


                                players[quiJoue].setPointDeVic(players[quiJoue].getPointDeVic() + 1);
                            } else {
                                System.out.println("Vous avez pas asses de ressources");
                            }
                        } else {
                            System.out.println("Vous devez construire d'abord une collonie");
                        }
                    }else {
                        System.out.println("C'est pas le bon tour pour construire");
                    }
                }
            }else {
                System.out.println("C'est pas un endroit pour construire un batiment");
            }
        }
    }

    public void construireRoute() {
        if(players[quiJoue].getResources().get(Resources.BOIS) >= 1 && players[quiJoue].getResources().get(Resources.ARGILE) >= 1) {
            CaseModel[][] plateaux = this.plateaux.getPlateaux();
            int x = askInteger("xBuild") - 1;
            int y = ((int) askString("yBuild").charAt(0) - 65);
            boolean allowBuild = false;
            if (verifCase(y, x)) {
                if (y + 1 <= getPlateaux().getLength_y() - 1 && plateaux[y + 1][x] instanceof Batiment && ((Batiment) plateaux[y + 1][x]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (y - 1 >= 0 && plateaux[y - 1][x] instanceof Batiment && ((Batiment) plateaux[y - 1][x]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (x + 1 <= getPlateaux().getLength_x() - 1 && plateaux[y][x + 1] instanceof Batiment && ((Batiment) plateaux[y][x + 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (x - 1 >= 0 && plateaux[y][x - 1] instanceof Batiment && ((Batiment) plateaux[y][x - 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                }else if (x + 1 <= getPlateaux().getLength_x() - 1 && y + 1 <= getPlateaux().getLength_y() - 1 && plateaux[y + 1][x + 1] instanceof Route && ((Route) plateaux[y + 1][x + 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (x - 1 >= 0 && y - 1 >= 0 && plateaux[y - 1][x - 1] instanceof Route && ((Route) plateaux[y - 1][x - 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (y - 1 >= 0 && x + 1 <= getPlateaux().getLength_x() - 1 && plateaux[y - 1][x + 1] instanceof Route && ((Route) plateaux[y - 1][x + 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (y + 1 <= getPlateaux().getLength_y() - 1 && x - 1 >= 0 && plateaux[y + 1][x - 1] instanceof Route && ((Route) plateaux[y + 1][x - 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (y % 2 != 0){
                    if (x + 2 <= getPlateaux().getLength_x() - 1 && plateaux[y][x + 2] instanceof Route && ((Route) plateaux[y][x + 2]).getPlayer() == players[quiJoue]) {
                        allowBuild = true;
                    } else if (x - 2 >= 0 && plateaux[y][x - 2] instanceof Route && ((Route) plateaux[y][x - 2]).getPlayer() == players[quiJoue]) {
                        allowBuild = true;
                    }
                }else if(x % 2 != 0){
                    if (y - 2 >= 0 && plateaux[y - 2][x] instanceof Route && ((Route) plateaux[y - 2][x]).getPlayer() == players[quiJoue]) {
                        allowBuild = true;
                    } else if (y + 2 <= getPlateaux().getLength_y() - 1 && plateaux[y + 2][x] instanceof Route && ((Route) plateaux[y + 2][x]).getPlayer() == players[quiJoue]) {
                        allowBuild = true;
                    }
                }

                if (y + 1 <= getPlateaux().getLength_y() - 1 && plateaux[y + 1][x] instanceof Batiment && ((Batiment) plateaux[y + 1][x]).getPlayer() != players[quiJoue] && ((Batiment) plateaux[y+1][x]).getPlayer() != null) {
                    allowBuild = false;
                } else if (y - 1 >= 0 && plateaux[y - 1][x] instanceof Batiment && ((Batiment) plateaux[y - 1][x]).getPlayer() != players[quiJoue] && ((Batiment) plateaux[y-1][x]).getPlayer() != null) {
                    allowBuild = false;
                } else if (x + 1 <= getPlateaux().getLength_x() - 1 && plateaux[y][x + 1] instanceof Batiment && ((Batiment) plateaux[y][x + 1]).getPlayer() != players[quiJoue] && ((Batiment) plateaux[y][x + 1]).getPlayer() != null) {
                    allowBuild = false;
                } else if (x - 1 >= 0 && plateaux[y][x - 1] instanceof Batiment && ((Batiment) plateaux[y][x - 1]).getPlayer() != players[quiJoue] && ((Batiment) plateaux[y][x - 1]).getPlayer() != null) {
                    allowBuild = false;
                }





                if (plateaux[y][x] instanceof Route) {
                    if (allowBuild) {
                        plateaux[y][x].setName("R " + players[quiJoue].getColor().toString().charAt(0));
                        ((Route) plateaux[y][x]).setPlayer(players[quiJoue]);

                        players[quiJoue].setResources(Resources.BOIS,players[quiJoue].getResources().get(Resources.BOIS) - 1);
                        players[quiJoue].setResources(Resources.ARGILE,players[quiJoue].getResources().get(Resources.ARGILE) - 1);

                        System.out.println("Vous avez construit une route");

                        //System.out.println(" | ! " + checkRoad(x,y, (Route) plateaux[y][x]) + " ! | ");

                    } else {
                        System.out.println("Vous avez pas le droit de construire une route ici");
                    }
                } else {
                    System.out.println("C'est pas un endroit pour construire une route");
                }
            }
        }else{
            System.out.println("Vous avez pas assez de ressources.");
        }
    }

    private void checkBiggestRoad(int x, int y){

    }

    public int checkRoad(int x, int y, Route father){
        if(endRoad(x, y)){
            System.out.println("| ! |");
            return 1;
        }

        int upl = 0, downl = 0, upr = 0, downr = 0;

        if (x + 1 <= getPlateaux().getLength_x() - 1 && y + 1 <= getPlateaux().getLength_y() - 1 && plateaux.getPlateaux()[y + 1][x + 1] instanceof Route && ((Route) plateaux.getPlateaux()[y + 1][x + 1]).getPlayer() == players[quiJoue] && plateaux.getPlateaux()[y + 1][x + 1] != father) {
            downr = checkRoad(x+1,y+1, (Route) plateaux.getPlateaux()[y + 1][x + 1]);
        }
        if (x - 1 >= 0 && y - 1 >= 0 && plateaux.getPlateaux()[y - 1][x - 1] instanceof Route && ((Route) plateaux.getPlateaux()[y - 1][x - 1]).getPlayer() == players[quiJoue] && plateaux.getPlateaux()[y - 1][x - 1] != father) {
            upl = checkRoad(x-1, y-1, (Route) plateaux.getPlateaux()[y - 1][x - 1]);
        }
        if (y - 1 >= 0 && x + 1 <= getPlateaux().getLength_x() - 1 && plateaux.getPlateaux()[y - 1][x + 1] instanceof Route && ((Route) plateaux.getPlateaux()[y - 1][x + 1]).getPlayer() == players[quiJoue] && plateaux.getPlateaux()[y - 1][x + 1] != father) {
            upr = checkRoad(x+1, y-1, (Route) plateaux.getPlateaux()[y - 1][x - 1]);
        }
        if (y + 1 <= getPlateaux().getLength_y() - 1 && x - 1 >= 0 && plateaux.getPlateaux()[y + 1][x - 1] instanceof Route && ((Route) plateaux.getPlateaux()[y + 1][x - 1]).getPlayer() == players[quiJoue] && plateaux.getPlateaux()[y + 1][x - 1] != father) {
            downl = checkRoad(x-1,y+1, (Route) plateaux.getPlateaux()[y + 1][x - 1]);
        }
        return 1+Math.max(Math.max(upr,upl),Math.max(downl,downr));
    }

    private boolean endRoad(int x, int y){
        if(plateaux.getPlateaux()[y][x] instanceof  Route){
            boolean to_return = false;
            if (x + 1 <= getPlateaux().getLength_x() - 1 && y + 1 <= getPlateaux().getLength_y() - 1 && plateaux.getPlateaux()[y + 1][x + 1] instanceof Route && ((Route) plateaux.getPlateaux()[y + 1][x + 1]).getPlayer() != players[quiJoue]) {
                to_return = true;
            } else {
                to_return = false;
            }
            if (x - 1 >= 0 && y - 1 >= 0 && plateaux.getPlateaux()[y - 1][x - 1] instanceof Route && ((Route) plateaux.getPlateaux()[y - 1][x - 1]).getPlayer() != players[quiJoue]) {
                to_return = true;
            } else {
                to_return = false;
            }
            if (y - 1 >= 0 && x + 1 <= getPlateaux().getLength_x() - 1 && plateaux.getPlateaux()[y - 1][x + 1] instanceof Route && ((Route) plateaux.getPlateaux()[y - 1][x + 1]).getPlayer() != players[quiJoue]) {
                to_return = true;
            } else {
                to_return = false;
            }
            if (y + 1 <= getPlateaux().getLength_y() - 1 && x - 1 >= 0 && plateaux.getPlateaux()[y + 1][x - 1] instanceof Route && ((Route) plateaux.getPlateaux()[y + 1][x - 1]).getPlayer() != players[quiJoue]) {
                to_return = true;
            }else {
                to_return = false;
            }
            return to_return;
        }
        return false;
    }

    public void acheterCarte() {
        if (players != null && players[quiJoue] instanceof Player) {
            if (players[quiJoue].getResources().get(Resources.MINERAI) >= 1 && players[quiJoue].getResources().get(Resources.MOUTON) >= 1 && players[quiJoue].getResources().get(Resources.BLE) >= 1) {
                CartesDev carte = plateaux.getCartesDevList().getList().get(plateaux.getCarteDevPos());
                System.out.println(plateaux.getCartesDevList().getList());
                if (carte.equals(CartesDev.PointVictoire)) {
                    players[quiJoue].setInvPVic(players[quiJoue].getInvPVic() + 1);
                }
                players[quiJoue].updateCartes(carte, true);
                System.out.println("Vous avez obtenu une carte de " + carte);
                plateaux.setCarteDevPos(plateaux.getCarteDevPos() + 1);

                players[quiJoue].setResources(Resources.MINERAI,players[quiJoue].getResources().get(Resources.MINERAI) - 1);
                players[quiJoue].setResources(Resources.MOUTON,players[quiJoue].getResources().get(Resources.MOUTON) - 1);
                players[quiJoue].setResources(Resources.BLE,players[quiJoue].getResources().get(Resources.BLE) - 1);

            } else {
                System.out.println("Vous avez pas assez de ressources");
            }
        }
    }

    public void montrerCartes() {
        if(players != null && players[quiJoue] instanceof Player){
            view.afficheCarteDev(false,players[quiJoue]);
        }
    }

    public void jouerCarte() {
        view.afficheCarteDev(true, players[quiJoue]);
        CartesDev carte = idToCartes(askInteger("idCartes"));
        if(carte == CartesDev.PointVictoire){
            System.out.println("Vous pouvez pas utiliser cette carte");
        }else {
            if (players[quiJoue].getCartesDev().get(carte) > 0) {
                players[quiJoue].updateCartes(carte, false);
                if(carte == CartesDev.Route){
                    players[quiJoue].setResources(Resources.ARGILE, players[quiJoue].getResources().get(Resources.ARGILE)+2);
                    players[quiJoue].setResources(Resources.BOIS, players[quiJoue].getResources().get(Resources.BOIS)+2);
                    System.out.println("Vous avez obtenu les ressources necessaire pour construire 2 routes");
                }else if(carte == CartesDev.Invention){
                    int num = 0;
                    while (num < 2) {
                        view.afficheRessource(players[quiJoue], true);
                        Resources get = idToRessources(askInteger("idRessources"));
                        players[quiJoue].setResources(get, players[quiJoue].getResources().get(get)+1);
                        System.out.println("Vous avez obtenue une ressource de type " + get);
                        num++;
                    }
                }else if(carte == CartesDev.Monopole){
                    view.afficheRessource(players[quiJoue], true);
                    Resources get = idToRessources(askInteger("idRessources"));
                    for(PlayerModel p : players){
                        if(p != players[quiJoue]){
                            int nbr = p.getResources().get(get);
                            p.setResources(get, 0);
                            players[quiJoue].setResources(get, players[quiJoue].getResources().get(get) + nbr);
                            System.out.println("Le jouer: " + players[quiJoue].getColor() + " a voler tout votre stock de " + get);
                            System.out.println("Vous avez voler tout le stock de " + get + " aux joueur " + p.getColor());
                        }
                    }
                    monopole = true;
                    monopolePlayer = players[quiJoue];
                    monopoleRes = get;
                }else if(carte == CartesDev.Chevalier){
                    thief();
                    players[quiJoue].increaseNbrKnight();
                    updateBiggestKnight();
                }
            } else {
                System.out.println("Vous avez pas assez de cartes");
            }
        }
    }

    private void updateBiggestKnight(){
        for(PlayerModel p : players){
            if(biggestThief == null){
                biggestThief = p;
            }else if(p != biggestThief){
                if(p.getNbrKnight() > biggestThief.getNbrKnight()){
                    biggestThief.setPointDeVic(biggestThief.getPointDeVic()-2);
                    biggestThief.setMoreKnight(false);
                    p.setMoreKnight(true);
                    p.setPointDeVic(p.getPointDeVic()+2);
                    biggestThief = p;
                }
            }
        }
    }

    private CartesDev idToCartes(int id){
        switch (id){
            case 1: return CartesDev.Chevalier;
            case 2: return CartesDev.Monopole;
            case 3: return CartesDev.Route;
            case 4: return CartesDev.Invention;
            case 5: return CartesDev.PointVictoire;
        }
        return null;
    }

    private Resources idToRessources(int id){
        switch (id){
            case 1: return Resources.BOIS;
            case 2: return Resources.BLE;
            case 3: return Resources.ARGILE;
            case 4: return Resources.MINERAI;
            case 5: return Resources.MOUTON;
        }
        return null;
    }

    public void echangerPort() {
        CaseModel[][] plateaux = this.plateaux.getPlateaux();
        System.out.println("Veuillez choisir le port que vous voulez utiliser");
        int x = askInteger("xBuild") - 1;
        int y = ((int) askString("yBuild").charAt(0) - 65);
        if(verifCords(y,x)) {
            boolean allowUse = false;
            if (plateaux[y][x] instanceof Port) {
                if (x + 1 <= getPlateaux().getLength_x() - 1 && y + 1 <= getPlateaux().getLength_y() - 1 && plateaux[y + 1][x + 1] instanceof Batiment && ((Batiment) plateaux[y + 1][x + 1]).getPlayer() == players[quiJoue]) {
                    allowUse = true;
                } else if (x - 1 >= 0 && y - 1 >= 0 && plateaux[y - 1][x - 1] instanceof Batiment && ((Batiment) plateaux[y - 1][x - 1]).getPlayer() == players[quiJoue]) {
                    allowUse = true;
                } else if (y - 1 >= 0 && x + 1 <= getPlateaux().getLength_x() - 1 && plateaux[y - 1][x + 1] instanceof Batiment && ((Batiment) plateaux[y - 1][x + 1]).getPlayer() == players[quiJoue]) {
                    allowUse = true;
                } else if (y + 1 <= getPlateaux().getLength_y() - 1 && x - 1 >= 0 && plateaux[y + 1][x - 1] instanceof Batiment && ((Batiment) plateaux[y + 1][x - 1]).getPlayer() == players[quiJoue]) {
                    allowUse = true;
                }
                if (allowUse) {
                    view.echangerPort((Player) players[quiJoue], (Port) plateaux[y][x]);
                } else {
                    System.out.println("Vous devez avoir une collonie ou une ville a cote du port");
                }
            }
        }
    }

    public void joueurSuivant() {
        players[quiJoue].setThrowDice(false);
        quiJoue++;
        if (quiJoue == players.length){
            quiJoue = 0;
            nbrTour++;
        }

        if(players[quiJoue] == monopolePlayer){
            monopole = false;
            monopoleRes = null;
            monopolePlayer = null;
        }
        view.affichePlateaux();
    }

    public boolean verifCase(int y, int x){
        if(verifCords(y,x)) {
            CaseModel cases = plateaux.getPlateaux()[y][x];
            if (cases != null) {
                if (cases instanceof Vide) {
                    System.out.println("C'est une case vide");
                    return false;
                }
                if (!(cases instanceof Route) && !(cases instanceof Batiment)) {
                    System.out.println("C'est n'est pas une route ni un batiment");
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
                }
                System.out.println("La case est aucuper");
            }
        }
        return false;
    }

    private boolean verifCords(int y, int x){
        if(y < 0 || x < 0 || y > plateaux.getLength_y()-1 || x > plateaux.getLength_x()-1){
            System.out.println("Hors du plateaux, merci de resseyer");
            return false;
        }
        return true;
    }

    private void askQuestion(){

        System.out.println("\nTour du jouer: " + players[quiJoue].getColor());

        System.out.println("Que voulez vous faire?:");
        System.out.println("    - Consulter les ressources (cr): ");
        System.out.println("    - Construire des batiments (cb): ");
        System.out.println("    - Afficher les commandes (help): ");
        System.out.println("    - Echanger avec le port (ep): ");
        System.out.println("    - Afficher le plateaux (af): ");
        System.out.println("    - Construire une route (br): ");
        System.out.println("    - Acheter des cartes (ac): ");
        System.out.println("    - Montrer des cartes (mc): ");
        System.out.println("    - Jouer une carte (pc): ");
        System.out.println("    - Lander de (ld): ");
        System.out.println("    - Finir (end): ");
    }

    public void throwdDice(){
        if(!players[quiJoue].isThrowDice()) {
            DiceModel dice = new DiceModel();
            int number = dice.throwDice();
            System.out.println("lancer de dés\nLe résultat est: " + number);
            players[quiJoue].setThrowDice(true);
            if(number == 7) {
                if(players[quiJoue] instanceof Player) {
                    thief();
                }
            }else {
                plateaux.generateRessources(number);
            }
        }else {
            System.out.println("Vous avez deja lancer le des");
        }
    }

    private void thief(){
        boolean good = true;
        while (good) {
            System.out.println("Merci de choisir, les nouvelles coordonnes du volleur");
            int x = askInteger("xBuild") - 1;
            int y = ((int) askString("yBuild").charAt(0) - 65);
            String result = plateaux.moveThief(x, y);
            if(result.contains("present")){
                System.out.println("Le voleur est deja sur cette case merci de le deplacer.");
            }else if(result.contains("noneRessources")){
                System.out.println("Merci de choisir une case qui peut generer des ressources");
            }else if(result.contains("ok")){
                Resources res = plateaux.stealOneRessources(x,y, players[quiJoue]);
                if(res != null) {
                    System.out.println("Vous avez voler une ressource: " + res);
                    for (PlayerModel p : players) {
                        p.setThiefPlay(true);
                    }
                }
                view.deleteRessources(players[quiJoue]);
                good = false;
            }
        }
    }

    private void debug(){
        System.out.println("DEBUG MODE!");
        players[quiJoue].debug();
    }

    private void askDecission() {
        while (true) {
            if(monopole && players[quiJoue] != monopolePlayer){
                System.out.println("Le jouer: " + monopolePlayer.getColor() + " a voler tout votre stock de " + monopoleRes);
            }
            if (players[quiJoue].isThiefPlay()) {
                view.deleteRessources(players[quiJoue]);
            } else {
                askQuestion();
                System.out.print("Merci d'indiquer votre choix: ");
                String rep = sc.next().toLowerCase();
                System.out.println();
                if(rep.contains("debug")){
                    debug();
                    break;
                }
                if(rep.contains("help")){
                    askQuestion();
                    break;
                }
                if (nbrTour < 3) {
                    if (rep.contains("cb")) {
                        construireBat();
                        break;
                    } else if (rep.contains("br")) {
                        construireRoute();
                        break;
                    } else if (rep.contains("end")) {
                        if (players[quiJoue].getPointDeVic() > 0) {
                            joueurSuivant();
                            for (int i = 0; i < 25; i++) {
                                System.out.println();
                            }
                            break;
                        } else {
                            System.out.println("Vous devez en moins contruite une collonie");
                        }
                    } else if (rep.contains("af")) {
                        view.affichePlateaux();
                        break;
                    } else {
                        System.out.println("Pendant les 2 premiers tours vous pouvez que construire des batiments ou des routes (br) (cb)");
                    }
                } else if (rep.contains("ld")) {
                    throwdDice();
                    break;
                } else if (players[quiJoue].isThrowDice() && nbrTour > 2) {
                    if (rep.contains("cr")) {
                        consulterRessources();
                        break;
                    }
                    if (rep.contains("cb")) {
                        construireBat();
                        break;
                    }
                    if (rep.contains("br")) {
                        construireRoute();
                        break;
                    }
                    if (rep.contains("pc")) {
                        jouerCarte();
                        break;
                    }
                    if (rep.contains("af")) {
                        view.affichePlateaux();
                        break;
                    }
                    if (rep.contains("ep")) {
                        echangerPort();
                        break;
                    }
                    if (rep.contains("ac")) {
                        acheterCarte();
                        break;
                    }
                    if (rep.contains("mc")) {
                        montrerCartes();
                        break;
                    }
                    if (rep.contains("end")) {
                        joueurSuivant();
                        for (int i = 0; i < 25; i++) {
                            System.out.println();
                        }
                        break;
                    }
                } else if (nbrTour > 2) {
                    System.out.println("D'abord il faut lancer les dés (ld)");
                    break;
                } else {
                    System.out.println("Reponse incorrecte!");
                }
            }
        }
    }

    private String askString(String type){
        while (true) {
            if(type.contains("cb")){
                System.out.print("Que voulez-vous construire ? (colonie/ville) ");
            }else if(type.contains("yBuild")){
                System.out.print("Veuillez indiquer la cordonne y: ");
            }
            String rep = sc.next().toLowerCase();

            if(rep.contains("col") || rep.contains("vil")) {
                return rep;
            }else if(type.contains("yBuild")){
                Pattern allLetter = Pattern.compile("[a-zA-Z]+");
                if(allLetter.matcher(rep).find()){
                    return rep.toUpperCase();
                }
            }
            System.out.println("Reponse incorrecte!");
        }
    }



    public void round(){
        view.affichePlateaux();
        while(true){
            if(players[quiJoue] instanceof Player){
                askDecission();
            }else {
                System.out.println("TOUR de l'ia");
                joueurSuivant();
            }
            /*line = sc.nextLine();

            if ("consulter_ress".equals(line)) {
                consulterRessources();

            } else if ("construire_batiment".equals(line)) {
                System.out.println("Que voulez-vous construire ? colonnie/cité");
                String choix = sc.nextLine();
                System.out.println("A quel emplacement ? ligne colonne");
                String lieu = sc.nextLine();
                //construireBat(choix, lieu);

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
            }*/
        }
    }

    public void start(){
        round();
    }

}
