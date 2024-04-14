package second.display;

import second.controller2.gameComponent.Game2;

import java.awt.*;

public class Renderer {
    public void render(Game2 game2, Graphics g){
        game2.getGameObjects().forEach(gameObject -> g.drawImage(gameObject.getSprite(), gameObject.position.intX(), gameObject.position.intY(),null
        ));
    }
}
