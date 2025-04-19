package it.unibo.oops.model;

import it.unibo.oops.controller.GameThreadImpl.EnemyObserver;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
/**
 *
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "To move enemies towards the player, its position is needed, " 
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public abstract class Enemy extends Entity {
    private boolean isSpawned;
    private boolean isBoss;
    private int sizeScale = 1;
    private final Player player;
    private Optional<EnemyObserver> observer = Optional.empty();
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
        return this.isSpawned;
    }
    /**
     * @return if the enemy is a Boss. 
     */
    protected boolean isBoss() {
        return this.isBoss;
    }
    /**
     * @return the scaling of the enemy size.
     */
    public int getSizeScale() {
        return this.sizeScale;
    }
    /**
     * @return the target player.
     */
    protected Player getPlayer() {
        return this.player;
    }
    /**
     * If an observer is present, trigger its action.
     */
    protected void observerAction() {
        observer.ifPresent(EnemyObserver::enemyObserverAction);
    }
    /**
     * @param isSpawned
     */
    protected void setSpawned(final boolean isSpawned) {
        this.isSpawned = isSpawned;
    }
    /**
     * @param isBoss
     */
    protected void setBoss(final boolean isBoss) {
        this.isBoss = isBoss;
    }
    /**
     * @param sizeScale
     */
    protected void setSizeScale(final int sizeScale) {
        this.sizeScale = sizeScale;
    }
    /**
     * @param observer
     */
    public void setObserver(final EnemyObserver observer) {
        this.observer = Optional.of(observer);
    }
}
