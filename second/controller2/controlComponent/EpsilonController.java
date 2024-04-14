package second.controller2.controlComponent;

import java.awt.event.KeyEvent;

public class EpsilonController implements Controller2 {

    private Input input;

    public EpsilonController(Input input) {
        this.input = input;
    }

    @Override
    public boolean idRequestingUp() {
        return input.isPressed(KeyEvent.VK_UP);
    }

    @Override
    public boolean idRequestingDown() {
        return input.isPressed(KeyEvent.VK_DOWN);
    }

    @Override
    public boolean idRequestingLeft() {
        return input.isPressed(KeyEvent.VK_LEFT);
    }

    @Override
    public boolean idRequestingRight() {
        return input.isPressed(KeyEvent.VK_RIGHT);
    }

    @Override
    public boolean idRequestingEsc() {
        return input.isPressed(KeyEvent.VK_ESCAPE);
    }
}
