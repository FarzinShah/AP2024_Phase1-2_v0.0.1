package third.all.data;

import java.util.ArrayList;

public class BooleansOfCollectibles {

    public static BooleansOfCollectibles instance;

    //todo: (isValidToCollectEnemies) Element Guider:
    // 0. isValidToCollectArchmire
    // 1. isValidToCollectNecropick
    // 2. isValidToCollectOmenoct
    // 3. isValidToCollectOWyrm
    // 4. isValidToCollectOBarricados
    // 5. isValidToCollectOBlackOrbs


    private ArrayList<Boolean> isValidToCollect;
    private ArrayList<Boolean> isValidYEtoCollect;
    private ArrayList<Boolean> showOfCollectiblesHelperG;
    private ArrayList<Boolean> showOfCollectiblesHelperY;
    private ArrayList<Boolean> isValidOrbToCollect;
    private boolean isValidToCollectWyrm;

    public BooleansOfCollectibles() {
        isValidToCollect = new ArrayList<>();
        showOfCollectiblesHelperG = new ArrayList<>();
        showOfCollectiblesHelperY = new ArrayList<>();
        isValidYEtoCollect= new ArrayList<>();
        isValidOrbToCollect = new ArrayList<>();
        isValidToCollectWyrm = true;
        for (int i = 0; i < 6; i++) {
            isValidToCollect.add(i,false);
        }




    }

    public ArrayList<Boolean> getIsValidToCollect() {
        return isValidToCollect;
    }

    public void setIsValidToCollect(ArrayList<Boolean> isValidToCollect) {
        this.isValidToCollect = isValidToCollect;
    }

    public ArrayList<Boolean> getIsValidYEtoCollect() {
        return isValidYEtoCollect;
    }

    public void setIsValidYEtoCollect(ArrayList<Boolean> isValidYEtoCollect) {
        this.isValidYEtoCollect = isValidYEtoCollect;
    }

    public ArrayList<Boolean> getShowOfCollectiblesHelperG() {
        return showOfCollectiblesHelperG;
    }

    public void setShowOfCollectiblesHelperG(ArrayList<Boolean> showOfCollectiblesHelperG) {
        this.showOfCollectiblesHelperG = showOfCollectiblesHelperG;
    }

    public ArrayList<Boolean> getShowOfCollectiblesHelperY() {
        return showOfCollectiblesHelperY;
    }

    public void setShowOfCollectiblesHelperY(ArrayList<Boolean> showOfCollectiblesHelperY) {
        this.showOfCollectiblesHelperY = showOfCollectiblesHelperY;
    }

    public ArrayList<Boolean> getIsValidOrbToCollect() {
        return isValidOrbToCollect;
    }

    public void setIsValidOrbToCollect(ArrayList<Boolean> isValidOrbToCollect) {
        this.isValidOrbToCollect = isValidOrbToCollect;
    }

    public boolean isValidToCollectWyrm() {
        return isValidToCollectWyrm;
    }

    public void setValidToCollectWyrm(boolean validToCollectWyrm) {
        isValidToCollectWyrm = validToCollectWyrm;
    }

    public static BooleansOfCollectibles getInstance(){
        if(instance==null) {
            instance = new BooleansOfCollectibles();
            return instance;
        }
        return instance;
    }
    public static void setInstance(BooleansOfCollectibles instance1){
        instance = instance1;
    }


}
