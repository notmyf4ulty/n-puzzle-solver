package model.algorithm;

import model.Block;
import model.PuzzleBoardModel;

import java.util.ArrayList;
import java.util.List;

public class AStar {

    List<AlgNodex> openList;
    List<AlgNodex> closedList;
    PuzzleBoardModel workedModel;

    public AStar(PuzzleBoardModel startPuzzleBoard) {
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        workedModel = startPuzzleBoard;
        AlgNodex startAlgNodex = new AlgNodex(startPuzzleBoard,new ManhattanDistanceHeristic(),0,null);
        openList.add(startAlgNodex);
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
        for (AlgNodex algNodex : openList) {
            algNodex.getPuzzleBoardModel().printBoard();
            System.out.println("Cost: " + algNodex.getfCost());
        }
    }

    public PuzzleBoardModel search() {
        AlgNodex currentAlgNodex;
        while (!openList.isEmpty()) {
            currentAlgNodex = getLowestFCostAlgNode();
            openList.remove(currentAlgNodex);
            closedList.add(currentAlgNodex);
            if (currentAlgNodex != null) {
                if (currentAlgNodex.isTargetConfiguration()) {
                    currentAlgNodex.getPuzzleBoardModel().printBoard();
                    System.out.println("Done");
                    return currentAlgNodex.getPuzzleBoardModel();
                } else {
                    List<AlgNodex> currentDescendants = currentAlgNodex.generateDescendants();
                    if (currentDescendants != null) {
                        for (AlgNodex algNodex : currentDescendants) {
                            if (openList.contains(algNodex)) {
                                AlgNodex containedAlgNodex = openList.get(openList.indexOf(algNodex));
                                if (algNodex.getgCost() < containedAlgNodex.getgCost()) {
                                    openList.remove(containedAlgNodex);
                                    openList.add(algNodex);
                                }
                            } else if (closedList.contains(algNodex)) {
                                AlgNodex containedAlgNodex = closedList.get(closedList.indexOf(algNodex));
                                if (algNodex.getgCost() < containedAlgNodex.getgCost()) {
                                    closedList.remove(containedAlgNodex);
                                    openList.add(algNodex);
                                }
                            } else {
                                openList.add(algNodex);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private AlgNodex getLowestFCostAlgNode() {
        if (openList != null) {
            AlgNodex lowestFCostAlgNodex = openList.get(0);
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
