package it.unibo.oop.view;

import java.awt.AlphaComposite;
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

import it.unibo.oop.model.Enemy;
/**
 * Class used to draw enemies.
 */
public class EnemyRendererImpl implements EnemyRenderer {
    private static final int BLINK_INTERVAL = 6;
    private static final float ALPHA_VALUE = 0.6f;
    private final Map<String, BufferedImage> enemySpriteMap = new HashMap<>();
    /**
     * Draws current enemy.
     * @param enemy
     * @param g2
     */
    @Override
    public void drawEnemy(final Enemy enemy, final Graphics2D g2) {
        try {
            if (enemy.isDying() && enemy.getBlinkCounter() / BLINK_INTERVAL % 2 == 0) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, ALPHA_VALUE));
            }
            final BufferedImage image = getEnemySprite(enemy);
                g2.drawImage(image, enemy.getX(), enemy.getY(), image.getWidth() * enemy.getSizeScale(),
                    image.getHeight() * enemy.getSizeScale(), null);
            if (enemy.isShowHitbox()) {
                g2.setColor(Color.RED);
                g2.drawRect(enemy.getX(), enemy.getY(), enemy.getSize(), enemy.getSize());
            }
            g2.setComposite(AlphaComposite.SrcOver);
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
     * @param enemy
     * @return the image of the enemy.
     */
    private BufferedImage getEnemySprite(final Enemy enemy) {
        return enemySpriteMap.computeIfAbsent(getSpriteName(enemy), key -> {
            try {
                return ImageIO.read(EnemyRendererImpl.class.getResource("/Monster/" + key + ".png"));
            } catch (IOException e) {
                Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, e.getClass().getSimpleName() + " occurred: ", e);
                    return null;
            }
        });
    }
    /**
     * @param enemy
     * @return the name of the sprite png.
     */
    private String getSpriteName(final Enemy enemy) {
        final String separator = "_";
        final String name = enemy.getEnemyName();
        final String direction = separator + enemy.getDirection().toString();
        String attacking = "";
        if (enemy.isAttacking()) {
            attacking = "attacking" + separator;
        }
        return attacking + name + direction;
    }
}
