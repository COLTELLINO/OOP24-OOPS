package it.unibo.oop.junit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.model.entities.Bat;
import it.unibo.oop.model.entities.BossEnemy;
import it.unibo.oop.model.entities.Cultist;
import it.unibo.oop.model.entities.Ghost;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.entities.Skull;
import it.unibo.oop.model.entities.Slime;
import it.unibo.oop.model.entities.Zombie;
import it.unibo.oop.model.factories.EnemyFactory;
import it.unibo.oop.model.factories.EnemyFactoryImpl;
/**
 * Tests all factories.
 */
class TestEnemyFactory {
    private EnemyFactory enemyFactory;
    private Player player;
    /**
     * Preparation for tests.
     */
    @BeforeEach
    void setUp() {
        enemyFactory = new EnemyFactoryImpl();
        player = new Player(0, 0, 0, 0, 0, 0, 0);
    }
    /**
     * Tests the EnemyFactory implementation to ensure it creates the correct enemy types.
     */
    @Test
    void testEnemyFactory() {
        assertInstanceOf(Slime.class, enemyFactory.createBaseSlime(0, 0, player));
        assertInstanceOf(Ghost.class, enemyFactory.createBaseGhost(0, 0, player));
        assertInstanceOf(Zombie.class, enemyFactory.createBaseZombie(0, 0, player));
        assertInstanceOf(Bat.class, enemyFactory.createBaseBat(0, 0, player));
        assertInstanceOf(Cultist.class, enemyFactory.createBaseCultist(0, 0, player));
        assertInstanceOf(Skull.class, enemyFactory.createBaseSkull(0, 0, player));
        assertInstanceOf(BossEnemy.class, enemyFactory.createBoss(enemyFactory.createBaseSlime(0, 0, player)));
        assertFalse(enemyFactory.createBaseGhost(0, 0, player) instanceof Slime);
    }

}
