package it.unibo.oop.utils;

import it.unibo.oop.model.entities.Player;

/**
 * Class that represents the camera in the game.
 * It is used to follow the player and keep the player in the center of the screen.
 */
public final class Camera {
    private float x;
    private float y;

    /**
     * Constructor of the camera.
     * @param x the x coordinate of the camera
     * @param y the y coordinate of the camera
     */
    public Camera(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Updates the camera position to center the player.
     * @param player the player to follow
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     */
    public void update(final Player player, final int screenWidth, final int screenHeight) {
        this.x = player.getX() - screenWidth / 2 + player.getSize() / 2;
        this.y = player.getY() - screenHeight / 2 + player.getSize() / 2;
    }

    /**
     * Sets the x coordinate of the camera.
     * @param x the x coordinate
     */
    public void setX(final float x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate of the camera.
     * @param y the y coordinate
     */
    public void setY(final float y) {
        this.y = y;
    }

    /**
     * @return the x coordinate of the camera
     */
    public float getX() {
        return x;
    }

    /**
     * @return the y coordinate of the camera
     */
    public float getY() {
        return y;
    }
}
