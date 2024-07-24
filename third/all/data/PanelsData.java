package third.all.data;

import third.all.gameComponents.game.Panel;
import third.all.model.Barricados;
import third.all.model.Wyrm;

import java.util.ArrayList;

import static third.all.controller.Constants.*;
import static third.all.controller.Variables.rng;

public class PanelsData {
    public static PanelsData instance;

    private ArrayList<Panel> panels;
    private ArrayList<Panel> blackOrbPanels;
    private Panel redZone;
    private Panel wyrm;
    private Panel barricados;
    private Panel bossPanel;



    public PanelsData(){
        panels = new ArrayList<>();
        blackOrbPanels = new ArrayList<>();
        redZone = new Panel((int)rng(STARTING_POINT.x,STARTING_POINT.x+300),(int)rng(STARTING_POINT.y,STARTING_POINT.y+300),60,60);
        wyrm = new Panel(Wyrm.getInstance().getLocation().x,Wyrm.getInstance().getLocation().y,80,80);
        barricados = new Panel(Barricados.getInstance().getLocation().x,Barricados.getInstance().getLocation().y,80,80);
        bossPanel = new Panel(Properties.getInstance().locationOfBossPanel.x,Properties.getInstance().locationOfBossPanel.y,Properties
                .getInstance().sizeOfBossPanel.getWidth(),Properties.getInstance().sizeOfBossPanel.getHeight());

        panels.add(0,new Panel(STARTING_POINT.x , STARTING_POINT.y, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT));
        panels.add(1,new Panel((int) Properties.getInstance().THIRD_FRAME_LOCATION_X, (int) Properties.getInstance().THIRD_FRAME_LOCATION_Y, Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH/2,Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH/2));

        blackOrbPanels.add(0,new Panel(1100,550,100,100));
        blackOrbPanels.add(1,new Panel(980,460,100,100));
        blackOrbPanels.add(2,new Panel(1220,460,100,100));
        blackOrbPanels.add(3,new Panel(1175,330,100,100));
        blackOrbPanels.add(4,new Panel(1025,330,100,100));


    }


    public ArrayList<Panel> getPanels() {
        return panels;
    }

    public void setPanels(ArrayList<Panel> panels) {
        this.panels = panels;
    }

    public ArrayList<Panel> getBlackOrbPanels() {
        return blackOrbPanels;
    }

    public void setBlackOrbPanels(ArrayList<Panel> blackOrbPanels) {
        this.blackOrbPanels = blackOrbPanels;
    }

    public Panel getRedZone() {
        return redZone;
    }

    public void setRedZone(Panel redZone) {
        this.redZone = redZone;
    }

    public Panel getWyrm() {
        return wyrm;
    }

    public void setWyrm(Panel wyrm) {
        this.wyrm = wyrm;
    }

    public Panel getBarricados() {
        return barricados;
    }

    public PanelsData setBarricados(Panel barricados) {
        this.barricados = barricados;
        return this;
    }

    public Panel getBossPanel() {
        return bossPanel;
    }

    public PanelsData setBossPanel(Panel bossPanel) {
        this.bossPanel = bossPanel;
        return this;
    }

    public static PanelsData getInstance(){
        if(instance==null) {
            instance = new PanelsData();
            return instance;
        }
        return instance;
    }
    public static void setInstance(PanelsData instance1){
        instance = instance1;
    }


}
