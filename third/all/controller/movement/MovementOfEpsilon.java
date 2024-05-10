package third.all.controller.movement;


import third.all.controller.componentController.Controller3;

public class MovementOfEpsilon {
    private Vector2D vector;
    private double speed;

    public MovementOfEpsilon(double speed) {
        this.speed = speed;
        this.vector = new Vector2D(0,0);
    }
    public void update(Controller3 controller){
        int deltaX=0,deltaY =0;

        if(controller.idRequestingEsc()){
            System.exit(4);
        }

        if (controller.idRequestingUp()){
            deltaY=-2;
        }
        if (controller.idRequestingDown()){
            deltaY+=2;
        }
        if ((controller.idRequestingRight())){
            deltaX+=2;
        }
        if (controller.idRequestingLeft()){
            deltaX=-2;
        }
//        position = new Position(position.getX() + deltaX, position.getY()+deltaY);
        vector = new Vector2D(deltaX,deltaY);
        vector.normalize();
        vector.multiply(speed);
    }

    public Vector2D getVector() {
        return vector;
    }

    public void setVector(Vector2D vector) {
        this.vector = vector;
    }
}
