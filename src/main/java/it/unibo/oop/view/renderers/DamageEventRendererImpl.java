package it.unibo.oop.view.renderers;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.unibo.oop.model.events.DamageEvent;

/**
 * Class for rendering a DamageEvent when the player hits an enemy.
 */
public class DamageEventRendererImpl implements DamageEventRenderer {
    private static final int FONT_SIZE = 12;
    private static final int LOW_DAMAGE = 10;
    private static final int MEDIUM_DAMAGE = 20;
    private static final int HIGH_DAMAGE = 30;
    private final Font font;
    /**
     * Constructor for DamageEventRendererImpl.
     * Initializes the font used for rendering damage events.
     */
    public DamageEventRendererImpl() {
        this.font = loadFont();
    }
    /**
     * Draws a DamageEvent.
     * @param g2
     * @param event
     */
    @Override
    public void drawDamageEvent(final Graphics2D g2, final DamageEvent event) {
        if (event.getValue() > 0) {
            g2.setColor(Color.WHITE);
            if (event.getValue() >= LOW_DAMAGE && event.getValue() < MEDIUM_DAMAGE) {
                g2.setColor(Color.YELLOW);
            } else if (event.getValue() >= MEDIUM_DAMAGE && event.getValue() < HIGH_DAMAGE) {
                g2.setColor(Color.ORANGE);
            } else if (event.getValue() >= HIGH_DAMAGE) {
                g2.setColor(Color.RED);
            }
            g2.setFont(font);
            g2.drawString(String.valueOf(event.getValue()), event.getX(), event.getY());
            g2.setFont(new Font("Dialog", Font.PLAIN, FONT_SIZE));
        }
    }
    /**
     * Draws a list of DamageEvents.
     * @param g2
     * @param events
     */
    @Override
    public void drawDamageEventList(final Graphics2D g2, final List<DamageEvent> events) {
        events.forEach(event -> {
            drawDamageEvent(g2, event);
        });
    }
    /**
     * Loads the font used for rendering damage events.
     * @return the loaded font
     */
    private Font loadFont() {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, 
                MapRendererImpl.class.getResourceAsStream("/Font/PressStart2P-Regular.ttf"))
                .deriveFont(Font.PLAIN, FONT_SIZE);
        } catch (FontFormatException | IOException e) {
            Logger.getLogger(this.getClass().getName())
                .log(Level.SEVERE, e.getClass().getSimpleName() + " occurred: ", e);
            return new Font("Dialog", Font.BOLD, FONT_SIZE);
        }
    }
}
