package third.all.data.booleans;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Booleans {

//    //todo: Wave 1:
//    public static boolean enemy1 = true;
//    public static boolean enemy2 = true;
//    public static boolean enemy3 = true;
//    public static boolean enemy4 = true;
//    public static boolean enemy5 = true;
//    public static boolean enemy6 = true;

    public static boolean lineShower = false;

    public static boolean isFinishedWave1 = false;

    public static boolean isRightPanel2 = false;
    public static boolean isEnteredToPanel2_Omenoct = false;
    public static boolean isEnteredToPanel1_Omenoct = true;

    //todo: Wave 2:
//    public static boolean enemy7 = true;
//    public static boolean enemy8 = true;
//    public static boolean enemy9 = true;
//    public static boolean enemy10 = true;
//    public static boolean enemy11 = true;
//    public static boolean enemy12 = true;
//    public static boolean enemy13 = true;
//    public static boolean enemy14 = true;
    public static boolean isFinishedWave2 = false;
    public static boolean isFinishedWave3 = false;
    public static boolean isFinishedWave4 = false;
    public static boolean isFinishedWave5 = false;

    //todo: Wave 3:
//    public static boolean enemy15 = true;
//    public static boolean enemy16 = true;
//    public static boolean enemy17 = true;
//    public static boolean enemy18 = true;
//    public static boolean enemy19 = true;
//    public static boolean enemy20 = true;
//    public static boolean enemy21 = true;
//    public static boolean enemy22 = true;
    //todo: Wave 4:

//    public static boolean enemy23 = true;
//    public static boolean enemy24 = true;
//    public static boolean enemy25 = true;
//    public static boolean enemy26 = true;
//    public static boolean enemy27 = true;
    //todo: Wave 5:

//    public static boolean enemy28 = true;
//    public static boolean enemy29 = true;
//    public static boolean enemy30 = true;
    //todo
    public static ArrayList<Boolean> showOfCollectiblesG;
    public static boolean necropick_isVisible =true;
    public static boolean runningOfNecropick = true;
    public static boolean isRightMoveArchmire = true;

//    //todo: بولین هایی که برای موقع اعمالشون تو موج ها موقعی که وقتش بشه باید ترو بشن.
//    public static boolean isValidToShowPanel2 = false;
//    public static boolean isValidToShowPanel3 = false;
//    public static boolean isValidToShowPanel4 = false;
//    public static boolean isValidToShowRedZone = false;
//
//
//    public static boolean isValidToShowArchmire = false;
//    public static boolean isValidToShowNecropick = false;
//    public static boolean isValidToShowOmenoct = false;
//    public static boolean isValidToShowOWyrm = false;
//    public static boolean isValidToShowOBarricados = false;
//
//    public static boolean isValidToCollectOmenoct = false;





    //todo: دیتاهای gameFrames2

    public static ArrayList<Boolean> showOfCollectiblesHelperG;
    public static ArrayList<Boolean> showOfCollectiblesHelperY;

    public static boolean showOfPointerItemHelper = true;
    public static boolean activateMechanismOfPointerItem = false;

    public static LinkedList<Boolean> isCollidedY = new LinkedList<>();
    public static LinkedList<Boolean> isCollidedG = new LinkedList<>();
    public static LinkedList<Boolean> isCollided = new LinkedList<>();
    public static LinkedList<Boolean> isCollidedEnemies = new LinkedList<>();




    public static boolean shapeIntersects(Shape s1, Shape s2) {
        Area area1 = new Area(s1);
        Area area2 = new Area(s2);
        area1.intersect(area2);
        return !area1.isEmpty();
    }


}
