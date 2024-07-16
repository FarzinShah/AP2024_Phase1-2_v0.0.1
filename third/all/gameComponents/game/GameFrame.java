package third.all.gameComponents.game;

import third.all.controller.componentController.*;
import third.all.controller.entity.EpsilonModel;
import third.all.controller.entity.GameObject;
import third.all.controller.entity.GreenEnemyModel;
import third.all.controller.entity.YellowEnemyModel;
import third.all.controller.movement.MovementOfGreenEnemy;
import third.all.controller.movement.MovementOfYellowEnemy;
import third.all.controller.movement.Position;
import third.all.controller.movement.Vector2D;
import third.all.data.Properties;
import third.all.model.ShotOfEpsilon;
import third.all.gameComponents.preGameComponent.GameOverFrame;
import third.all.gameComponents.preGameComponent.InformationsOfSettings;
import third.all.gameComponents.preGameComponent.MapGenerator;
import third.all.gameComponents.extendedComponents.StoreFrame;
import third.all.gameComponents.preGameComponent.Timer1;


import javax.sound.sampled.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static third.all.controller.Constants.*;
import static third.all.controller.Variables.rng;
import static third.all.data.Properties.*;
import static third.all.gameComponents.preGameComponent.MapGenerator.yellowEnemies;
import static third.all.gameComponents.preGameComponent.MapGenerator.yellowEnemies_triangles;
import static third.all.gameComponents.preGameComponent.Settings.informationsOfSettings;
import static third.all.gameComponents.preGameComponent.Timer1.*;


public class GameFrame implements KeyListener, ActionListener, MouseMotionListener, MouseListener {
    public MyPanel panel;
    public MyFrame frame;
    public static boolean play = false; // for starting
    public static GameFrame INSTANCE;
    public static int score = 0;
    private Timer time;
    private int delay = 0;
    public static Clip clip;
    boolean isUnvisible = true;
    private static Timer1 timerOfGame;

    private int numberOfRun = 0;
    private int elapsedTime1 = 0;

    private int[] startedTimeOfCollectibles = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    private boolean isGameOvered = false;
    private boolean b2 = true;
    private boolean timer1Starter = false;
    private boolean lineShower = false;

    public static boolean isValid6 = true;
    public static boolean isValid7 = false;
    public static boolean isValid8 = true;

    public static boolean isValidStore = true;
    boolean isValid9 = true;
    boolean isValid11 = true;
    public static ArrayList<GameObject> gameObjects;


    private final Color backGround = Color.BLACK;

    private LinkedList<ShotOfEpsilon> shotsOfEpsilons = new LinkedList<>();

    private LinkedList<ShotOfEpsilon> currentShots = new LinkedList<>();


    private LinkedList<Boolean> validShots = new LinkedList<>();
    private int shotCounter = 0;
    private int currentShotCounter = 0;
    private int currentNumOfRun = 0;

    int collidedOne = -5;
    int collidedTwo = -5;
    int collidedOneG = -6;
    int collidedTwoG = -6;
    int collidedOneGY = -6;
    int collidedTwoGY = -6;


    private boolean showOfPointerItem;
    private boolean lineShower2 = false;
    private boolean[] showOfCollectibles;
    private boolean[] showOfCollectiblesHelper = {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};

    private boolean showOfPointerItemHelper = true;
    private boolean activateMechanismOfPointerItem = false;
    private boolean[] activateMechanismOfCollectiblesItem;


    private double playerX = Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH - 450; //350
    private double playerY =Properties.getInstance(). GLASS_FRAME_DIMENSION_HEIGHT - 300;
    private final LinkedList<Integer> counters = new LinkedList<>();
    HashMap<Integer,Integer> startFromCollision = new HashMap<>();
    private Integer stateCounter;
    private Integer stateCounterG;

    static int numberOfShots = 100;

    private int record = 0;
    Timer timer1;

    private int startX = 345, startY = 520;

    //helper booleans:
    int collided = -5;
    private LinkedList<Boolean> isCollided = new LinkedList<>();
    private LinkedList<Boolean> isCollidedEnemies = new LinkedList<>();

    private LinkedList<Position> positionsOfCollectiblesY = new LinkedList<>();
    private LinkedList<Position> positionsOfCollectiblesG = new LinkedList<>();


    boolean h1 = false;

    private int elapsedTimeForShot = 0;

    private int currentTimeFromStartWaveI = 0;



    private int clickedX = 0, clickedY = 0;

    private MapGenerator map;
    Input input;
    TargetWithMouse targetWithMouse;
    TWM_Item_Model twm_item_model;
    Shot_Item_Model shot_item_model;
    TKM2_Item_Model[] collectibleItems;



