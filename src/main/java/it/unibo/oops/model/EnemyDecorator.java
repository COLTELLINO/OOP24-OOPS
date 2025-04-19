package it.unibo.oops.model;

import it.unibo.oops.controller.GameThreadImpl.EnemyObserver;

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
    protected int getMaxHealth() {
        return decoratedEnemy.getMaxHealth();
    }
    /**
     * @return the Entity's health value
     */
    @Override
    protected int getHealth() {
        return decoratedEnemy.getHealth();
    }
    /**
     * @return the Entity's attack value
     */
    @Override
    protected int getAttack() {
        return decoratedEnemy.getAttack();
    }
    /**
     * @return the Entity's speed value
     */
    @Override
    protected int getSpeed() {
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
    protected boolean isAlive() {
        return decoratedEnemy.isAlive();
    }

    /**
     * @return if the hitboxes are shown
     */
    @Override
    public boolean isShowHitbox() {
        return decoratedEnemy.isShowHitbox();
    }
    /**
     * @return the scaling of the enemy size.
     */
    @Override
    public int getSizeScale() {
        return decoratedEnemy.getSizeScale();
    }
    /**
     * @return the target player.
     */
    @Override
    protected Player getPlayer() {
        return decoratedEnemy.getPlayer();
    }
    /**
     * @return if the enemy is a Boss. 
     */
    @Override
    protected boolean isBoss() {
        return decoratedEnemy.isBoss();
    }
    /**
     * If an observer is present, trigger its action.
     */
    @Override
    protected void observerAction() {
        decoratedEnemy.observerAction();
    }
    /**
     * Sets the Entity's x position.
     * @param x The x coordinate
     */
    @Override
    protected void setX(final int x) {
        decoratedEnemy.setX(x);
    }
    /**
     * Sets the Entity's y position.
     * @param y The y coordinate
     */
    @Override
    protected void setY(final int y) {
        decoratedEnemy.setY(y);
    }
    /**
     * Sets the Entity's max health value.
     * @param maxHealth The max health value
     */
    @Override
    protected void setMaxHealth(final int maxHealth) {
        decoratedEnemy.setMaxHealth(maxHealth);
    }
    /**
     * Sets the Entity's health value.
     * @param health The health value
     */
    @Override
    protected void setHealth(final int health) {
        decoratedEnemy.setHealth(health);
    }
    /**
     * Sets the Entity's attack value.
     * @param attack The attack value
     */
    @Override
    protected void setAttack(final int attack) {
        decoratedEnemy.setAttack(attack);
    }
    /**
     * Sets the Entity's size value.
     * @param size The size value
     */
    @Override
    protected void setSize(final int size) {
        decoratedEnemy.setSize(size);
    }
    /**
     * Sets the Entity's speed value.
     * @param speed The speed value
     */
    @Override
    protected void setSpeed(final int speed) {
        decoratedEnemy.setSpeed(speed);
    }
    /**
     * Sets the Entity's alive status.
     * @param isAlive The alive status
     */
    @Override
    protected void setAlive(final boolean isAlive) {
        decoratedEnemy.setAlive(isAlive);
    }
    /**
     * Shows hitboxes when true.
     * @param show Whether to show hitboxes
     */
    @Override
    protected void setShowHitbox(final boolean show) {
        decoratedEnemy.setShowHitbox(show);
    }
    /**
     * @param isSpawned
     */
    @Override
    protected void setSpawned(final boolean isSpawned) {
        decoratedEnemy.setSpawned(isSpawned);
    }
    /**
     * @param isBoss
     */
    @Override
    protected void setBoss(final boolean isBoss) {
        decoratedEnemy.setBoss(isBoss);
    }
    /**
     * @param sizeScale
     */
    @Override
    protected void setSizeScale(final int sizeScale) {
        decoratedEnemy.setSizeScale(sizeScale);
    }
    /**
     * @param observer
     */
    @Override
    public void setObserver(final EnemyObserver observer) {
        decoratedEnemy.setObserver(observer);
    }
    /**
     * Updates current enemy.
     */
    @Override
    public void update() {
        this.decoratedEnemy.update();
    }
}
