package third.all.controller.componentController;

import java.awt.event.KeyEvent;

public class GreenEnemyController implements Controller3 {

    private Input input;

    public GreenEnemyController(Input input) {
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
