package it.unibo.oop.model.handlers;

import it.unibo.oop.model.entities.Player;
import it.unibo.oop.utils.Direction;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Class that handles the key events for controlling the player.
 *
 */
@SuppressFBWarnings(value = {"EI2"},
justification = "To change the player's position it has to be an externally mutable class.")
public final class InputHandler extends KeyAdapter {
    private final Player player;
    private boolean debugMode;
    private final Set<Integer> pressedKeys = new HashSet<>();
    private Runnable pauseObserver;

    /**
     * Constructor that initializes the InputHandler with the player.
     * @param player the player to control
     */
    public InputHandler(final Player player) {
        this.player = player;
    }

    /**
     * Handles the key press events to change the player's direction.
     * @param e the key event
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        final int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_H) {
            debugMode = !debugMode;
        }
        if (keyCode == KeyEvent.VK_P && pauseObserver != null) {
            pauseObserver.run();
        }
        pressedKeys.add(keyCode);
        updateDirection();
    }

    /**
     * Handles the key release events.
     * @param e the key event
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
        updateDirection();
    }

    /**
     * Updates the player's direction based on currently pressed keys.
     */
    private void updateDirection() {
        final boolean up = pressedKeys.contains(KeyEvent.VK_W);
        final boolean down = pressedKeys.contains(KeyEvent.VK_S);
        final boolean left = pressedKeys.contains(KeyEvent.VK_A);
        final boolean right = pressedKeys.contains(KeyEvent.VK_D);

        if (up && left) {
            player.setDirection(Direction.UPLEFT);
        } else if (up && right) {
            player.setDirection(Direction.UPRIGHT);
        } else if (down && left) {
            player.setDirection(Direction.DOWNLEFT);
        } else if (down && right) {
            player.setDirection(Direction.DOWNRIGHT);
        } else if (up) {
            player.setDirection(Direction.UP);
        } else if (down) {
            player.setDirection(Direction.DOWN);
        } else if (left) {
            player.setDirection(Direction.LEFT);
        } else if (right) {
            player.setDirection(Direction.RIGHT);
        } else {
            player.setDirection(Direction.NONE);
        }
    }
    /**
     * @return if debugMode should be activated.
     */
    public boolean isDebugMode() {
        return debugMode;
    }

    /**
     * Sets the pause observer.
     * @param observer the observer to set
     */
    public void setPauseObserver(final Runnable observer) {
        this.pauseObserver = observer;
    }
}
