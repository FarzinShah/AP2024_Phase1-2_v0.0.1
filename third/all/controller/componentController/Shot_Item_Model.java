package third.all.controller.componentController;

import java.awt.*;
import java.awt.geom.Point2D;

import static third.all.controller.Constants.SIZE_OF_SHOT_BULLET;
import static third.all.controller.Constants.SIZE_OF_TWM_ITEM;

public class Shot_Item_Model {
    private Point2D nodeOfRectangle;
    private double centerX;
    private double centerY;
    private double radius;
    public Shot_Item_Model(Point2D nodeOfRectangle){
        this.nodeOfRectangle = nodeOfRectangle;
        centerX = this.nodeOfRectangle.getX() + SIZE_OF_SHOT_BULLET/2;
        centerY = this.nodeOfRectangle.getY() + SIZE_OF_SHOT_BULLET/2;
        radius = SIZE_OF_SHOT_BULLET/2;

    }
    public void draw(Graphics2D g){
        Rectangle rectangle = new Rectangle((int) nodeOfRectangle.getX(), (int) nodeOfRectangle.getY(), (int) SIZE_OF_TWM_ITEM, (int) SIZE_OF_TWM_ITEM);
        g.fill(rectangle);
    }

    public Point2D getNodeOfRectangle() {
        return nodeOfRectangle;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getRadius() {
        return radius;
    }
}


