package second.controller2.gameComponent.utils;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Constant {
    public static final Dimension GLASS_FRAME_DIMENSION = new Dimension(1000,800);
    public static final int NUMBER_OF_EPSILONS = 1;
    public static final Dimension MAX_PANEL_SIZE = new Dimension((int) (GLASS_FRAME_DIMENSION.getWidth()/2), (int) (GLASS_FRAME_DIMENSION.getHeight()/2)); // 500 * 400
    public static final Dimension MIN_PANEL_SIZE = new Dimension((int) (GLASS_FRAME_DIMENSION.getWidth()/4), (int) (GLASS_FRAME_DIMENSION.getHeight()/4)); // 250 * 200
    public static Dimension PANEL_SIZE = MAX_PANEL_SIZE;
    public static Thread CENTRAL_THREAD = new Thread();

    public static final int FPS = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getRefreshRate();;
    public static final double FRAME_UPDATE_TIME=(double) TimeUnit.SECONDS.toMillis(1)/(5*FPS);
    public static final int UPS = 100;
    public static final double MODEL_UPDATE_TIME=(double) TimeUnit.SECONDS.toMillis(1)/(5*UPS);
    public static final double SPEED = 60D/UPS;



}
