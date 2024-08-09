package third.all.model.boss.reflection;

import third.all.model.boss.Fist;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FistR {
    private final Fist fist;


    public FistR(Fist fist) throws IllegalAccessException {
        this.fist = fist;
        Field[] fields = fist.getClass().getDeclaredFields();
        Method[] methods = fist.getClass().getDeclaredMethods();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
            if (fields[i].getName().equals("location")) {
                fields[i].setAccessible(true);
                fields[i].set(this.fist, new Point(fist.getLocation()));
            }
            if (fields[i].getName().equals("size")) {
                fields[i].setAccessible(true);
                fields[i].set(this.fist, fist.getSize());
            }
        }


    }

    public void setLocation(Point location) throws IllegalAccessException {
        Field[] fields = fist.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("location")) {
                field.setAccessible(true);
                field.set(this.fist, location);
            }
        }
    }

    public void setSize(int size) throws IllegalAccessException {
        Field[] fields = fist.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("size")) {
                field.setAccessible(true);
                field.set(this.fist, size);
            }
        }
    }

    public void setHP(int HP) throws IllegalAccessException {
        Field[] fields = fist.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("HP")) {
                field.setAccessible(true);
                field.set(this.fist, HP);
            }
        }
    }

    public void draw(Graphics g, ImageObserver i) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("draw")) {
                method.setAccessible(true);
                method.invoke(fist, g, i);
            }
        }
    }
}
