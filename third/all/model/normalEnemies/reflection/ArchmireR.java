package third.all.model.normalEnemies.reflection;

import third.all.model.normalEnemies.Archmire;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ArchmireR {
    private final Archmire archmire;


    public ArchmireR(Archmire archmire) throws IllegalAccessException {
        this.archmire = archmire;
        Field[] fields = archmire.getClass().getDeclaredFields();
        Method[] methods = archmire.getClass().getDeclaredMethods();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
            if (fields[i].getName().equals("location")) {
                fields[i].setAccessible(true);
                fields[i].set(this.archmire, new Point(archmire.getLocation()));
            }
            if (fields[i].getName().equals("size")) {
                fields[i].setAccessible(true);
                fields[i].set(this.archmire, archmire.getSize());
            }
            if (fields[i].getName().equals("HP")) {
                fields[i].setAccessible(true);
                fields[i].set(this.archmire, archmire.getHP());
            }
            if (fields[i].getName().equals("trails")) {
                fields[i].setAccessible(true);
                fields[i].set(this.archmire, archmire.getTrails());
            }
        }


    }

    public void setLocation(Point location) throws IllegalAccessException {
        Field[] fields = archmire.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("location")) {
                field.setAccessible(true);
                field.set(this.archmire, location);
            }
        }
    }

    public void setSize(int size) throws IllegalAccessException {
        Field[] fields = archmire.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("size")) {
                field.setAccessible(true);
                field.set(this.archmire, size);
            }
        }
    }

    public void setHP(int HP) throws IllegalAccessException {
        Field[] fields = archmire.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("HP")) {
                field.setAccessible(true);
                field.set(this.archmire, HP);
            }
        }
    }

    public void draw(Graphics g, ImageObserver i) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("draw")) {
                method.setAccessible(true);
                method.invoke(archmire, g, i);
            }
        }
    }
}
