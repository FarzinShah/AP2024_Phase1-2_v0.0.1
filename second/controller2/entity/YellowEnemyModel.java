package second.controller2.entity;

import second.controller2.controlComponent.Controller2;
import second.movement.SizeOfYellowEnemy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class YellowEnemyModel extends MovingEnemy {
    private final SizeOfYellowEnemy sizeOfYE; // موقت ->

    private double YE_posX1;
    private double YE_posY1;
    private double YE_posX2;
    private double YE_posY2;
    private double YE_posX3;
    private double YE_posY3;
    private double angleOfRotation;
    private double YE_dirX;
    private double YE_dirY;
    private double YE_rateX;
    private double YE_rateY;
    private final double radius;
    private int lifeValue;
    private final double posX;
    private final double posY;
    private final double posCenterX;
    private final double posCenterY;
    private final Controller2 controller;



    public YellowEnemyModel(Controller2 controller2, double radius, double posX,double posY) {
        super(controller2,posX,posY);
        this.posX = posX;
        this.posY = posY;
        this.posCenterX = radius;
        this.posCenterY = radius;
        sizeOfYE = new SizeOfYellowEnemy(2*radius,2*radius);
        this.radius = radius;
        this.controller = controller2;

        this.YE_posX1 = posCenterX;
        this.YE_posX2 = posCenterX + Math.floor(0.8555 * (radius));
        this.YE_posX3 = posCenterX - Math.floor(0.8555 * (radius));
        this.YE_posY1 = posCenterY + (radius);
        this.YE_posY2 = (posCenterY - (radius / 2));
        this.YE_posY3 = (posCenterY - (radius / 2));



    }

    @Override
    public void update() {
        super.update();

    }

    public Image getSprite() {

        BufferedImage image = new BufferedImage((int) sizeOfYE.getWidth(), (int) sizeOfYE.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
//        image.getGraphics().drawImage(null, 0, 0, null);

        g.setColor(new Color(0xCDD98F07, true));
//        for (GameObject gameObject : gameObjects) {
//            if (gameObject instanceof YellowEnemyModel) {
//                System.out.println("??");
//                g.drawPolygon(new int[]{(int) gameObject.getPosition().getX(), (int) (gameObject.getPosition().getX() - (0.8555 * ((YellowEnemyModel) gameObject).radius)), (int) (gameObject.getPosition().getX() + (0.8555 * ((YellowEnemyModel) gameObject).radius))},
//                        new int[]{(int) (gameObject.getPosition().getY() + ((YellowEnemyModel) gameObject).radius), (int) (gameObject.getPosition().getY() - (((YellowEnemyModel) gameObject).radius / 2)), (int) (gameObject.getPosition().getY() + (((YellowEnemyModel) gameObject).radius / 2))}, 3);
//
//            }
//
//        }
//        for (int i = 0; i < 2; i++) {
//               Polygon triangle = new Polygon(new int[]{(int) yellowEnemies.get(i).getPosition().getX(), (int) (yellowEnemies.get(i).getPosition().getX() - (0.8555 * ((YellowEnemyModel) yellowEnemies.get(i)).radius)), (int) (yellowEnemies.get(i).getPosition().getX() + (0.8555 * ((YellowEnemyModel) yellowEnemies.get(i)).radius))},
//                        new int[]{(int) (yellowEnemies.get(i).getPosition().getY() + ((YellowEnemyModel) yellowEnemies.get(i)).radius), (int) (yellowEnemies.get(i).getPosition().getY() - (((YellowEnemyModel) yellowEnemies.get(i)).radius / 2)), (int) (yellowEnemies.get(i).getPosition().getY() + (((YellowEnemyModel) yellowEnemies.get(i)).radius / 2))}, 3);
//                g.fillPolygon(triangle);
//        }
//        System.out.println(yellowEnemies.get(0).getPosition().getX() + " " + yellowEnemies.get(0).getPosition().getY() + " " + yellowEnemies.get(0).radius);
            Polygon triangle = new Polygon(new int[]{(int) YE_posX1, (int) YE_posX2, (int) YE_posX3},
                     new int[]{(int) (YE_posY1), (int) (YE_posY2), (int) (YE_posY3)}, 3);
               g.drawPolygon(triangle);
//        g.fillOval(0, 0, (int) (2*radius), (int) (2*radius)); //  آرگومانای i و i1 نسبت به sizeX و sizeY ثابت هستند.
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("Arial",Font.BOLD,20));
//        g.drawString("ε",(int) sizeOfYE.getWidth()-30,(int) sizeOfYE.getHeight()-21);
        g.dispose();
        return image;
    }

    public double getRadius() {
        return radius;
    }



    public double getYE_posX1() {
        return YE_posX1;
    }

    public void setYE_posX1(double YE_posX1) {
        this.YE_posX1 = YE_posX1;
    }

    public double getYE_posY1() {
        return YE_posY1;
    }

    public void setYE_posY1(double YE_posY1) {
        this.YE_posY1 = YE_posY1;
    }

    public double getYE_posX2() {
        return YE_posX2;
    }

    public void setYE_posX2(double YE_posX2) {
        this.YE_posX2 = YE_posX2;
    }

    public double getYE_posY2() {
        return YE_posY2;
    }

    public void setYE_posY2(double YE_posY2) {
        this.YE_posY2 = YE_posY2;
    }

    public double getYE_posX3() {
        return YE_posX3;
    }

    public void setYE_posX3(double YE_posX3) {
        this.YE_posX3 = YE_posX3;
    }

    public double getYE_posY3() {
        return YE_posY3;
    }

    public void setYE_posY3(double YE_posY3) {
        this.YE_posY3 = YE_posY3;
    }
}
