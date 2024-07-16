package third.all;

import third.all.gameComponents.game.GameFrame;
import third.all.gameComponents.game.GameFrame2;
import third.all.gameComponents.preGameComponent.MainFrame;
import third.all.gameComponents.preGameComponent.Settings;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {
        //AP2024 - HW2:

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new GameFrame2();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                new MainFrame();
//                new Settings();
//                try {
//                    new MediumFrame();
//                } catch (UnsupportedAudioFileException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }
}
