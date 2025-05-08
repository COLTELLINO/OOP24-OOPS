package it.unibo.oop.model;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.controller.GameThreadImpl.EnemyObserver;
/**
 *
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "To move enemies towards the player, its position is needed, " 
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public abstract class Enemy extends Entity {
    private static final int MAX_BLINKS = 30;
    private Direction direction;
    private boolean isAttacking;
    private int sizeScale = 1;
    private boolean isDying;
    private int blinkCounter;
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
        this.direction = Direction.DOWN;
    }
    /**
     * @return the name of the enemy class matching with its image.
     */
    public abstract String getEnemyName();
    /**
     * Updates the current enemy.
     */
    @Override
    protected void update() {
        this.onDeath();
        move();
    }
    /**
     * 
     */
    protected void move() {
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
            if (xDistance == 1) {
                setDirection(Direction.RIGHT);
            } else if (xDistance == -1) {
                setDirection(Direction.LEFT);
            } else if (yDistance == 1) {
                setDirection(Direction.DOWN);
            } else if (yDistance == -1)  {
                setDirection(Direction.UP);
            }
        }
    }
    /**
     * @return the euclidean distance between the enemy and the player. 
     */
    protected int getPlayerDistance() {
        final int xDistance = player.getX() + (player.getSize() / 2) - this.getX() + this.getSize() / 2;
        final int yDistance = player.getY() + (player.getSize() / 2) - this.getY() + this.getSize() / 2;
        return (int) Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }
    /**
     * Handles what happens when the enemy dies.
     */
    protected void onDeath() {
        if (getHealth() <= 0) {
            this.setAttack(0);
            setIsDying(true);
            if (this.blinkCounter <= MAX_BLINKS) {
                this.blinkCounter++;
            } else {
                setAlive(false);
                this.observerOnDeathAction();
            }
        }
    }
    /**
     * If an observer is present, trigger its action.
     */
    protected void observerOnDeathAction() {
        observer.ifPresent(EnemyObserver::enemyObserverAction);
    }
    /**
     * @return the direction of the enemy.
     */
    public Direction getDirection() {
        return this.direction;
    }
    /**
     * @return the direction of the enemy.
     */
    public Direction getOppositeDirection() {
        if (this.direction == Direction.UP) {
            return Direction.DOWN;
        } else if (this.direction == Direction.RIGHT) {
            return Direction.LEFT;
        } else if (this.direction == Direction.DOWN) {
            return Direction.UP;
        } else {
            return Direction.RIGHT;
        }
    }
    /**
     * @return if the enemy is attacking and needs to change its animation.
     */
    public boolean isAttacking() {
        return this.isAttacking;
    }
    /**
     * @return the scaling of the enemy size.
     */
    public int getSizeScale() {
        return this.sizeScale;
    }
    /**
     * @return if the enemy started their death animation.
     */
    public boolean isDying() {
        return this.isDying;
    }
    /**
     * @return how many frame have passed since the enemy died.
     */
    public int getBlinkCounter() {
        return this.blinkCounter;
    }
    /**
     * @return the target player.
     */
    protected Player getPlayer() {
        return this.player;
    }
    /**
     * @param direction
     */
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }
    /**
     * @param isAttacking
     */
    protected void setAttacking(final boolean isAttacking) {
        this.isAttacking = isAttacking;
    }
    /**
     * @param sizeScale
     */
    protected void setSizeScale(final int sizeScale) {
        this.sizeScale = sizeScale;
    }
    /**
     * @param isDying
     */
    protected void setIsDying(final boolean isDying) {
        this.isDying = isDying;
    }
    /**
     * @param observer
     */
    public void setDeathObserver(final EnemyObserver observer) {
        this.observer = Optional.of(observer);
    }
}
