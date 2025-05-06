package it.unibo.oop.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import javax.imageio.ImageIO;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(value = {"EI2"}, 
justification = "To position the weapon, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class Sword extends Weapon {
    private static final double DURATION = 30;
    private static final double COOLDOWN = 60;
    private double duration;
    private double cooldown;
    private boolean active;
    private final Player player;
    private boolean lastSwingRight = true;
    private Image swordImage;

    /**
     * @param player
     */
    public Sword(final Player player) {
        this.active = false;
        this.player = player;
        try {
            this.swordImage = ImageIO.read(getClass().getResource("/Weapon/Sword.png"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Sword image could not be loaded.");
        }
    }

    /**
     * Updates the sword.
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
     * @param g
     */
    @Override
    public void draw(final Graphics g) {
        if (active) {
            Graphics2D g2d = (Graphics2D) g;
            int drawX = player.getX(), drawY;
            double scale = 2;
            double rotation = 0;

            Direction currentDirection = player.getDirection();

            if (currentDirection == Direction.NONE || currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
                if (lastSwingRight) {
                    currentDirection = Direction.RIGHT;
                } else {
                    currentDirection = Direction.LEFT;
                }
            } else if (currentDirection == Direction.UPRIGHT || currentDirection == Direction.DOWNRIGHT) {
                currentDirection = Direction.RIGHT;
            } else if (currentDirection == Direction.UPLEFT || currentDirection == Direction.DOWNLEFT) {
                currentDirection = Direction.LEFT;
            }

            drawY = player.getY() + player.getSize() / 2 - (int) (swordImage.getHeight(null) * scale) / 2;

            switch (currentDirection) {
                case Direction.RIGHT:
                    drawX = player.getX() + player.getSize();
                    rotation = Math.toRadians(90);
                    lastSwingRight = true;
                    break;
                case Direction.LEFT:
                    drawX = player.getX() - (int) (swordImage.getWidth(null) * scale);
                    rotation = Math.toRadians(-90); 
                    lastSwingRight = false;
                    break;
                default:
                    break;
            }

            AffineTransform transform = new AffineTransform();
            transform.translate(drawX, drawY);
            transform.rotate(rotation, (swordImage.getWidth(null) * scale) / 2.0, (swordImage.getHeight(null) * scale) / 2.0);
            transform.scale(scale, scale);
            g2d.drawImage(swordImage, transform, null);
        }
    }
}