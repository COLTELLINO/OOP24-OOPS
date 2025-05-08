package it.unibo.oop.model;

/**
 * 
 */
public class Skull extends Enemy {
    private static final int BASE_MAXHEALTH = 200;
    private static final int BASE_HEALTH = 200;
    private static final int BASE_ATTACK = 5;
    private static final int BASE_SPEED = 1;
    private static final int BASE_SIZE = 32;
    private static final int MIN_PLAYER_DISTANCE = 100;
    //private static final int PROJECTILE_SPEED = 2;
    //private final Projectile projectile = new Projectile(getX(), getY(), getOppositeDirection(), PROJECTILE_SPEED);
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
     * @return the name of the enemy class matching with its image.
     */
    @Override
    public String getEnemyName() {
        return this.getClass().getSimpleName();
    }
    /**
     * Updates current enemy.
     */
    @Override
    public void update() {
        super.onDeath();
        if (!isAttacking()) {
            if (getPlayerDistance() > MIN_PLAYER_DISTANCE) {
                super.move();
            } else {
                setAttacking(true);
            }
        } else {
            attacking();
        }
    }
    /**
     * Executes the enemy attack.
     */
    private void attacking() {
        if (!countDownTimer.isRunning()) {
            countDownTimer.reset();
        } else {
            countDownTimer.tick();
        }
        setAttacking(false);
    }
}
