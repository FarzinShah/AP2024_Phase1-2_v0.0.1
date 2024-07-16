package third.all.controller.entity;

import third.all.controller.componentController.Controller3;
import third.all.controller.movement.MovementOfGreenEnemy;
import third.all.controller.movement.MovementOfYellowEnemy;

public abstract class MovingEnemy extends GameObject {
    private Controller3 controller3;
    private MovementOfYellowEnemy movementOfYellowEnemy;
    private MovementOfGreenEnemy movementOfGreenEnemy;
//    private double posX;
//    private double posY;

    public MovingEnemy(Controller3 controller3 , double posX, double posY){
        super(posX,posY);
        this.controller3 = controller3;
        this.movementOfYellowEnemy = new MovementOfYellowEnemy(5,0,0);
        this.movementOfGreenEnemy = new MovementOfGreenEnemy(5,0,0);
    }

    @Override
    public void update(){

        movementOfYellowEnemy.update(controller3);
        position.applyOfYellowEnemy(movementOfYellowEnemy);
        movementOfGreenEnemy.update(controller3);
        position.applyOfGreenEnemy(movementOfGreenEnemy);

      //  movementOfGreenEnemy.update(controller3);

    }

    public MovementOfYellowEnemy getMovementOfYellowEnemy() {
        return movementOfYellowEnemy;
    }
    public MovementOfGreenEnemy getMovementOfGreenEnemy() {
        return movementOfGreenEnemy;
    }

    public void setMovementOfYellowEnemy(MovementOfYellowEnemy movementOfYellowEnemy) {
        this.movementOfYellowEnemy = movementOfYellowEnemy;
    }
}
