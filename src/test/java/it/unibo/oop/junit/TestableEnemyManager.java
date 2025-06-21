package it.unibo.oop.junit;

import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.managers.EnemyManagerImpl;
/**
 * This class extends EnemyManagerImpl to allow testing of protected methods.
 */
public class TestableEnemyManager extends EnemyManagerImpl {
    /**
     * @param player
     */
    public TestableEnemyManager(final Player player) {
        super(player, null);
    }
    /**
     * Spawns a wave of enemies if there aren't too many on screen.
     */
    @Override
    protected void spawnWaveIfPossible() {
        super.spawnWaveIfPossible();
    }
    /**
     * @return the max amount of enemies.
     */
    @Override
    protected int getMaxEnemies() {
        return super.getMaxEnemies();
    }
}
