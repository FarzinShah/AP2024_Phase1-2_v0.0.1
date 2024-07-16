package third.all.data;

import third.all.controller.entity.GameObject;
import third.all.gameComponents.game.Panel;
import third.all.gameComponents.preGameComponent.Timer1;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameState {
    public BooleansOfCollectibles booleansOfCollectibles;
    public BooleansOf_IsValidToShow booleansOfIsValidToShow;
    public BooleansOfEnemies booleansOfEnemies;
    public ArrayList<GameObject> gameObjects;
    public ArrayList<GameObject> yellowEnemies1;
    public ArrayList<GameObject> greenEnemies1;
    public PanelsData panelsData;
    public int elapsedTime; // 120000
    public int seconds;
    public int minutes;
    public int hours;
    public int spentMilliSecond;
    public int spentMilliSecondW2;
    public int spentMilliSecondW3;
    public int spentMilliSecondW4;
    public int spentMilliSecondW5;
    public List<EnemyState> enemyStateList;
    public List<EnemyDirState> enemyDirStateList;
    public NormalEnemyData omenoctEnemyData;
    public NormalEnemyData necropickEnemyData;
    public Point epsilonLocation;
    public int[] addBlackOrbs;
    public Properties properties;


    private GameState(Builder builder) {
        this.booleansOfCollectibles = builder.booleansOfCollectibles;
        this.booleansOfIsValidToShow = builder.booleansOf_IsValidToShow;
        this.booleansOfEnemies = builder.booleansOfEnemies;
        this.gameObjects = builder.gameObjects;
        this.yellowEnemies1 = builder.yellowEnemies1;
        this.greenEnemies1 = builder.greenEnemies1;
        this.panelsData = builder.panelsData;
        this.elapsedTime = builder.elapsedTime;
        this.seconds = builder.seconds;
        this.minutes = builder.minutes;
        this.hours = builder.hours;
        this.spentMilliSecond = builder.spentMilliSecond;
        this.spentMilliSecondW2 = builder.spentMilliSecondW2;
        this.enemyStateList = builder.enemyStateList;
        this.enemyDirStateList = builder.enemyDirStateList;
        this.omenoctEnemyData = builder.omenoctEnemyData;
        this.necropickEnemyData = builder.necropickEnemyData;
        this.epsilonLocation = builder.epsilonLocation;
        this.addBlackOrbs = builder.addBlackOrbs;
        this.properties = builder.properties;


    }

    public static class Builder {
        private BooleansOfCollectibles booleansOfCollectibles;
        private BooleansOf_IsValidToShow booleansOf_IsValidToShow;
        private BooleansOfEnemies booleansOfEnemies;
        private ArrayList<GameObject> gameObjects;
        private ArrayList<GameObject> yellowEnemies1;
        private ArrayList<GameObject> greenEnemies1;
        private PanelsData panelsData;
        private int elapsedTime; // 120000
        private int seconds;
        private int minutes;
        private int hours;
        private int spentMilliSecond;
        private int spentMilliSecondW2;
        private int spentMilliSecondW3;
        private int spentMilliSecondW4;
        private int spentMilliSecondW5;

        private List<EnemyState> enemyStateList;
        private List<EnemyDirState> enemyDirStateList;
        private NormalEnemyData omenoctEnemyData;
        private NormalEnemyData necropickEnemyData;
        private Point epsilonLocation;
        private int[] addBlackOrbs;
        private Properties properties;

        public Builder setBooleansOfCollectibles(BooleansOfCollectibles booleansOfCollectibles) {
            this.booleansOfCollectibles = booleansOfCollectibles;
            return this;
        }

        public Builder setBooleansOfIsValidToShow(BooleansOf_IsValidToShow booleansOfIsValidToShow) {
            this.booleansOf_IsValidToShow = booleansOfIsValidToShow;
            return this;

        }

        public Builder setBooleansOfEnemies(BooleansOfEnemies booleansOfEnemies) {
            this.booleansOfEnemies = booleansOfEnemies;
            return this;

        }


        public Builder setGameObjects(ArrayList<GameObject> gameObjects) {
            this.gameObjects = gameObjects;
            return this;

        }


        public Builder setYellowEnemies1(ArrayList<GameObject> yellowEnemies1) {
            this.yellowEnemies1 = yellowEnemies1;
            return this;

        }


        public Builder setGreenEnemies1(ArrayList<GameObject> greenEnemies1) {
            this.greenEnemies1 = greenEnemies1;
            return this;

        }


        public Builder setPanelsData(PanelsData panelsData) {
            this.panelsData = panelsData;
            return this;

        }


        public Builder setElapsedTime(int elapsedTime) {
            this.elapsedTime = elapsedTime;
            return this;
        }

        public Builder setSeconds(int seconds) {
            this.seconds = seconds;
            return this;
        }

        public Builder setMinutes(int minutes) {
            this.minutes = minutes;
            return this;
        }

        public Builder setHours(int hours) {
            this.hours = hours;
            return this;
        }

        public Builder setSpentMilliSecond(int spentMilliSecond) {
            this.spentMilliSecond = spentMilliSecond;
            return this;
        }

        public Builder setSpentMilliSecondW2(int spentMilliSecondW2) {
            this.spentMilliSecondW2 = spentMilliSecondW2;
            return this;
        }

        public int getSpentMilliSecondW3() {
            return spentMilliSecondW3;
        }

        public Builder setSpentMilliSecondW3(int spentMilliSecondW3) {
            this.spentMilliSecondW3 = spentMilliSecondW3;
            return this;
        }

        public int getSpentMilliSecondW4() {
            return spentMilliSecondW4;
        }

        public Builder setSpentMilliSecondW4(int spentMilliSecondW4) {
            this.spentMilliSecondW4 = spentMilliSecondW4;
            return this;
        }

        public int getSpentMilliSecondW5() {
            return spentMilliSecondW5;
        }

        public Builder setSpentMilliSecondW5(int spentMilliSecondW5) {
            this.spentMilliSecondW5 = spentMilliSecondW5;
            return this;
        }

        public List<EnemyState> getEnemyStateList() {
            return enemyStateList;
        }

        public Builder setEnemyStateList(List<EnemyState> enemyStateList) {
            this.enemyStateList = enemyStateList;
            return this;
        }

        public List<EnemyDirState> getEnemyDirStateList() {
            return enemyDirStateList;
        }

        public Builder setEnemyDirStateList(List<EnemyDirState> enemyDirStateList) {
            this.enemyDirStateList = enemyDirStateList;
            return this;
        }

        public Builder setOmenoctEnemyData(NormalEnemyData omenoctEnemyData) {
            this.omenoctEnemyData = omenoctEnemyData;
            return this;
        }

        public Builder setNecropickEnemyData(NormalEnemyData necropickEnemyData) {
            this.necropickEnemyData = necropickEnemyData;
            return this;
        }

        public Builder setEpsilonLocation(Point epsilonLocation) {
            this.epsilonLocation = epsilonLocation;
            return this;
        }

        public Builder setAddBlackOrbs(int[] addBlackOrbs) {
            this.addBlackOrbs = addBlackOrbs;
            return this;
        }

        public Builder setProperties(Properties properties) {
            this.properties = properties;
            return this;
        }

        public GameState build() {
            return new GameState(this);
        }
    }

//    public Color getHeartColor() {
//        return new Color(heartColorRed, heartColorGreen, heartColorBlue);
//    }
}
