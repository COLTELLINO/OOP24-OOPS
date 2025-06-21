package it.unibo.oop.view.panels;

import javax.swing.JButton;

import it.unibo.oop.controller.controllers.AudioController;
import it.unibo.oop.utils.GameState;
import it.unibo.oop.view.window.ViewManager;

/**
 * Panel for the pause screen.
 */
public final class PausePanel extends AbstractSettingsPanel {
    private static final long serialVersionUID = 1L;
    /**
     * Constructs the pause panel.
     * @param screenWidth width of the panel
     * @param screenHeight height of the panel
     * @param drawView
     */
    public PausePanel(final int screenWidth, final int screenHeight, final ViewManager drawView, 
                     final AudioController audioController) {
        super(screenWidth, screenHeight, drawView, "PAUSA", audioController);
        initPanel(drawView);
    }

    @Override
    protected String getTitle() {
        return "PAUSA";
    }

    /**
     * This button that allows to return to the game.
     */
    @Override
    protected JButton createReturnButton(final ViewManager drawView) {
        final JButton returnButton = new JButton("Return to game");
        returnButton.addActionListener(e -> drawView.changeGameState(GameState.PLAYSTATE));
        return returnButton;
    }

    /**
     * Adds a button to exit the game and return to the title screen.
     * @param buttonPanel the panel to add buttons to
     * @param drawView
     */
    @Override
    protected void addExtraButtons(final javax.swing.JPanel buttonPanel, final ViewManager drawView) {
        final JButton exitButton = new JButton("Exit the game");
        exitButton.addActionListener(e -> drawView.changeGameState(GameState.TITLESTATE));
        buttonPanel.add(exitButton);
    }
}
