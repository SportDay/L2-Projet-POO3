package l2.poo3.controller.terminal;

import l2.poo3.model.PlayerModel;
import l2.poo3.view.terminal.TerminalView;

public class TerminalController {
    private PlayerModel[] players;
    private int quiJoue;
    private TerminalView view;


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
