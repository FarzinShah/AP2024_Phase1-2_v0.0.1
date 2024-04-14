package second.display;

import second.controller2.gameComponent.Game2;
import second.controller2.controlComponent.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

import static second.controller2.gameComponent.utils.Constant.CENTRAL_THREAD;
import static second.controller2.gameComponent.utils.Constant.PANEL_SIZE;

public class Display extends JFrame {
    private Canvas canvas;
//    private JPanel panel;
    private Renderer renderer;

    public Display(Dimension panelSize, Input input){
        canvas = new Canvas();
//        panel = new JPanel();
        this.renderer = new Renderer();
//        panel.setPreferredSize(PANEL_SIZE);
        canvas.setPreferredSize(null);
        canvas.setFocusable(false);
//        panel.setFocusable(false);

        add(canvas);
//        add(panel);

        //pack(); // توی پلن اول برای کوچیک شدن این باید کامنت بشه.
        //setLocationRelativeTo(null); // جای اولیه پنل - توی پلن اول برای کوچیک شدن این باید کامنت بشه.
        setVisible(true);
        setResizable(true);
        addKeyListener(input);
        canvas.createBufferStrategy(3);


    }

    public void render(Game2 game2){
        //پلن اول برای کوچیک شدن صفحه که انگار درست کار نمیکنه.
//        CENTRAL_THREAD.start();
//        panel.setPreferredSize(PANEL_SIZE);
        //------------------------------------------------

        canvas.setPreferredSize(PANEL_SIZE);
//        add(canvas);
        pack();
        setLocationRelativeTo(null); // جای اولیه پنل
        setVisible(true);
        //------------------------------------------------

//        setResizable(false);


//        CENTRAL_THREAD.start();

        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
//        Rectangle circle = game2.getCircle();
//        g.setColor(Color.green);
//        g.fillOval((int) circle.getX(), (int) circle.getY(), (int) circle.getWidth(), (int) circle.getHeight());
        renderer.render(game2,g);
        g.dispose();
        bufferStrategy.show();


    }

}
