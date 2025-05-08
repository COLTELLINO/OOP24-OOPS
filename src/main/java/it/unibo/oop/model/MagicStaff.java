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
    //private static final int DAMAGE = 100;
    private static final double COOLDOWN = 80;
    private static final int SPEED = 3;
    private static final int PROJECTILE_SIZE = 50;
    private static final int EXPLOSION_SIZE = 100;
    private static final Logger LOGGER = Logger.getLogger(MagicStaff.class.getName());

    private double cooldown;
    private final Player player;
    private final Image staffImage;
    private final List<Projectile> projectiles;
    private final List<Hitbox> explosionHitboxes;
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
        this.explosionHitboxes = new ArrayList<>();
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
        || direction == Direction.DOWN || direction == Direction.UP) {
            lastDirection = direction;
        } else {
            direction = lastDirection;
        }

        projectiles.forEach(Projectile::update);
        projectiles.removeIf(this::handleCollision);
        projectiles.removeIf(Projectile::isOutOfBounds);
    }

    /**
     * Gestisce la collisione di un proiettile.
     * 
     * @param projectile il proiettile da verificare
     * @return true se il proiettile deve essere rimosso, false altrimenti
     */
    private boolean handleCollision(final Projectile projectile) {
        final boolean hasCollided = false; // Temporaneo, manca la logica di collisione

        if (hasCollided) {
            explosionHitboxes.add(new Hitbox(
                projectile.getX() - (EXPLOSION_SIZE - PROJECTILE_SIZE) / 2,
                projectile.getY() - (EXPLOSION_SIZE - PROJECTILE_SIZE) / 2,
                EXPLOSION_SIZE,
                EXPLOSION_SIZE
            ));
            return true;
        }
        return false;
    }

    /**
     * Gets the hitboxes of all active projectiles.
     * 
     * @return a list of hitboxes for the active projectiles
     */
    public List<Hitbox> getProjectileHitboxes() {
        final List<Hitbox> hitboxes = new ArrayList<>();
        for (final Projectile projectile : projectiles) {
            hitboxes.add(new Hitbox(projectile.getX(), projectile.getY(), PROJECTILE_SIZE, PROJECTILE_SIZE));
        }
        return hitboxes;
    }

    /**
     * Gets all active explosion hitboxes.
     * 
     * @return a list of explosion hitboxes
     */
    public List<Hitbox> getExplosionHitboxes() {
        final List<Hitbox> hitboxList = new ArrayList<>();
        for (final Hitbox hitbox : explosionHitboxes) {
            hitboxList.add(new Hitbox(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight()));
        }
        return hitboxList;
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
