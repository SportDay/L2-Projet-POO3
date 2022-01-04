package l2.poo3.controller.gui;

import l2.poo3.model.CaseType.Batiment;
import l2.poo3.model.CaseType.Port;
import l2.poo3.model.CaseType.Route;
import l2.poo3.model.Enum.*;
import l2.poo3.model.*;
import l2.poo3.model.PlayerType.Ai;
import l2.poo3.model.PlayerType.Player;
import l2.poo3.view.gui.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class GuiController {

    private PlayerModel[] players;
    private final PlateauxModel plateaux;
    private final Scanner sc = new Scanner(System.in);
    private int quiJoue, nbrTour = 1;
    private final GuiView guiView;
    private final GuiSettingsView viewSettings;

    private boolean monopole = false;
    private boolean thief = false;
    private boolean port = false;
    private boolean choixRes1 = false;
    private boolean end = false;

    private int aiNumber = 0;

    private PlayerModel monopolePlayer = null;
    private Resources monopoleRes = null;

    public GuiController(PlateauxModel plateaux, GuiView guiView, GuiSettingsView viewSettings) {
        this.plateaux = plateaux;
        this.guiView = guiView;
        this.viewSettings = viewSettings;
        this.plateaux.setViewModel(guiView);
        this.guiView.setController(this);
        this.guiView.setPlateaux(plateaux);
        initListener();
    }

    public int getPlayerNum(){
        return players.length;
    }

    public boolean isEnd() {
        return end;
    }

    public GuiView getGuiView() {
        return guiView;
    }

    public PlayerModel getPlayerQuiJoue() {
        return players[quiJoue];
    }

    public PlayerModel[] getPlayers() {
        return players;
    }

    private void initListener() {
        viewSettings.getThreePlayerRadioButton().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                viewSettings.getPlayerFourFour().getComponent(0).setEnabled(false);
                viewSettings.getPlayerFourFour().getComponent(1).setEnabled(false);
            }
        });

        viewSettings.getFourPlayerRadioButton().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                viewSettings.getPlayerFourFour().getComponent(0).setEnabled(true);
                viewSettings.getPlayerFourFour().getComponent(1).setEnabled(true);
            }
        });

        viewSettings.getBtnNewButton().addActionListener(ae -> {
            aiNumber = 0;
            initView();
            if(aiNumber == players.length) {
                Object[] options = {"Ok"};
                JOptionPane.showOptionDialog(viewSettings, "Il faut avoir en moins un joueur humain", "Groupe 48 - Attention", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(ClassLoader.getSystemResource("warning.png")),options,options[0]);
            }else{
                viewSettings.dispose();
                guiView.initialize();
                guiView.setVisible(true);
                nextRoud();
            }
        });

        guiView.getBtnLancerDe().addActionListener(ae -> {
            if(thief){
                guiView.addInfo("Merci de deplacer le voleur");
                guiView.getBuildRadio().clearSelection();
            }else if(choixRes1) {
                guiView.addInfo("Merci choisir 2 ressources");
            }else if(nbrTour > 2) {
                throwdDice();
                guiView.getBtnLancerDe().setEnabled(false);
            }else {
                guiView.addInfo("Vous pouvez la lancer les des pendant les deux premier tour");
            }
        });

        guiView.getBtnFinirLeTour().addActionListener(ae -> {
            if(thief){
                guiView.addInfo("Merci de deplacer le voleur");
                guiView.getBuildRadio().clearSelection();
            }else if(choixRes1) {
                guiView.addInfo("Merci choisir 2 ressources");
            }else if(!players[quiJoue].isThrowDice() && nbrTour > 2) {
                guiView.addInfo("D'abord il faut lancer les dés");
            }else if(players[quiJoue].getPointDeVic() <= 0){
                guiView.addInfo("Vous devez en moins contruite une collonie");
            }else{
                joueurSuivant();
            }
        });

        guiView.getBtnDevelopement().addActionListener(ae -> {
            if(thief){
                guiView.addInfo("Merci de deplacer le voleur");
                guiView.getBuildRadio().clearSelection();
            }else if(choixRes1) {
                guiView.addInfo("Merci choisir 2 ressources");
            }else if(!players[quiJoue].isThrowDice() && nbrTour > 2) {
                guiView.addInfo("D'abord il faut lancer les dés");
            }else if(nbrTour < 3){
                guiView.addInfo("Pendant les 2 premiers tours vous pouvez que construire des batiments ou des routes");
            }else {
                acheterCarte();
            }
        });

        guiView.getRoadRadio().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if(thief){
                    guiView.addInfo("Merci de deplacer le voleur");
                    guiView.getBuildRadio().clearSelection();
                }else if(choixRes1) {
                    guiView.addInfo("Merci choisir 2 ressources");
                }else if(!players[quiJoue].isThrowDice() && nbrTour > 2){
                    guiView.addInfo("D'abord il faut lancer les dés");
                    guiView.getBuildRadio().clearSelection();
                }else {
                    updateAcces("route");
                }
            } else {
                for(JButton t : guiView.getCaseRoute()){
                    if(t.isEnabled()) {
                        t.setEnabled(false);
                        t.setBorder(null);

                    }
                }
            }
        });

        guiView.getColRadio().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if(thief){
                    guiView.addInfo("Merci de deplacer le voleur");
                    guiView.getBuildRadio().clearSelection();
                }else if(choixRes1) {
                    guiView.addInfo("Merci choisir 2 ressources");
                }else if(!players[quiJoue].isThrowDice() && nbrTour > 2){
                    guiView.addInfo("D'abord il faut lancer les dés");
                    guiView.getBuildRadio().clearSelection();
                }else {
                    updateAcces("col");
                }
            } else {
                for(JButton t : guiView.getCaseCol()){
                    if(t.isEnabled()) {
                        t.setEnabled(false);
                        t.setBorder(null);
                    }
                }
            }
        });

        guiView.getVilRadio().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if(thief){
                    guiView.addInfo("Merci de deplacer le voleur");
                    guiView.getBuildRadio().clearSelection();
                }else if(choixRes1) {
                    guiView.addInfo("Merci choisir 2 ressources");
                }else if(!players[quiJoue].isThrowDice() && nbrTour > 2){
                    guiView.addInfo("D'abord il faut lancer les dés");
                    guiView.getBuildRadio().clearSelection();
                }else {
                    updateAcces("vil");
                }
            } else {
                for(JButton t : guiView.getCaseVil()){
                    if(t.isEnabled()) {
                        t.setEnabled(false);
                        t.setBorder(UIManager.getBorder(null));
                    }
                }
            }
        });

        guiView.getUsePort().addActionListener(e -> {
                if(thief){
                    guiView.addInfo("Merci de deplacer le voleur");
                    guiView.getBuildRadio().clearSelection();
                }else if(choixRes1) {
                    guiView.addInfo("Merci choisir 2 ressources");
                }else if(!players[quiJoue].isThrowDice() && nbrTour > 2){
                    guiView.addInfo("D'abord il faut lancer les dés");
                    guiView.getBuildRadio().clearSelection();
                }else if(nbrTour < 3){
                    guiView.addInfo("Pendant les 2 premiers tours vous pouvez que construire des batiments ou des routes");
                }else {
                    port = true;
                    updateAcces("port");
                    updateListener();
                }
        });

        guiView.getUseChevalier().addActionListener(ae -> {
            if(thief){
                guiView.addInfo("Merci de deplacer le voleur");
                guiView.getBuildRadio().clearSelection();
            }else if(choixRes1) {
                guiView.addInfo("Merci choisir 2 ressources");
            }else if(!players[quiJoue].isThrowDice() && nbrTour > 2) {
                guiView.addInfo("D'abord il faut lancer les dés");
            }else if(nbrTour < 3){
                guiView.addInfo("Pendant les 2 premiers tours vous pouvez que construire des batiments ou des routes");
            }else if(players[quiJoue].getCartesDev().get(CartesDev.Chevalier) > 0) {
                players[quiJoue].updateCartes(CartesDev.Chevalier, false);
                playCartDevThief();
            }
        });

        guiView.getUseMonopole().addActionListener(ae -> {
            if(thief){
                guiView.addInfo("Merci de deplacer le voleur");
                guiView.getBuildRadio().clearSelection();
            }else if(choixRes1) {
                guiView.addInfo("Merci choisir 2 ressources");
            }else if(!players[quiJoue].isThrowDice() && nbrTour > 2) {
                guiView.addInfo("D'abord il faut lancer les dés");
            }else if(nbrTour < 3){
                guiView.addInfo("Pendant les 2 premiers tours vous pouvez que construire des batiments ou des routes");
            }else if(players[quiJoue].getCartesDev().get(CartesDev.Monopole) > 0) {
                players[quiJoue].updateCartes(CartesDev.Monopole, false);
                playCartDevMonopole();
            }
        });

        guiView.getUseInvention().addActionListener(ae -> {
            if(thief){
                guiView.addInfo("Merci de deplacer le voleur");
                guiView.getBuildRadio().clearSelection();
            }else if(choixRes1) {
                guiView.addInfo("Merci choisir 2 ressources");
            }else if(!players[quiJoue].isThrowDice() && nbrTour > 2) {
                guiView.addInfo("D'abord il faut lancer les dés");
            }else if(nbrTour < 3){
                guiView.addInfo("Pendant les 2 premiers tours vous pouvez que construire des batiments ou des routes");
            }else if(players[quiJoue].getCartesDev().get(CartesDev.Invention) > 0) {
                players[quiJoue].updateCartes(CartesDev.Invention, false);
                playCartDevInvention();
            }
        });

        guiView.getUseRoute().addActionListener(ae -> {
            if(thief){
                guiView.addInfo("Merci de deplacer le voleur");
                guiView.getBuildRadio().clearSelection();
            }else if(choixRes1) {
                guiView.addInfo("Merci choisir 2 ressources");
            }else if(!players[quiJoue].isThrowDice() && nbrTour > 2) {
                guiView.addInfo("D'abord il faut lancer les dés");
            }else if(nbrTour < 3){
                guiView.addInfo("Pendant les 2 premiers tours vous pouvez que construire des batiments ou des routes");
            }else if(players[quiJoue].getCartesDev().get(CartesDev.Route) > 0) {
                players[quiJoue].updateCartes(CartesDev.Route, false);
                playCartDevRoute();
            }
        });
    }

    private void playCartDevThief(){
        thief = true;
        guiView.addInfo("Merci de choisir, les nouvelles coordonnes du volleur");
        updateAcces("res");
        updateListener();
        players[quiJoue].increaseNbrKnight();
        plateaux.updateBiggestKnight(players);
    }

    private void playCartDevMonopole(){
        AskRessourcesView askRessources = new AskRessourcesView(guiView,  "Choisissez le stock la ressource a voler");
        askRessources.setVisible(true);
        Resources get = askRessources.getRes();
        for(PlayerModel p : players){
            if(p != players[quiJoue]){
                int nbr = p.getResources().get(get);
                p.setResources(get, 0);
                players[quiJoue].setResources(get, players[quiJoue].getResources().get(get) + nbr);
                guiView.addInfo("Vous avez voler tout le stock de " + get + " aux joueur " + p.getColor());
            }
        }
        monopole = true;
        monopolePlayer = players[quiJoue];
        monopoleRes = get;
        guiView.updateView();
    }

    private void playCartDevInvention(){
        choixRes1 = true;
        guiView.addInfo("Vous pouvez choisir 2 ressources a obtenir");
        guiView.addInfo("Choisissez la première ressource a obtenir");

        AskRessourcesView askRessources = new AskRessourcesView(guiView,  "Choisissez la première ressource a obtenir");
        askRessources.setVisible(true);
        Resources res = askRessources.getRes();

        players[quiJoue].setResources(res, players[quiJoue].getResources().get(res)+1);
        guiView.addInfo("Vous avez obtenue une ressource de type " + res);
        guiView.addInfo("Choisissez la seconde ressource");
        guiView.updateView();

        askRessources = new AskRessourcesView(guiView,  "Choisissez la seconde ressource a obtenir");
        askRessources.setVisible(true);
        res = askRessources.getRes();

        players[quiJoue].setResources(res, players[quiJoue].getResources().get(res)+1);
        guiView.addInfo("Vous avez obtenue une ressource de type " + res);
        guiView.updateView();
        choixRes1 = false;

    }

    private void playCartDevRoute(){
        players[quiJoue].setResources(Resources.ARGILE, players[quiJoue].getResources().get(Resources.ARGILE)+2);
        players[quiJoue].setResources(Resources.BOIS, players[quiJoue].getResources().get(Resources.BOIS)+2);
        guiView.addInfo("Vous avez obtenu les ressources necessaire pour construire 2 routes");
        guiView.updateView();
    }

    private void updateAcces(String type){
        if(type.equalsIgnoreCase("col")) {
            for (JButton t : guiView.getCaseCol()) {
                String[] cords = t.getActionCommand().split("!");
                int x = Integer.parseInt(cords[0]);
                int y = Integer.parseInt(cords[1]);
                if (!t.isEnabled()) {
                    if(verifBat(x, y, "col")) {
                        t.setEnabled(true);
                        t.setBorder(new LineBorder(new Color(0, 255, 0), 2, true));
                    }
                }
            }
        }else if(type.equalsIgnoreCase("vil")) {
            for (JButton t : guiView.getCaseVil()) {
                String[] cords = t.getActionCommand().split("!");
                int x = Integer.parseInt(cords[0]);
                int y = Integer.parseInt(cords[1]);
                if (!t.isEnabled()) {
                    if(verifBat(x, y, "vil")) {
                        t.setEnabled(true);
                        t.setBorder(new LineBorder(new Color(0, 255, 0), 2, true));
                    }
                }
            }
        }else if(type.equalsIgnoreCase("route")) {
            for (JButton t : guiView.getCaseRoute()) {
                String[] cords = t.getActionCommand().split("!");
                int x = Integer.parseInt(cords[0]);
                int y = Integer.parseInt(cords[1]);
                if (!t.isEnabled()) {
                    if(verifRoute(x, y)) {
                        t.setEnabled(true);
                        t.setBorder(new LineBorder(new Color(0, 255, 0), 2, true));
                    }
                }
            }
        }else if(type.equalsIgnoreCase("res")) {
            for (JPanel t : guiView.getCaseResource()) {
                if(thief) {
                    String[] cords = t.getName().split("!");
                    int x = Integer.parseInt(cords[0]);
                    int y = Integer.parseInt(cords[1]);
                    if (verifRes(x, y)) {
                        ((JLabel) t.getComponent(0)).setBorder(new LineBorder(new Color(0, 255, 0), 2, true));
                        ((JLabel) t.getComponent(0)).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    }
                }
            }
        }else if(type.equalsIgnoreCase("port")) {
            for (JPanel t : guiView.getCasePort()) {
                if(port) {
                    String[] cords = t.getName().split("!");
                    int x = Integer.parseInt(cords[0]);
                    int y = Integer.parseInt(cords[1]);
                    if (plateaux.getPlateaux()[y][x] instanceof Port) {
                        if (verifPort(x, y)) {
                            ((JLabel) t.getComponent(0)).setBorder(new LineBorder(new Color(0, 255, 0), 2, true));
                            ((JLabel) t.getComponent(0)).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                    }
                }
            }
        }
    }

    public void buildRoute(int x, int y) {
        if(players[quiJoue].getResources().get(Resources.BOIS) >= 1 && players[quiJoue].getResources().get(Resources.ARGILE) >= 1) {
            CaseModel[][] plateaux = this.plateaux.getPlateaux();
            plateaux[y][x].setName("R " + players[quiJoue].getColor().toString().charAt(0));
            ((Route) plateaux[y][x]).setPlayer(players[quiJoue]);

            players[quiJoue].setResources(Resources.BOIS, players[quiJoue].getResources().get(Resources.BOIS) - 1);
            players[quiJoue].setResources(Resources.ARGILE, players[quiJoue].getResources().get(Resources.ARGILE) - 1);

            guiView.addInfo("Vous avez construit une route");
            updateLargestRoadPlayer(x, y, players[quiJoue], players);
        }else {
            guiView.addInfo("Vous avez pas assez de ressources.");
        }
    }

    private void updateLargestRoadPlayer(int x, int y,PlayerModel player, PlayerModel[] players){
        plateaux.updateLargestRoadPlayer(x, y, player,players);
    }

    private boolean verifRes(int x, int y){
        if(plateaux.getPlateaux()[y][x].thiefPresent()){
            return false;
        }
        return true;
    }

    public void buildBat(int x, int y, String type){
        if(verifCase(y, x)){
            if(plateaux.getPlateaux()[y][x] instanceof Batiment){
                if(type.contains("col")){
                    if(players[quiJoue].getResources().get(Resources.BOIS) >= 1 && players[quiJoue].getResources().get(Resources.ARGILE) >= 1 && players[quiJoue].getResources().get(Resources.BLE) >= 1 && players[quiJoue].getResources().get(Resources.MOUTON) >= 1) {
                        plateaux.getPlateaux()[y][x].setName("C " + players[quiJoue].getColor().toString().charAt(0));
                        ((Batiment) plateaux.getPlateaux()[y][x]).setPlayer(players[quiJoue]);
                        guiView.addInfo("Vous avez construit une colonie");

                        players[quiJoue].setResources(Resources.BOIS, players[quiJoue].getResources().get(Resources.BOIS) - 1);
                        players[quiJoue].setResources(Resources.ARGILE, players[quiJoue].getResources().get(Resources.ARGILE) - 1);
                        players[quiJoue].setResources(Resources.BLE, players[quiJoue].getResources().get(Resources.BLE) - 1);
                        players[quiJoue].setResources(Resources.MOUTON, players[quiJoue].getResources().get(Resources.MOUTON) - 1);

                        players[quiJoue].addNbrCol();
                        players[quiJoue].setPointDeVic(players[quiJoue].getPointDeVic() + 1);
                    }
                }else if(type.contains("vil")) {
                    if (nbrTour > 2) {
                        if (plateaux.getPlateaux()[y][x].getName().contains("C")) {
                            if (players[quiJoue].getResources().get(Resources.BLE) >= 2 && players[quiJoue].getResources().get(Resources.MINERAI) >= 3) {
                                plateaux.getPlateaux()[y][x].setName("V " + players[quiJoue].getColor().toString().charAt(0));
                                ((Batiment) plateaux.getPlateaux()[y][x]).setPlayer(players[quiJoue]);
                                guiView.addInfo("Vous avez construit une ville");

                                players[quiJoue].setResources(Resources.BLE,players[quiJoue].getResources().get(Resources.BLE) - 2);
                                players[quiJoue].setResources(Resources.MINERAI,players[quiJoue].getResources().get(Resources.MINERAI) - 3);

                                players[quiJoue].addNbrVille();
                                players[quiJoue].setPointDeVic(players[quiJoue].getPointDeVic() + 1);
                            }else {
                                guiView.addInfo("Vous avez pas asses de ressources");
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean verifBat(int x, int y, String type) {
        boolean allowBuild = false;
        if(verifCase(y, x)) {
            if (plateaux.getPlateaux()[y][x] instanceof Batiment) {
                if (type.contains("col")) {
                    if(players[quiJoue].getResources().get(Resources.BOIS) >= 1 && players[quiJoue].getResources().get(Resources.ARGILE) >= 1 && players[quiJoue].getResources().get(Resources.BLE) >= 1 && players[quiJoue].getResources().get(Resources.MOUTON) >= 1) {

                        if (y + 1 <= plateaux.getLength_y() - 1 && plateaux.getPlateaux()[y + 1][x] instanceof Route && (((Route) plateaux.getPlateaux()[y + 1][x]).getPlayer() == players[quiJoue] || ((Route) plateaux.getPlateaux()[y + 1][x]).getPlayer() == null)) {
                            allowBuild = true;
                        } else if (y - 1 >= 0 && plateaux.getPlateaux()[y - 1][x] instanceof Route && (((Route) plateaux.getPlateaux()[y - 1][x]).getPlayer() == players[quiJoue] || ((Route) plateaux.getPlateaux()[y - 1][x]).getPlayer() == null)) {
                            allowBuild = true;
                        } else if (x + 1 <= plateaux.getLength_x() - 1 && plateaux.getPlateaux()[y][x + 1] instanceof Route && (((Route) plateaux.getPlateaux()[y][x + 1]).getPlayer() == players[quiJoue] || ((Route) plateaux.getPlateaux()[y][x + 1]).getPlayer() == null)) {
                            allowBuild = true;
                        } else if (x - 1 >= 0 && plateaux.getPlateaux()[y][x - 1] instanceof Route && (((Route) plateaux.getPlateaux()[y][x - 1]).getPlayer() == players[quiJoue] || ((Route) plateaux.getPlateaux()[y][x - 1]).getPlayer() == null)) {
                            allowBuild = true;
                        }

                        if (plateaux.getPlateaux()[y][x].getName().contains("C") && ((Batiment) plateaux.getPlateaux()[y][x]).getPlayer() == players[quiJoue]) {
                            allowBuild = false;
                        } else if (y + 1 <= plateaux.getLength_y() - 1 && plateaux.getPlateaux()[y + 1][x] instanceof Route && ((Route) plateaux.getPlateaux()[y + 1][x]).getPlayer() != players[quiJoue] && ((Route) plateaux.getPlateaux()[y + 1][x]).getPlayer() != null) {
                            allowBuild = false;
                        } else if (y - 1 >= 0 && plateaux.getPlateaux()[y - 1][x] instanceof Route && ((Route) plateaux.getPlateaux()[y - 1][x]).getPlayer() != players[quiJoue] && ((Route) plateaux.getPlateaux()[y - 1][x]).getPlayer() != null) {
                            allowBuild = false;
                        } else if (x + 1 <= plateaux.getLength_x() - 1 && plateaux.getPlateaux()[y][x + 1] instanceof Route && ((Route) plateaux.getPlateaux()[y][x + 1]).getPlayer() != players[quiJoue] && ((Route) plateaux.getPlateaux()[y][x + 1]).getPlayer() != null) {
                            allowBuild = false;
                        } else if (x - 1 >= 0 && plateaux.getPlateaux()[y][x - 1] instanceof Route && ((Route) plateaux.getPlateaux()[y][x - 1]).getPlayer() != players[quiJoue] && ((Route) plateaux.getPlateaux()[y][x - 1]).getPlayer() != null) {
                            allowBuild = false;
                        }
                    }
                } else if (type.contains("vil")) {
                    if (nbrTour > 2) {
                        if (plateaux.getPlateaux()[y][x].getName().contains("C")) {
                            if (players[quiJoue].getResources().get(Resources.BLE) >= 2 && players[quiJoue].getResources().get(Resources.MINERAI) >= 3) {
                                allowBuild = true;
                            }
                        }
                    }
                }
            }
        }
        return allowBuild;
    }

    public boolean verifCase(int y, int x){
        if(verifCords(y,x)) {
            CaseModel cases = plateaux.getPlateaux()[y][x];
            if (cases != null) {
                if (cases instanceof Route) {
                    if (((Route) cases).getPlayer() == null) {
                        return true;
                    }
                }
                if (cases instanceof Batiment) {
                    if (((Batiment) cases).getPlayer() == null) {
                        return true;
                    }
                    if (cases.getName().contains("C") && ((Batiment) cases).getPlayer() == players[quiJoue]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean verifCords(int y, int x){
        if(y < 0 || x < 0 || y > plateaux.getLength_y()-1 || x > plateaux.getLength_x()-1){
            return false;
        }
        return true;
    }

    public boolean verifRoute(int x, int y) {
        boolean allowBuild = false;
        if(players[quiJoue].getResources().get(Resources.BOIS) >= 1 && players[quiJoue].getResources().get(Resources.ARGILE) >= 1) {
            CaseModel[][] plateaux = this.plateaux.getPlateaux();
            if (verifCase(y, x)) {
                if (y + 1 <= this.plateaux.getLength_y() - 1 && plateaux[y + 1][x] instanceof Batiment && ((Batiment) plateaux[y + 1][x]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (y - 1 >= 0 && plateaux[y - 1][x] instanceof Batiment && ((Batiment) plateaux[y - 1][x]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (x + 1 <= this.plateaux.getLength_x() - 1 && plateaux[y][x + 1] instanceof Batiment && ((Batiment) plateaux[y][x + 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (x - 1 >= 0 && plateaux[y][x - 1] instanceof Batiment && ((Batiment) plateaux[y][x - 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                }else if (x + 1 <= this.plateaux.getLength_x() - 1 && y + 1 <= this.plateaux.getLength_y() - 1 && plateaux[y + 1][x + 1] instanceof Route && ((Route) plateaux[y + 1][x + 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (x - 1 >= 0 && y - 1 >= 0 && plateaux[y - 1][x - 1] instanceof Route && ((Route) plateaux[y - 1][x - 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (y - 1 >= 0 && x + 1 <= this.plateaux.getLength_x() - 1 && plateaux[y - 1][x + 1] instanceof Route && ((Route) plateaux[y - 1][x + 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (y + 1 <= this.plateaux.getLength_y() - 1 && x - 1 >= 0 && plateaux[y + 1][x - 1] instanceof Route && ((Route) plateaux[y + 1][x - 1]).getPlayer() == players[quiJoue]) {
                    allowBuild = true;
                } else if (y % 2 != 0){
                    if (x + 2 <= this.plateaux.getLength_x() - 1 && plateaux[y][x + 2] instanceof Route && ((Route) plateaux[y][x + 2]).getPlayer() == players[quiJoue]) {
                        allowBuild = true;
                    } else if (x - 2 >= 0 && plateaux[y][x - 2] instanceof Route && ((Route) plateaux[y][x - 2]).getPlayer() == players[quiJoue]) {
                        allowBuild = true;
                    }
                }else if(x % 2 != 0){
                    if (y - 2 >= 0 && plateaux[y - 2][x] instanceof Route && ((Route) plateaux[y - 2][x]).getPlayer() == players[quiJoue]) {
                        allowBuild = true;
                    } else if (y + 2 <= this.plateaux.getLength_y() - 1 && plateaux[y + 2][x] instanceof Route && ((Route) plateaux[y + 2][x]).getPlayer() == players[quiJoue]) {
                        allowBuild = true;
                    }
                }
                if (y + 1 <= this.plateaux.getLength_y() - 1 && plateaux[y + 1][x] instanceof Batiment && ((Batiment) plateaux[y + 1][x]).getPlayer() != players[quiJoue] && ((Batiment) plateaux[y+1][x]).getPlayer() != null) {
                    allowBuild = false;
                } else if (y - 1 >= 0 && plateaux[y - 1][x] instanceof Batiment && ((Batiment) plateaux[y - 1][x]).getPlayer() != players[quiJoue] && ((Batiment) plateaux[y-1][x]).getPlayer() != null) {
                    allowBuild = false;
                } else if (x + 1 <= this.plateaux.getLength_x() - 1 && plateaux[y][x + 1] instanceof Batiment && ((Batiment) plateaux[y][x + 1]).getPlayer() != players[quiJoue] && ((Batiment) plateaux[y][x + 1]).getPlayer() != null) {
                    allowBuild = false;
                } else if (x - 1 >= 0 && plateaux[y][x - 1] instanceof Batiment && ((Batiment) plateaux[y][x - 1]).getPlayer() != players[quiJoue] && ((Batiment) plateaux[y][x - 1]).getPlayer() != null) {
                    allowBuild = false;
                }
            }
        }
        return allowBuild;
    }

    public boolean verifPort(int x, int y) {
        boolean allowUse = false;
        if(verifCords(y,x)) {
            CaseModel[][] plateaux = this.plateaux.getPlateaux();
            if (plateaux[y][x] instanceof Port) {
                if (x + 1 <= this.plateaux.getLength_x() - 1 && y + 1 <= this.plateaux.getLength_y() - 1 && plateaux[y + 1][x + 1] instanceof Batiment && ((Batiment) plateaux[y + 1][x + 1]).getPlayer() == players[quiJoue]) {
                    allowUse = true;
                } else if (x - 1 >= 0 && y - 1 >= 0 && plateaux[y - 1][x - 1] instanceof Batiment && ((Batiment) plateaux[y - 1][x - 1]).getPlayer() == players[quiJoue]) {
                    allowUse = true;
                } else if (y - 1 >= 0 && x + 1 <= this.plateaux.getLength_x() - 1 && plateaux[y - 1][x + 1] instanceof Batiment && ((Batiment) plateaux[y - 1][x + 1]).getPlayer() == players[quiJoue]) {
                    allowUse = true;
                } else if (y + 1 <= this.plateaux.getLength_y() - 1 && x - 1 >= 0 && plateaux[y + 1][x - 1] instanceof Batiment && ((Batiment) plateaux[y + 1][x - 1]).getPlayer() == players[quiJoue]) {
                    allowUse = true;
                }
                if(((Port) plateaux[y][x]).getPrix().equalsIgnoreCase("3:1")){
                    if(players[quiJoue].getNbrRessources() < 3){
                        allowUse = false;
                    }
                }else {
                    if(players[quiJoue].getNbrRessources() < 2){
                        allowUse = false;
                    }
                }
            }
        }
        return allowUse;
    }

    private void initView(){
        int number_player = Integer.parseInt(viewSettings.getPlayerNum().getSelection().getActionCommand());
        int x = Integer.parseInt(viewSettings.getSpinnerX().getValue().toString());
        int y = Integer.parseInt(viewSettings.getSpinnerY().getValue().toString());
        int pointLimit = Integer.parseInt(viewSettings.getSpinnerPV().getValue().toString());

        players = new PlayerModel[number_player];

        if(number_player >= 3){
            if(viewSettings.getPlayer1Type41().getSelection().getActionCommand().equalsIgnoreCase("P41")){
                players[0] = new Player(Pcolor.Rouge);
            }else if(viewSettings.getPlayer1Type41().getSelection().getActionCommand().equalsIgnoreCase("A41")){
                players[0] = new Ai(Pcolor.Rouge);
                if(players[0] instanceof Ai) {
                    ((Ai) players[0]).setPlateaux(plateaux);
                }
                aiNumber++;
            }

            if(viewSettings.getPlayer1Type42().getSelection().getActionCommand().equalsIgnoreCase("P42")){
                players[1] = new Player(Pcolor.Jaune);
            }else if(viewSettings.getPlayer1Type42().getSelection().getActionCommand().equalsIgnoreCase("A42")){
                players[1] = new Ai(Pcolor.Jaune);
                if(players[1] instanceof Ai) {
                    ((Ai) players[1]).setPlateaux(plateaux);
                }
                aiNumber++;
            }

            if(viewSettings.getPlayer1Type43().getSelection().getActionCommand().equalsIgnoreCase("P43")){
                players[2] = new Player(Pcolor.Vert);
            }else if(viewSettings.getPlayer1Type43().getSelection().getActionCommand().equalsIgnoreCase("A43")){
                players[2] = new Ai(Pcolor.Vert);
                if(players[2] instanceof Ai) {
                    ((Ai) players[2]).setPlateaux(plateaux);
                }
                aiNumber++;
            }

        }
        if(number_player >= 4){
            if(viewSettings.getPlayer1Type44().getSelection().getActionCommand().equalsIgnoreCase("P44")){
                players[3] = new Player(Pcolor.Bleu);
            }else if(viewSettings.getPlayer1Type44().getSelection().getActionCommand().equalsIgnoreCase("A44")){
                players[3] = new Ai(Pcolor.Bleu);
                if(players[3] instanceof Ai) {
                    ((Ai) players[3]).setPlateaux(plateaux);
                }
                aiNumber++;
            }
        }

        plateaux.initPlateaux(x,y);
        plateaux.setPointLimit(pointLimit);
    }

    public void acheterCarte() {
        if (players != null && players[quiJoue] instanceof Player) {
            if (players[quiJoue].getResources().get(Resources.MINERAI) >= 1 && players[quiJoue].getResources().get(Resources.MOUTON) >= 1 && players[quiJoue].getResources().get(Resources.BLE) >= 1) {
                CartesDev carte = plateaux.getCartesDevList().getList().get(plateaux.getCarteDevPos());
                if (carte.equals(CartesDev.PointVictoire)) {
                    players[quiJoue].setInvPVic(players[quiJoue].getInvPVic() + 1);
                }
                players[quiJoue].updateCartes(carte, true);
                guiView.addInfo("Vous avez obtenu une carte de " + carte);
                plateaux.setCarteDevPos(plateaux.getCarteDevPos() + 1);

                players[quiJoue].setResources(Resources.MINERAI,players[quiJoue].getResources().get(Resources.MINERAI) - 1);
                players[quiJoue].setResources(Resources.MOUTON,players[quiJoue].getResources().get(Resources.MOUTON) - 1);
                players[quiJoue].setResources(Resources.BLE,players[quiJoue].getResources().get(Resources.BLE) - 1);
                guiView.updateView();
            } else {
                guiView.addInfo("Vous avez pas assez de ressources");
            }
        }
    }

    private void throwdDice(){
        if(!players[quiJoue].isThrowDice()) {
            DiceModel dice = new DiceModel();
            int num1 = dice.throwOneDice();
            int num2 = dice.throwOneDice();
            int result = num1 + num2;

            guiView.getDice1().setIcon(new ImageIcon(ClassLoader.getSystemResource(getDiceImagePath(num1))));
            guiView.getDice2().setIcon(new ImageIcon(ClassLoader.getSystemResource(getDiceImagePath(num2))));

            guiView.addLogs("Le joueur " + players[quiJoue].getColor() + " a lancer les des, le resultat est: " + result);
            players[quiJoue].setThrowDice(true);
            if(result == 7) {
                if(players[quiJoue] instanceof Player) {
                    thief = true;
                    guiView.addInfo("Merci de choisir, les nouvelles coordonnes du volleur");
                    updateAcces("res");
                    updateListener();
                }else if(players[quiJoue] instanceof Ai) {
                    thiefAi();
                }
            }
            guiView.addLogs("Generation des ressource");
            boolean generate = plateaux.generateRessources(result);
            if(generate){
                guiView.addLogs("Les ressources ont etait generer");
            }else {
                guiView.addLogs("Aucune ressource n'a etait generer");
            }
        }
        if(!thief) {
            guiView.updateView();
        }
    }

    private void thiefPlayer(int x, int y) {
        plateaux.moveThief(x, y);
        Resources res = plateaux.stealOneRessources(x, y, players[quiJoue]);
        if (res != null) {
            guiView.addInfo("Vous avez voler une ressource: " + res);
            for (PlayerModel p : players) {
                p.setThiefPlay(true);
            }
        }
        if(players[quiJoue].getNbrRessources() > 7){
            DeleteReeourcesView dell = new DeleteReeourcesView(guiView, players[quiJoue]);
            dell.setVisible(true);
        }
    }

    private void thiefAi(){
        if(players[quiJoue] instanceof Ai){
            ((Ai) players[quiJoue]).thiefAi(plateaux.getRealX(), plateaux.getRealY(), players);
        }
    }

    private String getDiceImagePath(int i){
        switch(i){
            case 1: return "die/die1.png";
            case 2: return "die/die2.png";
            case 3: return "die/die3.png";
            case 4: return "die/die4.png";
            case 5: return "die/die5.png";
            case 6: return "die/die6.png";
        }
        return "";
    }

    private boolean endGame(){

        if(plateaux.tmpWinPlayer(players) != null){
            System.out.println(1);
            return true;
        }
        return false;
    }

    public void joueurSuivant() {
        if(players[quiJoue] instanceof Player){
            guiView.clearLogs();
        }
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

        nextRoud();
        guiView.updateView();

        guiView.getBuildRadio().clearSelection();
    }

    public void start() {
        EventQueue.invokeLater(() -> {
            try {
                viewSettings.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void nextRoud() {
        ((javax.swing.border.TitledBorder) guiView.getOtherPanel().getBorder()).setTitle("Tour " + nbrTour + ", Joeur: " + players[quiJoue].getColor());
        guiView.addLogs("Tour " + nbrTour + ", Joeur: " + players[quiJoue].getColor());
        if (endGame()) {
            System.out.println(2);
            PlayerModel tmpWinPlayer = plateaux.tmpWinPlayer(players);
            if (tmpWinPlayer != null) {
                System.out.println(3);
                guiView.addLogs("Fin de la partie");
                guiView.addLogs("La partie a duree " + nbrTour + " tours");
                guiView.addLogs("Le joueur " + tmpWinPlayer.getColor() + " a obtenu " + tmpWinPlayer.getPointDeVic() + " point de victoire");
                boolean devCard = false;
                PlayerModel realWinPlayer = plateaux.realWinPlayer(players);
                if (realWinPlayer != null) {
                    System.out.println(4);
                    String text = "Le vainqueur est le joueur " + realWinPlayer.getColor() + ", avec " + realWinPlayer.getPoVicReal() + " point de victoire";
                    if (realWinPlayer.getPoVicReal() > plateaux.getPointLimit()) {
                        System.out.println(5);
                        text += " grace aux cartes de devloppement";
                        devCard = true;
                    }
                    guiView.addLogs(text);
                }

                String text = "Fin de la partie\nLa partie a duree " + nbrTour + " tours\n" +
                        "Le vainqueur est le joueur " + realWinPlayer.getColor() + ", avec " + realWinPlayer.getPoVicReal() + " point de victoire";
                if (devCard) {
                    text += "\ngrace aux cartes de devloppement";
                }
                end = true;
                GuiEndGameView guiEndGameView = new GuiEndGameView(guiView, text);
                guiEndGameView.setVisible(true);

            }
        } else {
            if (players[quiJoue] instanceof Ai) {

                guiView.addLogs("\n");
                guiView.addLogs("AI " + players[quiJoue].getColor() + " a commencer son tour");
                guiView.addLogs("\n");

                if (players[quiJoue].isThiefPlay()) {
                    ((Ai) players[quiJoue]).deleteRessources();
                }
                if(nbrTour > 2) {
                    throwdDice();
                }
                if (((Ai) players[quiJoue]).getPlateaux() != null) {
                    ((Ai) players[quiJoue]).buildVill(plateaux.getRealX(), plateaux.getRealY());
                    ((Ai) players[quiJoue]).buildRoad(plateaux.getRealX(), plateaux.getRealY(), players);
                    ((Ai) players[quiJoue]).buildColl(plateaux.getRealX(), plateaux.getRealY(), players);
                }
                guiView.addLogs("\n");
                guiView.addLogs("AI " + players[quiJoue].getColor() + " a finit son tour");
                guiView.addLogs("\n");

                joueurSuivant();
            }else {
                if (!players[quiJoue].isThrowDice()) {
                    guiView.getBtnLancerDe().setEnabled(true);
                }
                if(monopole && players[quiJoue] != monopolePlayer){
                    guiView.addInfo("Le jouer: " + monopolePlayer.getColor() + " a voler tout votre stock de " + monopoleRes);
                }
                if (players[quiJoue].isThiefPlay()) {
                    if(players[quiJoue].getNbrRessources() > 7){
                        DeleteReeourcesView dell = new DeleteReeourcesView(guiView, players[quiJoue]);
                        dell.setVisible(true);
                    }
                }
            }
        }
    }

    public void updateListener() {
        for(JButton t : guiView.getCaseCol()){
            t.addActionListener(ae -> {
                String[] cords = t.getActionCommand().split("!");
                int x = Integer.parseInt(cords[0]);
                int y = Integer.parseInt(cords[1]);
                if(t.isEnabled()) {
                    if(verifBat(x, y, "col")) {
                        buildBat(x,y,"col");
                        t.setEnabled(false);
                    }
                }
                guiView.updateView();
                updateAcces("col");
            });
        }

        for(JButton t : guiView.getCaseVil()){
            t.addActionListener(ae -> {
                String[] cords = t.getActionCommand().split("!");
                int x = Integer.parseInt(cords[0]);
                int y = Integer.parseInt(cords[1]);
                if(t.isEnabled()) {
                    if((verifBat(x, y, "vil"))){
                        buildBat(x,y,"vil");
                        t.setEnabled(false);
                    }
                }
                guiView.updateView();
                updateAcces("vil");
            });
        }

        for(JButton t : guiView.getCaseRoute()){
            t.addActionListener(ae -> {
                String[] cords = t.getActionCommand().split("!");
                int x = Integer.parseInt(cords[0]);
                int y = Integer.parseInt(cords[1]);
                if(t.isEnabled()) {
                    if(verifRoute(x, y)) {
                        buildRoute(x,y);
                        t.setEnabled(false);
                    }
                }
                guiView.updateView();
                updateAcces("route");
            });
        }

        if(thief) {
            for (JPanel t : guiView.getCaseResource()) {
                t.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (thief) {
                            String[] cords = t.getName().split("!");
                            int x = Integer.parseInt(cords[0]);
                            int y = Integer.parseInt(cords[1]);

                            if (verifRes(x, y)) {
                                thiefPlayer(x, y);
                                thief = false;
                            }
                            guiView.updateView();
                            updateAcces("res");
                        }
                    }
                });
            }
        }
        if (port) {
            for (JPanel t : guiView.getCasePort()) {
                t.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println(2);
                        if (port) {
                            System.out.println(4);

                            String[] cords = t.getName().split("!");
                            int x = Integer.parseInt(cords[0]);
                            int y = Integer.parseInt(cords[1]);

                            if (plateaux.getPlateaux()[y][x] instanceof Port) {
                                System.out.println(3);

                                if (verifPort(x, y)) {
                                    System.out.println(1);

                                    UsePortView portView = new UsePortView(guiView, players[quiJoue], (Port) plateaux.getPlateaux()[y][x]);
                                    portView.setVisible(true);
                                    port = false;
                                }
                            }
                            guiView.updateView();
                            updateAcces("port");
                        }
                    }
                });
            }
        }
    }
}
