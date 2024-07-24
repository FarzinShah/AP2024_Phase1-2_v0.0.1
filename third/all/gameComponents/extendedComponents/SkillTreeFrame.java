package third.all.gameComponents.extendedComponents;

import third.all.data.Properties;
import third.all.utils.ImageAddressses;
import third.all.gameComponents.preGameComponent.IdentifierPanel;
import third.all.gameComponents.preGameComponent.StarterPanel;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static third.all.controller.Constants.is_Writ_Of_Ares;
import static third.all.controller.Constants.is_Writ_Of_Aceso;
import static third.all.controller.Constants.is_Writ_Of_Proteus;

public class SkillTreeFrame extends JFrame implements Runnable{
    JButton startButton;
    JButton showAllGames;
    JButton exitButton;
    StarterPanel starterPanel;
    IdentifierPanel identifierPanel;
    JButton Writ_Of_Ares;
    JButton Writ_Of_Aceso;
    JButton Writ_Of_Proteus;




    public SkillTreeFrame() {

        this.setTitle("WindowKill++ - Skill Tree");
        this.setIconImage(new ImageIcon(ImageAddressses.LogoGame).getImage());
        this.setSize(600, 600);

        identifierPanel = new IdentifierPanel();
        identifierPanel.setSize(600, 600);





        //Writ_Of_Ares:
        Writ_Of_Ares = new JButton("Writ_Of_Ares");
        Writ_Of_Ares.setBounds(180, 250, 200, 50);
        identifierPanel.add(Writ_Of_Ares);

        Writ_Of_Ares.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Properties.getInstance().XP>=750){
                    is_Writ_Of_Ares = true;
                    Properties.getInstance(). XP-=750;
                }
                dispose();
            }
        });

        // Writ_Of_Aceso:
        Writ_Of_Aceso = new JButton("Writ_Of_Aceso");
        Writ_Of_Aceso.setBounds(180, 300, 200, 50);
        identifierPanel.add(Writ_Of_Aceso);

        Writ_Of_Aceso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Properties.getInstance().XP>=500){
                    Properties.getInstance().XP-=500;
                    is_Writ_Of_Aceso = true;
                }
                dispose();
            }
        });

        //Writ_Of_Proteus:
        Writ_Of_Proteus = new JButton("Writ_Of_Proteus");
        Writ_Of_Proteus.setBounds(180, 350, 200, 50);
        identifierPanel.add(Writ_Of_Proteus);

        Writ_Of_Proteus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Properties.getInstance().XP>=1000){
                    is_Writ_Of_Proteus = true;
                    Properties.getInstance().XP=-1000.0;
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
