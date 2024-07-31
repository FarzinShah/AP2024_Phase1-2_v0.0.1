package third.all.gameComponents.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import third.all.controller.componentController.*;
import third.all.controller.entity.EpsilonModel;
import third.all.controller.entity.GameObject;
import third.all.controller.entity.GreenEnemyModel;
import third.all.controller.entity.YellowEnemyModel;
import third.all.controller.gameController.GameKeyListener;
import third.all.controller.gameController.GameMouseHandler;
import third.all.controller.movement.MovementOfGreenEnemy;
import third.all.controller.movement.MovementOfYellowEnemy;
import third.all.controller.movement.Position;
import third.all.controller.movement.Vector2D;
import third.all.data.*;
import third.all.data.Properties;

import third.all.data.booleans.*;
import third.all.gameComponents.preGameComponent.GameOverFrame;
import third.all.model.boss.Fist;
import third.all.model.boss.Head;
import third.all.model.boss.LeftHand;
import third.all.model.boss.RightHand;
import third.all.model.epsilon.Bullet;
import third.all.model.normalEnemies.*;


import javax.sound.sampled.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static third.all.controller.Constants.*;
import static third.all.controller.Variables.rng;
import static third.all.data.Properties.*;
import static third.all.gameComponents.game.View.*;
import static third.all.gameComponents.preGameComponent.Timer1.*;
import static third.all.data.booleans.Booleans.*;
import static third.all.gameComponents.game.Timers.*;


public class GameLoop implements ActionListener {
    View view;
    MyFrame frame;
    private static final Logger logger = LoggerFactory.getLogger(GameLoop.class);

    public static GameLoop INSTANCE;
    public static int score = 0;

    boolean isInvisible = true;
    FunctionalMethods functionalMethod;

    public Archmire archmire;


    public static ArrayList<GameObject> gameObjects;
    public static List<YellowEnemyModel> yellowEnemies1;
    public static ArrayList<GameObject> greenEnemies1;


    int collidedOne = -5;
    int collidedTwo = -5;
    HashMap<Integer, Integer> startFromCollision = new HashMap<>();


    public static int startX = 345, startY = 520;

    Input input;
    TargetWithMouse targetWithMouse;
    public static TWM_Item_Model twm_item_model; // can be singleton
    public static ArrayList<TKM2_Item_Model> collectibleItems;// can't be singleton

    public static ArrayList<TKM2_Item_Model> collectibleItemsG;// can't be singleton


    public static ArrayList<Panel> blackOrbPanels;

    //TODO: data:
    BooleansOfEnemies booleansOfEnemies;
    BooleansOf_IsValidToShow booleansOfIsValidToShow;
    Properties properties;
    GameKeyListener gameKeyListener;
    GameMouseHandler gameMouseHandler;
    Timers timerController;
    ArrayList<NormalEnemy> normalEnemies;


