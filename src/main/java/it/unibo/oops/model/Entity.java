package it.unibo.oops.model;

import java.awt.Graphics2D;
    /**
     * 
     */
    public abstract class Entity {
    private int x, y;         // Posizione
    private int maxHealth;    // Salute Massima
    private int health;          // Salute
    private int size;        // Velocità
    private int speed;        // Velocità
    private boolean isAlive;     // Stato
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param speed
     * @param size
     */
    public Entity(final int x, final int y, final int maxHealth, final int health, final int speed, final int size) {
        this.x = x;
        this.y = y;
        this.maxHealth = maxHealth;
        this.health = health;
        this.speed = speed;
        this.size = size;
        this.isAlive = true;
    }
    /**
     * Draws an entity.
     * @param g
     */
    public abstract void draw(Graphics2D g);
    /**
     * Updates the entity.
     */
    public abstract void update();
    /**
     * @return the entity's X coordinate.
     */
    public int getX() {
        return x;
    }
    /**
     * @return the Entity's Y coordinate. 
     */
    public int getY() {
        return y;
    }
    /**
     * @return the Entity's max health value. 
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    /**
     * @return the Entity's health value. 
     */
    public int getHealth() {
        return health;
    }
    /**
     * @return the Entity's speed value. 
     */
    public int getSpeed() {
        return speed;
    }
    /**
     * @return the Entity's Size. 
     */
    public int getSize() {
        return size;
    }
    /**
     * @return the Entity's alive status. 
     */
    public boolean isAlive() {
        return isAlive;
    }

    // Setter per la salute e altre variabili, se necessario
    /**
     * Sets the Entity's x position.
     * @param x 
     */
    public void setX(final int x) {
        this.x = x;
    }
    /**
     * Sets the Entity's y position. 
     * @param y
     */
    public void setY(final int y) {
        this.y = y;
    }
    /**
     * Sets the Entity's max health value. 
     * @param maxHealth
     */
    public void setMaxHealth(final int maxHealth) {
        this.maxHealth = maxHealth;
    }
    /**
     * Sets the Entity's health value. 
     * @param health
     */
    public void setHealth(final int health) {
        this.health = health;
    }
    /**
     * Sets the Entity's size value.
     * @param size
     */
    public void setSize(final int size) {
        this.size = size;
    }
    /**
     * Sets the Entity's speed value.
     * @param speed
     */
    public void setSpeed(final int speed) {
        this.speed = speed;
    }
    /**
     * Sets the Entity's alive status. 
     * @param isAlive
     */
    public void setAlive(final boolean isAlive) {
        this.isAlive = isAlive;
    }
}
