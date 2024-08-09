package client;

import third.all.model.normalEnemies.NormalEnemy;
import view.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 2345;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameClient::new);
    }

    public GameClient() {
        connectToServer();
    }

    private void connectToServer() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            new Thread(new ServerListener()).start();
            JOptionPane.showMessageDialog(GameFrame.getINSTANCE(), "Connected to the server!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(GameFrame.getINSTANCE(), "Failed to connect to the server.");
            int option = JOptionPane.showConfirmDialog(GameFrame.getINSTANCE(), "Try again?", "Connection Failed", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                connectToServer();
            } else {
                JOptionPane.showMessageDialog(GameFrame.getINSTANCE(), "Running in offline mode.");
            }
        }
    }

    private class ServerListener implements Runnable {
        @Override
        public void run() {
            try {
                List<NormalEnemy> gameObjects;
                while ((gameObjects = (List<NormalEnemy>) in.readObject()) != null) {
                    System.out.println("Received from server:");

                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private class GamePanel extends JPanel {
        private List<NormalEnemy> gameObjects = new ArrayList<>();

        public GamePanel() {
            setFocusable(true);

            repaint();
            if (out != null) {
                try {
                    out.writeObject(gameObjects);
                    out.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    ;

    // افزودن چند شیء به لیست به عنوان نمونه
}