    public GameFrame() throws UnsupportedAudioFileException, IOException {
        yellowEnemies_triangles.clear();
        yellowEnemies.clear();
        int SCREEN_WIDTH = 1440;
        int SCREEN_HEIGHT = 815;
        int maxSize = Math.max(SCREEN_WIDTH, SCREEN_HEIGHT) / 3;
        panel.setSize(maxSize, maxSize);
        panel.setLocation(SCREEN_WIDTH / 2 - maxSize / 2, SCREEN_HEIGHT / 2 - maxSize / 2);
        frame.setUndecorated(true);
        frame.setSize(panel.getSize());
        frame.setLocation(panel.getLocation());
        frame.add(panel);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        frame.setVisible(true);


        setDefaultShots();
        input = new Input();
        panel.addKeyListener(input);

        if (informationsOfSettings.isPlay()) {
            playMusic(informationsOfSettings.isPlay());
        }
        timerOfGame = new Timer1();
        targetWithMouse = new TargetWithMouse(new Point2D.Double(0, 0));
        twm_item_model = new TWM_Item_Model(new Point2D.Double(rng(50.0, 300.0), rng(50.0, 300.0)));
        shot_item_model = new Shot_Item_Model(new Point2D.Double(230.0, 330.0));
        gameObjects = new ArrayList<>();
        frame.add(panel);
//        panel.setSize(500, 500);

//        frame.setLocation(500, 500);
//        frame.setUndecorated(true);
//        frame.setVisible(true);
//        panel.setBackground(new Color(0,0,0,0));
        map = new MapGenerator();

//        frame.setSize(panel.getSize());

        counters.add(0);
        counters.add(1);
        counters.add(2);
        counters.add(3);
        counters.add(4);
        counters.add(5);
        counters.add(6);
        counters.add(7);
        showOfCollectibles = new boolean[10];
        Arrays.fill(showOfCollectibles, false);
        collectibleItems = new TKM2_Item_Model[10];
        for (int i = 0; i < 5; i++) {
            collectibleItems[i] = new TKM2_Item_Model(new Position(-1,-1));
        }
        stateCounter = counters.get(0);

        panel.addKeyListener(this);
        panel.setFocusable(true);
        panel.setFocusTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
        timer1 = new Timer(0, this);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addMouseListener(targetWithMouse);
        panel.addMouseMotionListener(targetWithMouse);


//        frame.setBounds((int) boundX, (int) boundY, (int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT);

        gameObjects.add(new EpsilonModel(new EpsilonController(input), 230, 330));
        for (int i = 0; i < 2; i++) { // 10
            gameObjects.add(new YellowEnemyModel(new YellowEnemyController(input), 20, rng(-300, -10), rng(100, 200))); // این posX و posY نقطه گوشه بالا چپ رو معلوم میکنن.
        }
        for (int i = 0; i < 2; i++) { //
            gameObjects.add(new GreenEnemyModel(new GreenEnemyController(input), 20, rng(-300, -10), rng(100, 200))); // این posX و posY نقطه گوشه بالا چپ رو معلوم میکنن.

        }




        for (int i = 0; i < 100; i++) {
            isCollided.add(i, false);
        }
        for (int i = 0; i < 100; i++) {
            isCollidedEnemies.add(i, false);
        }
    }
    protected void paintComponent(Graphics g) {
        if (timer1Starter) {
            timerOfGame.start();
            timer1Starter = false;
        }
        //background:
        g.setColor(backGround);
        g.fillRect(1, 1, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
        // drawing map:
        map.draw((Graphics2D) g);
        // item pointer of mouse:
        if (showOfPointerItem) {
            System.out.println("???");
            g.setColor(Color.red);
            twm_item_model.draw((Graphics2D) g);
        }
        for (int i = 3; i < 5; i++) {
            if(showOfCollectibles[i]){
                g.setColor(Color.GREEN);
                collectibleItems[i].draw((Graphics2D) g);


            }
        }

        // borders:
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, 3, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
        g.fillRect(0, 0, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, 3);
        g.fillRect((int) (Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH - 3), 0, 3, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
        g.fillRect(0, (int) (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT - 3), (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, 3);

        //Showing the scores:
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        ((Graphics2D) g).drawString("HP: " + Properties.getInstance().HP, (int) (Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH - 125), 30);
        ((Graphics2D) g).drawString("XP: " + Properties.getInstance().XP, (int) (Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH - 225), 30);

        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        ((Graphics2D) g).drawString(timerOfGame.minutes_string + ":" + timerOfGame.seconds_string, 20, 30);
        if (record != 0) {
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 25));
            ((Graphics2D) g).drawString("Highest: " + record, 320, 30);
        }
        // shots:

        if (validShots.get(currentShotCounter)) {
            g.setColor(Color.red);
            for (int i = 0; i < currentShots.size(); i++) {
                g.fillOval((int) currentShots.get(i).getPlaceX(), (int) shotsOfEpsilons.get(i).getPlaceY(), 10, 10);

            }
        }

        if (lineShower && informationsOfSettings.isTherePointer()) {
            g.setColor(new Color(0x7A4BA0));
            g.drawLine((int) (gameObjects.get(0).position.getX() + 25), (int) (gameObjects.get(0).position.getY() + 25), startX, startY);
        }
        getGameObjects().forEach(gameObject -> g.drawImage(gameObject.getSprite(), gameObject.position.intX(), gameObject.position.intY(), null));

        // The Epsilon:

        // create Ball:

        if (elapsedTime == 0) {
            timerOfGame.stop();
            play = false;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            ((Graphics2D) g).drawString("Start Again! Scores: " + score, 190, 300);
            if (isValid8) {
                new GameOverFrame();
                isValid8 = false;

            }
        }



        time.setInitialDelay(10);
        time.start();
        g.dispose();
    }

    public void update() {
        gameObjects.forEach(GameObject::update);
//        frame.update();
        //Wave 1: start from releasing key "R". It takes 5 minutes, unless all the enemies being killed.
        // in this wave, every 30 seconds one enemy comes. Totally 3 enemies from each group.

        if(Properties.getInstance().STATE == 1){
            if(spentMilliSecond %29000 == 0 && spentMilliSecond < 150000){
                gameObjects.add(new YellowEnemyModel(new YellowEnemyController(input), 20, rng(-300, -10), rng(100, 200))); // این posX و posY نقطه گوشه بالا چپ رو معلوم میکنن.
            }
            if(spentMilliSecond %29000 == 0 && spentMilliSecond > 150000 && spentMilliSecond < 300000){
                gameObjects.add(new YellowEnemyModel(new YellowEnemyController(input), 20, rng(-300, -10), rng(100, 200))); // این posX و posY نقطه گوشه بالا چپ رو معلوم میکنن.
            }

            timer1Starter = true;
            //start after second one:

            // item of mouse pointer:

            for (int i = 3; i < 5; i++) {
                if(showOfCollectiblesHelper[i]) showOfCollectibles[i] = spentMilliSecond >= startedTimeOfCollectibles[i] && spentMilliSecond <= startedTimeOfCollectibles[i] + 10000;

            }
            if (showOfPointerItemHelper) showOfPointerItem = spentMilliSecond >= 5000 && spentMilliSecond <= 15000;

            for (int i = 3; i < 5; i++) {
                if(showOfCollectibles[i]){
                    double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                    double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                    if (((EpsilonModel) gameObjects.get(0)).getRadius() + twm_item_model.getRadius() >= Math.sqrt(((centerOfEpsilonX - collectibleItems[i].getCenterX()) * (centerOfEpsilonX - collectibleItems[i].getCenterX())) + ((centerOfEpsilonY - collectibleItems[i].getCenterY()) * (centerOfEpsilonY - collectibleItems[i].getCenterY())))) {
                        showOfCollectiblesHelper[i] = false;
                        showOfCollectibles[i] = false;
                        Properties.getInstance().XP+=5;
                    }

                }
            }
            if (showOfPointerItem) {
                double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                if (((EpsilonModel) gameObjects.get(0)).getRadius() + twm_item_model.getRadius() >= Math.sqrt(((centerOfEpsilonX - twm_item_model.getCenterX()) * (centerOfEpsilonX - twm_item_model.getCenterX())) + ((centerOfEpsilonY - twm_item_model.getCenterY()) * (centerOfEpsilonY - twm_item_model.getCenterY())))) {
                    showOfPointerItemHelper = false;
                    showOfPointerItem = false;
                    activateMechanismOfPointerItem = true;
                    startTimeFromActivationOfPointerItem = spentMilliSecond;

                }
            }


            if (((spentMilliSecond <= startTimeFromActivationOfPointerItem + 10000) && activateMechanismOfPointerItem)||lineShower2) {
                lineShower = true;
                targetWithMouse = new TargetWithMouse(new Point2D.Double(((EpsilonModel) gameObjects.get(0)).getPosition().getX(), ((EpsilonModel) gameObjects.get(0)).getPosition().getY()));

            } else lineShower = false;

            if (spentMilliSecond < 2000) {
                if (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT > GLASS_FRAME_DIMENSION_MIN_HEIGHT && Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH > GLASS_FRAME_DIMENSION_MIN_WIDTH) {
                    Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT--;
                    Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH--;
                    frame.setSize((int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, (int)Properties.getInstance(). GLASS_FRAME_DIMENSION_HEIGHT);
                    isValidStore = false;

                }
                time.restart();
            }

            if (spentMilliSecond % 5000 == 0 && spentMilliSecond >= 5000) {
                System.out.println(Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
                if (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT < GLASS_FRAME_DIMENSION_MAX_HEIGHT && Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH < GLASS_FRAME_DIMENSION_MAX_WIDTH) {
                    Properties.getInstance(). GLASS_FRAME_DIMENSION_HEIGHT++;
                    Properties.getInstance(). GLASS_FRAME_DIMENSION_WIDTH++;
                    frame.setSize((int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, (int)Properties.getInstance(). GLASS_FRAME_DIMENSION_HEIGHT);
                    isValidStore = false;
                }
//            time.restart();
            }

            if (spentMilliSecond % 5000 == 3000 && spentMilliSecond >= 5000) {
                if (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT > GLASS_FRAME_DIMENSION_MIN_HEIGHT && Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH > GLASS_FRAME_DIMENSION_MIN_WIDTH) {
                    Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT--;
                    Properties.getInstance(). GLASS_FRAME_DIMENSION_WIDTH--;
                    frame.setSize((int)Properties.getInstance(). GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
                    isValidStore = false;
                }
//            time.restart();
            }

            IS_VALID_STORE = !((spentMilliSecond % 5000 == 3000) || (spentMilliSecond % 5000 == 0));
//        System.out.println("ISVALIDSTORE: " + IS_VALID_STORE);


            if (isValid11) {
                reversePointer();
                isValid11 = false;
            }

            if (isValid7) {
                frame.dispose();
                isValid7 = false;
                timerOfGame.reset();
            }
            time.start();
            if (play) {
                for (int i = 1; i < 3 ; i++) {
                    if(((YellowEnemyModel)gameObjects.get(i)).getLifeValue() == 0){
                        if(((YellowEnemyModel)gameObjects.get(i)).isAlive()){
                            positionsOfCollectiblesY.add(((YellowEnemyModel)gameObjects.get(i)).getPosition());
                            collectibleItems[i] = new TKM2_Item_Model(((YellowEnemyModel)gameObjects.get(i)).getPosition());
                        }
                        ((YellowEnemyModel)gameObjects.get(i)).setPosition(new Position(1111111111,1111111111));
                        ((YellowEnemyModel)gameObjects.get(i)).setAlive(false);
                        startedTimeOfCollectibles[i] = spentMilliSecond;

                    }
                }
                for (int j = 1; j < 5; j++) {
                    if((gameObjects.get(j).getPosition().getY()==2000)||(gameObjects.get(j).getPosition().getX()==2000)||(gameObjects.get(j).getPosition().getY()<=-2000)||(gameObjects.get(j).getPosition().getX()<=-2000)){
                        for (int i = 1; i < 3; i++) {
                            ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - gameObjects.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - gameObjects.get(i).getPosition().getY())));
                            ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
                        }
                        for (int i = 3; i < 5; i++) {
                            ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - gameObjects.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - gameObjects.get(i).getPosition().getY())));
                            ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
                        }

                    }
                }
                for (int i = 3; i < 5; i++) {

                    if(((GreenEnemyModel)gameObjects.get(i)).getLifeValue() == 0){
                        if(((GreenEnemyModel)gameObjects.get(i)).isAlive()){
                            positionsOfCollectiblesG.add(((GreenEnemyModel)gameObjects.get(i)).getPosition());
                            collectibleItems[i] = new TKM2_Item_Model(((GreenEnemyModel)gameObjects.get(i)).getPosition());

                        }
                        ((GreenEnemyModel)gameObjects.get(i)).setPosition(new Position(1111111111,1111111111));
                        ((GreenEnemyModel)gameObjects.get(i)).setAlive(false);
                        startedTimeOfCollectibles[i] = spentMilliSecond;

                    }
                }
                for (int i = 1; i < gameObjects.size(); i++) {
                    if (startFromCollision.get(i)!=null){
                        System.out.println("????????????????????????????????????????????????????????????????????????????: " + startFromCollision.get(i));
                        if(spentMilliSecond==(startFromCollision.get(i)+5000)){
                            if(gameObjects.get(i) instanceof YellowEnemyModel){
                                isCollided.set(i, false);

                            }else if(gameObjects.get(i) instanceof GreenEnemyModel){
                                isCollided.set(i, false);

                            }
                        }
                    }
                }

                if(Properties.getInstance().HP <=0){
                    ((EpsilonModel)gameObjects.get(0)).sizeOfEpsilon.setHeight(((EpsilonModel)gameObjects.get(0)).sizeOfEpsilon.getHeight()+1);
                    ((EpsilonModel)gameObjects.get(0)).sizeOfEpsilon.setWidth(((EpsilonModel)gameObjects.get(0)).sizeOfEpsilon.getWidth()+1);
                }


                for (int i = 0; i < currentShots.size(); i++) {
                    shotsOfEpsilons.get(i).setPlaceX((int) (shotsOfEpsilons.get(i).getPlaceX() - shotsOfEpsilons.get(i).getDirX()));
                    shotsOfEpsilons.get(i).setPlaceY((int) (shotsOfEpsilons.get(i).getPlaceY() - shotsOfEpsilons.get(i).getDirY()));

                }
                for (int i = 0; i < shotsOfEpsilons.size(); i++) {
                    System.out.println("NumberOfCollision: " + shotsOfEpsilons.get(0).getNumberOfCollision());
                    if (shotsOfEpsilons.get(i).getNumberOfCollision() == 1) {
                        shotsOfEpsilons.get(i).setPlaceX(1000000);
                        shotsOfEpsilons.get(i).setPlaceY(1000000);
                    }
                    if (shotsOfEpsilons.get(i).getPlaceX() == 2) {
                        if (Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH < GLASS_FRAME_DIMENSION_MAX_WIDTH - 20) {
                            Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH += 5;
                            boundX -= 5;
                            frame.setBounds((int) boundX, (int) boundY, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
                            System.out.println("\u001B[41m" + i + "\u001B[0m");

                        }
                        if (Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH > 20) {
                            Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH -= 5;
//                        boundX+=5;
                            frame.setBounds((int) boundX, (int) boundY, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);

                        }
                    }
                    if (shotsOfEpsilons.get(i).getPlaceX() == Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH - 10) {
                        boundX += 5;
                        Properties.getInstance().  GLASS_FRAME_DIMENSION_WIDTH--;
                        frame.setBounds((int) boundX, (int) boundY, (int)Properties.getInstance(). GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);


                    }
                    if (shotsOfEpsilons.get(i).getPlaceX() <= 0 || shotsOfEpsilons.get(i).getPlaceY() <= -8 ||
                            shotsOfEpsilons.get(i).getPlaceY() >= Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT - 1 || (shotsOfEpsilons.get(i).getPlaceX() >= Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH))
                        shotsOfEpsilons.get(i).setNumberOfCollision(shotsOfEpsilons.get(i).getNumberOfCollision() + 1);



                }
                System.out.println("\u001B[41m" + shotsOfEpsilons.get(0).getPlaceX() + "\u001B[0m" + " - y:" + "\u001B[36m" + shotsOfEpsilons.get(0).getPlaceY() + "\u001B[0m");
                for (int i = 0; i < shotsOfEpsilons.size(); i++) {
                    for (int j = 1; j < 3; j++) {
                        if (Math.sqrt(((shotsOfEpsilons.get(i).getPlaceX() + 5.0) - ((YellowEnemyModel) gameObjects.get(j)).getPosition().getX() - ((YellowEnemyModel) gameObjects.get(j)).getRadius()) * ((shotsOfEpsilons.get(i).getPlaceX() + 5.0) - ((YellowEnemyModel) gameObjects.get(j)).getPosition().getX() - ((YellowEnemyModel) gameObjects.get(j)).getRadius()) +
                                ((shotsOfEpsilons.get(i).getPlaceY() + 5.0) - ((YellowEnemyModel) gameObjects.get(j)).getPosition().getY() - ((YellowEnemyModel) gameObjects.get(j)).getRadius()) * ((shotsOfEpsilons.get(i).getPlaceY() + 5.0) - ((YellowEnemyModel) gameObjects.get(j)).getPosition().getY() - ((YellowEnemyModel) gameObjects.get(j)).getRadius()))
                                < 5.0 + ((YellowEnemyModel) gameObjects.get(j)).getRadius()) {
                            // گلوله حذف بشه - یه جون از جونای enemy کم بشه.
                            if(is_Writ_Of_Ares){
                                REDUCTION_RATE_OF_HP_OF_ENEMY = 2;
                            }
                            ((YellowEnemyModel) gameObjects.get(j)).setLifeValue(((YellowEnemyModel) gameObjects.get(j)).getLifeValue()-REDUCTION_RATE_OF_HP_OF_ENEMY);
//                        XXED++;
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            shotsOfEpsilons.get(i).setPlaceX(1000000);
                            shotsOfEpsilons.get(i).setPlaceY(1000000);



                        }
                    }
                    for (int j = 3; j < 5; j++) {
                        if (Math.sqrt(((shotsOfEpsilons.get(i).getPlaceX() + 5.0) - ((GreenEnemyModel)gameObjects.get(j)).getPosition().getX() - ((GreenEnemyModel) gameObjects.get(j)).getRadius()) * ((shotsOfEpsilons.get(i).getPlaceX() + 5.0) - ((GreenEnemyModel) gameObjects.get(j)).getPosition().getX() - ((GreenEnemyModel) gameObjects.get(j)).getRadius()) +
                                ((shotsOfEpsilons.get(i).getPlaceY() + 5.0) - ((GreenEnemyModel)gameObjects.get(j)).getPosition().getY() - ((GreenEnemyModel) gameObjects.get(j)).getRadius()) * ((shotsOfEpsilons.get(i).getPlaceY() + 5.0) - ((GreenEnemyModel) gameObjects.get(j)).getPosition().getY() - ((GreenEnemyModel) gameObjects.get(j)).getRadius()))
                                < 5.0 + ((GreenEnemyModel) gameObjects.get(j)).getRadius()) {
                            // گلوله حذف بشه - یه جون از جونای enemy کم بشه.
                            ((GreenEnemyModel) gameObjects.get(j)).setLifeValue(((GreenEnemyModel) gameObjects.get(j)).getLifeValue()-1);
                            Properties.getInstance().HP++;
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            shotsOfEpsilons.get(i).setPlaceX(1000000);
                            shotsOfEpsilons.get(i).setPlaceY(1000000);


                        }
                    }


                }
                for (int i = 1; i < 3; i++) {
                    ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - gameObjects.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - gameObjects.get(i).getPosition().getY())));
                    ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
                }
                for (int i = 3; i < 5; i++) {
                    ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - gameObjects.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - gameObjects.get(i).getPosition().getY())));
                    ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
                }

                for (int j = 1; j < 3; j++) {
                    if (!isCollided.get(j)) {
                        for (int i = 1; i < 3; i++) {
                            if (i == j) {
                                ACCELERATION_OF_YELLOW_ENEMIES = 2;
                                gameObjects.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
                                if(waveOHB){
                                    isCollided.set(i,true);
                                    waveOHB=false;
                                }

                            }

                        }

                    }
                    if (isCollided.get(j)) {
                        for (int i = 1; i < 3; i++) {
                            if (i == j) {
                                ACCELERATION_OF_YELLOW_ENEMIES = 4;
                                gameObjects.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, -((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getX(), -((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
                            }
                        }

                    }
                }
                for (int j = 3; j < 5; j++) {
                    if (!isCollided.get(j)) {
                        for (int i = 3; i < 5; i++) {
                            if (i == j) {
                                ACCELERATION_OF_GREEN_ENEMIES = 2;
                                gameObjects.get(i).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().getX(), ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().getY()));
                                if(waveOHB){
                                    isCollided.set(i,true);
                                    waveOHB=false;
                                }
                            }


                        }

                    }
                    if (isCollided.get(j)) {
                        for (int i = 3; i < 5; i++) {
                            if (i == j) {
                                ACCELERATION_OF_GREEN_ENEMIES = 4;
                                gameObjects.get(i).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, -((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().getX(), -((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().getY()));
                            }
                        }

                    }
                }
            }
            System.out.println("x of moving enemy: " + gameObjects.get(1).getPosition().getX());
            System.out.println("x of moving epsilon: " + gameObjects.get(0).getPosition().getX());
            for (int k = 1; k < 5; k++) {

                for (int i = 1; i < 3; i++) {
                    for (int j = 3; j < 5; j++) {
                        double centerOfEnemy1X = gameObjects.get(i).getPosition().getX() + ((YellowEnemyModel) gameObjects.get(i)).getRadius();
                        double centerOfEnemy2X = gameObjects.get(j).getPosition().getX() + ((GreenEnemyModel) gameObjects.get(j)).getRadius();
                        double centerOfEnemy1Y = gameObjects.get(i).getPosition().getY() + ((YellowEnemyModel) gameObjects.get(i)).getRadius();
                        double centerOfEnemy2Y = gameObjects.get(j).getPosition().getY() + ((GreenEnemyModel) gameObjects.get(j)).getRadius();

                        if (Math.sqrt(((centerOfEnemy1X - centerOfEnemy2X) * (centerOfEnemy1X - centerOfEnemy2X)) + ((centerOfEnemy1Y - centerOfEnemy2Y) * (centerOfEnemy1Y - centerOfEnemy2Y))) <= (((YellowEnemyModel) gameObjects.get(i)).getRadius() + ((GreenEnemyModel) gameObjects.get(j)).getRadius())) {
                            System.out.println(":)");
                            ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(i).getPosition().getX() + gameObjects.get(j).getPosition().getX() + 25, -gameObjects.get(j).getPosition().getY() + gameObjects.get(i).getPosition().getY() + 25)));
                            ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
                            //                       ((GreenEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(j).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(i).getPosition().getY() + gameObjects.get(j).getPosition().getY() + 25)));
                            //                    ((GreenEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().normalize();
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(j).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(i).getPosition().getY() + gameObjects.get(j).getPosition().getY() + 25)));
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().normalize();

                            isCollidedEnemies.set(i,true);// : **********is needed???  - : yes!
                            isCollidedEnemies.set(j,true);
                            startFromCollision.put(i,spentMilliSecond);
                            startFromCollision.put(j,spentMilliSecond);

                            collidedOneGY = i;
                            collidedTwoGY = j;
                            gameObjects.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getY()));


                        }

                    }
                }

                if (gameObjects.get(k) instanceof GreenEnemyModel){
                    //پلن دوم:
                    for (int i = 3; i < 5; i++) {
                        double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                        double centerOfEnemyX = gameObjects.get(i).getPosition().getX() + ((GreenEnemyModel) gameObjects.get(i)).getRadius();
                        double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                        double centerOfEnemyY = gameObjects.get(i).getPosition().getY() + ((GreenEnemyModel) gameObjects.get(i)).getRadius();

//                    if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) == (((EpsilonModel) gameObjects.get(0)).getRadius() + ((GreenEnemyModel) gameObjects.get(i)).getRadius()+1)) {
//
//                    }

                        if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) < (((EpsilonModel) gameObjects.get(0)).getRadius() + ((GreenEnemyModel) gameObjects.get(i)).getRadius())) {
                            System.out.println(":)");
                            //((((((((((((+((*))+))))))))))))\\
//
                            ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(-gameObjects.get(0).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(0).getPosition().getY() + gameObjects.get(i).getPosition().getY() + 25)));
                            ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
                            Properties.getInstance(). HP -=6;
                            stateCounterG = counters.get(1);
                            isCollided.set(i, true);
                            gameObjects.get(i).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().getX(), ((GreenEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
//                isImpact1 = false;
//                isImpact2 = true;
//                با int counter استیت بندی می کنیم.
                        }
                    }
                    for (int i = 3; i < 5; i++) {
                        for (int j = i; j < 5; j++) {
                            if (i != j) {
                                double centerOfEnemy1X = gameObjects.get(i).getPosition().getX() + ((GreenEnemyModel) gameObjects.get(i)).getRadius();
                                double centerOfEnemy2X = gameObjects.get(j).getPosition().getX() + ((GreenEnemyModel) gameObjects.get(j)).getRadius();
                                double centerOfEnemy1Y = gameObjects.get(i).getPosition().getY() + ((GreenEnemyModel) gameObjects.get(i)).getRadius();
                                double centerOfEnemy2Y = gameObjects.get(j).getPosition().getY() + ((GreenEnemyModel) gameObjects.get(j)).getRadius();


                                if (Math.sqrt(((centerOfEnemy1X - centerOfEnemy2X) * (centerOfEnemy1X - centerOfEnemy2X)) + ((centerOfEnemy1Y - centerOfEnemy2Y) * (centerOfEnemy1Y - centerOfEnemy2Y))) <= (((GreenEnemyModel) gameObjects.get(i)).getRadius() + ((GreenEnemyModel) gameObjects.get(j)).getRadius())) {
                                    System.out.println(":)");
                                    ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(-gameObjects.get(i).getPosition().getX() + gameObjects.get(j).getPosition().getX() + 25, -gameObjects.get(j).getPosition().getY() + gameObjects.get(i).getPosition().getY() + 25)));
                                    ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(j).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(i).getPosition().getY() + gameObjects.get(j).getPosition().getY() + 25)));
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().normalize();
                                    startFromCollision.put(i,spentMilliSecond);

                                    isCollidedEnemies.set(i, true);
