package third.all.controller.entity;



import third.all.controller.componentController.Controller3;
import third.all.controller.movement.Position;
import third.all.controller.movement.SizeOfEpsilon;

import java.awt.*;
import java.awt.image.BufferedImage;


import static third.all.controller.Constants.EPSILON_LENGTH;
import static third.all.controller.Constants.EPSILON_WIDTH;

public class EpsilonModel extends MovingEpsilon {

    public SizeOfEpsilon sizeOfEpsilon;
    public double radius;
    public Position positionOfCenter;


    public EpsilonModel(Controller3 controller, double posX, double posY){
        super(controller,posX,posY);
        sizeOfEpsilon = new SizeOfEpsilon(EPSILON_WIDTH,EPSILON_LENGTH);
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
        g.setFont(new Font("Arial",Font.BOLD, (int) (2*sizeOfEpsilon.getWidth()/5)));
        g.drawString("ε", (int) ((int) sizeOfEpsilon.getWidth()-3*sizeOfEpsilon.getWidth()/5), (int) ((int) sizeOfEpsilon.getHeight()-(42*sizeOfEpsilon.getHeight()/100)));
        g.dispose();
        return image;
    }

}
