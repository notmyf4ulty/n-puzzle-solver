package model.algorithm;

import java.util.List;

/**
 * Created by przemek on 24.11.16.
 */
public abstract class Node {

    int fCost;
    int gCost;
    int hCost;
    Node parent;
    Heuristic heuristic;

    public Node(int gCost, Node parent, Heuristic heuristic) {
        this.gCost = gCost;
        this.parent = parent;
        this.heuristic = heuristic;
    }

    abstract public List<Node> generateDescendants();

    public int getfCost() {
        return fCost;
    }
}
