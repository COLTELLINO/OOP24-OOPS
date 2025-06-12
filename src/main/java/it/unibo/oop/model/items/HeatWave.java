package it.unibo.oop.model.items;

import java.awt.Rectangle;
import java.util.Collections;
import java.util.List;

import it.unibo.oop.model.entities.Player;

/**
* Represents a HeatWave weapon that creates a damaging wave around the player.
*/
public class HeatWave extends Weapon {

    private static final int BASE_DAMAGE = 15;
    private static final int DAMAGE_PER_LEVEL = 5;
    private static final int BASE_RADIUS = 60;
    private static final int RADIUS_PER_LEVEL = 10;
    private static final int BASE_COOLDOWN = 90;
    private static final int COOLDOWN_DECREASE_PER_LEVEL = 10;
    private static final int MIN_COOLDOWN = 30;
    private static final int DURATION = 15;

    private int duration;
    private int cooldown;
    private boolean showHitbox;
    private boolean isActive;
    private int level;

    public HeatWave(final Player player) {
        super(player);
        this.level = 1;
        this.duration = DURATION;
    }

    @Override
    public int getDamage() {
        final int baseDamage = BASE_DAMAGE + (level - 1) * DAMAGE_PER_LEVEL;
        final Player player = getPlayer();
        if (Math.random() * 100 < player.getCritRate()) {
            return (int) Math.round(baseDamage * player.getCritDamage());
        }
        return baseDamage;
    }

    public int getRadius() {
        return BASE_RADIUS + (getLevel() - 1) * RADIUS_PER_LEVEL;
    }

    public int getCooldown() {
        int cd = BASE_COOLDOWN - (getLevel() - 1) * COOLDOWN_DECREASE_PER_LEVEL;
        return Math.max(cd, MIN_COOLDOWN);
    }

    @Override
    public List<Rectangle> getHitBox() {
        if (isActive) {
            int radius = getRadius();
            int x = getPlayer().getX() + getPlayer().getSize() / 2 - radius;
            int y = getPlayer().getY() + getPlayer().getSize() / 2 - radius;
            Rectangle bounds = new Rectangle(x, y, radius * 2, radius * 2);
            return Collections.singletonList(bounds);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void setShowHitbox(boolean showHitbox) {
        this.showHitbox = showHitbox;
    }

    @Override
    public boolean isShowHitbox() {
        return showHitbox;
    }

    /**
     * Chiama questo metodo ogni tick/frame.
     * Quando il cooldown scade, genera una nuova onda.
     */
    @Override
    public void update() {
        if (isActive) {
            duration--;
            if (duration <= 0) {
                isActive = false;
                this.cooldown = getCooldown();
                this.duration = DURATION;
            }
        } else {
            if (cooldown <= 0) {
                this.isActive = true;
            } else {
                cooldown--;
            }
        }
    }

    /**
     * Gestisce la collisione con i nemici (da chiamare dal CollisionManager).
     */
    @Override
    public void handleCollision() {
    }

    /**
     * Se è attiva l'onda di calore.
     */
    public boolean isActive() {
        return isActive;
    }
}