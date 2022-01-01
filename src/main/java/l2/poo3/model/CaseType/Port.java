package l2.poo3.model.CaseType;

import l2.poo3.model.CaseModel;
import l2.poo3.model.Enum.Resources;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Port extends CaseModel {

    private ArrayList<Resources> sellRessource = new ArrayList<>();

    private String prix = "";

    private final Scanner sc = new Scanner(System.in);

    private Resources buy;

    public Port() {
        super(null, "Port", 2);
        setRandomPrice();
        setRandomResources();
    }

    public void setSellRessource(ArrayList<Resources> sellRessource) {
        this.sellRessource = sellRessource;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    private void setRandomResources() {
        boolean add = true;
        if(prix.equalsIgnoreCase("3:1")){
            super.setNature(null);
        }else {
            while (add) {
                int randomCase = new Random().nextInt(5) + 1;
                if(buy == null){
                    buy = choixRessource(randomCase);
                    super.setNature(buy);
                    add = false;
                }
            }
        }
    }

    private Resources choixRessource(int choix){
        switch (choix) {
            case 1:
                return Resources.BOIS;
            case 2:
                return Resources.BLE;
            case 3:
                return Resources.ARGILE;
            case 4:
                return Resources.MINERAI;
            case 5:
                return Resources.MOUTON;
        }
        return null;
    }

    public ArrayList<Resources> getSellRessource() {
        return sellRessource;
    }

    public Scanner getSc() {
        return sc;
    }

    public Resources getBuy() {
        return buy;
    }

    public void setBuy(Resources buy) {
        this.buy = buy;
    }

    private void setRandomPrice() {
        boolean add = true;
        String type1 = "2:1";
        String type2 = "3:1";
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
