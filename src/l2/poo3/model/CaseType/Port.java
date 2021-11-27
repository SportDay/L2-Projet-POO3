package l2.poo3.model.CaseType;

import l2.poo3.model.CaseModel;
import l2.poo3.model.Enum.Resources;

import java.util.ArrayList;
import java.util.Random;

public class Port extends CaseModel {

    static ArrayList<Resources> alredyResources = new ArrayList<>();

    private String prix = "";

    public Port() {
        super(null, "Port", 2);
        setRandomPrice();
        setRandomResources();
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setRandomResources() {
        boolean add = true;
        if(prix.equalsIgnoreCase("1:3")){
            super.setNature(null);
        }else {
            while (add) {
                int randomCase = new Random().nextInt(5);
                switch (randomCase) {
                    case 0:
                        if (!alredyResources.contains(Resources.BOIS)) {
                            super.setNature(Resources.BOIS);
                            //alredyResources.add(Resources.BOIS);
                            add = false;
                        }
                        break;
                    case 1:
                        if (!alredyResources.contains(Resources.ARGILE)) {
                            super.setNature(Resources.ARGILE);
                            //lredyResources.add(Resources.ARGILE);
                            add = false;
                        }
                        break;
                    case 2:
                        if (!alredyResources.contains(Resources.MINERAI)) {
                            super.setNature(Resources.MINERAI);
                            //alredyResources.add(Resources.MINERAI);
                            add = false;
                        }
                        break;
                    case 3:
                        if (!alredyResources.contains(Resources.MOUTON)) {
                            super.setNature(Resources.MOUTON);
                           // alredyResources.add(Resources.MOUTON);
                            add = false;
                        }
                        break;
                    case 4:
                        if (!alredyResources.contains(Resources.BLE)) {
                            super.setNature(Resources.BLE);
                            //alredyResources.add(Resources.BLE);
                            add = false;
                        }
                        break;
                }
            }
        }
    }

    public void setRandomPrice() {
        boolean add = true;
        String type1 = "1:2";
        String type2 = "1:3";
        while(add) {
            int randomCase = new Random().nextInt(2);
            switch (randomCase) {
                case 0:
                    setPrix(type1);
                    add = false;
                    break;
                case 1:
                    setPrix(type2);
                    add = false;
                    break;
            }
        }
    }
}
