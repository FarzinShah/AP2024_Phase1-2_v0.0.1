package third.all.controller.movement;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import third.all.controller.componentController.Controller3;
import third.all.data.booleans.BooleansOf_IsValidToShow;
import third.all.data.PanelsData;
import third.all.data.Properties;

import java.awt.*;

import static third.all.controller.Constants.*;
import static third.all.gameComponents.game.GameLoop.gameObjects;
import static third.all.gameComponents.game.MyPanel.panels;

public class MovementOfEpsilon {
    private static final Logger logger = LoggerFactory.getLogger(MovementOfEpsilon.class);

    private Vector2D vector;
    private final double speed;
    Rectangle epsilon;
    Rectangle mainPanel;
    Rectangle bossPanel;
    Rectangle secondPanel;


    public MovementOfEpsilon(double speed) {
        this.speed = speed;
        this.vector = new Vector2D(0, 0);
        logger.info("MovementOfEpsilon initialized with MovementOfEpsilon={}", speed);

    }

    public void update(Controller3 controller) {
        epsilon = new Rectangle((int) gameObjects.get(0).getPosition().getX(), (int) gameObjects.get(0).getPosition().getY(), EPSILON_WIDTH, EPSILON_LENGTH);
        mainPanel = new Rectangle(STARTING_POINT.x, STARTING_POINT.y, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT);
        secondPanel = PanelsData.getInstance().getPanels().get(1).getRectangle();
        bossPanel = PanelsData.getInstance().getBossPanel().getRectangle();
        Rectangle temp3 = new Rectangle(secondPanel);
        int deltaX = 0, deltaY = 0;


        if (mainPanel.contains(epsilon)) {
            BooleansOf_IsValidToShow.getInstance().getIsEpsilonIntoPanel().set(1, true);
        } else if (!mainPanel.intersects(epsilon)) {
            BooleansOf_IsValidToShow.getInstance().getIsEpsilonIntoPanel().set(1, false);
        }
        if (secondPanel.contains(epsilon)) {
            BooleansOf_IsValidToShow.getInstance().getIsEpsilonIntoPanel().set(2, true);
        } else if (!secondPanel.intersects(epsilon)) {
            BooleansOf_IsValidToShow.getInstance().getIsEpsilonIntoPanel().set(2, false);
        }


//        if(BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().get(1)){
//            if (controller.idRequestingEsc()) {
//                System.exit(4);
//            }
//            if (controller.idRequestingUp()) {
//                logger.debug("controller idRequestingUp{}", controller.idRequestingUp());
//                deltaY = -2;
//            }
//            if (controller.idRequestingDown()) {
//                logger.debug("controller idRequestingUp{}", controller.idRequestingDown());
//                deltaY += 2;
//            }
//            if ((controller.idRequestingRight())) {
//                logger.debug("controller idRequestingRight{}", controller.idRequestingRight());
//
//                deltaX += 2;
//            }
//            if (controller.idRequestingLeft()) {
//                logger.debug("controller idRequestingLeft{}", controller.idRequestingLeft());
//
//                deltaX = -2;
//            }
//        }
        if (BooleansOf_IsValidToShow.getInstance().isValidToShowBossPanel()) {

            if (controller.idRequestingEsc()) {
                System.exit(4);
            }
            if (controller.idRequestingUp()) {
                logger.debug("controller idRequestingUp{}", controller.idRequestingUp());
                deltaY = -2;
            }
            if (controller.idRequestingDown()) {
                logger.debug("controller idRequestingUp{}", controller.idRequestingDown());
                deltaY += 2;
            }
            if ((controller.idRequestingRight())) {
                logger.debug("controller idRequestingRight{}", controller.idRequestingRight());

                deltaX += 2;
            }
            if (controller.idRequestingLeft()) {
                logger.debug("controller idRequestingLeft{}", controller.idRequestingLeft());

                deltaX = -2;
            }
        }

        if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().get(1)) {
            if (BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().get(2)) {
                if (secondPanel.contains(epsilon)) {
                    if (controller.idRequestingEsc()) {
                        logger.debug("controller idRequestingEsc{}", controller.idRequestingEsc());
                        System.exit(4);
                    }
                    if (controller.idRequestingUp()) {
                        logger.debug("controller idRequestingUp{}", controller.idRequestingUp());
                        deltaY = -2;
                    }
                    if (controller.idRequestingDown()) {
                        logger.debug("controller idRequestingUp{}", controller.idRequestingDown());
                        deltaY += 2;
                    }
                    if ((controller.idRequestingRight())) {
                        logger.debug("controller idRequestingRight{}", controller.idRequestingRight());

                        deltaX += 2;
                    }
                    if (controller.idRequestingLeft()) {
                        logger.debug("controller idRequestingLeft{}", controller.idRequestingLeft());

                        deltaX = -2;
                    }

                    if (!BooleansOf_IsValidToShow.getInstance().getIsEpsilonIntoPanel().get(1)) {
                        if (gameObjects.get(0).getPosition().getX() < PanelsData.getInstance().getPanels().get(1).getX() + 5) {
                            gameObjects.get(0).setPosition(new Position(secondPanel.getX() + 5, gameObjects.get(0).getPosition().getY()));
                        }
                        if (gameObjects.get(0).getPosition().getX() + EPSILON_WIDTH > PanelsData.getInstance().getPanels().get(1).getRightX() - 5) {
                            gameObjects.get(0).setPosition(new Position(secondPanel.getX() + secondPanel.width - 5 - EPSILON_WIDTH, gameObjects.get(0).getPosition().getY()));
                        }
                        if (gameObjects.get(0).getPosition().getY() + EPSILON_WIDTH > PanelsData.getInstance().getPanels().get(1).getDownY() - 5) {
                            gameObjects.get(0).setPosition(new Position(gameObjects.get(0).getPosition().getX(), PanelsData.getInstance().getPanels().get(1).getDownY() - 5 - EPSILON_WIDTH));
                        }
                        if (gameObjects.get(0).getPosition().getY() < PanelsData.getInstance().getPanels().get(1).getY() + 5) {
                            gameObjects.get(0).setPosition(new Position(gameObjects.get(0).getPosition().getX(), PanelsData.getInstance().getPanels().get(1).getY() + 5));
                        }
                    }


                } else if (!(mainPanel.contains(epsilon) || secondPanel.contains(epsilon))) {

                    if (gameObjects.get(0).getPosition().getX() + EPSILON_WIDTH - Properties.getInstance().SECOND_FRAME_LOCATION_X + Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH / 2 <
                            gameObjects.get(0).getPosition().getX() + EPSILON_WIDTH - STARTING_POINT.x + Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH)

                        gameObjects.get(0).getPosition().setX(gameObjects.get(0).getPosition().getX() - 1.8);
                    gameObjects.get(0).getPosition()
                            .setY(gameObjects.get(0).getPosition().getY() - 2);
                    if (epsilon.intersects(STARTING_POINT.x, STARTING_POINT.y, (int) Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH, 2)) {
                        gameObjects.get(0).getPosition().setY(gameObjects.get(0).getPosition().getY() + 2.2);

                    }
                    if (epsilon.intersects(STARTING_POINT.x, STARTING_POINT.y, 2, Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT)) {
                        gameObjects.get(0).getPosition().setX(gameObjects.get(0).getPosition().getX() + 2.2);

                    }
                    if (epsilon.intersects(Properties.getInstance().SECOND_FRAME_LOCATION_X, Properties.getInstance().SECOND_FRAME_LOCATION_Y, Properties.getInstance().GLASS_FRAME_DIMENSION_WIDTH / 2, 2)) {
                        gameObjects.get(0).getPosition().setY(gameObjects.get(0).getPosition().getY() + 2.2);

                    }
                    if (epsilon.intersects(Properties.getInstance().SECOND_FRAME_LOCATION_X, Properties.getInstance().SECOND_FRAME_LOCATION_Y, 2, Properties.getInstance().GLASS_FRAME_DIMENSION_HEIGHT / 2)) {
                        gameObjects.get(0).getPosition().setX(gameObjects.get(0).getPosition().getX() + 2.2);

                    }

                } else if (mainPanel.contains(epsilon) && !secondPanel.contains(epsilon)) {
                    BooleansOf_IsValidToShow.getInstance().getIsEpsilonIntoPanel().set(1, true);

                    System.out.println(true + " " + temp3.x);
                    if (controller.idRequestingEsc()) {
                        System.exit(4);
                    }
                    if (controller.idRequestingUp()) {
                        deltaY = -2;
                    }
                    if (controller.idRequestingDown()) {
                        deltaY += 2;
                    }
                    if ((controller.idRequestingRight())) {
                        deltaX += 2;
                    }
                    if (controller.idRequestingLeft()) {
                        deltaX = -2;
                    }


                    if ((gameObjects.get(0)).getPosition().getX() + EPSILON_WIDTH > panels.get(0).getRightX()) {
                        (gameObjects.get(0)).setPosition(new Position(panels.get(0).getRightX() - EPSILON_WIDTH, (gameObjects.get(0)).getPosition().getY()));
                    }
                    if ((gameObjects.get(0)).getPosition().getX() < panels.get(0).getX()) {
                        (gameObjects.get(0)).setPosition(new Position(panels.get(0).getX(), (gameObjects.get(0)).getPosition().getY()));
                    }
                    if ((gameObjects.get(0)).getPosition().getY() < panels.get(0).getY()) {
                        (gameObjects.get(0)).setPosition(new Position((gameObjects.get(0)).getPosition().getX(), panels.get(0).getY()));
                    }
                    if ((gameObjects.get(0)).getPosition().getY() + EPSILON_WIDTH > panels.get(0).getDownY()) {
                        (gameObjects.get(0)).setPosition(new Position((gameObjects.get(0)).getPosition().getX(), panels.get(0).getDownY() - EPSILON_WIDTH));
                    }


                }
            } else {
                if (controller.idRequestingUp()) {
                    deltaY = -2;
                }
                if (controller.idRequestingDown()) {
                    deltaY += 2;
                }
                if ((controller.idRequestingRight())) {
                    deltaX += 2;
                }
                if (controller.idRequestingLeft()) {
                    deltaX = -2;
                }

                if ((gameObjects.get(0)).getPosition().getX() + EPSILON_WIDTH > panels.get(0).getRightX()) {
                    (gameObjects.get(0)).setPosition(new Position(panels.get(0).getRightX() - EPSILON_WIDTH, (gameObjects.get(0)).getPosition().getY()));
                }
                if ((gameObjects.get(0)).getPosition().getX() < panels.get(0).getX()) {
                    (gameObjects.get(0)).setPosition(new Position(panels.get(0).getX(), (gameObjects.get(0)).getPosition().getY()));
                }
                if ((gameObjects.get(0)).getPosition().getY() < panels.get(0).getY()) {
                    (gameObjects.get(0)).setPosition(new Position((gameObjects.get(0)).getPosition().getX(), panels.get(0).getY()));
                }
                if ((gameObjects.get(0)).getPosition().getY() + EPSILON_WIDTH > panels.get(0).getDownY()) {
                    (gameObjects.get(0)).setPosition(new Position((gameObjects.get(0)).getPosition().getX(), panels.get(0).getDownY() - EPSILON_WIDTH));
                }


            }
            if (!mainPanel.intersects(secondPanel)) {
                if (!secondPanel.contains(epsilon)) {
                    if ((gameObjects.get(0)).getPosition().getX() + EPSILON_WIDTH > panels.get(0).getRightX()) {
                        (gameObjects.get(0)).setPosition(new Position(panels.get(0).getRightX() - EPSILON_WIDTH, (gameObjects.get(0)).getPosition().getY()));
                    }
                    if ((gameObjects.get(0)).getPosition().getX() < panels.get(0).getX()) {
                        (gameObjects.get(0)).setPosition(new Position(panels.get(0).getX(), (gameObjects.get(0)).getPosition().getY()));
                    }
                    if ((gameObjects.get(0)).getPosition().getY() < panels.get(0).getY()) {
                        (gameObjects.get(0)).setPosition(new Position((gameObjects.get(0)).getPosition().getX(), panels.get(0).getY()));
                    }
                    if ((gameObjects.get(0)).getPosition().getY() + EPSILON_WIDTH > panels.get(0).getDownY()) {
                        (gameObjects.get(0)).setPosition(new Position((gameObjects.get(0)).getPosition().getX(), panels.get(0).getDownY() - EPSILON_WIDTH));
                    }
                } else if (!mainPanel.contains(epsilon)) {
                    if ((gameObjects.get(0)).getPosition().getX() + EPSILON_WIDTH > panels.get(1).getRightX()) {
                        (gameObjects.get(0)).setPosition(new Position(panels.get(1).getRightX() - EPSILON_WIDTH, (gameObjects.get(0)).getPosition().getY()));
                    }
                    if ((gameObjects.get(0)).getPosition().getX() < panels.get(1).getX()) {
                        (gameObjects.get(0)).setPosition(new Position(panels.get(1).getX(), (gameObjects.get(0)).getPosition().getY()));
                    }
                    if ((gameObjects.get(0)).getPosition().getY() < panels.get(1).getY()) {
                        (gameObjects.get(0)).setPosition(new Position((gameObjects.get(0)).getPosition().getX(), panels.get(1).getY()));
                    }
                    if ((gameObjects.get(0)).getPosition().getY() + EPSILON_WIDTH > panels.get(1).getDownY()) {
                        (gameObjects.get(0)).setPosition(new Position((gameObjects.get(0)).getPosition().getX(), panels.get(1).getDownY() - EPSILON_WIDTH));
                    }
                }
            }
        }

//        else if(!(mainPanel.contains(epsilon))){
//            gameObjects.get(0).getPosition().setX(gameObjects.get(0).getPosition().getX()-1.8);
//            gameObjects.get(0).getPosition().setY(gameObjects.get(0).getPosition().getY()-2);
//            if(epsilon.intersects(STARTING_POINT.x, STARTING_POINT.y,(int) GLASS_FRAME_DIMENSION_WIDTH,2)){
//                gameObjects.get(0).getPosition().setY(gameObjects.get(0).getPosition().getY()+2.2);
//
//            }
//            if(epsilon.intersects(STARTING_POINT.x,STARTING_POINT.y,2,GLASS_FRAME_DIMENSION_HEIGHT)){
//                gameObjects.get(0).getPosition().setX(gameObjects.get(0).getPosition().getX()+2.2);
//
//            }
//        }else if(!(thirdPanel.contains(epsilon))){
//            if(epsilon.intersects(THIRD_FRAME_LOCATION_X, THIRD_FRAME_LOCATION_Y,GLASS_FRAME_DIMENSION_WIDTH/2,2)){
//                gameObjects.get(0).getPosition().setY(gameObjects.get(0).getPosition().getY()+2.2);
//
//            }
//            if(epsilon.intersects(THIRD_FRAME_LOCATION_X,THIRD_FRAME_LOCATION_Y,2,GLASS_FRAME_DIMENSION_HEIGHT/2)){
//                gameObjects.get(0).getPosition().setX(gameObjects.get(0).getPosition().getX()+2.2);
//
//            }
//        }

//        position = new Position(position.getX() + deltaX, position.getY()+deltaY);
        vector = new Vector2D(deltaX, deltaY);
        vector.normalize();
        vector.multiply(speed);
    }

    public Vector2D getVector() {
        return vector;
    }

//    public void setVector(Vector2D vector) {
//        this.vector = vector;
//        vector.normalize();
//
//    }
//
//    public double getSpeed() {
//        return speed;
//    }
//
//    public void setSpeed(double speed) {
//        this.speed = speed;
//    }
}
