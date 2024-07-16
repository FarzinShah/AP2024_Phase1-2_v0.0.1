package third.all.model.boss;

import third.all.data.Properties;

import java.awt.*;

public class LeftHand {
    public static LeftHand instance;
    private Point location;
    private int size;
    private int HP;


    public LeftHand(Point location, int size, int HP) {
        this.location = location;
        this.size = size;
        this.HP = HP;
    }

    public Point getLocation() {
        return location;
    }

    public LeftHand setLocation(Point location) {
        this.location = location;
        return this;
    }

    public int getSize() {
        return size;
    }

    public LeftHand setSize(int size) {
        this.size = size;
        return this;
    }

    public int getHP() {
        return HP;
    }

    public LeftHand setHP(int HP) {
        this.HP = HP;
        return this;
    }

    public Rectangle getRectangle(){
        return new Rectangle(location.x,location.y,size,size);
    }

    public static LeftHand getInstance(){
        if(instance==null) {
            instance = new LeftHand(new Point(400,200),250, Properties.getInstance().handsOfBossHP);
            return instance;
        }
        return instance;
    }
    public static void setInstance(LeftHand instance1){
        instance = instance1;
    }
}
