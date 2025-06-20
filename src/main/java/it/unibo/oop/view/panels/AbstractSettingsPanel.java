package it.unibo.oop.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import it.unibo.oop.view.window.ViewManager;

/**
 * Abstract panel for settings and pause screens, provides common buttons and layout.
 */
public abstract class AbstractSettingsPanel extends MyPanel {
    private static final long serialVersionUID = 1L;
    private static final int FONT_SIZE = 24;
    private static final int VERTICAL_BORDER = 10;
    private static final int ROWS = 3;
    private static final int COLUMNS = 2;
    private static final int GAP = 5;

    /** Field for entering custom screen size. */
    private final JTextField screenSizeField = new JTextField(10);
    /** Panel containing the screen size controls. */
    private final JPanel screenSizePanel = new JPanel(new BorderLayout());

    private boolean initialized;

    /**
     * Constructs the panel with common settings buttons.
     * @param screenWidth width of the panel
     * @param screenHeight height of the panel
     * @param drawView
     * @param title
     */
    public AbstractSettingsPanel(
        final int screenWidth,
        final int screenHeight,
        final ViewManager drawView, // NOPMD
        final String title // NOPMD
    ) {
        super.setPreferredSize(new Dimension(screenWidth, screenHeight));
        super.setLayout(new BorderLayout());
    }

    /**
     * Initializes the panel UI. Must be called after construction.
     * @param drawView the view manager
     */
    public final void initPanel(final ViewManager drawView) {
        if (initialized) {
            return;
        }
        initialized = true;

        final JLabel titleLabel = new JLabel(getTitle(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        super.add(titleLabel, BorderLayout.NORTH);

        final JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(BorderFactory.createEmptyBorder(VERTICAL_BORDER, VERTICAL_BORDER, VERTICAL_BORDER, VERTICAL_BORDER));

        final JPanel buttonPanel = new JPanel(new GridLayout(ROWS, COLUMNS, GAP, GAP));

        final JButton fullscreenButton = new JButton("Fullscreen");
        final JButton screenSizeButton = new JButton("Screen Size");
        final JButton volumeButton = new JButton("Volume");
        final JButton sfxButton = new JButton("SFX");
        final JButton returnButton = createReturnButton(drawView);
        addExtraButtons(buttonPanel, drawView);

        fullscreenButton.addActionListener(e -> toggleFullscreen());
        screenSizeButton.addActionListener(e -> changeScreenSize());
        screenSizeField.addActionListener(e -> changeScreenSize());

        screenSizePanel.add(screenSizeButton, BorderLayout.WEST);
        screenSizePanel.add(screenSizeField, BorderLayout.CENTER);

        buttonPanel.add(fullscreenButton);
        buttonPanel.add(screenSizePanel);
        buttonPanel.add(volumeButton);
        buttonPanel.add(sfxButton);
        buttonPanel.add(returnButton);

        outerPanel.add(buttonPanel, BorderLayout.CENTER);
        super.add(outerPanel, BorderLayout.WEST);
    }

    /**
     * Returns the panel title.
     * @return the title string
     */
    protected abstract String getTitle();

    /**
     * Creates the return button (template method).
     * @param drawView the view manager
     * @return the return button
     */
    protected abstract JButton createReturnButton(ViewManager drawView);

    /**
     * Hook for subclasses to add extra buttons.
     * @param buttonPanel the panel to add buttons to
     * @param drawView the view manager
     */
    protected void addExtraButtons(final JPanel buttonPanel, final ViewManager drawView) {
        // Default: do nothing. Subclasses can override to add extra buttons.
    }

    private void toggleFullscreen() {
        final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            final boolean isFullscreen = frame.getExtendedState() == JFrame.MAXIMIZED_BOTH;
            frame.setExtendedState(isFullscreen ? JFrame.NORMAL : JFrame.MAXIMIZED_BOTH);
        }
    }

    private void changeScreenSize() {
        final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            final String widthInput =
                JOptionPane.showInputDialog(frame, "Enter width:", "Screen Size", JOptionPane.PLAIN_MESSAGE);
            final String heightInput =
                JOptionPane.showInputDialog(frame, "Enter height:", "Screen Size", JOptionPane.PLAIN_MESSAGE);

            if (widthInput != null && heightInput != null) {
                try {
                    final int width = Integer.parseInt(widthInput);
                    final int height = Integer.parseInt(heightInput);
                    frame.setSize(width, height);
                    screenSizeField.setText(width + "x" + height);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Invalid input. Please enter numeric values.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    screenSizeField.setText("Invalid! Use: 1090x180");
                }
            }
        }
    }
}
