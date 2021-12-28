package l2.poo3.view.terminal;

import l2.poo3.Other.StringUtil;
import l2.poo3.controller.terminal.*;
import l2.poo3.model.*;
import l2.poo3.model.CaseType.*;
import l2.poo3.model.Enum.*;
import l2.poo3.model.PlayerType.*;
import l2.poo3.view.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class TerminalView implements ViewModel {

    private TerminalController controller;
    private PlateauxModel plateaux;

    private final Scanner sc = new Scanner(System.in);


    public TerminalController getController() {
        return controller;
    }

    private LinkedList<Resources> removeRessource = new LinkedList<>();


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

    public void afficheRessource(PlayerModel player, boolean showNum){
        Map<Resources, Integer> resourcesList = player.getResources();
        if(resourcesList != null){
            System.out.println("\n+------------+---------+---------+---------+---------+---------+");
            System.out.println("|" + StringUtil.center("Ressources du Joueur: " + player.getColor(),62) + "|");
            System.out.println("+------------+---------+---------+---------+---------+---------+");
            System.out.print("| Ressources |");
            for(Map.Entry<Resources, Integer> x : resourcesList.entrySet()){
                System.out.print(StringUtil.center(x.getKey().toString(),9) + "|");
            }
            System.out.println("\n+------------+---------+---------+---------+---------+---------+");
            System.out.print("|  Quantite  |");
            for(Map.Entry<Resources, Integer> x : resourcesList.entrySet()){
                System.out.print(StringUtil.center(x.getValue().toString(),9) + "|");
            }
            if(showNum) {
                System.out.println("\n+------------+---------+---------+---------+---------+---------+");
                System.out.print("|     ID     |");
                for (int i = 0; i < resourcesList.size(); i++) {
                    System.out.print(StringUtil.center(i + 1 + "", 9) + "|");
                }
            }
            System.out.println("\n+------------+---------+---------+---------+---------+---------+");
        }
    }

    public void afficheCarteDev(boolean showNum,PlayerModel player){
        Map<CartesDev, Integer> resourcesList = player.getCartesDev();
        if(resourcesList != null){
            System.out.println("\n+-------------+---------------+---------------+---------------+---------------+---------------+");
            System.out.println("|" + StringUtil.center("Cartes Developpement du Joueur: " + player.getColor(),93) + "|");
            System.out.println("+-------------+---------------+---------------+---------------+---------------+---------------+");
            System.out.print("|   Cartes    |");
            for(Map.Entry<CartesDev, Integer> x : resourcesList.entrySet()){
                System.out.print(StringUtil.center(x.getKey().toString(),15) + "|");
            }
            System.out.println("\n+-------------+---------------+---------------+---------------+---------------+---------------+");
            System.out.print("|  Quantite   |");
            for(Map.Entry<CartesDev, Integer> x : resourcesList.entrySet()){
                System.out.print(StringUtil.center(x.getValue().toString(),15) + "|");
            }
            if(showNum) {
                System.out.println("\n+-------------+---------------+---------------+---------------+---------------+---------------+");
                System.out.print("|     ID      |");
                for (int i = 0; i < resourcesList.size(); i++) {
                    System.out.print(StringUtil.center(i + 1 + "", 15) + "|");
                }
            }
            System.out.println("\n+-------------+---------------+---------------+---------------+---------------+---------------+");
        }
    }

    private void afficheCommande(){
        System.out.println("Commandes:");
        System.out.println(" - Ajouter(aj)");
        System.out.println(" - Enlever(en)");
        System.out.println(" - Confirmer(co)");
        System.out.println(" - Annuler(an)");
        System.out.println(" - Afficher mes ressources(cr)");
        System.out.println(" - Afficher qui sont rajouter(ar)");
        System.out.println(" - Afficher les commandes(help)");
        System.out.println(" - Afficher la ressources a obtenir(ao)");
        System.out.println();
    }

    private void afficheAddRessource(Port port){
        if(port.getSellRessource().size() >= 1){
            System.out.println("+------------"+ StringUtil.buildTablePatternV2("---------+", port.getSellRessource().size()));
            System.out.print("| Ressources |");
            for(Resources x : port.getSellRessource()){
                System.out.print(StringUtil.center(x.toString(),9) + "|");
            }
            System.out.println("\n+------------"+ StringUtil.buildTablePatternV2("---------+", port.getSellRessource().size()));
        }else {
            System.out.println("La liste est vide");
        }
    }

    public void echangerPort(Player p, Port port){

        boolean run = true;
        afficheRessource(p, true);

        afficheCommande();
        port.setSellRessource(new ArrayList<>());
        System.out.println("Ressource que vous pouvez recevoir: " + port.getBuy());
        while (run){

            if(port.getPrix().contains("3:1") && port.getBuy() == null){
                port.setBuy(choixRessource(askInteger("idBuy")));
            }
            if(port.getPrix().contains("2:1")) {
                if (port.getSellRessource().size() < 2) {
                    System.out.println("Il faut rajouter encore: " + (2 - port.getSellRessource().size()) + " ressources");
                }
            }else if(port.getPrix().contains("3:1")) {
                if (port.getSellRessource().size() < 3) {
                    System.out.println("Il faut rajouter encore: " + (3 - port.getSellRessource().size()) + " ressources");
                }
            }

            String reponse = askString("des");

            if(reponse.contains("ajou")){
                add(askInteger("id"), p, port);
            }
            if(reponse.contains("enlev")){
                if(port.getSellRessource().size() <= 0){
                    System.out.println("La liste est vide");
                }else {
                    remove(askInteger("id"), p, port);
                }
            }
            if(reponse.contains("conf")){
                if(accept(p, port)){
                    run = false;
                }
            }
            if(reponse.contains("annul")){
                run = false;
            }
            if (reponse.contains("cr")) {
                afficheRessource(p,true);
            }
            if (reponse.contains("ar")) {
                afficheAddRessource(port);
            }
            if (reponse.contains("ao")) {
                System.out.println("Ressource que vous pouvez recevoir: " + port.getBuy());
            }
            if (reponse.contains("help")) {
                afficheCommande();
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

    private int askInteger(String type){
        int to_return = -1;
        while (true) {
            if(type.equalsIgnoreCase("id")) {
                System.out.print("Veuillez indiquer indiquer l'id de la ressource que vous voulez echanger: ");
            }else if(type.contains("idBuy")) {
                System.out.print("Veuillez indiquer indiquer l'id de la ressource que vous voulez obtenir: ");
            }else if(type.contains("remove")){
                System.out.print("Veuillez indiquer indiquer l'id de la ressource que vous voulez effacer: ");
            }

            String input = sc.next();
            try {
                to_return = Integer.parseInt(input);
                if(type.contains("id") || type.contains("idBuy") || type.contains("remove")){
                    if(to_return >= 1 && to_return <= 5){
                        return to_return;
                    }
                }else {
                    if (to_return != -1) {
                        return to_return;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Merci d'indiquer un chiffre");
            }
        }
    }

    private String askString(String type){
        while (true) {
            System.out.print("Faite votre choix: ");
            String rep = sc.next().toLowerCase();
            if(type.contains("des")) {
                if (rep.contains("aj")) {
                    return "ajout";
                }
                if (rep.contains("en")) {
                    return "enlev";
                }
                if (rep.contains("co")) {
                    return "conf";
                }
                if (rep.contains("an")) {
                    return "annul";
                }
                if (rep.contains("cr")) {
                    return "cr";
                }
                if (rep.contains("ar")) {
                    return "ar";
                }
                if (rep.contains("ao")) {
                    return "ao";
                }
                if (rep.contains("help")) {
                    return "help";
                }
            }else if (type.equalsIgnoreCase("remove1")){
                    if (rep.contains("aj")) {
                        return "ajout";
                    }
                    if (rep.contains("en")) {
                        return "enlev";
                    }
                    if (rep.contains("co")) {
                        return "conf";
                    }
                    if (rep.contains("cr")) {
                        return "cr";
                    }
                    if (rep.contains("ar")) {
                        return "ar";
                    }
                    if (rep.contains("help")) {
                        return "help";
                    }
            }
            System.out.println("Reponse incorrecte!");

        }
    }

    private boolean askYesNo(){
        while (true) {
            System.out.print("(oui / non): ");
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

    private void add(int id, Player p, Port port){
        Resources to_add = choixRessource(id);
        if(port.getPrix().contains("2:1")){
            if(port.getSellRessource().size() > 2){
                System.out.println("Vous avait rajoute le bon nombre de ressources");
                return;
            }
        }else if(port.getPrix().contains("3:1")){
            if(port.getSellRessource().size() > 3){
                System.out.println("Vous avait rajoute le bon nombre de ressources");
                return;
            }
        }
        if(p.getResources().get(to_add) >= 1) {
            port.getSellRessource().add(to_add);
            p.setResources(to_add,p.getResources().get(to_add) - 1);


            System.out.println("Vous avez avez rajouter " + to_add);
        }else {
            System.out.println("Vous avez pas asses de cette ressource merci de choisir une autre");
        }
    }

    private void remove(int id, Player p, Port port){
        Resources to_add = choixRessource(id);
        if(port.getSellRessource().size() <= 0){
            System.out.println("La liste est vide");
            return;
        }
        if(port.getSellRessource().contains(to_add)) {
            port.getSellRessource().remove(to_add);
            p.setResources(to_add,p.getResources().get(to_add) + 1);

            System.out.println("Vous avez avez enlever une " + to_add);
        }else {
            System.out.println("Cette ressource n'est pas dans la liste");
        }
    }

    private boolean accept(Player p, Port port){
        if(port.getSellRessource().size() <= 0){
            System.out.println("La liste est vide");
            return false;
        }
        System.out.print("Voulez vous echanger: ");
        for(Resources x : port.getSellRessource()){
            System.out.print(x.toString() + ", ");
        }
        System.out.println("Contre " + port.getBuy());

        boolean reponse = askYesNo();

        if(reponse) {
            if(port.getPrix().contains("2:1")){
                if(port.getSellRessource().size() < 2){
                    return false;
                }
            }else if(port.getPrix().contains("3:1")){
                if(port.getSellRessource().size() < 3){
                    return false;
                }
            }
            p.setResources(port.getBuy(),p.getResources().get(port.getBuy()) + 1);

            System.out.println("Vous avait obtenu: " + port.getBuy());
            if(port.getPrix().contains("3:1")){
                port.setBuy(null);
            }
            return true;
        }
        return false;
    }

    public void deleteRessources(PlayerModel player){
        if(player.getNbrRessources() > 7 && player instanceof Player) {
            removeRessource = new LinkedList<>();
            System.out.println("Le voleur a etait deplacer.\n" + "Vous devez vous debarasser de " + (player.getNbrRessources()-7) + " ressources de votre choix.");
            boolean run = true;
            afficheRessource(player, true);

            deleteAfficheCommande();
            while (run){
                if (player.getNbrRessources() > 7) {
                    System.out.println("Il faut rajouter encore: " + (player.getNbrRessources() - 7) + " ressources");
                }

                String reponse = askString("remove1");

                if(reponse.contains("ajou")){
                    deleteAdd(askInteger("remove"), (Player) player);
                }
                if(reponse.contains("enlev")){
                    if(removeRessource.size() <= 0){
                        System.out.println("La liste est vide");
                    }else {
                        deleteRemove(askInteger("remove"), (Player) player);
                    }
                }
                if(reponse.contains("conf")){
                    if(deleteAccept((Player) player)){
                        run = false;
                    }
                }
                if (reponse.contains("cr")) {
                    afficheRessource(player, true);
                }
                if (reponse.contains("ar")) {
                    deleteAfficheAddRessource();
                }
                if (reponse.contains("help")) {
                    deleteAfficheCommande();
                }
            }
        }
        player.setThiefPlay(false);
    }

    private void deleteAfficheAddRessource(){
        if(removeRessource.size() >= 1){
            System.out.println("+------------"+ StringUtil.buildTablePatternV2("---------+", removeRessource.size()));
            System.out.print("| Ressources |");
            for(Resources x : removeRessource){
                System.out.print(StringUtil.center(x.toString(),9) + "|");
            }
            System.out.println("\n+------------"+ StringUtil.buildTablePatternV2("---------+", removeRessource.size()));
        }else {
            System.out.println("La liste est vide");
        }
    }

    private void deleteAfficheCommande(){
        System.out.println("Commandes:");
        System.out.println(" - Ajouter(aj)");
        System.out.println(" - Enlever(en)");
        System.out.println(" - Confirmer(co)");
        System.out.println(" - Afficher mes ressources(cr)");
        System.out.println(" - Afficher les commandes(help)");
        System.out.println(" - Afficher qui sont rajouter(ar)");
        System.out.println();
    }

    private void deleteAdd(int id, Player player){
        Resources to_add = choixRessource(id);
        if (player.getNbrRessources() <= 7) {
            System.out.println("Vous avait rajoute le bon nombre de ressources");
            return;
        }
        if(player.getResources().get(to_add) >= 1) {
            removeRessource.add(to_add);
            player.setResources(to_add,player.getResources().get(to_add) - 1);
            System.out.println("Vous avez avez rajouter " + to_add);
        }else {
            System.out.println("Vous avez pas asses de cette ressource merci de choisir une autre");
        }
    }

    private void deleteRemove(int id, Player player){
        Resources to_add = choixRessource(id);
        if(removeRessource.size() <= 0){
            System.out.println("La liste est vide");
            return;
        }
        if(removeRessource.contains(to_add)) {
            removeRessource.remove(to_add);
            player.setResources(to_add,player.getResources().get(to_add) + 1);
            System.out.println("Vous avez avez enlever une " + to_add);
        }else {
            System.out.println("Cette ressource n'est pas dans la liste");
        }
    }

    private boolean deleteAccept(Player player){
        if(removeRessource.size() <= 0){
            System.out.println("La liste est vide");
            return false;
        }
        System.out.print("Voulez vous effacer ces ressources: ");
        for(Resources x : removeRessource){
            System.out.print(x.toString() + ", ");
        }

        boolean reponse = askYesNo();

        if(reponse) {
            if(removeRessource.size() >= (player.getNbrRessources()-7)) {
                System.out.println("Vous avez supprimer les ressources");
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    public void printGenerate(PlayerModel player, int num, Resources res){
        System.out.println("Le joueur " + player.getColor() + " a obtenu " + num + " : " + res);
    }

}
