package it.unibo.oop.model.items;

import java.awt.Rectangle;
import java.util.Collections;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;

/**
 * Represents a CursorSaw weapon in the game.
 * The saw follows the cursor position and deals damage based on its level.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the weapon, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class CursorSaw extends Weapon {

    private static final int BASE_DAMAGE = 20;
    private static final int DAMAGE_PER_LEVEL = 5;
    private static final int BASE_SIZE = 40;
    private static final int SIZE_PER_LEVEL = 8;

    private int cursorX;
    private int cursorY;

    /**
     * Creates a new CursorSaw instance.
     * @param player the player associated with this saw
     */
    public CursorSaw(final Player player) {
        super(player);
    }

    /**
     * Aggiorna la posizione del cursore.
     * @param x the new X coordinate of the cursor.
     * @param y the new Y coordinate of the cursor.
     */
    public void setCursorPosition(final int x, final int y) {
        this.cursorX = x;
        this.cursorY = y;
    }

    /**
     * Gets the base damage dealt by the CursorSaw.
     * @return the base damage dealt by the CursorSaw
     */
    @Override
    protected int getBaseDamage() {
        return BASE_DAMAGE + ((getLevel() - 1) * DAMAGE_PER_LEVEL);
    }

    /**
     * Gets the size of the saw based on its level.
     * The size increases with the level of the saw.
     * 
     * @return the size of the saw
     */
    public int getSawSize() {
        return BASE_SIZE + (getLevel() - 1) * SIZE_PER_LEVEL;
    }

    /**
     * Gets the hitbox of the saw based on its current position.
     * The hitbox is a square centered around the cursor position.
     * 
     * @return a list containing the hitbox rectangle
     */
    @Override
    public List<Rectangle> getHitBox() {
        final int size = getSawSize();
        final int x = cursorX - size / 2;
        final int y = cursorY - size / 2;
        return Collections.singletonList(new Rectangle(x, y, size, size));
    }

    /**
     * Updates the state of the saw.
     * Currently, this method does not perform any actions.
     */
    @Override
    public void update() {
    }

    /**
     * Handles the collision of the saw.
     * Currently, this method does not perform any actions.
     */
    @Override
    public void handleCollision() {
    }

    /**
     * Gets the current cursor X position.
     * 
     * @return the X coordinate of the cursor
     */
    public int getCursorX() {
        return this.cursorX;
    }

    /**
     * Gets the current cursor Y position.
     * 
     * @return the Y coordinate of the cursor
     */
    public int getCursorY() {
        return this.cursorY;
    }
}
