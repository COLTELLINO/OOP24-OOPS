package it.unibo.oop.model.entities;

/**
 * Extension of EnemyDecorator that decorates an enemy into a boss.
 */
public class BossEnemy extends EnemyDecorator {
    private static final int HEALTH_SCALE = 5;
    private static final int ATTACK_SCALE = 2;
    private static final int SIZE_SCALE = 2;
    /**
     * @param enemy
     */
    public BossEnemy(final Enemy enemy) {
        super(enemy);
        super.setSizeScale(enemy.getSizeScale() * SIZE_SCALE);
        super.setMaxHealth(enemy.getMaxHealth() * HEALTH_SCALE);
        super.setHealth(enemy.getHealth() * HEALTH_SCALE);
        super.setAttack(enemy.getAttack() + ATTACK_SCALE);
        super.setSize(enemy.getSize() * SIZE_SCALE);
    }
}
