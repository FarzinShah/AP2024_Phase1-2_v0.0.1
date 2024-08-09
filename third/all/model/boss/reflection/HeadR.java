package third.all.model.boss.reflection;

import third.all.model.boss.Head;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HeadR {
    private final Head head;


    public HeadR(Head head) throws IllegalAccessException {
        this.head = head;
        Field[] fields = head.getClass().getDeclaredFields();
        Method[] methods = head.getClass().getDeclaredMethods();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
            if (fields[i].getName().equals("location")) {
                fields[i].setAccessible(true);
                fields[i].set(this.head, new Point(head.getLocation()));
            }
            if (fields[i].getName().equals("size")) {
                fields[i].setAccessible(true);
                fields[i].set(this.head, head.getSize());
            }
            if (fields[i].getName().equals("HP")) {
                fields[i].setAccessible(true);
                fields[i].set(this.head, head.getHP());
            }
        }


    }

    public void setLocation(Point location) throws IllegalAccessException {
        Field[] fields = head.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("location")) {
                field.setAccessible(true);
                field.set(this.head, location);
            }
        }
    }

    public void setSize(int size) throws IllegalAccessException {
        Field[] fields = head.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("size")) {
                field.setAccessible(true);
                field.set(this.head, size);
            }
        }
    }

    public void setHP(int HP) throws IllegalAccessException {
        Field[] fields = head.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("HP")) {
                field.setAccessible(true);
                field.set(this.head, HP);
            }
        }
    }

    public void draw(Graphics g, ImageObserver i) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("draw")) {
                method.setAccessible(true);
                method.invoke(head, g, i);
            }
        }
    }
}
