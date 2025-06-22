package it.unibo.oop.utils;
/**
 * Enum representing sound indices for different sound effects and music tracks.
 */
public enum AudioIndex {
    /**
     * 
     */
    EXPLOSION(0), HIT(1), SHOT(2),
    /**
     * 
     */
    XP(3), SELECT(4), MUSIC_OOP_ADVENTURE(5);

    private final int index;
    /**
     * Constructor for SoundIndex enum.
     * @param index
     */
    AudioIndex(final int index) {
        this.index = index;
    }
    /**
     * @return the index of the sound
     */
    public int getIndex() {
        return index;
    }
}
