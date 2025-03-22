package it.unibo.oops.model;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 * 
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "To spawn enemies around the player, its position is needed, " 
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class EnemyManager {
    private static final int WAVE_SIZE = 8;
    private static final int ENEMY_CAP = 1000;
    private static final int PLAYER_DISTANCE = 200;
    private final List<Enemy> enemyList = new ArrayList<>();
    private final Player player;
    /**
     * @param player
     */
    public EnemyManager(final Player player) {
        this.player = player;
    }
    /**
    * Updates every enemy.
    */
    public void update() {
        if (!enemyList.isEmpty() && enemyList.size() >= WAVE_SIZE) {
            for (int i = 0; i < WAVE_SIZE; i++) {
                if (!enemyList.get(i).isPositioned()) {
                    final double angle = 2 * Math.PI * i / WAVE_SIZE;
                    final double x = player.getX() + player.getSize() / 2
                    + PLAYER_DISTANCE * Math.cos(angle) - (double) enemyList.get(i).getSize() / 2;
                    final double y = player.getY() + player.getSize() / 2
                    + PLAYER_DISTANCE * Math.sin(angle) - (double) enemyList.get(i).getSize() / 2;
                    enemyList.get(i).setX((int) x);
                    enemyList.get(i).setY((int) y);
                    enemyList.get(i).setPosition(true);
                    enemyList.get(i).setSpeed(1);
                }
                enemyList.get(i).update();
            }
        }
    }
    /**
     * Adds an enemy to the list.
     * @param enemy 
     */
    public void addEnemy(final Enemy enemy) {
        if (enemyList.size() <= ENEMY_CAP) {
            enemyList.add(enemy);
        }
    }
    /**
     * Draws every enemy.
     * @param g2d
     */
    public void draw(final Graphics2D g2d) {
        for (int i = 0; i < WAVE_SIZE; i++) {
            enemyList.get(i).draw(g2d);
        }
        //System.out.println(enemyList.size());
    }
}