    public GameLoop() throws UnsupportedAudioFileException, IOException {
        frame = new MyFrame();
        ClipHandler.getInstance().playBackgroundMusic();
        normalEnemiesLauncher();
        logger.info("GameLoop initialized.");
        functionalMethod = FunctionalMethods.getInstance();
        FunctionalMethods.getInstance().setNormalEnemies(normalEnemies);
        booleansOfEnemies = BooleansOfEnemies.getInstance();
        booleansOfIsValidToShow = BooleansOf_IsValidToShow.getInstance();
        (normalEnemies.get(1)).setLocation(OMENOCT_POSITION);
        (normalEnemies.get(1)).setSize(OMENOCT_SIZE);
        ( normalEnemies.get(0)).setLocation(NECROPICK_POSITION);
        ( normalEnemies.get(0)).setSize(NECROPICK_SIZE);
        archmire = Archmire.getInstance();
        archmire.setLocation(ARCHMIRE_POSITION);
        archmire.setSize(ARCHMIRE_SIZE);
        yellowEnemies1 = new ArrayList<>();
        greenEnemies1 = new ArrayList<>();
        blackOrbPanels = new ArrayList<>();

        input = new Input();
        view = new View(input, normalEnemies);
        timerController = new Timers();

        targetWithMouse = new TargetWithMouse(new Point2D.Double(0, 0));
        twm_item_model = new TWM_Item_Model(new Point2D.Double(rng(STARTING_POINT.x + 50.0, STARTING_POINT.y + 300.0), rng(STARTING_POINT.x + 50.0, STARTING_POINT.y + 300.0)));
        view.setTwm_item_model1(twm_item_model);
        gameObjects = new ArrayList<>();

        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0)); // todo: همینه که کار رو درمیاره :)))))))
        frame.setVisible(true);
        frame.add(view);
        FunctionalMethods.launchCollectibles();

        gameKeyListener = new GameKeyListener(input);
        view.addKeyListener(gameKeyListener);

        int delay = 0;
        time = new Timer(delay, this);
        time.start();
        timer1 = new Timer(0, this);
        gameMouseHandler = new GameMouseHandler();
        view.addMouseListener(gameMouseHandler);
        view.addMouseMotionListener(gameMouseHandler);
        view.addMouseListener(targetWithMouse);
        view.addMouseMotionListener(targetWithMouse);
        frame.setBounds(0, 0, 1440, 720);
        properties = Properties.getInstance();
        gameObjects.add(new EpsilonModel(new EpsilonController(input), 230, 330));
        FunctionalMethods.launchP1Enemies(input); //todo: no attention needed!
        Booleans.launchIsCollided();
        startGameLoop();

    }

    public void update() {
        yellowEnemies1.forEach(GameObject::update);
        greenEnemies1.forEach(GameObject::update);

        isValidToShowYE();
        isValidToShowGE();
        movementOfP1Enemies();
        movementOfOmenoct();
        movementOfArchmire();
        funcOfBlackOrbs();
        laserOfBlackOrbs();
        movementOfWyrm();
        funcOfBarricados();

        //Todo: Wave 1:
        /*todo: توی این موج، که 4 دقیقه طول می کشه، هر 30 ثانیه یه انمی ساده وارد صفحه میشه. اینجا همه چی آرومه و تازه بازی شروع شده. هنوز انمی های نرمال وارد نشدن و تنها چیزی که اعمال میشه، کوچیک و بزرگ شدن فریم اصلیه. و تنها یک فریم داریم
           چیزی که هست این موج اگه زودتر از چهاردقیقه تموم بشه، موج دوم شروع میشه.
           فرایند وارد شدن انمی ها تا ثانیه 130 بازی ادامه پیدا میکنه :))
        */
        if (Properties.getInstance().STATE == 21) {
            if (Properties.getInstance().WAVE == 1) {
                BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().set(1, true);
                HelpingBooleans.getInstance().isValidToShowStartingWelcome = false;
                BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().set(0, spentMilliSecond < 11000 && spentMilliSecond >= 5000);

                if (spentMilliSecond <= 4 * 60000) {
                    if (spentMilliSecond == 1000) {
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().set(2, true);
//                    shotTimer.start();
                    }
                    if (spentMilliSecond == 2000) {
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(2, true);
                        shotTimer.start();
                    }

                    if (spentMilliSecond == 5000) {
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(1, true);
                        necropickShower.start();
                        necropickShooter.start();
                    }
//                    if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(1)){
//                    }

//                    if (spentMilliSecond == 2000) {
//                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(6, true);
//                    }
//                    if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(6)) {
//                        writOfCerberus();
//                    }
//                    if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(6) && !BooleansOf_IsValidToShow.getInstance().isValidToAttackCerberus() && HelpingBooleans.getInstance().cerberusBool) {
//                        cerberusAttackTimer.start();
//                        HelpingBooleans.getInstance().cerberusBool = false;
//                    }
//
                    if (spentMilliSecond == 3000) {
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().set(6, true);
                        addBlackOrbsTimer.start();
                    }
                    if (spentMilliSecond == 4000) {
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(3, true);
                    }
                    if (spentMilliSecond == 5000 && booleansOfEnemies.getEnemyN().get(1)) {
                        yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 10, 10));
                        booleansOfEnemies.getEnemyN().set(1, false);
                    }
                    if (spentMilliSecond == 25000 && booleansOfEnemies.getEnemyN().get(2)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 40, 20));
                        booleansOfEnemies.getEnemyN().set(2, false);
                    }
                    if (spentMilliSecond == 50000 && booleansOfEnemies.getEnemyN().get(3)) {
                        yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 10, 10));
                        booleansOfEnemies.getEnemyN().set(3, false);
                    }
                    if (spentMilliSecond == 70000 && booleansOfEnemies.getEnemyN().get(4)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 60, 10));
                        booleansOfEnemies.getEnemyN().set(4, false);
                    }
                    if (spentMilliSecond == 100000 && booleansOfEnemies.getEnemyN().get(5)) {
                        yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 50, 100));
                        booleansOfEnemies.getEnemyN().set(5, false);
                    }
                    if (spentMilliSecond == 130000 && booleansOfEnemies.getEnemyN().get(6)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 150, 78));
                        booleansOfEnemies.getEnemyN().set(6, false);
                    }

                    if (spentMilliSecond == 60000) {
                        HelpingBooleans.getInstance().isValidToLargerMainPanel = true;
                    }
                    if (spentMilliSecond == 120000) HelpingBooleans.getInstance().isValidToLargerMainPanel = false;

                }


                //TODO: collectable items after destruction :  Done 90%
                //todo: خب باید هر آیتم رو خیلی ساده بزنیم. با فور و رندم


                if (Properties.getInstance().WAVE == 1 && (!booleansOfEnemies.getEnemyN().get(1) && !booleansOfEnemies.getEnemyN().get(2) && !booleansOfEnemies.getEnemyN().get(3) && !booleansOfEnemies.getEnemyN().get(4) && !booleansOfEnemies.getEnemyN().get(5) && !booleansOfEnemies.getEnemyN().get(6))) {
                    int count = 0;
                    for (GameObject gameObject : greenEnemies1) {
                        for (YellowEnemyModel object : yellowEnemies1) {
                            if (((GreenEnemyModel) gameObject).getLifeValue() > 0 && (object).getLifeValue() > 0) {
                                count++;
                            }

                        }

                    }
                    if (count == 0) isFinishedWave1 = true;
                    if (isFinishedWave1) {
                        Properties.getInstance().WAVE = 2;
                    }
                }
                if (Properties.getInstance().WAVE == 1 && Properties.getInstance().HP <= 0) {
                    FunctionalMethods.getInstance().loadGameState(input);
                }
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
                        booleansOfEnemies.getEnemyN().set(7, false);
                    }
                    if (spentMilliSecondW2 == 20000 && booleansOfEnemies.getEnemyN().get(8)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 40, 20));
                        booleansOfEnemies.getEnemyN().set(8, false);
                    }


                    if (spentMilliSecondW2 == 30000 && !BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().get(2)) {
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().set(2, true);
                    }


                    if (spentMilliSecondW2 == 35000 && booleansOfEnemies.getEnemyN().get(9)) {
                        yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 10, 10));
                        booleansOfEnemies.getEnemyN().set(9, false);
                    }
                    if (spentMilliSecondW2 == 50000 && booleansOfEnemies.getEnemyN().get(10)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 60, 10));
                        booleansOfEnemies.getEnemyN().set(10, false);
                    }
                    if (spentMilliSecondW2 == 65000 && booleansOfEnemies.getEnemyN().get(11)) {
                        yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 50, 100));
                        booleansOfEnemies.getEnemyN().set(11, false);
                    }
                    if (spentMilliSecondW2 == 80000 && booleansOfEnemies.getEnemyN().get(12)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 150, 78));
                        booleansOfEnemies.getEnemyN().set(12, false);
                    }
                    if (spentMilliSecondW2 == 95000 && booleansOfEnemies.getEnemyN().get(13)) {
                        yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 50, 100));
                        booleansOfEnemies.getEnemyN().set(13, false);
                    }
                    if (spentMilliSecondW2 == 105000 && booleansOfEnemies.getEnemyN().get(14)) {
                        greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 150, 78));
                        booleansOfEnemies.getEnemyN().set(14, false);
                    }
                    if (spentMilliSecondW2 == 30000) {
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().set(2, true);
                    }
                    if (spentMilliSecondW2 == 150000) {
                        BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(2, true);
                    }


                }
                if ((!booleansOfEnemies.getEnemyN().get(7) && !booleansOfEnemies.getEnemyN().get(8) && !booleansOfEnemies.getEnemyN().get(9) && !booleansOfEnemies.getEnemyN().get(10) && !booleansOfEnemies.getEnemyN().get(11) && !booleansOfEnemies.getEnemyN().get(12) && !booleansOfEnemies.getEnemyN().get(13) && !booleansOfEnemies.getEnemyN().get(14)) && !BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)) {
                    int count = 0;
                    for (GameObject gameObject : greenEnemies1) {
                        for (YellowEnemyModel object : yellowEnemies1) {
                            if (((GreenEnemyModel) gameObject).getLifeValue() > 0 && (object).getLifeValue() > 0) {
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
            if (Properties.getInstance().WAVE == 3) {
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


                if ((!booleansOfEnemies.getEnemyN().get(15) && !booleansOfEnemies.getEnemyN().get(16) && !booleansOfEnemies.getEnemyN().get(17)
                        && !booleansOfEnemies.getEnemyN().get(18) && !booleansOfEnemies.getEnemyN().get(19)
                        && !booleansOfEnemies.getEnemyN().get(20) && !booleansOfEnemies.getEnemyN().get(21)
                        && !booleansOfEnemies.getEnemyN().get(22)) && !BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)) {
                    int count = 0;
                    for (GameObject gameObject : greenEnemies1) {
                        for (YellowEnemyModel object : yellowEnemies1) {
                            if (((GreenEnemyModel) gameObject).getLifeValue() > 0 && (object).getLifeValue() > 0) {
                                count++;
                            }

                        }

                    }
                    if (count == 0) isFinishedWave3 = true;
                    if (isFinishedWave3) {
                        Properties.getInstance().WAVE = 4;
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
            if (Properties.getInstance().WAVE == 4) {
                if (spentMilliSecondW4 == 5000 && booleansOfEnemies.getEnemyN().get(23)) {
                    yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 10, 10));
                    booleansOfEnemies.getEnemyN().set(23, false);
                }
                if (spentMilliSecondW4 == 20000 && booleansOfEnemies.getEnemyN().get(24)) {
                    greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 40, 20));
                    booleansOfEnemies.getEnemyN().set(24, false);
                }
                if (spentMilliSecondW4 == 35000 && booleansOfEnemies.getEnemyN().get(25)) {
                    yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 10, 10));
                    booleansOfEnemies.getEnemyN().set(25, false);
                }
                if (spentMilliSecondW4 == 50000 && booleansOfEnemies.getEnemyN().get(26)) {
                    greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 60, 10));
                    booleansOfEnemies.getEnemyN().set(26, false);
                }
                if (spentMilliSecondW4 == 65000 && booleansOfEnemies.getEnemyN().get(27)) {
                    yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 50, 100));
                    booleansOfEnemies.getEnemyN().set(27, false);
                }
                if (spentMilliSecondW4 == 80000 && booleansOfEnemies.getEnemyN().get(28)) {
                    greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 150, 78));
                    booleansOfEnemies.getEnemyN().set(28, false);
                }
                if (spentMilliSecondW4 == 95000 && booleansOfEnemies.getEnemyN().get(29)) {
                    yellowEnemies1.add(new YellowEnemyModel(new YellowEnemyController(input), 20, 50, 100));
                    booleansOfEnemies.getEnemyN().set(29, false);
                }
                if (spentMilliSecondW4 == 105000 && booleansOfEnemies.getEnemyN().get(30)) {
                    greenEnemies1.add(new GreenEnemyModel(new GreenEnemyController(input), 20, 150, 78));
                    booleansOfEnemies.getEnemyN().set(30, false);
                }
                if (spentMilliSecondW4 == 30000) {
                    BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().set(2, true);
                }
                if (spentMilliSecondW4 == 150000) {
                    BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(2, true);
                }
            }

            if ((!booleansOfEnemies.getEnemyN().get(28) && !booleansOfEnemies.getEnemyN().get(29) && !booleansOfEnemies.getEnemyN().get(30)
                    && !booleansOfEnemies.getEnemyN().get(26) && !booleansOfEnemies.getEnemyN().get(27)
                    && !booleansOfEnemies.getEnemyN().get(25) && !booleansOfEnemies.getEnemyN().get(24)
                    && !booleansOfEnemies.getEnemyN().get(23)) && !BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)) {
                int count = 0;
                for (GameObject gameObject : greenEnemies1) {
                    for (YellowEnemyModel object : yellowEnemies1) {
                        if (((GreenEnemyModel) gameObject).getLifeValue() > 0 && (object).getLifeValue() > 0) {
                            count++;
                        }

                    }

                }
                if (count == 0) isFinishedWave4 = true;
                if (isFinishedWave4) {
                    Properties.getInstance().WAVE = 6;
                }
            }


            //Todo: BossFight
            if (Properties.getInstance().WAVE == 6) {
                if (!HelpingBooleans.getInstance().isBossPanelLaunched) {
                    BooleansOf_IsValidToShow.getInstance().setValidToShowBossPanel(true);
                    HelpingBooleans.getInstance().isBossPanelLaunched = true;
                }
                if (!HelpingBooleans.getInstance().isEpsilonSetOnFirstPlace) {
                    gameObjects.get(0).setPosition(new Position(Head.getInstance().getLocation().x + 100, Head.getInstance().getLocation().y + 500));
                    HelpingBooleans.getInstance().isEpsilonSetOnFirstPlace = true;

                }

                if (!BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().get(2) && spentMilliSecondW6 >= 1000 && !BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().get(0)) {
                    if (!HelpingBooleans.getInstance().isMovingUp && HelpingBooleans.getInstance().isMovingDown && LeftHand.getInstance().getLocation().y + LeftHand.getInstance().getSize() < PanelsData.getInstance().getBossPanel().getDownY()) {
                        Head.getInstance().setLocation(new Point(Head.getInstance().getLocation().x, Head.getInstance().getLocation().y + 1));
                        LeftHand.getInstance().setLocation(new Point(LeftHand.getInstance().getLocation().x, LeftHand.getInstance().getLocation().y + 1));
                        RightHand.getInstance().setLocation(new Point(RightHand.getInstance().getLocation().x, RightHand.getInstance().getLocation().y + 1));
                    } else if (LeftHand.getInstance().getLocation().y + LeftHand.getInstance().getSize() >= PanelsData.getInstance().getBossPanel().getDownY()) {
                        HelpingBooleans.getInstance().isMovingDown = false;
                        HelpingBooleans.getInstance().isMovingUp = true;
                    }
                    if (!HelpingBooleans.getInstance().isMovingDown && HelpingBooleans.getInstance().isMovingUp && Head.getInstance().getLocation().y > PanelsData.getInstance().getBossPanel().getY()) {
                        Head.getInstance().setLocation(new Point(Head.getInstance().getLocation().x, Head.getInstance().getLocation().y - 1));
                        LeftHand.getInstance().setLocation(new Point(LeftHand.getInstance().getLocation().x, LeftHand.getInstance().getLocation().y - 1));
                        RightHand.getInstance().setLocation(new Point(RightHand.getInstance().getLocation().x, RightHand.getInstance().getLocation().y - 1));
                    } else if (Head.getInstance().getLocation().y <= PanelsData.getInstance().getBossPanel().getY()) {
                        HelpingBooleans.getInstance().isMovingDown = true;
                        HelpingBooleans.getInstance().isMovingUp = false;
                    }
                }

                if (spentMilliSecondW6 == 70000) {
                    BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().set(0, true); // todo: Squeeze
                }
                if (spentMilliSecondW6 == 90000) {
                    HelpingBooleans.getInstance().isSqueezedFinished = true; // todo: EndingOfSqueeze
                }

                if (BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().get(0)) {

                    if (!HelpingBooleans.getInstance().isSqueezedFinished) {
                        if (LeftHand.getInstance().getLocation().x + (double) (2 * LeftHand.getInstance().getSize() / 5) > PanelsData.getInstance().getBossPanel().getX()) {
                            LeftHand.getInstance().setLocation(new Point(LeftHand.getInstance().getLocation().x - 1, LeftHand.getInstance().getLocation().y));
                        } else {
                            HelpingBooleans.getInstance().isSqueezed = true;
                        }
                        if (RightHand.getInstance().getLocation().x + (5 * RightHand.getInstance().getSize() / 8) < PanelsData.getInstance().getBossPanel().getRightX()) {
                            RightHand.getInstance().setLocation(new Point(RightHand.getInstance().getLocation().x + 1, RightHand.getInstance().getLocation().y));

                        } else HelpingBooleans.getInstance().isSqueezed = true;
                    }
                }

                if (!BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().get(2) && HelpingBooleans.getInstance().isSqueezedFinished) {
                    HelpingBooleans.getInstance().isSqueezed = false;

                    if (LeftHand.getInstance().getLocation().x < Properties.getInstance().locationOfLeftHand.x + 170) {
                        LeftHand.getInstance().setLocation(new Point(LeftHand.getInstance().getLocation().x + 1, LeftHand.getInstance().getLocation().y));
                    } else {
                        BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().set(0, false); // todo: finishedSqueeze
                    }
                    if (RightHand.getInstance().getLocation().x > Properties.getInstance().locationOfRightHand.x) {
                        RightHand.getInstance().setLocation(new Point(RightHand.getInstance().getLocation().x - 1, RightHand.getInstance().getLocation().y));

                    } else {
                        BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().set(0, false); // todo: finishedSqueeze
                    }

                }

                if (spentMilliSecondW6 == 40000) {
                    HelpingBooleans.getInstance().isProjectile = true;
                    BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().set(1, true); // todo: Projectile
                }
                if (spentMilliSecondW6 == 55000) {
                    HelpingBooleans.getInstance().isProjectileFinished = true;  // todo: Projectile Finished
                }


                if (BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().get(1)) {
                    if (Head.getInstance().getSize() > 100) {
                        Head.getInstance().setSize(Head.getInstance().getSize() - 2);
                    } else HelpingBooleans.getInstance().isSmalled = true;

                    if (RightHand.getInstance().getSize() > 100) {
                        RightHand.getInstance().setSize(RightHand.getInstance().getSize() - 2);
                    } else HelpingBooleans.getInstance().isSmalled = true;
                    if (LeftHand.getInstance().getSize() > 100) {
                        LeftHand.getInstance().setSize(LeftHand.getInstance().getSize() - 2);
                    } else HelpingBooleans.getInstance().isSmalled = true;

                    int dX_Head = (int) (Head.getInstance().getLocation().x + Head.getInstance().getSize() / 2 - gameObjects.get(0).getPosition().getX() - EPSILON_WIDTH);
                    int dY_Head = (int) (Head.getInstance().getLocation().y + Head.getInstance().getSize() / 2 - gameObjects.get(0).getPosition().getY() - EPSILON_LENGTH);
                    double distanceHead = Math.sqrt((dX_Head * dX_Head) + (dY_Head * dY_Head));

                    if (distanceHead > Properties.getInstance().radiusOfOrbitProjectile) {
                        if (!HelpingBooleans.getInstance().isOnOrbit) {
                            if ((Head.getInstance().getLocation().x > gameObjects.get(0).getPosition().getX()) && (Head.getInstance().getLocation().y > gameObjects.get(0).getPosition().getY())) {
                                Head.getInstance().setLocation(new Point(Head.getInstance().getLocation().x - 1, Head.getInstance().getLocation().y - 1));
                                HelpingBooleans.getInstance().isOnOrbit = true;
                            }
                            if ((Head.getInstance().getLocation().x > gameObjects.get(0).getPosition().getX()) && (Head.getInstance().getLocation().y < gameObjects.get(0).getPosition().getY())) {
                                Head.getInstance().setLocation(new Point(Head.getInstance().getLocation().x - 1, Head.getInstance().getLocation().y + 1));
                                HelpingBooleans.getInstance().isOnOrbit = true;

                            }
                            if ((Head.getInstance().getLocation().x < gameObjects.get(0).getPosition().getX()) && (Head.getInstance().getLocation().y < gameObjects.get(0).getPosition().getY())) {
                                Head.getInstance().setLocation(new Point(Head.getInstance().getLocation().x + 1, Head.getInstance().getLocation().y + 1));
                                HelpingBooleans.getInstance().isOnOrbit = true;

                            }
                            if ((Head.getInstance().getLocation().x < gameObjects.get(0).getPosition().getX()) && (Head.getInstance().getLocation().y > gameObjects.get(0).getPosition().getY())) {
                                Head.getInstance().setLocation(new Point(Head.getInstance().getLocation().x + 1, Head.getInstance().getLocation().y - 1));
                                HelpingBooleans.getInstance().isOnOrbit = true;

                            }
                        }


                    }
                    if (HelpingBooleans.getInstance().isOnOrbit) {
                        handsShooter.start();
                        int secX = (int) (gameObjects.get(0).getPosition().getX() + Properties.getInstance().radiusOfOrbitProjectile * Math.cos(Math.toRadians(Properties.getInstance().angleOfOrbitProjectile)));
                        int secY = (int) (gameObjects.get(0).getPosition().getY() - Properties.getInstance().radiusOfOrbitProjectile * Math.sin(Math.toRadians(Properties.getInstance().angleOfOrbitProjectile)));

                        Head.getInstance().setLocation(new Point(secX, secY));

                    }


                    int dX_RightHand = (int) (RightHand.getInstance().getLocation().x + RightHand.getInstance().getSize() / 2 - gameObjects.get(0).getPosition().getX() - EPSILON_WIDTH);
                    int dY_RightHand = (int) (RightHand.getInstance().getLocation().y + RightHand.getInstance().getSize() / 2 - gameObjects.get(0).getPosition().getY() - EPSILON_LENGTH);
                    double distanceRightHand = Math.sqrt((dX_RightHand * dX_RightHand) + (dY_RightHand * dY_RightHand));

                    if (distanceRightHand > Properties.getInstance().radiusOfOrbitProjectile) {
                        if ((RightHand.getInstance().getLocation().x > gameObjects.get(0).getPosition().getX()) && (RightHand.getInstance().getLocation().y > gameObjects.get(0).getPosition().getY())) {
                            RightHand.getInstance().setLocation(new Point(RightHand.getInstance().getLocation().x - 1, RightHand.getInstance().getLocation().y - 1));
                        }
                        if ((RightHand.getInstance().getLocation().x > gameObjects.get(0).getPosition().getX()) && (RightHand.getInstance().getLocation().y < gameObjects.get(0).getPosition().getY())) {
                            RightHand.getInstance().setLocation(new Point(RightHand.getInstance().getLocation().x - 1, RightHand.getInstance().getLocation().y + 1));
                        }
                        if ((RightHand.getInstance().getLocation().x < gameObjects.get(0).getPosition().getX()) && (RightHand.getInstance().getLocation().y < gameObjects.get(0).getPosition().getY())) {
                            RightHand.getInstance().setLocation(new Point(RightHand.getInstance().getLocation().x + 1, RightHand.getInstance().getLocation().y + 1));
                        }
                        if ((RightHand.getInstance().getLocation().x < gameObjects.get(0).getPosition().getX()) && (RightHand.getInstance().getLocation().y > gameObjects.get(0).getPosition().getY())) {
                            RightHand.getInstance().setLocation(new Point(RightHand.getInstance().getLocation().x + 1, RightHand.getInstance().getLocation().y - 1));
                        }
                    }
                    if (HelpingBooleans.getInstance().isOnOrbit) {
//                        handsShooter.start();
                        int secX = (int) (gameObjects.get(0).getPosition().getX() - Properties.getInstance().radiusOfOrbitProjectile * Math.cos(Math.toRadians(Properties.getInstance().angleOfOrbitProjectile)));
                        int secY = (int) (gameObjects.get(0).getPosition().getY() + Properties.getInstance().radiusOfOrbitProjectile * Math.sin(Math.toRadians(Properties.getInstance().angleOfOrbitProjectile)));

                        RightHand.getInstance().setLocation(new Point(secX, secY));

                    }


                    int dX_LeftHand = (int) (LeftHand.getInstance().getLocation().x + LeftHand.getInstance().getSize() / 2 - gameObjects.get(0).getPosition().getX() - EPSILON_WIDTH);
                    int dY_LeftHand = (int) (LeftHand.getInstance().getLocation().y + LeftHand.getInstance().getSize() / 2 - gameObjects.get(0).getPosition().getY() - EPSILON_LENGTH);
                    double distanceLeftHand = Math.sqrt((dX_LeftHand * dX_LeftHand) + (dY_LeftHand * dY_LeftHand));

                    if (distanceLeftHand > Properties.getInstance().radiusOfOrbitProjectile) {
                        if ((LeftHand.getInstance().getLocation().x > gameObjects.get(0).getPosition().getX()) && (LeftHand.getInstance().getLocation().y > gameObjects.get(0).getPosition().getY())) {
                            LeftHand.getInstance().setLocation(new Point(LeftHand.getInstance().getLocation().x - 1, LeftHand.getInstance().getLocation().y - 1));
                        }
                        if ((LeftHand.getInstance().getLocation().x > gameObjects.get(0).getPosition().getX()) && (LeftHand.getInstance().getLocation().y < gameObjects.get(0).getPosition().getY())) {
                            LeftHand.getInstance().setLocation(new Point(LeftHand.getInstance().getLocation().x - 1, LeftHand.getInstance().getLocation().y + 1));
                        }
                        if ((LeftHand.getInstance().getLocation().x < gameObjects.get(0).getPosition().getX()) && (LeftHand.getInstance().getLocation().y < gameObjects.get(0).getPosition().getY())) {
                            LeftHand.getInstance().setLocation(new Point(LeftHand.getInstance().getLocation().x + 1, LeftHand.getInstance().getLocation().y + 1));
                        }
                        if ((LeftHand.getInstance().getLocation().x < gameObjects.get(0).getPosition().getX()) && (LeftHand.getInstance().getLocation().y > gameObjects.get(0).getPosition().getY())) {
                            LeftHand.getInstance().setLocation(new Point(LeftHand.getInstance().getLocation().x + 1, LeftHand.getInstance().getLocation().y - 1));
                        }
                    }
                    if (HelpingBooleans.getInstance().isOnOrbit) {
//                        handsShooter.start();
                        int secX = (int) (gameObjects.get(0).getPosition().getX() - Properties.getInstance().radiusOfOrbitProjectile * Math.cos(Math.toRadians(Properties.getInstance().angleOfOrbitProjectile)));
                        int secY = (int) (gameObjects.get(0).getPosition().getY() - Properties.getInstance().radiusOfOrbitProjectile * Math.sin(Math.toRadians(Properties.getInstance().angleOfOrbitProjectile)));

                        LeftHand.getInstance().setLocation(new Point(secX, secY));

                    }
                }
                if (HelpingBooleans.getInstance().isProjectileFinished) {
                    handsShooter.stop();
                    BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().set(1, false);
                }
                if (spentMilliSecondW6 == 56000) { // todo: second after finishing attack
                    Head.getInstance().setSize(Properties.getInstance().sizeOfHead2);
                    Head.getInstance().setLocation(new Point(Properties.getInstance().locationOfHead.x + 50, Properties.getInstance().locationOfHead.y));
                    RightHand.getInstance().setLocation(new Point(Properties.getInstance().locationOfRightHand.x - 20, Properties.getInstance().locationOfRightHand.y + 25));
                    LeftHand.getInstance().setLocation(new Point(Properties.getInstance().locationOfLeftHand.x + 170, Properties.getInstance().locationOfLeftHand.y));

                }

                if (!HelpingBooleans.getInstance().isFistLaunched && Head.getInstance().getHP() < 2 * Properties.getInstance().headOfBossHP / 3) {
                    Fist.getInstance().setLocation(new Point(1200, 400));//todo launch fist
                    HelpingBooleans.getInstance().isFistLaunched = true;
                }


                if (spentMilliSecondW6 == 120000) {
                    BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().set(2, true);
                }
                if (BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().get(2)) { //todo: Vomit is on for 2 min's.
                    if (spentMilliSecondW6 >= 121000) {
                        if (HelpingBooleans.getInstance().isSmileyValidToMoveRight_Vomit && RightHand.getInstance().getLocation().x + RightHand.getInstance().getSize() < PanelsData.getInstance().getBossPanel().getRightX()) {
                            RightHand.getInstance().setLocation(new Point(RightHand.getInstance().getLocation().x + 1, RightHand.getInstance().getLocation().y));
                            LeftHand.getInstance().setLocation(new Point(LeftHand.getInstance().getLocation().x + 1, LeftHand.getInstance().getLocation().y));
                            Head.getInstance().setLocation(new Point(Head.getInstance().getLocation().x + 1, Head.getInstance().getLocation().y));

                        }
                        if (HelpingBooleans.getInstance().isSmileyValidToMoveRight_Vomit && RightHand.getInstance().getLocation().x + RightHand.getInstance().getSize() >= PanelsData.getInstance().getBossPanel().getRightX()) {
                            HelpingBooleans.getInstance().isSmileyValidToMoveRight_Vomit = false;
                            HelpingBooleans.getInstance().isSmileyValidToMoveUp_Vomit = true;
                        }
                        if (HelpingBooleans.getInstance().isSmileyValidToMoveUp_Vomit && Head.getInstance().getLocation().y > PanelsData.getInstance().getBossPanel().getY()) {
                            RightHand.getInstance().setLocation(new Point(RightHand.getInstance().getLocation().x, RightHand.getInstance().getLocation().y - 1));
                            LeftHand.getInstance().setLocation(new Point(LeftHand.getInstance().getLocation().x, LeftHand.getInstance().getLocation().y - 1));
                            Head.getInstance().setLocation(new Point(Head.getInstance().getLocation().x, Head.getInstance().getLocation().y - 1));

                        }
                        if (HelpingBooleans.getInstance().isSmileyValidToMoveUp_Vomit && Head.getInstance().getLocation().y <= PanelsData.getInstance().getBossPanel().getY()) {
                            HelpingBooleans.getInstance().isSmileyValidToMoveRight_Vomit = false;
                            HelpingBooleans.getInstance().isSmileyValidToMoveUp_Vomit = false;
                            HelpingBooleans.getInstance().isSmileyValidToMoveLeft_Vomit = true;

                        }
                        if (HelpingBooleans.getInstance().isSmileyValidToMoveLeft_Vomit && LeftHand.getInstance().getLocation().x > PanelsData.getInstance().getBossPanel().getX() + 50) {
                            RightHand.getInstance().setLocation(new Point(RightHand.getInstance().getLocation().x - 1, RightHand.getInstance().getLocation().y));
                            LeftHand.getInstance().setLocation(new Point(LeftHand.getInstance().getLocation().x - 1, LeftHand.getInstance().getLocation().y));
                            Head.getInstance().setLocation(new Point(Head.getInstance().getLocation().x - 1, Head.getInstance().getLocation().y));
                        }
                        if (HelpingBooleans.getInstance().isSmileyValidToMoveLeft_Vomit && LeftHand.getInstance().getLocation().x <= PanelsData.getInstance().getBossPanel().getX() + 50) {
                            HelpingBooleans.getInstance().isSmileyValidToMoveRight_Vomit = false;
                            HelpingBooleans.getInstance().isSmileyValidToMoveUp_Vomit = false;
                            HelpingBooleans.getInstance().isSmileyValidToMoveLeft_Vomit = false;
                            HelpingBooleans.getInstance().isSmileyValidToMoveDown_Vomit = true;
                        }
                        if (HelpingBooleans.getInstance().isSmileyValidToMoveDown_Vomit && LeftHand.getInstance().getLocation().y < PanelsData.getInstance().getBossPanel().getDownY() - 50) {
                            RightHand.getInstance().setLocation(new Point(RightHand.getInstance().getLocation().x, RightHand.getInstance().getLocation().y + 1));
                            LeftHand.getInstance().setLocation(new Point(LeftHand.getInstance().getLocation().x, LeftHand.getInstance().getLocation().y + 1));
                            Head.getInstance().setLocation(new Point(Head.getInstance().getLocation().x, Head.getInstance().getLocation().y + 1));
                        }
                        if (HelpingBooleans.getInstance().isSmileyValidToMoveDown_Vomit && LeftHand.getInstance().getLocation().y >= PanelsData.getInstance().getBossPanel().getDownY() - 50) {
                            HelpingBooleans.getInstance().isSmileyValidToMoveRight_Vomit = false;
                            HelpingBooleans.getInstance().isSmileyValidToMoveUp_Vomit = false;
                            HelpingBooleans.getInstance().isSmileyValidToMoveLeft_Vomit = false;
                            HelpingBooleans.getInstance().isSmileyValidToMoveDown_Vomit = false;
                            HelpingBooleans.getInstance().isSmileyValidToMoveRight2_Vomit = true;
                        }
                        if (!HelpingBooleans.getInstance().isSmileyValidToMoveLeft_Vomit && HelpingBooleans.getInstance().isSmileyValidToMoveRight2_Vomit && Head.getInstance().getLocation().x < Properties.getInstance().locationOfHead.x + 50) {
                            RightHand.getInstance().setLocation(new Point(RightHand.getInstance().getLocation().x + 1, RightHand.getInstance().getLocation().y));
                            LeftHand.getInstance().setLocation(new Point(LeftHand.getInstance().getLocation().x + 1, LeftHand.getInstance().getLocation().y));
                            Head.getInstance().setLocation(new Point(Head.getInstance().getLocation().x + 1, Head.getInstance().getLocation().y));
                        }
                        if (!HelpingBooleans.getInstance().isSmileyValidToMoveLeft_Vomit && HelpingBooleans.getInstance().isSmileyValidToMoveRight2_Vomit && Head.getInstance().getLocation().x >= Properties.getInstance().locationOfHead.x + 50) {
                            BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().set(2, false);
                            HelpingBooleans.getInstance().isSmileyValidToMoveRight2_Vomit = false;
                        }
                    }
                }

                if (spentMilliSecondW6 == 7000) { // todo: PowerPunch
                    BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().set(3, true);
                }

                if (spentMilliSecondW6 == 325000) HelpingBooleans.getInstance().startPunchRight = true;
                if (HelpingBooleans.getInstance().startPunchRight && !HelpingBooleans.getInstance().isFistPunchedRight && HelpingBooleans.getInstance().isFistLaunched && BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().get(3)) { //todo: Power Punch 1st
                    if (Fist.getInstance().getLocation().x + Fist.getInstance().getSize() < PanelsData.getInstance().getBossPanel().getRightX()) {
                        Fist.getInstance().setLocation(new Point(Fist.getInstance().getLocation().x + 1, Fist.getInstance().getLocation().y));
                    }
                    if (Fist.getInstance().getLocation().x + Fist.getInstance().getSize() >= PanelsData.getInstance().getBossPanel().getRightX()) {
                        PanelsData.getInstance().getBossPanel().setWidth(PanelsData.getInstance().getBossPanel().getWidth() - 200);
                        Fist.getInstance().setLocation(new Point(PanelsData.getInstance().getBossPanel().getRightX() - 200, 400));
                        HelpingBooleans.getInstance().isFistPunchedRight = true;

                    }
                }
                if (spentMilliSecondW6 == 200000) HelpingBooleans.getInstance().startPunchLeft = true;
                if (HelpingBooleans.getInstance().startPunchLeft && !HelpingBooleans.getInstance().isFistPunchedRight && HelpingBooleans.getInstance().isFistLaunched && BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().get(3)) { //todo: Power Punch 1st
                    if (Fist.getInstance().getLocation().x > PanelsData.getInstance().getBossPanel().getX()) {
                        Fist.getInstance().setLocation(new Point(Fist.getInstance().getLocation().x - 2, Fist.getInstance().getLocation().y));
                    }
                    if (Fist.getInstance().getLocation().x <= PanelsData.getInstance().getBossPanel().getX()) {
                        PanelsData.getInstance().getBossPanel().setX(PanelsData.getInstance().getBossPanel().getX() + 200);
                        PanelsData.getInstance().getBossPanel().setWidth(PanelsData.getInstance().getBossPanel().getWidth() - 200);
                        Fist.getInstance().setLocation(new Point((int) (PanelsData.getInstance().getBossPanel().getX() + 50), Fist.getInstance().getLocation().y));
                        HelpingBooleans.getInstance().isFistPunchedRight = true;

                    }
                }

                if (spentMilliSecondW6 == 240000) HelpingBooleans.getInstance().startPunchUp = true;
                if (HelpingBooleans.getInstance().startPunchUp && !HelpingBooleans.getInstance().isFistPunchedUp && HelpingBooleans.getInstance().isFistLaunched && BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().get(3)) { //todo: Power Punch 1st
                    if (Fist.getInstance().getLocation().y > PanelsData.getInstance().getBossPanel().getY()) {
                        Fist.getInstance().setLocation(new Point(Fist.getInstance().getLocation().x, Fist.getInstance().getLocation().y - 2));
                    }
                    if (Fist.getInstance().getLocation().y <= PanelsData.getInstance().getBossPanel().getY()) {
                        PanelsData.getInstance().getBossPanel().setY(PanelsData.getInstance().getBossPanel().getY() + 200);
                        PanelsData.getInstance().getBossPanel().setWidth(PanelsData.getInstance().getBossPanel().getHeight() - 200);
                        Fist.getInstance().setLocation(new Point((int) PanelsData.getInstance().getBossPanel().getX(), Fist.getInstance().getLocation().y));
                        HelpingBooleans.getInstance().isFistPunchedUp = true;

                    }
                }

                if (spentMilliSecondW6 == 275000) HelpingBooleans.getInstance().startPunchDown = true;
                if (HelpingBooleans.getInstance().startPunchDown && !HelpingBooleans.getInstance().isFistPunchedDown && HelpingBooleans.getInstance().isFistLaunched && BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().get(3)) { //todo: Power Punch 1st
                    if (Fist.getInstance().getLocation().y + Fist.getInstance().getSize() < PanelsData.getInstance().getBossPanel().getDownY()) {
                        Fist.getInstance().setLocation(new Point(Fist.getInstance().getLocation().x, Fist.getInstance().getLocation().y + 2));
                    }
                    if (Fist.getInstance().getLocation().y + Fist.getInstance().getSize() >= PanelsData.getInstance().getBossPanel().getDownY()) {
                        PanelsData.getInstance().getBossPanel().setWidth(PanelsData.getInstance().getBossPanel().getHeight() - 200);
                        Fist.getInstance().setLocation(new Point((int) PanelsData.getInstance().getBossPanel().getX(), Fist.getInstance().getLocation().y));
                        HelpingBooleans.getInstance().isFistPunchedUp = true;

                    }
                }
                //todo: Power Punch بازم جا داره اضافه بشه. if required.

                //todo: Quake
                if (spentMilliSecondW6 == 350000) {
                    BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().set(4, true);
                }
                if (BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().get(4)) {
                    if (Fist.getInstance().getLocation().y + Fist.getInstance().getSize() < PanelsData.getInstance().getBossPanel().getDownY()) {
                        Fist.getInstance().setLocation(new Point(Fist.getInstance().getLocation().x, Fist.getInstance().getLocation().y + 5));
                    }
                    if (Fist.getInstance().getLocation().y + Fist.getInstance().getSize() >= PanelsData.getInstance().getBossPanel().getDownY()) {
                        double distanceFistAndEpsilon_dx = Math.abs(gameObjects.get(0).getPosition().getX() - Fist.getInstance().getLocation().x);
                        double distanceFistAndEpsilon_dy = Math.abs(gameObjects.get(0).getPosition().getY() - Fist.getInstance().getLocation().y);
                        double distanceFistAndEpsilon = Math.sqrt((distanceFistAndEpsilon_dx * distanceFistAndEpsilon_dx) + (distanceFistAndEpsilon_dy * distanceFistAndEpsilon_dy));

                        double distanceFistAndLeftHand_dx = Math.abs(LeftHand.getInstance().getLocation().x - Fist.getInstance().getLocation().x);
                        double distanceFistAndLeftHand_dy = Math.abs(LeftHand.getInstance().getLocation().y - Fist.getInstance().getLocation().y);
                        double distanceFistAndLeftHand = Math.sqrt((distanceFistAndLeftHand_dx * distanceFistAndLeftHand_dx) + (distanceFistAndLeftHand_dy * distanceFistAndLeftHand_dy));

                        double distanceFistAndRightHand_dx = Math.abs(RightHand.getInstance().getLocation().x - Fist.getInstance().getLocation().x);
                        double distanceFistAndRightHand_dy = Math.abs(RightHand.getInstance().getLocation().y - Fist.getInstance().getLocation().y);
                        double distanceFistAndRightHand = Math.sqrt((distanceFistAndRightHand_dx * distanceFistAndRightHand_dx) + (distanceFistAndRightHand_dy * distanceFistAndRightHand_dy));

                        double distanceFistAndHead_dx = Math.abs(Head.getInstance().getLocation().x - Fist.getInstance().getLocation().x);
                        double distanceFistAndHead_dy = Math.abs(Head.getInstance().getLocation().y - Fist.getInstance().getLocation().y);
                        double distanceFistAndHead = Math.sqrt((distanceFistAndHead_dx * distanceFistAndHead_dx) + (distanceFistAndHead_dy * distanceFistAndHead_dy));

                        if (distanceFistAndEpsilon < 150) {
                            gameObjects.get(0).setPosition(new Position(gameObjects.get(0).getPosition().getX() - 160, gameObjects.get(0).getPosition().getY() - 10));
                        }
                        if (distanceFistAndHead < 150 || distanceFistAndLeftHand < 150 || distanceFistAndRightHand < 150) { //todo: Impact
                            Head.getInstance().setLocation(new Point(Head.getInstance().getLocation().x, Head.getInstance().getLocation().y - 150));
                            RightHand.getInstance().setLocation(new Point(RightHand.getInstance().getLocation().x, RightHand.getInstance().getLocation().y - 150));
                            LeftHand.getInstance().setLocation(new Point(LeftHand.getInstance().getLocation().x, LeftHand.getInstance().getLocation().y - 150));
                        }
                        HelpingBooleans.getInstance().startQuakeMouseAttack = true; // todo:Mouse & KeyBoard changing for 8"
                        BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().set(4, false);

                    }
                }
                if (spentMilliSecondQuake < 7000) {
                    HelpingBooleans.getInstance().doQuakeMouseAttack = true;
                }
                if (spentMilliSecondQuake == 7000) {
                    HelpingBooleans.getInstance().doQuakeMouseAttack = false;
                    HelpingBooleans.getInstance().startQuakeMouseAttack = false; // finishing the attack

                }

                if (spentMilliSecondW6 == 400000) { //todo: Rapid Fire
                    BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().set(5, true);
                }
                if (BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().get(5)) {
                    headShooter.start();
                }
                if (Properties.getInstance().headShooterSecondCounter <= 0) {
                    headShooter.stop();
                    BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().set(5, false);
                }


            }


        }

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
                showOfCollectiblesG.set(i, spentMilliSecond >= Properties.getInstance().startedTimeOfCollectiblesG.get(i) && spentMilliSecond <= Properties.getInstance().startedTimeOfCollectiblesG.get(i) + 10000);

        }

        if (showOfPointerItemHelper) showOfPointerItem = spentMilliSecond >= 5000 && spentMilliSecond <= 15000;


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


        if (((spentMilliSecond <= startTimeFromActivationOfPointerItem + 10000) && activateMechanismOfPointerItem) || HelpingBooleans.getInstance().lineShower2) {
            lineShower = true;
            targetWithMouse = new TargetWithMouse(new Point2D.Double((gameObjects.get(0)).getPosition().getX(), (gameObjects.get(0)).getPosition().getY()));

        } else lineShower = false;
        //todo: Shrinkage :
        if (spentMilliSecond < 2000) {
            if (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT > GLASS_FRAME_DIMENSION_MIN_HEIGHT && Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH > GLASS_FRAME_DIMENSION_MIN_WIDTH) {
                Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT -= 0.75;
                Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH -= 0.75;
//                frame.setSize((int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT);
                isValidStore = false;

            }
//            time.restart();
        }

        if (spentMilliSecond % 5000 == 0 && spentMilliSecond >= 5000) {
            System.out.println(Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
            if (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT < GLASS_FRAME_DIMENSION_MAX_HEIGHT && Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH < GLASS_FRAME_DIMENSION_MAX_WIDTH) {
                Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT += 1 * Properties.getInstance().constantOfShrinkagePanel;
                Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH += 1 * Properties.getInstance().constantOfShrinkagePanel;
//                frame.setSize((int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT);
                isValidStore = false;
            }
//            time.restart();
        }

        if (spentMilliSecond % 5000 == 3000 && spentMilliSecond >= 5000) {
            if (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT > GLASS_FRAME_DIMENSION_MIN_HEIGHT && Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH > GLASS_FRAME_DIMENSION_MIN_WIDTH) {
                Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT -= 1 * Properties.getInstance().constantOfShrinkagePanel;
                Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH -= 1 * Properties.getInstance().constantOfShrinkagePanel;
                isValidStore = false;
            }
        }

        IS_VALID_STORE = !((spentMilliSecond % 5000 == 3000) || (spentMilliSecond % 5000 == 0));


        if (isValid7) {
            frame.dispose();
            isValid7 = false;
            timerOfGame.reset();
        }

        if ((normalEnemies.get(1)).getHP() <= 0 && BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)) {
            shotTimer.stop();
            CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().add(new Collectable(( normalEnemies.get(1)).getLocation().x, ( normalEnemies.get(1)).getLocation().y + 10, 10, Color.CYAN));
            CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().add(new Collectable(( normalEnemies.get(1)).getLocation().x + 15, ( normalEnemies.get(1)).getLocation().y + 10, 10, Color.CYAN));
            CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().add(new Collectable(( normalEnemies.get(1)).getLocation().x + 30, ( normalEnemies.get(1)).getLocation().y + 10, 10, Color.CYAN));
            CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().add(new Collectable(( normalEnemies.get(1)).getLocation().x - 15, ( normalEnemies.get(1)).getLocation().y + 10, 10, Color.CYAN));
            CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().add(new Collectable(( normalEnemies.get(1)).getLocation().x - 30, ( normalEnemies.get(1)).getLocation().y + 10, 10, Color.CYAN));
            CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().add(new Collectable(( normalEnemies.get(1)).getLocation().x, ( normalEnemies.get(1)).getLocation().y + 30, 10, Color.CYAN));
            CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().add(new Collectable(( normalEnemies.get(1)).getLocation().x, ( normalEnemies.get(1)).getLocation().y + 50, 10, Color.CYAN));
            BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(2, false);

            BooleansOfCollectibles.getInstance().getIsValidToCollect().set(2, true);
        }


