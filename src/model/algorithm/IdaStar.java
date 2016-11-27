package model.algorithm;

import model.game.SearchStat;

import java.util.List;

public class IdaStar extends InformativeSearch{

    public IdaStar(Node rootNode, Node targetNode, int nodesLimit) {
        super(rootNode, targetNode, nodesLimit);
    }

    @Override
    public SearchStat search() {
        int fCostLimit = rootNode.getfCost();
        int oldNodesLimit = nodesLimit;
        Node resultNode;
        do {
            resultNode = depthFirstSearch(rootNode, fCostLimit);
            if (resultNode != null) {
                int newLimit = resultNode.getfCost();
                if (newLimit > fCostLimit) {
                    fCostLimit = newLimit;
                }
            } else {
                return new SearchStat(null,0);
            }
            if (!isTargetConfiguration(resultNode)) {
                nodesLimit = oldNodesLimit;
            }
        } while (!isTargetConfiguration(resultNode));
        return new SearchStat(resultNode,oldNodesLimit - nodesLimit);
    }

    private Node depthFirstSearch(Node rootNode, int limit) {
        Node lowestFCostAboveLimitNode = null;
        List<Node> descendants = rootNode.generateDescendants();
        nodesLimit -= descendants.size();
        if (!descendants.isEmpty()) {
            for (Node descendant : descendants) {
                Node lastDescendatsDescendant = descendant;
                if (descendant.getfCost() <= limit) {
                    if (isTargetConfiguration(descendant)) {
                        return descendant;
                    } else {
                        lastDescendatsDescendant = depthFirstSearch(descendant, limit);
                        if (lastDescendatsDescendant == null) {
                            return null;
                        } else if (isTargetConfiguration(lastDescendatsDescendant)) {
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
                if (nodesLimit < 0) {
                    return null;
                }
            }
            nodesLimit++;
            return lowestFCostAboveLimitNode;
        } else {
            return rootNode;
        }
    }


}
