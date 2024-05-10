package third.all.controller.componentController;

import third.all.controller.movement.Position;

import java.awt.*;

import static third.all.controller.Constants.SIZE_OF_TWM_ITEM;

public class TKM2_Item_Model {
    private Position nodeOfRectangle2;

    private double centerX;
    private double centerY;
    private double radius;


    public TKM2_Item_Model(Position nodeOfRectangle2){
        this.nodeOfRectangle2 = nodeOfRectangle2;
        centerX = this.nodeOfRectangle2.getX() + SIZE_OF_TWM_ITEM/2;
        centerY = this.nodeOfRectangle2.getY() + SIZE_OF_TWM_ITEM/2;
        radius = SIZE_OF_TWM_ITEM/2;

    }
    public void draw(Graphics2D g){
        Rectangle rectangle = new Rectangle((int) nodeOfRectangle2.getX(), (int) nodeOfRectangle2.getY(), (int) SIZE_OF_TWM_ITEM, (int) SIZE_OF_TWM_ITEM);
        g.fill(rectangle);
    }

    public Position getNodeOfRectangle() {
        return nodeOfRectangle2;
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
