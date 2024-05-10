package third.all.controller;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Constants {
    public static final double GLASS_FRAME_DIMENSION_MAX_WIDTH = 600.0;
    public static final double GLASS_FRAME_DIMENSION_MAX_HEIGHT = 600.0;
    public static double boundX = 450.0;
    public static double boundY = 100.0;
    public static Integer XXED = 100; /// HP
    public static Integer XP = 0;
    public static Integer EPSILON_WIDTH = 50;
    public static Integer EPSILON_LENGTH = 50;
    public static Integer ACCELERATION_OF_GREEN_ENEMIES = 2;
    public static Integer ACCELERATION_OF_YELLOW_ENEMIES = 2;
    public static boolean waveOHB = false;
    public static boolean IS_VALID_STORE = false;
    public static boolean is_Writ_Of_Proteus = false;
    public static boolean is_Writ_Of_Aceso = false;
    public static boolean is_Writ_Of_Ares = false;
    public static Integer REDUCTION_RATE_OF_HP_OF_ENEMY = 1;
    public static Integer WAVE = 1;
    public static int STATE = 0;




    public static final double GLASS_FRAME_DIMENSION_MIN_WIDTH = 500.0;
    public static final double GLASS_FRAME_DIMENSION_MIN_HEIGHT = 500.0;
    public static double GLASS_FRAME_DIMENSION_WIDTH = 600.0;
    public static double GLASS_FRAME_DIMENSION_HEIGHT = 600.0;

    public static final double SIZE_OF_TWM_ITEM = 10;
    public static final double SIZE_OF_SHOT_BULLET = 10;

    public static final Dimension GLASS_FRAME_DIMENSION = new Dimension((int) GLASS_FRAME_DIMENSION_WIDTH,(int) GLASS_FRAME_DIMENSION_HEIGHT);
    public static final Dimension PANEL_SIZE = new Dimension((int) (GLASS_FRAME_DIMENSION.getWidth()/2), (int) (GLASS_FRAME_DIMENSION.getHeight()/2));
    public static final int FPS = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getRefreshRate();;
    public static final double FRAME_UPDATE_TIME=(double) TimeUnit.SECONDS.toMillis(1)/(5*FPS);
    public static final int UPS = 100;
    public static final double MODEL_UPDATE_TIME=(double) TimeUnit.SECONDS.toMillis(1)/(5*UPS);
    public static final double SPEED = 60D/UPS;
    public static final double MAX_RADIUS = 50;
    public static final int NUMBER_OF_YELLOW_ENEMIES =2;//first value: 100

}
