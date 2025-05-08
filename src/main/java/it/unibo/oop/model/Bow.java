package it.unibo.oop.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a Bow weapon in the game.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the weapon, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class Bow extends Weapon {
    private static final int DAMAGE = 50;
    private static final double COOLDOWN = 100;
    private static final int SPEED = 1;
    private static final int PROJECTILE_SIZE = 30;

    private double cooldown;
    private final Player player;
    private final List<Projectile> projectiles;
    private Direction direction = Direction.UP;
    private Direction lastDirection = Direction.UP;
    private boolean showHitbox;

    /**
     * Constructs a Bow object.
     * 
     * @param player the player associated with the bow
     */
    public Bow(final Player player) {
        super(player);
        this.player = player;
        this.cooldown = 0;
        this.projectiles = new ArrayList<>();
    }

    /**
     * Updates the bow's state.
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
     * Gets the hitboxes of all active projectiles.
     * 
     * @return a list of hitboxes for the active projectiles
     */
    @Override
    public List<Rectangle> getHitBox() {
        final List<Rectangle> hitboxes = new ArrayList<>();
        for (final Projectile projectile : projectiles) {
            if (projectile.getDirection() == Direction.RIGHT) {
                hitboxes.add(new Rectangle(projectile.getX() + PROJECTILE_SIZE * 2, projectile.getY() 
                + PROJECTILE_SIZE / 3, PROJECTILE_SIZE, PROJECTILE_SIZE));
            } else if (projectile.getDirection() == Direction.LEFT) {
                hitboxes.add(new Rectangle(projectile.getX() - PROJECTILE_SIZE - PROJECTILE_SIZE / 2, projectile.getY() 
                + PROJECTILE_SIZE / 3, PROJECTILE_SIZE, PROJECTILE_SIZE));
            } else if (projectile.getDirection() == Direction.UP) {
                hitboxes.add(new Rectangle(projectile.getX() + PROJECTILE_SIZE / 3, projectile.getY() 
                - PROJECTILE_SIZE - PROJECTILE_SIZE / 2, PROJECTILE_SIZE, PROJECTILE_SIZE));
            } else if (projectile.getDirection() == Direction.DOWN) {
                hitboxes.add(new Rectangle(projectile.getX() + PROJECTILE_SIZE / 3, projectile.getY() 
                + PROJECTILE_SIZE * 2, PROJECTILE_SIZE, PROJECTILE_SIZE));
            }
        }
        return hitboxes;
    }

    /**
     * Shoots a projectile in the direction the player is facing.
     */
    private void shoot() {
        projectiles.add(new Projectile(player.getX(), player.getY(), direction, SPEED));
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
     * @return the player associated with the bow.
     */
    @Override
    public Player getPlayer() {
        return this.player;
    }
    /**
     * @return the level of the bow.
     */
    @Override
    public int getLevel() {
        return 1;
    }
    /**
     * @return the damage of the bow.
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
