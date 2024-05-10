package third.all.controller.movement;


import static third.all.controller.Constants.*;

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
        x+= 2*vector.getX();
        y+= 2*vector.getY();
        if(x>3*GLASS_FRAME_DIMENSION_WIDTH/4 + 75){
            x=3*GLASS_FRAME_DIMENSION_WIDTH/4 + 75 - 1.0; //todo set bounder
        }
        if(x<3){
            x=3;
        }
        if(y>3*GLASS_FRAME_DIMENSION_HEIGHT/4 + 75){
            y=3*GLASS_FRAME_DIMENSION_HEIGHT/4 +75- 1.0; // todo exact bound
        }
        if(y<3){
            y=3;
        }

    }
    public void applyOfYellowEnemy(MovementOfYellowEnemy movementOfYellowEnemy) {
        Vector2D vector = movementOfYellowEnemy.getVector1();
        x+= ACCELERATION_OF_YELLOW_ENEMIES*vector.getX(); //todo: از اینجا سرعت رو کم و زیاد کنم // x * vector.getX(); x میتونه زیاد و کم بشه
        y+= ACCELERATION_OF_YELLOW_ENEMIES*vector.getY(); //todo
    }
    public void applyOfGreenEnemy(MovementOfGreenEnemy movementOfGreenEnemy) {
        Vector2D vector = movementOfGreenEnemy.getVector1();
        x+= ACCELERATION_OF_GREEN_ENEMIES*vector.getX(); //todo: از اینجا سرعت رو کم و زیاد کنم // x * vector.getX(); x میتونه زیاد و کم بشه
        y+= ACCELERATION_OF_GREEN_ENEMIES*vector.getY(); //todo
    }
}
