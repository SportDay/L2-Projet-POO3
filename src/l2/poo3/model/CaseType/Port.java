package l2.poo3.model.CaseType;

import l2.poo3.model.CaseModel;
import l2.poo3.model.Enum.Resources;

import java.util.ArrayList;
import java.util.Random;

public class Port extends CaseModel {

    static ArrayList<Resources> alredyResources = new ArrayList<>();
    static ArrayList<String> alredyPrice = new ArrayList<>();

    private String prix = "";

    public Port() {
        super(Resources.ARGILE, "Port", 2);
        setRandomResources();
        setRandomPrice();
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setRandomResources() {
        boolean add = true;
        while(add) {
            int randomCase = new Random().nextInt(5);
            switch (randomCase) {
                case 0:
                    if(!alredyResources.contains(Resources.BOIS)){
                        super.setNature(Resources.BOIS);
                        alredyResources.add(Resources.BOIS);
                        add = false;
                    }
                    break;
                case 1:
                    if(!alredyResources.contains(Resources.ARGILE)){
                        super.setNature(Resources.ARGILE);
                        alredyResources.add(Resources.ARGILE);
                        add = false;
                    }
                    break;
                case 2:
                    if(!alredyResources.contains(Resources.MINERAI)){
                        super.setNature(Resources.MINERAI);
                        alredyResources.add(Resources.MINERAI);
                        add = false;
                    }
                    break;
                case 3:
                    if(!alredyResources.contains(Resources.MOUTON)){
                        super.setNature(Resources.MOUTON);
                        alredyResources.add(Resources.MOUTON);
                        add = false;
                    }
                    break;
                case 4:
                    if(!alredyResources.contains(Resources.BLE)){
                        super.setNature(Resources.BLE);
                        alredyResources.add(Resources.BLE);
                        add = false;
                    }
                    break;
            }
        }
    }

    public void setRandomPrice() {
        boolean add = true;
        String type1 = "1:2";
        String type2 = "1:3";
        String type3 = "2:3";
        String type4 = "1:1";
        while(add) {
            int randomCase = new Random().nextInt(5);
            switch (randomCase) {
                case 0:
                    if(!alredyPrice.contains(type1)){
                        setPrix(type1);
                        alredyPrice.add(type1);
                        add = false;
                    }
                    break;
                case 1:
                    if(!alredyPrice.contains(type2)){
                        setPrix(type2);
                        alredyPrice.add(type2);
                        add = false;
                    }
                    break;
                case 2:
                    if(!alredyPrice.contains(type3)){
                        setPrix(type3);
                        alredyPrice.add(type3);
                        add = false;
                    }
                    break;
                case 3:
                    if(!alredyPrice.contains(type4)){
                        setPrix(type4);
                        alredyPrice.add(type4);
                        add = false;
                    }
                    break;
            }
        }
    }
}
