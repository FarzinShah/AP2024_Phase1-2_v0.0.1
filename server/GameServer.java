package server;

import third.all.controller.entity.GameObject;
import third.all.model.normalEnemies.NormalEnemy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameServer {
    private static final int PORT = 2345;
    private static Set<ClientHandler> clientHandlers = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcast(List<NormalEnemy> gameObjects) {
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.sendObjects(gameObjects);
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            List<NormalEnemy> gameObjects;
            while ((gameObjects = (List<NormalEnemy>) in.readObject()) != null) {
                System.out.println("Received from client:");
                for (NormalEnemy gameObject : gameObjects) {
//                    System.out.println(" - " + gameObject.getX() + ", " + gameObject.getY());
//                    gameObject.setX(gameObject.getX()-10);
                }
                GameServer.broadcast(gameObjects);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendObjects(List<NormalEnemy> gameObjects) {
        try {
            out.writeObject(gameObjects);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
