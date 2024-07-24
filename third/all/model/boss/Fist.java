package third.all.model.boss;

import third.all.data.Properties;

import java.awt.*;
import java.awt.image.ImageObserver;

import static third.all.controller.Constants.FIST;
import static third.all.controller.Constants.HEAD;

public class Fist implements Boss{

    public static Fist instance;
    private Point location;
    private int size;

    public Fist(Point location, int size){
        this.location = location;
        this.size = size;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(location.x, location.y, size, size);
    }

    @Override
    public void draw(Graphics g, ImageObserver i) {
        if(Head.getInstance().getHP()<2* Properties.getInstance().headOfBossHP/3) {
            g.drawImage(FIST, location.x, location.y, size, size, i);
        }
    }


    public static Fist getInstance(){
        if(instance==null) {
//            instance = new Fist(Properties.getInstance().locationOfFist,250);
            return instance;
        }
        return instance;
    }
    public static void setInstance(Fist instance1){
        instance = instance1;
    }
}
