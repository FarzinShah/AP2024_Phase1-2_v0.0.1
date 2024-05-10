package third.all.gameComponents.preGameComponent;

import javax.swing.*;
import java.awt.*;

public class StarterPanel extends JPanel {


    Color color = new Color(160, 75, 75);


    StarterPanel() {
        this.setFocusable(true);
        this.setSize(1000, 1000);
        this.setBackground(color);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }



}
