package third.all.model.normalEnemies.reflection;

import third.all.model.normalEnemies.Archmire;
import third.all.model.normalEnemies.Barricados;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BarricadosR {
    private final Barricados barricados;


    public BarricadosR(Barricados barricados) throws IllegalAccessException {
        this.barricados = barricados;
        Field[] fields = barricados.getClass().getDeclaredFields();
        Method[] methods = barricados.getClass().getDeclaredMethods();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
            if (fields[i].getName().equals("location")) {
                fields[i].setAccessible(true);
                fields[i].set(this.barricados, new Point(barricados.getLocation()));
            }
            if (fields[i].getName().equals("size")) {
                fields[i].setAccessible(true);
                fields[i].set(this.barricados, barricados.getSize());
            }
            if (fields[i].getName().equals("HP")) {
                fields[i].setAccessible(true);
                fields[i].set(this.barricados, barricados.getHP());
            }
        }


    }

    public void setLocation(Point location) throws IllegalAccessException {
        Field[] fields = barricados.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("location")) {
                field.setAccessible(true);
                field.set(this.barricados, location);
            }
        }
    }

    public void setSize(int size) throws IllegalAccessException {
        Field[] fields = barricados.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("size")) {
                field.setAccessible(true);
                field.set(this.barricados, size);
            }
        }
    }

    public void setHP(int HP) throws IllegalAccessException {
        Field[] fields = barricados.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.getName().equals("HP")) {
                field.setAccessible(true);
                field.set(this.barricados, HP);
            }
        }
    }

    public void draw(Graphics g, ImageObserver i) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("draw")) {
                method.setAccessible(true);
                method.invoke(barricados, g, i);
            }
        }
    }
}
