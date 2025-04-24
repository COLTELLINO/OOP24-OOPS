package it.unibo.oop.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.controller.GameState;
/**
 * 
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "The purpose of this class is to interact with the game window,"
              + "so it needs to be able to use drawView to change the state of the game.")
public class TitlePanel extends MyPanel {
    @SuppressWarnings("unused") // TEMPORARY
    private static final double serialVersionUID = getSerialVersionUID();
    private final DrawView drawView;

    /**
     * @param screenWidth
     * @param screenHeight
     * @param drawView to handle game state changes
     */
    public TitlePanel(final int screenWidth, final int screenHeight, final DrawView drawView) {
        super.setPreferredSize(new Dimension(screenWidth, screenHeight));
        super.setLayout(new BorderLayout());
        this.drawView = drawView;

        final JLabel titleLabel = new JLabel("OOP Survivors", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        super.add(titleLabel, BorderLayout.NORTH);

        final JPanel buttonPanel = new JPanel(new GridLayout(ROWS, COLUMNS, GAP, GAP));
        buttonPanel.
        setBorder(BorderFactory.createEmptyBorder(VERTICAL_BORDER, HORIZONTAL_BORDER, VERTICAL_BORDER, HORIZONTAL_BORDER));

        final JButton newGameButton = new JButton("New Game");
        final JButton loadGameButton = new JButton("Load Game");
        final JButton settingsButton = new JButton("Settings");
        final JButton quitButton = new JButton("Quit");

        newGameButton.addActionListener(e -> this.drawView.changeGameState(GameState.PLAYSTATE));
        settingsButton.addActionListener(e -> this.drawView.changeGameState(GameState.TITLEOPTIONSTATE));
        quitButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(quitButton).dispose();
        });

        buttonPanel.add(newGameButton);
        buttonPanel.add(loadGameButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(quitButton);

        super.add(buttonPanel, BorderLayout.CENTER);
    }
}
