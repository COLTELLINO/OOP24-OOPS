package it.unibo.oop.view;

import java.awt.Graphics2D;
import java.util.List;

import it.unibo.oop.model.Enemy;
/**
 * Interface for rendering enemies.
 */
public interface EnemyRenderer {
    /**
     * Draws current enemy.
     * @param enemy
     * @param g2
     */
    void drawEnemy(Enemy enemy, Graphics2D g2);
    /**
     * Draws every enemy in a list.
     * @param enemyList
     * @param g2
     */
    void drawEnemyList(List<Enemy> enemyList, Graphics2D g2);
}
