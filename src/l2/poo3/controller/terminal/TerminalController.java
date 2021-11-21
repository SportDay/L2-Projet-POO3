package l2.poo3.controller.terminal;

import l2.poo3.model.CaseModel;
import l2.poo3.model.CaseType.*;
import l2.poo3.model.PlateauxModel;
import l2.poo3.model.PlayerModel;
import l2.poo3.view.terminal.TerminalView;

import java.util.Random;

public class TerminalController {
    private PlayerModel[] players;
    private PlateauxModel plateaux;
    private int quiJoue;
    private TerminalView view;

    public TerminalController(PlateauxModel plateaux, TerminalView view){
        this.plateaux = plateaux;
        this.view = view;
        view.setController(this);
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

}
