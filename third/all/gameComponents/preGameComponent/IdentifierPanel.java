package third.all.gameComponents.preGameComponent;

import javax.swing.*;
import java.awt.*;

public class IdentifierPanel extends JPanel {
    Color color = new Color(206, 119, 0);


    public IdentifierPanel() {
        this.setFocusable(true);
        this.setSize(1000, 1000);
        this.setBackground(color);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }



}
