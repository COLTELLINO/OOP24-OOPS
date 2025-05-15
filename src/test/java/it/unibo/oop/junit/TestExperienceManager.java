package it.unibo.oop.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.items.ExperienceOrb;
import it.unibo.oop.model.managers.ExperienceManagerImpl;

class TestExperienceManager {

    private static final int PLAYER_X = 0;
    private static final int PLAYER_Y = 0;
    private static final int PLAYER_MAX_HEALTH = 100;
    private static final int PLAYER_HEALTH = 100;
    private static final int PLAYER_ATTACK = 10;
    private static final int PLAYER_SPEED = 5;
    private static final int PLAYER_SIZE = 50;

    private static final int ORB_X = 100;
    private static final int ORB_Y = 100;
    private static final int ORB_XP = 50;

    private ExperienceManagerImpl experienceManager;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(PLAYER_X, PLAYER_Y, PLAYER_MAX_HEALTH, PLAYER_HEALTH, PLAYER_ATTACK, PLAYER_SPEED, PLAYER_SIZE);
        experienceManager = new ExperienceManagerImpl(player);
    }

    @Test
    void testSpawnAndCollectXP() {

        experienceManager.spawnXP(ORB_X, ORB_Y, ORB_XP);
        List<ExperienceOrb> orbs = experienceManager.getOrbs();
        assertEquals(1, orbs.size(), "There should be one orb spawned.");

        player.setX(ORB_X);
        player.setY(ORB_Y);
        experienceManager.update();

        orbs = experienceManager.getOrbs();
        assertTrue(orbs.isEmpty(), "The orb should be collected.");
        assertEquals(ORB_XP, player.getCurrentXP(), "Player should have gained 50 XP.");
    }

    @Test
    void testXPToNextLevel() {
        assertEquals(player.getXPToNextLevel(), experienceManager.getXPToNextLevel(),
            "XP to next level should match the player's value.");
    }
}
