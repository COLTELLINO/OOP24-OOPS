package it.unibo.oop.utils;
/**
 * 
 */
public enum Direction {
    /**
    * 
    */
    NONE, UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNRIGHT, DOWNLEFT;

    public Direction getOpposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            case UPLEFT -> DOWNRIGHT;
            case UPRIGHT -> DOWNLEFT;
            case DOWNRIGHT -> UPLEFT;
            case DOWNLEFT -> UPRIGHT;
            case NONE -> NONE;
        };
    }
}
