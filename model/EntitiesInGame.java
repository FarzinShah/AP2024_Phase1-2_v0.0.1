package model;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class EntitiesInGame {

    private double x;
    private double y;
    private double width;
    private double height;
    private double radius;

    public EntitiesInGame(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
