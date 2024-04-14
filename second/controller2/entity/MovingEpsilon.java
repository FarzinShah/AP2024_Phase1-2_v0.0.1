package second.controller2.entity;

import second.controller2.controlComponent.Controller2;
import second.movement.MovementOfEpsilon;

public abstract class MovingEpsilon extends GameObject {
    private Controller2 controller2;
    private MovementOfEpsilon movementOfEpsilon;
//    private double posX;
//    private double posY;

    public MovingEpsilon(Controller2 controller2 , double posX, double posY){
        super(posX,posY);
        this.controller2 = controller2;
        this.movementOfEpsilon = new MovementOfEpsilon(2);
    }

    @Override
    public void update(){
        movementOfEpsilon.update(controller2);
        position.applyOfEpsilon(movementOfEpsilon);

    }
}
