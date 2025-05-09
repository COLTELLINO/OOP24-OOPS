package it.unibo.oop.model;

/**
 *
 */
public class Player extends Entity {
    private Direction direction;
    private int xp;
    private int level;
    private static final int LEVELUP_SCALER = 20;
    private final HealthManager healthManager;
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
        this.healthManager = new HealthManagerImpl(this);
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

        healthManager.update();
        if (!healthManager.isAlive()) {
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
}
