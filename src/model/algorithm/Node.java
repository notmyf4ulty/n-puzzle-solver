package model.algorithm;

import java.util.List;

/**
 * Created by przemek on 24.11.16.
 */
public abstract class Node {

    protected int fCost;
    protected int gCost;
    protected int hCost;
    protected Node parent;
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

    public int getgCost() {
        return gCost;
    }
}
