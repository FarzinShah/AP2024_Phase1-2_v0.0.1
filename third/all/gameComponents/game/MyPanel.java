package third.all.gameComponents.game;

import third.all.controller.componentController.Input;
import third.all.controller.componentController.TWM_Item_Model;
import third.all.controller.entity.EpsilonModel;
import third.all.data.booleans.BooleansOf_IsValidToShow;
import third.all.data.CollectablesOfEnemies;
import third.all.data.PanelsData;
import third.all.data.Properties;
import third.all.gameComponents.preGameComponent.GameOverFrame;
import third.all.gameComponents.preGameComponent.Timer1;
import third.all.model.*;
import third.all.model.boss.Head;
import third.all.model.boss.LeftHand;
import third.all.model.boss.RightHand;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import static third.all.controller.Constants.*;

import static third.all.data.Properties.*;
import static third.all.gameComponents.game.FunctionalMethods.showOptionPane;
import static third.all.gameComponents.game.GameFrame2.*;
import static third.all.gameComponents.preGameComponent.Settings.informationsOfSettings;
import static third.all.gameComponents.preGameComponent.Timer1.elapsedTime;
import static third.all.data.booleans.Booleans.*;

public class MyPanel extends JPanel implements Runnable{
    public static boolean timer1Starter = false;
    private final Color backGround = Color.BLACK;
    private final Color backGroundTest = Color.GRAY;

    public static Timer1 timerOfGame;
    public static boolean showOfPointerItem;
    int record = 0;

    private TWM_Item_Model twm_item_model1;
//    public static boolean[] showOfCollectibles;

    private boolean running;
    public static ArrayList<Panel> panels;
    private Panel redZone;
    private Rectangle redZoneRectangle;








    public MyPanel(Input input){
        panels = new ArrayList<>();


        redZone = PanelsData.getInstance().getRedZone();
        redZoneRectangle = new Rectangle(redZone.getX(), redZone.getY(), (int) redZone.getWidth(), (int) redZone.getHeight());
        panels.add(0,new Panel((int) STARTING_POINT.x , (int) STARTING_POINT.y, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, (int)Properties.getInstance(). GLASS_FRAME_DIMENSION_HEIGHT));
        panels.add(1,new Panel((int) Properties.getInstance().THIRD_FRAME_LOCATION_X, (int) Properties.getInstance().THIRD_FRAME_LOCATION_Y, Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH/2,Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH/2));
        Thread gameThread = new Thread(this);
        gameThread.start();

        addKeyListener(input);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);


    }
    @Override
    protected void paintComponent(Graphics g) {

        if (timer1Starter) {
            timerOfGame.start();
            timer1Starter = false;
        }
        //background:
        g.setColor(backGround);
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().get(1)) {
            synchronized (panels.get(0)) {
                panels.set(0, new Panel(STARTING_POINT.x, STARTING_POINT.y, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT));
                panels.get(0).draw(g);
            }
        }
        //todo: panels
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().get(2)) {
            synchronized (panels.get(1)) {
                panels.set(1, new Panel((int) Properties.getInstance().THIRD_FRAME_LOCATION_X, (int) Properties.getInstance().THIRD_FRAME_LOCATION_Y, Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH / 2, Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH / 2));
                panels.get(1).draw(g);
            }
        }
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(3)){
            synchronized (PanelsData.getInstance().getWyrm()) {
                PanelsData.getInstance().setWyrm(new Panel(Wyrm.getInstance().getLocation().x,Wyrm.getInstance().getLocation().y,80,80));
                PanelsData.getInstance().getWyrm().drawWym(g);
            }
        }
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(4)){
            synchronized (PanelsData.getInstance().getBarricados()) {
                PanelsData.getInstance().setBarricados(new Panel(Barricados.getInstance().getLocation().x,Barricados.getInstance().getLocation().y,80,80));
                PanelsData.getInstance().getBarricados().drawWym(g);
            }
        }
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().get(0)) {
            synchronized (redZone) {
                redZone.drawRedZone(g);
            }
        }
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowBlackOrbPanels().get(0)) {
            synchronized (PanelsData.getInstance().getBlackOrbPanels().get(0)) {
                PanelsData.getInstance().getBlackOrbPanels().get(0).draw(g);
            }
        }
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowBlackOrbPanels().get(1)) {
            synchronized (PanelsData.getInstance().getBlackOrbPanels().get(1)) {
                PanelsData.getInstance().getBlackOrbPanels().get(1).draw(g);
            }
        }
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowBlackOrbPanels().get(2)) {
            synchronized (PanelsData.getInstance().getBlackOrbPanels().get(2)) {
                PanelsData.getInstance().getBlackOrbPanels().get(2).draw(g);
            }
        }
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowBlackOrbPanels().get(3)) {
            synchronized (PanelsData.getInstance().getBlackOrbPanels().get(3)) {
                PanelsData.getInstance().getBlackOrbPanels().get(3).draw(g);
            }
        }
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowBlackOrbPanels().get(4)) {
            synchronized (PanelsData.getInstance().getBlackOrbPanels().get(4)) {
                PanelsData.getInstance().getBlackOrbPanels().get(4).draw(g);
            }
        }
        if(BooleansOf_IsValidToShow.getInstance().isValidToShowBossPanel()){
            synchronized (PanelsData.getInstance().getBossPanel()) {
                PanelsData.getInstance().getBossPanel().drawBossPanel(g);
            }

        }

        Rectangle thirdMinusMain_RightSide = new Rectangle((int) Properties.getInstance().THIRD_FRAME_LOCATION_X, (int) Properties.getInstance().THIRD_FRAME_LOCATION_Y, (int) ((Properties.getInstance().THIRD_FRAME_LOCATION_X+Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH/2)-STARTING_POINT.x+Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH), (int) (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT/2));
