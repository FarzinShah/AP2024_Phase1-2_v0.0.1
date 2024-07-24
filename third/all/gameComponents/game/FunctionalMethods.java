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
import third.all.controller.movement.MovementOfYellowEnemy;
import third.all.controller.movement.Position;
import third.all.data.*;
import third.all.data.booleans.BooleansOfCollectibles;
import third.all.data.booleans.BooleansOfEnemies;
import third.all.data.booleans.BooleansOf_IsValidToShow;
import third.all.data.booleans.HelpingBooleans;
import third.all.model.*;
import third.all.model.boss.Head;
import third.all.model.boss.LeftHand;
import third.all.model.boss.RightHand;

import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static third.all.controller.Constants.*;
import static third.all.data.booleans.Booleans.isCollidedY;
import static third.all.data.booleans.Booleans.shapeIntersects;
import static third.all.data.Properties.*;
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

    public static void saveGameState() {
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
                .setAddBlackOrbs(Properties.getInstance().boHelper)
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

    public static void saveGameStateWhenPaused() {
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
                .setAddBlackOrbs(Properties.getInstance().boHelper)
                .setPanelsData(PanelsData.getInstance())
                .setProperties(Properties.getInstance())
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(gameState);

        try (FileWriter writer = new FileWriter(SAVING_DATA_PATH_WP)) {
            writer.write(json);
        } catch (IOException e) {
            logger.error("Saving of Game state is failed :(((");

            e.printStackTrace();
        }
    }

    public static void loadGameState(Input input) {
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
            Properties.getInstance().boHelper = gameState.addBlackOrbs;
            if (Properties.getInstance().boHelper[1] > 0) {
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

    public static void loadGameStateWhenPaused(Input input) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(SAVING_DATA_PATH_WP)) {
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
            Properties.getInstance().boHelper = gameState.addBlackOrbs;
            if (Properties.getInstance().boHelper[1] > 0) {
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
    public static void rightHandShoot() {
        bulletsOfRightHand.add(new Bullet(RightHand.getInstance().getLocation().x + 50, RightHand.getInstance().getLocation().y,
                (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getX(),
                (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getY(), Color.YELLOW));

    }
    public static void leftHandShoot() {
        bulletsOfLeftHand.add(new Bullet(LeftHand.getInstance().getLocation().x + 50, LeftHand.getInstance().getLocation().y,
                (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getX(),
                (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getY(), Color.YELLOW));

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
                    ((GreenEnemyModel) greenEnemies1.get(i)).setLifeValue(((GreenEnemyModel) greenEnemies1.get(i)).getLifeValue() - 10);
                }
            }
            if (Cerberus.getInstance().get(j).getRectangle().contains(Omenoct.getInstance().getRectangle())) {
                Omenoct.getInstance().setHP(Omenoct.getInstance().getHP() - 10);
            }
            if (Cerberus.getInstance().get(j).getRectangle().contains(Necropick.getInstance().getRectangle())) {
                Necropick.getInstance().setHP(Necropick.getInstance().getHP() - 10);
            }

        }
    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }


    public static void playBossMusic() {
        final File file = new File(BOSS_MUSIC_PATH);

        try (final AudioInputStream in = getAudioInputStream(file)) {

            final AudioFormat outFormat = getOutFormat(in.getFormat());
            final DataLine.Info info = new DataLine.Info(SourceDataLine.class, outFormat);

            try (final SourceDataLine line =
                         (SourceDataLine) AudioSystem.getLine(info)) {

                if (line != null) {
                    line.open(outFormat);
                    line.start();
                    stream(getAudioInputStream(outFormat, in), line);
                    line.drain();
                    line.stop();
                }
            }

        } catch (UnsupportedAudioFileException
                | LineUnavailableException
                | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();

        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    public static void stream(AudioInputStream in, SourceDataLine line)
            throws IOException {
        final byte[] buffer = new byte[4096];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            line.write(buffer, 0, n);
        }
    }



    public static void necropickShoot() {
        int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int i = 0; i < 8; i++) {
            bulletsOfNecropick.add(new Bullet(Necropick.getInstance().getLocation().x, Necropick.getInstance().getLocation().y, dx[i], dy[i]));
        }

    }

    public static Rectangle getBoundsGreenEnemy2(int i) {
        return new Rectangle((int) ((GreenEnemyModel) greenEnemies1.get(i)).getPosition().intX(), (int) (int) ((GreenEnemyModel) greenEnemies1.get(i)).getPosition().intY(), ((GreenEnemyModel) greenEnemies1.get(i)).getLength(), ((GreenEnemyModel) greenEnemies1.get(i)).getLength());
    }

    public static Rectangle getBoundsYellowEnemy2(int i) {
        return new Rectangle((int) ((YellowEnemyModel) yellowEnemies1.get(i)).getPosition().intX(), (int) (int) ((YellowEnemyModel) yellowEnemies1.get(i)).getPosition().intY(), 25, 25);
    }



    public static void checkCollisions() {
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        ArrayList<Collectable> collectablesToRemove = new ArrayList<>();
        Rectangle epsilon = new Rectangle((int) ((EpsilonModel) gameObjects.get(0)).getPosition().getX(), (int) (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getY(), EPSILON_WIDTH, EPSILON_LENGTH);
        for (Bullet bullet : bullets) {
            for (int i = 0; i < yellowEnemies1.size(); i++) {
                if (bullet.getBounds().intersects(getBoundsYellowEnemy2(i))) {
                    bulletsToRemove.add(bullet);
                    ((YellowEnemyModel) yellowEnemies1.get(i)).setLifeValue(((YellowEnemyModel) yellowEnemies1.get(i)).getLifeValue() - 1);
                }
                if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)) {
                    if (bullet.getBounds().intersects(Omenoct.getInstance().getRectangle())) {
                        bulletsToRemove.add(bullet);
                        Omenoct.getInstance().setHP(Omenoct.getInstance().getHP() - 1);
                        if (Properties.getInstance().isValidToChiron) {
                            Properties.getInstance().HP += 3;
                        }
                    }

                }
                if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(1)) {
                    if (shapeIntersects(bullet.getBounds(), Necropick.getInstance().getRectangle())) {
                        bulletsToRemove.add(bullet);
                        Necropick.getInstance().setHP(Necropick.getInstance().getHP() - 1);
                        if (Properties.getInstance().isValidToChiron) {
                            Properties.getInstance().HP += 3;
                        }
                    }

                }


            }
            for (int i = 0; i < greenEnemies1.size(); i++) {
                if (bullet.getBounds().intersects(getBoundsGreenEnemy2(i))) {
                    bulletsToRemove.add(bullet);
                    ((GreenEnemyModel) greenEnemies1.get(i)).setLifeValue(((GreenEnemyModel) greenEnemies1.get(i)).getLifeValue() - 1);
                }
                if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)) {
                    if (bullet.getBounds().intersects(Omenoct.getInstance().getRectangle())) {
                        bulletsToRemove.add(bullet);
                        Omenoct.getInstance().setHP(Omenoct.getInstance().getHP() - 1);
                        if (Properties.getInstance().isValidToChiron) {
                            Properties.getInstance().HP += 3;
                        }
                    }

                }
            }
            for (int j = 0; j < BooleansOf_IsValidToShow.getInstance().getIsValidToShowBlackOrbEntity().size(); j++) {
                if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowBlackOrbEntity().get(j)) {
                    if (bullet.getBounds().intersects(Orb.getInstance().get(j).getRectangle())) {
                        bulletsToRemove.add(bullet);
                        Orb.getInstance().get(j).setHP(Orb.getInstance().get(j).getHP() - 1);
                        if (Properties.getInstance().isValidToChiron) {
                            Properties.getInstance().HP += 3;
                        }
                    }
                }
            }

            if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(3)) {
                if (bullet.getBounds().intersects(Wyrm.getInstance().getRectangle())) {
                    bulletsToRemove.add(bullet);
                    Wyrm.getInstance().setHP(Wyrm.getInstance().getHP() - 1);
                    if (Properties.getInstance().isValidToChiron) {
                        Properties.getInstance().HP += 3;
                    }
                }

            }

           /* todo:these were for phase1:
           for (int i = 3; i < 5; i++) {
                if(bullet.getBounds().intersects(getBoundsGreenEnemy(i))){
                    bulletsToRemove.add(bullet);
                    ((GreenEnemyModel)gameObjects.get(i)).setLifeValue(((GreenEnemyModel)gameObjects.get(i)).getLifeValue()-1);
                }
            }
            for (int i = 1; i < 3; i++) {
                if(bullet.getBounds().intersects(getBoundsYellowEnemy(i))){
                    bulletsToRemove.add(bullet);
                    ((YellowEnemyModel)gameObjects.get(i)).setLifeValue(((YellowEnemyModel)gameObjects.get(i)).getLifeValue()-1);
                }
            }*/
        }

        for (Bullet bullet : bulletsOfOmenoct) {
            if (epsilon.intersects(bullet.getBounds())) {
                Properties.getInstance().HP -= 8;
                bulletsToRemove.add(bullet);
                ClipHandler.getInstance().playShotMusic();


            }
        }

        for (Bullet bullet : bulletsOfNecropick) {
            if (epsilon.intersects(bullet.getBounds2())) {
                Properties.getInstance().HP -= 5;
                bulletsToRemove.add(bullet);
                ClipHandler.getInstance().playShotMusic();


            }
        }
        for (Bullet bullet : bulletsOfWyrm) {
            if (epsilon.intersects(bullet.getBounds())) {
                Properties.getInstance().HP -= 8;
                bulletsToRemove.add(bullet);
                ClipHandler.getInstance().playShotMusic();


            }
        }
        for (Collectable collectable : CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct()) {
            if (collectable.getRectangle().intersects(epsilon)) {
                Properties.getInstance().XP += 4;
                collectablesToRemove.add(collectable);
                ClipHandler.getInstance().playShotMusic();

            }
        }

        for (Collectable collectablesOfOrb : CollectablesOfEnemies.getInstance().getCollectablesOfOrbs()) {
            if (collectablesOfOrb.getRectangle().intersects(epsilon)) {
                Properties.getInstance().XP += 30;
                collectablesToRemove.add(collectablesOfOrb);
                ClipHandler.getInstance().playShotMusic();


            }
        }

        for (Collectable collectable : CollectablesOfEnemies.getInstance().getCollectablesOfYE()) {
            if (collectable.getRectangle().intersects(epsilon)) {
                Properties.getInstance().XP += 10;
                collectablesToRemove.add(collectable);

            }
        }
        for (Collectable collectable : CollectablesOfEnemies.getInstance().getCollectablesOfWyrm()) {
            if (collectable.getRectangle().intersects(epsilon)) {
                Properties.getInstance().XP += 8;
                collectablesToRemove.add(collectable);

            }
        }

        if (BooleansOf_IsValidToShow.getInstance().isValidToAttackCerberus()) {
            for (int i = 0; i < Cerberus.getInstance().size(); i++) {
                for (Bullet bullet : bulletsOfNecropick) {
                    if (Cerberus.getInstance().get(i).getRectangle().contains(bullet.getBounds())) {
                        bulletsToRemove.add(bullet);
                    }
                }
                for (Bullet bullet : bulletsOfOmenoct) {
                    if (Cerberus.getInstance().get(i).getRectangle().contains(bullet.getBounds())) {
                        bulletsToRemove.add(bullet);
                    }
                }
            }
        }

        if (Properties.getInstance().WAVE == 6) {
            if (HelpingBooleans.getInstance().isOnOrbit) {
                for (Bullet bullet : bulletsOfLeftHand) {
                    if (bullet.getBounds().intersects(epsilon)) {
                        Properties.getInstance().HP -= 2;
                        bulletsToRemove.add(bullet);
                    }
                }
                for (Bullet bullet : bulletsOfRightHand) {
                    if (bullet.getBounds().intersects(epsilon)) {
                        Properties.getInstance().HP -= 2;
                        bulletsToRemove.add(bullet);
                    }
                }
            }
                for (Bullet bullet : bullets) {
                if (bullet.getBounds().intersects(Head.getInstance().getRectangle())) {
                    Head.getInstance().setHP(Head.getInstance().getHP() - 1);
                    bulletsToRemove.add(bullet);
                }
                if (bullet.getBounds().intersects(RightHand.getInstance().getRectangle())) {
                    RightHand.getInstance().setHP(RightHand.getInstance().getHP() - 1);
                    bulletsToRemove.add(bullet);

                }
                if (bullet.getBounds().intersects(LeftHand.getInstance().getRectangle())) {
                    LeftHand.getInstance().setHP(LeftHand.getInstance().getHP() - 1);
                    bulletsToRemove.add(bullet);

                }
                if (!PanelsData.getInstance().getBossPanel().getRectangle().contains(bullet.getBounds())) {
                    bulletsToRemove.add(bullet);
                    if (!HelpingBooleans.getInstance().isSqueezed) {
                        if (bullet.getBounds().getX() - PanelsData.getInstance().getBossPanel().getX() < 3) {
                            PanelsData.getInstance().getBossPanel().setX(PanelsData.getInstance().getBossPanel().getX() - 5);
                            PanelsData.getInstance().getBossPanel().setWidth(PanelsData.getInstance().getBossPanel().getWidth() + 10);
                        }
                        if (bullet.getBounds().getY() - PanelsData.getInstance().getBossPanel().getY() < 3) {
                            PanelsData.getInstance().getBossPanel().setY(PanelsData.getInstance().getBossPanel().getY() - 5);
                            PanelsData.getInstance().getBossPanel().setHeight(PanelsData.getInstance().getBossPanel().getHeight() + 10);
                        }
                        if (PanelsData.getInstance().getBossPanel().getRightX() - bullet.getBounds().getX() < 3) {
//                            PanelsData.getInstance().getBossPanel().setX(PanelsData.getInstance().getBossPanel().getX() + 5);
                            PanelsData.getInstance().getBossPanel().setWidth(PanelsData.getInstance().getBossPanel().getWidth() + 5);
                        }
                        if (PanelsData.getInstance().getBossPanel().getDownY() - bullet.getBounds().getY() < 3) {
//                            PanelsData.getInstance().getBossPanel().setX(PanelsData.getInstance().getBossPanel().getX() + 5);
                            PanelsData.getInstance().getBossPanel().setHeight(PanelsData.getInstance().getBossPanel().getHeight() + 5);
                        }
                    }
                }

            }
        }


        bullets.removeAll(bulletsToRemove);
        bulletsOfOmenoct.removeAll(bulletsToRemove);
        bulletsOfNecropick.removeAll(bulletsToRemove);
        bulletsOfWyrm.removeAll(bulletsToRemove);
        bulletsOfLeftHand.removeAll(bulletsToRemove);
        bulletsOfRightHand.removeAll(bulletsToRemove);
        CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().removeAll(collectablesToRemove);
        CollectablesOfEnemies.getInstance().getCollectablesOfYE().removeAll(collectablesToRemove);
        CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().removeAll(collectablesToRemove);
        CollectablesOfEnemies.getInstance().getCollectablesOfWyrm().removeAll(collectablesToRemove);

    }

}