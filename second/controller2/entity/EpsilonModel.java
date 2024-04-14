package second.controller2.entity;

import second.controller2.controlComponent.Controller2;
import second.movement.SizeOfEpsilon;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EpsilonModel extends MovingEpsilon {

    public SizeOfEpsilon sizeOfEpsilon;
    public double radius;


    public EpsilonModel(Controller2 controller, double posX, double posY){
        super(controller,posX,posY);
        sizeOfEpsilon = new SizeOfEpsilon(50,50);
        radius = 25;

    }

    @Override
    public void update() {
        super.update();
    }

    public double getRadius() {
        return radius;
    }


    @Override
    public Image getSprite() {
        BufferedImage image = new BufferedImage((int) sizeOfEpsilon.getWidth(), (int) sizeOfEpsilon.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        g.setColor(new Color(0xEF0A412B, true));
        g.fillOval(0,0, (int) sizeOfEpsilon.getWidth(), (int) sizeOfEpsilon.getHeight());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString("ε",(int) sizeOfEpsilon.getWidth()-30,(int) sizeOfEpsilon.getHeight()-21);
        g.dispose();
        return image;
    }
}
