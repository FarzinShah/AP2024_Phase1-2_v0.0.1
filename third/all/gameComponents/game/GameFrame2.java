package third.all.gameComponents.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import third.all.controller.componentController.*;
import third.all.controller.entity.EpsilonModel;
import third.all.controller.entity.GameObject;
import third.all.controller.entity.GreenEnemyModel;
import third.all.controller.entity.YellowEnemyModel;
import third.all.controller.movement.MovementOfGreenEnemy;
import third.all.controller.movement.MovementOfYellowEnemy;
import third.all.controller.movement.Position;
import third.all.controller.movement.Vector2D;
import third.all.data.*;
import third.all.data.Properties;

import third.all.gameComponents.extendedComponents.SkillTreeFrame;
import third.all.model.*;
import third.all.gameComponents.preGameComponent.GameOverFrame;
import third.all.gameComponents.preGameComponent.InformationsOfSettings;
import third.all.gameComponents.extendedComponents.StoreFrame;
import third.all.gameComponents.preGameComponent.Timer1;
import third.all.shop.Shopping;


import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;
import java.util.List;

import static third.all.controller.Constants.*;
import static third.all.controller.Variables.rng;
import static third.all.gameComponents.game.FunctionalMethods.*;
import static third.all.gameComponents.game.MyPanel.*;
import static third.all.gameComponents.preGameComponent.MapGenerator.*;
import static third.all.gameComponents.preGameComponent.Settings.informationsOfSettings;
import static third.all.gameComponents.preGameComponent.Timer1.*;
import static third.all.data.Booleans.*;
import static third.all.gameComponents.game.Timers.*;



public class GameFrame2 implements KeyListener, ActionListener, MouseMotionListener, MouseListener {
    MyPanel panel;
    MyFrame frame;
    private static final Logger logger = LoggerFactory.getLogger(GameFrame2.class);

    public static GameFrame INSTANCE;
    public static int score = 0;
    public static Timer time;
    private int delay = 0;
    private Clip clip;
    private Clip clip2;

    private FloatControl volumeControl;
    boolean isUnvisible = true;
    FunctionalMethods functionalMethod;
    private int numberOfRun = 0;
    boolean boo = true;


    private ArrayList<Integer> startedTimeOfCollectiblesG;
    private ArrayList<Integer> startedTimeOfCollectiblesY;

    public Omenoct omenoct;
    public Necropick necropick;
    public Archmire archmire;



    public static boolean isValid7 = false;
    public static boolean isValid8 = true;

    public static boolean isValidStore = true;
    public static ArrayList<GameObject> gameObjects;
    public static List<YellowEnemyModel> yellowEnemies1;
    public static ArrayList<GameObject> greenEnemies1;



    int collidedOne = -5;
    int collidedTwo = -5;
    int collidedOneG = -6;
    int collidedTwoG = -6;
    int collidedOneGY = -6;
    int collidedTwoGY = -6;


    private boolean lineShower2 = false;



    private final LinkedList<Integer> counters = new LinkedList<>();
    HashMap<Integer, Integer> startFromCollision = new HashMap<>();
    private Integer stateCounter;
    private Integer stateCounterG;

    static int numberOfShots = 100;


    public static int startX = 345, startY = 520;

    //helper booleans:
    int collided = -5;



    private LinkedList<Position> positionsOfCollectiblesG = new LinkedList<>();


    Input input;
    TargetWithMouse targetWithMouse;
    public static TWM_Item_Model twm_item_model; // can be singleton
    public static Shot_Item_Model shot_item_model;// can be singleton
    public static ArrayList<TKM2_Item_Model> collectibleItems;// can't be singleton

    public static ArrayList<TKM2_Item_Model> collectibleItemsG;// can't be singleton


    public static ArrayList<Panel> blackOrbPanels;

    //TODO: data:
    BooleansOfEnemies booleansOfEnemies;
    BooleansOf_IsValidToShow booleansOfIsValidToShow;
    Properties properties;

    static int[] boHelper = {5000,0};
    boolean cerberusBool = true;



    public GameFrame2() throws UnsupportedAudioFileException, IOException {
        frame = new MyFrame();
        playBackgroundMusic();

        logger.info("GameFrame initialized.");
        functionalMethod = new FunctionalMethods();
        booleansOfEnemies = BooleansOfEnemies.getInstance();
        booleansOfIsValidToShow = BooleansOf_IsValidToShow.getInstance();
        omenoct = Omenoct.getInstance();
        omenoct.setLocation(OMENOCT_POSITION);
        omenoct.setSize(OMENOCT_SIZE);
        necropick = Necropick.getInstance();
        necropick.setLocation(NECROPICK_POSITION);
        necropick.setSize(NECROPICK_SIZE);
        archmire = Archmire.getInstance();
        archmire.setLocation(ARCHMIRE_POSITION);
        archmire.setSize(ARCHMIRE_SIZE);
        yellowEnemies1 = new ArrayList<YellowEnemyModel>();
        greenEnemies1 = new ArrayList<>();
        blackOrbPanels = new ArrayList<>();
        yellowEnemies_triangles.clear();
        yellowEnemies.clear();
        input = new Input();
        panel = new MyPanel(input);





        addBlackOrbsTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boHelper[0]-=1000;
                if (boHelper[1] < 5) {
                    BooleansOf_IsValidToShow.getInstance().getIsValidToShowBlackOrbPanels().set(boHelper[1],true);
                    boHelper[1]++;
                } else {
                    boHelper[1] = 0;
                    addBlackOrbsTimer.stop();
                }
                if(boHelper[1]==5) BooleansOf_IsValidToShow.getInstance().getIsValidToShowBlackOrbPanels().set(5,true);

                if(boHelper[0]<=0 ){
                    addBlackOrbsTimer.stop();
                }
            }
        });


        addArchmirePrintTimer = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Archmire.getInstance().getTrails().add(new Point(archmire.getLocation().x, archmire.getLocation().y));

            }
        });
        cerberusAttackTimer = new Timer(15000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BooleansOf_IsValidToShow.getInstance().setValidToAttackCerberus(true);
                cerberusBool = true;
            }
        });
//        addArchmirePrintTimer.start(); //todo: هرموقع وقتش شد استارتش کنم
        archmirePrintTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Archmire.getInstance().getTrails().size() > 2) {
                    Archmire.getInstance().getTrails().remove(0);
                    Archmire.getInstance().getTrails().remove(0);

                }
                if (!BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(0)){
                    archmirePrintTimer.stop();
                }
            }
        });
//        archmirePrintTimer.start();//todo: هرموقع وقتش شد استارتش کنم


        int maxSize = Math.max(720, 1440) / 3;

//        setDefaultShots();

        shotTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                omenoct_shooter();
            }
        });
//        shotTimer.start(); //todo: هرموقع وقتش شد استارتش کنم

        necropickShower = new Timer(12000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                necropickHide();
            }
        });
//        necropickShower.start(); //todo: هرموقع وقتش شد استارتش کنم

        necropickShooter = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                necropickShoot();
            }
        });
//        necropickShooter.start(); //todo: هرموقع وقتش شد استارتش کنم

        wyrmShooter = new Timer(700, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Wyrm.getInstance().isValidToShoot()) wyrmShoot();
                if (Wyrm.getInstance().getHP()<=0) wyrmShooter.stop();
            }
        });
//        wyrmShooter.start(); //todo: هرموقع وقتش شد استارتش کنم


//        if (informationsOfSettings.isPlay()) {
//            playMusic(informationsOfSettings.isPlay());
//        }
        timerOfGame = new Timer1();
        targetWithMouse = new TargetWithMouse(new Point2D.Double(0, 0));
        twm_item_model = new TWM_Item_Model(new Point2D.Double(rng(STARTING_POINT.x + 50.0, STARTING_POINT.y + 300.0), rng(STARTING_POINT.x + 50.0, STARTING_POINT.y + 300.0)));
        panel.setTwm_item_model1(twm_item_model);
        gameObjects = new ArrayList<>();

        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0)); // todo: همینه که کار رو درمیاره :)))))))
        frame.setVisible(true);
        frame.add(panel);
//        frame.add(panel3);;
//        panel3.setVisible(true);
//        frame2.add(panel);
//        frame.add(panel2);


        counters.add(0);
        counters.add(1);
        counters.add(2);
        counters.add(3);
        counters.add(4);
        counters.add(5);
        counters.add(6);
        counters.add(7);
        showOfCollectiblesG = new ArrayList<>();
        showOfCollectiblesHelperG = new ArrayList<>();
        startedTimeOfCollectiblesG = new ArrayList<>();
        showOfCollectiblesHelperY = new ArrayList<>();
        startedTimeOfCollectiblesY = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            showOfCollectiblesG.add(i, false);
            showOfCollectiblesHelperG.add(i, true);
            showOfCollectiblesHelperY.add(i, true);
            BooleansOfCollectibles.getInstance().getIsValidYEtoCollect().add(i,true);
            BooleansOfCollectibles.getInstance().getIsValidOrbToCollect().add(i,true);

        }

        for (int i = 0; i < 100; i++) {
            startedTimeOfCollectiblesG.add(i, -1);
            startedTimeOfCollectiblesY.add(i, -1);
        }

//        Arrays.fill(showOfCollectibles, false);
        collectibleItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            collectibleItems.add(i, new TKM2_Item_Model(new Position(-1, -1)));
        }
        collectibleItemsG = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            collectibleItemsG.add(i, new TKM2_Item_Model(new Position(-1, -1)));
        }
        stateCounter = counters.get(0);

        panel.addKeyListener(this);

        time = new Timer(delay, this);
        time.start();
        timer1 = new Timer(0, this);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addMouseListener(targetWithMouse);
        panel.addMouseMotionListener(targetWithMouse);


        frame.setBounds((int) 0, (int) 0, (int) 1440, (int) 720); // todo:

        properties = Properties.getInstance();
        gameObjects.add(new EpsilonModel(new EpsilonController(input), 230, 330));

