package second.controller2.controlComponent;

import java.awt.event.KeyEvent;

public class YellowEnemyController implements Controller2 {

    private Input input;

    public YellowEnemyController(Input input) {
        this.input = input;
    }

    @Override
    public boolean idRequestingUp() {
        return input.isPressed(KeyEvent.VK_W);
    }

    @Override
    public boolean idRequestingDown() {
        return input.isPressed(KeyEvent.VK_S);
    }

    @Override
    public boolean idRequestingLeft() {
        return input.isPressed(KeyEvent.VK_A);
    }

    @Override
    public boolean idRequestingRight() {
        return input.isPressed(KeyEvent.VK_D);
    }

    @Override
    public boolean idRequestingEsc() {
        return input.isPressed(KeyEvent.VK_ESCAPE);
    }
}
