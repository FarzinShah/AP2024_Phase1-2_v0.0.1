package third.all.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import third.all.data.BooleansOfEnemies;
import third.all.data.Properties;
import third.all.gameComponents.game.GameFrame2;

import javax.swing.*;
import java.awt.*;

import static third.all.controller.Constants.EPSILON_LENGTH;
import static third.all.controller.Constants.EPSILON_WIDTH;

public class Shopping {
    private static final Logger logger = LoggerFactory.getLogger(Shopping.class);

    JFrame frame;

    JPanel mainPanel;

    JLabel HP_label;

    public Shopping() {
        logger.info("Shopping store is launched.");
        frame = new JFrame("Store");
        frame.setSize(600, 600);
        mainPanel = new JPanel();
        JPanel group2Panel;
        JPanel group3Panel;

        mainPanel.setLayout(new GridLayout(1, 3, 10, 10));
        group2Panel = createGroupPanel(frame, 2);
        group3Panel = createGroupPanel(frame, 3);

        HP_label = new JLabel("HP: " + String.valueOf(Properties.getInstance().HP) + "\n" + " -  XP: " + String.valueOf(Properties.getInstance().XP));
        HP_label.setFont(new Font("Candara", Font.BOLD, 14));
        HP_label.setForeground(Color.BLACK);
        mainPanel.add(HP_label);

        mainPanel.add(group2Panel);
        mainPanel.add(group3Panel);

        frame.add(mainPanel);
        frame.setVisible(true);

    }


    private static JPanel createGroupPanel(JFrame frame, int opt) {
        JPanel groupPanel = new JPanel();
        String groupName = "";
        groupPanel.setLayout(new GridLayout(3, 1, 10, 10));
        if (opt == 2) {
            groupName = "Phase 1";
            JButton button = new JButton("O’ Hephaestus, Banish");
            button.setPreferredSize(new Dimension(100, 100));
            button.setForeground(Color.RED);
            groupPanel.add(button);
            JButton button1 = new JButton("O’ Athena Empower");
            button1.setSize(new Dimension(100, 100));
            button1.setForeground(Color.RED);
            groupPanel.add(button1);
            JButton button2 = new JButton("O' Apollo Heal");
            button2.setSize(new Dimension(100, 100));
            button2.setForeground(Color.RED);
            groupPanel.add(button2);

            button.addActionListener(e -> {
                logger.debug("Phase 1 --> O’ Hephaestus, Banish");
                if (Properties.getInstance().XP >= 100) {
                    Properties.getInstance().XP -= 100;
                    Properties.getInstance().isValidHephaestus = true;
                    Properties.getInstance().play = true;
                } else {
                    logger.debug("Phase 1 -isFailed-> O’ Hephaestus, Banish");
                }
             Properties.getInstance().play = true;

            frame.dispose();

            });

            button1.addActionListener(e -> {
                logger.debug("Phase 1 --> O’ Athena Empower");
                if (Properties.getInstance().XP >= 75) {
                    Properties.getInstance().XP -= 75;
                    Properties.getInstance().isValidAthena = true;
                } else {
                    logger.debug("Phase 1 -isFailed-> O' Athena Empower ");
                }
                Properties.getInstance().play = true;

                frame.dispose();
            });
            button2.addActionListener(e -> {
                logger.debug("Phase 1 --> O’ Apollo Heal");
                if (Properties.getInstance().XP >= 50) {
                    Properties.getInstance().XP -= 50;
                    Properties.getInstance().HP += 10;

                } else {
                    logger.debug("Phase 1 -isFailed-> O' Apollo Heal ");
                }
                Properties.getInstance().play = true;

                frame.dispose();
            });


        } else if (opt == 3) {
            groupName = "Phase 2";
            JButton button = new JButton("O’ Deimos, Dismay");
            button.setSize(new Dimension(100, 100));
            button.setForeground(Color.RED);
            groupPanel.add(button);
            JButton button1 = new JButton("O’Hypnos, Slumber");
            button1.setSize(new Dimension(100, 100));
            button1.setForeground(Color.RED);
            groupPanel.add(button1);
            JButton button2 = new JButton("O’ Phonoi, Slaughter");
            button2.setSize(new Dimension(100, 100));
            button2.setForeground(Color.RED);
            groupPanel.add(button2);
            button.addActionListener(e -> {
                if (Properties.getInstance().XP >= 120) {
                    logger.debug("Phase 2 --> O’ Deimos, Dismay");
                    Properties.getInstance().XP -= 120;
                    Properties.getInstance().isValidDeimos = true;


                } else {
                    logger.debug("Phase 2 -isFailed-> Writ of Empusa ");
                }
                Properties.getInstance().play = true;

                frame.dispose();
            });
            button1.addActionListener(e -> {
                if (Properties.getInstance().XP >= 150) {
                    logger.debug("Phase 2 --> O’Hypnos, Slumber");
                    Properties.getInstance().XP -= 150;
                    Properties.getInstance().isValidSlumber = true;


                } else {
                    logger.debug("Phase 2 -isFailed-> O’Hypnos, Slumber ");
                }
                Properties.getInstance().play = true;

                frame.dispose();
            });
            button2.addActionListener(e -> {
                if (Properties.getInstance().XP >= 200) {
                    logger.debug("Phase 2 --> O’ Phonoi, Slaughter");
                    Properties.getInstance().XP -= 200;
                    Properties.getInstance().isValidSlaughter = true;


                } else {
                    logger.debug("Phase 2 -isFailed-> O’ Phonoi, Slaughter ");
                }
                Properties.getInstance().play = true;

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
