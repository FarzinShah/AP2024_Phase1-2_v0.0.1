package controller1;

import model.EpsilonModel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;

import static controller1.Utils1.relativeLocation;

public abstract class Controller {
    public static Point2D calculateViewLocation(Component component, String id){
        EpsilonModel epsilonModel = findModel(id);
        Point corner=new Point(component.getX(),component.getY());
        assert epsilonModel != null;
        return relativeLocation(epsilonModel.getAnchor(),corner);
    }
    public static double getViewRadius(String id){
        return Objects.requireNonNull(findModel(id)).getRadius();
    }
    public static EpsilonModel findModel(String id){
        for (EpsilonModel ballModel: EpsilonModel.epsilonModels){
            if (ballModel.getId().equals(id)) return ballModel;
        }
        return null;
    }
}
