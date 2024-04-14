package controller1;

import model.Direction;
import model.EpsilonModel;
import model.Game;
import view.EpsilonView;
import view.GameFrame;
import view.MotionPanel;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

import static controller1.Constant.*;
import static controller1.Controller.calculateViewLocation;
import static controller1.Controller.getViewRadius;
import static model.EpsilonModel.epsilonModels;

public class GameLoop extends Thread implements ActionListener, KeyListener {
    public static double epsilonModelX;
    public static double epsilonModelY;
    private Game game = new Game();


    public GameLoop() {
        epsilonModelX = GLASS_FRAME_DIMENSION.getWidth()/4;
        epsilonModelY = GLASS_FRAME_DIMENSION.getHeight()/4;
        new Timer((int) FRAME_UPDATE_TIME, e -> updateView()){{setCoalesce(true);}}.start();
        new Timer((int) MODEL_UPDATE_TIME, e -> updateModel()){{setCoalesce(true);}}.start();
        new EpsilonModel(new Direction(0),new Point2D.Double(epsilonModelX,epsilonModelY),20);
        start();
    }

    public void run() {
        System.out.println("?");
        while (game.isGameRunning()) {
            updateModel();
            updateView();
        }

    }


    public void updateView(){
        for (EpsilonView epsilonView: EpsilonView.epsilonViews){
            epsilonView.setCurrentLocation(calculateViewLocation(MotionPanel.getINSTANCE(),epsilonView.getId()));
            epsilonView.setCurrentRadius(getViewRadius(epsilonView.getId()));
        }
        GameFrame.getINSTANCE().repaint();
        MotionPanel.getINSTANCE().repaint();
    }
    public void updateModel() {
        epsilonModels.get(0).setAnchor(new Point2D.Double(epsilonModelX,epsilonModelY));
        for (EpsilonModel epsilonModel: epsilonModels){
            epsilonModel.move();
        }

//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            System.out.println(epsilonModelX + " " + epsilonModelY);

            epsilonModelX+=5.0;
        }

    }


    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }


}
