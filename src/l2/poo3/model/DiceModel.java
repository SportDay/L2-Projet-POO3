package l2.poo3.model;

import java.util.Random;

public class DiceModel {

    private int result;

    public int throwDice(){
        return new Random().nextInt(11) + 2;
    }
}
