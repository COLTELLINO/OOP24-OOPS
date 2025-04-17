package it.unibo.oops.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
/**
 * 
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "To move enemies towards the player, its position is needed, " 
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public abstract class Enemy extends Entity {
    private boolean isSpawned;
    private final Player player;
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
    public Enemy(final int x, final int y, final int maxHealth, final int health, final int attack, final int speed,
            final int size, final Player player) {
        super(x, y, maxHealth, health, attack, speed, size);
        this.player = player;
    }
    /**
     * @return the name of the enemy class matching with its image.
     */
    public abstract String getEnemyName();
        /**
     * Updates current enemy.
     */
    @Override
    public void update() {
        final int playerCenterX = player.getX() + player.getSize() / 2;
        final int playerCenterY = player.getY() + player.getSize() / 2;
        for (int i = 0; i < getSpeed(); i++) {
            final int enemyCenterX = this.getX() + this.getSize() / 2;
            final int enemyCenterY = this.getY() + this.getSize() / 2;
            final int xDistance = Integer.compare(playerCenterX, enemyCenterX);
            final int yDistance = Integer.compare(playerCenterY, enemyCenterY); 
            if (enemyCenterX == playerCenterX && enemyCenterY == playerCenterY) {
                player.setHealth(player.getHealth() - this.getAttack());
                return;
            }
            this.setX(getX() + xDistance);
            this.setY(getY() + yDistance);
        }
    }
    /**
     * @return if the enemy has been positioned.
     */
    protected boolean isSpawned() {
        return isSpawned;
    }
    /**
     * @return the size scaling of the enemy.
     */
    public int getSizeScale() {
        return 1;
    }
    /**
     * @return the target player.
     */
    protected Player getPlayer() {
        return this.player;
    }
    /**
     * @param isSpawned
     */
    protected void setSpawned(final boolean isSpawned) {
        this.isSpawned = isSpawned;
    }
}
