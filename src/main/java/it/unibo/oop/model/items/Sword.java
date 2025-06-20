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
    private static final int DAMAGE = 3;
    private static final double DURATION = 30;
    private static final double COOLDOWN = 60;
    private static final int SIZE = 70;

    private double duration;
    private Direction direction = Direction.RIGHT;
    private double cooldown;
    private boolean active;
    private final Player player;
    private static final int SIZESCALER = 1;
    private static final int DAMAGESCALER = 1;
    private boolean lastDirectionRight = true;

    /**
     * Constructs a Sword object.
     * 
     * @param player the player associated with the sword
     */
    public Sword(final Player player) {
        super(player);
        this.player = player;
        this.active = false;
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
                hitbox.add(new Rectangle(player.getX() - SIZE * (getLevel() / SIZESCALER), 
                player.getY(), SIZE * (getLevel() / SIZESCALER), player.getSize()));
                return hitbox;
            case RIGHT:
                hitbox.add(new Rectangle(player.getX() + player.getSize(), player.getY(), 
                SIZE * (getLevel() / SIZESCALER), player.getSize()));
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
     * @return the sword's base damage.
     */
    @Override
    protected int getBaseDamage() {
        return DAMAGE + ((getLevel() - 1) * DAMAGESCALER);
    }

    /**
     * Handles the weapon collision.
     */
    @Override
    public void handleCollision() {
        //unused for the sword.
    }

    /**
    * Gets the size of the sword based on its level and scaler.
    * 
    * @return the size of the sword
    */
    public int getSize() {
        return SIZE * (getLevel() / SIZESCALER);
    }
}
