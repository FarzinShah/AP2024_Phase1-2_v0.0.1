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
    private int length;

    private final Color normalColor;
    private final Color semiNormalColor;
    private final Color unNormalColor;
    private Color color;
    private boolean isValidToShow;


    public GreenEnemyModel(Controller3 controller3, double radius, double posX, double posY) {
        super(controller3, posX, posY);
        this.posX = posX;
        this.posY = posY;
        this.posCenterX = radius;
        this.posCenterY = radius;
        sizeOfGE = new SizeOfGreenEnemy(2 * radius, 2 * radius);
        this.radius = radius;
        this.controller3 = controller3;
        this.GE_posX1 = posCenterX;
        this.GE_posY1 = posCenterY + (radius);
        this.length = 25;
        normalColor = new Color(0xCD00FC04, true);
        semiNormalColor = new Color(0x4A1CD907, true);
        unNormalColor = new Color(0, 0, 0, 0);
        isValidToShow = false;
    }

    @Override
    public void update() {
        super.update();

    }

    public Image getSprite() {
        BufferedImage image = new BufferedImage((int) sizeOfGE.getWidth(), (int) sizeOfGE.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        if (isValidToShow) {
            if (lifeValue >= 8) {
                g.setColor(normalColor);
            }
            if (lifeValue < 8) {
                g.setColor(semiNormalColor);
            }
        } else g.setColor(unNormalColor);
        Rectangle rectangle = new Rectangle(0, 0, length, length);
        if (length == 25) {
            g.setFont(new Font("calibri", Font.BOLD, 10));
            ((Graphics2D) g).drawString(Integer.toString(lifeValue), 5, 25);
        }
        g.draw(rectangle);
        g.dispose();
        return image;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) posX, (int) posY, 25, 25);
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

    public boolean isValidToShow() {
        return isValidToShow;
    }

    public void setValidToShow(boolean validToShow) {
        isValidToShow = validToShow;

    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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
