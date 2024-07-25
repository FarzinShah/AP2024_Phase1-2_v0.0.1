package third.all.controller.gameController;

import third.all.controller.componentController.Input;
import third.all.data.booleans.BooleansOf_IsValidToShow;
import third.all.data.booleans.HelpingBooleans;
import third.all.data.Properties;
import third.all.gameComponents.extendedComponents.SkillTreeFrame;
import third.all.gameComponents.extendedComponents.StoreFrame;
import third.all.gameComponents.game.ClipHandler;
import third.all.gameComponents.game.FunctionalMethods;
import third.all.specialFeatures.shop.Shopping;
import third.all.specialFeatures.skillTree_v2.SkillTreeGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static third.all.controller.Constants.IS_VALID_STORE;

public class GameKeyListener implements KeyListener {

    Input input;

    public GameKeyListener(Input input){
        this.input = input;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (IS_VALID_STORE) {
            if (e.getKeyCode() == KeyEvent.VK_1) {
                new StoreFrame();
                Properties.getInstance().play = false;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {
            HelpingBooleans.getInstance().lineShower2 = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            HelpingBooleans.getInstance().lineShower2 = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_3){
            Thread shootThread = new Thread(() -> {
                while (HelpingBooleans.getInstance().isValidToPlayBossMusic) {
                    try {
                        Thread.sleep(20); // Sleep for 10 seconds
                        FunctionalMethods.playBossMusic();
                        HelpingBooleans.getInstance().isValidToPlayBossMusic = false;
                    } catch (InterruptedException p) {
                        p.printStackTrace();
                    }
                }
            });
            shootThread.start();        }

        if (e.getKeyCode() == KeyEvent.VK_O) {
            Properties.getInstance().play = false;
            Thread shootThread = new Thread(() -> {
                while (BooleansOf_IsValidToShow.getInstance().getIsValidToShowStores().get(0)) {
                    try {
                        Thread.sleep(20); // Sleep for 10 seconds
                        new SkillTreeGUI();
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowStores().set(0, false);
                    } catch (InterruptedException p) {
                        p.printStackTrace();
                    }
                }
            });
            shootThread.start();
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
//            disposer();
        }
        if(e.getKeyCode() == KeyEvent.VK_9){
            FunctionalMethods.epsilonProShoot();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Properties.getInstance().play = true;

        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            Properties.getInstance().play = true;
            FunctionalMethods.loadGameStateWhenPaused(input);

        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            Properties.getInstance().play = false;
            FunctionalMethods.saveGameStateWhenPaused();
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            FunctionalMethods.saveGameState();
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            FunctionalMethods.loadGameState(input);
        }
        if (e.getKeyCode() == KeyEvent.VK_V) {
            Properties.getInstance().play = false;
            ClipHandler.getInstance().openVolumeControlDialog();
        }
        if (e.getKeyCode() == KeyEvent.VK_I) {
            Properties.getInstance().play = false;
            Thread shootThread = new Thread(() -> {
                while (BooleansOf_IsValidToShow.getInstance().getIsValidToShowStores().get(1)) {
                    try {
                        Thread.sleep(20); // Sleep for 10 seconds
                        new Shopping();
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowStores().set(1, false);
                    } catch (InterruptedException p) {
                        p.printStackTrace();
                    }
                }
            });
            shootThread.start();
        }
        if (e.getKeyCode() == KeyEvent.VK_U) {
            Properties.getInstance().play = false;
            Thread shootThread = new Thread(() -> {
                while (HelpingBooleans.getInstance().isValidToShowSkillTree2) {
                    try {
                        Thread.sleep(20); // Sleep for 10 seconds
                        new SkillTreeFrame();
                        HelpingBooleans.getInstance().isValidToShowSkillTree2 = false;
                    } catch (InterruptedException p) {
                        p.printStackTrace();
                    }
                }
            });
            shootThread.start();
        }

/* todo: these were for phase1

       if (e.getKeyCode() == KeyEvent.VK_B) {
            validShots.set(shotCounter, true);
            currentShotCounter = shotCounter;

            double dX = clickedX - playerX;
            double dY = clickedY - playerY;
            System.out.println("dx: " + dX + "dy: " + dY + "dy/dx: " + dY / dX);

            shotsOfEpsilons.get(currentShotCounter).setDirX(-dX / 100);
            shotsOfEpsilons.get(currentShotCounter).setDirY(-dY / 100);
            shotsOfEpsilons.get(currentShotCounter).setPlaceX(playerX + 15);
            shotsOfEpsilons.get(currentShotCounter).setPlaceY(playerY);
            currentShots.add(shotsOfEpsilons.get(currentShotCounter));
            shotCounter++;

        }*/

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
