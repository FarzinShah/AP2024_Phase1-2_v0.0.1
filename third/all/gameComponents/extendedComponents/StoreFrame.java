package third.all.gameComponents.extendedComponents;

import third.all.data.Properties;
import third.all.utils.ImageAddressses;
import third.all.gameComponents.preGameComponent.IdentifierPanel;
import third.all.gameComponents.preGameComponent.StarterPanel;
import third.all.gameComponents.game.GameFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static third.all.controller.Constants.waveOHB;



public class StoreFrame extends JFrame implements Runnable{
    JButton startButton;
    JButton showAllGames;
    JButton exitButton;
    StarterPanel starterPanel;
    IdentifierPanel identifierPanel;
    JButton OHB;
    JButton OAE;
    JButton OAH;




    public StoreFrame() {

        this.setTitle("WindowKill++ - Store");
        this.setIconImage(new ImageIcon(ImageAddressses.LogoGame).getImage());
        this.setSize(600, 600);

        identifierPanel = new IdentifierPanel();
        identifierPanel.setSize(600, 600);





        //OHB:
        OHB = new JButton("O' Hephaestus، Banish");
        OHB.setBounds(180, 250, 200, 50);
        identifierPanel.add(OHB);

        OHB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Properties.getInstance().XP>=100){
                    waveOHB = true;
                    Properties.getInstance(). XP-=100;
                    GameFrame.play = true;
                }
                dispose();
            }
        });

        // OAE:
        OAE = new JButton("O’ Athena، Empower");
        OAE.setBounds(180, 300, 200, 50);
        identifierPanel.add(OAE);

        OAE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Properties.getInstance().XP>=75){
                    Properties.getInstance().XP-=75;
//                    mechanismOAE= true;
                }
                dispose();
            }
        });

        //OAH:
        OAH = new JButton("O’ Athena، Empower");
        OAH.setBounds(180, 350, 200, 50);
        identifierPanel.add(OAH);

        OAH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Properties.getInstance().XP>=50){
                    Properties.getInstance().XP-=50;
                    Properties.getInstance().HP +=10;
                }
                dispose();
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
