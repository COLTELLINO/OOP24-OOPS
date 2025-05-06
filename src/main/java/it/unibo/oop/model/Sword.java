package it.unibo.oop.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a Sword weapon in the game.
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "To position the weapon, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class Sword extends Weapon {
    private static final double DURATION = 30;
    private static final double COOLDOWN = 60;
    private static final double SCALE = 2.0;
    private static final double ROTATION_RIGHT = Math.toRadians(90);
    private static final double ROTATION_LEFT = Math.toRadians(-90);
    private static final Logger LOGGER = Logger.getLogger(Sword.class.getName());

    private double duration;
    private double cooldown;
    private boolean active;
    private final Player player;
    private boolean lastSwingRight = true;
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
            this.swordImage = ImageIO.read(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Weapon/Sword.png"),
                "Resource 'Weapon/Sword.png' not found."
            ));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Sword image could not be loaded.", e);
            throw new IllegalStateException("Sword image could not be loaded.", e);
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
    }

    /**
     * Draws the sword.
     * 
     * @param g the graphics context
     */
    @Override
    public void draw(final Graphics g) {
        if (active) {
            if (!(g instanceof Graphics2D)) {
                LOGGER.log(Level.WARNING, "Graphics object is not an instance of Graphics2D.");
                return;
            }
            final Graphics2D g2d = (Graphics2D) g;
            final int drawX;
            final int drawY = player.getY() + player.getSize() / 2 - (int) (swordImage.getHeight(null) * SCALE) / 2;
            double rotation = 0;

            Direction currentDirection = player.getDirection();

            if (currentDirection == Direction.NONE || currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
                currentDirection = lastSwingRight ? Direction.RIGHT : Direction.LEFT;
            } else if (currentDirection == Direction.UPRIGHT || currentDirection == Direction.DOWNRIGHT) {
                currentDirection = Direction.RIGHT;
            } else if (currentDirection == Direction.UPLEFT || currentDirection == Direction.DOWNLEFT) {
                currentDirection = Direction.LEFT;
            }

            if (currentDirection == Direction.RIGHT) {
                drawX = player.getX() + player.getSize();
                rotation = ROTATION_RIGHT;
                lastSwingRight = true;
            } else if (currentDirection == Direction.LEFT) {
                drawX = player.getX() - (int) (swordImage.getWidth(null) * SCALE);
                rotation = ROTATION_LEFT;
                lastSwingRight = false;
            } else {
                drawX = player.getX();
            }

            final AffineTransform transform = new AffineTransform();
            transform.translate(drawX, drawY);
            transform.rotate(rotation, swordImage.getWidth(null) * SCALE / 2.0, swordImage.getHeight(null) * SCALE / 2.0);
            transform.scale(SCALE, SCALE);
            g2d.drawImage(swordImage, transform, null);
        }
    }
}