//        for (int i = 0; i < 1; i++) {
//            yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input),20,10, 30));
//        }

        for (int i = 0; i < 2; i++) { // 10
            gameObjects.add(new YellowEnemyModel(new YellowEnemyController(input), 20, rng(-300, -10), rng(100, 200))); // این posX و posY نقطه گوشه بالا چپ رو معلوم میکنن.
        }
        for (int i = 0; i < 2; i++) { //
            gameObjects.add(new GreenEnemyModel(new GreenEnemyController(input), 20, rng(-300, -10), rng(100, 200))); // این posX و posY نقطه گوشه بالا چپ رو معلوم میکنن.

        }


        for (int i = 0; i < 100; i++) {
            isCollidedY.add(i, false);
        }
        for (int i = 0; i < 100; i++) {
            isCollidedG.add(i, false);
        }
        for (int i = 0; i < 100; i++) {
            isCollided.add(i, false);
        }
        for (int i = 0; i < 100; i++) {
            isCollidedEnemies.add(i, false);
        }
    }

    public void update() {
        yellowEnemies1.forEach(GameObject::update);
        greenEnemies1.forEach(GameObject::update);
//        frame.update();

        //Todo: Wave 1:
        /*todo: توی این موج، که 4 دقیقه طول می کشه، هر 30 ثانیه یه انمی ساده وارد صفحه میشه. اینجا همه چی آرومه و تازه بازی شروع شده. هنوز انمی های نرمال وارد نشدن و تنها چیزی که اعمال میشه، کوچیک و بزرگ شدن فریم اصلیه. و تنها یک فریم داریم
           چیزی که هست این موج اگه زودتر از چهاردقیقه تموم بشه، موج دوم شروع میشه.
           فرایند وارد شدن انمی ها تا ثانیه 130 بازی ادامه پیدا میکنه :))
        */
        if (Properties.getInstance().STATE == 21) {

            BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().set(0, spentMilliSecond < 11000 && spentMilliSecond >= 5000);

            if (spentMilliSecond <= 4 * 60000) {
                if (spentMilliSecond == 1000) {
                    BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().set(2,true);
//                    shotTimer.start();
                }
                if (spentMilliSecond == 2000) {
                    BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(2 ,true);
                    shotTimer.start();
                }

                if (spentMilliSecond == 2000) {
                    BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(4 ,true);
                }

                if(spentMilliSecond==2000){
                    BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(6 ,true);
                }
                if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(6)){
                    writOfCerberus();
                }
                if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(6) && !BooleansOf_IsValidToShow.getInstance().isValidToAttackCerberus() && cerberusBool){
                    cerberusAttackTimer.start();
                    cerberusBool = false;
                }

                if(spentMilliSecond == 3000){
                    BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().set(6,true);
                    addBlackOrbsTimer.start();
                }
                if(spentMilliSecond == 4000){
                    BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(3,true);
                }
                if (spentMilliSecond == 5000 && booleansOfEnemies.getEnemyN().get(1)) {
                    yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 10, 10));
                    booleansOfEnemies.getEnemyN().set(1,false);
                }
                if (spentMilliSecond == 25000 && booleansOfEnemies.getEnemyN().get(2)) {
                    greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 40, 20));
                    booleansOfEnemies.getEnemyN().set(2,false);
                }
                if (spentMilliSecond == 50000 && booleansOfEnemies.getEnemyN().get(3)) {
                    yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 10, 10));
                    booleansOfEnemies.getEnemyN().set(3,false);
                }
                if (spentMilliSecond == 70000 && booleansOfEnemies.getEnemyN().get(4)) {
                    greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 60, 10));
                    booleansOfEnemies.getEnemyN().set(4,false);
                }
                if (spentMilliSecond == 100000 && booleansOfEnemies.getEnemyN().get(5)) {
                    yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 50, 100));
                    booleansOfEnemies.getEnemyN().set(5,false);
                }
                if (spentMilliSecond == 130000 && booleansOfEnemies.getEnemyN().get(6)) {
                    greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 150, 78));
                    booleansOfEnemies.getEnemyN().set(6,false);
                }

            }


            for (int i = 0; i < yellowEnemies1.size(); i++) {
                if (!isCollidedY.get(i)) {
                    ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - yellowEnemies1.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - yellowEnemies1.get(i).getPosition().getY())));
                    ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
                    ((YellowEnemyModel) yellowEnemies1.get(i)).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
                }


            }

            for (int i = 0; i < greenEnemies1.size(); i++) {
                if (!isCollidedG.get(i)) {
                    ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - greenEnemies1.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - greenEnemies1.get(i).getPosition().getY())));
                    ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
                    ((GreenEnemyModel) greenEnemies1.get(i)).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().getX(), ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().getY()));
                }
            }


            //todo :YellowEnemy:
            for (int i = 0; i < yellowEnemies1.size(); i++) {
                double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                double centerOfEnemyX = yellowEnemies1.get(i).getPosition().getX() + ((YellowEnemyModel) yellowEnemies1.get(i)).getRadius();
                double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                double centerOfEnemyY = yellowEnemies1.get(i).getPosition().getY() + ((YellowEnemyModel) yellowEnemies1.get(i)).getRadius();


                if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) < (((EpsilonModel) gameObjects.get(0)).getRadius() + ((YellowEnemyModel) yellowEnemies1.get(i)).getRadius())) {
                    System.out.println(":)");
                    ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(0).getPosition().getX() + yellowEnemies1.get(i).getPosition().getX() + 25, -gameObjects.get(0).getPosition().getY() + yellowEnemies1.get(i).getPosition().getY() + 25)));

                    ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
                    startFromCollision.put(i, spentMilliSecond);
                    Properties.getInstance().HP -= 5;
                    stateCounter = counters.get(1);
                    isCollidedY.set(i, true);
                    yellowEnemies1.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getY()));

                }
                if (isCollidedY.get(i)) {
                    if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) > 700) {
                        isCollidedY.set(i, false);

                    }
                }
            }
            for (int i = 0; i < yellowEnemies1.size(); i++) {
                for (int j = i; j < yellowEnemies1.size(); j++) {
                    if (i != j) {
                        double centerOfEnemy1X = yellowEnemies1.get(i).getPosition().getX() + ((YellowEnemyModel) yellowEnemies1.get(i)).getRadius();
                        double centerOfEnemy2X = yellowEnemies1.get(j).getPosition().getX() + ((YellowEnemyModel) yellowEnemies1.get(j)).getRadius();
                        double centerOfEnemy1Y = yellowEnemies1.get(i).getPosition().getY() + ((YellowEnemyModel) yellowEnemies1.get(i)).getRadius();
                        double centerOfEnemy2Y = yellowEnemies1.get(j).getPosition().getY() + ((YellowEnemyModel) yellowEnemies1.get(j)).getRadius();


                        if (Math.sqrt(((centerOfEnemy1X - centerOfEnemy2X) * (centerOfEnemy1X - centerOfEnemy2X)) + ((centerOfEnemy1Y - centerOfEnemy2Y) * (centerOfEnemy1Y - centerOfEnemy2Y))) <= (((YellowEnemyModel) yellowEnemies1.get(i)).getRadius() + ((YellowEnemyModel) yellowEnemies1.get(j)).getRadius())) {
                            System.out.println(":)");
                            ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(-yellowEnemies1.get(i).getPosition().getX() + yellowEnemies1.get(j).getPosition().getX() + 25, -yellowEnemies1.get(j).getPosition().getY() + yellowEnemies1.get(i).getPosition().getY() + 25)));
                            ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(j).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(i).getPosition().getY() + gameObjects.get(j).getPosition().getY() + 25)));
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().normalize();

                            isCollidedEnemies.set(i, true);
//                                isCollidedEnemies.set(j, true);
                            collidedOne = i;
                            collidedTwo = j;
                            yellowEnemies1.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
//                                gameObjects.get(j).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().getY()));


                        }
                    }
                }
            }

            for (int i = 0; i < yellowEnemies1.size(); i++) {
                ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - yellowEnemies1.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - yellowEnemies1.get(i).getPosition().getY())));
                ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
            }

            for (int j = 0; j < yellowEnemies1.size(); j++) {
                if (!isCollidedY.get(j)) {
                    for (int i = 0; i < yellowEnemies1.size(); i++) {
                        if (i == j) {
                            double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                            double centerOfEnemyX = yellowEnemies1.get(i).getPosition().getX() + ((YellowEnemyModel) yellowEnemies1.get(i)).getRadius();
                            double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                            double centerOfEnemyY = yellowEnemies1.get(i).getPosition().getY() + ((YellowEnemyModel) yellowEnemies1.get(i)).getRadius();

                            ACCELERATION_OF_YELLOW_ENEMIES = 1;
                            yellowEnemies1.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
                            if (waveOHB) {
                                isCollidedY.set(i, true);
                                waveOHB = false;
                            }

                            if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) < 150 + (((EpsilonModel) gameObjects.get(0)).getRadius() + ((YellowEnemyModel) yellowEnemies1.get(i)).getRadius())) {
                                ACCELERATION_OF_YELLOW_ENEMIES = 3;
//                                ACCELERATION_OF_EPSILON = 4;

                            } else {
                                ACCELERATION_OF_YELLOW_ENEMIES = 1;
//                                ACCELERATION_OF_EPSILON = 2;
                            }
                            ;

                        }

                    }

                }
                if (isCollidedY.get(j)) {
                    for (int i = 0; i < yellowEnemies1.size(); i++) {
                        if (i == j) {
                            ACCELERATION_OF_YELLOW_ENEMIES = 2;
                            yellowEnemies1.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, -((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getX(), -((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
                        }
                    }

                }
            }

            //todo :GreenEnemy:
            for (int i = 0; i < greenEnemies1.size(); i++) {
                double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                double centerOfEnemyX = greenEnemies1.get(i).getPosition().getX() + ((GreenEnemyModel) greenEnemies1.get(i)).getRadius();
                double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                double centerOfEnemyY = greenEnemies1.get(i).getPosition().getY() + ((GreenEnemyModel) greenEnemies1.get(i)).getRadius();


                if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) < (((EpsilonModel) gameObjects.get(0)).getRadius() + ((GreenEnemyModel) greenEnemies1.get(i)).getRadius())) {
                    System.out.println(":)");
                    ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(-gameObjects.get(0).getPosition().getX() + greenEnemies1.get(i).getPosition().getX() + 25, -gameObjects.get(0).getPosition().getY() + greenEnemies1.get(i).getPosition().getY() + 25)));

                    ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
                    startFromCollision.put(i, spentMilliSecond);
                    Properties.getInstance().HP -= 5;
                    stateCounter = counters.get(1);
                    isCollidedG.set(i, true);
                    greenEnemies1.get(i).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().getX(), ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().getY()));

                }
                if (isCollidedG.get(i)) {
                    if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) > 700) {
                        isCollidedG.set(i, false);

                    }
                }
            }
            for (int i = 0; i < greenEnemies1.size(); i++) {
                for (int j = i; j < greenEnemies1.size(); j++) {
                    if (i != j) {
                        double centerOfEnemy1X = greenEnemies1.get(i).getPosition().getX() + ((GreenEnemyModel) greenEnemies1.get(i)).getRadius();
                        double centerOfEnemy2X = greenEnemies1.get(j).getPosition().getX() + ((GreenEnemyModel) greenEnemies1.get(j)).getRadius();
                        double centerOfEnemy1Y = greenEnemies1.get(i).getPosition().getY() + ((GreenEnemyModel) greenEnemies1.get(i)).getRadius();
                        double centerOfEnemy2Y = greenEnemies1.get(j).getPosition().getY() + ((GreenEnemyModel) greenEnemies1.get(j)).getRadius();


                        if (Math.sqrt(((centerOfEnemy1X - centerOfEnemy2X) * (centerOfEnemy1X - centerOfEnemy2X)) + ((centerOfEnemy1Y - centerOfEnemy2Y) * (centerOfEnemy1Y - centerOfEnemy2Y))) <= (((GreenEnemyModel) greenEnemies1.get(i)).getRadius() + ((GreenEnemyModel) greenEnemies1.get(j)).getRadius())) {
                            System.out.println(":)");
                            ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(-greenEnemies1.get(i).getPosition().getX() + greenEnemies1.get(j).getPosition().getX() + 25, -greenEnemies1.get(j).getPosition().getY() + greenEnemies1.get(i).getPosition().getY() + 25)));
                            ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(j).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(i).getPosition().getY() + gameObjects.get(j).getPosition().getY() + 25)));
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().normalize();

                            isCollidedEnemies.set(i, true);
//                                isCollidedEnemies.set(j, true);
                            collidedOne = i;
                            collidedTwo = j;
                            greenEnemies1.get(i).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().getX(), ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().getY()));
