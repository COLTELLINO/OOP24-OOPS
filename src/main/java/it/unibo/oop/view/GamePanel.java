package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.EnemyManager;
import it.unibo.oop.model.ExperienceManager;
import it.unibo.oop.model.Player;
import it.unibo.oop.model.ProjectileManager;
import it.unibo.oop.model.WeaponManager;
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
    private final transient ProjectileManager projectileManager;
    private final transient WeaponManager weaponManager;
    private final transient ExperienceManager experienceManager;
    private final transient EnemyRenderer enemyRenderer = new EnemyRendererImpl();
    private final transient WeaponRenderer weaponRenderer;
    private final transient ExperienceRenderer experienceRenderer = new ExperienceRendererImpl();
    private final transient ProjectileRenderer projectileRenderer = new ProjectileRendererImpl();
    /**
     * @param screenWidth
     * @param screenHeight
     * @param player
     * @param enemyManager
     * @param weaponManager
     * @param experienceManager
     * @param projectileManager
     */
    public GamePanel(final int screenWidth, final int screenHeight, final Player player, 
            final EnemyManager enemyManager, final WeaponManager weaponManager,
            final ExperienceManager experienceManager, final ProjectileManager projectileManager) {
        this.player = player;
        this.enemyManager = enemyManager;
        this.projectileManager = projectileManager;
        this.weaponManager = weaponManager;
        this.experienceManager = experienceManager;
        weaponRenderer = new WeaponRendererImpl(player);
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
        this.enemyRenderer.drawEnemyList(this.enemyManager.getSpawnedEnemies(), g2d);
        this.projectileRenderer.drawProjectileList(this.projectileManager.getEnemyProjectiles(), g2d);
        this.projectileRenderer.drawProjectileList(this.projectileManager.getPlayerProjectiles(), g2d);
        this.weaponRenderer.drawWeaponList(g2d, this.weaponManager.getWeapons());
        this.experienceRenderer.drawExperienceOrbs(g2d, this.experienceManager.getOrbs());
        // Disegna la barra dell'XP
        drawXPBar(g2d);
    }

    private void drawXPBar(final Graphics2D g2d) {
        final int currentXP = this.experienceManager.getCurrentXP();
        final int xpToNextLevel = this.experienceManager.getXPToNextLevel();
        final int barWidth = 300;
        final int barHeight = 20;
        final int x = 20;
        final int offset = 5;
        final int y = getHeight() - 40;

        final double xpRatio = (double) currentXP / xpToNextLevel;
        final int filledWidth = (int) (barWidth * xpRatio);

        // Sfondo della barra (grigio)
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(x, y, barWidth, barHeight);

        // Parte riempita (verde)
        g2d.setColor(Color.GREEN);
        g2d.fillRect(x, y, filledWidth, barHeight);

        // Bordo bianco
        g2d.setColor(Color.WHITE);
        g2d.drawRect(x, y, barWidth, barHeight);

        // Testo XP
        g2d.setColor(Color.WHITE);
        g2d.drawString("XP: " + currentXP + " / " + xpToNextLevel, x + offset, y - offset);

        g2d.setColor(Color.WHITE);
        g2d.drawString("XP: " + currentXP + " / " + xpToNextLevel, x + offset, y - offset);

        // Mostra anche il livello del giocatore
        g2d.drawString("LVL: " + this.player.getLevel(), x + barWidth + offset * 2, y + barHeight - offset);
    }
}
