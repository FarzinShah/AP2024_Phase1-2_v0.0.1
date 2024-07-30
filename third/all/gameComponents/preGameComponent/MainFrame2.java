package third.all.gameComponents.preGameComponent;

import third.all.utils.ImageAddressses;
import third.all.gameComponents.extendedComponentsPhase1.SkillTreeFrame;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame2 extends JFrame implements Runnable{
    JButton startButton;
    JButton showAllGames;
    JButton exitButton;
    JButton skillTreeButton;

    StarterPanel starterPanel;



    public MainFrame2(){
        this.setTitle("WindowKill ++");
        this.setIconImage(new ImageIcon(ImageAddressses.LogoGame).getImage());
        this.setSize(600, 600);
        starterPanel = new StarterPanel();
        starterPanel.setSize(600, 600);



        // start a new game
        startButton = new JButton("Start a New Game");
        startButton.setBounds(180, 100, 200, 50);
        starterPanel.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CheckFrame2 checkFrame2 = new CheckFrame2();


            }
        });


        // show recent games (with scroll)
        showAllGames = new JButton("Show Recent Games");
        showAllGames.setBounds(180, 200, 200, 50);
        starterPanel.add(showAllGames);

        showAllGames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoryFrame scoreboardFrame = new HistoryFrame();
            }
        });

        // Settings:
        exitButton = new JButton("Settings");
        exitButton.setBounds(180, 300, 200, 50);
        starterPanel.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Settings settings = new Settings();
            }
        });

        // exit app
        exitButton = new JButton("Exit");
        exitButton.setBounds(180, 400, 200, 50);
        starterPanel.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        skillTreeButton = new JButton("Skill Tree");
        skillTreeButton.setBounds(180, 400, 200, 50);
        starterPanel.add(skillTreeButton);
        skillTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SkillTreeFrame();
                dispose();
            }
        });

        this.setLayout(null);
        starterPanel.setLayout(null);
        this.setContentPane(starterPanel);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    @Override
    public void run() {
        repaint();
        revalidate();
    }
}
