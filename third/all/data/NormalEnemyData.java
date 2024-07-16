package third.all.data;

import java.awt.*;

public class NormalEnemyData {
    public Point location;
    public int size;
    public int HP;
    public int radius;


    public Point getLocation() {
        return location;
    }

    public NormalEnemyData setLocation(Point location) {
        this.location = location;
        return this;
    }

    public int getSize() {
        return size;
    }

    public NormalEnemyData setSize(int size) {
        this.size = size;
        return this;
    }

    public int getHP() {
        return HP;
    }

    public NormalEnemyData setHP(int HP) {
        this.HP = HP;
        return this;
    }

    public int getRadius() {
        return radius;
    }

    public NormalEnemyData setRadius(int radius) {
        this.radius = radius;
        return this;
    }
}
