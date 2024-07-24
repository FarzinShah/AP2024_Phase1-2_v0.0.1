package third.all.data;

import third.all.data.booleans.BooleansOfEnemies;
import third.all.data.booleans.BooleansOf_IsValidToShow;

public class GameData2 {
    private TimerData timerData;
    private BooleansOf_IsValidToShow booleansOfIsValidToShow;
    private BooleansOfEnemies booleansOfEnemies;

    private GameData2(Builder builder) {
        this.timerData = builder.timerData;
        this.booleansOfIsValidToShow = builder.booleansOfIsValidToShow;
        this.booleansOfEnemies = builder.booleansOfEnemies;
    }

    public static class Builder {
        private TimerData timerData;
        private BooleansOf_IsValidToShow booleansOfIsValidToShow;
        private BooleansOfEnemies booleansOfEnemies;


        public void setTimerData(TimerData timerData) {
            this.timerData = timerData;
        }

        public void setBooleansOfIsValidToShow(BooleansOf_IsValidToShow booleansOfIsValidToShow) {
            this.booleansOfIsValidToShow = booleansOfIsValidToShow;
        }

        public void setBooleansOfEnemies(BooleansOfEnemies booleansOfEnemies) {
            this.booleansOfEnemies = booleansOfEnemies;
        }

        public GameData2 build() {
            return new GameData2(this);
        }
    }



    public TimerData getTimerData() {
        return timerData;
    }

    public void setTimerData(TimerData timerData) {
        this.timerData = timerData;
    }

    public BooleansOf_IsValidToShow getBooleansOfIsValidToShow() {
        return booleansOfIsValidToShow;
    }

    public void setBooleansOfIsValidToShow(BooleansOf_IsValidToShow booleansOfIsValidToShow) {
        this.booleansOfIsValidToShow = booleansOfIsValidToShow;
    }

    public BooleansOfEnemies getBooleansOfEnemies() {
        return booleansOfEnemies;
    }

    public void setBooleansOfEnemies(BooleansOfEnemies booleansOfEnemies) {
        this.booleansOfEnemies = booleansOfEnemies;
    }

    // گترها و سترها...
}
