package third.all;

import third.all.gameComponents.game.GameLoop;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {
        //AP2024 - HW2:

        SwingUtilities.invokeLater(() -> {
            try {
                new GameLoop();
            } catch (UnsupportedAudioFileException | IOException e) {
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
        });
    }
}
