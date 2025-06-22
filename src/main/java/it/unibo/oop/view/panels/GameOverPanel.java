package it.unibo.oop.view.panels;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Panel for the game over screen.
 */
@SuppressFBWarnings(value = {"DM_EXIT"},
        justification = "drawView is not exposed, but is used to change the game state."
                      + "System.exit(0) is used to close all the Threads when the quit button is pressed."
    )
public final class GameOverPanel extends MyPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs the game over panel.
     * @param screenWidth width of the panel
     * @param screenHeight height of the panel
     */
    public GameOverPanel(final int screenWidth, final int screenHeight) {
        super.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        super.setLayout(new BorderLayout());

        final JLabel titleLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE * 2));
        super.add(titleLabel, BorderLayout.NORTH);

        final JPanel buttonPanel = new JPanel(new GridLayout(3, 1, GAP, GAP));
        buttonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(
            VERTICAL_BORDER, HORIZONTAL_BORDER, VERTICAL_BORDER, HORIZONTAL_BORDER));

        buttonPanel.add(new JLabel());
        final JButton returnButton = new JButton("Quit");
        returnButton.setFont(new JButton().getFont());
        returnButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(returnButton).dispose();
            System.exit(0);
        });
        buttonPanel.add(returnButton);
        buttonPanel.add(new JLabel());

        super.add(buttonPanel, BorderLayout.CENTER);
    }
}