//                                isCollidedEnemies.set(j, true);
                                    collidedOneG = i;
                                    collidedTwoG = j;
                                    gameObjects.get(i).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().getX(), ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().getY()));
//                                gameObjects.get(j).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().getY()));


                                }
                            }
                        }
                    }

                }


                if (gameObjects.get(k) instanceof YellowEnemyModel) {
                    // این پلن جواب نداد :( برای برخورد
//            if(isImpact&&Math.sqrt(((gameObjects.get(0).getPosition().getX()+25)-(gameObjects.get(1).getPosition().getX()+17))*((gameObjects.get(0).getPosition().getX()+25)-(gameObjects.get(1).getPosition().getX()+17)) +
//                    ((gameObjects.get(0).getPosition().getX()+25)-(gameObjects.get(1).getPosition().getY()+30))*((gameObjects.get(0).getPosition().getY()+30)-(gameObjects.get(1).getPosition().getY()+30))) == 45.0 ){
//                ((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().setVector1(new Vector2D(((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getX(),- ((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getY()));
//            isImpact = false;
//            }
//            System.out.println("second Xdir: " + ((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getX());
                    //پلن دوم:
                    for (int i = 1; i < 3; i++) {
                        double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                        double centerOfEnemyX = gameObjects.get(i).getPosition().getX() + ((YellowEnemyModel) gameObjects.get(i)).getRadius();
                        double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                        double centerOfEnemyY = gameObjects.get(i).getPosition().getY() + ((YellowEnemyModel) gameObjects.get(i)).getRadius();


                        if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) < (((EpsilonModel) gameObjects.get(0)).getRadius() + ((YellowEnemyModel) gameObjects.get(i)).getRadius())) {
                            System.out.println(":)");
                            ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(0).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(0).getPosition().getY() + gameObjects.get(i).getPosition().getY() + 25)));
                            ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
                            startFromCollision.put(i,spentMilliSecond);
                            Properties.getInstance(). HP -=5;
                            stateCounter = counters.get(1);
                            isCollided.set(i, true);
                            gameObjects.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
