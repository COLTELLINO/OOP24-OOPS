package it.unibo.oop.utils;
/**
 * 
 */
public enum Percentage {
    /**
     * 
     */
    TEN_PERCENT(0.1), TWENTY_PERCENT(0.2), THIRTY_PERCENT(0.3),
    /**
     * 
     */
    FOURTY_PERCENT(0.4), FIFTY_PERCENT(0.5), SIXTY_PERCENT(0.6),
    /**
     * 
     */
    SEVENTY_PERCENT(0.7), EIGHTY_PERCENT(0.8), NINETY_PERCENT(0.9),
    /**
     * 
     */
    ONE_HUNDRED_PERCENT(1.0);

    private final double percentage;
    /**
     * @param percentage
     */
    Percentage(final double percentage) {
        this.percentage = percentage;
    }
    /**
     * @return the percentage value as a double.
     */
    public double getPercentage() {
        return percentage;
    }
}
