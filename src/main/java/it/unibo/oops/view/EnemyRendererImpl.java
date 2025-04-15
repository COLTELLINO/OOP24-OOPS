package it.unibo.oops.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import it.unibo.oops.model.Enemy;
/**
 * Class used to draw enemies.
 */
public class EnemyRendererImpl implements EnemyRenderer {
    /**
     * Draws current enemy.
     * @param enemy
     * @param g2
     */
    @Override
    public void drawEnemy(final Enemy enemy, final Graphics2D g2) {
        try {
            final BufferedImage image = 
            ImageIO.read(this.getClass().getResource("/Monster/" + enemy.getEnemyName() + ".png"));
            g2.drawImage(image, enemy.getX(), enemy.getY(), image.getWidth() * enemy.getSizeScale(),
                image.getHeight() * enemy.getSizeScale(), null);
            if (enemy.showHitbox()) {
                g2.setColor(Color.RED);
                g2.drawRect(enemy.getX(), enemy.getY(), enemy.getSize(), enemy.getSize());
            }
        } catch (IOException | IllegalArgumentException e) {
            Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, e.getClass().getSimpleName() + " occurred: ", e);
        }
    }
    /**
     * Draws every enemy in a list.
     * @param enemyList
     * @param g2
     */
    @Override
    public void drawEnemyList(final List<Enemy> enemyList, final Graphics2D g2) {
        for (final Enemy enemy : enemyList) {
            this.drawEnemy(enemy, g2);
        }
    }
}
