package third.all.controller.gameController;

import third.all.data.Properties;
import third.all.data.booleans.HelpingBooleans;
import third.all.gameComponents.game.ClipHandler;
import third.all.model.Bullet;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static third.all.data.Properties.bullets;
import static third.all.gameComponents.game.GameFrame2.*;
import static third.all.gameComponents.game.MyPanel.timer1Starter;

public class GameMouseHandler implements MouseListener, MouseMotionListener {
    @Override
    public void mouseClicked(MouseEvent e) {

        int targetX = e.getX();
        int targetY = e.getY();
        if(!HelpingBooleans.getInstance().doQuakeMouseAttack) {
            bullets.add(new Bullet((int) (gameObjects.get(0).getPosition().getX() + 10), (int) (gameObjects.get(0).getPosition().getY() + 10), targetX, targetY, Color.RED));
        }
        else {
            bullets.add(new Bullet((int) (gameObjects.get(0).getPosition().getX() + 10), (int) (gameObjects.get(0).getPosition().getY() + 10), -targetX, targetY, Color.RED));
        }

    }


    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
        System.out.println(startX);
        ClipHandler.getInstance().playShotMusic();
        // todo: douf douf dodododdodooofff  :)

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
}
