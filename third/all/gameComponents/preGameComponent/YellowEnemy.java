package third.all.gameComponents.preGameComponent;

public class YellowEnemy {
    private double YE_posX1;
    private double YE_posY1;
    private double YE_posX2;
    private double YE_posY2;
    private double YE_posX3;
    private double YE_posY3;
    private double angleOfRotation;
    private double YE_dirX;
    private double YE_dirY;
    private double YE_rateX;
    private double YE_rateY;
    private double radius;
    private int lifeValue;

    public YellowEnemy(double YE_posX1, double YE_posY1,double YE_posX2, double YE_posY2,double YE_posX3, double YE_posY3, double YE_dirX, double YE_dirY,double radius) {
        this.YE_posX1 = YE_posX1;
        this.YE_posY1 = YE_posY1;
        this.YE_posX2 = YE_posX2;
        this.YE_posY2 = YE_posY2;
        this.YE_posX3 = YE_posX3;
        this.YE_posY3 = YE_posY3;
        this.YE_dirX = YE_dirX;
        this.YE_dirY = YE_dirY;
        this.radius = radius;

    }

    public double getYE_posX1() {
        return YE_posX1;
    }

    public void setYE_posX1(double YE_posX) {
        this.YE_posX1 = YE_posX;
    }

    public double getYE_posY1() {
        return YE_posY1;
    }

    public void setYE_posY1(double YE_posY) {
        this.YE_posY1 = YE_posY;
    }

    public double getYE_posX2() {
        return YE_posX2;
    }

    public void setYE_posX2(double YE_posX2) {
        this.YE_posX2 = YE_posX2;
    }

    public double getYE_posY2() {
        return YE_posY2;
    }

    public void setYE_posY2(double YE_posY2) {
        this.YE_posY2 = YE_posY2;
    }

    public double getYE_posX3() {
        return YE_posX3;
    }

    public void setYE_posX3(double YE_posX3) {
        this.YE_posX3 = YE_posX3;
    }

    public double getYE_posY3() {
        return YE_posY3;
    }

    public void setYE_posY3(double YE_posY3) {
        this.YE_posY3 = YE_posY3;
    }

    public double getYE_dirX() {
        return YE_dirX;
    }

    public void setYE_dirX(double YE_dirX) {
        this.YE_dirX = YE_dirX;
    }

    public double getYE_dirY() {
        return YE_dirY;
    }

    public void setYE_dirY(double YE_dirY) {
        this.YE_dirY = YE_dirY;
    }

    public double getYE_rateX() {
        return YE_rateX;
    }

    public void setYE_rateX(double YE_rateX) {
        this.YE_rateX = YE_rateX;
    }

    public double getYE_rateY() {
        return YE_rateY;
    }

    public void setYE_rateY(double YE_rateY) {
        this.YE_rateY = YE_rateY;
    }

    public double getAngleOfRotation() {
        return angleOfRotation;
    }

    public void setAngleOfRotation(double angleOfRotation) {
        this.angleOfRotation = angleOfRotation;
    }

    public int getLifeValue() {
        return lifeValue;
    }

    public void setLifeValue(int lifeValue) {
        this.lifeValue = lifeValue;
    }

}
