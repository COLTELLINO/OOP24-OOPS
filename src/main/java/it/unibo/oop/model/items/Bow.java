package it.unibo.oop.model.items;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.managers.WeaponManager;
import it.unibo.oop.model.managers.WeaponManagerImpl.WeaponObserver;
import it.unibo.oop.model.projectiles.Arrow;
import it.unibo.oop.model.projectiles.Projectile;
import it.unibo.oop.utils.Direction;

/**
 * Represents a Bow weapon in the game.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the weapon, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class Bow extends Weapon {
    private static final int DAMAGE = 1;
    private static final double COOLDOWN = 30;
    private static final int SPEED = 10;
    private static final int PROJECTILE_SIZE = 30;

    private double cooldown;
    private final Player player;
    private final List<Projectile> projectiles;
    private WeaponObserver observer = () -> {
        // Default no-op implementation
    };
    private Direction direction = Direction.UP;
    private Direction lastDirection = Direction.UP;
    private static final int DAMAGESCALER = 1;
    private static final int ATTACKSPEEDSCALER = 2;
    private static final int SPEEDSCALER = 10;

    /**
     * Constructs a Bow.
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
            observerAction();
            cooldown = COOLDOWN;
        } else {
            if (getLevel() >= 4) {
                cooldown -= ATTACKSPEEDSCALER;
            } else {
                cooldown--;
            }
        }
        direction = player.getDirection();
        if (direction == Direction.LEFT || direction == Direction.RIGHT 
        || direction == Direction.DOWN || direction == Direction.UP) {
            lastDirection = direction;
        } else {
            direction = lastDirection;
        }
        projectiles.forEach(Projectile::update);
        projectiles.removeIf(Projectile::isOutOfBounds);
    }
    /**
     * If an observer is present, trigger its action.
     */
    protected void observerAction() {
        observer.weaponObserverAction();
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
     * Shoots projectiles based on the bow's level.
     */
    private void shoot() {
        switch (getLevel()) {
            case 1 -> {
                projectiles.add(new Arrow(player.getX(), player.getY(), direction,
                    DAMAGE, SPEED, PROJECTILE_SIZE, player.getSize()));
            }
            case 2 -> {
                projectiles.add(new Arrow(player.getX(), player.getY(), direction,
                    DAMAGE, SPEED, PROJECTILE_SIZE, player.getSize()));
                projectiles.add(new Arrow(player.getX(), player.getY(), direction.getOpposite(),
                    DAMAGE, SPEED, PROJECTILE_SIZE, player.getSize()));
            }
            case 3 -> {
                for (final Direction dir : Direction.verticalHorizontal()) {
                    projectiles.add(new Arrow(player.getX(), player.getY(), dir,
                        DAMAGE, SPEED, PROJECTILE_SIZE, player.getSize()));
                }
            }
            case 4 -> {
                for (final Direction dir : Direction.verticalHorizontal()) {
                    projectiles.add(new Arrow(player.getX(), player.getY(), dir,
                        DAMAGE, SPEED, PROJECTILE_SIZE, player.getSize()));
                }
            }
            case WeaponManager.MAX_LEVEL -> {
                for (final Direction dir : Direction.verticalHorizontal()) {
                    projectiles.add(new Arrow(player.getX(), player.getY(), dir, 
                    DAMAGE, SPEED + SPEEDSCALER, PROJECTILE_SIZE, player.getSize()));
                }
            }
            default -> throw new IllegalStateException("Unexpected level: " + getLevel());
        }
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
     * Gets the base damage dealt by the Bow.
     * @return the base damage dealt by the Bow
     */
    @Override
    protected int getBaseDamage() {
        return DAMAGE + ((getLevel() - 1) * DAMAGESCALER);
    }

    /**
     * @param observer
     */
    public void setObserver(final WeaponObserver observer) {
        this.observer = observer;
    }

    /**
     * @return the observer.
     */
    public Object getObserver() {
        return this.observer;
    }
}
