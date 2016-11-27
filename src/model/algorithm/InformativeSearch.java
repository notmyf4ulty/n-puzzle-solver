package model.algorithm;

import model.game.SearchStat;

public abstract class InformativeSearch {

    Node rootNode;
    private Node targetNode;
    int nodesLimit;

    InformativeSearch(Node rootNode, Node targetNode, int nodesLimit) {
        this.rootNode = rootNode;
        this.targetNode = targetNode;
        this.nodesLimit = nodesLimit;
    }

    abstract public SearchStat search();

    boolean isTargetConfiguration(Node node) {
        return node.equals(targetNode);
    }
}
