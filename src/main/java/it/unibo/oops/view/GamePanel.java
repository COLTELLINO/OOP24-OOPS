package it.unibo.oops.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oops.model.EnemyManager;
import it.unibo.oops.model.ExperienceManager;
import it.unibo.oops.model.Player;
import it.unibo.oops.model.WeaponManager;
/**
 * 
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "To move, change direction or change health values of entities they need to be externally mutable.")
public class GamePanel extends MyPanel { 
    @SuppressWarnings("unused") // TEMPORARY
    private static final double serialVersionUID = getSerialVersionUID();

    private final transient Player player;
    private final transient EnemyManager enemyManager;
    private final transient WeaponManager weaponManager;
    @SuppressWarnings("unused") // TEMPORARY
    private final transient ExperienceManager experienceManager;
    /**
     * @param screenWidth
     * @param screenHeight
     * @param player
     * @param enemyManager
     * @param weaponManager
     * @param experienceManager
     */
    public GamePanel(final int screenWidth, final int screenHeight, final Player player, final EnemyManager enemyManager,
            final WeaponManager weaponManager, final ExperienceManager experienceManager) {
        this.player = player;
        this.enemyManager = enemyManager;
        this.weaponManager = weaponManager;
        this.experienceManager = experienceManager;
        super.setPreferredSize(new Dimension(screenWidth, screenHeight));
        super.setBackground(Color.BLACK);
    }
    /**
     * Draws on the Screen.
     * @param g
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        this.player.draw(g2d);
        this.enemyManager.draw(g2d);
        this.weaponManager.draw(g2d);
    }
}
