package model.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by przemek on 24.11.16.
 */
public class AStar<T extends Node> extends InformativeSearch<T> {
    List<T> openList;
    List<T> closedList;

    public AStar(T rootNode, T targetNode) {
        super(rootNode, targetNode);
        openList = new ArrayList<T>();
        closedList = new ArrayList<T>();
        openList.add(rootNode);
    }

    @Override
    SearchStat search() {
        T currentNode;
        while (!openList.isEmpty()) {
            currentNode = getLowestFCostAlgNode();
            openList.remove(currentNode);
            closedList.add(currentNode);
            
        }
    }

    private T getLowestFCostAlgNode() {
        if (openList != null) {
            T lowestFCostAlgNodex = openList.get(0);
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
