package model.game;

import java.util.ArrayList;

/**
 * Model pozycji zajmowanej przez bloczek. Należy zwrócić uwagę na fakt, że kierunek osi y jest przeciwny niż w układzie
 * kartezjańskim.
 */
public class Position {

    /**
     * Współrzędna x.
     */
    private final int x;

    /**
     * Współrzędna y.
     */
    private final int y;

    /**
     * Konstruktor inicjalizujący podstawowe wartości pól.
     */
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Konstrktor oparty na istniejącym obiekcie klasy.
     */
    Position(Position position) {
        this(position.getX(),position.getY());
    }

    /**
     * Sprawdza, czy pomiędzy aktualną, a zadaną pozycją jest prostopadła odległość jednego bloczka.
     * @param position Zadana pozycja do sprawdzenia.
     * @return true - pomiedzy pozycjami jest odległość jednego bloczka. false - jest inna odległość.
     */
    boolean isOneUnitDistance(Position position) {
        boolean isXSame = this.x == position.x;
        boolean isYSame = this.y == position.y;
        boolean isOneXDistance = Math.abs(this.x - position.x) == 1;
        boolean isOneYDistance = Math.abs(this.y - position.y) == 1;

        return isOneXDistance && isYSame || isXSame && isOneYDistance;
    }

    /**
     * Zwraca sąsiadujące pozycje. Zwracane są tylko te pozycje, które posiadają dodatnie lub równe zeru wartości
     * współrzędnych i takie, które są mniejsze od zadanej granicy.
     * @param border Granica dla wspórzędnych pozycji.
     * @return Tablica sąsiadujących, poprawnych pozycji.
     */
    Position [] getNeighbouringPositions(int border) {
        Position [] positions = new Position[4];
        positions[0] = new Position(x, y - 1);
        positions[1] = new Position(x, y + 1);
        positions[2] = new Position(x - 1, y);
        positions[3] = new Position(x + 1, y);

        ArrayList<Position> candidatePositions = new ArrayList<>();

        for (Position position : positions) {
            if (validatePosition(position, border)) {
                candidatePositions.add(position);
            }
        }

        Position [] candidatesPositionsArray = new Position [candidatePositions.size()];
        candidatesPositionsArray = candidatePositions.toArray(candidatesPositionsArray);

        return candidatesPositionsArray;
    }

    /**
     * Sprawdza, czy pozycjia jest poprawna, tj. Czy wartości współrzędnych nie są mniejsze od zera
     * i czy nie przekraczają zadanej granicy.
     * @param position Pozycja do sprawdzenia.
     * @param border Granica współrzędnych.
     * @return true - pozycja jest poprawna, false - nie jest.
     */
    static boolean validatePosition(Position position, int border) {
        return position.getX() < border && position.getY() < border &&
                position.getX() >= 0 && position.getY() >= 0;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
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

    /**
     * Zwraca współrzędną x.
     */
    public int getX() {
        return x;
    }

    /**
     * Zwraca współrzędna y.
     */
    public int getY() {
        return y;
    }
}
