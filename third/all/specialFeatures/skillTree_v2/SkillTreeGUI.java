package third.all.specialFeatures.skillTree_v2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import third.all.data.booleans.BooleansOfEnemies;
import third.all.data.Properties;

import javax.swing.*;
import java.awt.*;

import static third.all.controller.Constants.EPSILON_LENGTH;
import static third.all.controller.Constants.EPSILON_WIDTH;
import static third.all.gameComponents.game.Timers.acesoTimer;

public class SkillTreeGUI {
    private static final Logger logger = LoggerFactory.getLogger(SkillTreeGUI.class);

    JFrame frame;

    JPanel mainPanel;
    JPanel group1Panel;
    JPanel group2Panel;
    JPanel group3Panel;
    JLabel HP_label;

    public SkillTreeGUI() {
        logger.info("SkillTreeGUI is launched.");
        frame = new JFrame("Skill Tree v2.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 3, 10, 10));
        group1Panel = createGroupPanel(frame, 1);
        group2Panel = createGroupPanel(frame, 2);
        group3Panel = createGroupPanel(frame, 3);
        HP_label = new JLabel("HP: " + String.valueOf(Properties.getInstance().HP) + "\n" + " -  XP: " + String.valueOf(Properties.getInstance().XP));
        HP_label.setFont(new Font("Candara", Font.BOLD, 14));
        HP_label.setForeground(Color.BLACK);
        mainPanel.add(HP_label);

        mainPanel.add(group1Panel);
        mainPanel.add(group2Panel);
        mainPanel.add(group3Panel);

        frame.add(mainPanel);
        frame.setVisible(true);

    }


    private static JPanel createGroupPanel(JFrame frame, int opt) {
        JPanel groupPanel = new JPanel();
        String groupName = "";
        groupPanel.setLayout(new GridLayout(4, 1, 10, 10));
        if (opt == 1) {
            groupName = "Attacking";
            JButton button = new JButton("Writ of Ares");
            button.setSize(new Dimension(100, 100));
            button.setForeground(Color.RED);
            groupPanel.add(button);
            JButton button1 = new JButton("Writ of Astrape");
            button1.setSize(new Dimension(100, 100));
            button1.setForeground(Color.RED);
            groupPanel.add(button1);
            JButton button2 = new JButton("Writ of Cerberus");
            button2.setSize(new Dimension(100, 100));
            button2.setForeground(Color.RED);
            groupPanel.add(button2);


            button1.addActionListener(e -> {

                logger.debug("Attacking --> Writ of Astrape");
                if (Properties.getInstance().XP >= 1000) {
                    Properties.getInstance().XP -= 1000;
                    BooleansOfEnemies.getInstance().setWritOfAstrape(true);
                }else {
                    logger.debug("Defensing -isFailed-> Writ of Astrape ");
                }
                frame.dispose();
            });
            button2.addActionListener(e -> {
                if (Properties.getInstance().XP >= 1500) {
                    logger.debug("Attacking --> Writ of Cerberus");
                    Properties.getInstance().XP -= 1500;
                    BooleansOfEnemies.getInstance().setWritOfCerberus(true); //todo: اینو یه جا initialize کنم
                }else {
                    logger.debug("Defensing -isFailed-> Writ of Cerberus ");
                }
                frame.dispose();
            });


        } else if (opt == 2) {
            groupName = "Defensing";
            JButton button = new JButton("Writ of Aceso");
            button.setPreferredSize(new Dimension(100, 100));
            button.setForeground(Color.RED);
            groupPanel.add(button);
            JButton button1 = new JButton("Writ of Melampus");
            button1.setSize(new Dimension(100, 100));
            button1.setForeground(Color.RED);
            groupPanel.add(button1);
            JButton button2 = new JButton("Writ of Chiron");
            button2.setSize(new Dimension(100, 100));
            button2.setForeground(Color.RED);
            groupPanel.add(button2);
            JButton button3 = new JButton("Writ of Athena");
            button3.setSize(new Dimension(100, 100));
            button3.setForeground(Color.RED);
            groupPanel.add(button3);

            button.addActionListener(e -> {
                logger.debug("Defensing --> Writ of Aceso");
                if (Properties.getInstance().XP >= 500) {
                    Properties.getInstance().XP -= 500;
                    acesoTimer.start();
                }else {
                    logger.debug("Defensing -isFailed-> Writ of Aceso ");
                }
                frame.dispose();
            });

            button1.addActionListener(e -> {
                logger.debug("Defensing --> Writ of Melampus");
                if (Properties.getInstance().XP >= 750) {
                    Properties.getInstance().XP -= 750;
                    Properties.getInstance().probabilityOfGettingAttackedEpsilon = 0.9;
                }else {
                    logger.debug("Defensing -isFailed-> Writ of Melampus ");
                }
                frame.dispose();
            });
            button2.addActionListener(e -> {
                logger.debug("Defensing --> Writ of Chiron");
                if (Properties.getInstance().XP >= 900) {
                    Properties.getInstance().XP -= 900;
                    Properties.getInstance().isValidToChiron = true;

                }else {
                    logger.debug("Defensing -isFailed-> Writ of Chiron ");
                }
                frame.dispose();
            });
            button3.addActionListener(e -> {
                if (Properties.getInstance().XP >= 1200) {
                    logger.debug("Defensing --> Writ of Athena");
                    Properties.getInstance().XP -= 1200;
                    Properties.getInstance().constantOfShrinkagePanel =0.8;
                }else {
                    logger.debug("Defensing -isFailed-> Writ of Athena ");

                }
                frame.dispose();
            });


        } else if (opt == 3) {
            groupName = "Epsilon Shape";
            JButton button = new JButton("Writ of Proteus");
            button.setSize(new Dimension(100, 100));
            button.setForeground(Color.RED);
            groupPanel.add(button);
            JButton button1 = new JButton("Writ of Empusa");
            button1.setSize(new Dimension(100, 100));
            button1.setForeground(Color.RED);
            groupPanel.add(button1);
            JButton button2 = new JButton("Writ of Dolus");
            button2.setSize(new Dimension(100, 100));
            button2.setForeground(Color.RED);
            groupPanel.add(button2);
            button.addActionListener(e -> {
                if (Properties.getInstance().XP >= 1000) {
                    logger.debug("Epsilon Shape --> Writ of Proteus");
                    Properties.getInstance().XP -= 1000;
                    Properties.getInstance().edgesOfEpsilon +=1; // todo: خیلی بی مزه هست. کدوم خری میره 1000 تا ایکس پی خرجش کنه خخخخخ

                }else {
                    logger.debug("Epsilon Shape -isFailed-> Writ of Proteus ");
                }
                frame.dispose();
            });
            button1.addActionListener(e -> {
                if (Properties.getInstance().XP >= 120) {
                    logger.debug("Epsilon Shape --> Writ of Empusa");
                    Properties.getInstance().XP -= 120;
                    Properties.getInstance().constantOfShrinkageEpsilon = 0.9;
                    EPSILON_WIDTH = 45; // قبلیه 50 بود
                    EPSILON_LENGTH = 45; // قبلیه 50 بود

                }else {
                    logger.debug("Epsilon Shape -isFailed-> Writ of Empusa ");
                }
                frame.dispose();
            });
            button2.addActionListener(e -> {
                if (Properties.getInstance().XP >= 150) {
                    logger.debug("Epsilon Shape --> Writ of Dolus");
                    Properties.getInstance().XP -= 150;
                    double random = Math.random();
                    if(random<0.5){
                    }else {

                    }

                }else {
                    logger.debug("Epsilon Shape -isFailed-> Writ of Dolus ");
                }
                frame.dispose();
            });



        }

        JLabel groupLabel = new JLabel(groupName, SwingConstants.CENTER);
        groupLabel.setFont(new Font("Candara", Font.BOLD, 14));
        groupLabel.setForeground(Color.BLACK);

        JPanel panelWithLabel = new JPanel(new BorderLayout());
        panelWithLabel.add(groupLabel, BorderLayout.NORTH);
        panelWithLabel.add(groupPanel, BorderLayout.CENTER);
        panelWithLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        return panelWithLabel;
    }


}
