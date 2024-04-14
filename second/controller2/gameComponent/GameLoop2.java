package second.controller2.gameComponent;

import second.controller2.gameComponent.Game2;

import java.awt.*;

import static second.controller2.gameComponent.utils.Constant.MIN_PANEL_SIZE;
import static second.controller2.gameComponent.utils.Constant.PANEL_SIZE;

public class GameLoop2 implements Runnable {

    private Game2 game;

    private boolean isRunning;
    private final double updateRate = 1.0d/60.0d;
    private long nextStatTime;
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
        long currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;

        while (isRunning){
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate  = currentTime;
            if(accumulator >= updateRate) {
                while (accumulator >= updateRate) {
                    update();
                    accumulator -= updateRate;
                }
                render();
            }
            printStats();
        }
    }
    private void printStats(){
        if(System.currentTimeMillis() > nextStatTime){
            recentExpendedTime++;
            System.out.println("expended time since here: " + recentExpendedTime + " s.");
            System.out.println("FPS: " + FPS + " UPS: " + UPS);
            FPS = 0;
            UPS = 0;
            nextStatTime = System.currentTimeMillis() + 1000;

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
