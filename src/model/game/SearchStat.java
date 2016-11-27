package model.game;

import model.algorithm.Node;

public class SearchStat {
    private Node finishNode;
    private int pathDepth;
    private boolean nodesLimitError;
    private int visitedNodesNumber;

    public SearchStat(Node node, int visitedNodesNumber) {
        if (node != null) {
            this.finishNode = node;
            this.pathDepth = searchParent(node);
            this.visitedNodesNumber = visitedNodesNumber;
            copyRootParent();
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

    private void copyRootParent() {
        Node finishNode = this.finishNode;
        while (finishNode.hasParent()) {
            finishNode = finishNode.getParent();
        }
        PuzzleBoardNode finishPuzzleBoardNode = (PuzzleBoardNode) finishNode;
        finishPuzzleBoardNode.setPuzzleBoardModel(finishPuzzleBoardNode.getPuzzleBoardModel().getCopy());
    }

    private String getNodeEmptyBlockPosition(Node node) {
        PuzzleBoardNode puzzleBoardNode = (PuzzleBoardNode) node;
        return puzzleBoardNode.getPuzzleBoardModel().getEmptyBlock().getPosition().toString();
    }

    Node getFinishNode() {
        return finishNode;
    }

    public int getPathDepth() {
        return pathDepth;
    }

    public boolean isNodesLimitError() {
        return nodesLimitError;
    }

    public int getVisitedNodesNumber() {
        return visitedNodesNumber;
    }
}