//                                gameObjects.get(j).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().getY()));


                        }
                    }
                }
            }

            for (int i = 0; i < greenEnemies1.size(); i++) {
                ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - greenEnemies1.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - greenEnemies1.get(i).getPosition().getY())));
                ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
            }

            for (int j = 0; j < greenEnemies1.size(); j++) {
                if (!isCollidedG.get(j)) {
                    for (int i = 0; i < greenEnemies1.size(); i++) {
                        if (i == j) {
                            double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                            double centerOfEnemyX = greenEnemies1.get(i).getPosition().getX() + ((GreenEnemyModel) greenEnemies1.get(i)).getRadius();
                            double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                            double centerOfEnemyY = greenEnemies1.get(i).getPosition().getY() + ((GreenEnemyModel) greenEnemies1.get(i)).getRadius();

                            ACCELERATION_OF_GREEN_ENEMIES = 1;
                            greenEnemies1.get(i).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().getX(), ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().getY()));
                            if (waveOHB) {
                                isCollidedG.set(i, true);
                                waveOHB = false;
                            }

                            if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) < 150 + (((EpsilonModel) gameObjects.get(0)).getRadius() + ((GreenEnemyModel) greenEnemies1.get(i)).getRadius())) {
                                ACCELERATION_OF_GREEN_ENEMIES = 3;
//                                ACCELERATION_OF_EPSILON = 4;

                            } else {
                                ACCELERATION_OF_GREEN_ENEMIES = 1;
//                                ACCELERATION_OF_EPSILON = 2;
                            }

                        }

                    }

                }
                if (isCollidedG.get(j)) {
                    for (int i = 0; i < greenEnemies1.size(); i++) {
                        if (i == j) {
                            ACCELERATION_OF_GREEN_ENEMIES = 2;
                            greenEnemies1.get(i).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, -((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().getX(), -((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().getY()));
                        }
                    }

                }
            }

            //todo:collision between p1 enemies
            for (int i = 0; i < yellowEnemies1.size(); i++) {
                for (int j = 0; j < greenEnemies1.size(); j++) {
                    double distanceX = ((GameObject) yellowEnemies1.get(i)).getPosition().getX() - ((GameObject) greenEnemies1.get(j)).getPosition().getX();
                    double distanceY = ((GameObject) yellowEnemies1.get(i)).getPosition().getY() - ((GameObject) greenEnemies1.get(j)).getPosition().getY();
                    double oclidianDistance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
                    if (oclidianDistance < 2 * ((GreenEnemyModel) greenEnemies1.get(j)).getRadius() + 10) {
//                        ((GreenEnemyModel) greenEnemies1.get(j)).getMovementOfGreenEnemy().setVector1((new Vector2D(-greenEnemies1.get(j).getPosition().getX() + greenEnemies1.get(j).getPosition().getX() + 25, -greenEnemies1.get(j).getPosition().getY() + greenEnemies1.get(j).getPosition().getY() + 25)));
//                        ((GreenEnemyModel) greenEnemies1.get(j)).getMovementOfGreenEnemy().getVector1().normalize();
                        isCollidedG.set(j, true);
                        isCollidedY.set(i, true);

                    }
                }
            }


            //TODO: collectable items after destruction :  Done 50%
            //todo: خب باید هر آیتم رو خیلی ساده بزنیم. با فور و رندم

            //todo: Omenoct movement:
            if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)) {
                Rectangle epsilon = new Rectangle((int) ((EpsilonModel) gameObjects.get(0)).getPosition().getX(), (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getY(), EPSILON_WIDTH, EPSILON_LENGTH);
                int leftDistance = omenoct.getLocation().x - panels.get(0).getX();
                int rightDistance = panels.get(0).getRightX() - omenoct.getLocation().x;
                int upperDistance = omenoct.getLocation().y - panels.get(0).getY();
                int lowerDistance = panels.get(0).getDownY() - omenoct.getLocation().y;
                int tempDistance = Math.min(Math.min(leftDistance, rightDistance), Math.min(upperDistance, lowerDistance));

                //todo: panel.get(1)
                int leftDistance1 = omenoct.getLocation().x - panels.get(1).getX();
                int rightDistance1 = panels.get(1).getRightX() - omenoct.getLocation().x;
                int upperDistance1 = omenoct.getLocation().y - panels.get(1).getY();
                int lowerDistance1 = panels.get(1).getDownY() - omenoct.getLocation().y;
                int tempDistance1 = Math.min(Math.min(leftDistance1, rightDistance1), Math.min(upperDistance1, lowerDistance1));

                if (shapeIntersects(omenoct.getShape(), panels.get(0).getRectangle()) && shapeIntersects(omenoct.getShape(), panels.get(1).getRectangle())) {

                    if (leftDistance == tempDistance) {
                        omenoct.setLocation(new Point(omenoct.getLocation().x - 1, omenoct.getLocation().y));
                    } else if (rightDistance == tempDistance) {
                        omenoct.setLocation(new Point(omenoct.getLocation().x + 1, omenoct.getLocation().y));
                    } else if (upperDistance == tempDistance) {
                        if (omenoct.getLocation().y - 8 > panels.get(0).getY()) {
                            omenoct.setLocation(new Point(omenoct.getLocation().x, omenoct.getLocation().y - 1));

                        }
                        if (!isEnteredToPanel2_Omenoct && omenoct.getLocation().y - 8 == panels.get(0).getY()) {
                            if ((panels.get(0).getRectangle().contains(epsilon) && panels.get(1).getRectangle().intersects(panels.get(0).getRectangle())) || (!panels.get(1).getRectangle().contains(epsilon) && omenoct.getLocation().y - 8 == panels.get(0).getY())) {
                                if (omenoct.getLocation().x > epsilon.x) {
                                    omenoct.setLocation(new Point(omenoct.getLocation().x - 2, omenoct.getLocation().y));
                                } else if (omenoct.getLocation().x < epsilon.x) {
                                    omenoct.setLocation(new Point(omenoct.getLocation().x + 2, omenoct.getLocation().y));
                                }
                            }
                        }
                        if (panels.get(0).getRectangle().contains(epsilon)) {
                            if (omenoct.getLocation().x - (omenoct.getSize() / 2) < panels.get(0).getX()) {
                                omenoct.setLocation(new Point(panels.get(0).getX() + (omenoct.getSize() / 2), (int) omenoct.getLocation().getY()));
                            }
                        }
                        if (panels.get(0).getRectangle().contains(epsilon)) {
                            if (omenoct.getLocation().x + (2 * omenoct.getSize()) > panels.get(0).getRightX()) {
                                omenoct.setLocation(new Point(panels.get(0).getRightX() - (2 * omenoct.getSize()), (int) omenoct.getLocation().getY()));
                            }
                        }
                        if (panels.get(0).getRectangle().intersects(panels.get(1).getRectangle()) && !panels.get(0).getRectangle().contains(epsilon) && panels.get(1).getRectangle().contains(epsilon)) {
                            if (!isEnteredToPanel2_Omenoct) {
                                omenoct.setLocation(new Point(panels.get(0).getRightX(), panels.get(1).getY() + 5));
                                isEnteredToPanel2_Omenoct = true;
                            }

                        }


//                                if(omenoct.getLocation().y==panels.get(1).getY()){
//                                    if(omenoct.getLocation().x<epsilon.x){
//                                        omenoct.setLocation(new Point(omenoct.getLocation().x+1,omenoct.getLocation().y));
//                                    }else if(omenoct.getLocation().x>epsilon.x){
//                                        omenoct.setLocation(new Point(omenoct.getLocation().x-1,omenoct.getLocation().y));
//                                    }
//                                }


                    } else {
                        if (omenoct.getLocation().y + 8 < panels.get(0).getDownY()) {
                            omenoct.setLocation(new Point(omenoct.getLocation().x, omenoct.getLocation().y + 1));
                        }
                        if (omenoct.getLocation().y + 8 == panels.get(0).getDownY()) {
                            if (omenoct.getLocation().x > epsilon.x) {
                                omenoct.setLocation(new Point(omenoct.getLocation().x - 2, omenoct.getLocation().y));
                            } else if (omenoct.getLocation().x < epsilon.x) {
                                omenoct.setLocation(new Point(omenoct.getLocation().x + 2, omenoct.getLocation().y));
                            }
                        }
                        if (omenoct.getLocation().x - (omenoct.getSize() / 2) < panels.get(0).getX()) {
                            omenoct.setLocation(new Point(panels.get(0).getX() + (omenoct.getSize() / 2), (int) omenoct.getLocation().getY()));
                        }
                        if (omenoct.getLocation().x + (omenoct.getSize() / 2) > panels.get(0).getRightX()) {
                            omenoct.setLocation(new Point(panels.get(0).getRightX() - (omenoct.getSize() / 2), (int) omenoct.getLocation().getY()));
                        }
                    }


                }
                else if (shapeIntersects(omenoct.getShape(), panels.get(1).getRectangle()) && !shapeIntersects(omenoct.getShape(), panels.get(0).getRectangle())) {

                    if (leftDistance1 == tempDistance1) {
                        omenoct.setLocation(new Point(omenoct.getLocation().x - 1, omenoct.getLocation().y));
                    } else if (rightDistance1 == tempDistance1) {
                        omenoct.setLocation(new Point(omenoct.getLocation().x + 1, omenoct.getLocation().y));
                    } else if (upperDistance1 == tempDistance1) {
                        if (omenoct.getLocation().y - 8 > panels.get(1).getY()) {
                            omenoct.setLocation(new Point(omenoct.getLocation().x, omenoct.getLocation().y - 1));

                        }

                    }

                    if (!panels.get(0).getRectangle().contains(omenoct.getRectangle()) && panels.get(1).getRectangle().contains(omenoct.getRectangle())) {
                        if (omenoct.getLocation().x < epsilon.x) {
                            omenoct.setLocation(new Point(omenoct.getLocation().x + 2, omenoct.getLocation().y));
                        } else if (omenoct.getLocation().x > epsilon.x) {
                            omenoct.setLocation(new Point(omenoct.getLocation().x - 2, omenoct.getLocation().y));
                        }
                    }
//                    if(panels.get(0).getRectangle().contains(epsilon)&&!panels.get(1).getRectangle().contains(epsilon)){
//                        if(!isEnteredToPanel1_Omenoct) {
//                            omenoct.setLocation(new Point(epsilon.x, panels.get(0).getY()));
//                            isEnteredToPanel1_Omenoct = true;
//                        }
//                    }
                }
                else if (!shapeIntersects(omenoct.getShape(), panels.get(1).getRectangle()) && shapeIntersects(omenoct.getShape(), panels.get(0).getRectangle())) {
                    if (leftDistance == tempDistance) {
                        omenoct.setLocation(new Point(omenoct.getLocation().x - 1, omenoct.getLocation().y));
                    } else if (rightDistance == tempDistance) {
                        omenoct.setLocation(new Point(omenoct.getLocation().x + 1, omenoct.getLocation().y));
                    } else if (upperDistance == tempDistance) {
                        if (omenoct.getLocation().y - 8 > panels.get(0).getY()) {
                            omenoct.setLocation(new Point(omenoct.getLocation().x, omenoct.getLocation().y - 1));

                        }
                        if (!isEnteredToPanel2_Omenoct && omenoct.getLocation().y - 8 == panels.get(0).getY()) {
                            if ((panels.get(0).getRectangle().contains(epsilon) && panels.get(1).getRectangle().intersects(panels.get(0).getRectangle())) || (!panels.get(1).getRectangle().contains(epsilon) && omenoct.getLocation().y - 8 == panels.get(0).getY())) {
                                if (omenoct.getLocation().x > epsilon.x) {
                                    omenoct.setLocation(new Point(omenoct.getLocation().x - 2, omenoct.getLocation().y));
                                } else if (omenoct.getLocation().x < epsilon.x) {
                                    omenoct.setLocation(new Point(omenoct.getLocation().x + 2, omenoct.getLocation().y));
                                }
                            }
                        }
                        if (panels.get(0).getRectangle().contains(epsilon)) {
                            if (omenoct.getLocation().x - (omenoct.getSize() / 2) < panels.get(0).getX()) {
                                omenoct.setLocation(new Point(panels.get(0).getX() + (omenoct.getSize() / 2), (int) omenoct.getLocation().getY()));
                            }
                        }
                        if (panels.get(0).getRectangle().contains(epsilon)) {
                            if (omenoct.getLocation().x + (2 * omenoct.getSize()) > panels.get(0).getRightX()) {
                                omenoct.setLocation(new Point(panels.get(0).getRightX() - (2 * omenoct.getSize()), (int) omenoct.getLocation().getY()));
                            }
                        }
                        if (panels.get(0).getRectangle().intersects(panels.get(1).getRectangle()) && !panels.get(0).getRectangle().contains(epsilon) && panels.get(1).getRectangle().contains(epsilon)) {
                            if (!isEnteredToPanel2_Omenoct) {
                                omenoct.setLocation(new Point(panels.get(0).getRightX(), panels.get(1).getY() + 5));
                                isEnteredToPanel2_Omenoct = true;
                            }

                        }

                    }

                }

                if(panels.get(0).getRectangle().contains(omenoct.getRectangle())){
                    isEnteredToPanel1_Omenoct = true;
                }else if(panels.get(1).getRectangle().contains(omenoct.getRectangle())){
                    isEnteredToPanel2_Omenoct = true;
                }else if(!panels.get(0).getRectangle().contains(omenoct.getRectangle())){
                    isEnteredToPanel1_Omenoct = false;
                }else if(!panels.get(1).getRectangle().contains(omenoct.getRectangle())){
                    isEnteredToPanel2_Omenoct = false;
                }

            }


            //TODO: Archmire movement:
            if (booleansOfIsValidToShow.getIsValidToShowEnemies().get(0)) {
                if (archmire.getLocation().x < panels.get(0).getRightX() - archmire.getSize() - 50 && isRightMoveArchmire) {
                    archmire.setLocation(new Point(archmire.getLocation().x + 1, archmire.getLocation().y));
                } else if (archmire.getLocation().x == panels.get(0).getRightX() - archmire.getSize() - 50) {
                    isRightMoveArchmire = false;
                } else if (archmire.getLocation().x > panels.get(0).getX() && !isRightMoveArchmire) {
                    archmire.setLocation(new Point(archmire.getLocation().x - 1, archmire.getLocation().y));

                } else if (archmire.getLocation().x == panels.get(0).getX()) isRightMoveArchmire = true;
            }


            //todo: archmire:
            if (booleansOfIsValidToShow.getIsValidToShowEnemies().get(0) && spentMilliSecond == 5000) {
                archmirePrintTimer.start();
                addArchmirePrintTimer.start();
            }

            //todo: BlackOrbs:
            for (int i = 0; i < Orb.getInstance().size(); i++) {
                if(Orb.getInstance().get(i).getHP()<=0){
                    if(BooleansOfCollectibles.getInstance().getIsValidOrbToCollect().get(i)) {
                        CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().add(new Collectable(Orb.getInstance().get(i).getLocation().x, Orb.getInstance().get(i).getLocation().y + 10, 10, Color.LIGHT_GRAY));
                        CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().add(new Collectable(Orb.getInstance().get(i).getLocation().x + 25, Orb.getInstance().get(i).getLocation().y - 5, 10, Color.LIGHT_GRAY));
                        CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().add(new Collectable(Orb.getInstance().get(i).getLocation().x + 40, Orb.getInstance().get(i).getLocation().y + 10, 10, Color.LIGHT_GRAY));
                        CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().add(new Collectable(Orb.getInstance().get(i).getLocation().x - 5, Orb.getInstance().get(i).getLocation().y - 5, 10, Color.LIGHT_GRAY));
                        CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().add(new Collectable(Orb.getInstance().get(i).getLocation().x - 20, Orb.getInstance().get(i).getLocation().y + 10, 10, Color.LIGHT_GRAY));
                        BooleansOfCollectibles.getInstance().getIsValidOrbToCollect().set(i,false);
                    }
                }
            }
            //todo: Wyrm
            if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(3)){
                double distanceX = (int) (gameObjects.get(0).getPosition().getX() - Wyrm.getInstance().getLocation().x);
                double distanceY = (int) (gameObjects.get(0).getPosition().getY() - Wyrm.getInstance().getLocation().y);
                double oDistance = Math.sqrt(distanceX*distanceX+distanceY*distanceY);
                if(oDistance > 300){
                    if(Wyrm.getInstance().getLocation().x>gameObjects.get(0).getPosition().getX()) {
                        Wyrm.getInstance().setLocation(new Point(Wyrm.getInstance().getLocation().x-4, Wyrm.getInstance().getLocation().y));
                    }else if(Wyrm.getInstance().getLocation().x<gameObjects.get(0).getPosition().getX()){
                        Wyrm.getInstance().setLocation(new Point(Wyrm.getInstance().getLocation().x+4, Wyrm.getInstance().getLocation().y));
                    }else if(Wyrm.getInstance().getLocation().y>gameObjects.get(0).getPosition().getY()){
                        Wyrm.getInstance().setLocation(new Point(Wyrm.getInstance().getLocation().x, Wyrm.getInstance().getLocation().y-4));
                    }else if(Wyrm.getInstance().getLocation().y<gameObjects.get(0).getPosition().getY()){
                        Wyrm.getInstance().setLocation(new Point(Wyrm.getInstance().getLocation().x, Wyrm.getInstance().getLocation().y+4));
                    }

                }else if(oDistance<300){
                    Wyrm.getInstance().setValidToShoot(true);
                }
            }

            if(Wyrm.getInstance().isValidToShoot()){
                wyrmShooter.start();
            }

            if (Wyrm.getInstance().getHP()<=0){
                BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(3,false);
                if(BooleansOfCollectibles.getInstance().isValidToCollectWyrm()){
                    CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().add(new Collectable(Wyrm.getInstance().getLocation().x, Wyrm.getInstance().getLocation().y + 10, 10, Color.LIGHT_GRAY));
                    CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().add(new Collectable(Wyrm.getInstance().getLocation().x - 20, Wyrm.getInstance().getLocation().y + 10, 10, Color.LIGHT_GRAY));
                    BooleansOfCollectibles.getInstance().setValidToCollectWyrm(false);
                }
                wyrmShooter.stop();
            }







            if (Properties.getInstance().WAVE == 1 && (!booleansOfEnemies.getEnemyN().get(1) && !booleansOfEnemies.getEnemyN().get(2) && !booleansOfEnemies.getEnemyN().get(3) && !booleansOfEnemies.getEnemyN().get(4) && !booleansOfEnemies.getEnemyN().get(5) && !booleansOfEnemies.getEnemyN().get(6))) {
                int count = 0;
                for (GameObject gameObject : greenEnemies1) {
                    for (GameObject object : yellowEnemies1) {
                        if (((GreenEnemyModel) gameObject).getLifeValue() > 0 && ((YellowEnemyModel) object).getLifeValue() > 0) {
                            count++;
                        }

                    }

                }
                if (count == 0) isFinishedWave1 = true;
                if (isFinishedWave1) {
                    Properties.getInstance().WAVE = 2;
                }
            }
            if(Properties.getInstance().WAVE == 1 && Properties.getInstance().HP <= 0){
                FunctionalMethods.loadGameState(input);
            }
            //Todo: Wave 2:
        /*todo:  توی این موج، که 6 دقیقه طول می کشه، بازی رونق میگیره. اینطوریه که از زمان شروع این موج، هر 15 ثانیه انمی های ساده وارد میشن و ثانیه 30 ام بازی فریم دوم که ایزومتریه ظاهر میشه.
                      این فریم دوم طوری طراحی شده که صلب نیست و میتونه تو دل فریم اصلی هم بره.
           فرایند وارد شدن انمی های ساده مجدداً تا ثانیه 130 بازی ادامه پیدا میکنه :))
           ثانیه 150 اولین انمی نرمال ظاهر میشه (Omenoct)
           باز از از ثانیه 180 تا 310 فرایند وارد شدن انمی های ساده شروع میشه
           اپسیلون از ثانیه 310 تا 360 مهلت داره تا هرچی انمی هست رو از بین ببره.
        */

            if (Properties.getInstance().WAVE == 2) {
                if (spentMilliSecondW2 <= 4 * 60000) {
                    if (spentMilliSecondW2 == 5000 && booleansOfEnemies.getEnemyN().get(7)) {
                        yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 10, 10));
                        booleansOfEnemies.getEnemyN().set(7,false);
                    }
                    if (spentMilliSecondW2 == 20000 && booleansOfEnemies.getEnemyN().get(8)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 40, 20));
                        booleansOfEnemies.getEnemyN().set(8,false);
                    }


                    if(spentMilliSecondW2==30000 && !BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().get(2)){
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().set(2,true);
                    }




                    if (spentMilliSecondW2 == 35000 && booleansOfEnemies.getEnemyN().get(9)) {
                        yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 10, 10));
                        booleansOfEnemies.getEnemyN().set(9,false);
                    }
                    if (spentMilliSecondW2 == 50000 && booleansOfEnemies.getEnemyN().get(10)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 60, 10));
                        booleansOfEnemies.getEnemyN().set(10,false);
                    }
                    if (spentMilliSecondW2 == 65000 && booleansOfEnemies.getEnemyN().get(11)) {
                        yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 50, 100));
                        booleansOfEnemies.getEnemyN().set(11,false);
                    }
                    if (spentMilliSecondW2 == 80000 && booleansOfEnemies.getEnemyN().get(12)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 150, 78));
                        booleansOfEnemies.getEnemyN().set(12,false);
                    }
                    if (spentMilliSecondW2 == 95000 && booleansOfEnemies.getEnemyN().get(13)) {
                        yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 50, 100));
                        booleansOfEnemies.getEnemyN().set(13,false);
                    }
                    if (spentMilliSecondW2 == 105000 && booleansOfEnemies.getEnemyN().get(14)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 150, 78));
                        booleansOfEnemies.getEnemyN().set(14,false);
                    }
                    if (spentMilliSecondW2 == 30000) {
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().set(2,true);
                    }
                    if (spentMilliSecondW2 == 150000) {
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(2,true);
                    }


                }
                if((!booleansOfEnemies.getEnemyN().get(7) && !booleansOfEnemies.getEnemyN().get(8) && !booleansOfEnemies.getEnemyN().get(9) && !booleansOfEnemies.getEnemyN().get(10) && !booleansOfEnemies.getEnemyN().get(11) && !booleansOfEnemies.getEnemyN().get(12) && !booleansOfEnemies.getEnemyN().get(13) && !booleansOfEnemies.getEnemyN().get(14)) && !BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)){
                    int count = 0;
                    for (GameObject gameObject : greenEnemies1) {
                        for (GameObject object : yellowEnemies1) {
                            if (((GreenEnemyModel) gameObject).getLifeValue() > 0 && ((YellowEnemyModel) object).getLifeValue() > 0) {
                                count++;
                            }

                        }

                    }
                    if (count == 0) isFinishedWave2 = true;
                    if (isFinishedWave2) {
                        Properties.getInstance().WAVE = 3;
                    }
                }
            }//Todo: Wave 3:
        /*todo:  توی این موج، که 6 دقیقه طول می کشه، . بازی داغ تر میشه. مثل قبل تا ثانیه 130 انمی های ساده هر 15 ثانیه یه بار وارد میشن.
                      ثانیه 45 فریم دوم و ثانیه 90 فریم صلب سوم وارد میشه
           ثانیه 150 انمی نرمال با قابلیت شلیک هر دو ثانیه به هشت جهت وارد میشه (Necropick)
           باز از از ثانیه 180 تا 310 فرایند وارد شدن انمی های ساده شروع میشه
           اپسیلون از ثانیه 310 تا 360 مهلت داره تا هرچی انمی هست رو از بین ببره.
        */
            if(Properties.getInstance().WAVE==3){
                if (spentMilliSecondW3 <= 6 * 60000) {
                    if (spentMilliSecondW3 == 5000 && booleansOfEnemies.getEnemyN().get(15)) {
                        yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 10, 10));
                        booleansOfEnemies.getEnemyN().set(15, false);
                    }
                    if (spentMilliSecondW3 == 20000 && booleansOfEnemies.getEnemyN().get(16)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 40, 20));
                        booleansOfEnemies.getEnemyN().set(16, false);
                    }
                    if (spentMilliSecondW3 == 35000 && booleansOfEnemies.getEnemyN().get(17)) {
                        yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 10, 10));
                        booleansOfEnemies.getEnemyN().set(17, false);
                    }
                    if (spentMilliSecondW3 == 50000 && booleansOfEnemies.getEnemyN().get(18)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 60, 10));
                        booleansOfEnemies.getEnemyN().set(18, false);
                    }
                    if (spentMilliSecondW3 == 65000 && booleansOfEnemies.getEnemyN().get(19)) {
                        yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 50, 100));
                        booleansOfEnemies.getEnemyN().set(19, false);
                    }
                    if (spentMilliSecondW3 == 80000 && booleansOfEnemies.getEnemyN().get(20)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 150, 78));
                        booleansOfEnemies.getEnemyN().set(20, false);
                    }
                    if (spentMilliSecondW3 == 95000 && booleansOfEnemies.getEnemyN().get(21)) {
                        yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 50, 100));
                        booleansOfEnemies.getEnemyN().set(21, false);
                    }
                    if (spentMilliSecondW3 == 105000 && booleansOfEnemies.getEnemyN().get(22)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 150, 78));
                        booleansOfEnemies.getEnemyN().set(22, false);
                    }
                    if (spentMilliSecondW3 == 30000) {
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().set(2, true);
                    }
                    if (spentMilliSecondW3 == 150000) {
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(2, true);
                    }
                }


































                if((!booleansOfEnemies.getEnemyN().get(15) && !booleansOfEnemies.getEnemyN().get(16) && !booleansOfEnemies.getEnemyN().get(17) && !booleansOfEnemies.getEnemyN().get(18) && !booleansOfEnemies.getEnemyN().get(19) && !booleansOfEnemies.getEnemyN().get(20) && !booleansOfEnemies.getEnemyN().get(21) && !booleansOfEnemies.getEnemyN().get(22)) && !BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)){
                    int count = 0;
                    for (GameObject gameObject : greenEnemies1) {
                        for (GameObject object : yellowEnemies1) {
                            if (((GreenEnemyModel) gameObject).getLifeValue() > 0 && ((YellowEnemyModel) object).getLifeValue() > 0) {
                                count++;
                            }

                        }

                    }
                    if (count == 0) isFinishedWave2 = true;
                    if (isFinishedWave2) {
                        Properties.getInstance().WAVE = 3;
                    }
                }
            }




            //Todo: Wave 4:
        /*todo:  توی این موج، که 4.5 دقیقه طول می کشه، . بازی سخت تر میشه. مثل قبل تا ثانیه 130 انمی های ساده هر 15 ثانیه یه بار وارد میشن.
                      ثانیه 45 فریم دوم و ثانیه 90 فریم صلب سوم وارد میشه
           ثانیه 150 انمی نرمال با قابلیت شلیک هر دو ثانیه به هشت جهت وارد میشه (Necropick)
           ثانیه 180 همزمان انمی archmire و انمی های ساده تا ثانیه 210 هر 10 ثانیه یکبار وارد میشن
           از ثانیه 210 تا 270 اپسیلون وقت داره از خطر بگریزه.
           توی wave5 که آخرین موج بازی هست و قراره 10 دقیقه طول بکشه همه انمی های نرمال و فریم غیر صلب سوم وارد میشن و hp اپسیلون با توجه به شدت حملات تا 700 واحد زیادتر میشه.
           هر 30 ثانیه یه آیتم بونوس هم ظاهر میشه که با خوردنش 50 واحد به hp هاش اضافه میشه.
           زیاد شدن hp اینطوری توجیه میشه که بازی با سخت شدن و شدت حملات
        */


