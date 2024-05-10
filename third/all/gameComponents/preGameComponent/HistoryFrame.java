package third.all.gameComponents.preGameComponent;


import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static third.all.gameComponents.preGameComponent.LoginFrame.information;

public class HistoryFrame extends JFrame implements KeyListener {

    JScrollPane scrollPane;
    String[][] datas = new String[100][3];
    String[] header = new String[]{"Player", "Score", "Date"};


    HistoryFrame() {
        this.setTitle("ScoreBoard List");
        this.setSize(1000, 400);

        JTable table = new JTable(datas, header);
        scrollPane = new JScrollPane(table);


        scrollPane.setBounds(950, 0, 30, 400);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setLocation(10, 10);

        table.setBounds(0, 0, 1000, 1000);
        table.setRowHeight(40);

        for (int i = 0; i < information.size(); i++) {
            table.setValueAt(information.get(i).getName() , i, 0);
        }

        for (int i = 0; i < information.size(); i++) {
            table.setValueAt(String.valueOf(information.get(i).getScore()) , i, 1);
        }
        for (int i = 0; i < information.size(); i++) {
            table.setValueAt(information.get(i).getDate() , i, 2);
        }



        this.setResizable(false);
        this.add(scrollPane);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        if(e.getKeyCode()==KeyEvent.VK_){
//            dispose();
//            MainFrame2 loginFrame = new MainFrame2();
//        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
