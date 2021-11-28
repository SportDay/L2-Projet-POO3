package l2.poo3.model.PlayerType;

import l2.poo3.Other.StringUtil;
import l2.poo3.model.Enum.Pcolor;
import l2.poo3.model.Enum.Resources;
import l2.poo3.model.PlayerModel;

import java.util.Map;

public class Player extends PlayerModel {
    public Player(Pcolor color) {
        super(color);
    }

    public void afficheRessource(){
        Map<Resources, Integer> resourcesList = super.getResources();
        if(resourcesList != null){
            System.out.println("\n+------------+---------+---------+---------+---------+---------+");
            System.out.println("|" + StringUtil.center("Ressources du Joueur " + super.getColor(),62) + "|");
            System.out.println("+------------+---------+---------+---------+---------+---------+");
            System.out.print("| Ressources |");
            for(Map.Entry<Resources, Integer> x : super.getResources().entrySet()){
                System.out.print(StringUtil.center(x.getKey().toString(),9) + "|");
            }
            System.out.println("\n+------------+---------+---------+---------+---------+---------+");
            System.out.print("|  Quantite  |");
            for(Map.Entry<Resources, Integer> x : super.getResources().entrySet()){
                System.out.print(StringUtil.center(x.getValue().toString(),9) + "|");
            }
            System.out.println("\n+------------+---------+---------+---------+---------+---------+");
        }
    }
}
