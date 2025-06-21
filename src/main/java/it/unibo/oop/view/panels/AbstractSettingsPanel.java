package it.unibo.oop.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import it.unibo.oop.controller.controllers.AudioController;
import it.unibo.oop.utils.Percentage;
import it.unibo.oop.view.window.ViewManager;

/**
 * Abstract panel for settings and pause screens, provides common buttons and layout.
 */
public abstract class AbstractSettingsPanel extends MyPanel {
    private static final long serialVersionUID = 1L;
    private static final int FONT_SIZE = 24;
    private static final int SIZE_SELECTOR_WIDTH = 500;
    private static final int SIZE_SELECTOR_HEIGHT = 400;
    /** Field for entering custom screen size. */
    private final JTextField screenSizeField = new JTextField(10);
    private final AudioController audioController;

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
        final ViewManager drawView,
        final String title,
        final AudioController audioController
    ) {
        this.audioController = audioController;
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

        final JPanel buttonPanel = new JPanel(new GridLayout(ROWS, COLUMNS, GAP, GAP));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(VERTICAL_BORDER, HORIZONTAL_BORDER, VERTICAL_BORDER, HORIZONTAL_BORDER));

        final JButton fullscreenButton = new JButton("Fullscreen");
        final JButton screenSizeButton = new JButton("Screen Size");
        final JButton returnButton = createReturnButton(drawView);

        fullscreenButton.addActionListener(e -> toggleFullscreen());
        screenSizeButton.addActionListener(e -> changeScreenSize());
        screenSizeField.addActionListener(e -> changeScreenSize());
        final JButton increaseVolume = new JButton("Volume +");
        final JButton decreaseVolume = new JButton("Volume -");

        final JLabel volumePercentageLabel = new JLabel(getVolumeLabelText(audioController.getVolume()), SwingConstants.CENTER);
        volumePercentageLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE - 4));
        volumePercentageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // padding top and bottom

        decreaseVolume.addActionListener(e -> updateVolume(-1, volumePercentageLabel));
        increaseVolume.addActionListener(e -> updateVolume(1, volumePercentageLabel));

        buttonPanel.add(fullscreenButton);
        buttonPanel.add(screenSizeButton);
        buttonPanel.add(increaseVolume);
        buttonPanel.add(decreaseVolume);
        buttonPanel.add(returnButton);
        addExtraButtons(buttonPanel, drawView);

        super.add(buttonPanel, BorderLayout.CENTER);
        super.add(volumePercentageLabel, BorderLayout.SOUTH);
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
    /**
     * Changes the screen size based on user selection.
     */
    private void changeScreenSize() {
        final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        final JFrame sizeSelector = new JFrame("Select Screen Size");
        sizeSelector.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sizeSelector.setLayout(new GridLayout(0, 1, 10, 10));
        sizeSelector.setSize(SIZE_SELECTOR_WIDTH, SIZE_SELECTOR_HEIGHT);
        sizeSelector.setLocationRelativeTo(frame);
        final List<Dimension> sizes = List.of(
            new Dimension(800, 600),
            new Dimension(1024, 768),
            new Dimension(1280, 720),
            new Dimension(1366, 768),
            new Dimension(1920, 1080)
        );
        for (final Dimension size : sizes) {
            final JButton sizeButton = new JButton(size.width + " x " + size.height);
            sizeButton.addActionListener(e -> {
                resizeFrameCentered(frame, size.width, size.height);
                screenSizeField.setText(size.width + "x" + size.height);
                sizeSelector.dispose();
            });
            sizeSelector.add(sizeButton);
        }
        sizeSelector.setVisible(true);
    }
    /**
     * Resizes the frame to new dimensions, keeping it centered on the screen.
     * @param frame
     * @param newWidth
     * @param newHeight
     */
    private void resizeFrameCentered(final JFrame frame, final int newWidth, final int newHeight) {
        final int centerX = frame.getX() + frame.getWidth() / 2;
        final int centerY = frame.getY() + frame.getHeight() / 2;
        int newX = centerX - newWidth / 2;
        int newY = centerY - newHeight / 2;
        final Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        if (newX < 0) {
            newX = 0;
        } else if (newX + newWidth > screenSize.width) {
            newX = screenSize.width - newWidth;
        }
        if (newY < 0) {
            newY = 0;
        } else if (newY + newHeight > screenSize.height) {
            newY = screenSize.height - newHeight;
        }
        frame.setSize(newWidth, newHeight);
        frame.setLocation(newX, newY);
    }
    private int getCurrentVolumeIndex() {
        final Percentage[] values = Percentage.values();
        final Percentage current = audioController.getVolume();
        for (int i = 0; i < values.length; i++) {
            if (values[i] == current) {
                return i;
            }
        }
        return 0;
    }
    private void updateVolume(final int direction, final JLabel label) {
        final Percentage[] values = Percentage.values();
        int currentIndex = getCurrentVolumeIndex();
        int newIndex = Math.max(0, Math.min(values.length - 1, currentIndex + direction));
        audioController.setVolume(values[newIndex]);
        if (label != null) {
            label.setText(getVolumeLabelText(values[newIndex]));
        }
    }
    private String getVolumeLabelText(final Percentage p) {
        return "Volume: " + (int) (p.getPercentage() * 100) + "%";
    }
}
