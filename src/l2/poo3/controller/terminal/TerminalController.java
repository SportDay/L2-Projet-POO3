package l2.poo3.controller.terminal;

import l2.poo3.Other.StringUtil;
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
            }
            String input = sc.next();
            try {
                to_return = Integer.parseInt(input);
                if(type.contains("nbrPlayer")) {
                    if (to_return == 3 || to_return == 4) {
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
            ((Player) players[quiJoue]).afficheRessource();
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
                        plateaux.getPlateaux()[y][x].setName("C " + players[quiJoue].getColor().toString().charAt(0));
                        ((Batiment) plateaux.getPlateaux()[y][x]).setPlayer(players[quiJoue]);
                        System.out.println("Vous avez construit une colonie");

                        players[quiJoue].getResources().put(Resources.BOIS, players[quiJoue].getResources().get(Resources.BOIS) - 1);
                        players[quiJoue].getResources().put(Resources.ARGILE, players[quiJoue].getResources().get(Resources.ARGILE) - 1);
                        players[quiJoue].getResources().put(Resources.BLE, players[quiJoue].getResources().get(Resources.BLE) - 1);
                        players[quiJoue].getResources().put(Resources.MOUTON, players[quiJoue].getResources().get(Resources.MOUTON) - 1);

                        players[quiJoue].setPointDeVic(players[quiJoue].getPointDeVic()+1);
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
                                players[quiJoue].getResources().put(Resources.BLE, players[quiJoue].getResources().get(Resources.BLE) - 2);
                                players[quiJoue].getResources().put(Resources.MINERAI, players[quiJoue].getResources().get(Resources.MINERAI) - 3);
                                players[quiJoue].setPointDeVic(players[quiJoue].getPointDeVic() + 1);
                            } else {
                                System.out.println("Vous avez pas asses de ressources");
                            }
                        } else {
                            System.out.println("Vous devez construire d'abord une collonie");
                        }
                    }
                }else {
                    System.out.println("C'est pas le bon tour pour construire");
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

                if (y + 1 <= getPlateaux().getLength_y() - 1 && plateaux[y + 1][x] instanceof Route && ((Route) plateaux[y + 1][x]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (y - 1 >= 0 && plateaux[y - 1][x] instanceof Route && ((Route) plateaux[y - 1][x]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (x + 1 <= getPlateaux().getLength_x() - 1 && plateaux[y][x + 1] instanceof Route && ((Route) plateaux[y][x + 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (x - 1 >= 0 && plateaux[y][x - 1] instanceof Route && ((Route) plateaux[y][x - 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                }
                if (y + 1 <= getPlateaux().getLength_y() - 1 && plateaux[y + 1][x] instanceof Batiment && ((Batiment) plateaux[y + 1][x]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (y - 1 >= 0 && plateaux[y - 1][x] instanceof Batiment && ((Batiment) plateaux[y - 1][x]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (x + 1 <= getPlateaux().getLength_x() - 1 && plateaux[y][x + 1] instanceof Batiment && ((Batiment) plateaux[y][x + 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (x - 1 >= 0 && plateaux[y][x - 1] instanceof Batiment && ((Batiment) plateaux[y][x - 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                }


                if (plateaux[y][x] instanceof Route) {
                    if (allowBuild) {
                        plateaux[y][x].setName("R " + players[quiJoue].getColor().toString().charAt(0));
                        ((Route) plateaux[y][x]).setPlayer(players[quiJoue]);
                        players[quiJoue].getResources().put(Resources.BOIS, players[quiJoue].getResources().get(Resources.BOIS) - 1);
                        players[quiJoue].getResources().put(Resources.ARGILE, players[quiJoue].getResources().get(Resources.ARGILE) - 1);
                        System.out.println("Vous avez construit une route");
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

    public void acheterCarte() {
        if (players != null && players[quiJoue] instanceof Player) {
            if (players[quiJoue].getResources().get(Resources.MINERAI) >= 1 && players[quiJoue].getResources().get(Resources.MOUTON) >= 1 && players[quiJoue].getResources().get(Resources.BLE) >= 1) {
                CartesDev carte = plateaux.getCartesDevList().getList().get(plateaux.getCarteDevPos());
                System.out.println(plateaux.getCartesDevList().getList());
                if (carte.equals(CartesDev.PointVictoire)) {
                    players[quiJoue].setPointDeVic(players[quiJoue].getPointDeVic() + 1);
                    System.out.println("Vous avez obtenu un point de victoire");
                } else {
                    players[quiJoue].updateCartes(carte);
                    System.out.println("Vous avez obtenu une carte de " + carte);
                }
                plateaux.setCarteDevPos(plateaux.getCarteDevPos() + 1);
                players[quiJoue].getResources().put(Resources.MINERAI, players[quiJoue].getResources().get(Resources.MINERAI) - 1);
                players[quiJoue].getResources().put(Resources.MOUTON, players[quiJoue].getResources().get(Resources.MOUTON) - 1);
                players[quiJoue].getResources().put(Resources.BLE, players[quiJoue].getResources().get(Resources.BLE) - 1);

            } else {
                System.out.println("Vous avez pas assez de ressources");
            }
        }
    }

    public void montrerCartes() {
        if(players != null && players[quiJoue] instanceof Player){
            ((Player) players[quiJoue]).afficheCarteDev(false);
        }
    }

    public void jouerCarte() {

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
                    ((Port) plateaux[y][x]).echangerPort((Player) players[quiJoue]);
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
        System.out.println("    - Echanger avec le port (ep): ");
        System.out.println("    - Afficher le plateaux (af): ");
        System.out.println("    - Construire une route (br): ");
        System.out.println("    - Acheter des cartes (ac): ");
        System.out.println("    - Montrer des cartes (mc): ");
        System.out.println("    - Jouer une carte (pc): ");
        System.out.println("    - Lander de (ld): ");
        System.out.println("    - Finir (end): ");
    }

    public void thwrodDice(){
        if(!players[quiJoue].isThrowDice()) {
            DiceModel dice = new DiceModel();
            int number = dice.throwDice();
            System.out.println("lancer de dés\nLe résultat est: " + number);
            players[quiJoue].setThrowDice(true);
            if(number == 7) {
                //voleur
            }else {
                plateaux.generateRessources(number);
            }
        }else {
            System.out.println("Vous avez deja lancer le des");
        }
    }

    private void askDecission(){
        while (true) {
            askQuestion();
            System.out.print("Merci d'indiquer votre choix: ");
            String rep = sc.next().toLowerCase();
            System.out.println(nbrTour);

            if(nbrTour < 3) {
                if (rep.contains("cb")) {
                    construireBat();
                    break;
                }
                if (rep.contains("br")) {
                    construireRoute();
                    break;
                }
                if (rep.contains("end")) {
                    joueurSuivant();
                    for (int i = 0; i < 25; i ++){
                        System.out.println();
                    }
                    break;
                }
                if (rep.contains("af")) {
                    view.affichePlateaux();
                    break;
                }
                System.out.println("Pendant les 2 premiers tours vous pouvez que construire des batiments ou des routes (br) (cb)");
            }

            if (rep.contains("ld")) {
                thwrodDice();
                break;
            }

            if(players[quiJoue].isThrowDice() && nbrTour > 2) {
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
                    for (int i = 0; i < 25; i ++){
                        System.out.println();
                    }
                    break;
                }
            }else if(nbrTour > 2){
                System.out.println("D'abord il faut lancer les dés (ld)");
                break;
            }


            System.out.println("Reponse incorrecte!");
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
