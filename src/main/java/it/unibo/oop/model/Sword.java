package it.unibo.oop.model;

import java.awt.Image;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a Sword weapon in the game.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the weapon, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class Sword extends Weapon {
    //private static final int DAMAGE = 50;
    private static final double DURATION = 30;
    private static final double COOLDOWN = 60;
    private static final int SIZE = 50;
    private static final Logger LOGGER = Logger.getLogger(Sword.class.getName());

    private double duration;
    private Direction direction;
    private double cooldown;
    private boolean active;
    private final Player player;
    private boolean lastDirectionRight = true;
    private final Image swordImage;

    /**
     * Constructs a Sword object.
     * 
     * @param player the player associated with the sword
     */
    public Sword(final Player player) {
        this.active = false;
        this.player = player;
        try {
            System.out.println("Loading Sword.png: " + ClassLoader.getSystemResource("Weapon/Sword.png"));

            this.swordImage = ImageIO.read(Objects.requireNonNull(
                getClass().getClassLoader().getResource("/Weapon/Sword.png"),
                "Resource 'Weapon/Sword.png' not found."
            ));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Sword image could not be loaded.", e);
            throw new IllegalStateException("Sword image could not be loaded.", e);
        }
    }

    /**
     * Gets the hitbox of the sword.
     * 
     * @return the hitbox of the sword
     */
    public Hitbox getHitbox() {
        switch (direction) {
            case UP:
                return new Hitbox(player.getX(), player.getY() - SIZE, SIZE, SIZE);
            case DOWN:
                return new Hitbox(player.getX(), player.getY() + SIZE, SIZE, SIZE);
            case LEFT:
                return new Hitbox(player.getX() - SIZE, player.getY(), SIZE, SIZE);
            case RIGHT:
                return new Hitbox(player.getX() + player.getSize(), player.getY(), SIZE, SIZE);
            default:
                return null;
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
     * Returns the sword image.
     * 
     * @return the sword image
     */
    public Image getSwordImage() {
        return swordImage;
    }
}
