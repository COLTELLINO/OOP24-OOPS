package it.unibo.oop.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
    /**
     * 
     */
    public abstract class Entity {
    private int x, y;         // Posizione
    private int maxHealth;    // Salute Massima
    private int health;          // Salute
    private int attack;
    private int size;        // Velocità
    private int speed;        // Velocità
    private boolean isAlive = true;     // Stato
    private Rectangle hitBox;
    private boolean showHitbox;
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param attack
     * @param speed
     * @param size
     */
    public Entity(final int x, final int y, final int maxHealth, final int health, final int attack,
            final int speed, final int size) {
        this.x = x;
        this.y = y;
        this.maxHealth = maxHealth;
        this.health = health;
        this.attack = attack;
        this.speed = speed;
        this.size = size;
        this.hitBox = new Rectangle(x, y, size, size);
    }
    /**
     * Draws an entity.
     * @param g
     */
    protected void draw(final Graphics2D g) {

    }
    /**
     * Updates the entity.
     */
    protected abstract void update();
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
    protected int getMaxHealth() {
        return maxHealth;
    }
    /**
     * @return the Entity's health value. 
     */
    public int getHealth() {
        return health;
    }
    /**
     * @return the Entity's attack value. 
     */
    protected int getAttack() {
        return attack;
    }
    /**
     * @return the Entity's speed value. 
     */
    protected int getSpeed() {
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
    protected boolean isAlive() {
        return isAlive;
    }
    /**
     * @return if the hitboxes are showed. 
     */
    public boolean isShowHitbox() {
        return showHitbox;
    }
    // Setter per la salute e altre variabili, se necessario
    /**
     * Sets the Entity's x position.
     * @param x 
     */
    protected void setX(final int x) {
        this.x = x;
        this.setHitbox(x, hitBox.y, hitBox.width, hitBox.height);
    }
    /**
     * Sets the Entity's y position. 
     * @param y
     */
    protected void setY(final int y) {
        this.y = y;
        this.setHitbox(hitBox.x, y, hitBox.width, hitBox.height);
    }
    /**
     * Sets the Entity's max health value. 
     * @param maxHealth
     */
    protected void setMaxHealth(final int maxHealth) {
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
     * Sets the Entity's attack value. 
     * @param attack
     */
    protected void setAttack(final int attack) {
        this.attack = attack;
    }
    /**
     * Sets the Entity's size value.
     * @param size
     */
    protected void setSize(final int size) {
        this.size = size;
        this.setHitbox(hitBox.x, hitBox.y, size, size);
    }
    /**
     * Sets the Entity's speed value.
     * @param speed
     */
    protected void setSpeed(final int speed) {
        this.speed = speed;
    }
    /**
     * Sets the Entity's alive status. 
     * @param isAlive
     */
    protected void setAlive(final boolean isAlive) {
        this.isAlive = isAlive;
    }
    /**
     * Shows hitboxes when true.
     * @param show
     */
    public void setShowHitbox(final boolean show) {
        this.showHitbox = show;
     }
    /**
     * Sets the Entity's hitbox.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void setHitbox(final int x, final int y, final int width, final int height) {
        this.hitBox = new Rectangle(x, y, width, height);
    }
    /**
     * @return the Entity's hitbox.
     */
    public Rectangle getHitbox() {
        return hitBox;
    }
}
