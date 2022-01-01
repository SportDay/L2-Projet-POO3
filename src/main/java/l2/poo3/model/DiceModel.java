package l2.poo3.model;

import java.util.Random;

public class DiceModel {

    public int throwOneDice(){
        Random r = new Random(System.nanoTime()*System.currentTimeMillis());
        return ((r.nextInt(6) + 1));
    }

    public int throwDice(){
        Random r = new Random(System.nanoTime()*System.currentTimeMillis());
        return ((r.nextInt(6) + 1)+(r.nextInt(6) + 1));
    }
}
