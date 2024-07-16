package third.all.data;

public class TimerData {
    private int delay;
    private int initialDelay;
    private boolean repeats;

    public TimerData(int delay, int initialDelay, boolean repeats) {
        this.delay = delay;
        this.initialDelay = initialDelay;
        this.repeats = repeats;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getInitialDelay() {
        return initialDelay;
    }

    public void setInitialDelay(int initialDelay) {
        this.initialDelay = initialDelay;
    }

    public boolean isRepeats() {
        return repeats;
    }

    public void setRepeats(boolean repeats) {
        this.repeats = repeats;
    }
}
