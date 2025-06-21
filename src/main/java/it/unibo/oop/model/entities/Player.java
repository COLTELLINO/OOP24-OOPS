package it.unibo.oop.model.entities;

import it.unibo.oop.utils.Direction;

/**
 *
 */
public class Player extends Entity {
    private Direction direction;
    private int xp;
    private int level = 1;
    private static final int LEVELUP_SCALER = 50;
    private static final int MAXBOUND = 5000;
    private static final int DEFAULT_PICKUP_RANGE = 40;
    private static final int DEFAULT_CRIT_DAMAGE = 150;
    private static final int MIN_CRIT_DAMAGE = 100;
    private int defense;
    private int pickupRange = DEFAULT_PICKUP_RANGE;
    private int critRate;
    private int critDamage = DEFAULT_CRIT_DAMAGE;
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param attack
     * @param speed
     * @param size
     */
    public Player(final int x, final int y, final int maxHealth, final int health, final int attack,
                  final int speed, final int size) {
        super(x, y, maxHealth, health, attack, speed, size);
        this.direction = Direction.NONE;
    }
    /**
     * @return the facing direction of the player.
     */
    public Direction getDirection() {
        return this.direction;
    }
    /**
     * @return the current experience value of the player.
     */
    public int getXP() {
        return this.xp;
    }
    /**
     * @return the current level of the player.
     */
    public int getLevel() {
        return this.level;
    }
    /**
     * Sets the facing direction of the player.
     * @param direction
     */
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }
    /**
     * Adds to the player experience count.
     * @param xp
     */
    public void addXp(final int xp) {
        this.xp += xp;
        while (this.xp >= getXPToNextLevel()) {
            this.xp -= getXPToNextLevel();
            levelUp();
        }
    }
    /**
     * Increases player level.
     */
    public void levelUp() {
        this.level++;
        this.xp = 0;
    }
    /**
     * Updates the direction of the player.
     */
    @Override
    public void update() {
        if (this.getHealth() <= 0) {
            setAlive(false);
        }
        if (!this.isAlive()) {
            return;
        }
        int dx = 0;
        int dy = 0;
        final int speed = getSpeed();
        boolean diagonal = false;

        switch (direction) {
            case UP:
                dy = -speed;
                break;
            case DOWN:
                dy = speed;
                break;
            case LEFT:
                dx = -speed;
                break;
            case RIGHT:
                dx = speed;
                break;
            case UPLEFT:
                dx = -speed;
                dy = -speed;
                diagonal = true;
                break;
            case UPRIGHT:
                dx = speed;
                dy = -speed;
                diagonal = true;
                break;
            case DOWNLEFT:
                dx = -speed;
                dy = speed;
                diagonal = true;
                break;
            case DOWNRIGHT:
                dx = speed;
                dy = speed;
                diagonal = true;
                break;
            case NONE:
                return;
            default:
                throw new IllegalArgumentException();
        }
        if (diagonal) {
            dx /= Math.sqrt(2);
            dy /= Math.sqrt(2);
        }

        final int newX = Math.max(-MAXBOUND, Math.min(getX() + dx, MAXBOUND));
        final int newY = Math.max(-MAXBOUND, Math.min(getY() + dy, MAXBOUND));
        dx = newX - getX();
        dy = newY - getY();
        setX(getX() + dx);
        setY(getY() + dy);
    }
    /**
     * @return the current amount of XP.
     */
    public int getCurrentXP() {
        return this.xp;
    }
    /**
     * @return the XP necessary for the next level.
     */
    public int getXPToNextLevel() {
        return 100 + (level * LEVELUP_SCALER);
    }

    /**
     * @return the defense value.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Sets the defense value.
     * @param defense the new defense value
     */
    public void setDefense(final int defense) {
        this.defense = defense;
    }

    /**
     * @return the pickup range.
     */
    public int getPickupRange() {
        return pickupRange;
    }

    /**
     * Sets the pickup range.
     * @param pickupRange the new pickup range
     */
    public void setPickupRange(final int pickupRange) {
        this.pickupRange = pickupRange;
    }

    /**
     * @return the critical hit rate (0-100).
     */
    public int getCritRate() {
        return critRate;
    }

    /**
     * Sets the critical hit rate (0-100).
     * @param critRate the new critical hit rate
     */
    public void setCritRate(final int critRate) {
        this.critRate = Math.min(100, Math.max(0, critRate));
    }

    /**
     * @return the critical hit damage percentage.
     */
    public int getCritDamage() {
        return critDamage;
    }

    /**
     * Sets the critical hit damage percentage (minimum 100).
     * @param critDamage the new critical hit damage
     */
    public void setCritDamage(final int critDamage) {
        this.critDamage = Math.max(MIN_CRIT_DAMAGE, critDamage);
    }

    /**
     * Sets the speed value.
     * @param speed the new speed value
     */
    @Override
    public void setSpeed(final int speed) {
        super.setSpeed(speed);
    }
    /**
     * Sets the attack value.
     * @param attack the new attack value
     */
    @Override
    public void setAttack(final int attack) {
        super.setAttack(attack);
    }
}