//                isImpact1 = false;
//                isImpact2 = true;
//                با int counter استیت بندی می کنیم.
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        for (int j = i; j < 3; j++) {
                            if (i != j) {
                                double centerOfEnemy1X = gameObjects.get(i).getPosition().getX() + ((YellowEnemyModel) gameObjects.get(i)).getRadius();
                                double centerOfEnemy2X = gameObjects.get(j).getPosition().getX() + ((YellowEnemyModel) gameObjects.get(j)).getRadius();
                                double centerOfEnemy1Y = gameObjects.get(i).getPosition().getY() + ((YellowEnemyModel) gameObjects.get(i)).getRadius();
                                double centerOfEnemy2Y = gameObjects.get(j).getPosition().getY() + ((YellowEnemyModel) gameObjects.get(j)).getRadius();


                                if (Math.sqrt(((centerOfEnemy1X - centerOfEnemy2X) * (centerOfEnemy1X - centerOfEnemy2X)) + ((centerOfEnemy1Y - centerOfEnemy2Y) * (centerOfEnemy1Y - centerOfEnemy2Y))) <= (((YellowEnemyModel) gameObjects.get(i)).getRadius() + ((YellowEnemyModel) gameObjects.get(j)).getRadius())) {
                                    System.out.println(":)");
                                    ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(i).getPosition().getX() + gameObjects.get(j).getPosition().getX() + 25, -gameObjects.get(j).getPosition().getY() + gameObjects.get(i).getPosition().getY() + 25)));
                                    ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(j).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(i).getPosition().getY() + gameObjects.get(j).getPosition().getY() + 25)));
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().normalize();

                                    isCollidedEnemies.set(i, true);