//        time.start();
        if (Properties.getInstance().play) {


            for (int i = 0; i < greenEnemies1.size(); i++) {

                if (((GreenEnemyModel) greenEnemies1.get(i)).getLifeValue() == 0) {
                    if (((GreenEnemyModel) greenEnemies1.get(i)).isAlive()) {
                        Properties.getInstance().positionsOfCollectiblesG.add((greenEnemies1.get(i)).getPosition());
                        collectibleItemsG.add(i, new TKM2_Item_Model((greenEnemies1.get(i)).getPosition()));

                    }
                    (greenEnemies1.get(i)).setPosition(new Position(1111111111, 1111111111));
                    ((GreenEnemyModel) greenEnemies1.get(i)).setAlive(false);
                    Properties.getInstance().startedTimeOfCollectiblesG.set(i, spentMilliSecond);

                }
            }
            for (int i = 0; i < yellowEnemies1.size(); i++) {

                if ((yellowEnemies1.get(i)).getLifeValue() == 0) {
                    if (BooleansOfCollectibles.getInstance().getIsValidYEtoCollect().get(i)) {
                        CollectablesOfEnemies.getInstance().getCollectablesOfYE().add(new Collectable((int) yellowEnemies1.get(i).getPosition().getX(), (int) yellowEnemies1.get(i).getPosition().getY(), 10, Color.ORANGE));
                        CollectablesOfEnemies.getInstance().getCollectablesOfYE().add(new Collectable((int) yellowEnemies1.get(i).getPosition().getX() + 20, (int) yellowEnemies1.get(i).getPosition().getY(), 10, Color.ORANGE));
                        BooleansOfCollectibles.getInstance().getIsValidYEtoCollect().set(i, false);
                    }

                    (yellowEnemies1.get(i)).setPosition(new Position(1111111111, 1111111111));
                    (yellowEnemies1.get(i)).setAlive(false);
                    Properties.getInstance().startedTimeOfCollectiblesY.set(i, spentMilliSecond);

                }
            }

            if (Properties.getInstance().HP <= 0) {
                ((EpsilonModel) gameObjects.get(0)).sizeOfEpsilon.setHeight(((EpsilonModel) gameObjects.get(0)).sizeOfEpsilon.getHeight() + 1);
                ((EpsilonModel) gameObjects.get(0)).sizeOfEpsilon.setWidth(((EpsilonModel) gameObjects.get(0)).sizeOfEpsilon.getWidth() + 1);
            }


        }
        System.out.println("x of moving enemy: " + gameObjects.get(1).getPosition().getX());
        System.out.println("x of moving epsilon: " + gameObjects.get(0).getPosition().getX());


        if (isInvisible && ((EpsilonModel) gameObjects.get(0)).sizeOfEpsilon.getWidth() >= PANEL_SIZE.getWidth()) {
            Properties.getInstance().play = false;
            if (isValid8) {
                view.setVisible(false);
//                frame.dispose();
                disposer();
                new GameOverFrame();
                isValid8 = false;
                isInvisible = false;

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
        for (Bullet bullet : bulletsOfEpsilonProShoot) {
            bullet.move2();
        }
        for (Bullet bullet : bulletsOfHeadRapidFireShoot) {
            bullet.move2();
        }
        for (Bullet bullet : bulletsOfWyrm) {
            bullet.move();
        }
        for (Bullet bullet : bulletsOfLeftHand) {
            bullet.move();
        }
        for (Bullet bullet : bulletsOfRightHand) {
            bullet.move();
        }


        FunctionalMethods.getInstance().checkCollisions();
        if (BooleansOfEnemies.getInstance().isWritOfAstrape()) FunctionalMethods.getInstance().writOfAstrape();


//        time.restart();
        view.repaint();
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameObjects.forEach(GameObject::update);
        frame.update();

        if (Properties.getInstance().play) update();
    }

    public static GameLoop getINSTANCE() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new GameLoop();
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
        time.restart();
        time.stop();
        timer1.restart();
        timer1.stop();
        timerOfGame.reset();
        timerOfGame.stop();
        view.removeAll();

        Properties.getInstance().HP = 100;
        Properties.getInstance().XP = 0.0;
    }

    public void isValidToShowYE() {
        for (YellowEnemyModel yellowEnemyModel : yellowEnemies1) {
            Rectangle rectangle = new Rectangle((int) yellowEnemyModel.getPosition().getX(), (int) yellowEnemyModel.getPosition().getY(), (int) yellowEnemyModel.getRadius(), (int) yellowEnemyModel.getRadius());
            yellowEnemyModel.setValidToShow(rectangle.intersects(panels.get(1).getRectangle()) || rectangle.intersects(panels.get(0).getRectangle()));

        }
    }

    public void isValidToShowGE() {
        for (GameObject greenEnemyModel : greenEnemies1) {
            Rectangle rectangle = new Rectangle((int) greenEnemyModel.getPosition().getX(), (int) greenEnemyModel.getPosition().getY(), greenEnemyModel.getBounds().width, greenEnemyModel.getBounds().height);
            ((GreenEnemyModel) greenEnemyModel).setValidToShow(rectangle.intersects(panels.get(1).getRectangle()) || rectangle.intersects(panels.get(0).getRectangle()));

        }
    }

    public void movementOfP1Enemies() {
        //todo: movement if its not collided
        for (int i = 0; i < yellowEnemies1.size(); i++) {
            if (!isCollidedY.get(i)) {
                (yellowEnemies1.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - yellowEnemies1.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - yellowEnemies1.get(i).getPosition().getY())));
                (yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
                (yellowEnemies1.get(i)).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, (yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getX(), (yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
            }
        }

        for (int i = 0; i < greenEnemies1.size(); i++) {
            if (!isCollidedG.get(i)) {
                ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - greenEnemies1.get(i).getPosition().getX(), gameObjects.get(0).getPosition().getY() - greenEnemies1.get(i).getPosition().getY())));
                ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
                greenEnemies1.get(i).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().getX(), ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().getY()));
            }
        }


        //todo :YellowEnemy:
        for (int i = 0; i < yellowEnemies1.size(); i++) {
            double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
            double centerOfEnemyX = yellowEnemies1.get(i).getPosition().getX() + (yellowEnemies1.get(i)).getRadius();
            double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
            double centerOfEnemyY = yellowEnemies1.get(i).getPosition().getY() + (yellowEnemies1.get(i)).getRadius();


            double sqrt = Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY)));
            if (sqrt < (((EpsilonModel) gameObjects.get(0)).getRadius() + (yellowEnemies1.get(i)).getRadius())) {
                System.out.println(":)");
                (yellowEnemies1.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(0).getPosition().getX() + yellowEnemies1.get(i).getPosition().getX() + 25, -gameObjects.get(0).getPosition().getY() + yellowEnemies1.get(i).getPosition().getY() + 25)));

                (yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
                startFromCollision.put(i, spentMilliSecond);
                Properties.getInstance().HP -= 5;
                isCollidedY.set(i, true);
                yellowEnemies1.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, (yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getX(), (yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getY()));

            }
            if (isCollidedY.get(i)) {
                if (sqrt > 700) {
                    isCollidedY.set(i, false);

                }
            }
        }
        for (int i = 0; i < yellowEnemies1.size(); i++) {
            for (int j = i; j < yellowEnemies1.size(); j++) {
                if (i != j) {
                    double centerOfEnemy1X = yellowEnemies1.get(i).getPosition().getX() + (yellowEnemies1.get(i)).getRadius();
                    double centerOfEnemy2X = yellowEnemies1.get(j).getPosition().getX() + (yellowEnemies1.get(j)).getRadius();
                    double centerOfEnemy1Y = yellowEnemies1.get(i).getPosition().getY() + (yellowEnemies1.get(i)).getRadius();
                    double centerOfEnemy2Y = yellowEnemies1.get(j).getPosition().getY() + (yellowEnemies1.get(j)).getRadius();


                    if (Math.sqrt(((centerOfEnemy1X - centerOfEnemy2X) * (centerOfEnemy1X - centerOfEnemy2X)) + ((centerOfEnemy1Y - centerOfEnemy2Y) * (centerOfEnemy1Y - centerOfEnemy2Y))) <= ((yellowEnemies1.get(i)).getRadius() + (yellowEnemies1.get(j)).getRadius())) {
                        System.out.println(":)");
                        (yellowEnemies1.get(i)).getMovementOfYellowEnemy().setVector1((new Vector2D(-yellowEnemies1.get(i).getPosition().getX() + yellowEnemies1.get(j).getPosition().getX() + 25, -yellowEnemies1.get(j).getPosition().getY() + yellowEnemies1.get(i).getPosition().getY() + 25)));
                        (yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().normalize();
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().setVector1((new Vector2D(-gameObjects.get(j).getPosition().getX() + gameObjects.get(i).getPosition().getX() + 25, -gameObjects.get(i).getPosition().getY() + gameObjects.get(j).getPosition().getY() + 25)));
//                                ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().normalize();

                        isCollidedEnemies.set(i, true);
//                                isCollidedEnemies.set(j, true);
                        collidedOne = i;
                        collidedTwo = j;
                        yellowEnemies1.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, yellowEnemies1.get(i).getMovementOfYellowEnemy().getVector1().getX(), yellowEnemies1.get(i).getMovementOfYellowEnemy().getVector1().getY()));
//                                gameObjects.get(j).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().getX(), ((YellowEnemyModel) gameObjects.get(j)).getMovementOfYellowEnemy().getVector1().getY()));


                    }
                }
            }
        }

        for (YellowEnemyModel yellowEnemyModel : yellowEnemies1) {
            yellowEnemyModel.getMovementOfYellowEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - yellowEnemyModel.getPosition().getX(), gameObjects.get(0).getPosition().getY() - yellowEnemyModel.getPosition().getY())));
            yellowEnemyModel.getMovementOfYellowEnemy().getVector1().normalize();
        }

        for (int j = 0; j < yellowEnemies1.size(); j++) {
            if (!isCollidedY.get(j)) {
                for (int i = 0; i < yellowEnemies1.size(); i++) {
                    if (i == j) {
                        double centerOfEpsilonX = gameObjects.get(0).getPosition().getX() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                        double centerOfEnemyX = yellowEnemies1.get(i).getPosition().getX() + (yellowEnemies1.get(i)).getRadius();
                        double centerOfEpsilonY = gameObjects.get(0).getPosition().getY() + ((EpsilonModel) gameObjects.get(0)).getRadius();
                        double centerOfEnemyY = yellowEnemies1.get(i).getPosition().getY() + (yellowEnemies1.get(i)).getRadius();

                        ACCELERATION_OF_YELLOW_ENEMIES = 1;
                        yellowEnemies1.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, (yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getX(), (yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
                        if (waveOHB) {
                            isCollidedY.set(i, true);
                            waveOHB = false;
                        }

                        if (Math.sqrt(((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY))) < 150 + (((EpsilonModel) gameObjects.get(0)).getRadius() + (yellowEnemies1.get(i)).getRadius())) {
                            ACCELERATION_OF_YELLOW_ENEMIES = 3;
//                                ACCELERATION_OF_EPSILON = 4;

                        } else {
                            ACCELERATION_OF_YELLOW_ENEMIES = 1;
//                                ACCELERATION_OF_EPSILON = 2;
                        }

                    }

                }

            }
            if (isCollidedY.get(j)) {
                for (int i = 0; i < yellowEnemies1.size(); i++) {
                    if (i == j) {
                        ACCELERATION_OF_YELLOW_ENEMIES = 2;
                        yellowEnemies1.get(i).getPosition().applyOfYellowEnemy(new MovementOfYellowEnemy(0, -(yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getX(), -(yellowEnemies1.get(i)).getMovementOfYellowEnemy().getVector1().getY()));
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


            double a = ((centerOfEpsilonX - centerOfEnemyX) * (centerOfEpsilonX - centerOfEnemyX)) + ((centerOfEpsilonY - centerOfEnemyY) * (centerOfEpsilonY - centerOfEnemyY));
            if (Math.sqrt(a) < (((EpsilonModel) gameObjects.get(0)).getRadius() + ((GreenEnemyModel) greenEnemies1.get(i)).getRadius())) {
                System.out.println(":)");
                ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().setVector1((new Vector2D(-gameObjects.get(0).getPosition().getX() + greenEnemies1.get(i).getPosition().getX() + 25, -gameObjects.get(0).getPosition().getY() + greenEnemies1.get(i).getPosition().getY() + 25)));

                ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().normalize();
                startFromCollision.put(i, spentMilliSecond);
                Properties.getInstance().HP -= 5;
                isCollidedG.set(i, true);
                greenEnemies1.get(i).getPosition().applyOfGreenEnemy(new MovementOfGreenEnemy(0, ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().getX(), ((GreenEnemyModel) greenEnemies1.get(i)).getMovementOfGreenEnemy().getVector1().getY()));

            }
            if (isCollidedG.get(i)) {
                if (Math.sqrt(a) > 700) {
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

        for (GameObject value : greenEnemies1) {
            ((GreenEnemyModel) value).getMovementOfGreenEnemy().setVector1((new Vector2D(gameObjects.get(0).getPosition().getX() - value.getPosition().getX(), gameObjects.get(0).getPosition().getY() - value.getPosition().getY())));
            ((GreenEnemyModel) value).getMovementOfGreenEnemy().getVector1().normalize();
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
                double distanceX = (yellowEnemies1.get(i)).getPosition().getX() - (greenEnemies1.get(j)).getPosition().getX();
                double distanceY = (yellowEnemies1.get(i)).getPosition().getY() - (greenEnemies1.get(j)).getPosition().getY();
                double oclidianDistance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
                if (oclidianDistance < 2 * ((GreenEnemyModel) greenEnemies1.get(j)).getRadius() + 20) {
//                        ((GreenEnemyModel) greenEnemies1.get(j)).getMovementOfGreenEnemy().setVector1((new Vector2D(-greenEnemies1.get(j).getPosition().getX() + greenEnemies1.get(j).getPosition().getX() + 25, -greenEnemies1.get(j).getPosition().getY() + greenEnemies1.get(j).getPosition().getY() + 25)));
//                        ((GreenEnemyModel) greenEnemies1.get(j)).getMovementOfGreenEnemy().getVector1().normalize();
                    isCollidedG.set(j, true);
                    isCollidedY.set(i, true);
                }
            }
        }

    }

    public void movementOfOmenoct() {
        //todo: Omenoct movement:
        if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)) {
            Rectangle epsilon = new Rectangle((int) (gameObjects.get(0)).getPosition().getX(), (int) gameObjects.get(0).getPosition().getY(), EPSILON_WIDTH, EPSILON_LENGTH);
            int leftDistance = (int) ((normalEnemies.get(1)).getLocation().x - panels.get(0).getX());
            int rightDistance = panels.get(0).getRightX() - ( normalEnemies.get(1)).getLocation().x;
            int upperDistance = (int) (( normalEnemies.get(1)).getLocation().y - panels.get(0).getY());
            int lowerDistance = panels.get(0).getDownY() - ( normalEnemies.get(1)).getLocation().y;
            int tempDistance = Math.min(Math.min(leftDistance, rightDistance), Math.min(upperDistance, lowerDistance));

            //todo: panel.get(1)
            int leftDistance1 = (int) ((normalEnemies.get(1)).getLocation().x - panels.get(1).getX());
            int rightDistance1 = panels.get(1).getRightX() - (normalEnemies.get(1)).getLocation().x;
            int upperDistance1 = (int) ((normalEnemies.get(1)).getLocation().y - panels.get(1).getY());
            int lowerDistance1 = panels.get(1).getDownY() - (normalEnemies.get(1)).getLocation().y;
            int tempDistance1 = Math.min(Math.min(leftDistance1, rightDistance1), Math.min(upperDistance1, lowerDistance1));

            if (shapeIntersects(((Omenoct)normalEnemies.get(1)).getShape(), panels.get(0).getRectangle()) && shapeIntersects(((Omenoct)normalEnemies.get(1)).getShape(), panels.get(1).getRectangle())) {

                if (leftDistance == tempDistance) {
                    (normalEnemies.get(1)).setLocation(new Point((normalEnemies.get(1)).getLocation().x - 1, (normalEnemies.get(1)).getLocation().y));
                } else if (rightDistance == tempDistance) {
                    (normalEnemies.get(1)).setLocation(new Point((normalEnemies.get(1)).getLocation().x + 1, (normalEnemies.get(1)).getLocation().y));
                } else if (upperDistance == tempDistance) {
                    if ((normalEnemies.get(1)).getLocation().y - 8 > panels.get(0).getY()) {
                        (normalEnemies.get(1)).setLocation(new Point((normalEnemies.get(1)).getLocation().x, (normalEnemies.get(1)).getLocation().y - 1));

                    }
                    if (!isEnteredToPanel2_Omenoct && (normalEnemies.get(1)).getLocation().y - 8 == panels.get(0).getY()) {
                        if ((panels.get(0).getRectangle().contains(epsilon) && panels.get(1).getRectangle().intersects(panels.get(0).getRectangle())) || (!panels.get(1).getRectangle().contains(epsilon) && (normalEnemies.get(1)).getLocation().y - 8 == panels.get(0).getY())) {
                            if ((normalEnemies.get(1)).getLocation().x > epsilon.x) {
                                (normalEnemies.get(1)).setLocation(new Point((normalEnemies.get(1)).getLocation().x - 2, (normalEnemies.get(1)).getLocation().y));
                            } else if ((normalEnemies.get(1)).getLocation().x < epsilon.x) {
                                (normalEnemies.get(1)).setLocation(new Point((normalEnemies.get(1)).getLocation().x + 2, (normalEnemies.get(1)).getLocation().y));
                            }
                        }
                    }
                    if (panels.get(0).getRectangle().contains(epsilon)) {
                        if ((normalEnemies.get(1)).getLocation().x - (double) ((normalEnemies.get(1)).getSize() / 2) < panels.get(0).getX()) {
                            (normalEnemies.get(1)).setLocation(new Point((int) (panels.get(0).getX() + ((normalEnemies.get(1)).getSize() / 2)), (int) (normalEnemies.get(1)).getLocation().getY()));
                        }
                    }
                    if (panels.get(0).getRectangle().contains(epsilon)) {
                        if ((normalEnemies.get(1)).getLocation().x + (2 * (normalEnemies.get(1)).getSize()) > panels.get(0).getRightX()) {
                            (normalEnemies.get(1)).setLocation(new Point(panels.get(0).getRightX() - (2 * (normalEnemies.get(1)).getSize()), (int) (normalEnemies.get(1)).getLocation().getY()));
                        }
                    }
                    if (panels.get(0).getRectangle().intersects(panels.get(1).getRectangle()) && !panels.get(0).getRectangle().contains(epsilon) && panels.get(1).getRectangle().contains(epsilon)) {
                        if (!isEnteredToPanel2_Omenoct) {
                            (normalEnemies.get(1)).setLocation(new Point(panels.get(0).getRightX(), (int) (panels.get(1).getY() + 5)));
                            isEnteredToPanel2_Omenoct = true;
                        }

                    }


                } else {
                    if ((normalEnemies.get(1)).getLocation().y + 8 < panels.get(0).getDownY()) {
                        (normalEnemies.get(1)).setLocation(new Point((normalEnemies.get(1)).getLocation().x, (normalEnemies.get(1)).getLocation().y + 1));
                    }
                    if ((normalEnemies.get(1)).getLocation().y + 8 == panels.get(0).getDownY()) {
                        if ((normalEnemies.get(1)).getLocation().x > epsilon.x) {
                            (normalEnemies.get(1)).setLocation(new Point((normalEnemies.get(1)).getLocation().x - 2, (normalEnemies.get(1)).getLocation().y));
                        } else if ((normalEnemies.get(1)).getLocation().x < epsilon.x) {
                            (normalEnemies.get(1)).setLocation(new Point((normalEnemies.get(1)).getLocation().x + 2, (normalEnemies.get(1)).getLocation().y));
                        }
                    }
                    if ((normalEnemies.get(1)).getLocation().x - (double) ((normalEnemies.get(1)).getSize() / 2) < panels.get(0).getX()) {
                        (normalEnemies.get(1)).setLocation(new Point((int) (panels.get(0).getX() + ((normalEnemies.get(1)).getSize() / 2)), (int) (normalEnemies.get(1)).getLocation().getY()));
                    }
                    if ((normalEnemies.get(1)).getLocation().x + ((normalEnemies.get(1)).getSize() / 2) > panels.get(0).getRightX()) {
                        (normalEnemies.get(1)).setLocation(new Point(panels.get(0).getRightX() - ((normalEnemies.get(1)).getSize() / 2), (int) (normalEnemies.get(1)).getLocation().getY()));
                    }
                }


            } else if (shapeIntersects(((Omenoct)normalEnemies.get(1)).getShape(), panels.get(1).getRectangle()) && !shapeIntersects(((Omenoct)normalEnemies.get(1)).getShape(), panels.get(0).getRectangle())) {
                if (epsilon.intersects(panels.get(1).getRectangle()) && !epsilon.intersects(panels.get(0).getRectangle())) {
                    (normalEnemies.get(1)).setLocation(new Point(epsilon.x, (normalEnemies.get(1)).getLocation().y));
                }
 /*               if (leftDistance1 == tempDistance1) {
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
                }*/
//                    if(panels.get(0).getRectangle().contains(epsilon)&&!panels.get(1).getRectangle().contains(epsilon)){
//                        if(!isEnteredToPanel1_Omenoct) {
//                            omenoct.setLocation(new Point(epsilon.x, panels.get(0).getY()));
//                            isEnteredToPanel1_Omenoct = true;
//                        }
//                    }
            } else if (!shapeIntersects(((Omenoct) normalEnemies.get(1)).getShape(), panels.get(1).getRectangle()) && shapeIntersects(((Omenoct) normalEnemies.get(1)).getShape(), panels.get(0).getRectangle())) {
                if (leftDistance == tempDistance) {
                    ( normalEnemies.get(1)).setLocation(new Point((normalEnemies.get(1)).getLocation().x - 1, (normalEnemies.get(1)).getLocation().y));
                } else if (rightDistance == tempDistance) {
                    if (( normalEnemies.get(1)).getLocation().y + ( normalEnemies.get(1)).getSize() - 8 > panels.get(0).getRightX()) {
                        ( normalEnemies.get(1)).setLocation(new Point((normalEnemies.get(1)).getLocation().x + 1, (normalEnemies.get(1)).getLocation().y));
                    }
                    if (!isEnteredToPanel2_Omenoct && (normalEnemies.get(1)).getLocation().y + (normalEnemies.get(1)).getSize() - 8 == panels.get(0).getRightX()) {
                        if ((panels.get(0).getRectangle().contains(epsilon) && panels.get(1).getRectangle().intersects(panels.get(0).getRectangle())) || (!panels.get(1).getRectangle().contains(epsilon) && (normalEnemies.get(1)).getLocation().y - 8 == panels.get(0).getY())) {
                            if ((normalEnemies.get(1)).getLocation().y > epsilon.y) {
                                (normalEnemies.get(1)).setLocation(new Point((normalEnemies.get(1)).getLocation().x, (normalEnemies.get(1)).getLocation().y - 2));
                            } else if ((normalEnemies.get(1)).getLocation().y < epsilon.y) {
                                (normalEnemies.get(1)).setLocation(new Point((normalEnemies.get(1)).getLocation().x, (normalEnemies.get(1)).getLocation().y + 2));
                            }
                        }
                    }
                    if (panels.get(0).getRectangle().contains(epsilon)) {
                        if ((normalEnemies.get(1)).getLocation().y - ((double) (normalEnemies.get(1)).getSize() / 2) < panels.get(0).getY()) {
                            ( normalEnemies.get(1)).setLocation(new Point((int) panels.get(0).getX(), (int) (normalEnemies.get(1)).getLocation().getY() + ((normalEnemies.get(1)).getSize() / 2)));
                        }
                    }
                    if (panels.get(0).getRectangle().contains(epsilon)) {
                        if ((normalEnemies.get(1)).getLocation().y + (2 * (normalEnemies.get(1)).getSize()) > panels.get(0).getDownY()) {
                            (normalEnemies.get(1)).setLocation(new Point((int) panels.get(0).getX(), panels.get(0).getDownY() - (2 * ( normalEnemies.get(1)).getSize())));
                        }
                    }

                } else if (upperDistance == tempDistance) {
                    if ((normalEnemies.get(1)).getLocation().y - 8 > panels.get(0).getY()) {
                        ( normalEnemies.get(1)).setLocation(new Point((normalEnemies.get(1)).getLocation().x, (normalEnemies.get(1)).getLocation().y - 1));

                    }
                    if (!isEnteredToPanel2_Omenoct && ( normalEnemies.get(1)).getLocation().y - 8 == panels.get(0).getY()) {
                        if ((panels.get(0).getRectangle().contains(epsilon) && panels.get(1).getRectangle().intersects(panels.get(0).getRectangle())) || (!panels.get(1).getRectangle().contains(epsilon) && ( normalEnemies.get(1)).getLocation().y - 8 == panels.get(0).getY())) {
                            if ((normalEnemies.get(1)).getLocation().x > epsilon.x) {
                                (normalEnemies.get(1)).setLocation(new Point(( normalEnemies.get(1)).getLocation().x - 2, ( normalEnemies.get(1)).getLocation().y));
                            } else if ((normalEnemies.get(1)).getLocation().x < epsilon.x) {
                                ( normalEnemies.get(1)).setLocation(new Point(( normalEnemies.get(1)).getLocation().x + 2, ( normalEnemies.get(1)).getLocation().y));
                            }
                        }
                    }
                    if (panels.get(0).getRectangle().contains(epsilon)) {
                        if (( normalEnemies.get(1)).getLocation().x - ((double) ( normalEnemies.get(1)).getSize() / 2) < panels.get(0).getX()) {
                            ( normalEnemies.get(1)).setLocation(new Point((int) (panels.get(0).getX() + (( normalEnemies.get(1)).getSize() / 2)), (int) ( normalEnemies.get(1)).getLocation().getY()));
                        }
                    }
                    if (panels.get(0).getRectangle().contains(epsilon)) {
                        if (( normalEnemies.get(1)).getLocation().x + (2 * ( normalEnemies.get(1)).getSize()) > panels.get(0).getRightX()) {
                            ( normalEnemies.get(1)).setLocation(new Point(panels.get(0).getRightX() - (2 * ( normalEnemies.get(1)).getSize()), (int) ( normalEnemies.get(1)).getLocation().getY()));
                        }
                    }
                    if (panels.get(0).getRectangle().intersects(panels.get(1).getRectangle()) && !panels.get(0).getRectangle().contains(epsilon) && panels.get(1).getRectangle().contains(epsilon)) {
                        if (!isEnteredToPanel2_Omenoct) {
                            ( normalEnemies.get(1)).setLocation(new Point(panels.get(0).getRightX(), (int) (panels.get(1).getY() + 5)));
                            isEnteredToPanel2_Omenoct = true;
                        }

                    }

                }

            } else if (epsilon.intersects(panels.get(1).getRectangle()) && !epsilon.intersects(panels.get(0).getRectangle())) {
                (normalEnemies.get(1)).setLocation(new Point(epsilon.x, (normalEnemies.get(1)).getLocation().y));

            }
            if (!HelpingBooleans.getInstance().isValidToMoveOmenoctInPanel0 && ( normalEnemies.get(1)).getLocation().x + (normalEnemies.get(1)).getSize() >= panels.get(1).getRightX() && panels.get(1).getRightX() + ( normalEnemies.get(1)).getSize() >= panels.get(0).getRightX()) {
                (normalEnemies.get(1)).setLocation(new Point(panels.get(1).getRightX() - 2 * ( normalEnemies.get(1)).getSize(), ( normalEnemies.get(1)).getLocation().y));
//                HelpingBooleans.getInstance().inPanel0 = true;
                if (!panels.get(1).getRectangle().contains(((Omenoct) normalEnemies.get(1)).getRectangle())) {
                    ( normalEnemies.get(1)).setLocation(new Point(epsilon.x, (int) (panels.get(0).getY() + 8)));

                    HelpingBooleans.getInstance().isValidToMoveOmenoctInPanel0 = true;
                }
            }
            if (HelpingBooleans.getInstance().isValidToMoveOmenoctInPanel0) {
                (normalEnemies.get(1)).setLocation(new Point(epsilon.x, (int) (panels.get(0).getY() + 8)));
            }

            if (panels.get(0).getRectangle().contains(((Omenoct) normalEnemies.get(1)).getRectangle())) {
                isEnteredToPanel1_Omenoct = true;
            } else if (panels.get(1).getRectangle().contains(((Omenoct) normalEnemies.get(1)).getRectangle())) {
                isEnteredToPanel2_Omenoct = true;
            } else if (!panels.get(0).getRectangle().contains(((Omenoct) normalEnemies.get(1)).getRectangle())) {
                isEnteredToPanel1_Omenoct = false;
            } else if (!panels.get(1).getRectangle().contains(((Omenoct) normalEnemies.get(1)).getRectangle())) {
                isEnteredToPanel2_Omenoct = false;
            }

        }

    }

    public void movementOfArchmire() {
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
    }

    public void funcOfBlackOrbs() {
        //todo: BlackOrbs:
        for (int i = 0; i < Orb.getInstance().size(); i++) {
            if (Orb.getInstance().get(i).getHP() <= 0) {
                if (BooleansOfCollectibles.getInstance().getIsValidOrbToCollect().get(i)) {
                    CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().add(new Collectable(Orb.getInstance().get(i).getLocation().x, Orb.getInstance().get(i).getLocation().y + 10, 10, Color.LIGHT_GRAY));
                    CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().add(new Collectable(Orb.getInstance().get(i).getLocation().x + 25, Orb.getInstance().get(i).getLocation().y - 5, 10, Color.LIGHT_GRAY));
                    CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().add(new Collectable(Orb.getInstance().get(i).getLocation().x + 40, Orb.getInstance().get(i).getLocation().y + 10, 10, Color.LIGHT_GRAY));
                    CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().add(new Collectable(Orb.getInstance().get(i).getLocation().x - 5, Orb.getInstance().get(i).getLocation().y - 5, 10, Color.LIGHT_GRAY));
                    CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().add(new Collectable(Orb.getInstance().get(i).getLocation().x - 20, Orb.getInstance().get(i).getLocation().y + 10, 10, Color.LIGHT_GRAY));
                    BooleansOfCollectibles.getInstance().getIsValidOrbToCollect().set(i, false);
                }
            }
        }
    }

    public void movementOfWyrm() {
        //todo: Wyrm
        if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(3)) {
            double distanceX = (int) (gameObjects.get(0).getPosition().getX() - Wyrm.getInstance().getLocation().x);
            double distanceY = (int) (gameObjects.get(0).getPosition().getY() - Wyrm.getInstance().getLocation().y);
            double oDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
            if (HelpingBooleans.getInstance().isOnOrbitWyrm) {
                int secX = (int) (gameObjects.get(0).getPosition().getX() + Properties.getInstance().radiusOfOrbitWyrm * Math.cos(Math.toRadians(Properties.getInstance().angleOfOrbitWyrm)));
                int secY = (int) (gameObjects.get(0).getPosition().getY() + (Properties.getInstance().constantOfOrbitalMovement) * Properties.getInstance().radiusOfOrbitWyrm * Math.sin(Math.toRadians(Properties.getInstance().angleOfOrbitWyrm)));

                Wyrm.getInstance().setLocation(new Point(secX, secY));

            }
            if (oDistance > 150) {
                Wyrm.getInstance().setLocation(new Point(Wyrm.getInstance().getLocation().x - 3, Wyrm.getInstance().getLocation().y));

            } else if (oDistance < 150) {
                Wyrm.getInstance().setValidToShoot(true);
                HelpingBooleans.getInstance().isOnOrbitWyrm = true;
            }
        }

        if (Wyrm.getInstance().isValidToShoot()) {
            wyrmShooter.start();
        }

        if (Wyrm.getInstance().getHP() <= 0) {
            BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().set(3, false);
            if (BooleansOfCollectibles.getInstance().isValidToCollectWyrm()) {
                CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().add(new Collectable(Wyrm.getInstance().getLocation().x, Wyrm.getInstance().getLocation().y + 10, 10, Color.LIGHT_GRAY));
                CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().add(new Collectable(Wyrm.getInstance().getLocation().x - 20, Wyrm.getInstance().getLocation().y + 10, 10, Color.LIGHT_GRAY));
                BooleansOfCollectibles.getInstance().setValidToCollectWyrm(false);
            }
            wyrmShooter.stop();
        }

    }

    public void funcOfBarricados() {
        Rectangle epsilon = gameObjects.get(0).getBounds();
        Rectangle edge1 = new Rectangle(Barricados.getInstance().getLocation().x - 20, Barricados.getInstance().getLocation().y, Barricados.getInstance().getSize() + 35, 1);
        Rectangle edge2 = new Rectangle(Barricados.getInstance().getLocation().x - 30, Barricados.getInstance().getLocation().y - (EPSILON_LENGTH / 2), 1, Barricados.getInstance().getSize() - (EPSILON_LENGTH / 2) + 60);
        Rectangle edge3 = new Rectangle(Barricados.getInstance().getLocation().x + Barricados.getInstance().getSize() + 25, Barricados.getInstance().getLocation().y, 1, Barricados.getInstance().getSize() + 20);
        Rectangle edge4 = new Rectangle(Barricados.getInstance().getLocation().x - 10, Barricados.getInstance().getLocation().y + Barricados.getInstance().getSize() + 25, Barricados.getInstance().getSize() + 45, 1);

        if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(4)) {
            if (Properties.getInstance().barricadosMode == 1) {

//            HelpingBooleans.getInstance().isEpsilonValidToMove = epsilon.intersects(Barricados.getInstance().getRectangle());
                if (epsilon.x > Barricados.getInstance().getLocation().x && epsilon.x + epsilon.width < Barricados.getInstance().getLocation().x + Barricados.getInstance().getSize() + 25 && epsilon.y + epsilon.height > Barricados.getInstance().getLocation().y - 2 * (Barricados.getInstance().getSize() - 25) && epsilon.y < Barricados.getInstance().getLocation().y) {
                    gameObjects.get(0).setPosition(new Position(gameObjects.get(0).getPosition().getX(), Barricados.getInstance().getLocation().y - 2 * (Barricados.getInstance().getSize() - 25)));
                } else if (epsilon.intersects(edge2)) {
                    gameObjects.get(0).setPosition(new Position(Barricados.getInstance().getLocation().x - 2 * epsilon.width - 35, Barricados.getInstance().getLocation().y));
                } else if (epsilon.intersects(edge3)) {
                    gameObjects.get(0).setPosition(new Position(gameObjects.get(0).getPosition().getX() + 20, Barricados.getInstance().getLocation().y));
                } else if (epsilon.intersects(edge4)) {
                    gameObjects.get(0).setPosition(new Position(gameObjects.get(0).getPosition().getX(), Barricados.getInstance().getLocation().y + Barricados.getInstance().getSize() + 25));
                }
            } else if (Properties.getInstance().barricadosMode == 2) {
                Barricados.getInstance().setLocation(new Point(500, 400));
                if (Barricados.getInstance().getRectangle().intersects(panels.get(0).getRectangle())) {
                    HelpingBooleans.getInstance().isValidToLargerMainPanel = false;
                }
            }
        }
    }

    public void laserOfBlackOrbs() {
        if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(5)) {
            Rectangle epsilon = gameObjects.get(0).getBounds();
            float thickness = 10.0f;
            BasicStroke stroke = new BasicStroke(thickness);
            Line2D l1 = new Line2D.Double((int) (PanelsData.getInstance().getBlackOrbPanels().get(0).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(0).getY() + 50)
                    , (int) (PanelsData.getInstance().getBlackOrbPanels().get(1).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(1).getY() + 50));
            Shape s1 = stroke.createStrokedShape(l1);
            Line2D l2 = new Line2D.Double((int) (PanelsData.getInstance().getBlackOrbPanels().get(0).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(0).getY() + 50)
                    , (int) (PanelsData.getInstance().getBlackOrbPanels().get(2).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(2).getY() + 50));
            Shape s2 = stroke.createStrokedShape(l2);

            Line2D l3 = new Line2D.Double((int) (PanelsData.getInstance().getBlackOrbPanels().get(0).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(0).getY() + 50)
                    , (int) (PanelsData.getInstance().getBlackOrbPanels().get(3).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(3).getY() + 50));
            Shape s3 = stroke.createStrokedShape(l3);

            Line2D l4 = new Line2D.Double((int) (PanelsData.getInstance().getBlackOrbPanels().get(0).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(0).getY() + 50)
                    , (int) (PanelsData.getInstance().getBlackOrbPanels().get(4).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(4).getY() + 50));
            Shape s4 = stroke.createStrokedShape(l4);

            Line2D l5 = new Line2D.Double((int) (PanelsData.getInstance().getBlackOrbPanels().get(1).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(1).getY() + 50)
                    , (int) (PanelsData.getInstance().getBlackOrbPanels().get(2).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(2).getY() + 50));
            Shape s5 = stroke.createStrokedShape(l5);

            Line2D l6 = new Line2D.Double((int) (PanelsData.getInstance().getBlackOrbPanels().get(1).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(1).getY() + 50)
                    , (int) (PanelsData.getInstance().getBlackOrbPanels().get(3).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(3).getY() + 50));
            Shape s6 = stroke.createStrokedShape(l6);

            Line2D l7 = new Line2D.Double((int) (PanelsData.getInstance().getBlackOrbPanels().get(1).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(1).getY() + 50)
                    , (int) (PanelsData.getInstance().getBlackOrbPanels().get(4).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(4).getY() + 50));
            Shape s7 = stroke.createStrokedShape(l7);

            Line2D l8 = new Line2D.Double((int) (PanelsData.getInstance().getBlackOrbPanels().get(2).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(2).getY() + 50)
                    , (int) (PanelsData.getInstance().getBlackOrbPanels().get(3).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(3).getY() + 50));
            Shape s8 = stroke.createStrokedShape(l8);

            Line2D l9 = new Line2D.Double((int) (PanelsData.getInstance().getBlackOrbPanels().get(2).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(2).getY() + 50)
                    , (int) (PanelsData.getInstance().getBlackOrbPanels().get(4).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(4).getY() + 50));
            Shape s9 = stroke.createStrokedShape(l9);

            Line2D l10 = new Line2D.Double((int) (PanelsData.getInstance().getBlackOrbPanels().get(3).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(3).getY() + 50)
                    , (int) (PanelsData.getInstance().getBlackOrbPanels().get(4).getX() + 50), (int) (PanelsData.getInstance().getBlackOrbPanels().get(4).getY() + 50));
            Shape s10 = stroke.createStrokedShape(l10);

            if (shapeIntersects(epsilon, s1) || shapeIntersects(epsilon, s2) ||
                    shapeIntersects(epsilon, s3) || shapeIntersects(epsilon, s4) ||
                    shapeIntersects(epsilon, s5) || shapeIntersects(epsilon, s6) ||
                    shapeIntersects(epsilon, s7) || shapeIntersects(epsilon, s8) ||
                    shapeIntersects(epsilon, s9) || shapeIntersects(epsilon, s10)) {
                attackOfOrbsOnEpsilon.start();

            } else attackOfOrbsOnEpsilon.stop();
        }
    }

    public void startGameLoop() {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        executor.submit(this::isValidToShowYE);
        executor.submit(this::isValidToShowGE);
        executor.submit(this::movementOfP1Enemies);
        executor.submit(this::movementOfOmenoct);
        executor.submit(this::movementOfArchmire);
        executor.submit(this::funcOfBlackOrbs);
        executor.submit(this::movementOfWyrm);
        executor.submit(this::funcOfBarricados);
        executor.submit(this::funcOfBlackOrbs);
        executor.submit(this::laserOfBlackOrbs);


        executor.shutdown();
    }

    public void normalEnemiesLauncher() {

        normalEnemies = new ArrayList<>();
        normalEnemies.add(0,new Necropick());
        normalEnemies.add(1,new Omenoct());
        normalEnemies.add(2,Wyrm.getInstance());
        normalEnemies.add(3,Archmire.getInstance());

//        normalEnemies.addAll(Orb.getInstance());
    }

}
