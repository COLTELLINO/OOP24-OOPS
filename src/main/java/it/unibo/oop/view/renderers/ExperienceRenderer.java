package it.unibo.oop.view.renderers;

import java.awt.Graphics;
import java.util.List;

import it.unibo.oop.model.items.ExperienceOrb;

/**
 * Interface for rendering experience-related elements.
 */
public interface ExperienceRenderer {
    /**
     * Draws the experience orbs.
     * 
     * @param g the graphics context
     * @param orbs the list of experience orbs to draw
     */
    void drawExperienceOrbs(Graphics g, List<ExperienceOrb> orbs);
}
