package l2.poo3.model;

import java.util.Random;

public class DiceModel {

    private int random;

    public DiceModel() {
        random = new Random().nextInt(12-2) + 2;
    }

    public void throwDice(){
        random = new Random().nextInt(12-2) + 2;
    }

    public int getRandom() {
        return random;
    }
}
