package third.all.controller;

import java.util.Random;

public class Variables {
    public static int numberOfYellowEnemies = 5;
//    public static Random rng=new Random();
    public static double rng(double min, double max) {
        Random random = new Random();
        return random.doubles(min, max)
                .findFirst()
                .getAsDouble();
    }

}
