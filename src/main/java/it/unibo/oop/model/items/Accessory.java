package it.unibo.oop.model.items;

import it.unibo.oop.model.Upgrade;
import it.unibo.oop.model.entities.Player;

/**
 * Abstract class representing an accessory.
 */
public abstract class Accessory extends Upgrade {

    /**
     * Constructs an Accessory.
     * 
     * @param player the player associated with this accessory
     */
    public Accessory(final Player player) {
        super(player);
    }

    /**
     * Updates the accessory.
     */
    @Override
    public abstract void update();
}
