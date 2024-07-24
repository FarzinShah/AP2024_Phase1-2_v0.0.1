package third.all.data.booleans;

import java.util.ArrayList;

public class BooleansOfEnemies {
    public static BooleansOfEnemies instance;
    private ArrayList<Boolean> enemyN;
    private boolean writOfAstrape; //todo: هربار هر چیزی که به اپسیلون بخوره =-2
    private boolean writOfCerberus; //todo: هربار هر چیزی که به اپسیلون بخوره =-2

    public BooleansOfEnemies() {
        writOfAstrape =false;
        enemyN = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            enemyN.add(i,true);
        }
    }

    public ArrayList<Boolean> getEnemyN() {
        return enemyN;
    }

    public void setEnemyN(ArrayList<Boolean> enemyN) {
        this.enemyN = enemyN;
    }

    public boolean isWritOfAstrape() {
        return writOfAstrape;
    }

    public BooleansOfEnemies setWritOfAstrape(boolean writOfAstrape) {
        this.writOfAstrape = writOfAstrape;
        return this;
    }

    public boolean isWritOfCerberus() {
        return writOfCerberus;
    }

    public BooleansOfEnemies setWritOfCerberus(boolean writOfCerberus) {
        this.writOfCerberus = writOfCerberus;
        return this;
    }

    public static BooleansOfEnemies getInstance(){
        if(instance==null) {
            instance = new BooleansOfEnemies();
            return instance;
        }
        return instance;
    }
    public static void setInstance(BooleansOfEnemies instance1){
        instance = instance1;
    }
}
