package it.unibo.oop.model;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a Magic Staff weapon in the game.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the weapon, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class MagicStaff extends Weapon {
    private static final double COOLDOWN = 80;
    private static final int SPEED = 3;
    private static final Logger LOGGER = Logger.getLogger(MagicStaff.class.getName());

    private double cooldown;
    private final Player player;
    private final Image staffImage;
    private final List<Projectile> projectiles;
    private Direction direction = Direction.UP;
    private Direction lastDirection = Direction.UP;

    /**
     * Constructs a MagicStaff object.
     * 
     * @param player the player associated with the staff
     */
    public MagicStaff(final Player player) {
        this.player = player;
        this.cooldown = 0;
        this.projectiles = new ArrayList<>();
        try {
            this.staffImage = ImageIO.read(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Weapon/MagicStaff.png"),
                "Resource 'Weapon/MagicStaff.png' not found."
            ));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Magic Staff image could not be loaded.", e);
            throw new IllegalStateException("Magic Staff image could not be loaded.", e);
        }
    }

    /**
     * Updates the staff's state.
     */
    @Override
    public void update() {
        if (cooldown <= 0) {
            shoot();
            cooldown = COOLDOWN;
        } else {
            cooldown--;
        }
        direction = player.getDirection();
        if (direction == Direction.RIGHT || direction == Direction.LEFT 
        || direction == Direction.UP || direction == Direction.DOWN) {
            lastDirection = direction;
        } else {
            direction = lastDirection;
        }
        projectiles.forEach(Projectile::update);
        projectiles.removeIf(Projectile::isOutOfBounds);
    }

    /**
     * Shoots a projectile in the direction the player is facing.
     */
    private void shoot() {
        projectiles.add(new Projectile(player.getX(), player.getY(), direction, SPEED));
    }

    /**
     * Returns the staff image.
     * 
     * @return the staff image
     */
    public Image getStaffImage() {
        return staffImage;
    }

    /**
     * Returns the list of active projectiles.
     * 
     * @return the list of projectiles
     */
    public List<Projectile> getProjectiles() {
        return new ArrayList<>(projectiles);
    }
}
