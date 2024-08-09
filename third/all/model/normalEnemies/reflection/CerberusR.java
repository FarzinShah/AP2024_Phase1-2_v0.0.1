package third.all.model.normalEnemies.reflection;


import third.all.model.normalEnemies.Cerberus;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CerberusR {
    private final Cerberus cerberus;


    public CerberusR(Cerberus cerberus) throws IllegalAccessException {
        this.cerberus = cerberus;
        Field[] fields = cerberus.getClass().getDeclaredFields();
        Method[] methods = cerberus.getClass().getDeclaredMethods();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
            if (fields[i].getName().equals("x")) {
                fields[i].setAccessible(true);
                fields[i].set(this.cerberus, cerberus.getX());
            }
            if (fields[i].getName().equals("size")) {
                fields[i].setAccessible(true);
                fields[i].set(this.cerberus, cerberus.getSize());
            }

        }


    }

    public void setLocation(Point location) throws IllegalAccessException {
        Field[] fields = cerberus.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("location")) {
                field.setAccessible(true);
                field.set(this.cerberus, location);
            }
        }
    }

    public void setSize(int size) throws IllegalAccessException {
        Field[] fields = cerberus.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("size")) {
                field.setAccessible(true);
                field.set(this.cerberus, size);
            }
        }
    }

    public void setHP(int HP) throws IllegalAccessException {
        Field[] fields = cerberus.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("HP")) {
                field.setAccessible(true);
                field.set(this.cerberus, HP);
            }
        }
    }

    public void draw(Graphics g, ImageObserver i) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("draw")) {
                method.setAccessible(true);
                method.invoke(cerberus, g, i);
            }
        }
    }
}
