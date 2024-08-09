package third.all.model.normalEnemies;

import third.all.data.booleans.BooleansOf_IsValidToShow;
import third.all.data.booleans.HelpingBooleans;

import java.awt.*;
import java.awt.image.ImageObserver;

import static third.all.controller.Constants.WYRM;

public class Wyrm extends NormalEnemy {
    public static Wyrm instance;
    private Point location;
    private int size;
    private int HP;

    public Wyrm(Point location, int HP) {
        size = 50;
        this.location = location;
        this.HP = HP;
        HelpingBooleans.getInstance().isValidToShootWyrm = false;
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



    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public static Wyrm getInstance() { //todo: -Out of work: MVC
        if (instance == null) {
            instance = new Wyrm(new Point(1200, 200), 12);
            return instance;
        }
        return instance;
    }

    public void draw(Graphics g, ImageObserver i) {
        if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(3)) {
            g.drawImage(WYRM, getLocation().x, getLocation().y, getSize() + 25, getSize() + 25, i);
        }
    }

    public Rectangle getRectangle() {
        return new Rectangle(location.x, location.y, size, size);
    }

    public static void setInstance(Wyrm instance1) {
        instance = instance1;
    }


}
