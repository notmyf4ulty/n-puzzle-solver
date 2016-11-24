package model.algorithm;

import model.Block;
import model.PuzzleBoardModel;

import java.util.ArrayList;
import java.util.List;

public class AStar {

    List<AlgNode> openList;
    List<AlgNode> closedList;
    PuzzleBoardModel workedModel;

    public AStar(PuzzleBoardModel startPuzzleBoard) {
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        workedModel = startPuzzleBoard;
        AlgNode startAlgNode = new AlgNode(startPuzzleBoard,new ManhattanDistanceHeristic(),0,null);
        openList.add(startAlgNode);
    }

    public void printPuzzle(PuzzleBoardModel startPuzzleBoard) {
        System.out.println("--- Current choice ---");
        startPuzzleBoard.printBoard();
        System.out.println("--- Available moves ---");
        for (PuzzleBoardModel puzzleBoardModel : generateDescendants(startPuzzleBoard)) {
            puzzleBoardModel.printBoard();
        }
        System.out.println("---------");
    }

    private void printOpenList() {
        System.out.println("Available moves:");
        for (AlgNode algNode : openList) {
            algNode.getPuzzleBoardModel().printBoard();
            System.out.println("Cost: " + algNode.getfCost());
        }
    }

    public PuzzleBoardModel search() {
        AlgNode currentAlgNode;
        while (!openList.isEmpty()) {
            currentAlgNode = getLowestFCostAlgNode();
            openList.remove(currentAlgNode);
            closedList.add(currentAlgNode);
            if (currentAlgNode != null) {
                if (currentAlgNode.isTargetConfiguration()) {
                    currentAlgNode.getPuzzleBoardModel().printBoard();
                    System.out.println("Done");
                    return currentAlgNode.getPuzzleBoardModel();
                } else {
                    List<AlgNode> currentDescendants = currentAlgNode.generateDescendants();
                    if (currentDescendants != null) {
                        for (AlgNode algNode : currentDescendants) {
                            if (openList.contains(algNode)) {
                                AlgNode containedAlgNode = openList.get(openList.indexOf(algNode));
                                if (algNode.getgCost() < containedAlgNode.getgCost()) {
                                    openList.remove(containedAlgNode);
                                    openList.add(algNode);
                                }
                            } else if (closedList.contains(algNode)) {
                                AlgNode containedAlgNode = closedList.get(closedList.indexOf(algNode));
                                if (algNode.getgCost() < containedAlgNode.getgCost()) {
                                    closedList.remove(containedAlgNode);
                                    openList.add(algNode);
                                }
                            } else {
                                openList.add(algNode);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private AlgNode getLowestFCostAlgNode() {
        if (openList != null) {
            AlgNode lowestFCostAlgNode = openList.get(0);
            for (int i = 1; i < openList.size(); i++) {
                if (openList.get(i).getfCost() < lowestFCostAlgNode.getfCost()) {
                    lowestFCostAlgNode = openList.get(i);
                }
            }
            return lowestFCostAlgNode;
        } else {
            return null;
        }
    }

    private PuzzleBoardModel [] generateDescendants(PuzzleBoardModel parentPuzzleBoard) {
        Block[] blocks = parentPuzzleBoard.getEmptyBlockNeighbours();
        PuzzleBoardModel[] puzzleBoardModels = new PuzzleBoardModel[blocks.length];
        for (int i = 0 ; i < blocks.length ; i++) {
            PuzzleBoardModel descendant = parentPuzzleBoard.getCopy();
            descendant.interchangeEmptyBlock(blocks[i]);
            puzzleBoardModels[i] = descendant;
        }
        return puzzleBoardModels;
    }
}
