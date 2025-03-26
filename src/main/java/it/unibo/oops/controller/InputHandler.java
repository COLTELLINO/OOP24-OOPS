package it.unibo.oops.controller;

import it.unibo.oops.model.Player;
import it.unibo.oops.model.Direction;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Class that handles the key events for controlling the player.
 * 
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "To change the player's position it has to be an externally mutable class.")
public final class InputHandler extends KeyAdapter {
    private final Player player;

    /**
     * Constructor that initializes the InputHandler with the player.
     * @param player the player to control
     */
    public InputHandler(final Player player) {
        this.player = player;
    }

    /**
     * Handles the key press events to change the player's direction.
     * @param e the KeyEvent that contains the key code
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        final int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            player.setDirection(Direction.UP);
        } else if (key == KeyEvent.VK_S) {
            player.setDirection(Direction.DOWN);
        } else if (key == KeyEvent.VK_A) {
            player.setDirection(Direction.LEFT);
        } else if (key == KeyEvent.VK_D) {
            player.setDirection(Direction.RIGHT);
        }
    }

    /**
     * Handles the key release events. Subclasses can override this method to provide
     * custom behavior, but they must ensure to call the superclass implementation
     * to maintain the default behavior.
     * 
     * @param e the KeyEvent that contains the key code
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        final int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_S || key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
            player.setDirection(Direction.NONE);
        }
    }
}
