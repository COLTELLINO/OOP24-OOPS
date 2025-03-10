package it.unibo.oops.view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import it.unibo.oops.controller.gamestate.GameState;

import java.awt.Dimension;
import java.awt.Toolkit;
/**
 * 
 */
public final class DrawViewImpl implements DrawView {
    private static final String FRAME_NAME = "OOP Survivors";
    private static final int PROPORTION = 3;

    private final JFrame frame = new JFrame(FRAME_NAME);
    private final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private final int sw = (int) d.getWidth();
    private final int sh = (int) d.getHeight();
    private GameState currentGameState;
    private MyPanel currentPanel;
    /**
     * @param gameState
     */
    public DrawViewImpl(final GameState gameState) {
        SwingUtilities.invokeLater(() -> {
            this.changeGameState(gameState);
            this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.frame.setLocationRelativeTo(null);
            this.start();
        });
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
                case TITLESTATE, TITLEOPTIONSTATE, PAUSESTATE -> 
                this.currentPanel = new TitlePanel(sw / PROPORTION, sh / PROPORTION);
                case PLAYSTATE -> this.currentPanel = new GamePanel(sw / PROPORTION, sh / PROPORTION);
                case TESTSTATE -> this.currentPanel = new TestPanel(sw / PROPORTION, sh / PROPORTION);
                default -> throw new IllegalArgumentException();
            }
            setState();
        }
    }
    private void setState() {
        SwingUtilities.invokeLater(() -> {
            this.frame.setContentPane(this.currentPanel);
            this.frame.pack();
        });
    }
    /**
     *  @return the current gameState.
     */
    public GameState getCurrentGameState() {
        return currentGameState;
    }
    /**
     *  Memorizes current screen dimensions in the current panel.
     */
    public void setScreenDimension() {
        this.currentPanel.setScreenDimensions(d);
    }
    /**
    *  Draws current panel.
    */
    @Override
    public void draw() {
        SwingUtilities.invokeLater(() -> {
            this.currentPanel.draw();
            this.currentPanel.repaint();
        });
    }
}
