package it.unibo.oop.view.renderers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import it.unibo.oop.model.items.ExperienceOrb;

/**
 * Class for rendering experience-related elements.
 */
public class ExperienceRendererImpl implements ExperienceRenderer {
    private static final Logger LOGGER = Logger.getLogger(ExperienceRendererImpl.class.getName());
    private static final double SCALE = 0.5;
    private static final int MEDIUM_ORB = 100;
    private static final int BIG_ORB = 500;

    private final Image orbImageSmall;
    private final Image orbImageMedium;
    private final Image orbImageBig;

    /**
     * Constructs an ExperienceRendererImpl and loads the orb images.
     */
    public ExperienceRendererImpl() {
        try {
            this.orbImageSmall = ImageIO.read(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Experience/orb1.png"),
                "Resource 'Experience/orb1.png' not found."
            ));
            this.orbImageMedium = ImageIO.read(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Experience/orb2.png"),
                "Resource 'Experience/orb2.png' not found."
            ));
            this.orbImageBig = ImageIO.read(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Experience/orb3.png"),
                "Resource 'Experience/orb3.png' not found."
            ));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Orb images could not be loaded.", e);
            throw new IllegalStateException("Orb images could not be loaded.", e);
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
        final List<ExperienceOrb> orbsCopy = new ArrayList<>(orbs);
        orbsCopy.addAll(orbs);
        for (final ExperienceOrb orb : orbsCopy) {
            final int drawX = orb.getX();
            final int drawY = orb.getY();

            final Image orbImageToDraw;
            if (orb.getXP() >= BIG_ORB) {
                orbImageToDraw = orbImageBig;
            } else if (orb.getXP() >= MEDIUM_ORB) {
                orbImageToDraw = orbImageMedium;
            } else {
                orbImageToDraw = orbImageSmall;
            }

            final AffineTransform transform = new AffineTransform();
            transform.translate(drawX, drawY);
            transform.scale(SCALE, SCALE);

            g2d.drawImage(orbImageToDraw, transform, null);
        }
    }
}
