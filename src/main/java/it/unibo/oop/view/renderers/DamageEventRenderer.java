package it.unibo.oop.view.renderers;


import java.awt.Graphics2D;
import java.util.List;
import it.unibo.oop.model.events.DamageEvent;

/**
 * Interface for rendering a DamageEvent when the player hits an enemy.
 */
public interface DamageEventRenderer {
    /**
     * Draws a DamageEvent.
     * @param g2
     * @param event
     */
    void drawDamageEvent(Graphics2D g2, DamageEvent event);
    /**
     * Draws a list of DamageEvents.
     * @param g2
     * @param events
     */
    void drawDamageEventList(Graphics2D g2, List<DamageEvent> events);
}
