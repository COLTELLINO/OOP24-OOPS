package it.unibo.oop.utils;
/**
 * Class that handles cooldowns. 
 */
public class CountDownTimer {

    private int timeLeft;
    private final int duration;
    /**
     * @param duration
     */
    public CountDownTimer(final int duration) {
        this.duration = duration;
        start();
    }
    /**
     * Starts the timer.
     */
    private void start() {
        this.timeLeft = duration;
    }
    /**
     * Ticks the timer, reducing the cooldown by 1 unit.
     */
    public void tick() {
        if (timeLeft > 0) {
            timeLeft--;
        }
    }
    /**
     * @return if the timer is active.
     */
    public boolean isRunning() {
        return timeLeft > 0;
    }
    /**
     * Resets the timer to the starting value.
     */
    public void reset() {
        timeLeft = duration;
    }
}
