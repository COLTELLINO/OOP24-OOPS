package it.unibo.oop.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.oop.model.Direction;
import it.unibo.oop.model.Player;
import it.unibo.oop.model.Sword;
import it.unibo.oop.model.Weapon;

/**
 * Implementation of WeaponRender for rendering weapons.
 */
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
            LOGGER.log(Level.WARNING, "Graphics object is not an instance of Graphics2D.");
            return;
        }

        final Graphics2D g2d = (Graphics2D) g;
        final Player player = sword.getPlayer();
        final Image swordImage = sword.getSwordImage();
        final int drawX;
        final int drawY = player.getY() + player.getSize() / 2 - (int) (swordImage.getHeight(null) * SCALE) / 2;
        double rotation = 0;

        if (sword.getDirection() == Direction.RIGHT) {
            drawX = player.getX() + player.getSize();
            rotation = ROTATION_RIGHT;
        } else if (sword.getDirection() == Direction.LEFT) {
            drawX = player.getX() - (int) (swordImage.getWidth(null) * SCALE);
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
     * Draws a list of weapons on the screen.
     * 
     * @param g the graphics context
     * @param weapons the list of weapons to draw
     */
    @Override
    public void drawWeaponList(final Graphics g, final List<Weapon> weapons) {
        for (final Weapon weapon : weapons) {
            if (weapon instanceof Sword) {
                drawSword(g, (Sword) weapon);
            }
            // Add other weapon types here if needed in the future
        }
    }
}
