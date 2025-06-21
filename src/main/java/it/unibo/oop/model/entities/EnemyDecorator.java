package it.unibo.oop.model.entities;

import java.awt.Rectangle;

import it.unibo.oop.model.managers.EnemyManagerImpl.EnemyObserver;
import it.unibo.oop.utils.Direction;

/**
 * 
 */
public abstract class EnemyDecorator extends Enemy {
    private final Enemy decoratedEnemy;
    /**
     * @param enemy
     */
    protected EnemyDecorator(final Enemy enemy) {
        super(0, 0, 0, 0, 0, 0, 0, null);
        this.decoratedEnemy = enemy;
    }
    /**
     * If an observer is present, trigger its action.
     */
    @Override
    protected void observerOnDeathAction() {
        decoratedEnemy.observerOnDeathAction();
    }
    /**
     * @return the enemy to decorate.
     */
    protected Enemy getDecoratedEnemy() {
        return this.decoratedEnemy;
    }
    /**
     * @return the name of the enemy class matching with its image.
     */
    @Override
    public String getEnemyName() {
        return decoratedEnemy.getEnemyName();
    }
    /**
     * @return the entity's X coordinate.
     */
    @Override
    public int getX() {
        return decoratedEnemy.getX();
    }
    /**
     * @return the Entity's Y coordinate
     */
    @Override
    public int getY() {
        return decoratedEnemy.getY();
    }
    /**
     * @return the Entity's max health value
     */
    @Override
    public int getMaxHealth() {
        return decoratedEnemy.getMaxHealth();
    }
    /**
     * @return the Entity's health value
     */
    @Override
    public int getHealth() {
        return decoratedEnemy.getHealth();
    }
    /**
     * @return the Entity's attack value
     */
    @Override
    public int getAttack() {
        return decoratedEnemy.getAttack();
    }
    /**
     * @return the Entity's speed value
     */
    @Override
    public int getSpeed() {
        return decoratedEnemy.getSpeed();
    }
    /**
     * @return the Entity's Size
     */
    @Override
    public int getSize() {
        return decoratedEnemy.getSize();
    }

    /**
     * @return the Entity's alive status
     */
    @Override
    public boolean isAlive() {
        return decoratedEnemy.isAlive();
    }
    /**
     * @return the Entity's hitbox.
     */
    @Override
    public Rectangle getHitbox() {
        return decoratedEnemy.getHitbox();
    }
    /**
     * @return if the hitboxes are shown
     */
    @Override
    public boolean isHitboxShown() {
        return decoratedEnemy.isHitboxShown();
    }
    /**
     * @return the scaling of the enemy size.
     */
    @Override
    public int getSizeScale() {
        return decoratedEnemy.getSizeScale();
    }
    /**
     * @return if the enemy started their death animation.
     */
    @Override
    public boolean isDying() {
        return decoratedEnemy.isDying();
    }
    /**
     * @return how many frame have passed since the enemy died.
     */
    @Override
    public int getBlinkCounter() {
        return decoratedEnemy.getBlinkCounter();
    }
    /**
     * @return the target player.
     */
    @Override
    protected Player getPlayer() {
        return decoratedEnemy.getPlayer();
    }
    /**
     * @return the direction of the decorated enemy.
     */
    @Override
    public Direction getDirection() {
        return decoratedEnemy.getDirection();
    }
    /**
     * @return if the decorated enemy is attacking and needs to change its animation.
     */
    @Override
    public boolean isAttacking() {
        return decoratedEnemy.isAttacking();
    }
    /**
     * Sets the x position of the decorated enemy.
     * @param x
     */
    @Override
    public void setX(final int x) {
        decoratedEnemy.setX(x);
    }
    /**
     * Sets the y position of the decorated enemy.
     * @param y
     */
    @Override
    public void setY(final int y) {
        decoratedEnemy.setY(y);
    }
    /**
     * Sets the max health value of the decorated enemy.
     * @param maxHealth
     */
    @Override
    protected void setMaxHealth(final int maxHealth) {
        decoratedEnemy.setMaxHealth(maxHealth);
    }
    /**
     * Sets the health value of the decorated enemy.
     * @param health
     */
    @Override
    public void setHealth(final int health) {
        decoratedEnemy.setHealth(health);
    }
    /**
     * Sets the attack value of the decorated enemy.
     * @param attack
     */
    @Override
    protected void setAttack(final int attack) {
        decoratedEnemy.setAttack(attack);
    }
    /**
     * Sets the size value of the decorated enemy.
     * @param size
     */
    @Override
    protected void setSize(final int size) {
        decoratedEnemy.setSize(size);
    }
    /**
     * Sets the speed value of the decorated enemy.
     * @param speed
     */
    @Override
    protected void setSpeed(final int speed) {
        decoratedEnemy.setSpeed(speed);
    }
    /**
     * Sets the alive status of the decorated enemy.
     * @param isAlive
     */
    @Override
    public void setAlive(final boolean isAlive) {
        decoratedEnemy.setAlive(isAlive);
    }
    /**
     * Shows hitboxes when true and debugMode is active.
     * @param show
     */
    @Override
    public void showHitbox(final boolean show) {
        decoratedEnemy.showHitbox(show);
    }
    /**
     * @param direction
     */
    @Override
    public void setDirection(final Direction direction) {
        decoratedEnemy.setDirection(direction);
    }
    /**
     * @param isAttacking
     */
    @Override
    protected void setAttacking(final boolean isAttacking) {
        decoratedEnemy.setAttacking(isAttacking);
    }
    /**
     * @param sizeScale
     */
    @Override
    protected void setSizeScale(final int sizeScale) {
        decoratedEnemy.setSizeScale(sizeScale);
    }
    /**
     * @param isDying
     */
    @Override
    protected void setIsDying(final boolean isDying) {
        decoratedEnemy.setIsDying(isDying);
    }
    /**
     * @param observer
     */
    @Override
    public void setOnDeathObserver(final EnemyObserver observer) {
        decoratedEnemy.setOnDeathObserver(observer);
    }
    /**
     * Updates the decorated enemy.
     */
    @Override
    public void update() {
        decoratedEnemy.update();
    }
}
