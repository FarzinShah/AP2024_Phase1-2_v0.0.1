package third.all.data;

import java.util.ArrayList;

public class BooleansOf_IsValidToShow {
    public static BooleansOf_IsValidToShow instance;
    private ArrayList<Boolean> isValidToShowEnemies;
    private ArrayList<Boolean> isValidToShowPanels;
    private ArrayList<Boolean> isValidToShowBlackOrbPanels;
    private ArrayList<Boolean> isValidToShowBlackOrbEntity;
    private ArrayList<Boolean> isValidToCollectEnemies;
    private boolean isValidToAttackCerberus;
    private boolean isValidToShowBossPanel;

    //todo: (isValidToShowEnemies) Element Guider:
    // 0. isValidToShowArchmire
    // 1. isValidToShowNecropick
    // 2. isValidToShowOmenoct
    // 3. isValidToShowOWyrm
    // 4. isValidToShowOBarricados
    // 5. isValidToShowBlackOrb ---> isValidToShowBlackOrb.get(5) is for balls init;
    // 6. isValidToShowCerberus ---> it's not enemy.

    //----------------------------------------
    //todo: (isValidToShowPanels) Element Guider:
    // 0. isValidToShowRedZone
    // 1. isValidToShowMainPanel
    // 2. isValidToShowPanel2
    // 3. isValidToShowPanel3
    // 4. isValidToShowPanel4
    // 5. isValidToShowPanel5
    // 6. isValidToShowBlackOrbsPanels


    public BooleansOf_IsValidToShow() {
        isValidToShowEnemies = new ArrayList<>();
        isValidToShowPanels = new ArrayList<>();
        isValidToCollectEnemies = new ArrayList<>();
        isValidToShowBlackOrbPanels = new ArrayList<>();
        isValidToShowBlackOrbEntity = new ArrayList<>();
        isValidToShowBossPanel = false;
        isValidToAttackCerberus = true;

        for (int i = 0; i < 7; i++) {
            isValidToShowEnemies.add(i,false);
        }
        for (int i = 0; i < 7; i++) {
            isValidToShowPanels.add(i,false);
        }
        for (int i = 0; i < 5; i++) {
            isValidToCollectEnemies.add(i,false);
        }
        for (int i = 0; i < 6; i++) {
            isValidToShowBlackOrbPanels.add(i,false);
        }
        for (int i = 0; i < 5; i++) {
            isValidToShowBlackOrbEntity.add(i,true);
        }
    }

    public ArrayList<Boolean> getIsValidToShowEnemies() {
        return isValidToShowEnemies;
    }

    public void setIsValidToShowEnemies(ArrayList<Boolean> isValidToShowEnemies) {
        this.isValidToShowEnemies = isValidToShowEnemies;
    }

    public ArrayList<Boolean> getIsValidToShowPanels() {
        return isValidToShowPanels;
    }

    public void setIsValidToShowPanels(ArrayList<Boolean> isValidToShowPanels) {
        this.isValidToShowPanels = isValidToShowPanels;
    }

    public ArrayList<Boolean> getIsValidToCollectEnemies() {
        return isValidToCollectEnemies;
    }

    public void setIsValidToCollectEnemies(ArrayList<Boolean> isValidToCollectEnemies) {
        this.isValidToCollectEnemies = isValidToCollectEnemies;
    }

    public ArrayList<Boolean> getIsValidToShowBlackOrbPanels() {
        return isValidToShowBlackOrbPanels;
    }

    public void setIsValidToShowBlackOrbPanels(ArrayList<Boolean> isValidToShowBlackOrbPanels) {
        this.isValidToShowBlackOrbPanels = isValidToShowBlackOrbPanels;
    }

    public ArrayList<Boolean> getIsValidToShowBlackOrbEntity() {
        return isValidToShowBlackOrbEntity;
    }

    public void setIsValidToShowBlackOrbEntity(ArrayList<Boolean> isValidToShowBlackOrbEntity) {
        this.isValidToShowBlackOrbEntity = isValidToShowBlackOrbEntity;
    }

    public boolean isValidToAttackCerberus() {
        return isValidToAttackCerberus;
    }

    public BooleansOf_IsValidToShow setValidToAttackCerberus(boolean validToAttackCerberus) {
        isValidToAttackCerberus = validToAttackCerberus;
        return this;
    }

    public boolean isValidToShowBossPanel() {
        return isValidToShowBossPanel;
    }

    public BooleansOf_IsValidToShow setValidToShowBossPanel(boolean validToShowBossPanel) {
        isValidToShowBossPanel = validToShowBossPanel;
        return this;
    }

    public static BooleansOf_IsValidToShow getInstance(){
        if(instance==null) {
            instance = new BooleansOf_IsValidToShow();
            return instance;
        }
        return instance;
    }
    public static void setInstance(BooleansOf_IsValidToShow instance1){
        instance = instance1;
    }
}
