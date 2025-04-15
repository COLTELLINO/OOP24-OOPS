package it.unibo.oops.model;
/**
 * Extension of EnemyDecorator that decorates an enemy into a boss.
 */
public class BossEnemy extends EnemyDecorator {
    private static final int SPEED_SCALE = 2;
    private static final int SIZE_SCALE = 2;
    private final int sizeScale;
    /**
     * @param enemy
     */
    public BossEnemy(final Enemy enemy) {
        super(enemy);
        this.sizeScale = enemy.getSizeScale() * SIZE_SCALE;
        super.setSpeed(enemy.getSpeed() * SPEED_SCALE);
        super.setSize(enemy.getSize() * SIZE_SCALE);
    }
    /**
     * @return the target player.
     */
    @Override
    public int getSizeScale() {
        return this.sizeScale;
    }
    /**
     * @return the name of the enemy class matching with its image.
     */
    @Override
    public String getEnemyName() {
        return super.getDecoratedEnemy().getEnemyName();
    }
}
