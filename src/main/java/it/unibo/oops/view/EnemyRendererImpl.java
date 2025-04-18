package it.unibo.oops.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import it.unibo.oops.model.Enemy;
/**
 * Class used to draw enemies.
 */
public class EnemyRendererImpl implements EnemyRenderer {
    private final Map<String, BufferedImage> enemySpriteMap = new HashMap<>();
    /**
     * Draws current enemy.
     * @param enemy
     * @param g2
     */
    @Override
    public void drawEnemy(final Enemy enemy, final Graphics2D g2) {
        try {
            final BufferedImage image = getEnemySprite(enemy.getEnemyName());
            g2.drawImage(image, enemy.getX(), enemy.getY(), image.getWidth() * enemy.getSizeScale(),
                image.getHeight() * enemy.getSizeScale(), null);
            if (enemy.isShowHitbox()) {
                g2.setColor(Color.RED);
                g2.drawRect(enemy.getX(), enemy.getY(), enemy.getSize(), enemy.getSize());
            }
        } catch (IllegalArgumentException e) {
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
    /**
     * @param name
     * @return the image of the enemy.
     */
    private BufferedImage getEnemySprite(final String name) {
        return enemySpriteMap.computeIfAbsent(name, key -> {
            try {
                return ImageIO.read(EnemyRendererImpl.class.getResource("/Monster/" + key + ".png"));
            } catch (IOException e) {
                Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, e.getClass().getSimpleName() + " occurred: ", e);
                    return null;
            }
        });
    }
}
