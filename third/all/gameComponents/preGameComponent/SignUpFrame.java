package third.all.gameComponents.preGameComponent;

import third.all.utils.ImageAddressses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static third.all.gameComponents.preGameComponent.LoginFrame.users;

public class SignUpFrame extends JFrame implements Runnable, KeyListener {
    JButton loginButton = new JButton("login");
    JButton resetButton = new JButton("Reset");
    JButton confirmButton = new JButton("Confirm");
    LoginPanel loginPanel;


    JTextField usernameField = new JTextField();
    JPasswordField passcodeField = new JPasswordField();
    JLabel userIDLabel = new JLabel("Username: ");
    JLabel userPasscodeLabel = new JLabel("Passcode: ");
    JLabel messageLabel = new JLabel();

    SignUpFrame() {
        this.setTitle("WindowKill++");
        this.setIconImage(new ImageIcon(ImageAddressses.LogoGame).getImage());
        this.setSize(600, 600);
        setFocusable(true);
        addKeyListener(this);

        loginPanel = new LoginPanel();
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


        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == confirmButton) {
                    String userID = usernameField.getText();
                    String passcode = String.valueOf(passcodeField.getPassword());

                    if (!users.getUsernameToPasscode().containsKey(userID)) {
                        System.out.println("+");
                        users.addtoList(userID, passcode);
                        messageLabel.setText("Successful!");
                        confirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dispose();
                                MainFrame loginFrame = new MainFrame();
                            }
                        });
                    } else {
                        System.out.println("??");
                        messageLabel.setText("Your userID or password is wrong! Try Again.");
                        usernameField.setText("");
                        passcodeField.setText("");
                    }
                } else {
                    System.out.println("!!");
                    messageLabel.setText("Your userID or password exists! Try Again.");
                    usernameField.setText("");
                    passcodeField.setText("");
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
