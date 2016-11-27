package model.algorithm;

import model.game.SearchStat;

/**
 * Klasa przeszukiwania poinformowanego. Klasy po niej dziedziczące muszą zaimplementować metodę przeszukiwania
 * poinformowanego 'search()'.
 */
public abstract class InformativeSearch {

    /**
     * Węzeł początkowy.
     */
    Node rootNode;

    /**
     * Cel.
     */
    private Node targetNode;

    /**
     * Limit rozwijanych węzłów.
     */
    int nodesLimit;

    /**
     * Konstruktor inicjalizujacy obiekty klasy.
     * @param rootNode Węzeł początkowy.
     * @param targetNode Cel.
     * @param nodesLimit Limit rozwijanych wezłów.
     */
    InformativeSearch(Node rootNode, Node targetNode, int nodesLimit) {
        this.rootNode = rootNode;
        this.targetNode = targetNode;
        this.nodesLimit = nodesLimit;
    }

    /**
     * Metoda przeszukiwania poinformowanego.
     * @return Obiekt klasy SearchStat zawierający statystyki z przeszukiwania.
     */
    abstract public SearchStat search();

    /**
     * Sprawdza, czy zadany węzeł jest węzłem-celem.
     * @param node Sprawdzany węzeł.
     * @return True - jeśli to węzeł-cel, false w przeciwnym wypadku.
     */
    boolean isTargetConfiguration(Node node) {
        return node.equals(targetNode);
    }
}
