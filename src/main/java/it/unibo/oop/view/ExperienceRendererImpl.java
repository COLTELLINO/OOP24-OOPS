package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import it.unibo.oop.model.ExperienceOrb;
/**
 * Class for rendering experience-related elements.
 */
public class ExperienceRendererImpl implements ExperienceRenderer {
    /**
     * Draws the experience orbs.
     * 
     * @param g the graphics context
     * @param orbs the list of experience orbs to draw
     */
    @Override
    public void drawExperienceOrbs(final Graphics g, final List<ExperienceOrb> orbs) {
        for (final ExperienceOrb orb : orbs) {
            g.setColor(Color.PINK);
            g.fillRoundRect(orb.getX(), orb.getY(), 10, 10, 10, 10);
        }
    }
}
