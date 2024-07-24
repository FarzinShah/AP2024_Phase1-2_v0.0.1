package third.all.data;

import third.all.controller.movement.Position;
import third.all.model.Bullet;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static third.all.controller.Constants.*;
import static third.all.gameComponents.preGameComponent.Timer1.spentMilliSecond;

public class Properties {
    public static Properties instance;
    public ArrayList<Number> EPSILON_PROPERTIES;
    public ArrayList<Point> LOCATION_OF_PANELS;
    public Dimension GLASS_FRAME;
    public boolean play = false; // for starting
    public Integer HP = 1500;
    public Double XP = 0.0;
    public Integer WAVE = 6;
    public int STATE = 21;
    public int PR = WAVE * (spentMilliSecond / 1000);
    public double PROGRESS_RISK = Math.floor((double) 10 * XP * PR / HP);
    public double GLASS_FRAME_DIMENSION_WIDTH = 600.0;
    public double GLASS_FRAME_DIMENSION_HEIGHT = 600.0;
    public double SECOND_FRAME_LOCATION_X = 700, SECOND_FRAME_LOCATION_Y = 0;
    public double THIRD_FRAME_LOCATION_X = 600, THIRD_FRAME_LOCATION_Y = 150;
    public double FOURTH_FRAME_LOCATION_X = 600, FOURTH_FRAME_LOCATION_Y = 300;
    public int FIFTH_FRAME_LOCATION_X = 1400, FIFTH_FRAME_LOCATION_Y = 510;
    public double probabilityOfGettingAttackedEpsilon = 1;
    public int[] boHelper = {5000, 0};
    public boolean isValidToChiron;
    public boolean isValidToAthena;
    public double constantOfShrinkagePanel;
    public double constantOfShrinkageEpsilon;
    public int headOfBossHP;
    public int handsOfBossHP;
    public Point locationOfBossPanel;
    public Dimension sizeOfBossPanel;

    public Point locationOfLeftHand;
    public int sizeOfLeftHand;
    public Point locationOfRightHand;
    public int sizeOfRightHand;
    public Point locationOfHead;
    public int sizeOfHead;
    public double speedOfOrbitProjectile;
    public double angleOfOrbitProjectile;
    public double radiusOfOrbitProjectile;


    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Bullet> bulletsOfOmenoct = new ArrayList<>();
    public static ArrayList<Bullet> bulletsOfWyrm = new ArrayList<>();
    public static ArrayList<Bullet> bulletsOfNecropick = new ArrayList<>();
    public static ArrayList<Bullet> bulletsOfRightHand = new ArrayList<>();
    public static ArrayList<Bullet> bulletsOfLeftHand = new ArrayList<>();
    public ArrayList<Integer> startedTimeOfCollectiblesG;
    public ArrayList<Integer> startedTimeOfCollectiblesY;

    public LinkedList<Position> positionsOfCollectiblesG = new LinkedList<>();


    public boolean isValidAthena; //todo: shop
    public boolean isValidHephaestus; //todo: shop

    public boolean isValidDeimos; //todo: shop
    public boolean isValidSlumber; //todo: shop
    public boolean isValidSlaughter; //todo: shop

    public Properties() {
        EPSILON_PROPERTIES = new ArrayList<>();
        LOCATION_OF_PANELS = new ArrayList<>();
        isValidToChiron = false;
        isValidToAthena = false;
        isValidDeimos = false;
        isValidSlumber = false;
        isValidSlaughter = false;
        constantOfShrinkagePanel = 1.0;
        constantOfShrinkageEpsilon = 1.0;
        headOfBossHP = 300;
        handsOfBossHP = 100;
        locationOfBossPanel = new Point(200, 50);
        sizeOfBossPanel = new Dimension(1125, 700);
        locationOfLeftHand = new Point(400, 200);
        locationOfRightHand = new Point(900, 173);
        locationOfHead = new Point(650, 150);
        speedOfOrbitProjectile = 0.5;
        angleOfOrbitProjectile = 0;
        radiusOfOrbitProjectile = 200;


        GLASS_FRAME = GLASS_FRAME_DIMENSION;
        EPSILON_PROPERTIES.add(0, XP);
        EPSILON_PROPERTIES.add(1, HP);
        EPSILON_PROPERTIES.add(2, PR);
        EPSILON_PROPERTIES.add(3, WAVE);
        EPSILON_PROPERTIES.add(4, STATE);
        EPSILON_PROPERTIES.add(5, PROGRESS_RISK);
        LOCATION_OF_PANELS.add(0, STARTING_POINT);
        LOCATION_OF_PANELS.add(1, new Point((int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT));
        LOCATION_OF_PANELS.add(2, new Point((int) SECOND_FRAME_LOCATION_X, (int) SECOND_FRAME_LOCATION_Y));
        LOCATION_OF_PANELS.add(3, new Point((int) THIRD_FRAME_LOCATION_X, (int) THIRD_FRAME_LOCATION_Y));
        LOCATION_OF_PANELS.add(4, new Point((int) FOURTH_FRAME_LOCATION_X, (int) FOURTH_FRAME_LOCATION_Y));
        LOCATION_OF_PANELS.add(5, new Point((int) FIFTH_FRAME_LOCATION_X, (int) FIFTH_FRAME_LOCATION_Y));


    }

    public static Properties getInstance() {
        if (instance == null) {
            instance = new Properties();
            return instance;
        }
        return instance;
    }

    public static void setInstance(Properties instance1) {
        instance = instance1;
    }

}
