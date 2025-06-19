package it.unibo.oop.view.renderers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Implementation of the MapRenderer interface.
 * This class is responsible for rendering the game map using textures.
 */
public class MapRendererImpl implements MapRenderer {
    private static final int MAPSIZE = 5000;
    private static final int BORDER = MAPSIZE * 2;
    private static final int PLAYER_SIZE = 50;
    private static final int G_VALUE = 50;
    private static final int B_VALUE = 100;
    private BufferedImage mapImage;
    private final List<BufferedImage> mapTextures = new ArrayList<>();
    private final Random random = new Random();

    /**
     * Draws the map on screen.
     * @param g2
     */
    @Override
    public void drawMap(final Graphics2D g2) {
        if (mapImage == null) {
            createMapImage(g2);
        }
        g2.setColor(new Color(0, G_VALUE, B_VALUE));
        g2.fillRect(-BORDER, -BORDER, 2 * BORDER, 2 * BORDER);
        g2.drawImage(mapImage, -MAPSIZE, -MAPSIZE, null);
    }
    /**
     * Creates the map image by loading textures and drawing them.
     * @param g2
     */
    @Override
    public void createMapImage(final Graphics2D g2) {
        this.loadTextures();
        mapImage = new BufferedImage(2 * MAPSIZE + PLAYER_SIZE, 2 * MAPSIZE + PLAYER_SIZE, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2d = mapImage.createGraphics();

        final int tileWidth = mapTextures.get(0).getWidth();
        final int tileHeight = mapTextures.get(0).getHeight();

        for (int y = 0; y < 2 * MAPSIZE; y += tileHeight) {
            for (int x = 0; x < 2 * MAPSIZE; x += tileWidth) {
                final BufferedImage tileTexture = mapTextures.get(random.nextInt(mapTextures.size()));
                g2d.drawImage(tileTexture, x, y, null);
            }
        }
        g2d.dispose();
    }
    /**
     * Loads grass textures from resources.
     */
    private void loadTextures() {
        if (!mapTextures.isEmpty()) {
            return;
        }
        try {
            mapTextures.add(javax.imageio.ImageIO.read(MapRendererImpl.class.getResource("/Map/Grass_1.png")));
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName())
                .log(Level.SEVERE, e.getClass().getSimpleName() + " occurred: ", e);
        }
    }
}
