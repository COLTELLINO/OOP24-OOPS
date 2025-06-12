package it.unibo.oop.model.items;

import java.awt.Rectangle;
import java.util.Collections;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;

@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the weapon, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class CursorSaw extends Weapon {

    private static final int BASE_DAMAGE = 20;
    private static final int DAMAGE_PER_LEVEL = 5;
    private static final int BASE_SIZE = 40;
    private static final int SIZE_PER_LEVEL = 8;

    private boolean showHitbox;
    private int cursorX;
    private int cursorY;
    private int level;

    public CursorSaw(final Player player) {
        super(player);
        this.level = 1;
    }

    /**
     * Aggiorna la posizione del cursore (da chiamare dal MouseHandler).
     */
    public void setCursorPosition(final int x, final int y) {
        this.cursorX = x;
        this.cursorY = y;
    }

    @Override
    public int getDamage() {
        final int baseDamage = BASE_DAMAGE + ((level - 1) * DAMAGE_PER_LEVEL);
        final Player player = getPlayer();
        if (Math.random() * 100 < player.getCritRate()) {
            return (int) Math.round(baseDamage * player.getCritDamage());
        }
        return baseDamage;
    }

    public int getSawSize() {
        return BASE_SIZE + (level - 1) * SIZE_PER_LEVEL;
    }

    @Override
    public List<Rectangle> getHitBox() {
        int size = getSawSize();
        int x = cursorX - size / 2;
        int y = cursorY - size / 2;
        return Collections.singletonList(new Rectangle(x, y, size, size));
    }

    @Override
    public void setShowHitbox(boolean showHitbox) {
        this.showHitbox = showHitbox;
    }

    @Override
    public boolean isShowHitbox() {
        return this.showHitbox;
    }

    @Override
    public void update() {
    }

    @Override
    public void handleCollision() {
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(final int level) {
        this.level = level;
    }

    public int getCursorX() {
        return this.cursorX;
    }

    public int getCursorY() {
        return this.cursorY;
    }
}