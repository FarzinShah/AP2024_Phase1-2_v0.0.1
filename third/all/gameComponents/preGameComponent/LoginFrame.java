package third.all.gameComponents.preGameComponent;

import third.all.utils.ImageAddressses;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class LoginFrame extends JFrame implements Runnable, KeyListener {
    JButton loginButton = new JButton("login");
    JButton resetButton = new JButton("Reset");
    JButton confirmButton = new JButton("Confirm");
    LoginPanel loginPanel;
    static Users users = new Users();
    static String currentPlayerName;
    static LinkedList<PlayersInformation> information = new LinkedList<>();



    JTextField usernameField = new JTextField();
    JPasswordField passcodeField = new JPasswordField();
    JLabel userIDLabel = new JLabel("Username: ");
    JLabel userPasscodeLabel = new JLabel("Passcode: ");
    JLabel messageLabel = new JLabel();

    LoginFrame() {
        this.setTitle("WindowKill++");
        this.setIconImage(new ImageIcon(ImageAddressses.LogoGame).getImage());
        this.setSize(600, 600);
        loginPanel = new LoginPanel();
        setFocusable(true);
        addKeyListener(this);

        loginPanel.setSize(600, 600);

        userIDLabel.setBounds(50, 100, 75, 25);
        loginPanel.add(userIDLabel);

        userPasscodeLabel.setBounds(50, 150, 75, 25);
        loginPanel.add(userPasscodeLabel);

        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));
        loginPanel.add(messageLabel);

        usernameField.setBounds(125, 100, 250, 35);
        loginPanel.add(usernameField);

        passcodeField.setBounds(125, 150, 250, 35);
        loginPanel.add(passcodeField);

        confirmButton.setBounds(175, 200, 100, 25);
        confirmButton.setFocusable(true);
        loginPanel.add(confirmButton);

        confirmButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == confirmButton) {
                    String userID = usernameField.getText();
                    String passcode = String.valueOf(passcodeField.getPassword());
                    for (int i = 0; i < users.getUsernameToPasscode().size(); i++) {
                        System.out.println(users.getUsernameToPasscode().keySet());
                    }
                    if (users.getUsernameToPasscode().get(userID)!=null) {
                        if (users.getUsernameToPasscode().get(userID).equals(passcode)) {
                            System.out.println("+");
                            messageLabel.setText("Successful!");

//                Timer timer = new Timer(40,this);
//                timer.start();
                            confirmButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    currentPlayerName = userID;
                                    information.add(new PlayersInformation(userID,0,""));
                                    dispose();
                                    MainFrame2 loginFrame = new MainFrame2();
                                }
                            });
                        } else {
                            System.out.println("??");
                            messageLabel.setText("Your userID or password is wrong! Try Again.");
                            usernameField.setText("");
                            passcodeField.setText("");
                        }
                    }else {
                        messageLabel.setText("Your userID or password is wrong! Try Again.");
                        usernameField.setText("");
                        passcodeField.setText("");
                    }
                }
            }
        });



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(null);
        loginPanel.setLayout(null);
        this.setContentPane(loginPanel);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        System.out.println(String.valueOf(passcodeField.getPassword()));


    }

    @Override
    public void run() {
        repaint();
        revalidate();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_B){
            dispose();
            new MainFrame();
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            String userID = usernameField.getText();
            String passcode = String.valueOf(passcodeField.getPassword());
            for (int i = 0; i < users.getUsernameToPasscode().size(); i++) {
                System.out.println(users.getUsernameToPasscode().keySet());
            }
            if (users.getUsernameToPasscode().get(userID)!=null) {
                if (users.getUsernameToPasscode().get(userID).equals(passcode)) {
                    System.out.println("+");
                    messageLabel.setText("Successful!");
//                Timer timer = new Timer(40,this);
//                timer.start();
                    confirmButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                            MainFrame2 loginFrame = new MainFrame2();
                        }
                    });
                } else {
                    System.out.println("??");
                    messageLabel.setText("Your userID or password is wrong! Try Again.");
                    usernameField.setText("");
                    passcodeField.setText("");
                }
            }else {
                messageLabel.setText("Your userID or password is wrong! Try Again.");
                usernameField.setText("");
                passcodeField.setText("");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
