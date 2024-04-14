package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

import static controller1.Constant.MAX_PANEL_SIZE;
import static controller1.GameLoop.epsilonModelX;
import static controller1.GameLoop.epsilonModelY;

public class MotionPanel extends JPanel implements KeyListener {
    private static MotionPanel INSTANCE;
    public MotionPanel(){
        setBorder(BorderFactory.createLineBorder(Color.black,5));
        setSize(MAX_PANEL_SIZE);
        setBackground(new Color(0xED29292D, true));
        setLocationToCenter(GameFrame.getINSTANCE());
        GameFrame.getINSTANCE().add(this);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (EpsilonView ballView: EpsilonView.epsilonViews){
            g.setColor(new Color(0x127C12));
            Point2D location=ballView.getCurrentLocation();
            double radius=ballView.getCurrentRadius();
            g.fillOval((int) (location.getX()-radius), (int) (location.getY()-radius), (int) (2 *radius), (int) (2*radius));
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",Font.BOLD,(int) (radius)));
            ((Graphics2D) g).drawString("ε",(int) (location.getX()-radius/2),(int) (location.getY()-radius/2));
        }
    }
    public void setLocationToCenter(GameFrame glassFrame){
        setLocation(glassFrame.getWidth()/2-getWidth()/2,glassFrame.getHeight()/2-getHeight()/2);
    }
    public static MotionPanel getINSTANCE() {
        if (INSTANCE==null) INSTANCE=new MotionPanel();
        return INSTANCE;
    }



    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            System.out.println(epsilonModelX + " " + epsilonModelY);

            epsilonModelX+=5.0;
        }

    }


    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

}
