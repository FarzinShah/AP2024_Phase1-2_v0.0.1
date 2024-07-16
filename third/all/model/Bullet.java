package third.all.model;

import java.awt.*;

import static third.all.controller.Constants.COLOR1;

public class Bullet {
    private int x, y;
    private int dx,dy;
    private int targetX, targetY;
    private int speed = 5;
    int tempx =x,tempy=y;
    int temp2x =x,temp2y=y;

    private Color color;
    private final int speed2 = 2;


    public Bullet(int startX, int startY, int targetX, int targetY, Color color) {
        this.x = startX;
        this.y = startY;
        this.targetX = targetX;
        this.targetY = targetY;
        this.tempx =x;
        this.tempy =y;
        this.color = color;
    }

    public Bullet(int x, int y, int dx, int dy){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.temp2x=x;
        this.temp2y=y;
    }

    public void move() {

        double angle = Math.atan2(targetY - y, targetX - x);

        tempx += (int) (speed * Math.cos(angle));
        tempy += (int) (speed * Math.sin(angle));
    }

    public void move2() {
        temp2x += dx * speed2;
        temp2y += dy * speed2;
    }


    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(tempx, tempy, 10, 10);
    }

    public void draw2(Graphics g) {
        g.setColor(COLOR1);
        g.fillOval(temp2x, temp2y, 10, 10);
    }

    public Rectangle getBounds() {
        return new Rectangle(tempx, tempy, 10, 10);
    }
    public Rectangle getBounds2() {
        return new Rectangle(temp2x, temp2y, 10, 10);
    }
}