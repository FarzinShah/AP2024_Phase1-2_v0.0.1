package third.all.model;

public class Ball {
    private int ballPosX;
    private int ballPosY;
    private double ballXdir;
    private double ballYdir;
    private int ovalWidth;
    private int ovalHeight;

    public Ball(int ballPosX, int ballPosY, double ballXdir, double ballYdir) {
        this.ballPosX = ballPosX;
        this.ballPosY = ballPosY;
        this.ballXdir = ballXdir;
        this.ballYdir = ballYdir;
    }

    public int getOvalWidth() {
        return ovalWidth;
    }

    public void setOvalWidth(int ovalWidth) {
        this.ovalWidth = ovalWidth;
    }

    public int getOvalHeight() {
        return ovalHeight;
    }

    public void setOvalHeight(int ovalHeight) {
        this.ovalHeight = ovalHeight;
    }

    public int getBallPosX() {
        return ballPosX;
    }

    public void setBallPosX(int ballPosX) {
        this.ballPosX = ballPosX;
    }

    public int getBallPosY() {
        return ballPosY;
    }

    public void setBallPosY(int ballPosY) {
        this.ballPosY = ballPosY;
    }

    public double getBallXdir() {
        return ballXdir;
    }

    public void setBallXdir(double ballXdir) {
        this.ballXdir = ballXdir;
    }

    public double getBallYdir() {
        return ballYdir;
    }

    public void setBallYdir(double ballYdir) {
        this.ballYdir = ballYdir;
    }

}
