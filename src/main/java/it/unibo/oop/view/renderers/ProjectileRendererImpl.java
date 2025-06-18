package it.unibo.oop.view.renderers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import it.unibo.oop.model.projectiles.Projectile;
/**
 * Implementation of ProjectileRenderer for rendering projectiles.
 */
public class ProjectileRendererImpl implements ProjectileRenderer {
    private static final double ROTATION_RIGHT = Math.toRadians(90);
    private static final double ROTATION_LEFT = Math.toRadians(-90);
    private static final double SCALE = 1.0;
    private final Map<String, BufferedImage> projectileSpriteMap = new HashMap<>();

    /**
     * Draws current projectile.
     * @param projectile
     * @param g2
     */
    @Override
    public void drawProjectile(final Projectile projectile, final Graphics2D g2) {
        final BufferedImage projectileImage = getProjectileSprite(projectile);
        final AffineTransform transform = new AffineTransform();
        transform.translate(projectile.getX(), projectile.getY());

        switch (projectile.getDirection()) {
            case RIGHT -> transform.rotate(ROTATION_RIGHT, 
                projectileImage.getWidth(null) / 2.0, projectileImage.getHeight(null) / 2.0);
            case LEFT -> transform.rotate(ROTATION_LEFT, 
                projectileImage.getWidth(null) / 2.0, projectileImage.getHeight(null) / 2.0);
            case UP -> transform.rotate(0, 
                projectileImage.getWidth(null) / 2.0, projectileImage.getHeight(null) / 2.0);
            case DOWN -> transform.rotate(ROTATION_RIGHT * 2, 
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
    /**
     * @param projectile
     * @return the image of the enemy.
     */
    private BufferedImage getProjectileSprite(final Projectile projectile) {
        return projectileSpriteMap.computeIfAbsent(projectile.getProjectileName(), name -> {
            try {
                return ImageIO.read(EnemyRendererImpl.class.getResource("/Weapon/" + name + ".png"));
            } catch (IOException e) {
                Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, e.getClass().getSimpleName() + " occurred: ", e);
                    return null;
            }
        });
    }
}
