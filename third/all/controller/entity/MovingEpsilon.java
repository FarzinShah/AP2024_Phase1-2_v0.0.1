package third.all.controller.entity;


import third.all.controller.componentController.Controller3;
import third.all.controller.movement.MovementOfEpsilon;


public abstract class MovingEpsilon extends GameObject {
    private Controller3 controller3;
    private MovementOfEpsilon movementOfEpsilon;
//    private double posX;
//    private double posY;

    public MovingEpsilon(Controller3 controller3 , double posX, double posY){
        super(posX,posY);
        this.controller3 = controller3;

        this.movementOfEpsilon = new MovementOfEpsilon(2);
    }

    @Override
    public void update(){

        movementOfEpsilon.update(controller3);
        position.applyOfEpsilon(movementOfEpsilon);

    }

    public MovementOfEpsilon getMovementOfEpsilon() {
        return movementOfEpsilon;
    }

    public void setMovementOfEpsilon(MovementOfEpsilon movementOfEpsilon) {
        this.movementOfEpsilon = movementOfEpsilon;
    }
}
