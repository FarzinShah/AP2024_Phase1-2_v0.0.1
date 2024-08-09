package third.all.model.normalEnemies.reflection;
import third.all.model.normalEnemies.Wyrm;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WyrmR {
    private final Wyrm wyrm;


    public WyrmR(Wyrm wyrm) throws IllegalAccessException {
        this.wyrm = wyrm;
        Field[] fields = wyrm.getClass().getDeclaredFields();
        Method[] methods = wyrm.getClass().getDeclaredMethods();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
            if (fields[i].getName().equals("location")) {
                fields[i].setAccessible(true);
                fields[i].set(this.wyrm, new Point(wyrm.getLocation()));
            }
            if (fields[i].getName().equals("size")) {
                fields[i].setAccessible(true);
                fields[i].set(this.wyrm, wyrm.getSize());
            }
            if (fields[i].getName().equals("HP")) {
                fields[i].setAccessible(true);
                fields[i].set(this.wyrm, wyrm.getHP());
            }
        }


    }

    public void setLocation(Point location) throws IllegalAccessException {
        Field[] fields = wyrm.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("location")) {
                field.setAccessible(true);
                field.set(this.wyrm, location);
            }
        }
    }

    public void setSize(int size) throws IllegalAccessException {
        Field[] fields = wyrm.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("size")) {
                field.setAccessible(true);
                field.set(this.wyrm, size);
            }
        }
    }

    public void setHP(int HP) throws IllegalAccessException {
        Field[] fields = wyrm.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("HP")) {
                field.setAccessible(true);
                field.set(this.wyrm, HP);
            }
        }
    }

    public void draw(Graphics g, ImageObserver i) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("draw")) {
                method.setAccessible(true);
                method.invoke(wyrm, g, i);
            }
        }
    }
}
