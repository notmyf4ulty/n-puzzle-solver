package model.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by przemek on 25.11.16.
 */
public class IdaStar extends InformativeSearch{
    LinkedList<Node> openList;
    int currentFCostLimit = 0;

    public IdaStar(Node rootNode, Node targetNode) {
        super(rootNode, targetNode);
        openList = new LinkedList<>();
        openList.push(rootNode);
    }

    @Override
    public Node fullSearch() {
        int limit = rootNode.getfCost();
        Node resultNode;
        do {
            resultNode = depthFirstSearch(rootNode, limit);
            limit = resultNode.getfCost();
        } while (!isTargetConfiguration(resultNode) && (limit < 10000));
        return resultNode;
    }

    private Node depthFirstSearch(Node rootNode, int limit) {
        Node lowestFCostAboveLimitNode = rootNode;
        List<Node> descendants = rootNode.generateDescendants();
        if (!descendants.isEmpty()) {
            for (Node descendant : descendants) {
                if (descendant.getfCost() <= limit) {
                    if (isTargetConfiguration(descendant)) {
                        return descendant;
                    } else {
                        return depthFirstSearch(descendant, limit);
                    }
                } else if (lowestFCostAboveLimitNode.equals(rootNode) ||
                        (lowestFCostAboveLimitNode.getfCost() >= descendant.getfCost())) {
                    lowestFCostAboveLimitNode = descendant;
                }
            }
            return lowestFCostAboveLimitNode;
        } else if (isTargetConfiguration(rootNode)) {
            return rootNode;
        } else {
            return lowestFCostAboveLimitNode;
        }
    }


}
