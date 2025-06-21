package it.unibo.oop.utils;
/**
 * Class that handles cooldowns. 
 */
public class CountDownTimer {

    private static final int SECONDS_IN_MINUTE = 60;
    private int timeLeft;
    private final int duration;
    private int totalSeconds;
    private int seconds;
    private int minutes;
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
        totalSeconds++;
        seconds++;
        if (seconds >= SECONDS_IN_MINUTE) {
            seconds = 0;
            minutes++;
        }
    }
    /**
     * @return seconds that have passed.
     */
    public int getSeconds() {
        return seconds;
    }
    /**
     * @return the minutes that passed.
     */
    public int getMinutes() {
        return minutes; 
    }
    /**
     * @return the total amount of seconds that passed.
     */
    public int getTotalSeconds() {
        return totalSeconds;
    }
}
