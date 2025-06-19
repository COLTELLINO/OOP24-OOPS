package it.unibo.oop.view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.managers.CollisionManager;
import it.unibo.oop.model.managers.EnemyManager;
import it.unibo.oop.model.managers.ExperienceManager;
import it.unibo.oop.model.managers.HealthManager;
import it.unibo.oop.model.managers.ProjectileManager;
import it.unibo.oop.model.managers.WeaponManager;
import it.unibo.oop.view.renderers.DamageEventRenderer;
import it.unibo.oop.view.renderers.DamageEventRendererImpl;
import it.unibo.oop.view.renderers.EnemyRenderer;
import it.unibo.oop.view.renderers.EnemyRendererImpl;
import it.unibo.oop.view.renderers.ExperienceRenderer;
import it.unibo.oop.view.renderers.ExperienceRendererImpl;
import it.unibo.oop.view.renderers.HealthRenderer;
import it.unibo.oop.view.renderers.HealthRendererImpl;
import it.unibo.oop.view.renderers.MapRenderer;
import it.unibo.oop.view.renderers.MapRendererImpl;
import it.unibo.oop.view.renderers.PlayerRenderer;
import it.unibo.oop.view.renderers.PlayerRendererImpl;
import it.unibo.oop.view.renderers.ProjectileRenderer;
import it.unibo.oop.view.renderers.ProjectileRendererImpl;
import it.unibo.oop.view.renderers.WeaponRenderer;
import it.unibo.oop.view.renderers.WeaponRendererImpl;
import it.unibo.oop.utils.Camera;
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
    private final transient CollisionManager collisionManager;
    private final transient HealthManager healthManager;
    private final transient EnemyRenderer enemyRenderer = new EnemyRendererImpl();
    private final transient WeaponRenderer weaponRenderer;
    private final transient ExperienceRenderer experienceRenderer = new ExperienceRendererImpl();
    private final transient ProjectileRenderer projectileRenderer = new ProjectileRendererImpl();
    private final transient PlayerRenderer playerRenderer = new PlayerRendererImpl();
    private final transient HealthRenderer healthRenderer = new HealthRendererImpl();
    private final transient MapRenderer mapRenderer = new MapRendererImpl();
    private final transient DamageEventRenderer damageEventRenderer = new DamageEventRendererImpl();
    private final transient Camera camera;
    /**
     * @param screenWidth
     * @param screenHeight
     * @param player
     * @param enemyManager
     * @param weaponManager
     * @param experienceManager
     * @param collisionManager
     * @param healthManager
     * @param projectileManager
     * @param camera
     */
    public GamePanel(final int screenWidth, final int screenHeight, final Player player, 
            final EnemyManager enemyManager, final WeaponManager weaponManager,
            final ExperienceManager experienceManager, final CollisionManager collisionManager,
            final HealthManager healthManager, final ProjectileManager projectileManager,
            final Camera camera) {
        this.player = player;
        this.enemyManager = enemyManager;
        this.projectileManager = projectileManager;
        this.weaponManager = weaponManager;
        this.experienceManager = experienceManager;
        this.collisionManager = collisionManager;
        this.healthManager = healthManager;
        this.camera = camera;
        weaponRenderer = new WeaponRendererImpl();
        super.setPreferredSize(new Dimension(screenWidth, screenHeight));
        super.setBackground(Color.BLACK);
        this.mapRenderer.createMapImage(null);
    }
    /**
     * Draws on the Screen.
     * @param g
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        // 1. Inizio camera: traslazione negativa
        g2d.translate(-camera.getX(), -camera.getY());
        // 2. Disegna tutto ciò che segue la camera
        this.mapRenderer.drawMap(g2d);
        this.playerRenderer.drawPlayer(this.player, g2d);
        this.enemyRenderer.drawEnemyList(this.enemyManager.getSpawnedEnemies(), g2d);
        this.projectileRenderer.drawProjectileList(this.projectileManager.getAllProjectiles(), g2d);
        this.weaponRenderer.drawWeaponList(g2d, this.weaponManager.getWeapons());
        this.experienceRenderer.drawExperienceOrbs(g2d, this.experienceManager.getOrbs());
        this.damageEventRenderer.drawDamageEventList(g2d, this.collisionManager.getDamageEvents());

        // 3. Fine camera: ripristina traslazione
        g2d.translate(camera.getX(), camera.getY());

        // 4. Disegna HUD/barre che restano fisse
        drawXPBar(g2d);
        drawHealthBar(g2d);
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

    private void drawHealthBar(final Graphics2D g2d) {
        this.healthRenderer.drawHealthBar(
            g2d, 
            this.healthManager.getHealth(), 
            this.healthManager.getMaxHealth()
        );
    }
}
