package third.all.model.boss.reflection;

import third.all.model.boss.Head;
import third.all.model.boss.LeftHand;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LeftHandR {
    private final LeftHand leftHand;


    public LeftHandR(LeftHand leftHand) throws IllegalAccessException {
        this.leftHand = leftHand;
        Field[] fields = leftHand.getClass().getDeclaredFields();
        Method[] methods = leftHand.getClass().getDeclaredMethods();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
            if (fields[i].getName().equals("location")) {
                fields[i].setAccessible(true);
                fields[i].set(this.leftHand, new Point(leftHand.getLocation()));
            }
            if (fields[i].getName().equals("size")) {
                fields[i].setAccessible(true);
                fields[i].set(this.leftHand, leftHand.getSize());
            }
            if (fields[i].getName().equals("HP")) {
                fields[i].setAccessible(true);
                fields[i].set(this.leftHand, leftHand.getHP());
            }
        }


    }

    public void setLocation(Point location) throws IllegalAccessException {
        Field[] fields = leftHand.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("location")) {
                field.setAccessible(true);
                field.set(this.leftHand, location);
            }
        }
    }

    public void setSize(int size) throws IllegalAccessException {
        Field[] fields = leftHand.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("size")) {
                field.setAccessible(true);
                field.set(this.leftHand, size);
            }
        }
    }

    public void setHP(int HP) throws IllegalAccessException {
        Field[] fields = leftHand.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("HP")) {
                field.setAccessible(true);
                field.set(this.leftHand, HP);
            }
        }
    }

    public void draw(Graphics g, ImageObserver i) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("draw")) {
                method.setAccessible(true);
                method.invoke(leftHand, g, i);
            }
        }
    }
}
