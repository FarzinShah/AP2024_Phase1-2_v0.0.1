package third.all.model.normalEnemies;

import third.all.data.booleans.BooleansOf_IsValidToShow;

import java.awt.*;
import java.awt.image.ImageObserver;

import static third.all.controller.Constants.NECROPICK;

public class Necropick extends NormalEnemy {
    public static Necropick instance;

    private Point location;
    private int size;
    private int HP = 20;

    public Necropick(Point location, int size) {
        this.location = location;
        this.size = size;
    }

    public Necropick() {
    }

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

    public void draw(Graphics g, ImageObserver i) {
        if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(1)) {
            g.drawImage(NECROPICK, Necropick.getInstance().getLocation().x, Necropick.getInstance().getLocation().y, Necropick.getInstance().getSize(), Necropick.getInstance().getSize(), i);
        }
    }

    public Rectangle getRectangle() {
        return new Rectangle(location.x, location.y, size, size);
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public static Necropick getInstance() {
        if (instance == null) {
            instance = new Necropick();
            return instance;
        }
        return instance;
    }

}
