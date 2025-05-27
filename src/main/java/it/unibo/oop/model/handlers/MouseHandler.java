package it.unibo.oop.model.handlers;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Handles mouse input: position and click state.
 */
public final class MouseHandler extends MouseAdapter {
    private Point mousePosition = new Point(0, 0);
    private boolean mouseClicked;

    @Override
    public void mouseMoved(final MouseEvent e) {
        mousePosition = e.getPoint();
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        mousePosition = e.getPoint();
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        mouseClicked = true;
    }

    /**
     * Returns the current mouse position.
     * @return a copy of the mouse position
     */
    public Point getMousePosition() {
        return new Point(mousePosition);
    }

    /**
     * Returns true if the mouse was clicked since the last clear.
     * @return true if clicked
     */
    public boolean isMouseClicked() {
        return mouseClicked;
    }

    /**
     * Clears the mouse click state.
     */
    public void clearMouseClick() {
        mouseClicked = false;
    }
}
