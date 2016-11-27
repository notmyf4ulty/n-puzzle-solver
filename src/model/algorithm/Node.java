package model.algorithm;

import java.util.List;

public abstract class Node {

    protected int fCost;
    protected int gCost;
    protected int travelCost;
    private Node parent;
    protected Heuristic heuristic;

    public Node(int gCost, Node parent, Heuristic heuristic) {
        this.gCost = gCost;
        this.parent = parent;
        this.heuristic = heuristic;
    }

    abstract public List<Node> generateDescendants();
    abstract public int calculateFCost();

    public int getfCost() {
        return fCost;
    }

    int getgCost() {
        return gCost;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public Node getParent() {
        return parent;
    }
}
