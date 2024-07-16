package third.all.model;

import third.all.data.BooleansOf_IsValidToShow;
import third.all.data.PanelsData;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import static third.all.controller.Constants.NECROPICK;
import static third.all.controller.Constants.WYRM;

public class Wyrm implements NormalEnemyModel{
    public static Wyrm instance;
    private Point location;
    private int size;
    private int HP;
    private boolean isValidToShoot;

    Wyrm(Point location,int HP){
        size = 50;
        this.location = location;
        this.HP = HP;
        isValidToShoot = false;
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

    public boolean isValidToShoot() {
        return isValidToShoot;
    }

    public void setValidToShoot(boolean validToShoot) {
        isValidToShoot = validToShoot;
    }

    public static Wyrm getInstance(){
        if(instance==null) {
            instance = new Wyrm(new Point(1200,200),12);
            return instance;
        }
        return instance;
    }
    public static void draw(Graphics g, ImageObserver i) {
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(3)) {
            g.drawImage(WYRM, Wyrm.getInstance().getLocation().x, Wyrm.getInstance().getLocation().y, Wyrm.getInstance().getSize()+25, Wyrm.getInstance().getSize()+25, i);
        }
    }

    public Rectangle getRectangle(){
        return new Rectangle(location.x,location.y,size,size);
    }
    public static void setInstance(Wyrm instance1){
        instance = instance1;
    }




}
