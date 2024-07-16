package third.all.model;

import java.awt.*;
import java.util.ArrayList;

public class Archmire implements NormalEnemyModel{
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
