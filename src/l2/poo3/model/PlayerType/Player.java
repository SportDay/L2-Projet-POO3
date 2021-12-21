package l2.poo3.model.PlayerType;

import l2.poo3.Other.StringUtil;
import l2.poo3.model.Enum.CartesDev;
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
            System.out.println("|" + StringUtil.center("Ressources du Joueur: " + super.getColor(),62) + "|");
            System.out.println("+------------+---------+---------+---------+---------+---------+");
            System.out.print("| Ressources |");
            for(Map.Entry<Resources, Integer> x : resourcesList.entrySet()){
                System.out.print(StringUtil.center(x.getKey().toString(),9) + "|");
            }
            System.out.println("\n+------------+---------+---------+---------+---------+---------+");
            System.out.print("|  Quantite  |");
            for(Map.Entry<Resources, Integer> x : resourcesList.entrySet()){
                System.out.print(StringUtil.center(x.getValue().toString(),9) + "|");
            }
            System.out.println("\n+------------+---------+---------+---------+---------+---------+");
        }
    }

    public void afficheCarteDev(boolean showNum){
        Map<CartesDev, Integer> resourcesList = super.getCartesDev();
        if(resourcesList != null){
            System.out.println("\n+-------------+-----------+-----------+");
            System.out.println("|" + StringUtil.center("Cartes Developpement du Joueur: " + super.getColor(),37) + "|");
            System.out.println("+-------------+-----------+-----------+");
            System.out.print("|   Cartes    |");
            for(Map.Entry<CartesDev, Integer> x : resourcesList.entrySet()){
                System.out.print(StringUtil.center(x.getKey().toString(),11) + "|");
            }
            System.out.println("\n+-------------+-----------+-----------+");
            System.out.print("|  Quantite   |");
            for(Map.Entry<CartesDev, Integer> x : resourcesList.entrySet()){
                System.out.print(StringUtil.center(x.getValue().toString(),11) + "|");
            }
            if(showNum) {
                System.out.println("\n+-------------+-----------+-----------+");
                System.out.print("|     ID      |");
                for (int i = 0; i < resourcesList.size(); i++) {
                    System.out.print(StringUtil.center(i + 1 + "", 11) + "|");
                }
            }
            System.out.println("\n+-------------+-----------+-----------+");
        }
    }
}