//        g.fillRect((int) THIRD_FRAME_LOCATION_X, (int) THIRD_FRAME_LOCATION_Y, (int) ((THIRD_FRAME_LOCATION_X+GLASS_FRAME_DIMENSION_WIDTH/2)-STARTING_POINT.x+GLASS_FRAME_DIMENSION_WIDTH), (int) (GLASS_FRAME_DIMENSION_HEIGHT/2));
        //todo: با چندتا if زدن درمیاد انشاالله.
//        g.fillRect(900,50,300,300);//todo
        // drawing map:
        g.setColor(BLUE_BACKGROUND);
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().get(0)){
            g.fillRect(1000,10,220,50);
            g.drawImage(SAVING_OPTION, 1000,10,220,50,this);
        }
        if(CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct().size()!=0 || CollectablesOfEnemies.getInstance().getCollectablesOfYE().size()!=0 || CollectablesOfEnemies.getInstance().getCollectablesOfOrbs().size()!=0){
            g.fillRect(20,10,220,50);
            g.drawImage(COLLECTIBLE_OPTION, 20,10,220,50,this);
            g.setFont(new Font("serif", Font.BOLD, 25));
            ((Graphics2D) g).drawString(String.valueOf(CollectablesOfEnemies.getInstance().getCollectablesOfYE().size()),200, 40);
        }



        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(1)) {
            g.drawImage(NECROPICK, Necropick.getInstance().getLocation().x, Necropick.getInstance().getLocation().y, Necropick.getInstance().getSize(), Necropick.getInstance().getSize(), this);
        }

        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)) {
            g.drawImage(OMENOCT, Omenoct.getInstance().getLocation().x, Omenoct.getInstance().getLocation().y, Omenoct.getInstance().getSize() * 2, Omenoct.getInstance().getSize() * 2, this);
        }
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(3)) {
            Wyrm.draw(g,this);
        }
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(4)) {
            Barricados.draw(g,this);
        }

        if(BooleansOf_IsValidToShow.getInstance().isValidToShowBossPanel()) {
            RightHand.getInstance().draw(g,this);
            LeftHand.getInstance().draw(g,this);
            Head.getInstance().draw(g,this);
        }

//        g.drawImage(BEAUTIFUL_HEART,600,200,50,50,this);

        g.setColor(new Color(0,0,0, 60));
        g.fillRect(250,250,500,500);
//        g.setColor(Color.WHITE);
        g.setColor(new Color(0x94676464, true));
        float[] dashingPattern1 = {5, 30};
        Stroke stroke1 = new BasicStroke(2f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 1.0f, dashingPattern1, 2.0f);

        ((Graphics2D)g).setStroke(stroke1);

        g.drawLine(100,100,400,400);
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowBlackOrbPanels().get(5)){
            Orb.draw(g,this);
        }
