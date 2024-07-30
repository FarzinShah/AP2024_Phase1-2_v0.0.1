package third.all.gameComponents.extendedComponentsPhase1;

import third.all.utils.ImageAddressses;
import third.all.gameComponents.preGameComponent.IdentifierPanel;
import third.all.gameComponents.preGameComponent.MainFrame;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GuideFrame extends JFrame implements Runnable , KeyListener {
    File file = new File(ImageAddressses.LogoGame);
    IdentifierPanel identifierPanel;

    BufferedImage bufferedImage;

    {
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public GuideFrame(){
        this.setTitle("Press B to back");
        this.setIconImage(new ImageIcon(ImageAddressses.LogoGame).getImage());
        this.setSize(750, 890);

        identifierPanel = new IdentifierPanel();
        identifierPanel.setSize(800, 800);

        JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon((ImageAddressses.GuideLine))); //Sets the image to be displayed as an icon
        label.setBounds(0, 0,746 , 872); //Sets the location of the image
        identifierPanel.add(label);

        setVisible(true);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        addKeyListener(this);


        this.setLayout(null);
        identifierPanel.setLayout(null);
        this.setContentPane(identifierPanel);
        this.setLocationRelativeTo(null);
//        this.setResizable(false);
        this.setVisible(true);
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            System.exit(2);
        }
        if(e.getKeyCode()==KeyEvent.VK_B){
            dispose();
            MainFrame mainFrame = new MainFrame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
//        repaint();
//        revalidate();
    }
}
