package model.algorithm;

public class SearchStat {
    private Node finishNode;
    private int pathDepth;
    private int cost;

    public SearchStat(Node node) {
        this.finishNode = node;
        this.cost = node.getfCost();
        this.pathDepth = searchParent(node);
    }

    private int searchParent(Node node) {
        pathDepth = 1;
        if (node.getParent() != null) {
            pathDepth += searchParent(node.getParent());
        } else {
            return 1;
        }
        return pathDepth;
    }

    public Node getFinishNode() {
        return finishNode;
    }

    public int getPathDepth() {
        return pathDepth;
    }

    public int getCost() {
        return cost;
    }
}
