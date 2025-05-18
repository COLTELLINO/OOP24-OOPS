package it.unibo.oop.model.entities;
/**
 * 
 */
public abstract class AttackingEnemy extends Enemy {
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
    public AttackingEnemy(final int x, final int y, final int maxHealth, final int health, final int attack,
        final int speed, final int size, final Player player) {
        super(x, y, maxHealth, health, attack, speed, size, player);
    }
    /**
     * Updates current enemy.
     */
    @Override
    public void update() {
        super.onDeath();
        if (!isAttacking()) {
            if (getPlayerDistance() > getMinPlayerDistance()) {
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
    protected abstract void attacking();
    /**
     * @return the minimum distance from the player to trigger the observer action.
     */
    protected abstract int getMinPlayerDistance();
}
