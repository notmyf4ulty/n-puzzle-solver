package model;

/**
 * Created by przemek on 20.11.16.
 */
public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        this(position.getX(),position.getY());
    }

    public boolean isOneUnitDistance(Position position) {
        boolean isXSame = this.x == position.x;
        boolean isYSame = this.y == position.y;
        boolean isOneXDistance = Math.abs(this.x - position.x) == 1;
        boolean isOneYDistance = Math.abs(this.y - position.y) == 1;

        return isOneXDistance && isYSame || isXSame && isOneYDistance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
