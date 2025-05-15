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
 * Draws the player with animated sprites for each cardinal direction.
 */
public class PlayerRendererImpl implements PlayerRenderer {

    private static final Logger LOGGER = Logger.getLogger(PlayerRendererImpl.class.getName());
    private static final String SPRITE_PATH = "/Player/";
    private static final String SPRITE_PREFIX = "Player_";

    private static final int FRAME_COUNT = 2; // 2 frame per direzione
    private static final long FRAME_DURATION_MS = 200; // durata di ogni frame

    private final Map<Direction, BufferedImage[]> directionSprites = new EnumMap<>(Direction.class);

    /**
     * Constructor loads all directional animation frames.
     */
    public PlayerRendererImpl() {
        loadSprites();
    }

    /**
     * Loads two frames per direction (UP, DOWN, LEFT, RIGHT).
     */
    private void loadSprites() {
        for (final Direction dir : Direction.values()) {
            if (isCardinalDirection(dir)) {
                final BufferedImage[] frames = new BufferedImage[FRAME_COUNT];
                for (int i = 0; i < FRAME_COUNT; i++) {
                    final String path = SPRITE_PATH + SPRITE_PREFIX + dir.name() + "_" + (i + 1) + ".png";
                    try {
                        frames[i] = ImageIO.read(PlayerRendererImpl.class.getResource(path));
                    } catch (IOException | IllegalArgumentException e) {
                        LOGGER.log(Level.WARNING, "Missing sprite: " + path, e);
                    }
                }
                directionSprites.put(dir, frames);
            }
        }
    }

    /**
     * Draws the player using animated sprite based on direction.
     * @param player the player to draw
     * @param g2 the graphics context
     */
    @Override
    public void drawPlayer(final Player player, final Graphics2D g2) {
        Direction dir = player.getDirection();
        final BufferedImage[] frames;
        final int frameIndex;

        if (dir == Direction.NONE) {
            frames = directionSprites.get(Direction.DOWN);
            frameIndex = 0;
        } else {
            if (!isCardinalDirection(dir)) {
                dir = Direction.RIGHT;
            }
            frames = directionSprites.get(dir);
            frameIndex = (int) (System.currentTimeMillis() / FRAME_DURATION_MS % FRAME_COUNT);
        }

        if (frames == null || frames[0] == null) {
            LOGGER.warning("Missing sprite frames for direction: " + dir);
            return;
        }

        final BufferedImage sprite = frames[frameIndex] != null ? frames[frameIndex] : frames[0];
        g2.drawImage(sprite, player.getX(), player.getY(), player.getSize(), player.getSize(), null);
    }

    /**
     * Only allow UP, DOWN, LEFT, RIGHT.
     * @param dir the direction to check
     * @return true if the direction is cardinal (UP, DOWN, LEFT, RIGHT), false otherwise
     */
    private boolean isCardinalDirection(final Direction dir) {
        return dir == Direction.UP || dir == Direction.DOWN || dir == Direction.LEFT || dir == Direction.RIGHT;
    }
}
