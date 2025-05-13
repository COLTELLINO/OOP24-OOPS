package it.unibo.oop.model.items;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.projectiles.Projectile;
import it.unibo.oop.utils.Direction;

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

    private double cooldown;
    private final Player player;
    private final List<Projectile> projectiles;
    private final Map<Rectangle, Integer> explosionHitboxes;
    private Direction direction = Direction.UP;
    private Direction lastDirection = Direction.UP;
    private boolean showHitbox;
    private int level;

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
        this.explosionHitboxes = new LinkedHashMap<>();
        this.level = 1;
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

        final Iterator<Map.Entry<Rectangle, Integer>> iterator = explosionHitboxes.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<Rectangle, Integer> entry = iterator.next();
            final int remainingTime = entry.getValue() - 1;
            if (remainingTime <= 0) {
                iterator.remove();
            } else {
                entry.setValue(remainingTime);
            }
        }
    }

    /**
     * Gets the hitboxes of all active projectiles.
     * 
     * @return a list of hitboxes for the active projectiles
     */
    @Override
    public List<Rectangle> getHitBox() {
        return List.of();
    }

    /**
     * Gets all active explosion hitboxes.
     * 
     * @return a list of explosion hitboxes
     */
    public List<Rectangle> getExplosionHitboxes() {
        return new ArrayList<>(explosionHitboxes.keySet());
    }

    /**
     * Shoots a projectile in the direction the player is facing.
     */
    private void shoot() {
        projectiles.add(new Projectile(player.getX(), player.getY(), direction, DAMAGE, SPEED, PROJECTILE_SIZE));
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
     * Gets the level of the magic staff.
     * 
     * @return the level of the magic staff
     */
    @Override
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the magic staff.
     * 
     * @param level the new level of the magic staff
     */
    @Override
    public void setLevel(final int level) {
        this.level = level;
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
     * @return the list of projectiles.
     */
    public List<Projectile> getProjectilesList() {
        return projectiles;
    }

    /**
     * hndles the weapon collision.
     */
    @Override
    public void handleCollision() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleCollision'");
    }
}
