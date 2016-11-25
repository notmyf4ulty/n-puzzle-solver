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
            int newLimit = resultNode.getfCost();
            if (newLimit > limit) {
                limit = newLimit;
            }
        } while (!isTargetConfiguration(resultNode) && (limit < 10000));
        return resultNode;
    }

    private Node depthFirstSearch(Node rootNode, int limit) {
        Node lowestFCostAboveLimitNode = null;
        List<Node> descendants = rootNode.generateDescendants();
        if (!descendants.isEmpty()) {
            for (Node descendant : descendants) {
                Node lastDescendatsDescendant = descendant;
                if (descendant.getfCost() <= limit) {
                    if (isTargetConfiguration(descendant)) {
                        return descendant;
                    } else {
                        lastDescendatsDescendant = depthFirstSearch(descendant, limit);
                        if (isTargetConfiguration(lastDescendatsDescendant)) {
                            return lastDescendatsDescendant;
                        }
                    }
                } else {
                    if ((lowestFCostAboveLimitNode == null) ||
                            (lastDescendatsDescendant.getfCost() < lowestFCostAboveLimitNode.getfCost())) {
                        lowestFCostAboveLimitNode = lastDescendatsDescendant;
                    }
                }
                if (lastDescendatsDescendant.getfCost() > limit) {
                    if ((lowestFCostAboveLimitNode == null) ||
                            lastDescendatsDescendant.getfCost() < lowestFCostAboveLimitNode.getfCost()) {
                        lowestFCostAboveLimitNode = lastDescendatsDescendant;
                    }
                }
            }
            return lowestFCostAboveLimitNode;
        } else {
            return rootNode;
        }
    }


}