//                                isCollidedEnemies.set(j, true);
                                    collidedOne = i;
                                    collidedTwo = j;
                                    gameObjects.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
//                                gameObjects.get(j).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().getY()));


                                }
                            }
                        }
                    }

                }
            }





        }










        System.out.println("3th: x : "+(gameObjects.get(2).getPosition().getX()) + " y : " + (gameObjects.get(2).getPosition().getY()));
        System.out.println("4th: x : "+(gameObjects.get(3).getPosition().getX()) + " y : " + (gameObjects.get(3).getPosition().getY()));
        timer1Starter = true;
        //start after second one:

        // item of mouse pointer:

        for (int i = 3; i < 5; i++) {
            if(showOfCollectiblesHelper[i]) showOfCollectibles[i] = spentMilliSecond >= startedTimeOfCollectibles[i] && spentMilliSecond <= startedTimeOfCollectibles[i] + 10000;

        }
        if (showOfPointerItemHelper) showOfPointerItem = spentMilliSecond >= 5000 && spentMilliSecond <= 15000;

        for (int i = 3; i < 5; i++) {
            if(showOfCollectibles[i]){
                double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                if (((EpsilonModel) gameObjects.get(0)).getRadius() + twm_item_model.getRadius() >= Math.sqrt(((centerOfEpsilonX - collectibleItems[i].getCenterX()) * (centerOfEpsilonX - collectibleItems[i].getCenterX())) + ((centerOfEpsilonY - collectibleItems[i].getCenterY()) * (centerOfEpsilonY - collectibleItems[i].getCenterY())))) {
                    showOfCollectiblesHelper[i] = false;
                    showOfCollectibles[i] = false;
                    Properties.getInstance(). XP+=5;
                }

            }
        }
        if (showOfPointerItem) {
            double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
            double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
            if (((EpsilonModel) gameObjects.get(0)).getRadius() + twm_item_model.getRadius() >= Math.sqrt(((centerOfEpsilonX - twm_item_model.getCenterX()) * (centerOfEpsilonX - twm_item_model.getCenterX())) + ((centerOfEpsilonY - twm_item_model.getCenterY()) * (centerOfEpsilonY - twm_item_model.getCenterY())))) {
                showOfPointerItemHelper = false;
                showOfPointerItem = false;
                activateMechanismOfPointerItem = true;
                startTimeFromActivationOfPointerItem = spentMilliSecond;

            }
        }


        if (((spentMilliSecond <= startTimeFromActivationOfPointerItem + 10000) && activateMechanismOfPointerItem)||lineShower2) {
            lineShower = true;
            targetWithMouse = new TargetWithMouse(new Point2D.Double(((EpsilonModel) gameObjects.get(0)).getPosition().getX(), ((EpsilonModel) gameObjects.get(0)).getPosition().getY()));

        } else lineShower = false;

        if (spentMilliSecond < 2000) {
            if (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT > GLASS_FRAME_DIMENSION_MIN_HEIGHT && Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH > GLASS_FRAME_DIMENSION_MIN_WIDTH) {
                Properties.getInstance(). GLASS_FRAME_DIMENSION_HEIGHT--;
                Properties.getInstance(). GLASS_FRAME_DIMENSION_WIDTH--;
                frame.setSize((int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
                isValidStore = false;

            }
            time.restart();
        }

        if (spentMilliSecond % 5000 == 0 && spentMilliSecond >= 5000) {
            System.out.println(Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
            if (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT < GLASS_FRAME_DIMENSION_MAX_HEIGHT && Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH < GLASS_FRAME_DIMENSION_MAX_WIDTH) {
                Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT++;
                Properties.getInstance(). GLASS_FRAME_DIMENSION_WIDTH++;
                frame.setSize((int)Properties.getInstance(). GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
                isValidStore = false;
            }
//            time.restart();
        }

        if (spentMilliSecond % 5000 == 3000 && spentMilliSecond >= 5000) {
            if (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT > GLASS_FRAME_DIMENSION_MIN_HEIGHT && Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH > GLASS_FRAME_DIMENSION_MIN_WIDTH) {
                Properties.getInstance(). GLASS_FRAME_DIMENSION_HEIGHT--;
                Properties.getInstance(). GLASS_FRAME_DIMENSION_WIDTH--;
                frame.setSize((int)Properties.getInstance(). GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
                isValidStore = false;
            }
//            time.restart();
        }

        IS_VALID_STORE = !((spentMilliSecond % 5000 == 3000) || (spentMilliSecond % 5000 == 0));
//        System.out.println("ISVALIDSTORE: " + IS_VALID_STORE);


        if (isValid11) {
            reversePointer();
            isValid11 = false;
        }

        if (isValid7) {
            frame.dispose();
            isValid7 = false;
            timerOfGame.reset();
        }
        time.start();
        if (play) {
            for (int i = 1; i < 3 ; i++) {
                if(((YellowEnemyModel)gameObjects.get(i)).getLifeValue() == 0){
                    if(((YellowEnemyModel)gameObjects.get(i)).isAlive()){
                        positionsOfCollectiblesY.add(((YellowEnemyModel)gameObjects.get(i)).getPosition());
                        collectibleItems[i] = new TKM2_Item_Model(((YellowEnemyModel)gameObjects.get(i)).getPosition());
                    }
                    ((YellowEnemyModel)gameObjects.get(i)).setPosition(new Position(1111111111,1111111111));
                    ((YellowEnemyModel)gameObjects.get(i)).setAlive(false);
                    startedTimeOfCollectibles[i] = spentMilliSecond;

                }
            }
            for (int j = 1; j < 5; j++) {
                if((gameObjects.get(j).getPosition().getY()==2000)||(gameObjects.get(j).getPosition().getX()==2000)||(gameObjects.get(j).getPosition().getY()<=-2000)||(gameObjects.get(j).getPosition().getX()<=-2000)){
                    for (int i = 1; i < 3; i++) {
                        ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - gameObjects.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - gameObjects.get(i).getPosition().getY())));
                        ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
                    }
                    for (int i = 3; i < 5; i++) {
                        ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - gameObjects.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - gameObjects.get(i).getPosition().getY())));
                        ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
                    }

                }
            }
            for (int i = 3; i < 5; i++) {

                if(((GreenEnemyModel)gameObjects.get(i)).getLifeValue() == 0){
                    if(((GreenEnemyModel)gameObjects.get(i)).isAlive()){
                        positionsOfCollectiblesG.add(((GreenEnemyModel)gameObjects.get(i)).getPosition());
                        collectibleItems[i] = new TKM2_Item_Model(((GreenEnemyModel)gameObjects.get(i)).getPosition());

                    }
                    ((GreenEnemyModel)gameObjects.get(i)).setPosition(new Position(1111111111,1111111111));
                    ((GreenEnemyModel)gameObjects.get(i)).setAlive(false);
                    startedTimeOfCollectibles[i] = spentMilliSecond;

                }
            }
            for (int i = 1; i < gameObjects.size(); i++) {
                if (startFromCollision.get(i)!=null){
                    System.out.println("????????????????????????????????????????????????????????????????????????????: " + startFromCollision.get(i));
                    if(spentMilliSecond==(startFromCollision.get(i)+5000)){
                        if(gameObjects.get(i) instanceof YellowEnemyModel){
                            isCollided.set(i, false);

                        }else if(gameObjects.get(i) instanceof GreenEnemyModel){
                            isCollided.set(i, false);

                        }
                    }
                }
            }

            if(Properties.getInstance().HP <=0){
                ((EpsilonModel)gameObjects.get(0)).sizeOfEpsilon.setHeight(((EpsilonModel)gameObjects.get(0)).sizeOfEpsilon.getHeight()+1);
                ((EpsilonModel)gameObjects.get(0)).sizeOfEpsilon.setWidth(((EpsilonModel)gameObjects.get(0)).sizeOfEpsilon.getWidth()+1);
            }


            for (int i = 0; i < currentShots.size(); i++) {
                shotsOfEpsilons.get(i).setPlaceX((int) (shotsOfEpsilons.get(i).getPlaceX() - shotsOfEpsilons.get(i).getDirX()));
                shotsOfEpsilons.get(i).setPlaceY((int) (shotsOfEpsilons.get(i).getPlaceY() - shotsOfEpsilons.get(i).getDirY()));

            }
            for (int i = 0; i < shotsOfEpsilons.size(); i++) {
                System.out.println("NumberOfCollision: " + shotsOfEpsilons.get(0).getNumberOfCollision());
                if (shotsOfEpsilons.get(i).getNumberOfCollision() == 1) {
                    shotsOfEpsilons.get(i).setPlaceX(1000000);
                    shotsOfEpsilons.get(i).setPlaceY(1000000);
                }
                if (shotsOfEpsilons.get(i).getPlaceX() == 2) {
                    if (Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH < GLASS_FRAME_DIMENSION_MAX_WIDTH - 20) {
                        Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH += 5;
                        boundX -= 5;
                        frame.setBounds((int) boundX, (int) boundY, (int)Properties.getInstance(). GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
                        System.out.println("\u001B[41m" + i + "\u001B[0m");

                    }
                    if (Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH > 20) {
                        Properties.getInstance(). GLASS_FRAME_DIMENSION_WIDTH -= 5;
//                        boundX+=5;
                        frame.setBounds((int) boundX, (int) boundY, (int)Properties.getInstance(). GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);

                    }
                }
                if (shotsOfEpsilons.get(i).getPlaceX() == Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH - 10) {
                    boundX += 5;
                    Properties.getInstance(). GLASS_FRAME_DIMENSION_WIDTH--;
                    frame.setBounds((int) boundX, (int) boundY, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);


                }
                if (shotsOfEpsilons.get(i).getPlaceX() <= 0 || shotsOfEpsilons.get(i).getPlaceY() <= -8 ||
                        shotsOfEpsilons.get(i).getPlaceY() >= Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT - 1 || (shotsOfEpsilons.get(i).getPlaceX() >= Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH))
                    shotsOfEpsilons.get(i).setNumberOfCollision(shotsOfEpsilons.get(i).getNumberOfCollision() + 1);



            }
            System.out.println("\u001B[41m" + shotsOfEpsilons.get(0).getPlaceX() + "\u001B[0m" + " - y:" + "\u001B[36m" + shotsOfEpsilons.get(0).getPlaceY() + "\u001B[0m");
            for (int i = 0; i < shotsOfEpsilons.size(); i++) {
                for (int j = 1; j < 3; j++) {
                    if (Math.sqrt(((shotsOfEpsilons.get(i).getPlaceX() + 5.0) - ((YellowEnemyModel) gameObjects.get(j)).getPosition().getX() - ((YellowEnemyModel) gameObjects.get(j)).getRadius()) * ((shotsOfEpsilons.get(i).getPlaceX() + 5.0) - ((YellowEnemyModel) gameObjects.get(j)).getPosition().getX() - ((YellowEnemyModel) gameObjects.get(j)).getRadius()) +
                            ((shotsOfEpsilons.get(i).getPlaceY() + 5.0) - ((YellowEnemyModel) gameObjects.get(j)).getPosition().getY() - ((YellowEnemyModel) gameObjects.get(j)).getRadius()) * ((shotsOfEpsilons.get(i).getPlaceY() + 5.0) - ((YellowEnemyModel) gameObjects.get(j)).getPosition().getY() - ((YellowEnemyModel) gameObjects.get(j)).getRadius()))
                            < 5.0 + ((YellowEnemyModel) gameObjects.get(j)).getRadius()) {
                        // گلوله حذف بشه - یه جون از جونای enemy کم بشه.
                        if(is_Writ_Of_Ares){
                            REDUCTION_RATE_OF_HP_OF_ENEMY = 2;
                        }
                        ((YellowEnemyModel) gameObjects.get(j)).setLifeValue(((YellowEnemyModel) gameObjects.get(j)).getLifeValue()-REDUCTION_RATE_OF_HP_OF_ENEMY);
//                        XXED++;
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        shotsOfEpsilons.get(i).setPlaceX(1000000);
                        shotsOfEpsilons.get(i).setPlaceY(1000000);



                    }
                }
                for (int j = 3; j < 5; j++) {
                    if (Math.sqrt(((shotsOfEpsilons.get(i).getPlaceX() + 5.0) - ((GreenEnemyModel)gameObjects.get(j)).getPosition().getX() - ((GreenEnemyModel) gameObjects.get(j)).getRadius()) * ((shotsOfEpsilons.get(i).getPlaceX() + 5.0) - ((GreenEnemyModel) gameObjects.get(j)).getPosition().getX() - ((GreenEnemyModel) gameObjects.get(j)).getRadius()) +
                            ((shotsOfEpsilons.get(i).getPlaceY() + 5.0) - ((GreenEnemyModel)gameObjects.get(j)).getPosition().getY() - ((GreenEnemyModel) gameObjects.get(j)).getRadius()) * ((shotsOfEpsilons.get(i).getPlaceY() + 5.0) - ((GreenEnemyModel) gameObjects.get(j)).getPosition().getY() - ((GreenEnemyModel) gameObjects.get(j)).getRadius()))
                            < 5.0 + ((GreenEnemyModel) gameObjects.get(j)).getRadius()) {
                        // گلوله حذف بشه - یه جون از جونای enemy کم بشه.
                        ((GreenEnemyModel) gameObjects.get(j)).setLifeValue(((GreenEnemyModel) gameObjects.get(j)).getLifeValue()-1);
                        Properties.getInstance().HP++;
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        shotsOfEpsilons.get(i).setPlaceX(1000000);
                        shotsOfEpsilons.get(i).setPlaceY(1000000);


                    }
                }


            }
            for (int i = 1; i < 3; i++) {
                ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - gameObjects.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - gameObjects.get(i).getPosition().getY())));
                ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
            }
            for (int i = 3; i < 5; i++) {
                ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - gameObjects.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - gameObjects.get(i).getPosition().getY())));
                ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
            }

            for (int j = 1; j < 3; j++) {
                if (!isCollided.get(j)) {
                    for (int i = 1; i < 3; i++) {
                        if (i == j) {
                            ACCELERATION_OF_YELLOW_ENEMIES = 2;
                            gameObjects.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
                            if(waveOHB){
                                isCollided.set(i,true);
                                waveOHB=false;
                            }

                        }

                    }

                }
                if (isCollided.get(j)) {
                    for (int i = 1; i < 3; i++) {
                        if (i == j) {
                            ACCELERATION_OF_YELLOW_ENEMIES = 4;
                            gameObjects.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, -((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getX(), -((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
                        }
                    }

                }
            }
            for (int j = 3; j < 5; j++) {
                if (!isCollided.get(j)) {
                    for (int i = 3; i < 5; i++) {
                        if (i == j) {
                            ACCELERATION_OF_GREEN_ENEMIES = 2;
                            gameObjects.get(i).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().getX(), ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().getY()));
                            if(waveOHB){
                                isCollided.set(i,true);
                                waveOHB=false;
                            }
                        }


                    }

                }
                if (isCollided.get(j)) {
                    for (int i = 3; i < 5; i++) {
                        if (i == j) {
                            ACCELERATION_OF_GREEN_ENEMIES = 4;
                            gameObjects.get(i).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, -((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().getX(), -((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().getY()));
                        }
                    }

                }
            }
        }
        System.out.println("x of moving enemy: " + gameObjects.get(1).getPosition().getX());
        System.out.println("x of moving epsilon: " + gameObjects.get(0).getPosition().getX());
        for (int k = 1; k < 5; k++) {

            for (int i = 1; i < 3; i++) {
                for (int j = 3; j < 5; j++) {
                    double centerOfEnemy1X = gameObjects.get(i).getPosition().getX() + ((YellowEnemyModel) gameObjects.get(i)).getRadius();
                    double centerOfEnemy2X = gameObjects.get(j).getPosition().getX() + ((GreenEnemyModel) gameObjects.get(j)).getRadius();
                    double centerOfEnemy1Y = gameObjects.get(i).getPosition().getY() + ((YellowEnemyModel) gameObjects.get(i)).getRadius();
                    double centerOfEnemy2Y = gameObjects.get(j).getPosition().getY() + ((GreenEnemyModel) gameObjects.get(j)).getRadius();

                    if (Math.sqrt(((centerOfEnemy1X - centerOfEnemy2X) * (centerOfEnemy1X - centerOfEnemy2X)) + ((centerOfEnemy1Y - centerOfEnemy2Y) * (centerOfEnemy1Y - centerOfEnemy2Y))) <= (((YellowEnemyModel) gameObjects.get(i)).getRadius() + ((GreenEnemyModel) gameObjects.get(j)).getRadius())) {
                        System.out.println(":)");
                        ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(i).getPosition().getX() + gameObjects.get(j).getPosition().getX() + 25, -gameObjects.get(j).getPosition().getY() + gameObjects.get(i).getPosition().getY() + 25)));
                        ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
 //                       ((GreenEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(j).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(i).getPosition().getY() + gameObjects.get(j).getPosition().getY() + 25)));
    //                    ((GreenEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().normalize();
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(j).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(i).getPosition().getY() + gameObjects.get(j).getPosition().getY() + 25)));
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().normalize();

                        isCollidedEnemies.set(i,true);// : **********is needed???  - : yes!
                        isCollidedEnemies.set(j,true);
                        startFromCollision.put(i,spentMilliSecond);
                        startFromCollision.put(j,spentMilliSecond);

                        collidedOneGY = i;
                        collidedTwoGY = j;
                        gameObjects.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getY()));


                    }

                }
            }

            if (gameObjects.get(k) instanceof GreenEnemyModel){
                //پلن دوم:
                for (int i = 3; i < 5; i++) {
                    double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                    double centerOfEnemyX = gameObjects.get(i).getPosition().getX() + ((GreenEnemyModel) gameObjects.get(i)).getRadius();
                    double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                    double centerOfEnemyY = gameObjects.get(i).getPosition().getY() + ((GreenEnemyModel) gameObjects.get(i)).getRadius();

//                    if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) == (((EpsilonModel) gameObjects.get(0)).getRadius() + ((GreenEnemyModel) gameObjects.get(i)).getRadius()+1)) {
//
//                    }

                        if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) < (((EpsilonModel) gameObjects.get(0)).getRadius() + ((GreenEnemyModel) gameObjects.get(i)).getRadius())) {
                        System.out.println(":)");
                        //((((((((((((+((*))+))))))))))))\\
//
                        ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(-gameObjects.get(0).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(0).getPosition().getY() + gameObjects.get(i).getPosition().getY() + 25)));
                        ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
                            Properties.getInstance(). HP -=6;
                        stateCounterG = counters.get(1);
                        isCollided.set(i, true);
                        gameObjects.get(i).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().getX(), ((GreenEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
//                isImpact1 = false;
//                isImpact2 = true;
//                با int counter استیت بندی می کنیم.
                    }
                }
                for (int i = 3; i < 5; i++) {
                    for (int j = i; j < 5; j++) {
                        if (i != j) {
                            double centerOfEnemy1X = gameObjects.get(i).getPosition().getX() + ((GreenEnemyModel) gameObjects.get(i)).getRadius();
                            double centerOfEnemy2X = gameObjects.get(j).getPosition().getX() + ((GreenEnemyModel) gameObjects.get(j)).getRadius();
                            double centerOfEnemy1Y = gameObjects.get(i).getPosition().getY() + ((GreenEnemyModel) gameObjects.get(i)).getRadius();
                            double centerOfEnemy2Y = gameObjects.get(j).getPosition().getY() + ((GreenEnemyModel) gameObjects.get(j)).getRadius();


                            if (Math.sqrt(((centerOfEnemy1X - centerOfEnemy2X) * (centerOfEnemy1X - centerOfEnemy2X)) + ((centerOfEnemy1Y - centerOfEnemy2Y) * (centerOfEnemy1Y - centerOfEnemy2Y))) <= (((GreenEnemyModel) gameObjects.get(i)).getRadius() + ((GreenEnemyModel) gameObjects.get(j)).getRadius())) {
                                System.out.println(":)");
                                ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(-gameObjects.get(i).getPosition().getX() + gameObjects.get(j).getPosition().getX() + 25, -gameObjects.get(j).getPosition().getY() + gameObjects.get(i).getPosition().getY() + 25)));
                                ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(j).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(i).getPosition().getY() + gameObjects.get(j).getPosition().getY() + 25)));
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().normalize();
                                startFromCollision.put(i,spentMilliSecond);

                                isCollidedEnemies.set(i, true);
