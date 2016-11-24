package model.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by przemek on 24.11.16.
 */
public class AStar extends InformativeSearch {
    List<Node> openList;
    List<Node> closedList;

    public AStar(Node rootNode, Node targetNode) {
        super(rootNode, targetNode);
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        openList.add(rootNode);
    }

    @Override
    public Node fullSearch() {
        Node currentNode;
        while (!openList.isEmpty()) {
            currentNode = getLowestFCostAlgNode();
            openList.remove(currentNode);
            closedList.add(currentNode);
            if (isTargetConfiguration(currentNode)) {
                return currentNode;
            } else {
                for (Node descendantNode : currentNode.generateDescendants()) {
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
                }

            }
        }
        return null;
    }

    private Node getLowestFCostAlgNode() {
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
