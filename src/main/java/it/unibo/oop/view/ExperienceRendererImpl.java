package it.unibo.oop.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import it.unibo.oop.model.ExperienceOrb;

/**
 * Class for rendering experience-related elements.
 */
public class ExperienceRendererImpl implements ExperienceRenderer {
    private static final Logger LOGGER = Logger.getLogger(ExperienceRendererImpl.class.getName());
    private static final double SCALE = 0.5;
    private final Image orbImage;

    /**
     * Constructs an ExperienceRendererImpl and loads the orb image.
     */
    public ExperienceRendererImpl() {
        try {
            this.orbImage = ImageIO.read(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Experience/orb1.png"),
                "Resource 'Experience/orb1.png' not found."
            ));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Orb image could not be loaded.", e);
            throw new IllegalStateException("Orb image could not be loaded.", e);
        }
    }

    /**
     * Draws the experience orbs.
     * 
     * @param g the graphics context
     * @param orbs the list of experience orbs to draw
     */
    @Override
    public void drawExperienceOrbs(final Graphics g, final List<ExperienceOrb> orbs) {
        if (!(g instanceof Graphics2D)) {
            LOGGER.log(Level.WARNING, "Graphics object is not an instance of Graphics2D.");
            return;
        }

        final Graphics2D g2d = (Graphics2D) g;

        for (final ExperienceOrb orb : orbs) {
            final int drawX = orb.getX();
            final int drawY = orb.getY();

            final AffineTransform transform = new AffineTransform();
            transform.translate(drawX, drawY);
            transform.scale(SCALE, SCALE);

            g2d.drawImage(orbImage, transform, null);
        }
    }
}
