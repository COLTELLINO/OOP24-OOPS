package it.unibo.oop.view.window;

import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import it.unibo.oop.controller.gamethread.MouseHandler;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.managers.CollisionManager;
import it.unibo.oop.model.managers.EnemyManager;
import it.unibo.oop.model.managers.ExperienceManager;
import it.unibo.oop.model.managers.HealthManager;
import it.unibo.oop.model.managers.ProjectileManager;
import it.unibo.oop.model.managers.WeaponManager;
import it.unibo.oop.utils.GameState;
import it.unibo.oop.view.panels.GamePanel;
import it.unibo.oop.view.panels.MyPanel;
import it.unibo.oop.view.panels.OptionPanel;
import it.unibo.oop.view.panels.PausePanel;
import it.unibo.oop.view.panels.TestPanel;
import it.unibo.oop.view.panels.TitlePanel;
import it.unibo.oop.view.panels.GameOverPanel;
import it.unibo.oop.utils.Camera;

import java.awt.Dimension;
import java.awt.Toolkit;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * 
 */
public final class ViewManagerImpl implements ViewManager {
    private static final String FRAME_NAME = "OOP Survivors";
    private static final int PROPORTION = 3;

    private final JFrame frame = new JFrame(FRAME_NAME);
    private final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private final int sw = (int) d.getWidth();
    private final int sh = (int) d.getHeight();
    private GameState currentGameState;
    private MyPanel currentPanel;
    private final TitlePanel titlePanel;
    private final OptionPanel optionPanel;
    private final GamePanel gamePanel;
    private final TestPanel testPanel;
    private final PausePanel pausePanel;
    private final GameOverPanel gameOverPanel;
    private final MouseHandler mouseHandler = new MouseHandler();
    /**
     * @param gameState
     * @param player
     * @param enemyManager
     * @param weaponManager
     * @param experienceManager
     * @param collisionManager
     * @param healthManager
     * @param projectileManager
     * @param camera
     */
    public ViewManagerImpl(final GameState gameState, final Player player, final EnemyManager enemyManager,
        final WeaponManager weaponManager, final ExperienceManager experienceManager, 
        final CollisionManager collisionManager, final HealthManager healthManager,
        final ProjectileManager projectileManager, final Camera camera) {
        this.titlePanel = new TitlePanel(this.sw / PROPORTION, this.sh / PROPORTION, this);
        this.optionPanel = new OptionPanel(this.sw / PROPORTION, this.sh / PROPORTION, this);
        this.gamePanel = new GamePanel(this.sw / PROPORTION, this.sh / PROPORTION, 
        player, enemyManager, weaponManager, experienceManager, collisionManager, healthManager, projectileManager, camera);
        testPanel = new TestPanel(this.sw / PROPORTION, this.sh / PROPORTION);
        this.pausePanel = new PausePanel(this.sw / PROPORTION, this.sh / PROPORTION, this);
        this.gameOverPanel = new GameOverPanel(this.sw / PROPORTION, this.sh / PROPORTION, this);

        this.gamePanel.addMouseListener(mouseHandler);
        this.gamePanel.addMouseMotionListener(mouseHandler);

        this.changeGameState(gameState);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.start();
    }

    /**
     * Returns the MouseHandler for mouse input.
     * Suppress SpotBugs warning: MouseHandler is stateless and safe to expose.
     * @return the MouseHandler
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "MouseHandler is stateless and safe to expose")
    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }
    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            this.frame.setVisible(true);
        });
    }

    @Override
    public void changeGameState(final GameState gameState) {
        if (currentGameState != gameState) {
            this.currentGameState = gameState;
            switch (currentGameState) {
                case TITLESTATE -> {
                    this.currentPanel = titlePanel;
                }
                case TITLEOPTIONSTATE -> {
                    this.currentPanel = optionPanel;
                }
                case PLAYSTATE -> {
                    this.currentPanel = gamePanel;
                }
                case TESTSTATE -> {
                    this.currentPanel = testPanel;
                }
                case PAUSEMENU -> {
                    this.currentPanel = pausePanel;
                }
                case GAMEOVER -> {
                    this.currentPanel = gameOverPanel;
                }
                default -> throw new IllegalArgumentException();
            }
            this.setState();
        }
    }
    /**
    *  Sets the current panel on the screen.
    */
    private void setState() {
        SwingUtilities.invokeLater(() -> {
            this.frame.setContentPane(this.currentPanel);
            this.frame.pack();
        });
    }
    @Override
    public GameState getCurrentGameState() {
        return this.currentGameState;
    }
    @Override
    public void repaint() {
        SwingUtilities.invokeLater(() -> {
            this.currentPanel.repaint();
        });
    }
    /**
     * Adds a key listener to the frame.
     * 
     * @param listener the key listener to add
     */
    @Override
    public void addKeyListener(final KeyListener listener) {
        frame.addKeyListener(listener);
    }

    /**
     * Sets whether the frame is focusable.
     * 
     * @param focusable true if the frame should be focusable
     */
    @Override
    public void setFocusable(final boolean focusable) {
        frame.setFocusable(focusable);
        frame.requestFocusInWindow();
    }

    @Override
    public int getGameScreenWidth() {
        // return this.sw / PROPORTION;
        return this.gamePanel.getWidth();
    }

    @Override
    public int getGameScreenHeight() {
        // return this.sh / PROPORTION;
        return this.gamePanel.getHeight();
    }
}
