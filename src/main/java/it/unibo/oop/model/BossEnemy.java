package it.unibo.oop.model;
/**
 * Extension of EnemyDecorator that decorates an enemy into a boss.
 */
public class BossEnemy extends EnemyDecorator {
    private static final int SPEED_SCALE = 2;
    private static final int SIZE_SCALE = 2;
    /**
     * @param enemy
     */
    public BossEnemy(final Enemy enemy) {
        super(enemy);
        super.setSizeScale(enemy.getSizeScale() * SIZE_SCALE);
        super.setSpeed(enemy.getSpeed() * SPEED_SCALE);
        super.setSize(enemy.getSize() * SIZE_SCALE);
        super.setBoss(true);
    }
}
