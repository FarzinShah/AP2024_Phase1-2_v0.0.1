package third.all.gameComponents.preGameComponent;

import third.all.utils.ImageAddressses;
import third.all.gameComponents.extendedComponentsPhase1.GuideFrame;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame implements Runnable , KeyListener {

    JButton startButton;
    JButton showAllGames;
    JButton exitButton;
    StarterPanel starterPanel;
    IdentifierPanel identifierPanel;
    JButton loginButton;
    JButton signUpButton;
    JButton guidePageButton;

    File file = new File(ImageAddressses.LogoGame);
    BufferedImage bufferedImage;

    {
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ImageIcon imageIcon = new ImageIcon(bufferedImage);


    public MainFrame() {

        this.setTitle("WindowKill++");
        this.setIconImage(new ImageIcon(ImageAddressses.LogoGame).getImage());
        this.setSize(600, 600);

        identifierPanel = new IdentifierPanel();
        identifierPanel.setSize(600, 600);

        JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon((ImageAddressses.LogoGame100px))); //Sets the image to be displayed as an icon
//        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(250, 100,100 , 100); //Sets the location of the image
//        pack();
        identifierPanel.add(label);

        setVisible(true);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(this);




        loginButton = new JButton("Login");
        loginButton.setBounds(100, 200, 200, 50);
        identifierPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginFrame loginFrame = new LoginFrame();
            }
        });
        //sign up:
        signUpButton = new JButton("SignUp");
        signUpButton.setBounds(300, 200, 200, 50);
        identifierPanel.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SignUpFrame signUpFrame = new SignUpFrame();
            }
        });
        //guide:
        guidePageButton = new JButton("Guide");
        guidePageButton.setBounds(200, 250, 200, 50);
        identifierPanel.add(guidePageButton);

        guidePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuideFrame guideFrame = new GuideFrame();
            }
        });


        //-------------------------------------------------------------

        this.setLayout(null);
        identifierPanel.setLayout(null);
        this.setContentPane(identifierPanel);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }


    public void run() {
        repaint();
        revalidate();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){

            System.exit(2);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}