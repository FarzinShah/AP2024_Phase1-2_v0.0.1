package third.all.gameComponents.game;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Panel {
    private double x;
    private double y;
    private double width;
    private double height;

    public Panel(double x, double y ,double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }
    public int getRightX() {
        return (int) (x + width);
    }
    public int getDownY() {
        return (int) (y + height);
    }

    public double getY() {
        return y;
    }

    public Panel setX(double x) {
        this.x = x;
        return this;
    }

    public Panel setY(double y) {
        this.y = y;
        return this;
    }

    public Rectangle getRectangle(){
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
    }

    public void drawBossPanel(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect((int) x, (int) y, (int) width, (int) height);


    }

    public void drawWym(Graphics g) {
        g.setColor(new Color(0xD3000000, true));
        g.fillRect((int) x, (int) y, (int) width, (int) height);
    }
    public void drawRedZone(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
    }
}
