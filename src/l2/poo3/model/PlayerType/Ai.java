package l2.poo3.model.PlayerType;

import l2.poo3.model.Enum.Pcolor;
import l2.poo3.model.PlayerModel;

public class Ai extends PlayerModel {
    protected Ai(Pcolor color) {
        super(color, true);
    }
}
