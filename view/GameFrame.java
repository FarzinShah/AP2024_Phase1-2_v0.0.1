package view;

import javax.swing.*;
import java.awt.*;

import static controller1.Constant.MAX_PANEL_SIZE;

public class GameFrame extends JFrame {

    private static GameFrame INSTANCE; //Singleton works like a static variable.
    JPanel panel;
    public GameFrame() throws HeadlessException{
        setVisible(true);
        panel = new JPanel();
        panel.setSize(MAX_PANEL_SIZE);
        panel.setVisible(true);
        panel.setFocusable(true);
        panel.requestFocus();
        panel.requestFocusInWindow();
        panel.setLayout(null);
        panel.setBackground(new Color(50));
        setBackground(new Color(0xB0494949, false));
//        setUndecorated(true);
        setSize(MAX_PANEL_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        setContentPane(panel);


    }
    public static GameFrame getINSTANCE() {
        if (INSTANCE==null) INSTANCE=new GameFrame();
        return INSTANCE;
    }

}
