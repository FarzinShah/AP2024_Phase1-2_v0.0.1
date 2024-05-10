package third.all.gameComponents.preGameComponent;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    Color color = new Color(206, 119, 0);


    LoginPanel() {
        this.setFocusable(true);
        this.setSize(1000, 1000);
        this.setBackground(color);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}