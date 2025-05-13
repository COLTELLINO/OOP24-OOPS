package it.unibo.oop.utils;

import java.util.List;

/**
 * 
 */
public enum Direction {
    /**
    * 
    */
    NONE, UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNRIGHT, DOWNLEFT;

    /**
    * Returns the opposite direction of the current direction.
    *
    * @return the opposite direction
    */
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

    /**
    * Returns a list of vertical and horizontal directions.
    *
    * @return a list containing UP, DOWN, LEFT, and RIGHT directions
    */
    public static List<Direction> verticalHorizontal() {
        return List.of(UP, DOWN, LEFT, RIGHT);
    }
}
