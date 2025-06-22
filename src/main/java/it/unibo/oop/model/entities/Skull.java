package it.unibo.oop.model.entities;

import it.unibo.oop.model.projectiles.Bone;
import it.unibo.oop.utils.CountDownTimer;

/**
 * 
 */
public class Skull extends AttackingEnemy {
    private static final int BASE_MAXHEALTH = 10;
    private static final int BASE_HEALTH = 10;
    private static final int BASE_ATTACK = 2;
    private static final int BASE_SPEED = 1;
    private static final int BASE_SIZE = 32;
    private static final int MIN_PLAYER_DISTANCE = 200;
    private static final int PROJECTILE_SPEED = 2;
    private static final int PROJECTILE_SIZE = 32;
    private final CountDownTimer countDownTimer = new CountDownTimer(60);
    /**
     * @param x
     * @param y
     * @param player
     * @return a Skull enemy with base stats.
     */
    public static Enemy createDefault(final int x, final int y, final Player player) {
        return new Skull(x, y, BASE_MAXHEALTH, BASE_HEALTH, BASE_ATTACK, BASE_SPEED, BASE_SIZE, player);
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
    public Skull(final int x, final int y, final int maxHealth, final int health, final int attack, final int speed,
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
            this.setProjectile(new Bone(getX(), getY(), getDirection(),
                getAttack(), PROJECTILE_SPEED, PROJECTILE_SIZE, getSize()));
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
