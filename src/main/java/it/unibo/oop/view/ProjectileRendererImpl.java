package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import it.unibo.oop.model.Projectile;
/**
 * Implementation of ProjectileRenderer for rendering projectiles.
 */
public class ProjectileRendererImpl implements ProjectileRenderer {
    private static final int WIDTH_HEIGHT = 10; 
    /**
     * Draws current projectile.
     * @param projectile
     * @param g2
     */
    @Override
    public void drawProjectile(final Projectile projectile, final Graphics2D g2) {
        //projectile.getName() o qualcosa del genere
        // per ottenere il nome dell'immagine che poi verrà caricata
        g2.setColor(Color.WHITE);
        g2.fillOval(projectile.getX() - WIDTH_HEIGHT / 2, projectile.getY() - WIDTH_HEIGHT / 2, WIDTH_HEIGHT, WIDTH_HEIGHT);
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
