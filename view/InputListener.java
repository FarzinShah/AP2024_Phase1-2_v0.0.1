package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static controller1.GameLoop.epsilonModelY;

public class InputListener {


    InputMap inputMap = new InputMap();
    ActionMap actionMap = new ActionMap();
    GameFrame gameFrame;

    public InputListener(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        createKeyBindings();
        createKeyActions();
    }


    private void createKeyBindings() {


        // Key Press:
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "moveRight");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "releaseBlock");

        // Key Release:

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "moveUpReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "moveDownReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "moveLeftReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "moveRightReleased");

    }

    private void createKeyActions() {

        // Key Press Action:
        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                epsilonModelY+=5.0;
            }
        });

        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // set limit

            }
        });

        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // set limit

                // این چرا وقتی به سمت راست میره عمل میکنه نه چپ؟؟؟ Its moveRight!!
            }
        });

        actionMap.put("releaseBlock", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // return if it is null

            }
        });

//        // Key Release Action:
//        actionMap.put("moveUpReleased", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                magnet.setyVelocity(0);
//            }
//        });
//
//        actionMap.put("moveDownReleased", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                magnet.setyVelocity(0);
//            }
//        });
//
//        actionMap.put("moveLeftReleased", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                magnet.setxVelocity(0);
//            }
//        });
//
//        actionMap.put("moveRightReleased", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                magnet.setxVelocity(0);
//            }
//        });


    }

}
