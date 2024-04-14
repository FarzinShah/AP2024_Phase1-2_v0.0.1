package second.controller2.entity;

import second.controller2.controlComponent.Controller2;
import second.movement.MovementOfYellowEnemy;

public abstract class MovingEnemy extends GameObject {
    private Controller2 controller2;
    private MovementOfYellowEnemy movementOfYellowEnemy;
//    private double posX;
//    private double posY;

    public MovingEnemy(Controller2 controller2 , double posX, double posY){
        super(posX,posY);
        this.controller2 = controller2;
        this.movementOfYellowEnemy = new MovementOfYellowEnemy(5,0,0);
    }

    @Override
    public void update(){
        movementOfYellowEnemy.update(controller2);
        position.applyOfYellowEnemy(movementOfYellowEnemy);

    }

    public MovementOfYellowEnemy getMovementOfYellowEnemy() {
        return movementOfYellowEnemy;
    }

    public void setMovementOfYellowEnemy(MovementOfYellowEnemy movementOfYellowEnemy) {
        this.movementOfYellowEnemy = movementOfYellowEnemy;
    }
}
