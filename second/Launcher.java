package second;

import second.controller2.gameComponent.Game2;
import second.controller2.gameComponent.GameLoop2;

import java.awt.*;

import static second.controller2.gameComponent.utils.Constant.CENTRAL_THREAD;
import static second.controller2.gameComponent.utils.Constant.PANEL_SIZE;

public class Launcher {
    public static void main(String[] args) {
        CENTRAL_THREAD = new Thread(new GameLoop2(new Game2(new Dimension(PANEL_SIZE))));
        CENTRAL_THREAD.start();
//        Display display = new Display(PANEL_SIZE);
    }
}