//        ((Graphics2D) g).fill(createOctagonShape(OMENOCT_POSITION.x+25,OMENOCT_POSITION.y+25,OMENOCT_SIZE+2));



        // item pointer of mouse:
        if (showOfPointerItem) {
            System.out.println("???");
            g.setColor(Color.red);
            twm_item_model1.draw((Graphics2D) g);
        }

        for (int i = 0; i < greenEnemies1.size(); i++) {
            if(showOfCollectiblesG.get(i)){
                g.setColor(Color.GREEN);
                collectibleItemsG.get(i).draw((Graphics2D) g);


            }
        }
//        for (int i = 0; i < yellowEnemies1.size(); i++) {
//            if(showOfCollectiblesY.get(i)){
//                g.setColor(Color.ORANGE);
//                collectibleItemsY.get(i).draw((Graphics2D) g);
//            }
//        }


        // borders:
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().get(1)) {
            g.setColor(Color.ORANGE);
            synchronized (PanelsData.getInstance().getPanels().get(1)) {
                g.fillRect(STARTING_POINT.x - 1, STARTING_POINT.y - 1, 3, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
                g.fillRect(STARTING_POINT.x - 1, STARTING_POINT.y - 1, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, 3);
                g.fillRect(STARTING_POINT.x - 1 + (int) (Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH - 3), STARTING_POINT.y - 1, 3, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
                g.fillRect(STARTING_POINT.x - 1, STARTING_POINT.y - 1 + (int) (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT - 3), (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, 3);
            }
        }
        //Showing the scores:
        g.setColor(new Color(0x0B0B7C));
        g.fillRect(300-1+ (600 - 360),15,430,40);
        g.setColor(Color.white);

        g.setFont(new Font("serif", Font.BOLD, 25));
        ((Graphics2D) g).drawString("HP: " +Properties.getInstance(). HP,300-1+ (600 - 150), 40);
        ((Graphics2D) g).drawString("XP: " + Properties.getInstance().XP, 300-1+ (600 - 250), 40);
        ((Graphics2D) g).drawString("Wave: " + Properties.getInstance().WAVE, 300-1+ (600-25), 40);
        ((Graphics2D) g).drawString("Head: " + Head.getInstance().getHP(), 50, 500);

        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        ((Graphics2D) g).drawString(timerOfGame.minutes_string + ":" + timerOfGame.seconds_string, 300-1+ (600 - 350), 40);
        if (record != 0) {
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 25));
            ((Graphics2D) g).drawString("Highest: " + record, 320, 30);
        }
        // shots:
        synchronized (bullets) {
            for (Bullet bullet : bullets) {
                bullet.draw(g);
            }
        }
        synchronized (bulletsOfOmenoct) {
            for (Bullet bullet : bulletsOfOmenoct) {
                bullet.draw(g);
            }
        }
        synchronized (bulletsOfWyrm) {
            for (Bullet bullet : bulletsOfWyrm) {
                bullet.draw(g);
            }
        }
        synchronized (bulletsOfLeftHand) {
            for (Bullet bullet : bulletsOfLeftHand) {
                bullet.draw(g);
            }
        }
        synchronized (bulletsOfRightHand) {
            for (Bullet bullet : bulletsOfRightHand) {
                bullet.draw(g);
            }
        }
        synchronized (bulletsOfNecropick) {
            for (Bullet bullet : bulletsOfNecropick) {
                bullet.draw2(g);
            }
        }
        synchronized (CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct()) {
            for (Collectable collectable : CollectablesOfEnemies.getInstance().getCollectablesOfOmenoct()) {
                collectable.draw(g);
            }
        }
        synchronized (CollectablesOfEnemies.getInstance().getCollectablesOfYE()) {
            for (Collectable collectable : CollectablesOfEnemies.getInstance().getCollectablesOfYE()) {
                collectable.draw(g);
            }
        }
        synchronized (CollectablesOfEnemies.getInstance().getCollectablesOfOrbs()) {
            for (Collectable collectable : CollectablesOfEnemies.getInstance().getCollectablesOfOrbs()) {
                collectable.draw(g);
            }
        }
        synchronized (CollectablesOfEnemies.getInstance().getCollectablesOfWyrm()) {
            for (Collectable collectable : CollectablesOfEnemies.getInstance().getCollectablesOfWyrm()) {
                collectable.draw(g);
            }
        }
        g.setColor(new Color(0x44981062, true));
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(0)) {
            synchronized (Archmire.getInstance().getTrails()){
            for (Point trail : Archmire.getInstance().getTrails()) {
                g.fillOval(trail.x, trail.y, Archmire.getInstance().getSize(), Archmire.getInstance().getSize());
            }
        }
            g.drawImage(ARCHMIRE, Archmire.getInstance().getLocation().x, Archmire.getInstance().getLocation().y, Archmire.getInstance().getSize(), Archmire.getInstance().getSize(), this);
        }
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(6)) Cerberus.draw(g);



        if (lineShower && informationsOfSettings.isTherePointer()) {
            g.setColor(new Color(0x7A4BA0));
            g.drawLine((int) (gameObjects.get(0).position.getX() + 25), (int) (gameObjects.get(0).position.getY() + 25), startX, startY);
        }
        getGameObjects().forEach(gameObject -> g.drawImage(gameObject.getSprite(), gameObject.position.intX(), gameObject.position.intY(), null));
        getYellowObjects().forEach(gameObject -> g.drawImage(gameObject.getSprite(), gameObject.position.intX(), gameObject.position.intY(), null));
        getGreenObjects().forEach(gameObject -> g.drawImage(gameObject.getSprite(), gameObject.position.intX(), gameObject.position.intY(), null));

        // The Epsilon:

        // create Ball:

        if (elapsedTime == 0) {
            timerOfGame.stop();
            Properties.getInstance().play = false;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            ((Graphics2D) g).drawString("Start Again! Scores: " + score, 190, 300);
            if (isValid8) {
                new GameOverFrame();
                isValid8 = false;

            }
        }



        checkCollision();

        time.setInitialDelay(10);
        time.start();
        g.dispose();
    }

