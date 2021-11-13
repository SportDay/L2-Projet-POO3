package l2.poo3.view.terminal;

import l2.poo3.controller.terminal.TerminalController;

import java.util.Scanner;

public class TerminalView {

    private TerminalController controller;

    public TerminalController getController() {
        return controller;
    }

    public void setController(TerminalController controller) {
        this.controller = controller;
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
