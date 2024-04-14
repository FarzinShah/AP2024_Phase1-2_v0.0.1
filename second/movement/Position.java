package second.movement;

public class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public int intX() {
        return (int) Math.round(x);
    }
    public int intY() {
        return (int) Math.round(y);
    }
    public void applyOfEpsilon(MovementOfEpsilon movementOfEpsilon) {
        Vector2D vector = movementOfEpsilon.getVector();
        x+= vector.getX();
        y+= vector.getY();
    }
    public void applyOfYellowEnemy(MovementOfYellowEnemy movementOfYellowEnemy) {
        Vector2D vector = movementOfYellowEnemy.getVector1();
        x+= vector.getX();
        y+= vector.getY();
    }
}
