package it.unibo.oop.view.panels;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import it.unibo.oop.utils.GameState;
import it.unibo.oop.view.window.ViewManager;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Panel for the game over screen.
 */
public final class GameOverPanel extends MyPanel {
    private static final long serialVersionUID = 1L;

    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "drawView is not exposed"
    )
    private final transient ViewManager drawView;

    /**
     * Constructs the game over panel.
     * @param screenWidth width of the panel
     * @param screenHeight height of the panel
     * @param drawView
     */
    public GameOverPanel(final int screenWidth, final int screenHeight, final ViewManager drawView) {
        super.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        super.setLayout(new BorderLayout());
        this.drawView = drawView;

        final JLabel titleLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE * 2));
        super.add(titleLabel, BorderLayout.NORTH);

        final JPanel buttonPanel = new JPanel(new GridLayout(3, 1, GAP, GAP));
        buttonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(
            VERTICAL_BORDER, HORIZONTAL_BORDER, VERTICAL_BORDER, HORIZONTAL_BORDER));

        buttonPanel.add(new JLabel());
        final JButton returnButton = new JButton("Return");
        returnButton.setFont(new JButton().getFont());
        returnButton.addActionListener(e -> this.drawView.changeGameState(GameState.TITLESTATE));
        buttonPanel.add(returnButton);
        buttonPanel.add(new JLabel());

        super.add(buttonPanel, BorderLayout.CENTER);
    }
}
