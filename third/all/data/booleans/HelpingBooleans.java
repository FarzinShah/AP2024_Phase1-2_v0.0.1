package third.all.data.booleans;

public class HelpingBooleans {
    public static HelpingBooleans instance;


    public boolean lineShower2 = false;
    public boolean isValidToShowSkillTree2= true;



    // todo: BossFight:
    public boolean isValidToPlayBossMusic = true;

    public boolean isEpsilonSetOnFirstPlace = false;
    public boolean isBossPanelLaunched = false;
    public boolean isSqueezed = false;
    public boolean isSqueezedFinished = false;
    public boolean isOnOrbit = false;
    public boolean isSmalled = false;



    public static HelpingBooleans getInstance(){
        if(instance==null) {
            instance = new HelpingBooleans();
            return instance;
        }
        return instance;
    }
    public static void setInstance(HelpingBooleans instance1){
        instance = instance1;
    }
}
