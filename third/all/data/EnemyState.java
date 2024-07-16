package third.all.data;

import third.all.controller.componentController.Controller3;
import third.all.controller.movement.Vector2D;

public class EnemyState {
    public Controller3 controller3;
    public double radius;
    public double posX,posY;
    public Vector2D dir;

    private EnemyState(Builder builder) {
        this.controller3 = builder.controller3;
        this.radius = builder.radius;
        this.posX = builder.posX;
        this.posY = builder.posY;
        this.dir = builder.dir;
    }
    public static class Builder {
        private Controller3 controller3;
        private double radius;
        private double posX,posY;
        private Vector2D dir;


        public Controller3 getController3() {
            return controller3;
        }

        public Builder setController3(Controller3 controller3) {
            this.controller3 = controller3;
            return this;
        }

        public double getRadius() {
            return radius;
        }

        public Builder setRadius(double radius) {
            this.radius = radius;
            return this;
        }

        public double getPosX() {
            return posX;
        }

        public Builder setPosX(double posX) {
            this.posX = posX;
            return this;
        }

        public double getPosY() {
            return posY;
        }

        public Builder setPosY(double posY) {
            this.posY = posY;
            return this;
        }

        public Vector2D getDir() {
            return dir;
        }

        public Builder setDir(Vector2D dir) {
            this.dir = dir;
            return this;
        }

        public EnemyState build() {
            return new EnemyState(this);
        }
    }
}
