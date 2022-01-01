package l2.poo3.model.CaseType;

import l2.poo3.model.CaseModel;
import l2.poo3.model.PlayerModel;

public class Batiment extends CaseModel {

    private PlayerModel player;

    public Batiment() {
        super("");
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }
}
