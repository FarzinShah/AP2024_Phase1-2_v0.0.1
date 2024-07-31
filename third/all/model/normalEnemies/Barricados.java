package third.all.model.normalEnemies;

import third.all.data.booleans.BooleansOf_IsValidToShow;

import java.awt.*;
import java.awt.image.ImageObserver;

import static third.all.controller.Constants.BARRICADOS;

public class Barricados extends NormalEnemy implements NormalEnemyModel {
    public static Barricados instance;
    private Point location;
    private int size;


    Barricados(Point location){
        size = 50;
        this.location = location;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }


    @Override
    int getHP() {
        return 0;
    }


    public static Barricados getInstance(){
        if(instance==null) {
            instance = new Barricados(new Point(300, 400));
            return instance;
        }
        return instance;
    }
    public static void draw(Graphics g, ImageObserver i) {
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(4)) {
            g.drawImage(BARRICADOS, Barricados.getInstance().getLocation().x, Barricados.getInstance().getLocation().y, Barricados.getInstance().getSize()+25, Barricados.getInstance().getSize()+25, i);
        }
    }

    public Rectangle getRectangle(){
        return new Rectangle(location.x,location.y,size,size);
    }
    public static void setInstance(Barricados instance1){
        instance = instance1;
    }

}
