package third.all.model.boss;

import third.all.data.Properties;

import java.awt.*;

public class RightHand {
    public static RightHand instance;
    private Point location;
    private int size;
    private int HP;


    public RightHand(Point location, int size, int HP) {
        this.location = location;
        this.size = size;
        this.HP = HP;
    }

    public Point getLocation() {
        return location;
    }

    public RightHand setLocation(Point location) {
        this.location = location;
        return this;
    }

    public int getSize() {
        return size;
    }

    public RightHand setSize(int size) {
        this.size = size;
        return this;
    }

    public int getHP() {
        return HP;
    }

    public RightHand setHP(int HP) {
        this.HP = HP;
        return this;
    }

    public Rectangle getRectangle(){
        return new Rectangle(location.x,location.y,size,size);
    }

    public static RightHand getInstance(){
        if(instance==null) {
            instance = new RightHand(new Point(900,173),275, Properties.getInstance().handsOfBossHP);
            return instance;
        }
        return instance;
    }
    public static void setInstance(RightHand instance1){
        instance = instance1;
    }
}
