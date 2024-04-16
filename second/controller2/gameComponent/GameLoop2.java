package second.controller2.gameComponent;

import second.controller2.gameComponent.Game2;

import java.awt.*;

import static second.controller2.gameComponent.utils.Constant.MIN_PANEL_SIZE;
import static second.controller2.gameComponent.utils.Constant.PANEL_SIZE;
import static second.display.Display.canvas;

public class GameLoop2 implements Runnable {

    private Game2 game;

    private boolean isRunning;
    private final double updateRate = 1.0d/60.0d;
    private long nextStatTime;
    private long nextStatTime2; // موقت

    private double FPS,UPS;
    public static double recentExpendedTime;

    public GameLoop2(Game2 game) {
        this.game = game;
        recentExpendedTime = 0;
    }

    @Override
    public void run() {
        isRunning = true;
        double accumulator = 0;
        double accumulator2 = 0;

        long currentTime,currentTime2, lastUpdate = System.currentTimeMillis() , lastUpdate2 = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;
        nextStatTime2 = System.currentTimeMillis() + 500;

        while (isRunning){
            currentTime = System.currentTimeMillis();
            currentTime2 = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            double lastRenderTimeInHalfSeconds = (currentTime2 - lastUpdate2) / 500d;

            accumulator += lastRenderTimeInSeconds;
            accumulator2 += lastRenderTimeInHalfSeconds;

            lastUpdate  = currentTime;
            lastUpdate2  = currentTime2;

            if(accumulator >= updateRate) {
                while (accumulator >= updateRate) {
                    update();
                    accumulator -= updateRate;
                    accumulator2-= updateRate;
                }
                render();
            }

            printStats();
        }
    }
    private void printStats(){
        // every 1 second:
//        if(System.currentTimeMillis() > nextStatTime){
//            if(recentExpendedTime>3.0 && recentExpendedTime<7.0){
//                game.getDisplay().setPreferredSize(PANEL_SIZE);
//                game.getDisplay().add(canvas);
//                game.getDisplay().pack();
//            }
//
//            recentExpendedTime++;
//            System.out.println("expended time since here: " + recentExpendedTime + " s.");
//            System.out.println("FPS: " + FPS + " UPS: " + UPS);
//            FPS = 0;
//            UPS = 0;
//            nextStatTime = System.currentTimeMillis() + 1000;
//
//        }
        // every 0.5 second:

        if(System.currentTimeMillis() > nextStatTime2){
            if(recentExpendedTime>3.0 && recentExpendedTime<7.0){
                game.getDisplay().setPreferredSize(PANEL_SIZE);
                game.getDisplay().add(canvas);
                game.getDisplay().pack();
            }

            recentExpendedTime+=0.5;
            System.out.println("expended time since here: " + recentExpendedTime + " s.");
            System.out.println("FPS: " + FPS + " UPS: " + UPS);
            FPS = 0;
            UPS = 0;
            nextStatTime2 = System.currentTimeMillis() + 500;
        }

    }
    private void render(){
        game.render();
        FPS++;

    }
    private void update(){
        game.update();
        UPS++;

    }
}
