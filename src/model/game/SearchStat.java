package model.game;

import model.algorithm.Node;

public class SearchStat {
    private Node finishNode;
    private int pathDepth;
    private int cost;
    private boolean nodesLimitError;
    private int visitedNodesNumber;

    public SearchStat(Node node, int visitedNodesNumber) {
        if (node != null) {
            this.finishNode = node;
            this.cost = node.getfCost();
            this.pathDepth = searchParent(node);
            this.visitedNodesNumber = visitedNodesNumber;
        } else {
            nodesLimitError = true;
        }
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

    public String printPath() {
        int positionCounter = 0;
        Node traversedNode = finishNode;
        String path = getNodeEmptyBlockPosition(traversedNode);
        while (traversedNode.hasParent()) {
            traversedNode = traversedNode.getParent();
            path = getNodeEmptyBlockPosition(traversedNode) + path;
            positionCounter++;
            if(positionCounter % 4 == 0) {
                path += "\n";
            }
        }
        return path;
    }

    private String getNodeEmptyBlockPosition(Node node) {
        return ((PuzzleBoardNode) node).getPuzzleBoardModel().getEmptyBlock().getPosition().toString();
    }

    Node getFinishNode() {
        return finishNode;
    }

    public int getPathDepth() {
        return pathDepth;
    }

    public int getCost() {
        return cost;
    }

    public boolean isNodesLimitError() {
        return nodesLimitError;
    }

    public int getVisitedNodesNumber() {
        return visitedNodesNumber;
    }
}
