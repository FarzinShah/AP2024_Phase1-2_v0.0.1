package third.all.model.boss;

import java.awt.*;
import java.awt.image.ImageObserver;

public interface Boss {
    Point getLocation();
    int getSize();
    Rectangle getRectangle();
    void draw(Graphics g, ImageObserver i);
}