//            for (int i = 0; i < greenEnemies1.size(); i++) {
//
//                if(((GreenEnemyModel)greenEnemies1.get(i)).getLifeValue() == 0){
//                    if(((GreenEnemyModel)greenEnemies1.get(i)).isAlive()){
//                        ((GreenEnemyModel)greenEnemies1.get(i)).setAlive(false);
//
//                        positionsOfCollectiblesG.add(((GreenEnemyModel)greenEnemies1.get(i)).getPosition());
//                        collectibleItemsG.add(i,new TKM2_Item_Model(((GreenEnemyModel)greenEnemies1.get(i)).getPosition()));
//
//                    }
////                    greenEnemies1.set(i,null);
//                    ((GreenEnemyModel)greenEnemies1.get(i)).setPosition(new Position(1111111111,1111111111));
//                    ((GreenEnemyModel)greenEnemies1.get(i)).setLength(0);
//                    ((GreenEnemyModel)greenEnemies1.get(i)).setAlive(false);
//                    startedTimeOfCollectiblesG.set(i, spentMilliSecond);
//
//                }
//            }


//            for (int i = 0; i < yellowEnemies1.size(); i++) {
//                if(isCollided.get(i)) {
//                    ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
//                    yellowEnemies1.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, -((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getX(), -((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
//                }
//                if(!isCollided.get(i)) {
//                    ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
//                    yellowEnemies1.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
//                }
//            }
//            for (int j = 0; j < yellowEnemies1.size(); j++) {
//                if (!isCollided.get(j)) {
//                    for (int i = 0; i < yellowEnemies1.size(); i++) {
//                        if (i == j) {
//                            ACCELERATION_OF_YELLOW_ENEMIES = 2;
//                            yellowEnemies1.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
//                            if(waveOHB){
//                                isCollided.set(i,true);
//                                waveOHB=false;
//                            }
//
//                        }
//
//                    }
//
//                }
//                if (isCollided.get(j)) {
//                    for (int i = 0; i < yellowEnemies1.size(); i++) {
//                        if (i == j) {
//                            ACCELERATION_OF_YELLOW_ENEMIES = 4;
//                            yellowEnemies1.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, -((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getX(), -((YellowEnemyModel) yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
//                        }
//                    }
//
//                }
//            }

