package third.all.gameComponents.preGameComponent;

import java.awt.*;

public class InformationsOfSettings {
    private Color colorOfBalls;
    private int sizeOfBalls = 0;

    private static int volume = 0;



    private boolean isPlay = false;

    private boolean isTherePointer = true;

    private Color colorOfPaddle = new Color(0xEF0A412B, true);


    public boolean isPlay() {
        return isPlay;
    }

    public boolean isTherePointer() {
        return isTherePointer;
    }

    public void setTherePointer(boolean therePointer) {
        isTherePointer = therePointer;
    }

    public void setPlay(boolean play) {
        isPlay = play;
    }

    public int getSizeOfBalls() {
        return sizeOfBalls;
    }

    public void setSizeOfBalls(int sizeOfBalls) {
        this.sizeOfBalls = sizeOfBalls;
    }

    public InformationsOfSettings() {
    }

    public Color getColorOfBalls() {
        return colorOfBalls;
    }

    public void setColorOfBalls(Color colorOfBalls) {
        this.colorOfBalls = colorOfBalls;
    }

    public Color getColorOfPaddle() {
        return colorOfPaddle;
    }

    public void setColorOfPaddle(Color colorOfPaddle) {
        this.colorOfPaddle = colorOfPaddle;
    }

    public static int getVolume() {
        return volume;
    }

    public static void setVolume(int volume) {
        InformationsOfSettings.volume = volume;
    }
}
