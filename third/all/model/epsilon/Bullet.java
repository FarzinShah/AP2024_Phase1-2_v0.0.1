package third.all.model.epsilon;

import third.all.data.PanelsData;

import java.awt.*;

import static third.all.controller.Constants.COLOR1;
import static third.all.gameComponents.game.View.panels;

public class Bullet {
    private int x, y;
    private int dx, dy;
    private int targetX, targetY;
    int tempx = x, tempy = y;
    int temp2x = x, temp2y = y;

    private Color color;
    private final int speed2 = 2;


    public Bullet(int startX, int startY, int targetX, int targetY, Color color) {
        this.x = startX;
        this.y = startY;
        this.targetX = targetX;
        this.targetY = targetY;
        this.tempx = x;
        this.tempy = y;
        this.color = color;
    }

    public Bullet(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.temp2x = x;
        this.temp2y = y;
    }

    public void move() {

        double angle = Math.atan2(targetY - y, targetX - x);

        int speed = 5;
        tempx += (int) (speed * Math.cos(angle));
        tempy += (int) (speed * Math.sin(angle));
    }

    public void move2() {
        temp2x += dx * 3;
        temp2y += dy * 3;
    }


    public void draw(Graphics g) {
        if (!getBounds().intersects(panels.get(1).getRectangle()) && !getBounds().intersects(panels.get(0).getRectangle()) && !getBounds().intersects(PanelsData.getInstance().getBlackOrbPanels().get(0).getRectangle())
                && !getBounds().intersects(PanelsData.getInstance().getBlackOrbPanels().get(1).getRectangle()) && !getBounds().intersects(PanelsData.getInstance().getBlackOrbPanels().get(2).getRectangle()) && !getBounds().intersects(PanelsData.getInstance().getBlackOrbPanels().get(3).getRectangle()) && !getBounds().intersects(PanelsData.getInstance().getBlackOrbPanels().get(4).getRectangle())) {
            g.setColor(new Color(0, 0, 0, 0));
        } else {
            g.setColor(color);
        }

        g.fillOval(tempx, tempy, 10, 10);
    }

    public void draw2(Graphics g) {
        if (!getBounds2().intersects(panels.get(1).getRectangle()) && !getBounds2().intersects(panels.get(0).getRectangle())) {
            g.setColor(new Color(0, 0, 0, 0));
        } else {
            g.setColor(COLOR1);
        }
        g.fillOval(temp2x, temp2y, 10, 10);
    }

    public void draw3(Graphics g) {
        if (!getBounds2().intersects(panels.get(1).getRectangle()) && !getBounds2().intersects(panels.get(0).getRectangle())) {
            g.setColor(new Color(0, 0, 0, 0));
        } else {
            g.setColor(new Color(0));
        }
        g.fillOval(temp2x, temp2y, 10, 10);
    }

    public void draw4(Graphics g) {
        if (!getBounds2().intersects(panels.get(1).getRectangle()) && !getBounds2().intersects(panels.get(0).getRectangle())) {
            g.setColor(new Color(0, 0, 0, 0));
        } else {
            g.setColor(new Color(0x9BBB3400, true));
        }
        g.fillOval(temp2x, temp2y, 10, 10);
    }

    public Rectangle getBounds() {
        return new Rectangle(tempx, tempy, 10, 10);
    }

    public Rectangle getBounds2() {
        return new Rectangle(temp2x, temp2y, 10, 10);
    }
}