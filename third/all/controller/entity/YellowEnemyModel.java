package third.all.controller.entity;

import third.all.controller.componentController.Controller3;
import third.all.controller.movement.SizeOfYellowEnemy;


import java.awt.*;
import java.awt.image.BufferedImage;

public class YellowEnemyModel extends MovingEnemy {
    private final SizeOfYellowEnemy sizeOfYE; // موقت ->

    private final double YE_posX1;
    private final double YE_posY1;
    private final double YE_posX2;
    private final double YE_posY2;
    private final double YE_posX3;
    private final double YE_posY3;
    private double angleOfRotation;
    private double YE_dirX;
    private double YE_dirY;
    private double YE_rateX;
    private double YE_rateY;
    private final double radius;
    private final double posX;
    private final double posY;
    private final double posCenterX;
    private final double posCenterY;
    private Controller3 controller3;
    private Integer lifeValue = 15;
    private boolean isAlive = true;
    private final Color normalColor;
    private final Color semiNormalColor;
    private final Color unNormalColor;
    private Color color;
    private boolean isValidToShow;


    public YellowEnemyModel(Controller3 controller3, double radius, double posX, double posY) {
        super(controller3, posX, posY);
        this.posX = posX;
        this.posY = posY;
        this.posCenterX = radius;
        this.posCenterY = radius;
        sizeOfYE = new SizeOfYellowEnemy(2 * radius, 2 * radius);
        this.radius = radius;
        this.controller3 = controller3;
        normalColor = new Color(0xBAD98F07, true);
        semiNormalColor = new Color(0x6CD98F07, true);
        unNormalColor = new Color(0, 0, 0,0);
        color = normalColor;
        this.YE_posX1 = posCenterX;
        this.YE_posX2 = posCenterX + Math.floor(0.8555 * (radius));
        this.YE_posX3 = posCenterX - Math.floor(0.8555 * (radius));
        this.YE_posY1 = posCenterY + (radius);
        this.YE_posY2 = (posCenterY - (radius / 2));
        this.YE_posY3 = (posCenterY - (radius / 2));
        isValidToShow = false;


    }

    @Override
    public void update() {
        super.update();

    }

    public Image getSprite() {

        BufferedImage image = new BufferedImage((int) sizeOfYE.getWidth(), (int) sizeOfYE.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        if (isValidToShow) {
            if (lifeValue >= 8) {
                g.setColor(normalColor);
            }
            if (lifeValue < 8) {
                g.setColor(semiNormalColor);
            }
        }else  g.setColor(unNormalColor);

//        if(length==25) {
        if (lifeValue > 0) {
            g.setFont(new Font("calibri", Font.BOLD, 20));
            (g).drawString(Integer.toString(lifeValue), 10, 25);
        }
        if (lifeValue <= 0) {
            isAlive = false;
        }

        if (isAlive) {
            Polygon triangle = new Polygon(new int[]{(int) YE_posX1, (int) YE_posX2, (int) YE_posX3},
                    new int[]{(int) (YE_posY1), (int) (YE_posY2), (int) (YE_posY3)}, 3);
            g.drawPolygon(triangle);

        }

        g.dispose();
        return image;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
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

    public Color getColor() {
        return color;
    }

    public YellowEnemyModel setColor(Color color) {
        this.color = color;
        return this;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public Controller3 getController3() {
        return controller3;
    }

    public boolean isValidToShow() {
        return isValidToShow;
    }

    public void setValidToShow(boolean validToShow) {
        isValidToShow = validToShow;
    }

    public void setController3(Controller3 controller3) {
        this.controller3 = controller3;
    }

    public Rectangle getRectangle() {
        return new Rectangle((int) posX, (int) posY, (int) sizeOfYE.getWidth(), (int) sizeOfYE.getHeight());
    }
}
