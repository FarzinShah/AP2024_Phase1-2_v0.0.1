package third.all.controller;

import javax.swing.*;
import static third.all.controller.Constants.FRAME_UPDATE_TIME;
import static third.all.controller.Constants.MODEL_UPDATE_TIME;

public class Update {
    public Update() {
        new Timer((int) FRAME_UPDATE_TIME, e -> updateView()) {{
            setCoalesce(true);
        }}.start();
        new Timer((int) MODEL_UPDATE_TIME, e -> updateModel()) {{
            setCoalesce(true);
        }}.start();
    }

    public void updateView() {

//        MediumFrame.getINSTANCE().repaint();
    }

    public void updateModel() {


    }
}