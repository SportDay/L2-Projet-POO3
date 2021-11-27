package l2.poo3;

import l2.poo3.controller.terminal.TerminalController;
import l2.poo3.model.PlateauxModel;
import l2.poo3.view.terminal.TerminalView;



public class CatanMain {
    public static void main(String[] args) {

        PlateauxModel platModel = new PlateauxModel();
        TerminalView terView = new TerminalView();

        platModel.initPlateaux(5,5);

        TerminalController terController = new TerminalController(platModel, terView);
        terController.start();

    }

}
