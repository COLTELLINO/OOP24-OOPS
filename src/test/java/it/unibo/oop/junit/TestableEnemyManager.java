package it.unibo.oop.junit;

import it.unibo.oop.model.EnemyManagerImpl;
import it.unibo.oop.model.Player;
/**
 * Class to Test if EnemyManagerImpl is working as intended.
 */
public class TestableEnemyManager extends EnemyManagerImpl {
    /**
     * @param player
     */
    public TestableEnemyManager(final Player player) {
        super(player);
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
