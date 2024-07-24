package third.all.gameComponents.preGameComponent;

import third.all.data.Properties;
import third.all.model.Archmire;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static third.all.controller.Constants.*;



public class Timer1 {
    public static Timer1 instance;

    public static int elapsedTime = 45*60*1000; // 120000
    public int seconds =0;
    public int minutes =45;
    public int hours =0;
    public static int spentMilliSecond = 0;
    public static int spentMilliSecondW2 = 0;
    public static int spentMilliSecondW3 = 0;
    public static int spentMilliSecondW4 = 0;
    public static int spentMilliSecondW5 = 0;
    public static int spentMilliSecondW6 = 0; // BossFight



    public static int startTimeFromActivationOfPointerItem = -78;
    public static int[] startTimeFromCollectiblesItem = {-78,-78,-78,-78,-78,-78,-78,-78,-78,-78,-78,-78};
    public String seconds_string = String.format("%02d", seconds);
    public String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            if(is_Writ_Of_Aceso){
                Properties.getInstance(). HP++;
            }
            spentMilliSecond+=1000;
            if(Properties.getInstance().WAVE==2){
                spentMilliSecondW2+=1000;
            }
            if(Properties.getInstance().WAVE==3){
                spentMilliSecondW3+=1000;
            }
            if(Properties.getInstance().WAVE==4){
                spentMilliSecondW4+=1000;
            }
            if(Properties.getInstance().WAVE==5){
                spentMilliSecondW5+=1000;
            }
            if(Properties.getInstance().WAVE==6){
                spentMilliSecondW6+=1000;
            }
            elapsedTime=elapsedTime-1000;
            hours = (elapsedTime/3600000);
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
//            timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);

            System.out.println(hours+ ":" + minutes + ":" + seconds);

        }

    });


    public Timer1(){

    }


    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        timer.stop();
        elapsedTime=45*60000;
        seconds =0;
        minutes=45;
        hours=0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
    }

    public static Timer1 getInstance(){
        if(instance==null) {
            instance = new Timer1();
            return instance;
        }
        return instance;
    }


}