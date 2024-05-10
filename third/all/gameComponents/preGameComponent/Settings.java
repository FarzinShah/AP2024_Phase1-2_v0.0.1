package third.all.gameComponents.preGameComponent;

import third.all.utils.ImageAddressses;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Settings implements KeyListener, Runnable, ChangeListener {

    private Color selectedColor;
    private int sizeOfBall;
    //    LoginPanel loginPanel;
    JButton confirmButton = new JButton("Confirm");
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();

    JLabel messageLabel = new JLabel();
    JLabel messageLabel2 = new JLabel();
    JLabel messageLabel3 = new JLabel();

    JPanel panel;

    JSlider slider1;
    JSlider slider2;
    JSlider slider3;
    JFrame frame = new JFrame("Slider");


    public static InformationsOfSettings informationsOfSettings = new InformationsOfSettings();

//
    JButton playButton = new JButton("Play");
    JButton muteButton = new JButton("Mute");


    JLabel chooseColor = new JLabel("Coler of Balls: ");
    JLabel selectSize = new JLabel("Size of Balls: ");
    JLabel pointer = new JLabel("Pointer: ");
    JLabel playMusic = new JLabel("Music: ");
    JLabel paddle = new JLabel("Volume: ");


    public Settings() {
        frame.setTitle("Window Kill ++");
        frame.setIconImage(new ImageIcon(ImageAddressses.LogoGame).getImage());
        panel = new JPanel();
        panel.setSize(1700, 700);
        slider1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider2 = new JSlider(JSlider.HORIZONTAL, 1, 3, 2); // acceleration.
        slider3 = new JSlider(JSlider.HORIZONTAL, 1, 3, 2); // level of hardness.

//        slider1.setPreferredSize(new Dimension(400,200));
//        slider2.setPreferredSize(new Dimension(400,200));
        slider1.setBounds(0, 50, 400, 200);
        slider2.setBounds(600, 50, 400, 200);
        slider3.setBounds(600, 50, 400, 200);

        slider1.setPaintTicks(true);
        slider1.setMinorTickSpacing(10);
        slider2.setPaintTicks(true);
        slider2.setMinorTickSpacing(1);
        slider3.setPaintTicks(true);
        slider3.setMinorTickSpacing(1);

        slider1.setPaintTrack(true);
        slider1.setMajorTickSpacing(25);
        slider2.setPaintTrack(true);
        slider2.setMajorTickSpacing(2);
        slider3.setPaintTrack(true);
        slider3.setMajorTickSpacing(2);

        slider1.setPaintLabels(true);
        slider1.setFont(new Font("MV Boli", Font.PLAIN, 15));
        label1.setFont(new Font("MV Boli", Font.PLAIN, 25));
        label1.setText("Volume: " + slider1.getValue());
//        label1.setLocation(slider1.getX(),slider1.getY()+50);


        slider2.setPaintLabels(true);
        slider2.setFont(new Font("MV Boli", Font.PLAIN, 15));
        label2.setFont(new Font("MV Boli", Font.PLAIN, 25));
        label2.setText("Acceleration of enemy: " + slider2.getValue());
        slider3.setPaintLabels(true);
        slider3.setFont(new Font("MV Boli", Font.PLAIN, 15));
        label3.setFont(new Font("MV Boli", Font.PLAIN, 25));
        label3.setText("Level of Hardness: " + slider3.getValue());


        slider1.addChangeListener(this);
        slider2.addChangeListener(this);
        slider3.addChangeListener(this);


        panel.add(slider1);
        panel.add(label1);

        panel.add(slider2);
        panel.add(label2);

        panel.add(slider3);
        panel.add(label3);

        confirmButton.setBounds(400, 460, 100, 25);
        confirmButton.setFocusable(true);
        panel.add(confirmButton);

        playButton.setBounds(250, 100, 100, 25);
        playButton.setFocusable(true);
        panel.add(playButton);
        muteButton.setBounds(350, 100, 100, 25);
        muteButton.setFocusable(true);
        panel.add(muteButton);

        messageLabel.setBounds(375, 260, 300, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));
        panel.add(messageLabel);

        messageLabel2.setBounds(375, 50, 300, 35);
        messageLabel2.setFont(new Font("Arial", Font.ITALIC, 25));
        panel.add(messageLabel2);
        System.out.println("x slider1: " + slider1.getLocation().x + " y slider1:  " + slider1.getLocation().y);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == playButton) {
                    informationsOfSettings.setPlay(true);
                }
            }


        });
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == muteButton) {
                    informationsOfSettings.setPlay(false);
                }
            }


        });
//
//        yesPointerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == yesPointerButton) {
//                    informationsOfSettings.setTherePointer(true);
//                }
//            }
//
//
//        });
//
//        noPointerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == noPointerButton) {
//                    informationsOfSettings.setTherePointer(false);
//                }
//            }
//
//
//        });
//
//        volumePlus.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == volumePlus) {
//                    gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//                    gainControl.setValue(gainControl.getValue()+5);
//                    messageLabel2.setText(String.valueOf(gainControl.getValue()));
//
//                }
//            }
//
//
//        });
//
//        volumeMinus.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == volumeMinus) {
//                    gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//                    gainControl.setValue(gainControl.getValue()-5);
//                    messageLabel2.setText(String.valueOf(gainControl.getValue()));
//                }
//            }
//        });


        System.out.println("colorisset");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == confirmButton) {
                    InformationsOfSettings.setVolume(slider1.getValue());
                    informationsOfSettings.setSizeOfBalls(sizeOfBall);
                        informationsOfSettings.setColorOfBalls(selectedColor);
                        messageLabel.setText("Successful!");

                        confirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                frame.dispose();
                                MainFrame2 loginFrame = new MainFrame2();
                            }
                        });

                }
            }
        });


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);
        frame.setSize(335, 500);
        frame.setVisible(true);

    }


    public int getSizeOfBall() {
        return sizeOfBall;
    }

    public void setSizeOfBall(int sizeOfBall) {
        this.sizeOfBall = sizeOfBall;
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
//        repaint();
//        revalidate();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        label1.setText("Volume: " + String.valueOf(slider1.getValue()));
        label2.setText("Acceleration of enemy: " + String.valueOf(slider2.getValue()));
        label3.setText("Level of Hardness: " + String.valueOf(slider3.getValue()));

    }
}
