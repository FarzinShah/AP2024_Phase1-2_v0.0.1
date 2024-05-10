package third.all.controller.movement;


import third.all.controller.componentController.Controller3;

public class MovementOfYellowEnemy {
    private Vector2D vector1;
    private double speed;

    public MovementOfYellowEnemy(double speed , double xDir , double yDir) {
        this.speed = speed;
        this.vector1 = new Vector2D(xDir,yDir);
    }
    public void update(Controller3 controller){
        int deltaX=0,deltaY =0;

//        if(controller.idRequestingEsc()){
//            System.exit(4);
//        }
//
//        if (controller.idRequestingUp()){
//            deltaY=-5;
//        }
//        if (controller.idRequestingDown()){
//            System.out.println("??");
//            deltaY+=5;
//        }
//        if ((controller.idRequestingRight())){
//            deltaX+=2;
//        }
//        if (controller.idRequestingLeft()){
//            deltaX=-2;
//        }
//        position = new Position(position.getX() + deltaX, position.getY()+deltaY);
        vector1 = new Vector2D(deltaX,deltaY);
        vector1.normalize();
        vector1.multiply(speed);
    }

    public Vector2D getVector1() {
        return vector1;
    }

    public void setVector1(Vector2D vector) {
        this.vector1 = vector;
    }
}
