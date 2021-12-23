package l2.poo3;

import l2.poo3.controller.terminal.TerminalController;
import l2.poo3.model.Enum.Pcolor;
import l2.poo3.model.PlateauxModel;
import l2.poo3.model.PlayerType.Player;
import l2.poo3.view.terminal.TerminalView;



public class CatanMain {
    public static void main(String[] args) {

        PlateauxModel platModel = new PlateauxModel();
        TerminalView terView = new TerminalView();

        Player p = new Player(Pcolor.RED);

        //terView.afficheCarteDev(true, p);
        //terView.afficheRessource(p,true);

        TerminalController terController = new TerminalController(platModel, terView);
        terController.start();//*/


    }

}
