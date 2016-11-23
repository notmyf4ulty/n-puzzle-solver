package model;

import java.util.ArrayList;

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

    public Position [] getNeighbouringPositions(int border) {
        Position [] positions = new Position[4];
        positions[0] = new Position(x, y - 1);
        positions[1] = new Position(x, y + 1);
        positions[2] = new Position(x - 1, y);
        positions[3] = new Position(x + 1, y);

        ArrayList<Position> candidatePositions = new ArrayList<>();

        for (Position position : positions) {
            if (position.getX() < border && position.getY() < border &&
                    position.getX() >= 0 && position.getY() >= 0) {
                candidatePositions.add(position);
            }
        }

        Position [] candidatesPositionsArray = new Position [candidatePositions.size()];
        candidatesPositionsArray = candidatePositions.toArray(candidatesPositionsArray);

        return candidatesPositionsArray;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(this.getClass())) {
            Position position = (Position) o;
            return position.getX() == x && position.getY() == y;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
