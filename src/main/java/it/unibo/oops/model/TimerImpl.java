package it.unibo.oops.model;
/**
 * 
 */
public class TimerImpl implements Timer {
    private static final double FPS = 60.0;
    private static final long SECOND = 1_000_000_000;
    private long lastTime;
    private long delta;
    private final long nanoFps;
    /**
     * @param timerExtender
     */
    public TimerImpl(final int timerExtender) {
        this.nanoFps = (long) (SECOND / FPS * timerExtender);
        this.lastTime = System.nanoTime();
        this.delta = 0;
    }
    /**
     * Updates the timer based on FPS.
     * @param updateLogic makes lambda function possible.
     */
    @Override
    public void update(final Runnable updateLogic) {
        final long currentTime = System.nanoTime();
        delta += currentTime - lastTime;
        lastTime = currentTime;

        while (delta >= nanoFps) {
            updateLogic.run();
            delta -= nanoFps;
        }
    }
    /**
     *  @return frames per second
     */
    @Override
    public double getFps() {
        return FPS;
    }
}
