package model.algorithm;

import java.util.List;

/**
 * Abstrakcja węzła wykorzystywanego w przeszukiwaniu.
 */
public abstract class Node {

    /**
     * Wartość funkcji kosztu dotarcia f(n).
     */
    protected int fCost;

    /**
     * Wartość dotychczasowego kosztu dotarcia.
     */
    protected int gCost;

    /**
     * Koszt jednego ruchu.
     */
    protected int travelCost;

    /**
     * Rodzic węzła. Potrzebny do późniejszego przedstawienia rozwiązania.
     */
    private Node parent;

    /**
     * Wykorzystywana heurystyka.
     */
    protected Heuristic heuristic;

    /**
     * Konstruktor inicjalizujący początkowe wartości.
     */
    public Node(int gCost, Node parent, Heuristic heuristic) {
        this.gCost = gCost;
        this.parent = parent;
        this.heuristic = heuristic;
    }

    /**
     * Funkcja generująca następców węzła. Wymagana implementacja.
     * @return Lista następców węzła.
     */
    abstract public List<Node> generateDescendants();

    /**
     * Oblicza koszt dostarcia dla danego węzła.
     * @return Koszt dotarcia.
     */
    abstract public int calculateFCost();

    /**
     * Zwraca wartość funkcji kosztu dotarcia f(n).
     * @return Wartość funkcji kosztu dotarcia f(n).
     */
    public int getfCost() {
        return fCost;
    }

    /**
     * Zwraca wartość kosztu dotychczasowego.
     * @return Dotychczasowy koszt dotarcia.
     */
    int getgCost() {
        return gCost;
    }

    /**
     * Sprawdza, czy aktualny węzeł posiada rodzica.
     * @return true - posiada, false - nie posiada.
     */
    public boolean hasParent() {
        return parent != null;
    }

    /**
     * Zwraca rodzica danego węzła.
     * @return Rodzic danego węzła.
     */
    public Node getParent() {
        return parent;
    }
}
