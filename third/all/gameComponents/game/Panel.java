package third.all.gameComponents.game;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Panel {
    private int x;
    private int y;
    private double width;
    private double height;

    public Panel(int x, int y ,double width, double height) {
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

    public int getX() {
        return x;
    }
    public int getRightX() {
        return (int) (x + width);
    }
    public int getDownY() {
        return (int) (y + height);
    }

    public int getY() {
        return y;
    }

    public Rectangle getRectangle(){
        return new Rectangle(x, y, (int) width, (int) height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, (int) width, (int) height);
    }

    public void drawBossPanel(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, (int) width, (int) height);


    }

    public void drawWym(Graphics g) {
        g.setColor(new Color(0x13000000, true));
        g.fillRect(x, y, (int) width, (int) height);
    }
    public void drawRedZone(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, (int) width, (int) height);
    }
}
