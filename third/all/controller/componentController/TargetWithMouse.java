package third.all.controller.componentController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

public class TargetWithMouse implements MouseListener, MouseMotionListener {
    private Point2D centerOfEpsilon;
    private double startX;
    private double startY;


    public TargetWithMouse(Point2D centerOfEpsilon){
        this.centerOfEpsilon = centerOfEpsilon;
        startX = this.centerOfEpsilon.getX();
        startY = this.centerOfEpsilon.getY();
    }




    public Point2D getCenterOfEpsilon() {
        return centerOfEpsilon;
    }

    public void setCenterOfEpsilon(Point2D centerOfEpsilon) {
        this.centerOfEpsilon = centerOfEpsilon;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