//            ToDo:


        }

//        frame.setBackground(new Color(0,0,0,0)); // todo: همینه که کار رو درمیاره :)))))))


        //Wave 1: start from releasing key "R". It takes 5 minutes, unless all the enemies being killed.
        // in this wave, every 30 seconds one enemy comes. Totally 3 enemies from each group.

/*        if(STATE == 1){
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
                if(showOfCollectiblesHelper[i])  showOfCollectibles.set(i,spentMilliSecond >= startedTimeOfCollectibles[i] && spentMilliSecond <= startedTimeOfCollectibles[i] + 10000);

            }
            for (int i = 0; i < greenEnemies1.size(); i++) {
                if(showOfCollectiblesHelperG.get(i))  showOfCollectiblesG.set(i,spentMilliSecond >= startedTimeOfCollectiblesG.get(i) && spentMilliSecond <= startedTimeOfCollectiblesG.get(i) + 10000);

            }
            for (int i = 0; i < yellowEnemies1.size(); i++) {
                if(showOfCollectiblesHelperY.get(i))  showOfCollectiblesY.set(i,spentMilliSecond >= startedTimeOfCollectiblesY.get(i) && spentMilliSecond <= startedTimeOfCollectiblesY.get(i) + 10000);

            }
            if (showOfPointerItemHelper) showOfPointerItem = spentMilliSecond >= 5000 && spentMilliSecond <= 15000;

            for (int i = 3; i < 5; i++) {
                if(showOfCollectibles.get(i)){
                    double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                    double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                    if (((EpsilonModel) gameObjects.get(0)).getRadius() + twm_item_model.getRadius() >= Math.sqrt(((centerOfEpsilonX - collectibleItems.get(i).getCenterX()) * (centerOfEpsilonX - collectibleItems.get(i).getCenterX())) + ((centerOfEpsilonY - collectibleItems.get(i).getCenterY()) * (centerOfEpsilonY - collectibleItems.get(i).getCenterY())))) {
                        showOfCollectiblesHelper[i] = false;
                        showOfCollectibles.set(i,false);
                        XP+=5;
                    }

                }
            }

            for (int i = 0; i < greenEnemies1.size(); i++) {
                if(showOfCollectiblesG.get(i)){
                    double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                    double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                    if (((EpsilonModel) gameObjects.get(0)).getRadius() + twm_item_model.getRadius() >= Math.sqrt(((centerOfEpsilonX - collectibleItemsG.get(i).getCenterX()) * (centerOfEpsilonX - collectibleItemsG.get(i).getCenterX())) + ((centerOfEpsilonY - collectibleItemsG.get(i).getCenterY()) * (centerOfEpsilonY - collectibleItemsG.get(i).getCenterY())))) {
                        showOfCollectiblesHelperG.set(i ,false);
                        showOfCollectiblesG.set(i,false);
                        XP+=5;
                    }

                }
            }
            for (int i = 0; i < yellowEnemies1.size(); i++) {
                if(showOfCollectiblesY.get(i)){
                    double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                    double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                    if (((EpsilonModel) gameObjects.get(0)).getRadius() + twm_item_model.getRadius() >= Math.sqrt(((centerOfEpsilonX - collectibleItemsY.get(i).getCenterX()) * (centerOfEpsilonX - collectibleItemsY.get(i).getCenterX())) + ((centerOfEpsilonY - collectibleItemsY.get(i).getCenterY()) * (centerOfEpsilonY - collectibleItemsY.get(i).getCenterY())))) {
                        showOfCollectiblesHelperY.set(i ,false);
                        showOfCollectiblesY.set(i,false);
                        XP+=10;
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
                if (GLASS_FRAME_DIMENSION_HEIGHT > GLASS_FRAME_DIMENSION_MIN_HEIGHT && GLASS_FRAME_DIMENSION_WIDTH > GLASS_FRAME_DIMENSION_MIN_WIDTH) {
                    GLASS_FRAME_DIMENSION_HEIGHT--;
                    GLASS_FRAME_DIMENSION_WIDTH--;
//                    frame.setSize((int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT);
                    isValidStore = false;

                }
                time.restart();
            }

            if (spentMilliSecond % 5000 == 0 && spentMilliSecond >= 5000) {
                System.out.println(GLASS_FRAME_DIMENSION_HEIGHT);
                if (GLASS_FRAME_DIMENSION_HEIGHT < GLASS_FRAME_DIMENSION_MAX_HEIGHT && GLASS_FRAME_DIMENSION_WIDTH < GLASS_FRAME_DIMENSION_MAX_WIDTH) {
                    GLASS_FRAME_DIMENSION_HEIGHT++;
                    GLASS_FRAME_DIMENSION_WIDTH++;
//                    frame.setSize((int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT);
                    isValidStore = false;
                }
//            time.restart();
            }

            if (spentMilliSecond % 5000 == 3000 && spentMilliSecond >= 5000) {
                if (GLASS_FRAME_DIMENSION_HEIGHT > GLASS_FRAME_DIMENSION_MIN_HEIGHT && GLASS_FRAME_DIMENSION_WIDTH > GLASS_FRAME_DIMENSION_MIN_WIDTH) {
                    GLASS_FRAME_DIMENSION_HEIGHT--;
                    GLASS_FRAME_DIMENSION_WIDTH--;
//                    frame.setSize((int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT);
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
                            positionsOfCollectibles.add(((YellowEnemyModel)gameObjects.get(i)).getPosition());
                            collectibleItems.add(i ,new TKM2_Item_Model(((YellowEnemyModel)gameObjects.get(i)).getPosition()));
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
                            positionsOfCollectibles.add(((GreenEnemyModel)gameObjects.get(i)).getPosition());
                            collectibleItems.add(i, new TKM2_Item_Model(((GreenEnemyModel)gameObjects.get(i)).getPosition()));

                        }
                        ((GreenEnemyModel)gameObjects.get(i)).setPosition(new Position(1111111111,1111111111));
                        ((GreenEnemyModel)gameObjects.get(i)).setAlive(false);
                        startedTimeOfCollectibles[i] = spentMilliSecond;

                    }
                }
                for (int i = 0; i < greenEnemies1.size(); i++) {
                    if(((GreenEnemyModel)greenEnemies1.get(i)).isAlive()){
                        positionsOfCollectiblesG.add(((GreenEnemyModel)greenEnemies1.get(i)).getPosition());
                        collectibleItemsG.add(i, new TKM2_Item_Model(((GreenEnemyModel)greenEnemies1.get(i)).getPosition()));

                    }
                    ((GreenEnemyModel)greenEnemies1.get(i)).setPosition(new Position(1111111111,1111111111));
                    ((GreenEnemyModel)greenEnemies1.get(i)).setAlive(false);
                    startedTimeOfCollectiblesG.set(i, spentMilliSecond);

                }
                for (int i = 0; i < yellowEnemies1.size(); i++) {
                    if(((YellowEnemyModel)yellowEnemies1.get(i)).isAlive()){
                        positionsOfCollectiblesY.add(((YellowEnemyModel)yellowEnemies1.get(i)).getPosition());
                        collectibleItemsY.get(i).setNodeOfRectangle(new Position(((YellowEnemyModel)yellowEnemies1.get(i)).getPosX()+100,((YellowEnemyModel)yellowEnemies1.get(i)).getPosY()));

//                        collectibleItemsY.add(i, new TKM2_Item_Model(((YellowEnemyModel)yellowEnemies1.get(i)).getPosition()));

                    }
                    ((YellowEnemyModel)yellowEnemies1.get(i)).setPosition(new Position(1111111111,1111111111));
                    ((YellowEnemyModel)yellowEnemies1.get(i)).setAlive(false);
                    startedTimeOfCollectiblesY.set(i, spentMilliSecond);

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

                if(HP <=0){
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
                        if (GLASS_FRAME_DIMENSION_WIDTH < GLASS_FRAME_DIMENSION_MAX_WIDTH - 20) {
                            GLASS_FRAME_DIMENSION_WIDTH += 5;
                            boundX -= 5;
//                            frame.setBounds((int) boundX, (int) boundY, (int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT);
                            System.out.println("\u001B[41m" + i + "\u001B[0m");

                        }
                        if (GLASS_FRAME_DIMENSION_WIDTH > 20) {
                            GLASS_FRAME_DIMENSION_WIDTH -= 5;
//                        boundX+=5;
//                            frame.setBounds((int) boundX, (int) boundY, (int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT);

                        }
                    }
                    if (shotsOfEpsilons.get(i).getPlaceX() == GLASS_FRAME_DIMENSION_WIDTH - 10) {
                        boundX += 5;
                        GLASS_FRAME_DIMENSION_WIDTH--;
//                        frame.setBounds((int) boundX, (int) boundY, (int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT);


                    }
                    if (shotsOfEpsilons.get(i).getPlaceX() <= 0 || shotsOfEpsilons.get(i).getPlaceY() <= -8 ||
                            shotsOfEpsilons.get(i).getPlaceY() >= GLASS_FRAME_DIMENSION_HEIGHT - 1 || (shotsOfEpsilons.get(i).getPlaceX() >= GLASS_FRAME_DIMENSION_WIDTH))
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
                            HP++;
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
                            HP -=6;
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
                            HP -=5;
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





        }*/


        //   System.out.println("3th: x : "+(gameObjects.get(2).getPosition().getX()) + " y : " + (gameObjects.get(2).getPosition().getY()));
        //   System.out.println("4th: x : "+(gameObjects.get(3).getPosition().getX()) + " y : " + (gameObjects.get(3).getPosition().getY()));
        timer1Starter = true;
        //start after second one:

        // item of mouse pointer:
        //todo:these were for phase1:
