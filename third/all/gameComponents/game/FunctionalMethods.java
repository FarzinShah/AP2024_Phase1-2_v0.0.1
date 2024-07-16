package third.all.gameComponents.game;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import third.all.controller.componentController.Input;
import third.all.controller.componentController.YellowEnemyController;
import third.all.controller.entity.EpsilonModel;
import third.all.controller.entity.GreenEnemyModel;
import third.all.controller.entity.YellowEnemyModel;
import third.all.controller.movement.MovementOfGreenEnemy;
import third.all.controller.movement.MovementOfYellowEnemy;
import third.all.controller.movement.Position;
import third.all.controller.movement.Vector2D;
import third.all.data.*;
import third.all.model.*;

import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.Area;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import static third.all.controller.Constants.*;
import static third.all.data.Booleans.isCollidedG;
import static third.all.data.Booleans.isCollidedY;
import static third.all.gameComponents.game.GameFrame2.*;
import static third.all.gameComponents.game.MyPanel.timerOfGame;
import static third.all.gameComponents.game.Timers.addBlackOrbsTimer;
import static third.all.gameComponents.preGameComponent.Timer1.*;

public class FunctionalMethods {
    private static final Logger logger = LoggerFactory.getLogger(FunctionalMethods.class);

    public static FunctionalMethods instance;


