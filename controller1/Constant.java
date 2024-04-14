package controller1;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Constant {
    public static final Dimension GLASS_FRAME_DIMENSION = new Dimension(1000,800);
    public static final int NUMBER_OF_EPSILONS = 1;
    public static final Dimension MAX_PANEL_SIZE = new Dimension((int) (GLASS_FRAME_DIMENSION.getWidth()/2), (int) (GLASS_FRAME_DIMENSION.getHeight()/2));
    public static final Dimension MIN_PANEL_SIZE = new Dimension((int) (GLASS_FRAME_DIMENSION.getWidth()/5), (int) (GLASS_FRAME_DIMENSION.getHeight()/5));
    public static final int FPS = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getRefreshRate();;
    public static final double FRAME_UPDATE_TIME=(double) TimeUnit.SECONDS.toMillis(1)/(5*FPS);
    public static final int UPS = 100;
    public static final double MODEL_UPDATE_TIME=(double) TimeUnit.SECONDS.toMillis(1)/(5*UPS);
    public static final double SPEED = 60D/UPS;



}
