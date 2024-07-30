package third.all.data.booleans;

public class HelpingBooleans {
    public static HelpingBooleans instance;


    public boolean lineShower2 = false;
    public boolean isValidToShowSkillTree2 = true;
    public boolean isValidToShowStartingWelcome = true;
    public boolean isEpsilonValidToMove = true;
    public boolean isValidToLargerMainPanel = false;

    // todo: BossFight:
    public boolean isValidToPlayBossMusic = true;

    public boolean isEpsilonSetOnFirstPlace = false;
    public boolean isBossPanelLaunched = false;
    public boolean isSqueezed = false;
    public boolean isSqueezedFinished = false;
    public boolean isOnOrbit = false;
    public boolean isOnOrbitWyrm = false;
    public boolean isSmalled = false;
    public boolean isProjectile = false;
    public boolean isProjectileFinished = false;
    public boolean isVomitFinished = false;
    public boolean isSmileyValidToMoveRight_Vomit = true;
    public boolean isSmileyValidToMoveLeft_Vomit = false;
    public boolean isSmileyValidToMoveUp_Vomit = false;
    public boolean isSmileyValidToMoveDown_Vomit = false;
    public boolean isSmileyValidToMoveRight2_Vomit = false;
    public boolean isFistLaunched = false;
    public boolean isFistPunchedLeft = false;
    public boolean isFistPunchedRight = false;
    public boolean isFistPunchedUp = false;
    public boolean isFistPunchedDown = false;
    public boolean startPunchLeft = false;
    public boolean startPunchRight = false;
    public boolean startPunchUp = false;
    public boolean startPunchDown = false;
    public boolean startQuakeMouseAttack = false;
    public boolean doQuakeMouseAttack = false;
    public boolean cerberusBool = true;
    public boolean isValidToMoveOmenoctInPanel0 = false;





    public boolean isValidToShowFist = false;
    public boolean isMovingDown = true;
    public boolean isMovingUp = false;


    public static HelpingBooleans getInstance() {
        if (instance == null) {
            instance = new HelpingBooleans();
            return instance;
        }
        return instance;
    }

    public static void setInstance(HelpingBooleans instance1) {
        instance = instance1;
    }
}
