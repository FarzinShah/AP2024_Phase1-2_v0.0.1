package second.controller2.entity;

import second.movement.Position;
import second.movement.SizeOfEpsilon;

import java.awt.*;

public abstract class GameObject {
    public Position position;


    public GameObject(double posX,double posY){
        position= new Position(posX,posY);

    }
    public abstract void update();

    public Position getPosition() {
        return position;
    }
//    public Integer getRadius(){
//        return position.intX()
//    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract Image getSprite(); //وقتی این کال بشه میره همه بچه های گیم آبجکت که ازش استفاده کردن رو get میکنه
}
