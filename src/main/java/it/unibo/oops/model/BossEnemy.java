package it.unibo.oops.model;
/**
 * Extension of EnemyDecorator that decorates an enemy into a boss.
 */
public class BossEnemy extends EnemyDecorator {
    private static final int SPEED_SCALE = 2;
    /**
     * Adjusts the scale of boss enemies.
     */
    protected static final int SIZE_SCALE = 2;
    /**
     * @param enemy
     */
    public BossEnemy(final Enemy enemy) {
        super(enemy);
        super.setSpeed(enemy.getSpeed() * SPEED_SCALE);
        super.setSize(enemy.getSize() * SIZE_SCALE);
    }
    /**
     * @return the target player.
     */
    @Override
    public int getSizeScale() {
        return SIZE_SCALE;
    }
    /**
     * @return the name of the enemy class matching with its image.
     */
    @Override
    public String getEnemyName() {
        return super.getDecoratedEnemy().getEnemyName();
    }
}
