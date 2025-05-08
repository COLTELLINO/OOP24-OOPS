package it.unibo.oop.model;

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
    private static final double COOLDOWN = 40;
    private static final int SPEED = 10;

    private double cooldown;
    private final Player player;
    private final List<Projectile> projectiles;
    private Direction direction = Direction.UP;
    private Direction lastDirection = Direction.UP;

    /**
     * Constructs a Bow object.
     * 
     * @param player the player associated with the bow
     */
    public Bow(final Player player) {
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
}
