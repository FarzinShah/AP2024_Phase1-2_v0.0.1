package org.main;

import controller1.GameLoop;
import view.GameFrame;
import view.MotionPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            GameFrame.getINSTANCE();
            MotionPanel.getINSTANCE();
            new GameLoop();
        });

    }
}
