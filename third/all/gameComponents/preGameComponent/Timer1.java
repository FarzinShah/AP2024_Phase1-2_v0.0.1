package third.all.gameComponents.preGameComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static third.all.controller.Constants.is_Writ_Of_Aceso;
import static third.all.controller.Constants.XXED;


public class Timer1 {

//    JFrame frame = new JFrame();
//    JButton startButton = new JButton("START");
//    JButton resetButton = new JButton("RESET");
//    JLabel timeLabel = new JLabel();
    public static int elapsedTime = 45*60*1000; // 120000
    int seconds =0;
    int minutes =45;
    int hours =0;
    boolean started = false;
    public static int spentMilliSecond = 0;
    public static int startTimeFromActivationOfPointerItem = -78;
    public static int[] startTimeFromCollectiblesItem = {-78,-78,-78,-78,-78,-78,-78,-78,-78,-78,-78,-78};
    public String seconds_string = String.format("%02d", seconds);
    public String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            if(is_Writ_Of_Aceso){
                XXED++;
            }
            spentMilliSecond+=1000;
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

//        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
//        timeLabel.setBounds(100,100,200,100);
//        timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
//        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
//        timeLabel.setOpaque(true);
//        timeLabel.setHorizontalAlignment(JTextField.CENTER);

//        startButton.setBounds(100,200,100,50);
//        startButton.setFont(new Font("Ink Free",Font.PLAIN,20));
//        startButton.setFocusable(false);
//        startButton.addActionListener(this);
//
//        resetButton.setBounds(200,200,100,50);
//        resetButton.setFont(new Font("Ink Free",Font.PLAIN,20));
//        resetButton.setFocusable(false);
//        resetButton.addActionListener(this);
//
//        frame.add(startButton);
//        frame.add(resetButton);
//        frame.add(timeLabel);

//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(420,420);
//        frame.setLayout(null);
//        frame.setVisible(true);
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
//        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
    }
//
//    public boolean bricksQuake(){
//        return elapsedTime > 65000 && elapsedTime < 70000;
//    }


}