//    public void drawPanel(){
//        for (Panel panel : panels) {
//            panel.draw(g);
//        }
//    }


    public TWM_Item_Model getTwm_item_model1() {
        return twm_item_model1;
    }

    public void setTwm_item_model1(TWM_Item_Model twm_item_model1) {
        this.twm_item_model1 = twm_item_model1;
    }

    private void checkCollision() {
        Rectangle blueArea = redZoneRectangle;
        Rectangle epsilon = new Rectangle((int) ((EpsilonModel)gameObjects.get(0)).getPosition().getX(), (int) (int) ((EpsilonModel)gameObjects.get(0)).getPosition().getY(), EPSILON_WIDTH, EPSILON_LENGTH);
        Shape octagon = createOctagonShape(Omenoct.getInstance().getLocation().x+25,Omenoct.getInstance().getLocation().y+25,OMENOCT_SIZE+2);
        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(2)) {
            if (shapeIntersects(epsilon, octagon)) {
                Properties.getInstance().XP += 5;
            }
        }

        if(!BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().get(0)){
            blueArea = new Rectangle(10000,4000,11,11);
        }
        if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().get(0)) {
            if (blueArea.contains(epsilon.getBounds2D())) {
//            play =false;
                redZone = new Panel(10000, 10000, 10, 10);
                redZoneRectangle = new Rectangle(10000, 4000, 11, 11);
                Thread redCharacterThread = new Thread(() -> {
                    try {
                        Thread.sleep(0); // Sleep for 30 seconds
                        showOptionPane(); // Show the panel with buttons if collision happens with blue area
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                redCharacterThread.start();
            }
        }
    }

    private boolean shapeIntersects(Shape s1, Shape s2) {
        Area area1 = new Area(s1);
        Area area2 = new Area(s2);
        area1.intersect(area2);
        return !area1.isEmpty();
    }


    private Shape createOctagonShape(int x, int y, int size) {
        Path2D.Double octagon = new Path2D.Double();
        double angleStep = Math.PI / 4;
        double startAngle = Math.PI / 8;

        for (int i = 0; i < 8; i++) {
            double angle = startAngle + i * angleStep;
            double dx = Math.cos(angle) * size;
            double dy = Math.sin(angle) * size;
            if (i == 0) {
                octagon.moveTo(x + dx, y + dy);
            } else {
                octagon.lineTo(x + dx, y + dy);
            }
        }
        octagon.closePath();
        return octagon;
    }

    public void drawArchmireFootPrint(){

    }



    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(10); // Sleep for 10 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