//                                isCollidedEnemies.set(j, true);
                                collidedOneG = i;
                                collidedTwoG = j;
                                gameObjects.get(i).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().getX(), ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().getY()));
//                                gameObjects.get(j).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().getY()));


                            }
                        }
                    }
                }

            }


            if (gameObjects.get(k) instanceof YellowEnemyModel) {
                // این پلن جواب نداد :( برای برخورد
//            if(isImpact&&Math.sqrt(((gameObjects.get(0).getPosition().getX()+25)-(gameObjects.get(1).getPosition().getX()+17))*((gameObjects.get(0).getPosition().getX()+25)-(gameObjects.get(1).getPosition().getX()+17)) +
//                    ((gameObjects.get(0).getPosition().getX()+25)-(gameObjects.get(1).getPosition().getY()+30))*((gameObjects.get(0).getPosition().getY()+30)-(gameObjects.get(1).getPosition().getY()+30))) == 45.0 ){
//                ((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().setVector1(new Vector2D(((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getX(),- ((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getY()));
//            isImpact = false;
//            }
//            System.out.println("second Xdir: " + ((YellowEnemyModel) gameObjects.get(1)).getMovementOfYellowEnemy().getVector1().getX());
                //پلن دوم:
                for (int i = 1; i < 3; i++) {
                    double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                    double centerOfEnemyX = gameObjects.get(i).getPosition().getX() + ((YellowEnemyModel) gameObjects.get(i)).getRadius();
                    double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                    double centerOfEnemyY = gameObjects.get(i).getPosition().getY() + ((YellowEnemyModel) gameObjects.get(i)).getRadius();


                    if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) < (((EpsilonModel) gameObjects.get(0)).getRadius() + ((YellowEnemyModel) gameObjects.get(i)).getRadius())) {
                        System.out.println(":)");
                        ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(0).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(0).getPosition().getY() + gameObjects.get(i).getPosition().getY() + 25)));
                        ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
                        startFromCollision.put(i,spentMilliSecond);
                        Properties.getInstance(). HP -=5;
                        stateCounter = counters.get(1);
                        isCollided.set(i, true);
                        gameObjects.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
