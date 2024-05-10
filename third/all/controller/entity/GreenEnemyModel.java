package third.all.controller.entity;
import third.all.controller.componentController.Controller3;
import third.all.controller.movement.SizeOfGreenEnemy;


import java.awt.*;
import java.awt.image.BufferedImage;

public class GreenEnemyModel extends MovingEnemy {
    private final SizeOfGreenEnemy sizeOfGE; // موقت ->

    private double GE_posX1;
    private double GE_posY1;
    private double GE_posX2;
    private double GE_posY2;
    private double GE_posX3;
    private double GE_posY3;
    private double angleOfRotation;
    private double GE_dirX;
    private double GE_dirY;
    private double GE_rateX;
    private double GE_rateY;
    private final double radius;
    private final double posX;
    private final double posY;
    private final double posCenterX;
    private final double posCenterY;
    private final Controller3 controller3;

    private Integer lifeValue = 10;
    private boolean isAlive = true;



    public GreenEnemyModel(Controller3 controller3, double radius, double posX, double posY) {
        super(controller3,posX,posY);
        this.posX = posX;
        this.posY = posY;
        this.posCenterX = radius;
        this.posCenterY = radius;
        sizeOfGE = new SizeOfGreenEnemy(2*radius,2*radius);
        this.radius = radius;
        this.controller3 = controller3;
        this.GE_posX1 = posCenterX;
        this.GE_posY1 = posCenterY + (radius);
    }

    @Override
    public void update() {
        super.update();

    }

    public Image getSprite() {
        BufferedImage image = new BufferedImage((int) sizeOfGE.getWidth(), (int) sizeOfGE.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(new Color(0xCD00FC04, true));
        Rectangle rectangle = new Rectangle(0,0,25,25);
        g.draw(rectangle);
        g.dispose();
        return image;
    }

    public double getRadius() {
        return radius;
    }

    public Integer getLifeValue() {
        return lifeValue;
    }

    public void setLifeValue(Integer lifeValue) {
        this.lifeValue = lifeValue;
    }

    public double getGE_posX1() {
        return GE_posX1;
    }

    public void setGE_posX1(double GE_posX1) {
        this.GE_posX1 = GE_posX1;
    }

    public double getGE_posY1() {
        return GE_posY1;
    }

    public void setGE_posY1(double GE_posY1) {
        this.GE_posY1 = GE_posY1;
    }

    public double getGE_posX2() {
        return GE_posX2;
    }

    public void setGE_posX2(double GE_posX2) {
        this.GE_posX2 = GE_posX2;
    }

    public double getGE_posY2() {
        return GE_posY2;
    }

    public void setGE_posY2(double GE_posY2) {
        this.GE_posY2 = GE_posY2;
    }

    public double getGE_posX3() {
        return GE_posX3;
    }

    public void setGE_posX3(double GE_posX3) {
        this.GE_posX3 = GE_posX3;
    }

    public double getGE_posY3() {
        return GE_posY3;
    }

    public void setGE_posY3(double GE_posY3) {
        this.GE_posY3 = GE_posY3;
    }
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
