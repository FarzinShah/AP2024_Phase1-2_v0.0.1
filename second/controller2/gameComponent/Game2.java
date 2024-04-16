package second.controller2.gameComponent;

import second.controller2.controlComponent.YellowEnemyController;
import second.controller2.entity.YellowEnemyModel;
import second.display.Display;
import second.controller2.entity.GameObject;
import second.controller2.controlComponent.Input;
import second.controller2.entity.EpsilonModel;
import second.controller2.controlComponent.EpsilonController;
import second.movement.MovementOfYellowEnemy;
import second.movement.Position;
import second.movement.Vector2D;


import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import static second.controller2.gameComponent.GameLoop2.recentExpendedTime;
import static second.controller2.gameComponent.utils.Constant.MIN_PANEL_SIZE;
import static second.controller2.gameComponent.utils.Constant.PANEL_SIZE;
import static second.controller2.gameComponent.utils.Variables.*;

public class Game2{
    private static Display display;
    public static ArrayList<GameObject> gameObjects;
    private Input input;
    public static LinkedList<YellowEnemyModel> yellowEnemies;
    static LinkedList<Polygon> yellowEnemies_triangles = new LinkedList<>();
    private LinkedList<Double> randomXDoubles;
    private LinkedList<Double> randomYDoubles;
    private boolean isImpact1 = true;
    private boolean isImpact2 = false;
    private final LinkedList<Integer> counters = new LinkedList<>();
    private Integer stateCounter;



    //    private Rectangle circle;
    public Game2(Dimension widthHeight){
        input = new Input();
        display = new Display(widthHeight,input);
        gameObjects = new ArrayList<>();
        yellowEnemies = new LinkedList<>();
        randomXDoubles = new LinkedList<>();
        randomYDoubles = new LinkedList<>();
        counters.add(0);
        counters.add(1);
        counters.add(2);
        counters.add(3);
        counters.add(4);
        counters.add(5);
        counters.add(6);
        counters.add(7);
        stateCounter = counters.get(0);
        boolean b =true;

//        gameObjects.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 50, 100)); // این posX و posY نقطه گوشه بالا چپ رو معلوم میکنن.
        gameObjects.add(new EpsilonModel(new EpsilonController(input),230,330));
        for (int i = 0; i < 2; i++) {
            gameObjects.add(new YellowEnemyModel(new YellowEnemyController(input), 20, rng(10,300), rng(10,300))); // این posX و posY نقطه گوشه بالا چپ رو معلوم میکنن.
        }

//        for (int i = 0; i < 1; i++) {
////            double x, y;
////            while (b) {
////                x = rng(0, 350);
////                y = rng(0, 350);
////                for (int j = 0; b && j < randomXDoubles.size(); j++) {
////                    if (!(x < randomXDoubles.get(j) + 20 && y < randomYDoubles.get(j) + 20)) {
////                        b = false;
////                    }
////                    randomXDoubles.add(x);
////                    randomYDoubles.add(y);
////                }
////                System.out.println("??");
////            }
//
//            gameObjects.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 50, 500)); // این posX و posY نقطه گوشه بالا چپ رو معلوم میکنن.
//        }
    }


