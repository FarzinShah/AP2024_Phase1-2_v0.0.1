package model;

import java.awt.*;
import java.awt.geom.Dimension2D;

public interface Entity {
    Dimension center(double x, double y);
    Dimension2D velocity(double x, double y);
    Dimension2D acceleration(double x, double y);
    Dimension semiVector(double x, double y);
}
