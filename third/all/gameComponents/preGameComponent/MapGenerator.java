package third.all.gameComponents.preGameComponent;

import java.awt.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class MapGenerator{
    public int[][] map;
    public static int brickWidth;

    public static LinkedList<YellowEnemy> yellowEnemies = new LinkedList<>();

    public static LinkedList<Polygon> yellowEnemies_triangles = new LinkedList<>();


    public static int fallingdY = 50;

    public MapGenerator(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTask(), 1000, 1000);


        for (int i = 0; i < 2; i++) {
            regularTriangleYellowEnemy(20,ThreadLocalRandom.current().nextDouble(100,450),ThreadLocalRandom.current().nextDouble(100,600),ThreadLocalRandom.current().nextDouble(-1,1),ThreadLocalRandom.current().nextDouble(-1,1));
        }

        for (int i = 0; i < yellowEnemies.size(); i++) {
            yellowEnemies_triangles.add(new Polygon(new int[] {(int) yellowEnemies.get(i).getYE_posX1(),(int) yellowEnemies.get(i).getYE_posX2(),(int) yellowEnemies.get(i).getYE_posX3()},new int[] {(int) yellowEnemies.get(i).getYE_posY1(),(int) yellowEnemies.get(i).getYE_posY2(),(int) yellowEnemies.get(i).getYE_posY3()},3));
        }

    }

    public void regularTriangleYellowEnemy(double radius, double centerX, double centerY,double dirX,double dirY){
        yellowEnemies.add(new YellowEnemy(centerX,centerY+radius,centerX-(0.855*radius),centerY-(radius/2),centerX+(0.855*radius),centerY-(radius/2),dirX,dirY,radius));
    }

    public void draw(Graphics2D g){

//        for (int i = 0; i < yellowEnemies.size(); i++) {
//            g.setColor(Color.yellow);
//            Polygon triangle = new Polygon(new int[] {(int) yellowEnemies.get(i).getYE_posX1(),(int) yellowEnemies.get(i).getYE_posX2(),(int) yellowEnemies.get(i).getYE_posX3()},new int[] {(int) yellowEnemies.get(i).getYE_posY1(),(int) yellowEnemies.get(i).getYE_posY2(),(int) yellowEnemies.get(i).getYE_posY3()},3);
//            g.drawPolygon(triangle);
//
//        }

    }

    static class MyTask extends TimerTask {
        public void run() {
            fallingdY+=1;
        }
    }
}
