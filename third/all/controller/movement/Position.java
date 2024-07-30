package third.all.controller.movement;


import third.all.data.Properties;

import java.awt.*;

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
        Rectangle epsilon = new Rectangle((int) x, (int) y, EPSILON_WIDTH, EPSILON_LENGTH);
        Rectangle mainPanel = new Rectangle(STARTING_POINT.x, STARTING_POINT.y, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
        Rectangle thirdPanel = new Rectangle((int) Properties.getInstance().SECOND_FRAME_LOCATION_X, (int) Properties.getInstance().SECOND_FRAME_LOCATION_Y, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH / 2, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT / 2);
        Rectangle thirdMinusMain_RightSide = new Rectangle(
                (int) Properties.getInstance().SECOND_FRAME_LOCATION_X, (int) Properties.getInstance().SECOND_FRAME_LOCATION_Y, (int) ((Properties.getInstance().SECOND_FRAME_LOCATION_X + Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH / 2) - STARTING_POINT.x + Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH), (int) (Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT / 2));
//        if (play) {
            Vector2D vector = movementOfEpsilon.getVector();
            x += ACCELERATION_OF_EPSILON * vector.getX();
            y += ACCELERATION_OF_EPSILON * vector.getY();
//        }
//        if (x > GLASS_FRAME_DIMENSION_WIDTH - EPSILON_LENGTH) {
//            x = GLASS_FRAME_DIMENSION_WIDTH - EPSILON_LENGTH; //todo set bounder
//        }


//        if (STARTING_POINT.x + GLASS_FRAME_DIMENSION_WIDTH < THIRD_FRAME_LOCATION_X) {
//            if (epsilon.intersects(thirdMinusMain_RightSide)) {
//                if (y + EPSILON_WIDTH < THIRD_FRAME_LOCATION_Y) {
//                    y = -EPSILON_WIDTH + THIRD_FRAME_LOCATION_Y;
//                }
//                if (y > THIRD_FRAME_LOCATION_Y - EPSILON_WIDTH + GLASS_FRAME_DIMENSION_WIDTH / 2) {
//                    y = THIRD_FRAME_LOCATION_Y - EPSILON_WIDTH + GLASS_FRAME_DIMENSION_WIDTH / 2;
//                }
//                if (x > THIRD_FRAME_LOCATION_X - EPSILON_WIDTH + (GLASS_FRAME_DIMENSION_WIDTH / 2)) {
//                    x = THIRD_FRAME_LOCATION_X - EPSILON_WIDTH + (GLASS_FRAME_DIMENSION_WIDTH / 2);
//                }
//            }
//        }

//        //todo: case1: وقتی که دوتاصفحه مماس میشن:
//        if (THIRD_FRAME_LOCATION_X < STARTING_POINT.x + GLASS_FRAME_DIMENSION_WIDTH &&
//                THIRD_FRAME_LOCATION_X + GLASS_FRAME_DIMENSION_HEIGHT / 2 > STARTING_POINT.x + GLASS_FRAME_DIMENSION_WIDTH) {
////            if(x>THIRD_FRAME_LOCATION_X + GLASS_FRAME_DIMENSION_HEIGHT/2 - EPSILON_LENGTH){
////                x=THIRD_FRAME_LOCATION_X + GLASS_FRAME_DIMENSION_HEIGHT/2 - EPSILON_LENGTH;
////            }
////            if(y + EPSILON_WIDTH> THIRD_FRAME_LOCATION_Y){
////                y = -EPSILON_WIDTH + THIRD_FRAME_LOCATION_Y;
////            }
//
//
//        }
//        if ((STARTING_POINT.x + GLASS_FRAME_DIMENSION_WIDTH) >= THIRD_FRAME_LOCATION_X &&
//                THIRD_FRAME_LOCATION_X > STARTING_POINT.x) {
//            if (epsilon.intersects(mainPanel) && !epsilon.intersects(thirdPanel)) {
//                if (x > GLASS_FRAME_DIMENSION_WIDTH - EPSILON_LENGTH) {
//                    x = GLASS_FRAME_DIMENSION_WIDTH - EPSILON_LENGTH; //todo set bounder
//                }
//                if (y > GLASS_FRAME_DIMENSION_HEIGHT - EPSILON_LENGTH) {
//                    y = GLASS_FRAME_DIMENSION_HEIGHT - EPSILON_LENGTH;
//                } else if (y < 3) y = 0;
//                //todo: if needed;
//            } else if (epsilon.intersects(mainPanel) && epsilon.intersects(thirdPanel)) {
//                if (x > THIRD_FRAME_LOCATION_X + GLASS_FRAME_DIMENSION_HEIGHT / 2) {
//                    x = THIRD_FRAME_LOCATION_X + GLASS_FRAME_DIMENSION_HEIGHT / 2;
//                }
//                if (THIRD_FRAME_LOCATION_Y < STARTING_POINT.y) {
//                    if (y > STARTING_POINT.y) y = STARTING_POINT.y;
//                    if (y - EPSILON_LENGTH < THIRD_FRAME_LOCATION_Y - GLASS_FRAME_DIMENSION_HEIGHT / 2) {
//                        y = (THIRD_FRAME_LOCATION_Y - GLASS_FRAME_DIMENSION_HEIGHT / 2 + EPSILON_LENGTH);
//                    }
//                } else if (THIRD_FRAME_LOCATION_Y >= STARTING_POINT.y) {
////                    if(y<THIRD_FRAME_LOCATION_Y){
////                        y=THIRD_FRAME_LOCATION_Y;
////                    }
////                    if(y>STARTING_POINT.y+GLASS_FRAME_DIMENSION_HEIGHT+EPSILON_LENGTH){
////                        y=STARTING_POINT.y+GLASS_FRAME_DIMENSION_HEIGHT+EPSILON_LENGTH;
////                    }
//                }
//            } else if (!epsilon.intersects(mainPanel) && epsilon.intersects(thirdPanel)) {
//                if (x > THIRD_FRAME_LOCATION_X + GLASS_FRAME_DIMENSION_HEIGHT / 2) {
//                    x = THIRD_FRAME_LOCATION_X + GLASS_FRAME_DIMENSION_HEIGHT / 2;
//                }
//                if (y > THIRD_FRAME_LOCATION_Y + GLASS_FRAME_DIMENSION_HEIGHT / 2) {
//                    y = THIRD_FRAME_LOCATION_Y + GLASS_FRAME_DIMENSION_HEIGHT / 2;
//                }
//                if (y < THIRD_FRAME_LOCATION_Y) {
//                    y = THIRD_FRAME_LOCATION_Y;
//                }
//            } else if (STARTING_POINT.x + GLASS_FRAME_DIMENSION_WIDTH < THIRD_FRAME_LOCATION_X) {
//                if (x > GLASS_FRAME_DIMENSION_WIDTH - EPSILON_LENGTH) {
//                    x = GLASS_FRAME_DIMENSION_WIDTH - EPSILON_LENGTH; //todo set bounder
//                }
//            }
////        Rectangle intersection  =(new Rectangle(STARTING_POINT.x, STARTING_POINT.y, (int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT)
////                .intersection(new Rectangle((int) THIRD_FRAME_LOCATION_X, (int) THIRD_FRAME_LOCATION_Y, (int) GLASS_FRAME_DIMENSION_WIDTH / 2, (int) GLASS_FRAME_DIMENSION_HEIGHT / 2)));
//////        if(new Rectangle((int) x, (int) y,EPSILON_WIDTH,EPSILON_LENGTH).intersects(intersection)){
//////            if(x)
//////        }
////        if ((new Rectangle(STARTING_POINT.x, STARTING_POINT.y, (int) GLASS_FRAME_DIMENSION_WIDTH, (int) GLASS_FRAME_DIMENSION_HEIGHT)
////                .intersects(new Rectangle((int) THIRD_FRAME_LOCATION_X, (int) THIRD_FRAME_LOCATION_Y, (int) GLASS_FRAME_DIMENSION_WIDTH / 2, (int) GLASS_FRAME_DIMENSION_HEIGHT / 2)))) {
////
////            if ((STARTING_POINT.x + GLASS_FRAME_DIMENSION_WIDTH > THIRD_FRAME_LOCATION_X)) {
////                if (x > GLASS_FRAME_DIMENSION_WIDTH / 2 - EPSILON_LENGTH) {
////                    x = GLASS_FRAME_DIMENSION_WIDTH / 2 - EPSILON_LENGTH;
////                }
////            }else {
//////                if (x > GLASS_FRAME_DIMENSION_WIDTH - EPSILON_LENGTH) {
//////                    x = GLASS_FRAME_DIMENSION_WIDTH - EPSILON_LENGTH; //todo set bounder
//////                }
////            }
////        }else{
////            if (x > GLASS_FRAME_DIMENSION_WIDTH - EPSILON_LENGTH) {
////                x = GLASS_FRAME_DIMENSION_WIDTH - EPSILON_LENGTH; //todo set bounder
////            }
////        }
////        if (x>THIRD_FRAME_LOCATION_X+GLASS_FRAME_DIMENSION_WIDTH/2-EPSILON_LENGTH){
////            x=THIRD_FRAME_LOCATION_X+GLASS_FRAME_DIMENSION_WIDTH/2-EPSILON_LENGTH;
////        }
//            if (x < 3) {
//                x = 3;
//            }
//            if (y > GLASS_FRAME_DIMENSION_HEIGHT - EPSILON_WIDTH) {
//                y = GLASS_FRAME_DIMENSION_HEIGHT - EPSILON_WIDTH; // todo exact bound
//            }
//            if (y < 3) {
//                y = 3;
//            }
//
//        }
    }
    public void applyOfYellowEnemy(MovementOfYellowEnemy movementOfYellowEnemy) {
        Vector2D vector = movementOfYellowEnemy.getVector1();
        x += ACCELERATION_OF_YELLOW_ENEMIES * vector.getX(); //todo: از اینجا سرعت رو کم و زیاد کنم // x * vector.getX(); x میتونه زیاد و کم بشه
        y += ACCELERATION_OF_YELLOW_ENEMIES * vector.getY(); //todo
    }

    public void applyOfGreenEnemy(MovementOfGreenEnemy movementOfGreenEnemy) {
        Vector2D vector = movementOfGreenEnemy.getVector1();
        x += ACCELERATION_OF_GREEN_ENEMIES * vector.getX(); //todo: از اینجا سرعت رو کم و زیاد کنم // x * vector.getX(); x میتونه زیاد و کم بشه
        y += ACCELERATION_OF_GREEN_ENEMIES * vector.getY(); //todo
    }
}

