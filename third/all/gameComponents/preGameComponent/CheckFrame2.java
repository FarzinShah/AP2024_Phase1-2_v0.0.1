package third.all.gameComponents.preGameComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import third.all.utils.ImageAddressses;

import static third.all.gameComponents.game.GameFrame.getINSTANCE;


public class CheckFrame2 extends JFrame implements Runnable {

    JButton startButton;
    JButton showAllGames;
    JButton exitButton;
    StarterPanel starterPanel;
    IdentifierPanel identifierPanel;
    JButton easyButton;
    JButton mediumButton;
    JButton hardButton;
    int count = 0;





    public CheckFrame2() {

        this.setTitle("Bricks Breaker++");
        this.setIconImage(new ImageIcon(ImageAddressses.LogoGame).getImage());
        this.setSize(600, 600);

        identifierPanel = new IdentifierPanel();
        identifierPanel.setSize(600, 600);



//        easyButton = new JButton("Easy");
//        easyButton.setBounds(180, 200, 200, 50);
//        identifierPanel.add(easyButton);
//
//        easyButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();
////                try {
////                    EasyFrame easyFrame = new EasyFrame(); // game in easy mode:
////                } catch (UnsupportedAudioFileException ex) {
////                    ex.printStackTrace();
////                } catch (IOException ex) {
////                    ex.printStackTrace();
////                }
//            }
//        });
        //medium:
        mediumButton = new JButton("Play");
        mediumButton.setBounds(180, 250, 200, 50);
        identifierPanel.add(mediumButton);

        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                EventQueue.invokeLater(() -> {
                    if(count == 0){
                        getINSTANCE();
                        count++;
                    }
                    if(count!=0){
                        getINSTANCE().panel.setVisible(true);
                    }
                });


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


}