package third.all.gameComponents.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import third.all.data.Properties;
import third.all.model.boss.LeftHand;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;

public class ClipHandler {
    private static final Logger logger = LoggerFactory.getLogger(ClipHandler.class);
    public static ClipHandler instance;

    private Clip clip;
    private Clip clip2;
    private FloatControl volumeControl;


    public void playBackgroundMusic() {
        File musicPath = new File("src\\open-fields-aaron-paul-low-main-version-25198-02-16.wav");

        try {
            AudioInputStream audioInputStream = getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            logger.info("Background music started.");
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            logger.error("Error playing background music: ", e);
        }
    }

    public void playShotMusic() {
        File musicPath = new File("src\\main\\java\\third\\all\\utils\\Hit2.wav");

        try {
            AudioInputStream audioInputStream = getAudioInputStream(musicPath);
            clip2 = AudioSystem.getClip();
            clip2.open(audioInputStream);
            clip2.loop(Clip.LOOP_CONTINUOUSLY);
            clip2.start();
            logger.info("sound of Shot .");
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            logger.error("Error playing sound of Shot: ", e);
        }
    }

    public void openVolumeControlDialog() {

        JFrame dialog = new JFrame("Volume Control");
//        dialog.setLocation(0,0);
        dialog.setLocationRelativeTo(null);

        JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, (int) volumeControl.getMinimum(), (int) volumeControl.getMaximum(), (int) volumeControl.getValue());
        volumeSlider.addChangeListener(e -> {
            int value = volumeSlider.getValue();
            volumeControl.setValue(value);
            logger.info("Volume set to {}", value);
            Properties.getInstance().play = true;
        });
        dialog.add(volumeSlider);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static ClipHandler getInstance(){
        if(instance==null) {
            instance = new ClipHandler();
            return instance;
        }
        return instance;
    }
    public static void setInstance(ClipHandler instance1){
        instance = instance1;
    }
}