//                isImpact1 = false;
//                isImpact2 = true;
//                با int counter استیت بندی می کنیم.
                    }
                }
                for (int i = 1; i < 3; i++) {
                    for (int j = i; j < 3; j++) {
                        if (i != j) {
                            double centerOfEnemy1X = gameObjects.get(i).getPosition().getX() + ((YellowEnemyModel) gameObjects.get(i)).getRadius();
                            double centerOfEnemy2X = gameObjects.get(j).getPosition().getX() + ((YellowEnemyModel) gameObjects.get(j)).getRadius();
                            double centerOfEnemy1Y = gameObjects.get(i).getPosition().getY() + ((YellowEnemyModel) gameObjects.get(i)).getRadius();
                            double centerOfEnemy2Y = gameObjects.get(j).getPosition().getY() + ((YellowEnemyModel) gameObjects.get(j)).getRadius();


                            if (Math.sqrt(((centerOfEnemy1X - centerOfEnemy2X) * (centerOfEnemy1X - centerOfEnemy2X)) + ((centerOfEnemy1Y - centerOfEnemy2Y) * (centerOfEnemy1Y - centerOfEnemy2Y))) <= (((YellowEnemyModel) gameObjects.get(i)).getRadius() + ((YellowEnemyModel) gameObjects.get(j)).getRadius())) {
                                System.out.println(":)");
                                ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(i).getPosition().getX() + gameObjects.get(j).getPosition().getX() + 25, -gameObjects.get(j).getPosition().getY() + gameObjects.get(i).getPosition().getY() + 25)));
                                ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(j).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(i).getPosition().getY() + gameObjects.get(j).getPosition().getY() + 25)));
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().normalize();

                                isCollidedEnemies.set(i, true);
