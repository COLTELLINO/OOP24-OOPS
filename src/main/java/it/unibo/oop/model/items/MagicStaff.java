package it.unibo.oop.model.items;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.managers.WeaponManagerImpl.WeaponObserver;
import it.unibo.oop.model.projectiles.Projectile;
import it.unibo.oop.model.projectiles.StaffProjectile;
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
    private static final int EXPLOSION_SIZE = 200;
    private static final int EXPLOSION_LIFETIME = 30;
    private static final int DAMAGESCALER = 1;
    private static final int SIZESCALER = 2;
    private static final int SPEEDSCALER = 2;

    private double cooldown;
    private final Player player;
    private final List<Projectile> projectiles;
    private final Map<Rectangle, Integer> explosionHitboxes;
    private WeaponObserver observer = () -> {
        // Default no-op implementation
    };
    private Direction direction = Direction.UP;
    private Direction lastDirection = Direction.UP;

    /**
    * Functional interface for observing projectile actions.
    */
    @FunctionalInterface
    public interface ProjectileObserver {
        /**
        * Called when a projectile explodes.
        * 
        * @param projectile the projectile that exploded
        */
        void onProjectileExploded(Projectile projectile);
    }
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
    }

    /**
     * Updates the staff's state.
     */
    @Override
    public void update() {
        if (cooldown <= 0) {
            shoot();
            observerAction();
            cooldown = COOLDOWN;
        } else {
            if (getLevel() >= 3) {
                cooldown -= SPEEDSCALER;
            } else {
                cooldown--;
            }
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
     * Gets the hitboxes of all active explosions.
     * 
     * @return a list of hitboxes for the active explsoion
     */
    @Override
    public List<Rectangle> getHitBox() {
        return new ArrayList<>(explosionHitboxes.keySet());
    }


    /**
     * Shoots a projectile in the direction the player is facing.
     */
    private void shoot() {
        final StaffProjectile projectile = new StaffProjectile(player.getX(), player.getY(),
        direction, 0, SPEED, PROJECTILE_SIZE);

        projectile.setObserver(explodedProjectile -> {
            final int explosionX;
            final int explosionY;
            final int offset1 = 70; 
            final int offset2 = 50;
            final int offset3 = 60;

            switch (projectile.getDirection()) {
                case UP -> {
                    explosionX = projectile.getX() - offset1;
                    explosionY = projectile.getY() - offset2 * 2;
                }
                case DOWN -> {
                    explosionX = projectile.getX() - offset2 * 2;
                    explosionY = projectile.getY() - offset2;
                }
                case LEFT -> {
                    explosionX = projectile.getX() - offset2 * 2;
                    explosionY = projectile.getY() - offset2 * 2;
                }
                case RIGHT -> {
                    explosionX = projectile.getX() - offset2;
                    explosionY = projectile.getY() - offset3;
                }
                default -> {
                    explosionX = projectile.getX();
                    explosionY = projectile.getY();
                }
            }

            final Rectangle explosion = new Rectangle(
                explosionX,
                explosionY,
                EXPLOSION_SIZE,
                EXPLOSION_SIZE
            );

            if (getLevel() >= 2) {
                explosionHitboxes.put(scaleRectangle(explosion, SIZESCALER), EXPLOSION_LIFETIME);
            } else {
                explosionHitboxes.put(explosion, EXPLOSION_LIFETIME);
            }
        });

        projectiles.add(projectile);
    }

    /**
     * Scales a rectangle while keeping its center unchanged.
     * 
     * @param rect the original rectangle
     * @param scaleFactor the factor by which to scale the rectangle
     * @return a new scaled rectangle with the same center
     */
    private Rectangle scaleRectangle(final Rectangle rect, final double scaleFactor) {
        final int newWidth = (int) (rect.width * scaleFactor);
        final int newHeight = (int) (rect.height * scaleFactor);
        final int newX = rect.x - (newWidth - rect.width) / 2;
        final int newY = rect.y - (newHeight - rect.height) / 2;
        return new Rectangle(newX, newY, newWidth, newHeight);
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
     * @return the base damage of the magic staff.
     */
    @Override
    protected int getBaseDamage() {
        return DAMAGE + ((getLevel() - 1) * DAMAGESCALER);
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
        //unused
    }

    /**
     * If an observer is present, trigger its action.
     */
    protected void observerAction() {
        observer.weaponObserverAction();
    }

    /**
     * Sets the observer for this projectile.
     * 
     * @param observer the observer to set
     */
    public void setObserver(final WeaponObserver observer) {
        this.observer = observer;
    }

    /**
     * @return the observer
     */
    public Object getObserver() {
        return this.observer;
    }
}
