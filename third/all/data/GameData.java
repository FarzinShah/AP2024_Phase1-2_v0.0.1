package third.all.data;

import third.all.controller.componentController.Input;
import third.all.controller.componentController.TKM2_Item_Model;
import third.all.controller.componentController.TWM_Item_Model;
import third.all.controller.entity.GameObject;
import third.all.controller.movement.Position;
import third.all.gameComponents.game.Panel;
import third.all.gameComponents.preGameComponent.Timer1;
import third.all.model.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
/*
public class GameData {
    private int spentMilliSecond_D;
    private int spentMilliSecondW2_D;
    private Timer1 timerOfGame_D;
    public static ArrayList<GameObject> gameObjects_D;
    public static ArrayList<GameObject> yellowEnemies1_D;
    public static ArrayList<GameObject> greenEnemies1_D;
    private LinkedList<Boolean> isCollidedY_D;
    private LinkedList<Boolean> isCollidedG_D;
    private LinkedList<Boolean> isCollided_D;
    private LinkedList<Boolean> isCollidedEnemies_D;
    private ArrayList<TimerData> timers_D;
    public static double GLASS_FRAME_DIMENSION_WIDTH_D;
    public static double GLASS_FRAME_DIMENSION_HEIGHT_D;
    public static Integer WAVE;
    public static double SECOND_FRAME_LOCATION_X_D, SECOND_FRAME_LOCATION_Y_D;
    public static double THIRD_FRAME_LOCATION_X_D, THIRD_FRAME_LOCATION_Y_D;
    public static double FOURTH_FRAME_LOCATION_X_D, FOURTH_FRAME_LOCATION_Y_D;
    public static int FIFTH_FRAME_LOCATION_X_D, FIFTH_FRAME_LOCATION_Y_D;
    public static ArrayList<TKM2_Item_Model> collectibleItems_D;
    public static ArrayList<TKM2_Item_Model> collectibleItemsG_D;
    public static ArrayList<TKM2_Item_Model> collectibleItemsY_D;
    public static Input input_D;
    public static double boundX_D;
    public static double boundY_D;
    public static Integer HP_D;
    public static Double XP_D;
    public static Integer EPSILON_WIDTH_D;
    public static Integer EPSILON_LENGTH_D;
    public static Integer ACCELERATION_OF_GREEN_ENEMIES_D;
    public static Integer ACCELERATION_OF_YELLOW_ENEMIES_D;
    public static Integer ACCELERATION_OF_EPSILON_D;
    public static boolean waveOHB_D;
    public static boolean IS_VALID_STORE_D;
    public static boolean is_Writ_Of_Proteus_D;
    public static boolean is_Writ_Of_Aceso_D;
    public static boolean is_Writ_Of_Ares_D;
    public static ArrayList<Bullet> bullets_D;
    public static ArrayList<Bullet> bulletsOfOmenoct_D;
    public static ArrayList<Bullet> bulletsOfNecropick_D;
    public static Point OMENOCT_POSITION_D;
    public static int OMENOCT_SIZE_D;
    public static Point NECROPICK_POSITION_D;
    public static int NECROPICK_SIZE_D;
    public static Point ARCHMIRE_POSITION_D;
    public static int ARCHMIRE_SIZE_D;

    //todo: Wave 1:
    public static boolean enemy1_D;
    public static boolean enemy2_D;
    public static boolean enemy3_D;
    public static boolean enemy4_D;
    public static boolean enemy5_D;
    public static boolean enemy6_D;
    public static boolean isFinishedWave1_D;

    public static boolean isRightPanel2_D;
    public static boolean isEnteredToPanel2_Omenoct_D;
    public static boolean isEnteredToPanel1_Omenoct_D;

    //todo: Wave 2:
    public static boolean enemy7_D;
    public static boolean enemy8_D;
    public static boolean enemy9_D;
    public static boolean enemy10_D;
    public static boolean enemy11_D;
    public static boolean enemy12_D;
    public static boolean enemy13_D;
    public static boolean enemy14_D;
    public static boolean isFinishedWave2_D;

    //todo: Wave 3:
    public static boolean enemy15_D;
    public static boolean enemy16_D;
    public static boolean enemy17_D;
    public static boolean enemy18_D;
    public static boolean enemy19_D;
    public static boolean enemy20_D;
    public static boolean enemy21_D;
    public static boolean enemy22_D;
    //todo: Wave 4:

    public static boolean enemy23_D;
    public static boolean enemy24_D;
    public static boolean enemy25_D;
    public static boolean enemy26_D;
    public static boolean enemy27_D;
    //todo: Wave 5:

    public static boolean enemy28_D;
    public static boolean enemy29_D;
    public static boolean enemy30_D;
    //todo
    public static ArrayList<Boolean> showOfCollectibles_D;
    public static ArrayList<Boolean> showOfCollectiblesG_D;
    public static ArrayList<Boolean> showOfCollectiblesY_D;
    public static boolean necropick_isVisible_D;
    public static boolean runningOfNecropick_D;
    public static boolean isRightMoveArchmire_D;

    //todo: بولین هایی که برای موقع اعمالشون تو موج ها موقعی که وقتش بشه باید ترو بشن.
    public static boolean isValidToShowPanel2_D;
    public static boolean isValidToShowPanel3_D;
    public static boolean isValidToShowPanel4_D;
    public static boolean isValidToShowRedZone_D;


    public static boolean isValidToShowArchmire_D;
    public static boolean isValidToShowNecropick_D;
    public static boolean isValidToShowOmenoct_D;
    public static boolean isValidToShowOWyrm_D;
    public static boolean isValidToShowOBarricados_D;

    public static boolean isValidToCollectOmenoct_D;

    public static ArrayList<Boolean> isValidYEtoCollect_D;

    //TODO: MyPanel Data:

    public static boolean timer1Starter_D;
    public static boolean showOfPointerItem_D;
    public TWM_Item_Model twm_item_model1_D;
    public static ArrayList<Panel> panels_D;
    private Panel redZone_D;
    private Rectangle redZoneRectangle_D;
    public static ArrayList<Collectable> collectablesOfOmenoct_D;
    public static ArrayList<Collectable> collectablesOfYE_D;
    public static Integer REDUCTION_RATE_OF_HP_OF_ENEMY_D;

    private LinkedList<Position> positionsOfCollectiblesY_D;
    private LinkedList<Position> positionsOfCollectibles_D;

    private LinkedList<Position> positionsOfCollectiblesG_D;

    private ArrayList<Boolean> showOfCollectiblesHelperG_D;
    private ArrayList<Boolean> showOfCollectiblesHelperY_D;

    public static Omenoct omenoct_D;
    public static Necropick necropick_D;
    public static Archmire archmire_D;
    public static ArrayList<Point> trails_D;


//    public GameData(int spentMilliSecond_D,
//                    int spentMilliSecondW2_D,
//                    Timer1 timerOfGame_D,
//                    LinkedList<Boolean> isCollidedY_D,
//                    LinkedList<Boolean> isCollidedG_D,
//                    LinkedList<Boolean> isCollided_D,
//                    LinkedList<Boolean> isCollidedEnemies_D,
//                    ArrayList<TimerData> timers_D,
//                    TWM_Item_Model twm_item_model1_D,
//                    Panel redZone_D,
//                    Rectangle redZoneRectangle_D,
//                    LinkedList<Position> positionsOfCollectiblesY_D,
//                    LinkedList<Position> positionsOfCollectibles_D,
//                    LinkedList<Position> positionsOfCollectiblesG_D,
//                    ArrayList<Boolean> showOfCollectiblesHelperG_D,
//                    ArrayList<Boolean> showOfCollectiblesHelperY_D ) {
//        this.spentMilliSecond_D = spentMilliSecond_D;
//        this.spentMilliSecondW2_D = spentMilliSecondW2_D;
//        this.timerOfGame_D = timerOfGame_D;
//        this.isCollidedY_D = isCollidedY_D;
//        this.isCollidedG_D = isCollidedG_D;
//        this.isCollided_D = isCollided_D;
//        this.isCollidedEnemies_D = isCollidedEnemies_D;
//        this.timers_D = timers_D;
//        this.twm_item_model1_D = twm_item_model1_D;
//        this.redZone_D = redZone_D;
//        this.redZoneRectangle_D = redZoneRectangle_D;
//        this.positionsOfCollectiblesY_D = positionsOfCollectiblesY_D;
//        this.positionsOfCollectibles_D = positionsOfCollectibles_D;
//        this.positionsOfCollectiblesG_D = positionsOfCollectiblesG_D;
//        this.showOfCollectiblesHelperG_D = showOfCollectiblesHelperG_D;
//        this.showOfCollectiblesHelperY_D = showOfCollectiblesHelperY_D;
//    }

    public int getSpentMilliSecond_D() {
        return spentMilliSecond_D;
    }

    public void setSpentMilliSecond_D(int spentMilliSecond_D) {
        this.spentMilliSecond_D = spentMilliSecond_D;
    }

    public int getSpentMilliSecondW2_D() {
        return spentMilliSecondW2_D;
    }

    public void setSpentMilliSecondW2_D(int spentMilliSecondW2_D) {
        this.spentMilliSecondW2_D = spentMilliSecondW2_D;
    }

    public Timer1 getTimerOfGame_D() {
        return timerOfGame_D;
    }

    public void setTimerOfGame_D(Timer1 timerOfGame_D) {
        this.timerOfGame_D = timerOfGame_D;
    }

    public static ArrayList<GameObject> getGameObjects_D() {
        return gameObjects_D;
    }

    public static void setGameObjects_D(ArrayList<GameObject> gameObjects_D) {
        GameData.gameObjects_D = gameObjects_D;
    }

    public static ArrayList<GameObject> getYellowEnemies1_D() {
        return yellowEnemies1_D;
    }

    public static void setYellowEnemies1_D(ArrayList<GameObject> yellowEnemies1_D) {
        GameData.yellowEnemies1_D = yellowEnemies1_D;
    }

    public static ArrayList<GameObject> getGreenEnemies1_D() {
        return greenEnemies1_D;
    }

    public static void setGreenEnemies1_D(ArrayList<GameObject> greenEnemies1_D) {
        GameData.greenEnemies1_D = greenEnemies1_D;
    }

    public LinkedList<Boolean> getIsCollidedY_D() {
        return isCollidedY_D;
    }

    public void setIsCollidedY_D(LinkedList<Boolean> isCollidedY_D) {
        this.isCollidedY_D = isCollidedY_D;
    }

    public LinkedList<Boolean> getIsCollidedG_D() {
        return isCollidedG_D;
    }

    public void setIsCollidedG_D(LinkedList<Boolean> isCollidedG_D) {
        this.isCollidedG_D = isCollidedG_D;
    }

    public LinkedList<Boolean> getIsCollided_D() {
        return isCollided_D;
    }

    public void setIsCollided_D(LinkedList<Boolean> isCollided_D) {
        this.isCollided_D = isCollided_D;
    }

    public LinkedList<Boolean> getIsCollidedEnemies_D() {
        return isCollidedEnemies_D;
    }

    public void setIsCollidedEnemies_D(LinkedList<Boolean> isCollidedEnemies_D) {
        this.isCollidedEnemies_D = isCollidedEnemies_D;
    }

    public ArrayList<TimerData> getTimers_D() {
        return timers_D;
    }

    public void setTimers_D(ArrayList<TimerData> timers_D) {
        this.timers_D = timers_D;
    }

    public static double getGlassFrameDimensionWidthD() {
        return GLASS_FRAME_DIMENSION_WIDTH_D;
    }

    public static void setGlassFrameDimensionWidthD(double glassFrameDimensionWidthD) {
        GLASS_FRAME_DIMENSION_WIDTH_D = glassFrameDimensionWidthD;
    }

    public static double getGlassFrameDimensionHeightD() {
        return GLASS_FRAME_DIMENSION_HEIGHT_D;
    }

    public static void setGlassFrameDimensionHeightD(double glassFrameDimensionHeightD) {
        GLASS_FRAME_DIMENSION_HEIGHT_D = glassFrameDimensionHeightD;
    }

    public static Integer getWAVE() {
        return WAVE;
    }

    public static void setWAVE(Integer WAVE) {
        GameData.WAVE = WAVE;
    }

    public static double getSecondFrameLocationXD() {
        return SECOND_FRAME_LOCATION_X_D;
    }

    public static void setSecondFrameLocationXD(double secondFrameLocationXD) {
        SECOND_FRAME_LOCATION_X_D = secondFrameLocationXD;
    }

    public static double getSecondFrameLocationYD() {
        return SECOND_FRAME_LOCATION_Y_D;
    }

    public static void setSecondFrameLocationYD(double secondFrameLocationYD) {
        SECOND_FRAME_LOCATION_Y_D = secondFrameLocationYD;
    }

    public static double getThirdFrameLocationXD() {
        return THIRD_FRAME_LOCATION_X_D;
    }

    public static void setThirdFrameLocationXD(double thirdFrameLocationXD) {
        THIRD_FRAME_LOCATION_X_D = thirdFrameLocationXD;
    }

    public static double getThirdFrameLocationYD() {
        return THIRD_FRAME_LOCATION_Y_D;
    }

    public static void setThirdFrameLocationYD(double thirdFrameLocationYD) {
        THIRD_FRAME_LOCATION_Y_D = thirdFrameLocationYD;
    }

    public static double getFourthFrameLocationXD() {
        return FOURTH_FRAME_LOCATION_X_D;
    }

    public static void setFourthFrameLocationXD(double fourthFrameLocationXD) {
        FOURTH_FRAME_LOCATION_X_D = fourthFrameLocationXD;
    }

    public static double getFourthFrameLocationYD() {
        return FOURTH_FRAME_LOCATION_Y_D;
    }

    public static void setFourthFrameLocationYD(double fourthFrameLocationYD) {
        FOURTH_FRAME_LOCATION_Y_D = fourthFrameLocationYD;
    }

    public static int getFifthFrameLocationXD() {
        return FIFTH_FRAME_LOCATION_X_D;
    }

    public static void setFifthFrameLocationXD(int fifthFrameLocationXD) {
        FIFTH_FRAME_LOCATION_X_D = fifthFrameLocationXD;
    }

    public static int getFifthFrameLocationYD() {
        return FIFTH_FRAME_LOCATION_Y_D;
    }

    public static void setFifthFrameLocationYD(int fifthFrameLocationYD) {
        FIFTH_FRAME_LOCATION_Y_D = fifthFrameLocationYD;
    }

    public static ArrayList<TKM2_Item_Model> getCollectibleItems_D() {
        return collectibleItems_D;
    }

    public static void setCollectibleItems_D(ArrayList<TKM2_Item_Model> collectibleItems_D) {
        GameData.collectibleItems_D = collectibleItems_D;
    }

    public static ArrayList<TKM2_Item_Model> getCollectibleItemsG_D() {
        return collectibleItemsG_D;
    }

    public static void setCollectibleItemsG_D(ArrayList<TKM2_Item_Model> collectibleItemsG_D) {
        GameData.collectibleItemsG_D = collectibleItemsG_D;
    }

    public static ArrayList<TKM2_Item_Model> getCollectibleItemsY_D() {
        return collectibleItemsY_D;
    }

    public static void setCollectibleItemsY_D(ArrayList<TKM2_Item_Model> collectibleItemsY_D) {
        GameData.collectibleItemsY_D = collectibleItemsY_D;
    }

    public static Input getInput_D() {
        return input_D;
    }

    public static void setInput_D(Input input_D) {
        GameData.input_D = input_D;
    }

    public static double getBoundX_D() {
        return boundX_D;
    }

    public static void setBoundX_D(double boundX_D) {
        GameData.boundX_D = boundX_D;
    }

    public static double getBoundY_D() {
        return boundY_D;
    }

    public static void setBoundY_D(double boundY_D) {
        GameData.boundY_D = boundY_D;
    }

    public static Integer getHpD() {
        return HP_D;
    }

    public static void setHpD(Integer hpD) {
        HP_D = hpD;
    }

    public static Double getXpD() {
        return XP_D;
    }

    public static void setXpD(Double xpD) {
        XP_D = xpD;
    }

    public static Integer getEpsilonWidthD() {
        return EPSILON_WIDTH_D;
    }

    public static void setEpsilonWidthD(Integer epsilonWidthD) {
        EPSILON_WIDTH_D = epsilonWidthD;
    }

    public static Integer getEpsilonLengthD() {
        return EPSILON_LENGTH_D;
    }

    public static void setEpsilonLengthD(Integer epsilonLengthD) {
        EPSILON_LENGTH_D = epsilonLengthD;
    }

    public static Integer getAccelerationOfGreenEnemiesD() {
        return ACCELERATION_OF_GREEN_ENEMIES_D;
    }

    public static void setAccelerationOfGreenEnemiesD(Integer accelerationOfGreenEnemiesD) {
        ACCELERATION_OF_GREEN_ENEMIES_D = accelerationOfGreenEnemiesD;
    }

    public static Integer getAccelerationOfYellowEnemiesD() {
        return ACCELERATION_OF_YELLOW_ENEMIES_D;
    }

    public static void setAccelerationOfYellowEnemiesD(Integer accelerationOfYellowEnemiesD) {
        ACCELERATION_OF_YELLOW_ENEMIES_D = accelerationOfYellowEnemiesD;
    }

    public static Integer getAccelerationOfEpsilonD() {
        return ACCELERATION_OF_EPSILON_D;
    }

    public static void setAccelerationOfEpsilonD(Integer accelerationOfEpsilonD) {
        ACCELERATION_OF_EPSILON_D = accelerationOfEpsilonD;
    }

    public static boolean isWaveOHB_D() {
        return waveOHB_D;
    }

    public static void setWaveOHB_D(boolean waveOHB_D) {
        GameData.waveOHB_D = waveOHB_D;
    }

    public static boolean isIsValidStoreD() {
        return IS_VALID_STORE_D;
    }

    public static void setIsValidStoreD(boolean isValidStoreD) {
        IS_VALID_STORE_D = isValidStoreD;
    }

    public static boolean isIs_Writ_Of_Proteus_D() {
        return is_Writ_Of_Proteus_D;
    }

    public static void setIs_Writ_Of_Proteus_D(boolean is_Writ_Of_Proteus_D) {
        GameData.is_Writ_Of_Proteus_D = is_Writ_Of_Proteus_D;
    }

    public static boolean isIs_Writ_Of_Aceso_D() {
        return is_Writ_Of_Aceso_D;
    }

    public static void setIs_Writ_Of_Aceso_D(boolean is_Writ_Of_Aceso_D) {
        GameData.is_Writ_Of_Aceso_D = is_Writ_Of_Aceso_D;
    }

    public static boolean isIs_Writ_Of_Ares_D() {
        return is_Writ_Of_Ares_D;
    }

    public static void setIs_Writ_Of_Ares_D(boolean is_Writ_Of_Ares_D) {
        GameData.is_Writ_Of_Ares_D = is_Writ_Of_Ares_D;
    }

    public static ArrayList<Bullet> getBullets_D() {
        return bullets_D;
    }

    public static void setBullets_D(ArrayList<Bullet> bullets_D) {
        GameData.bullets_D = bullets_D;
    }

    public static ArrayList<Bullet> getBulletsOfOmenoct_D() {
        return bulletsOfOmenoct_D;
    }

    public static void setBulletsOfOmenoct_D(ArrayList<Bullet> bulletsOfOmenoct_D) {
        GameData.bulletsOfOmenoct_D = bulletsOfOmenoct_D;
    }

    public static ArrayList<Bullet> getBulletsOfNecropick_D() {
        return bulletsOfNecropick_D;
    }

    public static void setBulletsOfNecropick_D(ArrayList<Bullet> bulletsOfNecropick_D) {
        GameData.bulletsOfNecropick_D = bulletsOfNecropick_D;
    }

    public static Point getOmenoctPositionD() {
        return OMENOCT_POSITION_D;
    }

    public static void setOmenoctPositionD(Point omenoctPositionD) {
        OMENOCT_POSITION_D = omenoctPositionD;
    }

    public static int getOmenoctSizeD() {
        return OMENOCT_SIZE_D;
    }

    public static void setOmenoctSizeD(int omenoctSizeD) {
        OMENOCT_SIZE_D = omenoctSizeD;
    }

    public static Point getNecropickPositionD() {
        return NECROPICK_POSITION_D;
    }

    public static void setNecropickPositionD(Point necropickPositionD) {
        NECROPICK_POSITION_D = necropickPositionD;
    }

    public static int getNecropickSizeD() {
        return NECROPICK_SIZE_D;
    }

    public static void setNecropickSizeD(int necropickSizeD) {
        NECROPICK_SIZE_D = necropickSizeD;
    }

    public static Point getArchmirePositionD() {
        return ARCHMIRE_POSITION_D;
    }

    public static void setArchmirePositionD(Point archmirePositionD) {
        ARCHMIRE_POSITION_D = archmirePositionD;
    }

    public static int getArchmireSizeD() {
        return ARCHMIRE_SIZE_D;
    }

    public static void setArchmireSizeD(int archmireSizeD) {
        ARCHMIRE_SIZE_D = archmireSizeD;
    }

    public static boolean isEnemy1_D() {
        return enemy1_D;
    }

    public static void setEnemy1_D(boolean enemy1_D) {
        GameData.enemy1_D = enemy1_D;
    }

    public static boolean isEnemy2_D() {
        return enemy2_D;
    }

    public static void setEnemy2_D(boolean enemy2_D) {
        GameData.enemy2_D = enemy2_D;
    }

    public static boolean isEnemy3_D() {
        return enemy3_D;
    }

    public static void setEnemy3_D(boolean enemy3_D) {
        GameData.enemy3_D = enemy3_D;
    }

    public static boolean isEnemy4_D() {
        return enemy4_D;
    }

    public static void setEnemy4_D(boolean enemy4_D) {
        GameData.enemy4_D = enemy4_D;
    }

    public static boolean isEnemy5_D() {
        return enemy5_D;
    }

    public static void setEnemy5_D(boolean enemy5_D) {
        GameData.enemy5_D = enemy5_D;
    }

    public static boolean isEnemy6_D() {
        return enemy6_D;
    }

    public static void setEnemy6_D(boolean enemy6_D) {
        GameData.enemy6_D = enemy6_D;
    }

    public static boolean isIsFinishedWave1_D() {
        return isFinishedWave1_D;
    }

    public static void setIsFinishedWave1_D(boolean isFinishedWave1_D) {
        GameData.isFinishedWave1_D = isFinishedWave1_D;
    }

    public static boolean isIsRightPanel2_D() {
        return isRightPanel2_D;
    }

    public static void setIsRightPanel2_D(boolean isRightPanel2_D) {
        GameData.isRightPanel2_D = isRightPanel2_D;
    }

    public static boolean isIsEnteredToPanel2_Omenoct_D() {
        return isEnteredToPanel2_Omenoct_D;
    }

    public static void setIsEnteredToPanel2_Omenoct_D(boolean isEnteredToPanel2_Omenoct_D) {
        GameData.isEnteredToPanel2_Omenoct_D = isEnteredToPanel2_Omenoct_D;
    }

    public static boolean isIsEnteredToPanel1_Omenoct_D() {
        return isEnteredToPanel1_Omenoct_D;
    }

    public static void setIsEnteredToPanel1_Omenoct_D(boolean isEnteredToPanel1_Omenoct_D) {
        GameData.isEnteredToPanel1_Omenoct_D = isEnteredToPanel1_Omenoct_D;
    }

    public static boolean isEnemy7_D() {
        return enemy7_D;
    }

    public static void setEnemy7_D(boolean enemy7_D) {
        GameData.enemy7_D = enemy7_D;
    }

    public static boolean isEnemy8_D() {
        return enemy8_D;
    }

    public static void setEnemy8_D(boolean enemy8_D) {
        GameData.enemy8_D = enemy8_D;
    }

    public static boolean isEnemy9_D() {
        return enemy9_D;
    }

    public static void setEnemy9_D(boolean enemy9_D) {
        GameData.enemy9_D = enemy9_D;
    }

    public static boolean isEnemy10_D() {
        return enemy10_D;
    }

    public static void setEnemy10_D(boolean enemy10_D) {
        GameData.enemy10_D = enemy10_D;
    }

    public static boolean isEnemy11_D() {
        return enemy11_D;
    }

    public static void setEnemy11_D(boolean enemy11_D) {
        GameData.enemy11_D = enemy11_D;
    }

    public static boolean isEnemy12_D() {
        return enemy12_D;
    }

    public static void setEnemy12_D(boolean enemy12_D) {
        GameData.enemy12_D = enemy12_D;
    }

    public static boolean isEnemy13_D() {
        return enemy13_D;
    }

    public static void setEnemy13_D(boolean enemy13_D) {
        GameData.enemy13_D = enemy13_D;
    }

    public static boolean isEnemy14_D() {
        return enemy14_D;
    }

    public static void setEnemy14_D(boolean enemy14_D) {
        GameData.enemy14_D = enemy14_D;
    }

    public static boolean isIsFinishedWave2_D() {
        return isFinishedWave2_D;
    }

    public static void setIsFinishedWave2_D(boolean isFinishedWave2_D) {
        GameData.isFinishedWave2_D = isFinishedWave2_D;
    }

    public static boolean isEnemy15_D() {
        return enemy15_D;
    }

    public static void setEnemy15_D(boolean enemy15_D) {
        GameData.enemy15_D = enemy15_D;
    }

    public static boolean isEnemy16_D() {
        return enemy16_D;
    }

    public static void setEnemy16_D(boolean enemy16_D) {
        GameData.enemy16_D = enemy16_D;
    }

    public static boolean isEnemy17_D() {
        return enemy17_D;
    }

    public static void setEnemy17_D(boolean enemy17_D) {
        GameData.enemy17_D = enemy17_D;
    }

    public static boolean isEnemy18_D() {
        return enemy18_D;
    }

    public static void setEnemy18_D(boolean enemy18_D) {
        GameData.enemy18_D = enemy18_D;
    }

    public static boolean isEnemy19_D() {
        return enemy19_D;
    }

    public static void setEnemy19_D(boolean enemy19_D) {
        GameData.enemy19_D = enemy19_D;
    }

    public static boolean isEnemy20_D() {
        return enemy20_D;
    }

    public static void setEnemy20_D(boolean enemy20_D) {
        GameData.enemy20_D = enemy20_D;
    }

    public static boolean isEnemy21_D() {
        return enemy21_D;
    }

    public static void setEnemy21_D(boolean enemy21_D) {
        GameData.enemy21_D = enemy21_D;
    }

    public static boolean isEnemy22_D() {
        return enemy22_D;
    }

    public static void setEnemy22_D(boolean enemy22_D) {
        GameData.enemy22_D = enemy22_D;
    }

    public static boolean isEnemy23_D() {
        return enemy23_D;
    }

    public static void setEnemy23_D(boolean enemy23_D) {
        GameData.enemy23_D = enemy23_D;
    }

    public static boolean isEnemy24_D() {
        return enemy24_D;
    }

    public static void setEnemy24_D(boolean enemy24_D) {
        GameData.enemy24_D = enemy24_D;
    }

    public static boolean isEnemy25_D() {
        return enemy25_D;
    }

    public static void setEnemy25_D(boolean enemy25_D) {
        GameData.enemy25_D = enemy25_D;
    }

    public static boolean isEnemy26_D() {
        return enemy26_D;
    }

    public static void setEnemy26_D(boolean enemy26_D) {
        GameData.enemy26_D = enemy26_D;
    }

    public static boolean isEnemy27_D() {
        return enemy27_D;
    }

    public static void setEnemy27_D(boolean enemy27_D) {
        GameData.enemy27_D = enemy27_D;
    }

    public static boolean isEnemy28_D() {
        return enemy28_D;
    }

    public static void setEnemy28_D(boolean enemy28_D) {
        GameData.enemy28_D = enemy28_D;
    }

    public static boolean isEnemy29_D() {
        return enemy29_D;
    }

    public static void setEnemy29_D(boolean enemy29_D) {
        GameData.enemy29_D = enemy29_D;
    }

    public static boolean isEnemy30_D() {
        return enemy30_D;
    }

    public static void setEnemy30_D(boolean enemy30_D) {
        GameData.enemy30_D = enemy30_D;
    }

    public static ArrayList<Boolean> getShowOfCollectibles_D() {
        return showOfCollectibles_D;
    }

    public static void setShowOfCollectibles_D(ArrayList<Boolean> showOfCollectibles_D) {
        GameData.showOfCollectibles_D = showOfCollectibles_D;
    }

    public static ArrayList<Boolean> getShowOfCollectiblesG_D() {
        return showOfCollectiblesG_D;
    }

    public static void setShowOfCollectiblesG_D(ArrayList<Boolean> showOfCollectiblesG_D) {
        GameData.showOfCollectiblesG_D = showOfCollectiblesG_D;
    }

    public static ArrayList<Boolean> getShowOfCollectiblesY_D() {
        return showOfCollectiblesY_D;
    }

    public static void setShowOfCollectiblesY_D(ArrayList<Boolean> showOfCollectiblesY_D) {
        GameData.showOfCollectiblesY_D = showOfCollectiblesY_D;
    }

    public static boolean isNecropick_isVisible_D() {
        return necropick_isVisible_D;
    }

    public static void setNecropick_isVisible_D(boolean necropick_isVisible_D) {
        GameData.necropick_isVisible_D = necropick_isVisible_D;
    }

    public static boolean isRunningOfNecropick_D() {
        return runningOfNecropick_D;
    }

    public static void setRunningOfNecropick_D(boolean runningOfNecropick_D) {
        GameData.runningOfNecropick_D = runningOfNecropick_D;
    }

    public static boolean isIsRightMoveArchmire_D() {
        return isRightMoveArchmire_D;
    }

    public static void setIsRightMoveArchmire_D(boolean isRightMoveArchmire_D) {
        GameData.isRightMoveArchmire_D = isRightMoveArchmire_D;
    }

    public static boolean isIsValidToShowPanel2_D() {
        return isValidToShowPanel2_D;
    }

    public static void setIsValidToShowPanel2_D(boolean isValidToShowPanel2_D) {
        GameData.isValidToShowPanel2_D = isValidToShowPanel2_D;
    }

    public static boolean isIsValidToShowPanel3_D() {
        return isValidToShowPanel3_D;
    }

    public static void setIsValidToShowPanel3_D(boolean isValidToShowPanel3_D) {
        GameData.isValidToShowPanel3_D = isValidToShowPanel3_D;
    }

    public static boolean isIsValidToShowPanel4_D() {
        return isValidToShowPanel4_D;
    }

    public static void setIsValidToShowPanel4_D(boolean isValidToShowPanel4_D) {
        GameData.isValidToShowPanel4_D = isValidToShowPanel4_D;
    }

    public static boolean isIsValidToShowRedZone_D() {
        return isValidToShowRedZone_D;
    }

    public static void setIsValidToShowRedZone_D(boolean isValidToShowRedZone_D) {
        GameData.isValidToShowRedZone_D = isValidToShowRedZone_D;
    }

    public static boolean isIsValidToShowArchmire_D() {
        return isValidToShowArchmire_D;
    }

    public static void setIsValidToShowArchmire_D(boolean isValidToShowArchmire_D) {
        GameData.isValidToShowArchmire_D = isValidToShowArchmire_D;
    }

    public static boolean isIsValidToShowNecropick_D() {
        return isValidToShowNecropick_D;
    }

    public static void setIsValidToShowNecropick_D(boolean isValidToShowNecropick_D) {
        GameData.isValidToShowNecropick_D = isValidToShowNecropick_D;
    }

    public static boolean isIsValidToShowOmenoct_D() {
        return isValidToShowOmenoct_D;
    }

    public static void setIsValidToShowOmenoct_D(boolean isValidToShowOmenoct_D) {
        GameData.isValidToShowOmenoct_D = isValidToShowOmenoct_D;
    }

    public static boolean isIsValidToShowOWyrm_D() {
        return isValidToShowOWyrm_D;
    }

    public static void setIsValidToShowOWyrm_D(boolean isValidToShowOWyrm_D) {
        GameData.isValidToShowOWyrm_D = isValidToShowOWyrm_D;
    }

    public static boolean isIsValidToShowOBarricados_D() {
        return isValidToShowOBarricados_D;
    }

    public static void setIsValidToShowOBarricados_D(boolean isValidToShowOBarricados_D) {
        GameData.isValidToShowOBarricados_D = isValidToShowOBarricados_D;
    }

    public static boolean isIsValidToCollectOmenoct_D() {
        return isValidToCollectOmenoct_D;
    }

    public static void setIsValidToCollectOmenoct_D(boolean isValidToCollectOmenoct_D) {
        GameData.isValidToCollectOmenoct_D = isValidToCollectOmenoct_D;
    }

    public static ArrayList<Boolean> getIsValidYEtoCollect_D() {
        return isValidYEtoCollect_D;
    }

    public static void setIsValidYEtoCollect_D(ArrayList<Boolean> isValidYEtoCollect_D) {
        GameData.isValidYEtoCollect_D = isValidYEtoCollect_D;
    }

    public static boolean isTimer1Starter_D() {
        return timer1Starter_D;
    }

    public static void setTimer1Starter_D(boolean timer1Starter_D) {
        GameData.timer1Starter_D = timer1Starter_D;
    }

    public static boolean isShowOfPointerItem_D() {
        return showOfPointerItem_D;
    }

    public static void setShowOfPointerItem_D(boolean showOfPointerItem_D) {
        GameData.showOfPointerItem_D = showOfPointerItem_D;
    }

    public TWM_Item_Model getTwm_item_model1_D() {
        return twm_item_model1_D;
    }

    public void setTwm_item_model1_D(TWM_Item_Model twm_item_model1_D) {
        this.twm_item_model1_D = twm_item_model1_D;
    }

    public static ArrayList<Panel> getPanels_D() {
        return panels_D;
    }

    public static void setPanels_D(ArrayList<Panel> panels_D) {
        GameData.panels_D = panels_D;
    }

    public Panel getRedZone_D() {
        return redZone_D;
    }

    public void setRedZone_D(Panel redZone_D) {
        this.redZone_D = redZone_D;
    }

    public Rectangle getRedZoneRectangle_D() {
        return redZoneRectangle_D;
    }

    public void setRedZoneRectangle_D(Rectangle redZoneRectangle_D) {
        this.redZoneRectangle_D = redZoneRectangle_D;
    }

    public static ArrayList<Collectable> getCollectablesOfOmenoct_D() {
        return collectablesOfOmenoct_D;
    }

    public static void setCollectablesOfOmenoct_D(ArrayList<Collectable> collectablesOfOmenoct_D) {
        GameData.collectablesOfOmenoct_D = collectablesOfOmenoct_D;
    }

    public static ArrayList<Collectable> getCollectablesOfYE_D() {
        return collectablesOfYE_D;
    }

    public static void setCollectablesOfYE_D(ArrayList<Collectable> collectablesOfYE_D) {
        GameData.collectablesOfYE_D = collectablesOfYE_D;
    }

    public static Integer getReductionRateOfHpOfEnemyD() {
        return REDUCTION_RATE_OF_HP_OF_ENEMY_D;
    }

    public static void setReductionRateOfHpOfEnemyD(Integer reductionRateOfHpOfEnemyD) {
        REDUCTION_RATE_OF_HP_OF_ENEMY_D = reductionRateOfHpOfEnemyD;
    }

    public LinkedList<Position> getPositionsOfCollectiblesY_D() {
        return positionsOfCollectiblesY_D;
    }

    public void setPositionsOfCollectiblesY_D(LinkedList<Position> positionsOfCollectiblesY_D) {
        this.positionsOfCollectiblesY_D = positionsOfCollectiblesY_D;
    }

    public LinkedList<Position> getPositionsOfCollectibles_D() {
        return positionsOfCollectibles_D;
    }

    public void setPositionsOfCollectibles_D(LinkedList<Position> positionsOfCollectibles_D) {
        this.positionsOfCollectibles_D = positionsOfCollectibles_D;
    }

    public LinkedList<Position> getPositionsOfCollectiblesG_D() {
        return positionsOfCollectiblesG_D;
    }

    public void setPositionsOfCollectiblesG_D(LinkedList<Position> positionsOfCollectiblesG_D) {
        this.positionsOfCollectiblesG_D = positionsOfCollectiblesG_D;
    }

    public ArrayList<Boolean> getShowOfCollectiblesHelperG_D() {
        return showOfCollectiblesHelperG_D;
    }

    public void setShowOfCollectiblesHelperG_D(ArrayList<Boolean> showOfCollectiblesHelperG_D) {
        this.showOfCollectiblesHelperG_D = showOfCollectiblesHelperG_D;
    }

    public ArrayList<Boolean> getShowOfCollectiblesHelperY_D() {
        return showOfCollectiblesHelperY_D;
    }

    public void setShowOfCollectiblesHelperY_D(ArrayList<Boolean> showOfCollectiblesHelperY_D) {
        this.showOfCollectiblesHelperY_D = showOfCollectiblesHelperY_D;
    }

    public static Omenoct getOmenoct_D() {
        return omenoct_D;
    }

    public static void setOmenoct_D(Omenoct omenoct_D) {
        GameData.omenoct_D = omenoct_D;
    }

    public static Necropick getNecropick_D() {
        return necropick_D;
    }

    public static void setNecropick_D(Necropick necropick_D) {
        GameData.necropick_D = necropick_D;
    }

    public static Archmire getArchmire_D() {
        return archmire_D;
    }

    public static void setArchmire_D(Archmire archmire_D) {
        GameData.archmire_D = archmire_D;
    }

    public static ArrayList<Point> getTrails_D() {
        return trails_D;
    }

    public static void setTrails_D(ArrayList<Point> trails_D) {
        GameData.trails_D = trails_D;
    }
}

 */