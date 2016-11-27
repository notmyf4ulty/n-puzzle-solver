package model.algorithm;

import model.game.SearchStat;

import java.util.List;

/**
 * Klasa zajmująca się przesuzkiwaniem poinformowanym wg algorymu IDA*.
 */
public class IdaStar extends InformativeSearch{

    /**
     * Konstruktor oparty o konstrkutor klasy bazowej. Brak dodatkowych czynności.
     * @param rootNode Węzeł startowy.
     * @param targetNode Cel.
     * @param nodesLimit Limit rozwijanych węzłów.
     */
    public IdaStar(Node rootNode, Node targetNode, int nodesLimit) {
        super(rootNode, targetNode, nodesLimit);
    }

    /**
     * Implementacja algorytmu przeszukiwania poinformowanego IDA*głąb. W każdej iteracji sprawdzany jest węzeł,
     * który zwraca przeszukiwanie w głąb.
     * Jeżeli jest to cel - następuje koniec algorytmu.
     * Jeżeli jest to obiekt 'null' - nastąpiło przekroczenie dopuszczalnej liczby węzłów.
     * Jeżeli żadne z powyższych, algorytm kontynuuje przeszukiwanie ze zwiększoną granicą do wartości,
     * którą posiada zwrócony przez przeszukiwanie w głąb węzeł.
     * @return Obiekt klasy SearchStat, oparty na końcowym rowijanym węźle.
     */
    @Override
    public SearchStat search() {
        int fCostLimit = rootNode.getfCost();
        int oldNodesLimit = nodesLimit;
        Node resultNode;
        do {
            resultNode = depthFirstSearch(rootNode, fCostLimit);
            if (resultNode != null) {
                int newLimit = resultNode.getfCost();
                if (newLimit > fCostLimit) {
                    fCostLimit = newLimit;
                }
            } else {
                return new SearchStat(null,0);
            }
            if (!isTargetConfiguration(resultNode)) {
                nodesLimit = oldNodesLimit;
            }
        } while (!isTargetConfiguration(resultNode));
        return new SearchStat(resultNode,oldNodesLimit - nodesLimit);
    }

    /**
     * Przeszukiwanie w głąb. Rozwija drzewo przeszukiwań dla węzłów, które nie przekroczą aktualnej granicy funkcji
     * kosztu.
     * @param rootNode Węzeł początkowy.
     * @param limit Granica funkcji kosztu f(n).
     * @return Węzeł wynikowy. Jeżeli znaleziono węzeł-cel, jest on zwracany.
     * Jeżeli nie znaleziono węzła-celu, zwracany jest węzeł o najmniejszej wartości funkcji kosztu f(n),
     * większej od aktualnej granicy. Jeżeli przekroczono limit maksymalnej liczby rozwijanych węzłów,
     * zwracany jest obiekt 'null', który zostanie odpowiednio przetworzony w metodzie 'search'.
     */
    private Node depthFirstSearch(Node rootNode, int limit) {
        Node lowestFCostAboveLimitNode = null;
        List<Node> descendants = rootNode.generateDescendants();
        nodesLimit -= descendants.size();
        if (!descendants.isEmpty()) {
            for (Node descendant : descendants) {
                Node lastDescendatsDescendant = descendant;
                if (descendant.getfCost() <= limit) {
                    if (isTargetConfiguration(descendant)) {
                        return descendant;
                    } else {
                        lastDescendatsDescendant = depthFirstSearch(descendant, limit);
                        if (lastDescendatsDescendant == null) {
                            return null;
                        } else if (isTargetConfiguration(lastDescendatsDescendant)) {
                            return lastDescendatsDescendant;
                        }
                    }
                } else {
                    if ((lowestFCostAboveLimitNode == null) ||
                            (lastDescendatsDescendant.getfCost() < lowestFCostAboveLimitNode.getfCost())) {
                        lowestFCostAboveLimitNode = lastDescendatsDescendant;
                    }
                }
                if (lastDescendatsDescendant.getfCost() > limit) {
                    if ((lowestFCostAboveLimitNode == null) ||
                            lastDescendatsDescendant.getfCost() < lowestFCostAboveLimitNode.getfCost()) {
                        lowestFCostAboveLimitNode = lastDescendatsDescendant;
                    }
                }
                if (nodesLimit < 0) {
                    return null;
                }
            }
            nodesLimit++;
            return lowestFCostAboveLimitNode;
        } else {
            return rootNode;
        }
    }


}