    public static void showOptionPane() {

//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        JFrame optionFrame = new JFrame("Options");
        JPanel optionPanel = new JPanel();
        JButton button1 = new JButton("Save");
        JButton button2 = new JButton("Continue");
        Properties.getInstance().play = false;

//        Thread redCharacterThread = new Thread(() -> {
//            try {
//                Thread.sleep(0); // Sleep for 30 milliseconds
//                play =false;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        button1.addActionListener(e -> {
            System.out.println("Button 1 pressed");
            saveGameState();
            //           ((EpsilonModel)gameObjects.get(0)).getMovementOfEpsilon().setSpeed(2);
            //           ((EpsilonModel)gameObjects.get(0)).getMovementOfEpsilon().setVector(new Vector2D(-((EpsilonModel)gameObjects.get(0)).getMovementOfEpsilon().getVector().getX(),-((EpsilonModel)gameObjects.get(0)).getMovementOfEpsilon().getVector().getY()));
//            ((EpsilonModel)gameObjects.get(0)).setMovementOfEpsilon(new MovementOfEpsilon(((EpsilonModel)gameObjects.get(0)).getMovementOfEpsilon().setSpeed();));
            Properties.getInstance().play = true;
            optionFrame.dispose();
        });
        button2.addActionListener(e -> {
            System.out.println("Button 2 pressed");
            Properties.getInstance().play = true;
            Properties.getInstance().XP += (double) (0.1 * Properties.getInstance().PR);
            optionFrame.dispose();

        });

        optionPanel.add(button1);
        optionPanel.add(button2);
        optionFrame.add(optionPanel);
        optionFrame.setSize(200, 100);
        optionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionFrame.setVisible(true);
    }

    static void saveGameState() {
        logger.debug("Game state is saved! :)))");
        NormalEnemyData omenoctEnemyData = new NormalEnemyData()
                .setHP(Omenoct.getInstance().getHP())
                .setSize(Omenoct.getInstance().getSize())
                .setRadius(Omenoct.getInstance().getRadius())
                .setLocation(Omenoct.getInstance().getLocation());
        NormalEnemyData necropickEnemyData = new NormalEnemyData()
                .setHP(Necropick.getInstance().getHP())
                .setSize(Necropick.getInstance().getSize())
                .setLocation(Necropick.getInstance().getLocation());
        List<EnemyState> enemyStatesY = yellowEnemies1.stream()
                .map(shot -> new EnemyState.Builder()
                        .setPosX(shot.getPosition().getX())
                        .setPosY(shot.getPosition().getY())
                        .setRadius(((YellowEnemyModel) shot).getRadius())
                        .build())
                .collect(Collectors.toList());


        List<EnemyDirState> enemyDirStatesY = yellowEnemies1.stream()
                .map(shot -> new EnemyDirState.Builder()
                        .setDir(shot.getMovementOfYellowEnemy().getVector1())
                        .build())
                .collect(Collectors.toList());

        GameState gameState = new GameState.Builder()
                .setElapsedTime(elapsedTime)
                .setHours(timerOfGame.hours)
                .setMinutes(timerOfGame.minutes)
                .setSeconds(timerOfGame.seconds)
                .setSpentMilliSecond(spentMilliSecond)
                .setSpentMilliSecondW2(spentMilliSecondW2)
                .setSpentMilliSecondW3(spentMilliSecondW3)
                .setSpentMilliSecondW4(spentMilliSecondW4)
                .setSpentMilliSecondW5(spentMilliSecondW5)
                .setEnemyStateList(enemyStatesY)
                .setEnemyDirStateList(enemyDirStatesY)
                .setOmenoctEnemyData(omenoctEnemyData)
                .setNecropickEnemyData(necropickEnemyData)
                .setBooleansOfIsValidToShow(BooleansOf_IsValidToShow.getInstance())
                .setBooleansOfEnemies(BooleansOfEnemies.getInstance())
                .setBooleansOfCollectibles(BooleansOfCollectibles.getInstance())
                .setEpsilonLocation(new Point((int) gameObjects.get(0).getPosition().getX(), (int) gameObjects.get(0).getPosition().getY()))
                .setAddBlackOrbs(boHelper)
                .setPanelsData(PanelsData.getInstance())
                .setProperties(Properties.getInstance())
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(gameState);

        try (FileWriter writer = new FileWriter(SAVING_DATA_PATH)) {
            writer.write(json);
        } catch (IOException e) {
            logger.error("Saving of Game state is failed :(((");

            e.printStackTrace();
        }
    }

    static void loadGameState(Input input) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(SAVING_DATA_PATH)) {
            Type gameStateType = new TypeToken<GameState>() {
            }.getType();
            Type omenoctEnemyDataType = new TypeToken<NormalEnemyData>() {
            }.getType();

            GameState gameState = gson.fromJson(reader, gameStateType);
            NormalEnemyData omenoctEnemyData = gson.fromJson(reader, omenoctEnemyDataType);

            elapsedTime = gameState.elapsedTime;
            timerOfGame.hours = gameState.hours;
            timerOfGame.minutes = gameState.minutes;
            timerOfGame.seconds = gameState.seconds;
            spentMilliSecond = gameState.spentMilliSecond;
            spentMilliSecondW2 = gameState.spentMilliSecondW2;
            spentMilliSecondW3 = gameState.spentMilliSecondW3;
            spentMilliSecondW4 = gameState.spentMilliSecondW4;
            spentMilliSecondW5 = gameState.spentMilliSecondW5;
            yellowEnemies1 = gameState.enemyStateList.stream()
                    .map(state -> new YellowEnemyModel(new YellowEnemyController(input), state.radius, state.posX, state.posY))
                    .collect(Collectors.toList());

            for (int i = 0; i < yellowEnemies1.size(); i++) {
                yellowEnemies1.get(i).getMovementOfYellowEnemy().setVector1(gameState.enemyDirStateList.stream()
                        .map((state -> state.dir))
                        .collect(Collectors.toList()).get(i));
                ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
                if (isCollidedY.get(i))
                    yellowEnemies1.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, -((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getX(), -((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getY()));

            }

            Omenoct.getInstance().setLocation(gameState.omenoctEnemyData.location);
            Omenoct.getInstance().setHP(gameState.omenoctEnemyData.HP);
            Omenoct.getInstance().setSize(gameState.omenoctEnemyData.size);
            Necropick.getInstance().setSize(gameState.necropickEnemyData.size);
            Necropick.getInstance().setHP(gameState.necropickEnemyData.HP);
            Necropick.getInstance().setLocation(gameState.necropickEnemyData.location);
            BooleansOf_IsValidToShow.setInstance(gameState.booleansOfIsValidToShow);
            BooleansOfEnemies.setInstance(gameState.booleansOfEnemies);
            BooleansOfCollectibles.setInstance(gameState.booleansOfCollectibles);
            gameObjects.get(0).setPosition(new Position(gameState.epsilonLocation.x, gameState.epsilonLocation.y));
            boHelper = gameState.addBlackOrbs;
            if (boHelper[1] > 0) {
                addBlackOrbsTimer.restart();
            }
            PanelsData.setInstance(gameState.panelsData);
            Properties.setInstance(gameState.properties);

            logger.debug("Game state is loaded! :)))");

        } catch (IOException e) {
            logger.error("Loading the state is come failed! :((((");

            e.printStackTrace();
        }
    }

    public static void omenoct_shooter() {
        bulletsOfOmenoct.add(new Bullet(Omenoct.getInstance().getLocation().x, Omenoct.getInstance().getLocation().y,
                (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getX(),
                (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getY(), Color.ORANGE));

    }

    public static void wyrmShoot() {
        bulletsOfWyrm.add(new Bullet(Wyrm.getInstance().getLocation().x + 10, Wyrm.getInstance().getLocation().y,
                (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getX(),
                (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getY(), Color.MAGENTA));

    }

    public static void writOfAstrape() {
        Rectangle epsilon = new Rectangle((int) ((EpsilonModel) gameObjects.get(0)).getPosition().getX(), (int) (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getY(), EPSILON_WIDTH, EPSILON_LENGTH);
        for (YellowEnemyModel yellowEnemyModel : yellowEnemies1) {
            double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
            double centerOfEnemyX = yellowEnemyModel.getPosition().getX() + ((YellowEnemyModel) yellowEnemyModel).getRadius();
            double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
            double centerOfEnemyY = yellowEnemyModel.getPosition().getY() + ((YellowEnemyModel) yellowEnemyModel).getRadius();
            if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) < (((EpsilonModel) gameObjects.get(0)).getRadius() + ((YellowEnemyModel) yellowEnemyModel).getRadius())) {
                yellowEnemyModel.setLifeValue(yellowEnemyModel.getLifeValue() - 2);
            }
        }
        for (third.all.controller.entity.GameObject gameObject : greenEnemies1) {
            double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
            double centerOfEnemyX = gameObject.getPosition().getX() + ((GreenEnemyModel) gameObject).getRadius();
            double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
            double centerOfEnemyY = gameObject.getPosition().getY() + ((GreenEnemyModel) gameObject).getRadius();
            if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) < (((EpsilonModel) gameObjects.get(0)).getRadius() + ((GreenEnemyModel) gameObject).getRadius())) {
                ((GreenEnemyModel) gameObject).setLifeValue(((GreenEnemyModel) gameObject).getLifeValue() - 2);
            }
        }
        if (epsilon.intersects(Omenoct.getInstance().getRectangle())) {
            Omenoct.getInstance().setHP(Omenoct.getInstance().getHP() - 2);
        }
        if (epsilon.intersects(Necropick.getInstance().getRectangle())) {
            Necropick.getInstance().setHP(Necropick.getInstance().getHP() - 2);
        }
        if (epsilon.intersects(Necropick.getInstance().getRectangle())) {
            Necropick.getInstance().setHP(Necropick.getInstance().getHP() - 2);
        }
        if (epsilon.intersects(Wyrm.getInstance().getRectangle())) {
            Wyrm.getInstance().setHP(Wyrm.getInstance().getHP() - 2);
        }
        for (int i = 0; i < Orb.getInstance().size(); i++) {
            if (epsilon.intersects(Orb.getInstance().get(i).getRectangle())) {
                Orb.getInstance().get(i).setHP(Orb.getInstance().get(i).getHP() - 2);
            }
        }

    }


    public static void writOfCerberus() {
        for (int j = 0; j < Cerberus.getInstance().size(); j++) {
            for (int i = 0; i < yellowEnemies1.size(); i++) {
                if (Cerberus.getInstance().get(i).getRectangle().contains(yellowEnemies1.get(i).getBounds())) {
                    yellowEnemies1.get(i).setLifeValue(yellowEnemies1.get(i).getLifeValue() - 10);
                    BooleansOf_IsValidToShow.getInstance().setValidToAttackCerberus(false);
                }
            }
            for (int i = 0; i < greenEnemies1.size(); i++) {
                if (Cerberus.getInstance().get(i).getRectangle().contains(greenEnemies1.get(i).getBounds())) {
                    ((GreenEnemyModel)greenEnemies1.get(i)).setLifeValue(((GreenEnemyModel)greenEnemies1.get(i)).getLifeValue() - 10);
                }
            }
            if (Cerberus.getInstance().get(j).getRectangle().contains(Omenoct.getInstance().getRectangle())){
                Omenoct.getInstance().setHP(Omenoct.getInstance().getHP()-10);
            }
            if (Cerberus.getInstance().get(j).getRectangle().contains(Necropick.getInstance().getRectangle())){
                Necropick.getInstance().setHP(Necropick.getInstance().getHP()-10);
            }

        }
    }



}