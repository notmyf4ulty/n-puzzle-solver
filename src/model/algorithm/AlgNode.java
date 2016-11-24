package model.algorithm;

import model.Block;
import model.PuzzleBoardModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AlgNode {
    private static final int TRAVEL_COST = 1;
    private PuzzleBoardModel puzzleBoardModel;
    private Heuristic heuristic;
    private int gCost;
    private int fCost;
    private final AlgNode parent;

    public AlgNode(PuzzleBoardModel puzzleBoardModel, Heuristic heuristic, int gCost, AlgNode parent) {
        this.puzzleBoardModel = puzzleBoardModel;
        this.heuristic = heuristic;
        this.gCost = gCost + TRAVEL_COST;
        this.fCost = computeFCost();
        this.parent = parent;
    }

    private int computeFCost() {
        return gCost + heuristic.manhattanDistance(puzzleBoardModel);
    }

    public List<AlgNode> generateDescendants() {
        List<AlgNode> descendants = new ArrayList<>();
        for (PuzzleBoardModel puzzleBoardModel : generateDescendantPuzzleBoards(this.puzzleBoardModel)) {
            descendants.add(new AlgNode(puzzleBoardModel,heuristic,gCost,this));
        }
        return descendants;
    }

    private PuzzleBoardModel [] generateDescendantPuzzleBoards(PuzzleBoardModel parentPuzzleBoard) {
        Block[] blocks = parentPuzzleBoard.getEmptyBlockNeighbours();
        PuzzleBoardModel[] puzzleBoardModels = new PuzzleBoardModel[blocks.length];
        for (int i = 0 ; i < blocks.length ; i++) {
            PuzzleBoardModel descendant = parentPuzzleBoard.getCopy();
            descendant.interchangeEmptyBlock(blocks[i]);
            puzzleBoardModels[i] = descendant;
        }
        return puzzleBoardModels;
    }

    public boolean isTargetConfiguration() {
        for (Block[] blocks : puzzleBoardModel.getBoard()) {
            for (Block block : blocks) {
                if (!block.isOnTargetPosition()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlgNode algNode = (AlgNode) o;

        return puzzleBoardModel.equals(algNode.puzzleBoardModel);

    }

    @Override
    public int hashCode() {
        return puzzleBoardModel.hashCode();
    }

    public PuzzleBoardModel getPuzzleBoardModel() {
        return puzzleBoardModel;
    }

    public int getgCost() {
        return gCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public int getfCost() {
        return fCost;
    }

    public void setfCost(int fCost) {
        this.fCost = fCost;
    }

    public AlgNode getParent() {
        return parent;
    }
}