//        for (int i = 3; i < 5; i++) {
//            if(showOfCollectiblesHelper[i])  showOfCollectibles.set(i,spentMilliSecond >= startedTimeOfCollectibles[i] && spentMilliSecond <= startedTimeOfCollectibles[i] + 10000);
//
//        }
        for (int i = 0; i < greenEnemies1.size(); i++) {
            if (showOfCollectiblesHelperG.get(i))
                showOfCollectiblesG.set(i, spentMilliSecond >= startedTimeOfCollectiblesG.get(i) && spentMilliSecond <= startedTimeOfCollectiblesG.get(i) + 10000);

        }
//        for (int i = 0; i < yellowEnemies1.size(); i++) {
//            if (showOfCollectiblesHelperY.get(i))
//                showOfCollectiblesY.set(i, spentMilliSecond >= startedTimeOfCollectiblesY.get(i) && spentMilliSecond <= startedTimeOfCollectiblesY.get(i) + 10000);
//
//        }
        if (showOfPointerItemHelper) showOfPointerItem = spentMilliSecond >= 5000 && spentMilliSecond <= 15000;


        //todo:these were for phase1:
//        for (int i = 3; i < 5; i++) {
//            if(showOfCollectibles.get(i)){
//                double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
//                double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
//                if (((EpsilonModel) gameObjects.get(0)).getRadius() + twm_item_model.getRadius() >= Math.sqrt(((centerOfEpsilonX - collectibleItems.get(i).getCenterX()) * (centerOfEpsilonX - collectibleItems.get(i).getCenterX())) + ((centerOfEpsilonY - collectibleItems.get(i).getCenterY()) * (centerOfEpsilonY - collectibleItems.get(i).getCenterY())))) {
//                    showOfCollectiblesHelper[i] = false;
//                    showOfCollectibles.set(i,false);
//                    XP+=5;
//                }
//
//            }
//        }
        for (int i = 0; i < greenEnemies1.size(); i++) {
            if (showOfCollectiblesG.get(i)) {
                double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                if (((EpsilonModel) gameObjects.get(0)).getRadius() + twm_item_model.getRadius() >= Math.sqrt(((centerOfEpsilonX - collectibleItemsG.get(i).getCenterX()) * (centerOfEpsilonX - collectibleItemsG.get(i).getCenterX())) + ((centerOfEpsilonY - collectibleItemsG.get(i).getCenterY()) * (centerOfEpsilonY - collectibleItemsG.get(i).getCenterY())))) {
                    showOfCollectiblesHelperG.set(i, false);
                    showOfCollectiblesG.set(i, false);
                    Properties.getInstance().XP += 5;
                }

            }
        }
//        for (int i = 0; i < yellowEnemies1.size(); i++) {
//            if (showOfCollectiblesY.get(i)) {
//                double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
//                double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
//                if (((EpsilonModel) gameObjects.get(0)).getRadius() + twm_item_model.getRadius() >= Math.sqrt(((centerOfEpsilonX - collectibleItemsY.get(i).getCenterX()) * (centerOfEpsilonX - collectibleItemsY.get(i).getCenterX())) + ((centerOfEpsilonY - collectibleItemsY.get(i).getCenterY()) * (centerOfEpsilonY - collectibleItemsY.get(i).getCenterY())))) {
//                    showOfCollectiblesHelperY.set(i, false);
//                    showOfCollectiblesY.set(i, false);
//                    XP += 10;
//                }
//
//            }
//        }
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


        if (((spentMilliSecond <= startTimeFromActivationOfPointerItem + 10000) && activateMechanismOfPointerItem) || lineShower2) {
            lineShower = true;
            targetWithMouse = new TargetWithMouse(new Point2D.Double(((EpsilonModel) gameObjects.get(0)).getPosition().getX(), ((EpsilonModel) gameObjects.get(0)).getPosition().getY()));

        } else lineShower = false;
        //todo: Shrinkage :
        if (spentMilliSecond < 2000) {
            if (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT > GLASS_FRAME_DIMENSION_MIN_HEIGHT && Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH > GLASS_FRAME_DIMENSION_MIN_WIDTH) {
                Properties.getInstance(). GLASS_FRAME_DIMENSION_HEIGHT -= 0.75;
                Properties.getInstance(). GLASS_FRAME_DIMENSION_WIDTH -= 0.75;
//                frame.setSize((int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT);
                isValidStore = false;

            }
//            time.restart();
        }

        if (spentMilliSecond % 5000 == 0 && spentMilliSecond >= 5000) {
            System.out.println(Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
            if (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT < GLASS_FRAME_DIMENSION_MAX_HEIGHT && Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH < GLASS_FRAME_DIMENSION_MAX_WIDTH) {
                Properties.getInstance(). GLASS_FRAME_DIMENSION_HEIGHT += 1*Properties.getInstance().constantOfShrinkagePanel;
                Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH += 1*Properties.getInstance().constantOfShrinkagePanel;
//                frame.setSize((int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT);
                isValidStore = false;
            }
//            time.restart();
        }

        if (spentMilliSecond % 5000 == 3000 && spentMilliSecond >= 5000) {
            if (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT > GLASS_FRAME_DIMENSION_MIN_HEIGHT && Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH > GLASS_FRAME_DIMENSION_MIN_WIDTH) {
                Properties.getInstance().  GLASS_FRAME_DIMENSION_HEIGHT-=1*Properties.getInstance().constantOfShrinkagePanel;
                Properties.getInstance().  GLASS_FRAME_DIMENSION_WIDTH-=1*Properties.getInstance().constantOfShrinkagePanel;
//                frame.setSize((int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT);
                isValidStore = false;
            }
//            time.restart();
        }

        IS_VALID_STORE = !((spentMilliSecond % 5000 == 3000) || (spentMilliSecond % 5000 == 0));
//        System.out.println("ISVALIDSTORE: " + IS_VALID_STORE);



        if (isValid7) {
            frame.dispose();
            isValid7 = false;
            timerOfGame.reset();
        }

        if(omenoct.getHP()<=0 && BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)){
            shotTimer.stop();
            CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().add(new Collectable(omenoct.getLocation().x, omenoct.getLocation().y +10, 10, Color.CYAN));
            CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().add(new Collectable(omenoct.getLocation().x + 15, omenoct.getLocation().y+10 , 10, Color.CYAN));
            CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().add(new Collectable(omenoct.getLocation().x + 30, omenoct.getLocation().y+10 , 10, Color.CYAN));
            CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().add(new Collectable(omenoct.getLocation().x - 15, omenoct.getLocation().y+10 , 10, Color.CYAN));
            CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().add(new Collectable(omenoct.getLocation().x - 30, omenoct.getLocation().y+10 , 10, Color.CYAN));
            CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().add(new Collectable(omenoct.getLocation().x, omenoct.getLocation().y +30 , 10, Color.CYAN));
            CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().add(new Collectable(omenoct.getLocation().x, omenoct.getLocation().y +50, 10, Color.CYAN));
            BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(2, false);

            BooleansOfCollectibles.getInstance().getIsValidToCollect().set(2,true);
        }



