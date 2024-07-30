package third.all.model.epsilon;

public class ShotOfEpsilon_P1 {
    private double dirX;
    private double dirY;
    private double placeX;
    private double placeY;
    private int numberOfCollision;

    public ShotOfEpsilon_P1(double dirX, double dirY, double placeX, double placeY) {
        this.dirX = dirX;
        this.dirY = dirY;
        this.placeX = placeX;
        this.placeY = placeY;
        this.numberOfCollision = 0;

    }

    public int getNumberOfCollision() {
        return numberOfCollision;
    }

    public void setNumberOfCollision(int numberOfCollision) {
        this.numberOfCollision = numberOfCollision;
    }

    public double getDirX() {
        return dirX;
    }

    public void setDirX(double dirX) {
        this.dirX = dirX;
    }

    public double getDirY() {
        return dirY;
    }

    public void setDirY(double dirY) {
        this.dirY = dirY;
    }

    public double getPlaceX() {
        return placeX;
    }

    public void setPlaceX(double placeX) {
        this.placeX = placeX;
    }

    public double getPlaceY() {
        return placeY;
    }

    public void setPlaceY(double placeY) {
        this.placeY = placeY;
    }
}
