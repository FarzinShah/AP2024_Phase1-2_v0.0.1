package third.all;

import third.all.gameComponents.preGameComponent.MainFrame;
import third.all.gameComponents.preGameComponent.Settings;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {
        //AP2024 - HW2:

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
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
