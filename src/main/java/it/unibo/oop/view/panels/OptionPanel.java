package it.unibo.oop.view.panels;

import javax.swing.JButton;

import it.unibo.oop.utils.GameState;
import it.unibo.oop.view.window.ViewManager;

/**
 * Panel for game settings.
 */
public final class OptionPanel extends AbstractSettingsPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs the option panel.
     * @param screenWidth width of the panel
     * @param screenHeight height of the panel
     * @param drawView
     */
    public OptionPanel(final int screenWidth, final int screenHeight, final ViewManager drawView) {
        super(screenWidth, screenHeight, drawView, "Settings");
        initPanel(drawView);
    }

    @Override
    protected String getTitle() {
        return "Settings";
    }

    /**
     * This button brings the user back to the Title panel.
     */
    @Override
    protected JButton createReturnButton(final ViewManager drawView) {
        final JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> drawView.changeGameState(GameState.TITLESTATE));
        return returnButton;
    }
}
