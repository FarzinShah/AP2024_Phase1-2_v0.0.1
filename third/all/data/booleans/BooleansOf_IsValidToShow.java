package third.all.data.booleans;

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
    private ArrayList<Boolean> isValidToShowStores;
    private ArrayList<Boolean> isValidToAttackBoss;
    private ArrayList<Boolean> isEpsilonIntoPanel;


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

    //----------------------------------------
    //todo: (isValidToShowStores) Element Guider:
    // 0. isValidToShowSkillTreeGUI
    // 1. isValidToShowShopping
    // 2. isValidToShow????

    //----------------------------------------
    //todo: (isValidToAttackBoss) Element Guider:
    // 0. isValidToAttackSqueeze
    // 1. isValidToAttackProjectile
    // 2. isValidToAttackVomit
    // 3. isValidToAttackPowerPunch
    // 4. isValidToAttackQuake
    // 5. isValidToAttackRapidFire
    // 6. isValidToAttackSlap
    // 7. isValidToAttackAnnihilator

    //----------------------------------------
    //todo: (isEpsilonIntoPanel) Element Guider:
    // 0. isEpsilonIntoPanel0 - not needed
    // 1. isEpsilonIntoPanel1
    // 2. isEpsilonIntoPanel2
    // 3. isEpsilonIntoPanel3
    // 4. isEpsilonIntoPanel4
    // 5. isEpsilonIntoPanel5





    public BooleansOf_IsValidToShow() {
        isValidToShowEnemies = new ArrayList<>();
        isValidToShowPanels = new ArrayList<>();
        isValidToCollectEnemies = new ArrayList<>();
        isValidToShowBlackOrbPanels = new ArrayList<>();
        isValidToShowBlackOrbEntity = new ArrayList<>();
        isValidToShowStores = new ArrayList<>();
        isValidToShowBossPanel = false; // todo: false
        isValidToAttackCerberus = true;
        isValidToAttackBoss = new ArrayList<>();
        isEpsilonIntoPanel = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            isValidToShowEnemies.add(i,false);
        }
        for (int i = 0; i < 7; i++) {
            isValidToShowPanels.add(i,false);
        }
//        isValidToShowPanels.set(1,false);
//        isValidToShowPanels.set(1,true);
        for (int i = 0; i < 5; i++) {
            isValidToCollectEnemies.add(i,false);
            isEpsilonIntoPanel.add(i,false);
        }
        for (int i = 0; i < 6; i++) {
            isValidToShowBlackOrbPanels.add(i,false);
        }
        for (int i = 0; i < 5; i++) {
            isValidToShowBlackOrbEntity.add(i,true);
        }
        for (int i = 0; i < 3; i++) {
            isValidToShowStores.add(i,true);
        }
        for (int i = 0; i < 8; i++) {
            isValidToAttackBoss.add(i,false);

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

    public void setValidToShowBossPanel(boolean validToShowBossPanel) {
        isValidToShowBossPanel = validToShowBossPanel;
    }

    public ArrayList<Boolean> getIsValidToShowStores() {
        return isValidToShowStores;
    }

    public BooleansOf_IsValidToShow setIsValidToShowStores(ArrayList<Boolean> isValidToShowStores) {
        this.isValidToShowStores = isValidToShowStores;
        return this;
    }

    public ArrayList<Boolean> getIsValidToAttackBoss() {
        return isValidToAttackBoss;
    }

    public BooleansOf_IsValidToShow setIsValidToAttackBoss(ArrayList<Boolean> isValidToAttackBoss) {
        this.isValidToAttackBoss = isValidToAttackBoss;
        return this;
    }

    public ArrayList<Boolean> getIsEpsilonIntoPanel() {
        return isEpsilonIntoPanel;
    }

    public BooleansOf_IsValidToShow setIsEpsilonIntoPanel(ArrayList<Boolean> isEpsilonIntoPanel) {
        this.isEpsilonIntoPanel = isEpsilonIntoPanel;
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
