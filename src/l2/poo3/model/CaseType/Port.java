package l2.poo3.model.CaseType;

import l2.poo3.Other.StringUtil;
import l2.poo3.model.CaseModel;
import l2.poo3.model.Enum.Resources;
import l2.poo3.model.PlayerType.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Port extends CaseModel {

    private ArrayList<Resources> sellRessource = new ArrayList<>();

    private String prix = "";

    private Scanner sc = new Scanner(System.in);

    private Resources buy;

    public Port() {
        super(null, "Port", 2);
        setRandomPrice();
        setRandomResources();
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    private void setRandomResources() {
        boolean add = true;
        if(prix.equalsIgnoreCase("3:1")){
            super.setNature(null);
        }else {
            while (add) {
                int randomCase = new Random().nextInt(5) + 1;
                if(buy == null){
                    buy = choixRessource(randomCase);
                    super.setNature(buy);
                    add = false;
                }
            }
        }
    }

    private void setRandomPrice() {
        boolean add = true;
        String type1 = "2:1";
        String type2 = "3:1";
        while(add) {
            int randomCase = new Random().nextInt(2);
            switch (randomCase) {
                case 0:
                    setPrix(type1);
                    add = false;
                    break;
                case 1:
                    setPrix(type2);
                    add = false;
                    break;
            }
        }
    }

    private void afficheRessourceList(){
        System.out.println("\n+------------+---------+---------+---------+---------+---------+");
        System.out.println("|     ID     |    1    |    2    |    3    |    4    |    5    |");
        System.out.println("+------------+---------+---------+---------+---------+---------+");
        System.out.println("| Ressources |   " + Resources.BLE + "   | " + Resources.ARGILE + "  | " + Resources.MINERAI + " |  " + Resources.BOIS + "   | " + Resources.MOUTON + "  |");
        System.out.println("+------------+---------+---------+---------+---------+---------+");
    }

    private void afficheCommande(){
        System.out.println("Commandes:");
        System.out.println(" - Ajouter(aj)");
        System.out.println(" - Enlever(en)");
        System.out.println(" - Confirmer(co)");
        System.out.println(" - Annuler(an)");
        System.out.println(" - Afficher les commandes(ac)");
        System.out.println(" - Afficher mes ressources(af)");
        System.out.println(" - Afficher qui sont rajouter(ar)");
        System.out.println(" - Afficher les id des ressources(id)");
        System.out.println(" - Afficher la ressources a obtenir(ao)");
        System.out.println();
    }

    private void afficheAddRessource(){
        if(sellRessource.size() >= 1){
                System.out.println("+------------"+ StringUtil.buildTablePatternV2("---------+", sellRessource.size()));
                System.out.print("| Ressources |");
                for(Resources x : sellRessource){
                    System.out.print(StringUtil.center(x.toString(),9) + "|");
                }
                System.out.println("\n+------------"+ StringUtil.buildTablePatternV2("---------+", sellRessource.size()));
        }else {
            System.out.println("La liste est vide");
        }
    }

    public void echangerPort(Player p){

        boolean run = true;
        p.afficheRessource();
        afficheRessourceList();

        afficheCommande();

        System.out.println("Ressource que vous pouvez recevoir: " + buy);
        while (run){

            if(getPrix().contains("3:1") && buy == null){
                buy = choixRessource(askInteger("idBuy"));
            }
            if(prix.contains("2:1")) {
                if (sellRessource.size() < 2) {
                    System.out.println("Il faut rajouter encore: " + (2 - sellRessource.size()) + " ressources");
                }
            }else if(prix.contains("3:1")) {
                if (sellRessource.size() < 3) {
                    System.out.println("Il faut rajouter encore: " + (3 - sellRessource.size()) + " ressources");
                }
            }

            String reponse = askString("des");

            if(reponse.contains("ajou")){
                add(askInteger("id"), p);
            }
            if(reponse.contains("enlev")){
                if(sellRessource.size() <= 0){
                    System.out.println("La liste est vide");
                }else {
                    remove(askInteger("id"), p);
                }
            }
            if(reponse.contains("conf")){
               if(accept(p)){
                   run = false;
               }
            }
            if(reponse.contains("annul")){
                run = false;
            }
            if (reponse.contains("af")) {
                p.afficheRessource();
            }
            if (reponse.contains("ar")) {
                afficheAddRessource();
            }
            if (reponse.contains("ao")) {
                System.out.println("Ressource que vous pouvez recevoir: " + buy);
            }
            if (reponse.contains("ac")) {
                afficheCommande();
            }
            if (reponse.contains("id")) {
                afficheRessourceList();
            }
        }
    }

    private Resources choixRessource(int choix){
        switch (choix) {
            case 1:
                return Resources.BLE;
            case 2:
                return Resources.ARGILE;
            case 3:
                return Resources.MINERAI;
            case 4:
                return Resources.BOIS;
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
                System.out.println("Veuillez indiquer indiquer l'id de la ressource que vous voulez obtenir ");
            }

            String input = sc.next();
            try {
                to_return = Integer.parseInt(input);
                if(type.contains("id") || type.contains("idBuy")){
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
                if (rep.contains("af")) {
                    return "af";
                }
                if (rep.contains("ar")) {
                    return "ar";
                }
                if (rep.contains("ao")) {
                    return "ao";
                }
                if (rep.contains("ac")) {
                    return "ac";
                }
                if (rep.contains("id")) {
                    return "id";
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

    private void add(int id, Player p){
        Resources to_add = choixRessource(id);
        if(prix.contains("2:1")){
            if(sellRessource.size() > 2){
                System.out.println("Vous avait rajoute le bon nombre de ressources");
                return;
            }
        }else if(prix.contains("3:1")){
            if(sellRessource.size() > 3){
                System.out.println("Vous avait rajoute le bon nombre de ressources");
                return;
            }
        }
        if(p.getResources().get(to_add) >= 1) {
            sellRessource.add(to_add);
            p.getResources().put(to_add, p.getResources().get(to_add) - 1);

            System.out.println("Vous avez avez rajouter " + to_add);
        }else {
            System.out.println("Vous avez pas asse de cette ressource merci de choisir une autre");
        }
    }

    private void remove(int id, Player p){
        Resources to_add = choixRessource(id);
        if(sellRessource.size() <= 0){
            System.out.println("La liste est vide");
            return;
        }
        if(sellRessource.contains(to_add)) {
            sellRessource.remove(to_add);
            p.getResources().put(to_add, p.getResources().get(to_add) + 1);
            System.out.println("Vous avez avez enlever une " + to_add);
        }else {
            System.out.println("Cette ressource n'est pas dans la liste");
        }
    }

    private boolean accept(Player p){
        if(sellRessource.size() <= 0){
            System.out.println("La liste est vide");
            return false;
        }
        System.out.print("Voulez vous echanger: ");
        for(Resources x : sellRessource){
            System.out.print(x.toString() + ", ");
        }
        System.out.println("Contre " + buy);

        boolean reponse = askYesNo();

        if(reponse) {
            p.getResources().put(buy, p.getResources().get(buy) + 1);
            System.out.println("Vous avait obtenu: " + buy);
            if(getPrix().contains("3:1")){
                buy = null;
            }
            return true;
        }
        return false;
    }

}
