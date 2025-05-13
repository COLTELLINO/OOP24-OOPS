package it.unibo.oop.model.items;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.utils.Direction;

/**
 * Represents a Sword weapon in the game.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the weapon, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class Sword extends Weapon {
    private static final int DAMAGE = 200;
    private static final double DURATION = 30;
    private static final double COOLDOWN = 60;
    private static final int SIZE = 70;

    private double duration;
    private Direction direction = Direction.RIGHT;
    private double cooldown;
    private boolean active;
    private final Player player;
    private boolean lastDirectionRight = true;
    private boolean showHitbox;
    private int level;

    /**
     * Constructs a Sword object.
     * 
     * @param player the player associated with the sword
     */
    public Sword(final Player player) {
        super(player);
        this.player = player;
        this.active = false;
        this.level = 1;
    }

    /**
     * Gets the hitbox of the sword.
     * 
     * @return the hitbox of the sword
     */
    @Override
    public List<Rectangle> getHitBox() {
        final List<Rectangle> hitbox = new ArrayList<>();
        if (!active) {
            return List.of();
        }
        switch (direction) {
            case LEFT:
                hitbox.add(new Rectangle(player.getX() - SIZE, player.getY(), SIZE, player.getSize()));
                return hitbox;
            case RIGHT:
                hitbox.add(new Rectangle(player.getX() + player.getSize(), player.getY(), SIZE, player.getSize()));
                return hitbox;
            default:
                return List.of();
        }
    }

    /**
     * Updates the sword's state.
     */
    @Override
    public void update() {
        if (active) {
            duration--;
            if (duration <= 0) {
                active = false;
                this.cooldown = COOLDOWN;
                this.duration = DURATION;
            }
        } else {
            if (cooldown <= 0) {
                this.active = true;
            } else {
                cooldown--;
            }
        }
        this.direction = player.getDirection();
        if (this.direction == Direction.NONE || this.direction == Direction.UP || this.direction == Direction.DOWN) {
            this.direction = lastDirectionRight ? Direction.RIGHT : Direction.LEFT;
        } else if (this.direction == Direction.UPRIGHT || this.direction == Direction.DOWNRIGHT) {
            this.direction = Direction.RIGHT;
        } else if (this.direction == Direction.UPLEFT || this.direction == Direction.DOWNLEFT) {
            this.direction = Direction.LEFT;
        } else if (this.direction == Direction.RIGHT) {
            this.lastDirectionRight = true;
        } else if (this.direction == Direction.LEFT) {
            this.lastDirectionRight = false;
        }
    }

    /**
     * Returns whether the sword is active.
     * 
     * @return true if the sword is active, false otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns the player associated with the sword.
     * 
     * @return the player
     */
    @Override
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the direction of the sword.
     * 
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Gets the level of the sword.
     * 
     * @return the level of the sword
     */
    @Override
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the sword.
     * 
     * @param level the new level of the sword
     */
    @Override
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     * @return the sword's damage.
     */
    @Override
    public int getDamage() {
        return DAMAGE;
    }

    /**
     * @param showHitbox the visibility of the hitbox.
     */
    @Override
    public void setShowHitbox(final boolean showHitbox) {
        this.showHitbox = showHitbox;
    }

    /**
     * @return the visibility of the hitbox.
     */
    @Override
    public boolean isShowHitbox() {
        return showHitbox;
    }
}
