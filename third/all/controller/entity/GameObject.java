package third.all.controller.entity;

import third.all.controller.movement.Position;

import java.awt.*;

public abstract class GameObject {
    public Position position;


    public GameObject(double posX, double posY) {
        position = new Position(posX, posY);

    }

    public abstract void update();

    public Position getPosition() {
        return position;
    }

    //    public Integer getRadius(){
//        return position.intX()
//    }
    public Rectangle getBounds() {
        return new Rectangle((int) position.getX(), (int) position.getY(), 10, 10);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract Image getSprite(); //وقتی این کال بشه میره همه بچه های گیم آبجکت که ازش استفاده کردن رو get میکنه

//
}
