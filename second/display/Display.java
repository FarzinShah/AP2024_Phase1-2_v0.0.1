package second.display;

import second.controller2.gameComponent.Game2;
import second.controller2.controlComponent.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

import static second.controller2.gameComponent.GameLoop2.recentExpendedTime;
import static second.controller2.gameComponent.utils.Constant.CENTRAL_THREAD;
import static second.controller2.gameComponent.utils.Constant.PANEL_SIZE;

public class Display extends JFrame {
    public static Canvas canvas;
//    private JPanel panel;
    private Renderer renderer;

    private Timer time;


    public Display(Dimension panelSize, Input input){
        canvas = new Canvas();
//        panel = new JPanel();
        this.renderer = new Renderer();
//        panel.setPreferredSize(PANEL_SIZE);
        canvas.setPreferredSize(PANEL_SIZE);

//        canvas.setPreferredSize(null);
        canvas.setFocusable(false);
//        panel.setFocusable(false);

        add(canvas);
//        add(panel);

        pack(); // توی پلن اول برای کوچیک شدن این باید کامنت بشه.
        setLocationRelativeTo(null); // جای اولیه پنل - توی پلن اول برای کوچیک شدن این باید کامنت بشه.
        setVisible(true);
        setResizable(true);
        addKeyListener(input);
        canvas.createBufferStrategy(3);

    }

    public void render(Game2 game2){
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();

        //پلن اول برای کوچیک شدن صفحه که انگار درست کار نمیکنه.
//        CENTRAL_THREAD.start();
//        panel.setPreferredSize(PANEL_SIZE);
        //------------------------------------------------
        // timer:
//        time = new Timer(100, e->{
            if(true){

//                time= new Timer(0, e->{ //---
//                    canvas.setSize(PANEL_SIZE); //---
//                    add(canvas); //---

//                    pack();

//                    BufferStrategy bufferStrategy = canvas.getBufferStrategy();
                    Graphics g = bufferStrategy.getDrawGraphics();

                    g.setColor(Color.BLACK);
                    g.fillRect(0,0,canvas.getWidth(), canvas.getHeight());

                    renderer.render(game2,g);
                    g.dispose();
                    bufferStrategy.show();
//                }); //---
//                time.setRepeats(true);
//                time.setInitialDelay(10); //---
//                time.restart(); //---
            }
//            else if(recentExpendedTime>=7.0001){
//                time.stop();
//
//            }

//            pack(); //---
//            setLocationRelativeTo(null); // جای اولیه پنل //----
//            setVisible(true);
//        });
//        time.setRepeats(true);
//        time.start();


        //------------------------------------------------

//        setResizable(false);


//        CENTRAL_THREAD.start();
//        if (recentExpendedTime<3.0 || recentExpendedTime>=7.00001){
//            Graphics g = bufferStrategy.getDrawGraphics();
//
//            g.setColor(Color.BLACK);
//            g.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
//
//            renderer.render(game2,g);
//            g.dispose();
//
//        }
//        bufferStrategy.show();

        // تا قبل از ثانیه 3 درست کار میکنه.



    }

}
