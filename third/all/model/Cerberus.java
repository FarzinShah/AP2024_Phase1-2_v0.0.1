package third.all.model;

import third.all.controller.entity.EpsilonModel;

import java.awt.*;
import java.util.ArrayList;

import static third.all.controller.Constants.EPSILON_LENGTH;
import static third.all.controller.Constants.EPSILON_WIDTH;
import static third.all.gameComponents.game.GameFrame2.gameObjects;

public class Cerberus {
    public static ArrayList<Cerberus> instance;
    private int x, y, size;

    public Cerberus(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public Cerberus setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Cerberus setY(int y) {
        this.y = y;
        return this;
    }

    public int getSize() {
        return size;
    }

    public Cerberus setSize(int size) {
        this.size = size;
        return this;
    }

    public static ArrayList<Cerberus> getInstance() {
        if (instance == null) {
            instance = new ArrayList<>();
            Rectangle epsilon = new Rectangle((int) ((EpsilonModel) gameObjects.get(0)).getPosition().getX(), (int) (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getY(), EPSILON_WIDTH, EPSILON_LENGTH);
            instance.add(0, new Cerberus(epsilon.x + 100, epsilon.y, 50));
            instance.add(1, new Cerberus(epsilon.x, epsilon.y + 100, 50));
            instance.add(2, new Cerberus(epsilon.x + 100, epsilon.y + 100, 50));
            return instance;
        }
        return instance;
    }

    public static void draw(Graphics g) {
        g.setColor(new Color(0x8C797070, true));
        for (int i = 0; i < Cerberus.getInstance().size(); i++) {
            g.fillOval(Cerberus.getInstance().get(i).x, Cerberus.getInstance().get(i).y, Cerberus.getInstance().get(i).getSize(), Cerberus.getInstance().get(i).getSize());
        }
    }

    public static void setInstance(ArrayList<Cerberus> instance1) {
        instance = instance1;
    }
    public Rectangle getRectangle(){
        return new Rectangle(x,y,size,size);
    }


}
