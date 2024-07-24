package third.all.model.boss;

import third.all.data.Properties;
import third.all.model.Omenoct;

import java.awt.*;
import java.awt.image.ImageObserver;

import static third.all.controller.Constants.HEAD;

public class Head implements Boss {
    public static Head instance;
    private Point location;
    private int size;
    private int HP;


    public Head(Point location, int size, int HP) {
        this.location = location;
        this.size = size;
        this.HP = HP;
    }

    public Point getLocation() {
        return location;
    }

    public Head setLocation(Point location) {
        this.location = location;
        return this;
    }

    public int getSize() {
        return size;
    }

    public Head setSize(int size) {
        this.size = size;
        return this;
    }

    public int getHP() {
        return HP;
    }

    public Head setHP(int HP) {
        this.HP = HP;
        return this;
    }

    @Override
    public Rectangle getRectangle(){
        return new Rectangle(location.x,location.y,size,size);
    }

    @Override
    public void draw(Graphics g, ImageObserver i) {
        if(HP>0)
        g.drawImage(HEAD, Head.getInstance().getLocation().x, Head.getInstance().getLocation().y, Head.getInstance().getSize(), Head.getInstance().getSize(), i);

    }


    public static Head getInstance(){
        if(instance==null) {
            instance = new Head(Properties.getInstance().locationOfHead,250, Properties.getInstance().headOfBossHP);
            return instance;
        }
        return instance;
    }
    public static void setInstance(Head instance1){
        instance = instance1;
    }
}