//        time.start();
        if (Properties.getInstance().play) {

            /*todo: these were for phase1:
            for (int i = 1; i < 3 ; i++) {
                if(((YellowEnemyModel)gameObjects.get(i)).getLifeValue() == 0){
                    if(((YellowEnemyModel)gameObjects.get(i)).isAlive()){
                        positionsOfCollectibles.add(((YellowEnemyModel)gameObjects.get(i)).getPosition());
                        collectibleItems.add(i, new TKM2_Item_Model(((YellowEnemyModel)gameObjects.get(i)).getPosition()));
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
                        positionsOfCollectibles.add(((GreenEnemyModel)gameObjects.get(i)).getPosition());
                        collectibleItems.add(i, new TKM2_Item_Model(((GreenEnemyModel)gameObjects.get(i)).getPosition()));

                    }
                    ((GreenEnemyModel)gameObjects.get(i)).setPosition(new Position(1111111111,1111111111));
                    ((GreenEnemyModel)gameObjects.get(i)).setAlive(false);
                    startedTimeOfCollectibles[i] = spentMilliSecond;

                }
            }*/

            for (int i = 0; i < greenEnemies1.size(); i++) {

                if (((GreenEnemyModel) greenEnemies1.get(i)).getLifeValue() == 0) {
                    if (((GreenEnemyModel) greenEnemies1.get(i)).isAlive()) {
                        positionsOfCollectiblesG.add(((GreenEnemyModel) greenEnemies1.get(i)).getPosition());
                        collectibleItemsG.add(i, new TKM2_Item_Model(((GreenEnemyModel) greenEnemies1.get(i)).getPosition()));

                    }
                    ((GreenEnemyModel) greenEnemies1.get(i)).setPosition(new Position(1111111111, 1111111111));
                    ((GreenEnemyModel) greenEnemies1.get(i)).setAlive(false);
                    startedTimeOfCollectiblesG.set(i, spentMilliSecond);

                }
            }
            for (int i = 0; i < yellowEnemies1.size(); i++) {

                if (((YellowEnemyModel) yellowEnemies1.get(i)).getLifeValue() == 0) {
                    if(BooleansOfCollectibles.getInstance().getIsValidYEtoCollect().get(i)){
                        CollectablesOfEnemies.getInstance().getCollectablesOfYE().add(new Collectable((int) yellowEnemies1.get(i).getPosition().getX(), (int) yellowEnemies1.get(i).getPosition().getY(),10,Color.ORANGE));
                        CollectablesOfEnemies.getInstance().getCollectablesOfYE().add(new Collectable((int) yellowEnemies1.get(i).getPosition().getX()+20, (int) yellowEnemies1.get(i).getPosition().getY(),10,Color.ORANGE));
                        BooleansOfCollectibles.getInstance().getIsValidYEtoCollect().set(i,false);
                    }

                    ((YellowEnemyModel) yellowEnemies1.get(i)).setPosition(new Position(1111111111, 1111111111));
                    ((YellowEnemyModel) yellowEnemies1.get(i)).setAlive(false);
                    startedTimeOfCollectiblesY.set(i, spentMilliSecond);

                }
            }

            /*todo:these were for phase1:
            for (int i = 1; i < gameObjects.size(); i++) {
                if (startFromCollision.get(i)!=null){
                    System.out.println("????????????????????: " + startFromCollision.get(i));
                    if(spentMilliSecond==(startFromCollision.get(i)+5000)){
                        if(gameObjects.get(i) instanceof YellowEnemyModel){
                            isCollided.set(i, false);

                        }else if(gameObjects.get(i) instanceof GreenEnemyModel){
                            isCollided.set(i, false);

                        }
                    }
                }
            }*/

            if (Properties.getInstance().HP <= 0) {
                ((EpsilonModel) gameObjects.get(0)).sizeOfEpsilon.setHeight(((EpsilonModel) gameObjects.get(0)).sizeOfEpsilon.getHeight() + 1);
                ((EpsilonModel) gameObjects.get(0)).sizeOfEpsilon.setWidth(((EpsilonModel) gameObjects.get(0)).sizeOfEpsilon.getWidth() + 1);
            }


/*                    todo:these were for phase1:
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
                    if (GLASS_FRAME_DIMENSION_WIDTH < GLASS_FRAME_DIMENSION_MAX_WIDTH - 20) {
                        GLASS_FRAME_DIMENSION_WIDTH += 5;
                        boundX -= 5;
//                        frame.setBounds((int) boundX, (int) boundY, (int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT);
                        System.out.println("\u001B[41m" + i + "\u001B[0m");

                    }
                    if (GLASS_FRAME_DIMENSION_WIDTH > 20) {
                        GLASS_FRAME_DIMENSION_WIDTH -= 5;
//                        boundX+=5;
//                        frame.setBounds((int) boundX, (int) boundY, (int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT);

                    }
                }*/

                /* todo:these were for phase1:
                if (shotsOfEpsilons.get(i).getPlaceX() <= 0 || shotsOfEpsilons.get(i).getPlaceY() <= -8 ||
                        shotsOfEpsilons.get(i).getPlaceY() >= GLASS_FRAME_DIMENSION_HEIGHT - 1 || (shotsOfEpsilons.get(i).getPlaceX() >= GLASS_FRAME_DIMENSION_WIDTH))
                    shotsOfEpsilons.get(i).setNumberOfCollision(shotsOfEpsilons.get(i).getNumberOfCollision() + 1);


            }
            System.out.println("\u001B[41m" + shotsOfEpsilons.get(0).getPlaceX() + "\u001B[0m" + " - y:" + "\u001B[36m" + shotsOfEpsilons.get(0).getPlaceY() + "\u001B[0m");
            for (int i = 0; i < shotsOfEpsilons.size(); i++) {
                for (int j = 1; j < 3; j++) {
                    if (Math.sqrt(((shotsOfEpsilons.get(i).getPlaceX() + 5.0) - ((YellowEnemyModel) gameObjects.get(j)).getPosition().getX() - ((YellowEnemyModel) gameObjects.get(j)).getRadius()) * ((shotsOfEpsilons.get(i).getPlaceX() + 5.0) - ((YellowEnemyModel) gameObjects.get(j)).getPosition().getX() - ((YellowEnemyModel) gameObjects.get(j)).getRadius()) +
                            ((shotsOfEpsilons.get(i).getPlaceY() + 5.0) - ((YellowEnemyModel) gameObjects.get(j)).getPosition().getY() - ((YellowEnemyModel) gameObjects.get(j)).getRadius()) * ((shotsOfEpsilons.get(i).getPlaceY() + 5.0) - ((YellowEnemyModel) gameObjects.get(j)).getPosition().getY() - ((YellowEnemyModel) gameObjects.get(j)).getRadius()))
                            < 5.0 + ((YellowEnemyModel) gameObjects.get(j)).getRadius()) {
                        // گلوله حذف بشه - یه جون از جونای enemy کم بشه.
                        if (is_Writ_Of_Ares) {
                            REDUCTION_RATE_OF_HP_OF_ENEMY = 2;
                        }
                        ((YellowEnemyModel) gameObjects.get(j)).setLifeValue(((YellowEnemyModel) gameObjects.get(j)).getLifeValue() - REDUCTION_RATE_OF_HP_OF_ENEMY);
//                        XXED++;
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        shotsOfEpsilons.get(i).setPlaceX(1000000);
                        shotsOfEpsilons.get(i).setPlaceY(1000000);


                    }
                }*/

               /* todo:these were for phase1:
                for (int j = 3; j < 5; j++) {
                    if (Math.sqrt(((shotsOfEpsilons.get(i).getPlaceX() + 5.0) - ((GreenEnemyModel)gameObjects.get(j)).getPosition().getX() - ((GreenEnemyModel) gameObjects.get(j)).getRadius()) * ((shotsOfEpsilons.get(i).getPlaceX() + 5.0) - ((GreenEnemyModel) gameObjects.get(j)).getPosition().getX() - ((GreenEnemyModel) gameObjects.get(j)).getRadius()) +
                            ((shotsOfEpsilons.get(i).getPlaceY() + 5.0) - ((GreenEnemyModel)gameObjects.get(j)).getPosition().getY() - ((GreenEnemyModel) gameObjects.get(j)).getRadius()) * ((shotsOfEpsilons.get(i).getPlaceY() + 5.0) - ((GreenEnemyModel) gameObjects.get(j)).getPosition().getY() - ((GreenEnemyModel) gameObjects.get(j)).getRadius()))
                            < 5.0 + ((GreenEnemyModel) gameObjects.get(j)).getRadius()) {
                        // گلوله حذف بشه - یه جون از جونای enemy کم بشه.
                        ((GreenEnemyModel) gameObjects.get(j)).setLifeValue(((GreenEnemyModel) gameObjects.get(j)).getLifeValue()-1);
                        HP++;
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        shotsOfEpsilons.get(i).setPlaceX(1000000);
                        shotsOfEpsilons.get(i).setPlaceY(1000000);


                    }
                }*/


 //           }

           /* todo: these were for phase1:
            for (int i = 1; i < 3; i++) {
                ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - gameObjects.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - gameObjects.get(i).getPosition().getY())));
                ((YellowEnemyModel) gameObjects.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
            }
            for (int i = 3; i < 5; i++) {
                ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - gameObjects.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - gameObjects.get(i).getPosition().getY())));
                ((GreenEnemyModel) gameObjects.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
            }*/

            /*todo:these were for phase1:
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
            }*/
        }
        System.out.println("x of moving enemy: " + gameObjects.get(1).getPosition().getX());
        System.out.println("x of moving epsilon: " + gameObjects.get(0).getPosition().getX());

        /*todo:these were for phase1:
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
                        HP -=6;
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
                        HP -=5;
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
        }*/
        if (isUnvisible && ((EpsilonModel) gameObjects.get(0)).sizeOfEpsilon.getWidth() >= PANEL_SIZE.getWidth()) {
            Properties.getInstance().play = false;
            if (isValid8) {
                panel.setVisible(false);
//                frame.dispose();
                disposer();
                new GameOverFrame();
                isValid8 = false;
                isUnvisible = false;

            }

        }

        //todo: 7/6/2024:
        for (Bullet bullet : bullets) {
            bullet.move();
        }
        for (Bullet bullet : bulletsOfOmenoct) {
            bullet.move();
        }
        for (Bullet bullet : bulletsOfNecropick) {
            bullet.move2();
        }
        for (Bullet bullet : bulletsOfWyrm) {
            bullet.move();
        }


        checkCollisions();
        if(BooleansOfEnemies.getInstance().isWritOfAstrape()) FunctionalMethods.writOfAstrape();



