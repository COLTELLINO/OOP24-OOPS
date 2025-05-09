package it.unibo.oop.view;

import java.awt.Graphics2D;
import java.util.List;

import it.unibo.oop.model.Projectile;
/**
 * Interface for rendering projectiles.
 */
public interface ProjectileRenderer {
    /**
     * Draws current projectile.
     * @param projectile
     * @param g2
     */
    void drawProjectile(Projectile projectile, Graphics2D g2);
    /**
     * Draws every projectile in a list.
     * @param projectileList
     * @param g2
     */
    void drawProjectileList(List<Projectile> projectileList, Graphics2D g2);
}
