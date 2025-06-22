package it.unibo.oop.model.entities;

import it.unibo.oop.utils.CountDownTimer;

/**
 * 
 */
public class Cultist extends AttackingEnemy {
    private static final int BASE_MAXHEALTH = 5;
    private static final int BASE_HEALTH = 5;
    private static final int BASE_ATTACK = 1;
    private static final int BASE_SPEED = 1;
    private static final int BASE_SIZE = 32;
    private static final int MIN_PLAYER_DISTANCE = 300;
    private final CountDownTimer countDownTimer = new CountDownTimer(120);
    /**
     * @param x
     * @param y
     * @param player
     * @return a Cultist enemy with base stats.
     */
    public static Enemy createDefault(final int x, final int y, final Player player) {
        return new Cultist(x, y, BASE_MAXHEALTH, BASE_HEALTH, BASE_ATTACK, BASE_SPEED, BASE_SIZE, player);
    }
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param attack
     * @param speed
     * @param size
     * @param player
     */
    public Cultist(final int x, final int y, final int maxHealth, final int health, final int attack, final int speed,
            final int size, final Player player) {
        super(x, y, maxHealth, health, attack, speed, size, player);
    }
    /**
     * Executes the enemy attack.
     */
    @Override
    protected void attacking() {
        if (!countDownTimer.isRunning()) {
            countDownTimer.reset();
            this.facePlayerAndGetDirection();
            observerAction();
        } else {
            countDownTimer.tick();
        }
        setAttacking(false);
    }
    /**
     * @return the minimum distance from the player to trigger the observer action.
     */
    @Override
    protected int getMinPlayerDistance() {
        return MIN_PLAYER_DISTANCE;
    }
}
