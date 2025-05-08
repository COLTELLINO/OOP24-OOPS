package it.unibo.oop.model;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a Magic Staff weapon in the game.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the weapon, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class MagicStaff extends Weapon {
    private static final int DAMAGE = 100;
    private static final double COOLDOWN = 80;
    private static final int SPEED = 3;
    private static final int PROJECTILE_SIZE = 30;
    private static final int EXPLOSION_SIZE = 200;
    private static final Logger LOGGER = Logger.getLogger(MagicStaff.class.getName());

    private double cooldown;
    private final Player player;
    private final Image staffImage;
    private final List<Projectile> projectiles;
    private final List<Rectangle> explosionHitboxes;
    private Direction direction = Direction.UP;
    private Direction lastDirection = Direction.UP;
    private boolean showHitbox;

    /**
     * Constructs a MagicStaff object.
     * 
     * @param player the player associated with the staff
     */
    public MagicStaff(final Player player) {
        super(player);
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
        projectiles.removeIf(Projectile::isOutOfBounds);
    }

    /**
     * Gestisce la collisione di un proiettile.
     * 
     * @param projectile il proiettile da verificare.
     */
    public void handleCollision(final Projectile projectile) {
        explosionHitboxes.add(new Rectangle(
        projectile.getX() - (EXPLOSION_SIZE - PROJECTILE_SIZE) / 2,
        projectile.getY() - (EXPLOSION_SIZE - PROJECTILE_SIZE) / 2,
        EXPLOSION_SIZE, EXPLOSION_SIZE));
    }

    /**
     * Gets the hitboxes of all active projectiles.
     * 
     * @return a list of hitboxes for the active projectiles
     */
    @Override
    public List<Rectangle> getHitBox() {
        return projectiles.stream().map(Projectile::getHitBox)
        .collect(Collectors.toList());
    }

    /**
     * Gets all active explosion hitboxes.
     * 
     * @return a list of explosion hitboxes
     */
    public List<Rectangle> getExplosionHitboxes() {
        final List<Rectangle> hitboxes = new ArrayList<>();
        for (final Rectangle hitbox : explosionHitboxes) {
            hitboxes.add(new Rectangle((int) hitbox.getX(), (int) hitbox.getY(),
                (int) hitbox.getWidth(), (int) hitbox.getHeight()));
        }
        return hitboxes;
    }

    /**
     * Shoots a projectile in the direction the player is facing.
     */
    private void shoot() {
        projectiles.add(new Projectile(player.getX(), player.getY(), direction, SPEED, PROJECTILE_SIZE));
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

    /**
     * @return the player associated with the staff.
     */
    @Override
    public Player getPlayer() {
        return null;
    }
    /**
     * @return the level of the staff.
     */
    @Override
    public int getLevel() {
        return 1;
    }
    /** 
     * @return the damage of the staff.
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
    /**
     * removes the projectile from the list.
     * @param projectile the projectile to remove
     */
    public void removeProjectile(final Projectile projectile) {
        projectiles.remove(projectile);
    }
    /**
     * @return the list of projectiles.
     */
    public List<Projectile> getProjectilesList() {
        return projectiles;
    }
    /**
     * clears the explosion hitboxes.
     * @param explosionHitbox the explosion hitbox to remove
     */
    public void removeExplosion(final Rectangle explosionHitbox) {
        explosionHitboxes.remove(explosionHitbox);
    }
}
