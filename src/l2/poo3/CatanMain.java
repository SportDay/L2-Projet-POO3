package l2.poo3;

import l2.poo3.controller.terminal.TerminalController;
import l2.poo3.model.PlateauxModel;
import l2.poo3.view.terminal.TerminalView;

public class CatanMain {
    public static void main(String[] args) {

        PlateauxModel platModel = new PlateauxModel(5,5);
        TerminalView terView = new TerminalView();

        TerminalController terController = new TerminalController(platModel, terView);

        terView.affichePlateaux();

    }

}
