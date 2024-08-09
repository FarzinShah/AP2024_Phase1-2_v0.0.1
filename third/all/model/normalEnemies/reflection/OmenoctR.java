package third.all.model.normalEnemies.reflection;

import third.all.model.normalEnemies.Necropick;
import third.all.model.normalEnemies.Omenoct;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OmenoctR {
    private final Omenoct omenoct;


    public OmenoctR(Omenoct omenoct) throws IllegalAccessException {
        this.omenoct = omenoct;
        Field[] fields = omenoct.getClass().getDeclaredFields();
        Method[] methods = omenoct.getClass().getDeclaredMethods();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
            if (fields[i].getName().equals("location")) {
                fields[i].setAccessible(true);
                fields[i].set(this.omenoct, new Point(omenoct.getLocation()));
            }
            if (fields[i].getName().equals("size")) {
                fields[i].setAccessible(true);
                fields[i].set(this.omenoct, omenoct.getSize());
            }
            if (fields[i].getName().equals("HP")) {
                fields[i].setAccessible(true);
                fields[i].set(this.omenoct, omenoct.getHP());
            }
        }


    }

    public void setLocation(Point location) throws IllegalAccessException {
        Field[] fields = omenoct.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("location")) {
                field.setAccessible(true);
                field.set(this.omenoct, location);
            }
        }
    }

    public void setSize(int size) throws IllegalAccessException {
        Field[] fields = omenoct.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("size")) {
                field.setAccessible(true);
                field.set(this.omenoct, size);
            }
        }
    }

    public void setHP(int HP) throws IllegalAccessException {
        Field[] fields = omenoct.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("HP")) {
                field.setAccessible(true);
                field.set(this.omenoct, HP);
            }
        }
    }

    public void draw(Graphics g, ImageObserver i) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("draw")) {
                method.setAccessible(true);
                method.invoke(omenoct, g, i);
            }
        }
    }
}
