package third.all.gameComponents.game;

import third.all.data.Properties;
import third.all.data.booleans.BooleansOf_IsValidToShow;
import third.all.data.booleans.HelpingBooleans;
import third.all.gameComponents.preGameComponent.Timer1;
import third.all.model.normalEnemies.Archmire;
import third.all.model.normalEnemies.Wyrm;

import javax.swing.*;
import java.awt.*;


import static third.all.gameComponents.game.FunctionalMethods.*;

public class Timers {
    public static Timer time;
    public static Timer timer1;
    public static Timer1 timerOfGame;
    public static Timer shotTimer;
    public static Timer necropickShower;
    public static Timer necropickShooter;
    public static Timer wyrmShooter;
    public static Timer archmirePrintTimer;
    public static Timer addArchmirePrintTimer;
    public static Timer addBlackOrbsTimer;
    public static Timer barricadosValidatorTimer;
    public static Timer cerberusAttackTimer;
    public static Timer orbitalMovement;
    public static Timer handsShooter;
    public static Timer headShooter;
    public static Timer acesoTimer;
    public static Timer attackOfOrbsOnEpsilon;

    public Timers() {
        timerOfGame = new Timer1();
        necropickShower = new Timer(12000, e -> FunctionalMethods.getInstance().necropickHide());
//        necropickShower.start(); //todo: هرموقع وقتش شد استارتش کنم
        barricadosValidatorTimer = new Timer(120000, e -> {
            if (Properties.getInstance().barricadosMode == 1) {
                BooleansOf_IsValidToShow.getInstance().getIsValidToShowPanels().set(6, false);
                Properties.getInstance().barricadosMode += 1;
                barricadosValidatorTimer.stop();
            }
        });
        attackOfOrbsOnEpsilon = new Timer(1000, e -> Properties.getInstance().HP -= 12);
        shotTimer = new Timer(5000, e -> FunctionalMethods.getInstance().omenoct_shooter());

        headShooter = new Timer(1000, e -> {
            if (BooleansOf_IsValidToShow.getInstance().getIsValidToAttackBoss().get(5) && Properties.getInstance().headShooterSecondCounter > 0) {
                FunctionalMethods.headRapidFireShoot();
                Properties.getInstance().headShooterSecondCounter -= 1000;
            }

        });
//        headShooter.start(); //todo: هرموقع وقتش شد استارتش کنم
        handsShooter = new Timer(700, e -> {
            if (HelpingBooleans.getInstance().isOnOrbit) {
                rightHandShoot();
                leftHandShoot();
            }

//                if (Wyrm.getInstance().getHP() <= 0) wyrmShooter.stop();
        });
        wyrmShooter = new Timer(700, e -> {
            if (Wyrm.getInstance().isValidToShoot()) wyrmShoot();
            if (Wyrm.getInstance().getHP() <= 0) wyrmShooter.stop();
        });

//        wyrmShooter.start(); //todo: هرموقع وقتش شد استارتش کنم
        necropickShooter = new Timer(1000, e -> FunctionalMethods.getInstance().necropickShoot());
        orbitalMovement = new Timer(10, e -> {
            Properties.getInstance().angleOfOrbitProjectile += Properties.getInstance().speedOfOrbitProjectile;
            Properties.getInstance().angleOfOrbitWyrm += Properties.getInstance().speedOfOrbitWyrm;
            if (Properties.getInstance().angleOfOrbitProjectile >= 360) {
                Properties.getInstance().angleOfOrbitProjectile = 0;
            }
            if (Properties.getInstance().angleOfOrbitWyrm >= 360) {
                Properties.getInstance().angleOfOrbitWyrm = 0;
            }
        });
        orbitalMovement.start();
        archmirePrintTimer = new Timer(500, e -> {
            if (Archmire.getInstance().getTrails().size() > 2) {
                Archmire.getInstance().getTrails().remove(0);
                Archmire.getInstance().getTrails().remove(0);

            }
            if (!BooleansOf_IsValidToShow.getInstance().getIsValidToShowEnemies().get(0)) {
                archmirePrintTimer.stop();
            }
        });
        cerberusAttackTimer = new Timer(15000, e -> {
            BooleansOf_IsValidToShow.getInstance().setValidToAttackCerberus(true);
            HelpingBooleans.getInstance().cerberusBool = true;
        });
        addArchmirePrintTimer = new Timer(250, e -> Archmire.getInstance().getTrails().add(new Point(Archmire.getInstance().getLocation().x, Archmire.getInstance().getLocation().y)));

        addBlackOrbsTimer = new Timer(1000, e -> {
            Properties.getInstance().boHelper[0] -= 1000;
            if (Properties.getInstance().boHelper[1] < 5) {
                BooleansOf_IsValidToShow.getInstance().getIsValidToShowBlackOrbPanels().set(Properties.getInstance().boHelper[1], true);
                Properties.getInstance().boHelper[1]++;
            } else {
                Properties.getInstance().boHelper[1] = 0;
                addBlackOrbsTimer.stop();
            }
            if (Properties.getInstance().boHelper[1] == 5)
                BooleansOf_IsValidToShow.getInstance().getIsValidToShowBlackOrbPanels().set(5, true);

            if (Properties.getInstance().boHelper[0] <= 0) {
                addBlackOrbsTimer.stop();
            }
        });
        acesoTimer = new Timer(1000, e -> Properties.getInstance().HP += 1);


    }


}
