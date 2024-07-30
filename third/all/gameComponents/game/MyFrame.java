package third.all.gameComponents.game;

import third.all.data.Properties;

import javax.swing.*;

import static third.all.data.booleans.Booleans.isRightPanel2;

public class MyFrame extends JFrame {
//    public MyFrame(MyPanel panel){
//
//
//    }

    public void update() {
        if (!isRightPanel2){
            Properties.getInstance().SECOND_FRAME_LOCATION_X -=0.5;
        }else Properties.getInstance().SECOND_FRAME_LOCATION_X +=0.5;

        if(Properties.getInstance().SECOND_FRAME_LOCATION_X <300){
            isRightPanel2 = true;
        }else if(Properties.getInstance().SECOND_FRAME_LOCATION_X ==1100){
            isRightPanel2 = false;

        }
        System.out.println("?????????????????");
        repaint();
    }
}
