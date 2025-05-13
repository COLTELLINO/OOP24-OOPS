package it.unibo.oop.view.renderers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.items.MagicStaff;
import it.unibo.oop.model.items.Sword;
import it.unibo.oop.model.items.Weapon;
import it.unibo.oop.utils.Direction;

/**
 * Implementation of WeaponRender for rendering weapons.
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "To position the weapon, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public final class WeaponRendererImpl implements WeaponRenderer {
    private static final Logger LOGGER = Logger.getLogger(WeaponRendererImpl.class.getName());
    private static final double SCALE = 2.0;
    private static final double ROTATION_RIGHT = Math.toRadians(90);
    private static final double ROTATION_LEFT = Math.toRadians(-90);

    /**
     * Draws a sword on the screen.
     * 
     * @param g the graphics context
     * @param sword the sword to draw
     */
    @Override
    public void drawSword(final Graphics g, final Sword sword) {
        if (!sword.isActive()) {
            return;
        }

        if (!(g instanceof Graphics2D)) {
            LOGGER.log(Level.WARNING, "Graphics object is not instance of Graphics2D.");
            return;
        }

        final Image swordImage;
        try {
            swordImage = ImageIO.read(Objects.requireNonNull(
            getClass().getClassLoader().getResource("Weapon/Sword.png"),
            "Resource 'Weapon/Sword.png' not found."
            ));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Sword image could not be loaded.", e);
            throw new IllegalStateException("Sword image could not be loaded.", e);
        }
        final Graphics2D g2d = (Graphics2D) g;
        final Player player = sword.getPlayer();
        final int drawX;
        final int drawY = player.getY() + player.getSize() / 2 - (int) (swordImage.getHeight(null) * SCALE) / 2;
        double rotation = 0;
        final int offset1 = 40;
        final int offset2 = 55;

        if (sword.getDirection() == Direction.RIGHT) {
            drawX = player.getX() + offset1;
            rotation = ROTATION_RIGHT;
        } else if (sword.getDirection() == Direction.LEFT) {
            drawX = player.getX() - offset2;
            rotation = ROTATION_LEFT;
        } else {
            drawX = player.getX();
        }

        final AffineTransform transform = new AffineTransform();
        transform.translate(drawX, drawY);
        transform.rotate(rotation, swordImage.getWidth(null) * SCALE / 2.0, swordImage.getHeight(null) * SCALE / 2.0);
        transform.scale(SCALE, SCALE);
        g2d.drawImage(swordImage, transform, null);
    }

    /**
     * Draws a magic staff on the screen.
     * 
     * @param g the graphics context
     * @param staff the magic staff to draw
     */
    @Override
    public void drawMagicStaff(final Graphics g, final MagicStaff staff) {
        if (!(g instanceof Graphics2D)) {
            LOGGER.log(Level.WARNING, "Graphics object is not an instance of Graphics2D.");
            return;
        }
        final Graphics2D g2d = (Graphics2D) g;
        final List<Rectangle> explosionHitBoxes = staff.getHitBox();
        final Image explosionImage;

        try {
            explosionImage = ImageIO.read(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Weapon/explosion.png"),
                "Resource 'Weapon/explosion.png' not found."
            ));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Explosion image could not be loaded.", e);
            return;
        }
        final float alpha = 0.3f;
        final Composite originalComposite = g2d.getComposite();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        for (final Rectangle hitbox : explosionHitBoxes) {
            if (hitbox != null) {
                g2d.drawImage(explosionImage, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
            }
        }

        g2d.setComposite(originalComposite);
    }

    /**
     * Draws a list of weapons on the screen.
     * 
     * @param g the graphics context
     * @param weapons the list of weapons to draw
     */
    @Override
    public void drawWeaponList(final Graphics2D g, final List<Weapon> weapons) {
        for (final Weapon weapon : weapons) {
            if (weapon instanceof Sword) {
                drawSword(g, (Sword) weapon);
            }
            if (weapon instanceof MagicStaff) {
                drawMagicStaff(g, (MagicStaff) weapon);
            }
            if (weapon.isShowHitbox()) {
                final Graphics2D g2d = g;
                g2d.setColor(java.awt.Color.RED);
                final List<Rectangle> hitboxes = new ArrayList<>();
                hitboxes.addAll(weapon.getHitBox());
                for (final Rectangle rectangle : hitboxes) {
                    if (rectangle != null) {
                        g2d.draw(rectangle);
                    }
                }
            }
        }

    }
}
