package third.all.model.normalEnemies;

import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class NormalEnemy {
    private Point location;
    private int size;
    private int HP;


    public abstract Point getLocation();

    public abstract void setLocation(Point location);

    public abstract int getSize();

    public abstract void setSize(int size);

    public static void draw(Graphics g, ImageObserver i) {
    }

    abstract int getHP();
}
