package it.unibo.oop.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import it.unibo.oop.model.entities.Player;
import it.unibo.oop.utils.Direction;

class PlayerTest {

    private static final int START_X = 100;
    private static final int START_Y = 100;
    private static final int PLAYER_HEALTH = 100;
    private static final int PLAYER_ATTACK = 10;
    private static final int PLAYER_SPEED = 5;
    private static final int PLAYER_SIZE = 50;
    private static final int EXPECTED_Y_AFTER_UP = 95;
    private static final int NEGATIVE_HEALTH = -50;

    @Test
    void testPlayerMovementUp() {
        final Player player = new Player(
            START_X, START_Y, PLAYER_HEALTH, PLAYER_HEALTH,
            PLAYER_ATTACK, PLAYER_SPEED, PLAYER_SIZE
        );
        player.setDirection(Direction.UP);
        player.update();
        assertEquals(START_X, player.getX());
        assertEquals(EXPECTED_Y_AFTER_UP, player.getY());
    }

    @Test
    void testPlayerHealthCannotGoNegative() {
        final Player player = new Player(0, 0, PLAYER_HEALTH, 10, PLAYER_ATTACK, PLAYER_SPEED, PLAYER_SIZE);
        player.setHealth(NEGATIVE_HEALTH);
        assertEquals(0, player.getHealth());
    }

    @Test
    void testPlayerDiesAtZeroHealth() {
        final Player player = new Player(0, 0, PLAYER_HEALTH, 10, PLAYER_ATTACK, PLAYER_SPEED, PLAYER_SIZE);
        player.setHealth(0);
        player.update();
        assertFalse(player.isAlive());
    }
}