//                                isCollidedEnemies.set(j, true);
                                collidedOne = i;
                                collidedTwo = j;
                                gameObjects.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
//                                gameObjects.get(j).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().getY()));


                            }
                        }
                    }
                }

            }
        }
        if( isUnvisible&& ((EpsilonModel)gameObjects.get(0)).sizeOfEpsilon.getWidth() >= PANEL_SIZE.getWidth()){
            play = false;
            if (isValid8) {
                panel.setVisible(false);
//                frame.dispose();
                disposer();
                new GameOverFrame();
                isValid8 = false;
                isUnvisible = false;

            }

        }

        time.restart();
        panel.repaint();
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(IS_VALID_STORE){
            if (e.getKeyCode() == KeyEvent.VK_1) {
                new StoreFrame();
                play=false;
            }
        }
            if (e.getKeyCode() == KeyEvent.VK_L){
                lineShower2 = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_K){
                lineShower2 = false;
            }

        if (e.getKeyCode() == KeyEvent.VK_O) {
            System.out.println("e1xCenter:" + yellowEnemies.get(0).getYE_posX1() + " e2xCenter:" + yellowEnemies.get(1).getYE_posX1());
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            disposer();
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            play = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            play = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            validShots.set(shotCounter, true);
            currentShotCounter = shotCounter;

            double dX = clickedX - playerX;
            double dY = clickedY - playerY;
            System.out.println("dx: " + dX + "dy: " + dY + "dy/dx: " + dY / dX);
            shotsOfEpsilons.get(currentShotCounter).setDirX(-dX / 100);
            shotsOfEpsilons.get(currentShotCounter).setDirY(-dY / 100);
            shotsOfEpsilons.get(currentShotCounter).setPlaceX(playerX + 15);
            shotsOfEpsilons.get(currentShotCounter).setPlaceY(playerY);
            currentShots.add(shotsOfEpsilons.get(currentShotCounter));
            shotCounter++;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setDefaultShots() {
        for (int i = 0; i < numberOfShots; i++) {
            shotsOfEpsilons.add(i, new ShotOfEpsilon(-1, 2, 100000000 + 15, 1000000000));  // برای مقدار واقعی، dir ها رو یه منفی ضرب کن
        }
        for (int i = 0; i < numberOfShots; i++) {
            validShots.add(i, false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        clickedX = e.getX();
        clickedY = e.getY();
        validShots.set(shotCounter, true);
        currentShotCounter = shotCounter;
        double dX = clickedX - gameObjects.get(0).getPosition().getX();
        double dY = clickedY - gameObjects.get(0).getPosition().getY();
        shotsOfEpsilons.get(currentShotCounter).setDirX(-dX / 90);
        shotsOfEpsilons.get(currentShotCounter).setDirY(-dY / 90);
        shotsOfEpsilons.get(currentShotCounter).setPlaceX(gameObjects.get(0).getPosition().getX() + 15);
        shotsOfEpsilons.get(currentShotCounter).setPlaceY(gameObjects.get(0).getPosition().getY() + 15);
        currentShots.add(shotsOfEpsilons.get(currentShotCounter));
        shotCounter++;

    }

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
        System.out.println(startX);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (startX == e.getX()) {
            timer1Starter = true;
            play = true;

        }


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();


    }

    public void reversePointer() {
        if (!play && numberOfRun == currentNumOfRun) {
            startX = -startX;
        }
    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }


    public void playMusic(boolean isValid) {
        if (isValid) {
            try {
                File musicPath = new File("src/open-fields-aaron-paul-low-main-version-25198-02-16.wav");
                if (musicPath.exists()) {
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(-40.0f);
//                    System.out.println(gainControl.getValue());
                    gainControl.setValue((float) 86* InformationsOfSettings.getVolume()/100 - 80);
                } else {
                    System.out.println("Music is not found.");
                }
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static GameFrame getINSTANCE() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new GameFrame();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }

    public static ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
    public void disposer(){
//        time=null;
        time.restart();
        time.stop();
        timer1.restart();
        timer1.stop();
        timerOfGame.reset();
        timerOfGame.stop();
//        INSTANCE = null;
        panel.removeAll();
//        gameObjects.clear();
//        shotsOfEpsilons.clear();
//        currentShots.clear();
        Properties.getInstance(). HP = 100;
        Properties.getInstance(). XP= 0.0;
//        timerOfGame = null;
    }
    public void makeInvisible(){
        frame.setVisible(false);
    }
    public void makeVisible(){
        frame.setVisible(true);
    }

}
