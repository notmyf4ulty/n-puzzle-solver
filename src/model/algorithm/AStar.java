package model.algorithm;

import model.game.SearchStat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStar extends InformativeSearch {
    private List<Node> openList;
    private List<Node> closedList;

    public AStar(Node rootNode, Node targetNode, int nodesLimit) {
        super(rootNode, targetNode, nodesLimit);
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        openList.add(rootNode);
    }

    @Override
    public SearchStat search() {
        Node currentNode;
        int nodesLimit = this.nodesLimit;
        while (!openList.isEmpty()) {
            currentNode = getLowestFCostAlgNode();
            openList.remove(currentNode);
            closedList.add(currentNode);
            if (isTargetConfiguration(currentNode)) {
                return new SearchStat(currentNode, this.nodesLimit - nodesLimit);
            } else {
                for (Node descendantNode : currentNode.generateDescendants()) {
                    nodesLimit--;
                    if (openList.contains(descendantNode)) {
                        Node containedNode = openList.get(openList.indexOf(descendantNode));
                        if (descendantNode.getgCost() < containedNode.getgCost()) {
                            openList.remove(containedNode);
                            openList.add(descendantNode);
                            closedList.add(containedNode);
                        } else {
                            closedList.add(descendantNode);
                        }
                    } else if (closedList.contains(descendantNode)) {
                        Node containedNode = closedList.get(closedList.indexOf(descendantNode));
                        if (descendantNode.getgCost() < containedNode.getgCost()) {
                            openList.add(descendantNode);
                        } else {
                            closedList.add(descendantNode);
                        }
                    } else {
                        openList.add(descendantNode);
                    }
                    if (nodesLimit <= 0) {
                        return new SearchStat(null,0);
                    }
                }
            }
        }
        return null;
    }

    private Node getLowestFCostAlgNode() {
        Collections.sort(openList, (node, t1) -> node.getfCost() - t1.getfCost());

        if (openList != null) {
            Node lowestFCostAlgNodex = openList.get(0);
            for (int i = 1; i < openList.size(); i++) {
                if (openList.get(i).getfCost() < lowestFCostAlgNodex.getfCost()) {
                    lowestFCostAlgNodex = openList.get(i);
                }
            }
            return lowestFCostAlgNodex;
        } else {
            return null;
        }
    }
}
