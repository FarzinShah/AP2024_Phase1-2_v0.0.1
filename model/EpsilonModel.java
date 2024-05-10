package model;

import view.EpsilonView;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Random;
import java.util.UUID;

import static controller1.Constant.SPEED;
import static controller1.Utils1.addVectors;
import static controller1.Utils1.multiplyVector;

public class EpsilonModel implements Movable,Entity{
    Direction direction;
    Point2D center;
    double radius;
    String id;
    public static LinkedList<EpsilonModel> epsilonModels = new LinkedList<>();

    public EpsilonModel(Direction direction, Point2D center, double radius) {
        this.direction = direction;
        this.center = center;
        this.radius = radius;
        this.id= UUID.randomUUID().toString();
        this.direction=new Direction(new Random().nextInt());
        epsilonModels.add(this);
        createEpsilonView(id);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Point2D getAnchor() {
        return center;
    }

    public void setAnchor(Point2D anchor) {
        this.center = anchor;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public static void createEpsilonView(String id){
        new EpsilonView(id);
    }

    @Override
    public Dimension center(double x, double y) {
        return null;
    }

    @Override
    public Dimension2D velocity(double x, double y) {
        return null;
    }

    @Override
    public Dimension2D acceleration(double x, double y) {
        return null;
    }

    @Override
    public Dimension semiVector(double x, double y) {
        return null;
    }
    public void move(Direction direction, double speed) {
        Point2D movement=multiplyVector(direction.getDirectionVector(),speed);
        this.center=addVectors(center,movement);
    }


    @Override
    public void move() {
        move(direction,SPEED);
    }
}
