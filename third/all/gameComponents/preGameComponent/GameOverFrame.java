package third.all.gameComponents.preGameComponent;

import javax.swing.*;
import java.awt.event.*;


import third.all.utils.ImageAddressses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static third.all.gameComponents.game.GameFrame.isValid6;
import static third.all.gameComponents.game.GameFrame.isValid7;
import static third.all.gameComponents.game.GameFrame.isValid8;

import static third.all.gameComponents.preGameComponent.LoginFrame.currentPlayerName;
import static third.all.gameComponents.preGameComponent.LoginFrame.information;
import static third.all.gameComponents.game.GameFrame.score;

public class GameOverFrame extends JFrame implements Runnable, KeyListener {

    JButton startButton;
    JButton showAllGames;
    JButton exitButton;
    StarterPanel starterPanel;
    IdentifierPanel identifierPanel;
    JButton easyButton;
    JButton mediumButton;
    JButton hardButton;




    public GameOverFrame() {

        this.setTitle("WindowKill++");
        this.setIconImage(new ImageIcon(ImageAddressses.LogoGame).getImage());
        this.setSize(600, 600);
        setFocusable(true);
        addKeyListener(this);

        identifierPanel = new IdentifierPanel();
        identifierPanel.setSize(600, 600);


        easyButton = new JButton("Exit & Set Level");
        easyButton.setBounds(180, 200, 200, 50);
        identifierPanel.add(easyButton);

        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    isValid7 = true;
                    isValid6 = true; //is needed?
                    isValid8 = true; //is needed?
                dispose();
                CheckFrame2 checkFrame2 = new CheckFrame2();

            }
        });
        mediumButton = new JButton("Resume");
        mediumButton.setBounds(180, 250, 200, 50);
        identifierPanel.add(mediumButton);

        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        hardButton = new JButton("Sign Out");

        hardButton.setBounds(180, 300, 200, 50);
        identifierPanel.add(hardButton);

        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isValid7 = true;
                for (int i = 0; i < information.size(); i++) {
                    if(currentPlayerName.equals(information.get(i).getName())){
                        information.get(i).setScore(score);
                        information.get(i).setDate(date1());
                        score = 0;
                    }
                }
                dispose();
                MainFrame mainFrame = new MainFrame();
            }
        });



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


    public String date1(){
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);
        return formattedDate;

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_ENTER){
            dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_B){
            dispose();
            new MainFrame(); //: singlton بزنم
        }
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            System.exit(0);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}