//        time.restart();
        panel.repaint();
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameObjects.forEach(GameObject::update);
        frame.update();

        if (Properties.getInstance().play) update();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (IS_VALID_STORE) {
            if (e.getKeyCode() == KeyEvent.VK_1) {
                new StoreFrame();
                Properties.getInstance().play = false;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {
            lineShower2 = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            lineShower2 = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_O) {
            System.out.println("e1xCenter:" + yellowEnemies.get(0).getYE_posX1() + " e2xCenter:" + yellowEnemies.get(1).getYE_posX1());
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            disposer();
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            Properties.getInstance().play = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            Properties.getInstance().play = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            FunctionalMethods.saveGameState();
        }
        if(e.getKeyCode() == KeyEvent.VK_Q){
            FunctionalMethods.loadGameState(input);
        }
        if(e.getKeyCode() == KeyEvent.VK_V){
            Properties.getInstance().play= false;
            openVolumeControlDialog();
        }
        if(e.getKeyCode() == KeyEvent.VK_I){
            Properties.getInstance().play = false;
            Thread shootThread = new Thread(() -> {
                while (boo) {
                    try {
                        Thread.sleep(20); // Sleep for 10 seconds
                        new Shopping();
                        boo =false;
                    } catch (InterruptedException p) {
                        p.printStackTrace();
                    }
                }
            });
            shootThread.start();
        }
        if(e.getKeyCode() == KeyEvent.VK_U){
            Properties.getInstance().play = false;
            Thread shootThread = new Thread(() -> {
                while (boo) {
                    try {
                        Thread.sleep(20); // Sleep for 10 seconds
                        new SkillTreeFrame();
                        boo =false;
                    } catch (InterruptedException p) {
                        p.printStackTrace();
                    }
                }
            });
            shootThread.start();
        }

/* todo: these were for phase1

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

        }*/

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

/* // todo: these were for phase1
    public void setDefaultShots() {
        for (int i = 0; i < numberOfShots; i++) {
            shotsOfEpsilons.add(i, new ShotOfEpsilon(-1, 2, 100000000 + 15, 1000000000));  // برای مقدار واقعی، dir ها رو یه منفی ضرب کن
        }
        for (int i = 0; i < numberOfShots; i++) {
            validShots.add(i, false);
        }
    }
*/

    @Override
    public void mouseClicked(MouseEvent e) {

        int targetX = e.getX();
        int targetY = e.getY();
        bullets.add(new Bullet((int) (gameObjects.get(0).getPosition().getX() + 10), (int) (gameObjects.get(0).getPosition().getY() + 10), targetX, targetY, Color.RED));

    }

    private void checkCollisions() {
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        ArrayList<Collectable> collectablesToRemove = new ArrayList<>();
        Rectangle epsilon = new Rectangle((int) ((EpsilonModel) gameObjects.get(0)).getPosition().getX(), (int) (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getY(), EPSILON_WIDTH, EPSILON_LENGTH);
        for (Bullet bullet : bullets) {
            for (int i = 0; i < yellowEnemies1.size(); i++) {
                if (bullet.getBounds().intersects(getBoundsYellowEnemy2(i))) {
                    bulletsToRemove.add(bullet);
                    ((YellowEnemyModel) yellowEnemies1.get(i)).setLifeValue(((YellowEnemyModel) yellowEnemies1.get(i)).getLifeValue() - 1);
                }
                if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)){
                    if(bullet.getBounds().intersects(omenoct.getRectangle())){
                        bulletsToRemove.add(bullet);
                        omenoct.setHP(omenoct.getHP()-1);
                        if(Properties.getInstance().isValidToChiron){
                            Properties.getInstance().HP += 3;
                        }
                    }

                }
                if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(1)){
                    if(shapeIntersects(bullet.getBounds(),necropick.getRectangle())){
                        bulletsToRemove.add(bullet);
                        necropick.setHP(necropick.getHP()-1);
                        if(Properties.getInstance().isValidToChiron){
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
                if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)){
                    if(bullet.getBounds().intersects(omenoct.getRectangle())){
                        bulletsToRemove.add(bullet);
                        omenoct.setHP(omenoct.getHP()-1);
                        if(Properties.getInstance().isValidToChiron){
                            Properties.getInstance().HP += 3;
                        }
                    }

                }
            }
            for (int j = 0; j < BooleansOf_IsValidToShow.getInstance().getIsValidToShowBlackOrbEntity().size(); j++) {
                if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowBlackOrbEntity().get(j)){
                    if(bullet.getBounds().intersects(Orb.getInstance().get(j).getRectangle())){
                        bulletsToRemove.add(bullet);
                        Orb.getInstance().get(j).setHP(Orb.getInstance().get(j).getHP()-1);
                        if(Properties.getInstance().isValidToChiron){
                            Properties.getInstance().HP += 3;
                        }
                    }
                }
            }

            if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(3)){
                if(bullet.getBounds().intersects(Wyrm.getInstance().getRectangle())){
                    bulletsToRemove.add(bullet);
                    Wyrm.getInstance().setHP(Wyrm.getInstance().getHP()-1);
                    if(Properties.getInstance().isValidToChiron){
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
                playShotMusic();


            }
        }

        for (Bullet bullet : bulletsOfNecropick) {
            if (epsilon.intersects(bullet.getBounds2())) {
                Properties.getInstance().HP -= 5;
                bulletsToRemove.add(bullet);
                playShotMusic();


            }
        }
        for (Bullet bullet : bulletsOfWyrm) {
            if (epsilon.intersects(bullet.getBounds())) {
                Properties.getInstance().HP -= 8;
                bulletsToRemove.add(bullet);
                playShotMusic();


            }
        }
        for (Collectable collectable : CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct()) {
            if (collectable.getRectangle().intersects(epsilon)) {
                Properties.getInstance().XP += 4;
                collectablesToRemove.add(collectable);
                playShotMusic();

            }
        }

        for (Collectable collectablesOfOrb : CollectablesOfEnemies.getInstance().getCollectablesOfOrbs()) {
            if (collectablesOfOrb.getRectangle().intersects(epsilon)) {
                Properties.getInstance().XP += 30;
                collectablesToRemove.add(collectablesOfOrb);
                playShotMusic();


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

        if (BooleansOf_IsValidToShow.getInstance().isValidToAttackCerberus()){
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


        bullets.removeAll(bulletsToRemove);
        bulletsOfOmenoct.removeAll(bulletsToRemove);
        bulletsOfNecropick.removeAll(bulletsToRemove);
        bulletsOfWyrm.removeAll(bulletsToRemove);
        CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().removeAll(collectablesToRemove);
        CollectablesOfEnemies.getInstance().getCollectablesOfYE().removeAll(collectablesToRemove);
        CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().removeAll(collectablesToRemove);
        CollectablesOfEnemies.getInstance().getCollectablesOfWyrm().removeAll(collectablesToRemove);

    }


    public Rectangle getBoundsGreenEnemy(int i) {
        return new Rectangle((int) ((GreenEnemyModel) gameObjects.get(i)).getPosition().intX(), (int) (int) ((GreenEnemyModel) gameObjects.get(i)).getPosition().intY(), 25, 25);
    }

    public Rectangle getBoundsYellowEnemy(int i) {
        return new Rectangle((int) ((YellowEnemyModel) gameObjects.get(i)).getPosition().intX(), (int) (int) ((YellowEnemyModel) gameObjects.get(i)).getPosition().intY(), 25, 25);
    }

    public Rectangle getBoundsGreenEnemy2(int i) {
        return new Rectangle((int) ((GreenEnemyModel) greenEnemies1.get(i)).getPosition().intX(), (int) (int) ((GreenEnemyModel) greenEnemies1.get(i)).getPosition().intY(), ((GreenEnemyModel) greenEnemies1.get(i)).getLength(), ((GreenEnemyModel) greenEnemies1.get(i)).getLength());
    }

    public Rectangle getBoundsYellowEnemy2(int i) {
        return new Rectangle((int) ((YellowEnemyModel) yellowEnemies1.get(i)).getPosition().intX(), (int) (int) ((YellowEnemyModel) yellowEnemies1.get(i)).getPosition().intY(), 25, 25);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
        System.out.println(startX);
        playShotMusic(); // todo: douf douf dodododdodooofff  :)

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (startX == e.getX()) {
            timer1Starter = true;
            Properties.getInstance().play = true;

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



    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }



    public static GameFrame getINSTANCE() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new GameFrame();
            } catch (UnsupportedAudioFileException e) {
                logger.error("singleton error");
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

    public static List<YellowEnemyModel> getYellowObjects() {
        return yellowEnemies1;
    }

    public static ArrayList<GameObject> getGreenObjects() {
        return greenEnemies1;
    }

    public void disposer() {
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
        Properties.getInstance().HP = 100;
        Properties.getInstance().XP = 0.0;
//        timerOfGame = null;
    }

    public void makeInvisible() {
        frame.setVisible(false);
    }

    public void makeVisible() {
        frame.setVisible(true);
    }


    private void necropickHide() {
        Timer colorChangeTimer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isNecropickInRightRange()) {
                    necropick.setLocation(new Point((int) (((EpsilonModel) gameObjects.get(0)).getPosition().getX() + 200), (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getY()));
                } else if (isNecopickInLeftRange()) {
                    necropick.setLocation(new Point((int) (((EpsilonModel) gameObjects.get(0)).getPosition().getX() - 200), (int) ((EpsilonModel) gameObjects.get(0)).getPosition().getY()));

                }
                necropick_isVisible = true;
            }
        });
        necropick_isVisible = false;
        colorChangeTimer.setRepeats(false);
        colorChangeTimer.start();
    }

    public boolean isNecropickInRightRange() {
        return ((EpsilonModel) gameObjects.get(0)).getPosition().getX() + 200 < panels.get(0).getRightX();
    }

    public boolean isNecopickInLeftRange() {
        return ((EpsilonModel) gameObjects.get(0)).getPosition().getX() - 200 < panels.get(0).getX();
    }

    private void necropickShoot() {
        int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int i = 0; i < 8; i++) {
            bulletsOfNecropick.add(new Bullet(necropick.getLocation().x, necropick.getLocation().y, dx[i], dy[i]));
        }

    }
    public void playBackgroundMusic() {
        File musicPath = new File("src\\open-fields-aaron-paul-low-main-version-25198-02-16.wav");

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            logger.info("Background music started.");
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            logger.error("Error playing background music: ", e);
        }
    }
    public void playShotMusic() {
        File musicPath = new File("src\\main\\java\\third\\all\\utils\\Hit2.wav");

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
            clip2 = AudioSystem.getClip();
            clip2.open(audioInputStream);
            clip2.loop(Clip.LOOP_CONTINUOUSLY);
            clip2.start();
            logger.info("sound of Shot .");
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            logger.error("Error playing sound of Shot: ", e);
        }
    }
    private void openVolumeControlDialog() {

        JFrame dialog = new JFrame("Volume Control");
//        dialog.setLocation(0,0);
        dialog.setLocationRelativeTo(null);

        JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, (int) volumeControl.getMinimum(), (int) volumeControl.getMaximum(), (int) volumeControl.getValue());
        volumeSlider.addChangeListener(e -> {
            int value = volumeSlider.getValue();
            volumeControl.setValue(value);
            logger.info("Volume set to {}", value);
            Properties.getInstance().play = true;
        });
        dialog.add(volumeSlider);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }


}
