package l2.poo3.model;

import java.util.Random;

public class Dice {

    private int result;

    public Dice() {

    }

    public int throwTwoDice(){
        result = throwDice() + throwDice();
        return result;
    }

    public int getResult() {
        return result;
    }

    private int throwDice(){
        return new Random().nextInt(11) + 2;
    }
}
