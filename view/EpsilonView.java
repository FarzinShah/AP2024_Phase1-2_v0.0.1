package view;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class EpsilonView {
    String id; // برای اینکه با دادن id یکتا بتونیم entity موردنظرمون رو به server side منتقل کنیم.
    Point2D currentLocation=new Point2D.Double(0,0);
    double currentRadius;
    public static LinkedList<EpsilonView> epsilonViews=new LinkedList<>();
    public EpsilonView(String id) {
        this.id = id;
        epsilonViews.add(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Point2D getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point2D currentLocation) {
        this.currentLocation = currentLocation;
    }

    public double getCurrentRadius() {
        return currentRadius;
    }

    public void setCurrentRadius(double currentRadius) {
        this.currentRadius = currentRadius;
    }

    public static LinkedList<EpsilonView> getBallViews() {
        return epsilonViews;
    }

    public static void setBallViews(LinkedList<EpsilonView> ballViews) {
        EpsilonView.epsilonViews = ballViews;
    }
}
