package it.unibo.oop.view.renderers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import it.unibo.oop.model.entities.Player;
import it.unibo.oop.utils.Direction;

/**
 * Draws the player.
 */
public class PlayerRendererImpl implements PlayerRenderer {

    private static final Logger LOGGER = Logger.getLogger(PlayerRendererImpl.class.getName());
    private static final String SPRITE_PATH = "/Player/";
    private static final String SPRITE_PREFIX = "Player_";

    private final Map<Direction, BufferedImage> directionSprites = new EnumMap<>(Direction.class);

    /**
     * Constructor loads all direction sprites.
     */
    public PlayerRendererImpl() {
        loadSprites();
    }

    /**
     * Loads all directional sprites.
     */
    private void loadSprites() {
        for (final Direction dir : Direction.values()) {
            try {
                final String path = SPRITE_PATH + SPRITE_PREFIX + dir.name() + ".png";
                final BufferedImage img = ImageIO.read(PlayerRendererImpl.class.getResource(path));
                directionSprites.put(dir, img);
            } catch (IOException | IllegalArgumentException e) {
                LOGGER.log(Level.WARNING, "Sprite missing or invalid for direction " + dir.name(), e);
            }
        }
    }

    /**
     * Draws the player with the correct directional sprite.
     * @param player the player
     * @param g2 the graphics context
     */
    @Override
    public void drawPlayer(final Player player, final Graphics2D g2) {
        final Direction dir = player.getDirection();
        final BufferedImage sprite = directionSprites.getOrDefault(dir, directionSprites.get(Direction.DOWN));

        if (sprite != null) {
            g2.drawImage(sprite, player.getX(), player.getY(), player.getSize(), player.getSize(), null);
        } else {
            LOGGER.warning("No sprite found for player direction: " + dir);
        }
    }
}
