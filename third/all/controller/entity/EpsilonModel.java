package third.all.controller.entity;


import third.all.controller.componentController.Controller3;
import third.all.controller.movement.SizeOfEpsilon;
import third.all.data.Properties;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static third.all.controller.Constants.*;

public class EpsilonModel extends MovingEpsilon {

    public SizeOfEpsilon sizeOfEpsilon;
    public double radius;
    BufferedImage hexagonalEpsilon;
    BufferedImage pentagonalEpsilon;
    BufferedImage triangleEpsilon;


    public EpsilonModel(Controller3 controller, double posX, double posY) {
        super(controller, posX, posY);
        sizeOfEpsilon = new SizeOfEpsilon(EPSILON_WIDTH, EPSILON_LENGTH);
        radius = 25;
        try {
            pentagonalEpsilon = ImageIO.read(new File(PentagonalEpsilon_PATH));
            hexagonalEpsilon = ImageIO.read(new File(HexagonalEpsilon_PATH));
            triangleEpsilon = ImageIO.read(new File(TriangleEpsilon_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        BufferedImage image = new BufferedImage((int) sizeOfEpsilon.getWidth(), (int) sizeOfEpsilon.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        g.setColor(new Color(0xEF0A412B, true));
        if (Properties.getInstance().edgesOfEpsilon < 3) {
            g.fillOval(0, 0, (int) sizeOfEpsilon.getWidth(), (int) sizeOfEpsilon.getHeight());
            if (Properties.getInstance().edgesOfEpsilon == 1) {
                g.setColor(new Color(0xEA1FEA93, true));
                g.fillOval(0, 10, 6, 6);
            }
            if (Properties.getInstance().edgesOfEpsilon == 2) {
                g.setColor(new Color(0xEA1FEA93, true));
                g.fillOval(0, 10, 6, 6);
                g.fillOval(42, 10, 6, 6);
            }

        }
        if (Properties.getInstance().edgesOfEpsilon == 3) {
            g.drawImage(triangleEpsilon, 0, 0, (int) sizeOfEpsilon.getWidth(), (int) sizeOfEpsilon.getHeight(), (image1, i, i1, i2, i3, i4) -> true);
        }
        if (Properties.getInstance().edgesOfEpsilon == 4) {
            g.fillRect(0, 0, (int) sizeOfEpsilon.getWidth(), (int) sizeOfEpsilon.getHeight());
        }
        if (Properties.getInstance().edgesOfEpsilon == 5) {
            g.drawImage(pentagonalEpsilon, 0, 0, (int) sizeOfEpsilon.getWidth(), (int) sizeOfEpsilon.getHeight(), (image12, i, i1, i2, i3, i4) -> true);
        }
        if (Properties.getInstance().edgesOfEpsilon == 6) {
            g.drawImage(hexagonalEpsilon, 0, 0, (int) sizeOfEpsilon.getWidth(), (int) sizeOfEpsilon.getHeight(), (image13, i, i1, i2, i3, i4) -> true);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, (int) (2 * sizeOfEpsilon.getWidth() / 5)));
        g.drawString("ε", (int) ((int) sizeOfEpsilon.getWidth() - 3 * sizeOfEpsilon.getWidth() / 5), (int) ((int) sizeOfEpsilon.getHeight() - (42 * sizeOfEpsilon.getHeight() / 100)));
        g.dispose();
        return image;
    }

}
