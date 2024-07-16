package third.all.model;
import java.awt.*;
import java.awt.geom.Path2D;

public class Omenoct implements NormalEnemyModel{
    public static Omenoct instance;
    private Point location;
    private int size;
    private int HP = 20;
    private int radius = 30;

    public Omenoct(Point location, int size) {
        this.location = location;
        this.size = size;
    }

    public Omenoct(){}

    @Override
    public Point getLocation() {
        return location;
    }
    @Override
    public void setLocation(Point location) {
        this.location = location;
    }
    @Override
    public int getSize() {
        return size;
    }
    @Override
    public void setSize(int size) {
        this.size = size;
    }

    public Rectangle getRectangle(){
        return new Rectangle(location.x,location.y,size,size);
    }
    public Shape getShape() {
        Path2D.Double octagon = new Path2D.Double();
        double angleStep = Math.PI / 4;
        double startAngle = Math.PI / 8;

        for (int i = 0; i < 8; i++) {
            double angle = startAngle + i * angleStep;
            double dx = Math.cos(angle) * size;
            double dy = Math.sin(angle) * size;
            if (i == 0) {
                octagon.moveTo(location.x + dx, location.y + dy);
            } else {
                octagon.lineTo(location.x + dx, location.y + dy);
            }
        }
        octagon.closePath();
        return octagon;
    }


    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public static Omenoct getInstance(){
        if(instance==null) {
            instance = new Omenoct();
            return instance;
        }
        return instance;
    }


}
