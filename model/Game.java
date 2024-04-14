package model;

import view.GameFrame;
import view.InputListener;

public class Game {


    protected InputListener inputListener;
//    protected Intersection intersection;
    private boolean isGameRunning = true;


    public Game() {
//        gameFrame = new GameFrame();
//        intersection = new Intersection(gameFrame.getMagnet());
        inputListener = new InputListener(GameFrame.getINSTANCE());
    }


//
//    public Intersection getIntersection() {
//        return intersection;
//    }
//
//    public void setIntersection(Intersection intersection) {
//        this.intersection = intersection;
//    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        isGameRunning = gameRunning;
    }

}
