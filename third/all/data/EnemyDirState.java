package third.all.data;

import third.all.controller.componentController.Controller3;
import third.all.controller.movement.Vector2D;

public class EnemyDirState {
    public Vector2D dir;

    public EnemyDirState(Builder builder) {
        this.dir = builder.dir;
    }
    public static class Builder {

        private Vector2D dir;

        public Vector2D getDir() {
            return dir;
        }

        public Builder setDir(Vector2D dir) {
            this.dir = dir;
            return this;
        }

        public EnemyDirState build() {
            return new EnemyDirState(this);
        }
    }
}
