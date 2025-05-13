package it.unibo.oop.view.renderers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import it.unibo.oop.model.projectiles.Projectile;
import it.unibo.oop.utils.Direction;
/**
 * Implementation of ProjectileRenderer for rendering projectiles.
 */
public class ProjectileRendererImpl implements ProjectileRenderer {
    private static final Logger LOGGER = Logger.getLogger(ProjectileRendererImpl.class.getName());
    private static final double ROTATION_RIGHT = Math.toRadians(90);
    private static final double ROTATION_LEFT = Math.toRadians(-90);
    private static final double SCALE = 2.0;

    /**
     * Draws current projectile.
     * @param projectile
     * @param g2
     */
    @Override
    public void drawProjectile(final Projectile projectile, final Graphics2D g2) {
        final Image projectileImage;
        try {
            projectileImage = ImageIO.read(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Weapon/" + projectile.getProjectileName() + ".png"),
                "Resource 'Weapon/Bow.png' not found."
            ));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Bow projectile image could not be loaded.", e);
            return;
        }
        final AffineTransform transform = new AffineTransform();
        transform.translate(projectile.getX(), projectile.getY());

        switch (projectile.getDirection()) {
            case Direction.RIGHT -> transform.rotate(ROTATION_RIGHT, 
            projectileImage.getWidth(null) / 2.0, projectileImage.getHeight(null) / 2.0);
            case Direction.LEFT -> transform.rotate(ROTATION_LEFT, 
            projectileImage.getWidth(null) / 2.0, projectileImage.getHeight(null) / 2.0);
            case Direction.UP -> transform.rotate(0, 
            projectileImage.getWidth(null) / 2.0, projectileImage.getHeight(null) / 2.0);
            case Direction.DOWN -> transform.rotate(ROTATION_RIGHT * 2, 
            projectileImage.getWidth(null) / 2.0, projectileImage.getHeight(null) / 2.0);
            default -> { }
        }

        transform.scale(SCALE, SCALE);
        g2.drawImage(projectileImage, transform, null);

        if (projectile.isHitboxShowed()) {
            g2.setColor(Color.BLUE);
            g2.draw(projectile.getProjectileHitBox());
        }
    }
    /**
     * Draws every projectile in a list.
     * @param projectileList
     * @param g2
     */
    @Override
    public void drawProjectileList(final List<Projectile> projectileList, final Graphics2D g2) {
        for (final Projectile projectile : projectileList) {
            this.drawProjectile(projectile, g2);
        }
    }
}
