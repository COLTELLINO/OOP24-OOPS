package it.unibo.oops.model;
/**
 * 
 */
public abstract class EnemyDecorator extends Enemy {
    private final Enemy decoratedEnemy;
    /**
     * @param enemy
     */
    protected EnemyDecorator(final Enemy enemy) {
        super(enemy.getX(), enemy.getY(), enemy.getMaxHealth(), enemy.getHealth(), 
            enemy.getAttack(), enemy.getSpeed(), enemy.getSize(), enemy.getPlayer());
        this.decoratedEnemy = enemy;
    }
    /**
     * @return the enemy to decorate.
     */
    protected Enemy getDecoratedEnemy() {
        return this.decoratedEnemy;
    }
    // /**
    //  * Updates current enemy.
    //  */
    // @Override
    // public void update() {
    //     super.update();
    // }
}