    public void update(){
        gameObjects.forEach(GameObject::update);
        if( (recentExpendedTime>3.0 && recentExpendedTime<7.0) && (PANEL_SIZE.getWidth()>=MIN_PANEL_SIZE.getWidth()) && (PANEL_SIZE.getHeight()>=MIN_PANEL_SIZE.getHeight()) ){
            PANEL_SIZE.setSize(new Dimension((int) (PANEL_SIZE.getWidth()-1), (int) (PANEL_SIZE.getHeight()-1)));
            System.out.println(PANEL_SIZE.getWidth());
        }
        if(gameObjects.get(1) instanceof YellowEnemyModel){
//            System.out.println();
//            System.out.println("first Xdir: " + ((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getX());
        }
//        for (int i = 1; i < 2; i++) {
//            if(gameObjects.get(i) instanceof YellowEnemyModel){
        // اینجا فقط دارم یکیشون رو به اپسیلون نزدیک می کنم.
                ((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX()-gameObjects.get(1).getPosition().getX(),gameObjects.get(0).getPosition().getY()-gameObjects.get(1).getPosition().getY())));
                ((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().normalize();
//
//            };
//        }
        if(stateCounter == 0){
            gameObjects.get(1).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0,((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getX(),((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getY()));
        }
        if(stateCounter == 1){
            gameObjects.get(1).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0,((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getX(),-((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getY()));
        }
       // System.out.println("x of moving enemy: " + gameObjects.get(1).getPosition().getX()); *****
       //  System.out.println("x of moving epsilon: " + gameObjects.get(0).getPosition().getX()); *****
        if(gameObjects.get(1) instanceof YellowEnemyModel){
            // این پلن جواب نداد :( برای برخورد
//            if(isImpact&&Math.sqrt(((gameObjects.get(0).getPosition().getX()+25)-(gameObjects.get(1).getPosition().getX()+17))*((gameObjects.get(0).getPosition().getX()+25)-(gameObjects.get(1).getPosition().getX()+17)) +
//                    ((gameObjects.get(0).getPosition().getX()+25)-(gameObjects.get(1).getPosition().getY()+30))*((gameObjects.get(0).getPosition().getY()+30)-(gameObjects.get(1).getPosition().getY()+30))) == 45.0 ){
//                ((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().setVector1(new Vector2D(((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getX(),- ((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getY()));
//            isImpact = false;
//            }
//            System.out.println("second Xdir: " + ((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getX());
            //پلن دوم:
            double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
            double centerOfEnemyX = gameObjects.get(1).getPosition().getX() + ((YellowEnemyModel) gameObjects.get(1)).getRadius();
            double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
            double centerOfEnemyY = gameObjects.get(1).getPosition().getY() + ((YellowEnemyModel) gameObjects.get(1)).getRadius();

            if (Math.sqrt( ((centerOfEpsilonX-centerOfEnemyX)*(centerOfEpsilonX-centerOfEnemyX)) + ((centerOfEpsilonY-centerOfEnemyY)*(centerOfEpsilonY-centerOfEnemyY))) <= ( ((EpsilonModel) gameObjects.get(0)).getRadius() + ((YellowEnemyModel) gameObjects.get(1)).getRadius() )){
                System.out.println(":)");
                ((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(0).getPosition().getX()+gameObjects.get(1).getPosition().getX(),-gameObjects.get(0).getPosition().getY()+gameObjects.get(1).getPosition().getY())));
                ((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().normalize();
                stateCounter = counters.get(1);
                gameObjects.get(1).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0,((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getX(),((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getY()));
//                isImpact1 = false;
//                isImpact2 = true;
//                با int counter استیت بندی می کنیم.

            }

        }

//        System.out.println(gameObjects.get(1).getPosition().getX() + " - " + gameObjects.get(1).getPosition().getY());
//        System.out.println(gameObjects.get(0).getPosition().getX() + " - " + gameObjects.get(0).getPosition().getY());

//        yellowEnemies.forEach(YellowEnemyModel::update);
//        for (int i = 1; i < gameObjects.size() ; i++) {
//            gameObjects.get(i).setPosition(new Position(gameObjects.get(i).getPosition().getX(),gameObjects.get(i).getPosition().getX()));
////            gameObjects.get(i).
//        }
//        gameObjects.get(1).setPosition(new Position(gameObjects.get(1).getPosition().getX(),gameObjects.get(1).getPosition().getY()));

//        gameObjects.get(1).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0.000005));
//        gameObjects.get(1).setPosition(new Position(gameObjects.get(0).getPosition().getX()+((YellowEnemyModel)gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getX(),gameObjects.get(0).getPosition().getY()+((YellowEnemyModel)gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getY()));
//        gameObjects.get(1).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0.000005));

//        circle.setLocation((int) (circle.getX()+1), (int) (circle.getY()+1));
    }
    public void render(){
        display.render(this);
    }

    public static Display getDisplay() {
        return display;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }
}
