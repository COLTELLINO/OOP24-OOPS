package it.unibo.oop.model.projectiles;

import it.unibo.oop.utils.Direction;

public class Arrow extends Projectile {

    public Arrow(int x, int y, Direction direction, int damage, int speed, int size) {
        super(x, y, direction, damage, speed, size);
        switch (this.getDirection()) {
            case UP -> {
                this.setY(this.getY() - 40);
                this.setX(this.getX() - 8);
            }
            case DOWN -> {
                this.setX(this.getX() + 25);
                this.setY(this.getY() + 50);
            }
            case LEFT -> {
                this.setY(this.getY() + 25);
                this.setX(this.getX() - 25);
            }
            case RIGHT -> {
                this.setY(this.getY() - 10);
                this.setX(this.getX() + 50);
            }
            default -> throw new IllegalArgumentException("Invalid direction");
        }
    }
    /**
    * @return the name of the projectile class
    */
    public String getProjectileName() {
        return this.getClass().getSimpleName();
    }
}
