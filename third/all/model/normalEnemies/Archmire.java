package third.all.model.normalEnemies;

import third.all.data.booleans.BooleansOf_IsValidToShow;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import static third.all.controller.Constants.ARCHMIRE;

public class Archmire extends NormalEnemy implements NormalEnemyModel {
    public static Archmire instance;

    private Point location;
    private int size;
    private int HP = 30;
    private ArrayList<Point> trails;


    public Archmire(Point location, int size) {
        this.location = location;
        this.size = size;
    }

    public Archmire(){
        trails = new ArrayList<>();
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public void setLocation(Point location) {
        this.location=location;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    int getHP() {
        return HP;
    }

    public static void draw(Graphics g, ImageObserver i){
        g.setColor(new Color(0x44981062, true));
        if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(0)) {
            synchronized (Archmire.getInstance().getTrails()) {
                for (Point trail : Archmire.getInstance().getTrails()) {
                    g.fillOval(trail.x, trail.y, Archmire.getInstance().getSize(), Archmire.getInstance().getSize());
                }
            }
            g.drawImage(ARCHMIRE, Archmire.getInstance().getLocation().x, Archmire.getInstance().getLocation().y, Archmire.getInstance().getSize(), Archmire.getInstance().getSize(), i);
        }

    }

    public ArrayList<Point> getTrails() {
        return trails;
    }

    public void setTrails(ArrayList<Point> trails) {
        this.trails = trails;
    }

    public static Archmire getInstance(){
        if(instance==null) {
            instance = new Archmire();
            return instance;
        }
        return instance;
    }
}
