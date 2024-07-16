package third.all.data;

import third.all.model.Collectable;

import java.util.ArrayList;

public class CollectablesOfEnemies {
    public static CollectablesOfEnemies instance;
    private ArrayList<Collectable> collectablesOfOmenoct;
    private ArrayList<Collectable> collectablesOfYE;
    private ArrayList<Collectable> collectablesOfOrbs;
    private ArrayList<Collectable> collectablesOfWyrm;


    public CollectablesOfEnemies(){
        collectablesOfOmenoct = new ArrayList<>();
        collectablesOfYE = new ArrayList<>();
        collectablesOfOrbs = new ArrayList<>();
        collectablesOfWyrm  = new ArrayList<>();
    }

    public ArrayList<Collectable> getCollectablesOfOmenoct() {
        return collectablesOfOmenoct;
    }

    public void setCollectablesOfOmenoct(ArrayList<Collectable> collectablesOfOmenoct) {
        this.collectablesOfOmenoct = collectablesOfOmenoct;
    }

    public ArrayList<Collectable> getCollectablesOfYE() {
        return collectablesOfYE;
    }

    public void setCollectablesOfYE(ArrayList<Collectable> collectablesOfYE) {
        this.collectablesOfYE = collectablesOfYE;
    }

    public ArrayList<Collectable> getCollectablesOfOrbs() {
        return collectablesOfOrbs;
    }

    public void setCollectablesOfOrbs(ArrayList<Collectable> collectablesOfOrbs) {
        this.collectablesOfOrbs = collectablesOfOrbs;
    }

    public ArrayList<Collectable> getCollectablesOfWyrm() {
        return collectablesOfWyrm;
    }

    public void setCollectablesOfWyrm(ArrayList<Collectable> collectablesOfWyrm) {
        this.collectablesOfWyrm = collectablesOfWyrm;
    }

    public static CollectablesOfEnemies getInstance(){
        if(instance==null) {
            instance = new CollectablesOfEnemies();
            return instance;
        }
        return instance;
    }
    public static void setInstance(CollectablesOfEnemies instance1){
        instance = instance1;
    }


}
